package nc.apps.smartadder.controllers.exceptiocontroller;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dto.ExceptionResponse;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({DAOException.class, ServiceException.class, RestFacadeException.class})
    public ResponseEntity<ExceptionResponse> daoExceptionHandler(Exception ex){
        return new ResponseEntity<ExceptionResponse>(
                new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        System.currentTimeMillis(),ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
