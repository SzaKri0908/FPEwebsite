package hu.hardcore.FPEweblap.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.hardcore.FPEweblap.exceptionhandling.exception.restexception.GeneralFPEException;
import hu.hardcore.FPEweblap.service.GalleryImageService;
import hu.hardcore.FPEweblap.util.ConstantStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryImageController {

    private final GalleryImageService galleryImageService;

    private final ObjectMapper jsonMapper;

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity insert(MultipartHttpServletRequest request){
        try{
            galleryImageService.saveWithImg(request);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Sikeres mentés.");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (HibernateException e){
            log.error("insert: Hiba a mentés során. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a mentés során.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (GeneralFPEException e){
            log.error("insert: Hiba a mentés során. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("insert: Hiba a mentés során. Exception: ",e);
            ObjectNode response = jsonMapper.createObjectNode();
            response.put("message", "Hiba a mentés során.");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = ConstantStore.PRODUCES_JSON)
    public ResponseEntity delete(@RequestParam Long id){
        try{
            galleryImageService.softDelete(id);
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

}
