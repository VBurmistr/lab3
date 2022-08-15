package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Author;

public interface AuthorDAO {
    Integer save(Author author) throws DAOException;
    Integer getIdByValues(Author author) throws DAOException;

}
