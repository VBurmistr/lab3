package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Category;

public interface CategoryDAO {
    Integer save(Category category) throws DAOException;
    Integer getIdByValues(Category category) throws DAOException;

}
