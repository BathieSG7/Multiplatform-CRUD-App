package ssamba.ept.sn.BankerApp.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() { }

    public ResourceNotFoundException(String entity, Long id) {
        super(entity + " id " + id + " introuvable");
    }

    public ResourceNotFoundException(String entity, String id) {
        super(entity + " id " + id + " introuvable");
    }
    public ResourceNotFoundException(String entity, int id) {
        super(entity + " id " + id + " introuvable");
    }
}