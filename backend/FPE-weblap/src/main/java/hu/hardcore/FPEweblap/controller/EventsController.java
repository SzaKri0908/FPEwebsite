package hu.hardcore.FPEweblap.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.hardcore.FPEweblap.model.Events;
import hu.hardcore.FPEweblap.model.NewsPage;
import hu.hardcore.FPEweblap.model.dto.EventsIUDTO;
import hu.hardcore.FPEweblap.service.EventsService;
import hu.hardcore.FPEweblap.util.ConstantStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.Files;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity insert(@RequestBody List<EventsIUDTO> dtos){
        try{
            for(EventsIUDTO dto : dtos) {
                eventsService.saveWithImg(dto);
            }
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Sikeres mentés.");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (HibernateException e){
            log.error("insert: Hiba a mentés során. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a mentés során.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity update(@RequestBody EventsIUDTO dto){
        try{
            eventsService.saveWithImg(dto);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Sikeres módosítás.");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (HibernateException e){
            log.error("update: Hiba a módosítás során. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a módosítás során.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity delete(@RequestParam Long id){
        try{
            eventsService.softDelete(id);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Sikeres törlés.");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (HibernateException e){
            log.error("delete: Hiba a törlés során. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a törlés során.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/undelete", method = RequestMethod.DELETE, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity undelete(@RequestParam Long id){
        try{
            eventsService.softUndelete(id);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Sikeres visszaállítás.");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (HibernateException e){
            log.error("undelete: Hiba a visszaállítás során. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a visszaállítás során.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
