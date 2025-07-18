package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Default Role not exist")
public class DefaultRoleNotFoundException  extends RuntimeException{
    public DefaultRoleNotFoundException (String message){
        super(message);
    }
}
