package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Book;

public interface BookDAO {
    void save(Book book) throws DAOException;

}
