package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.Events;
import hu.hardcore.FPEweblap.model.dto.EventsIUDTO;
import hu.hardcore.FPEweblap.model.dto.NewsPageIUDTO;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.Base64;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class EventsServiceImpl extends BaseServiceInterfaceImpl<Events> implements EventsService {

    private final Environment environment;


    protected EventsServiceImpl(Environment environment) {
        super(Events.class);
        this.environment = environment;
    }

    @Override
    public List<Events> listByPaging(Integer pageIndex, Integer pageSize, Date date, Map<String, String> filterParams){
        List<Predicate> predicates = new ArrayList<Predicate>(2);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Events> query = builder.createQuery(Events.class);
        Root<Events> root = query.from(Events.class);

        //Egy szűrési kritérium arra, hogy ne legyen törölt elem visszahozva
        Predicate notDeletedPredicate = builder.equal(root.get("deleted"), 0);
        predicates.add(notDeletedPredicate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1; // Calendar months are 0-based, so add 1

        //Dekrementáljuk az oldal indexet eggyel, hogy ha az első oldalnál vagyunk, akkor 10-es oldalméret esetén az első
        //10 jöjjön vissza, és így tovább. Így ha 1-es page index és 10-es page size esetén a resultok kezdete 0*10, tehát 0 lesz.
        //A page size pedig egyértelmű, a visszahozandó találatok számát jelöli.
        Integer firstResult = --pageIndex*pageSize;

        StringBuilder builder1 = new StringBuilder()
                .append("SELECT * FROM events ");
//        if(date!= null && !filterParams.isEmpty()){
//
//        }
        if(date!=null) {
          builder1.append(" WHERE ");
            builder1.append("extract(MONTH FROM event_date) = ")
                .append(month)
                .append(" AND extract(year FROM event_date) = ")
                .append(year);
        }
        if(filterParams.containsKey("title")){
            if(date == null) {
                builder1.append(" AND ");
            }
            builder1.append(" tile LIKE %");
            builder1.append(filterParams.get("title"));
            builder1.append("% ");
        }
                builder1.append(" ORDER BY created_on DESC LIMIT ")
                .append(pageSize)
                .append(" OFFSET ")
                .append(firstResult);
        Query typedQuery = getEntityManager().createNativeQuery(builder1.toString(), Events.class);

        List<Events> eventsList = (List<Events>) typedQuery.getResultList();

        return eventsList;


    }

    @Override
    public Long eventListCount(Date date, Map<String, String> filterParams) {
        List<Predicate> predicates = new ArrayList<>(2);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Events> root = query.from(Events.class);

        // Szűrési kritérium arra, hogy ne legyen törölt elem visszahozva
        Predicate notDeletedPredicate = builder.equal(root.get("deleted"), 0);
        predicates.add(notDeletedPredicate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1; // Calendar months are 0-based, so add 1

        StringBuilder builder1 = new StringBuilder()
                .append("SELECT count(id) FROM events");

        boolean whereClauseAdded = false;

        if (date != null) {
            builder1.append(" WHERE extract(MONTH FROM event_date) = ")
                    .append(month)
                    .append(" AND extract(YEAR FROM event_date) = ")
                    .append(year);
            whereClauseAdded = true;
        }

        if (filterParams.containsKey("title")) {
            if (!whereClauseAdded) {
                builder1.append(" WHERE ");
            } else {
                builder1.append(" AND ");
            }
            builder1.append("title LIKE '%")
                    .append(filterParams.get("title"))
                    .append("%' ");
        }

        // Execute the query
        Query typedQuery = getEntityManager().createNativeQuery(builder1.toString());

        Long count = ((Number) typedQuery.getSingleResult()).longValue();

        return count;

    }


    @Override
    public void softDelete(Long id) {
        super.doDelete(id, 1);
    }

    @Override
    public void softUndelete(Long id) {
        super.doDelete(id, 0);
    }

    @Override
    public void saveWithImg(EventsIUDTO eventsIUDTO) {
        try {
            Events events = setPropertiesFromRequest(eventsIUDTO);
            this.save(events);
        }catch (Exception e){
            log.info("saveWithImg - ", e);
        }
    }

    private Events setPropertiesFromRequest(EventsIUDTO eventsIUDTO) throws Exception {

        Events events = new Events();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        events.setEventDate(new Timestamp(dateFormat.parse(eventsIUDTO.getEventDate()).getTime()));
        events.setFacebookEventLink(eventsIUDTO.getFacebookEventLink());
        events.setText(eventsIUDTO.getText());
        events.setTitle(eventsIUDTO.getTitle());
        events.setDeleted(0);

        if(eventsIUDTO.getId()!=null){
            events.setId(eventsIUDTO.getId());

            if(eventsIUDTO.getImage() == null) {
                super.getEntityManager().detach(events);

                Events original = super.findById(eventsIUDTO.getId());
                super.getEntityManager().detach(original);

                events.setImageUrl(original.getImageUrl());
            }else{
                Path imgSavedTo = handleFileSave(eventsIUDTO);
                events.setImageUrl(imgSavedTo.toString());
            }
        }else{
            Path imgSavedTo = handleFileSave(eventsIUDTO);
            events.setImageUrl(imgSavedTo.toString());
        }

        return events;
    }

    private Path handleFileSave(EventsIUDTO eventsIUDTO) throws Exception {
        try {
            String base64 = eventsIUDTO.getImage().getFile().replaceAll(getBase64RemovableRegex(eventsIUDTO), "");
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
                imgFile = new File(getDestination(eventsIUDTO, imgExists, imgNameCount));
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

    private String getBase64RemovableRegex(EventsIUDTO eventsIUDTO){
        StringBuilder builder = new StringBuilder();
        builder.append("data:")
                .append(eventsIUDTO.getImage().getType())
                .append(";base64,");

        return builder.toString();

    }

    private String getDestination(EventsIUDTO eventsIUDTO, boolean imgExists, int imgNameCount) {
        StringBuilder builder = new StringBuilder();
        String baseDestination = environment.getProperty("file.server.dir");
        String name = eventsIUDTO.getImage().getName().substring(0, eventsIUDTO.getImage().getName().lastIndexOf('.'));
        String extension = eventsIUDTO.getImage().getName().substring(eventsIUDTO.getImage().getName().lastIndexOf('.')+1);

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
