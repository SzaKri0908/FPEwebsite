package hu.hardcore.FPEweblap.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.hardcore.FPEweblap.model.NewsPage;
import hu.hardcore.FPEweblap.model.dto.NewsPageIUDTO;
import hu.hardcore.FPEweblap.service.NewsPageService;
import hu.hardcore.FPEweblap.util.ConstantStore;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/newsPage")
public class NewsPageController {

    private final NewsPageService newsPageService;
    private final ObjectMapper jsonMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity findAll(){
        try{
            //Lekérjük az összes találatot. Azért alakítjuk Stringgé, mert ugyan a Spring is ezt teszi a háttérben,
            //Felmerülhetnek hibák amiket elrejt előlünk, és nem értjük miért van. Olyan hibát, ami az átalakításkor történik.
            List<NewsPage> result = newsPageService.findAll();
            String resultAsString = jsonMapper.writeValueAsString(result);
            return new ResponseEntity(resultAsString, HttpStatus.OK);
        }catch (NoResultException e){
            log.error("findAll: Nem található rekordok. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Nincsenek posztok felvéve.");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            log.error("findAll: Jsonné alakítási hiba. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a lista összeállítása közben.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity findById(@RequestParam Long id){
        try{
            NewsPage result = newsPageService.findById(id);
            String resultAsString = jsonMapper.writeValueAsString(result);
            return new ResponseEntity(resultAsString, HttpStatus.OK);
        }catch (NoResultException e){
            log.error("findById: Nem található reklord a megadott id-val. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Nem található bejegyzés.");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }catch (JsonProcessingException e) {
            log.error("findById: Jsonné alakítási hiba. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a poszt keresése.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity insert(@RequestBody List<NewsPageIUDTO> newsPages){
        try{
            for(NewsPageIUDTO newsPageIUDTO : newsPages) {
                newsPageService.saveWithImg(newsPageIUDTO);
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
    public ResponseEntity update(@RequestBody NewsPageIUDTO newsPages){
        try{
            newsPageService.saveWithImg(newsPages);
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
            newsPageService.softDelete(id);
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
            newsPageService.softUndelete(id);
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
