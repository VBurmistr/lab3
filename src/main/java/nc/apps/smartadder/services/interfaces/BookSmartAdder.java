package nc.apps.smartadder.services.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.services.exceptions.ServiceException;

public interface BookSmartAdder {
    Book addNewBook(String title, String author) throws DAOException, RestFacadeException, ServiceException;
}
