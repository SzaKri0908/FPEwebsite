package hu.hardcore.FPEweblap.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.hardcore.FPEweblap.model.GalleryImage;
import hu.hardcore.FPEweblap.model.GalleryView;
import hu.hardcore.FPEweblap.model.dto.EmailDTO;
import hu.hardcore.FPEweblap.model.dto.EventsDTO;
import hu.hardcore.FPEweblap.model.dto.ListByPagingDTO;
import hu.hardcore.FPEweblap.model.dto.NewsPageDTO;
import hu.hardcore.FPEweblap.service.EmailService;
import hu.hardcore.FPEweblap.service.EventsService;
import hu.hardcore.FPEweblap.service.GalleryImageService;
import hu.hardcore.FPEweblap.service.NewsPageService;
import hu.hardcore.FPEweblap.util.ConstantStore;
import hu.hardcore.FPEweblap.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {

    private final NewsPageService newsPageService;

    private final EventsService eventsService;

    private final GalleryImageService galleryImageService;

    private final EmailService emailService;

    private final ObjectMapper objectMapper;
    @RequestMapping(value = "/listNews", method = RequestMethod.GET, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity listNews(@RequestParam final int pageCount,
                                   @RequestParam final int pageSize,
                                   @RequestParam(required = false) final String title) throws JsonProcessingException {

        return new ResponseEntity(ObjectMapperUtil.writeValueAsString(newsPageService.listByPaging(pageCount, pageSize, title)), HttpStatus.OK);
    }

    @RequestMapping(value = "/listEvents", method = RequestMethod.GET, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity listEvents(@RequestParam final int pageCount,
                                     @RequestParam final int pageSize,
                                     @RequestParam Date date,
                                     @RequestParam(required = false) final String filter) throws JsonProcessingException {

        Map<String,String> filterParams;
        if(filter!=null && !filter.isEmpty() && !filter.isBlank()){
            filterParams = objectMapper.readValue(filter, new TypeReference<Map<String, String>>() {});
        }else{
            filterParams = new HashMap<>();
        }

        List<EventsDTO> dtos = eventsService.listByPaging(pageCount, pageSize, date, filterParams)
                .stream()
                .map(EventsDTO::new)
                .collect(Collectors.toList());
        Long count = eventsService.eventListCount(date, filterParams);
        ListByPagingDTO result = new ListByPagingDTO(count, dtos);
        return new ResponseEntity(ObjectMapperUtil.writeValueAsString(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/listGallery", method = RequestMethod.GET, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity listGallery(@RequestParam final int pageCount, @RequestParam final int pageSize) throws JsonProcessingException {

        List<GalleryView> dtos = galleryImageService.listByPaging(pageCount, pageSize);

        return new ResponseEntity(ObjectMapperUtil.writeValueAsString(dtos), HttpStatus.OK);
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity sendEmail(@RequestBody EmailDTO emailDTO){

        emailService.sendEmail(emailDTO);

        return new ResponseEntity(ObjectMapperUtil.objectNode().put("reason", "Sikeres email küldés"), HttpStatus.OK);

    }

    /*@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity uploadFile(MultipartHttpServletRequest request){
        final String destionation = "http://localhost:80/upload";
        try {
            MultipartFile multipartFile = request.getFile("file");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(multipartFile.getBytes()) {
                @Override
                public String getFilename() {
                    return multipartFile.getOriginalFilename();
                }
            });

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(destionation, requestEntity, String.class);

            return new ResponseEntity("Success", HttpStatus.OK);
        }catch (Exception ex){
            log.error("uploadFile: ",ex);
            return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


}
