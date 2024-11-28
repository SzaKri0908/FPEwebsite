package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.exceptionhandling.exception.restexception.GeneralFPEException;
import hu.hardcore.FPEweblap.model.GalleryImage;
import hu.hardcore.FPEweblap.model.GalleryView;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class GalleryImageServiceImpl extends BaseServiceInterfaceImpl<GalleryImage> implements GalleryImageService{

    private final Environment environment;

    public GalleryImageServiceImpl(Environment environment){
        super(GalleryImage.class);
        this.environment = environment;
    }

    @Override
    public void saveWithImg(MultipartHttpServletRequest request) throws Exception {
        try {
            List<GalleryImage> galleryImages = setPropertiesFromRequest(request);
            for(GalleryImage galleryImage : galleryImages) {
                this.save(galleryImage);
            }
        }catch (Exception e){
            log.info("saveWithImg - ", e);
            if(e instanceof GeneralFPEException){
                throw new GeneralFPEException(e.getMessage());
            }else{
                throw e;
            }
        }
    }

    @Override
    public void softDelete(Long id) {
        super.doDelete(id, 1);
    }

    @Override
    public List<GalleryView> listByPaging(int pageCount, int pageSize) {
        List<Predicate> predicates = new ArrayList<Predicate>(2);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GalleryView> query = builder.createQuery(GalleryView.class);
        Root<GalleryView> root = query.from(GalleryView.class);

        query.select(root);//.orderBy(builder.desc(root.get("createdOn")));


        //Dekrementáljuk az oldal indexet eggyel, hogy ha az első oldalnál vagyunk, akkor 10-es oldalméret esetén az első
        //10 jöjjön vissza, és így tovább. Így ha 1-es page index és 10-es page size esetén a resultok kezdete 0*10, tehát 0 lesz.
        //A page size pedig egyértelmű, a visszahozandó találatok számát jelöli.
        Integer firstResult = --pageCount*pageSize;
        return getEntityManager().createQuery(query).setFirstResult(firstResult).setMaxResults(pageSize).getResultList();
    }

    private List<GalleryImage> setPropertiesFromRequest(MultipartHttpServletRequest request) throws Exception {
        List<MultipartFile> multipartFiles = request.getFiles("file");
        List<GalleryImage> galleryImages = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles) {
            checkIfFileExists(multipartFile);
            Path imgSavedTo = handleFileSave(multipartFile);
            GalleryImage galleryImage = new GalleryImage();

            galleryImage.setFacebookLink(request.getParameter("facebookLink"));
            galleryImage.setImgUrl(imgSavedTo.toAbsolutePath().toString());

            galleryImages.add(galleryImage);

            if (request.getParameter("id") != null) {
                galleryImage.setId(Long.parseLong(request.getParameter("id")));
            }
        }
        return galleryImages;
    }

    private void checkIfFileExists(MultipartFile multipartFile) throws GeneralFPEException {
        List<Predicate> predicates = new ArrayList<Predicate>(2);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GalleryImage> query = builder.createQuery(GalleryImage.class);
        Root<GalleryImage> root = query.from(GalleryImage.class);

        //Egy szűrési kritérium arra, hogy ne legyen törölt elem visszahozva
        Predicate notDeletedPredicate = builder.equal(root.get("deleted"), 0);
        Predicate withFileName =
                builder.or(
                        builder.like(root.get("imgUrl"), "%/"+multipartFile.getOriginalFilename()),
                        builder.like(root.get("imgUrl"), "%\\"+multipartFile.getOriginalFilename())
                );
        predicates.add(notDeletedPredicate);
        predicates.add(withFileName);

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        List<GalleryImage> galleryImages = getEntityManager().createQuery(query).setMaxResults(1).getResultList();

        if(!galleryImages.isEmpty()){
            throw new GeneralFPEException("Ezzel a fjálnévvel már létezik file: "+multipartFile.getOriginalFilename());
        }
    }

    private Path handleFileSave(MultipartFile multipartFile) throws Exception {
        try {
            byte[] imgBytes = multipartFile.getBytes();
            File imgFile = new File(getDestination(multipartFile));

            return Files.write(imgFile.toPath(), imgBytes);


        }catch (Exception e){
            log.info("handleFileSave - ",e);
            throw e;
        }
    }

    private String getDestination(MultipartFile multipartFile) {
        StringBuilder builder = new StringBuilder();
        String baseDestination = environment.getProperty("gallery.img.dir");

        if(File.separator.equals("\\")){
            baseDestination = baseDestination.replaceAll("/", "\\\\");
        }

        builder.append(baseDestination);
        builder.append(File.separator);
        builder.append(multipartFile.getOriginalFilename());

        return builder.toString();
    }

}
