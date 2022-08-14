package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Author;

public interface AuthorDAO {
    Long save(Author author) throws DAOException;
    Long getIdByValues(Author author) throws DAOException;

}
