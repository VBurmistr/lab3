package nc.apps.smartadder.controllers.exceptiocontroller;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dto.ErrorObject;
import nc.apps.smartadder.dto.ResponseObject;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler({DAOException.class, ServiceException.class, RestFacadeException.class})
    public ResponseEntity<ResponseObject<ErrorObject>> daoExceptionHandler(Exception ex) {
        log.error("Handled exception in controller advance:", ex);
        ErrorObject errorObject = new ErrorObject(500,ex.getMessage(),System.currentTimeMillis());
        ResponseObject<ErrorObject> responseObject = new ResponseObject<>(false,errorObject);
        return new ResponseEntity<>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
