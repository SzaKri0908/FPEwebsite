package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.Events;
import hu.hardcore.FPEweblap.model.NewsPage;
import hu.hardcore.FPEweblap.model.dto.ListByPagingDTO;
import hu.hardcore.FPEweblap.model.dto.NewsPageDTO;
import hu.hardcore.FPEweblap.model.dto.NewsPageIUDTO;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class NewsPageServiceImpl extends BaseServiceInterfaceImpl<NewsPage> implements NewsPageService {

    private final Environment environment;

    //Az alapvető Service számára biztosítjuk a perzisztálható osztályt amivel dolgozni akarunk
    public NewsPageServiceImpl(Environment environment) {
        super(NewsPage.class);
        this.environment=environment;
    }

    //Egyszerű minden lekérés
    public List<NewsPage> findAll(){
        return super.findAll();
    }

    //Egyszerű Id alapú megtalálás
    public NewsPage findById(Long id){
        return super.findById(id);
    }

    //Név alapján keresünk
    public List<NewsPage> findByTitle(String title){
        StringBuilder titleBuilder = new StringBuilder();
        //Első sorban fogjuk a címet, és kisbetűssé tesszük, hogy ne legyen para a case sensitivityvel
        titleBuilder.append(title.toLowerCase());
        //A JPA Criteria számára hozzáadunk egy % jelet, így a kezdés is elég.
        titleBuilder.append("%");


        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<NewsPage> query = builder.createQuery(NewsPage.class);
        Root<NewsPage> root = query.from(NewsPage.class);

        //Specifikáljuk hogy az egész entitást akarjuk, és a címre szűrünk rá
        query.select(root).where(builder.equal(builder.lower(root.get("title")), titleBuilder.toString()));

        return getEntityManager().createQuery(query).getResultList();
    }

    //Pagniálásos szűrés
    public ListByPagingDTO listByPaging(Integer pageIndex, Integer pageSize, String title){
        List<Predicate> predicates = new ArrayList<Predicate>(2);
        List<Predicate> countPredicates = new ArrayList<Predicate>(2);

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<NewsPage> query = builder.createQuery(NewsPage.class);
        Root<NewsPage> root = query.from(NewsPage.class);

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<NewsPage> countRoot = countQuery.from(NewsPage.class);

        //Egy szűrési kritérium arra, hogy ne legyen törölt elem visszahozva
        Predicate notDeletedPredicate = builder.equal(root.get("deleted"), 0);
        Predicate countNotDeletedPredicate = builder.equal(countRoot.get("deleted"), 0);
        predicates.add(notDeletedPredicate);
        countPredicates.add(countNotDeletedPredicate);

        //Ha akarnak névre szűrni
        if(title!=null) {
            StringBuilder titleBuilder = new StringBuilder();
            titleBuilder.append(title.toLowerCase());
            titleBuilder.append("%");
            Predicate titlePredicate = builder.like(builder.lower(root.get("title")), titleBuilder.toString());
            Predicate countTitlePredicate = builder.like(builder.lower(countRoot.get("title")), titleBuilder.toString());
            predicates.add(titlePredicate);
            countPredicates.add(countTitlePredicate);
        }

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(builder.desc(root.get("createdOn")));
        countQuery.select(builder.count(countRoot)).where(countPredicates.toArray(predicates.toArray(new Predicate[predicates.size()])));

        //Dekrementáljuk az oldal indexet eggyel, hogy ha az első oldalnál vagyunk, akkor 10-es oldalméret esetén az első
        //10 jöjjön vissza, és így tovább. Így ha 1-es page index és 10-es page size esetén a resultok kezdete 0*10, tehát 0 lesz.
        //A page size pedig egyértelmű, a visszahozandó találatok számát jelöli.
        Integer firstResult = --pageIndex*pageSize;
        List<NewsPage> resultList = getEntityManager().createQuery(query).setFirstResult(firstResult).setMaxResults(pageSize).getResultList();
        Long count = getEntityManager().createQuery(countQuery).getSingleResult();

        List<NewsPageDTO> dtos = resultList
                .stream()
                .map(NewsPageDTO::new)
                .collect(Collectors.toList());
        return new ListByPagingDTO(count, dtos);


    }

    public void softDelete(Long id){
        super.doDelete(id,1);
    }

    public void softUndelete(Long id){
        super.doDelete(id,0);
    }


    @Override
    public void saveWithImg(NewsPageIUDTO newsPageIUDTO) {
        try {
            NewsPage newsPage = setPropertiesFromRequest(newsPageIUDTO);
            this.save(newsPage);
        } catch (Exception e) {
            log.error("saveWithImg - ", e); // Changed to log error
        }
    }

    private NewsPage setPropertiesFromRequest(NewsPageIUDTO newsPageIUDTO) throws Exception {
        NewsPage newsPage = new NewsPage();
        newsPage.setFacebookLink(newsPageIUDTO.getFacebookLink());
        newsPage.setText(newsPageIUDTO.getText()); // Fixed the text setting
        newsPage.setTitle(newsPageIUDTO.getTitle());
        newsPage.setDeleted(0);
        newsPage.setIsMainNews(newsPageIUDTO.getIsMainNews());

        if (newsPageIUDTO.getId() != null) {
            newsPage.setId(newsPageIUDTO.getId());
            if (newsPageIUDTO.getImage() == null) {
                super.getEntityManager().detach(newsPage);

                NewsPage original = super.findById(newsPage.getId());
                super.getEntityManager().detach(original);

                newsPage.setImageUrl(original.getImageUrl());
            } else {
                Path imgSavedTo = handleFileSave(newsPageIUDTO);
                newsPage.setImageUrl(imgSavedTo.toString());
            }
        } else {
            Path imgSavedTo = handleFileSave(newsPageIUDTO);
            newsPage.setImageUrl(imgSavedTo.toString());
        }

        return newsPage;
    }

    private Path handleFileSave(NewsPageIUDTO newsPageIUDTO) throws Exception {
        try {
            String base64 = newsPageIUDTO.getImage().getFile().replaceAll(getBase64RemovableRegex(newsPageIUDTO), "");
            byte[] imgBytes = Base64.decodeBase64(base64);

            // Convert byte array to BufferedImage
            InputStream is = new ByteArrayInputStream(imgBytes);
            BufferedImage bufferedImage = ImageIO.read(is);

            if (bufferedImage == null) {
                throw new IOException("Decoded byte array is not a valid image.");
            }

            // Ensure destination path and filename are set correctly
            File imgFile;
            boolean imgExists = false;
            int imgNameCount = 0;
            do {
                imgFile = new File(getDestination(newsPageIUDTO, imgExists, imgNameCount));
                imgExists = true;
                imgNameCount++;
            } while (imgFile.exists());

            Files.write(imgFile.toPath(), imgBytes);


            // Verifying if the file was written correctly
            if (imgFile.length() == 0) {
                throw new IOException("File writing failed, no data written.");
            }

            return imgFile.toPath();
        } catch (Exception e) {
            log.error("handleFileSave - ", e); // Changed to log error
            throw e;
        }
    }

    private String getBase64RemovableRegex(NewsPageIUDTO newsPageIUDTO){
        StringBuilder builder = new StringBuilder();
        builder.append("data:")
               .append(newsPageIUDTO.getImage().getType())
                .append(";base64,");

        return builder.toString();

    }

    private String getDestination(NewsPageIUDTO newsPageIUDTO, boolean imgExists, int imgNameCount) {
        StringBuilder builder = new StringBuilder();
        String baseDestination = environment.getProperty("file.server.dir");
        String name = newsPageIUDTO.getImage().getName().substring(0, newsPageIUDTO.getImage().getName().lastIndexOf('.'));
        String extension = newsPageIUDTO.getImage().getName().substring(newsPageIUDTO.getImage().getName().lastIndexOf('.')+1);

        if (File.separator.equals("\\")) {
            baseDestination = baseDestination.replace("/", "\\\\");
        }

        builder.append(baseDestination);
        builder.append(File.separator);
        builder.append(name);
        if(imgExists){
            builder.append("(");
            builder.append(imgNameCount);
            builder.append(")");
        }
        builder.append(".");
        builder.append(extension);

        return builder.toString();
    }




}
