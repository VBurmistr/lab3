package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Category;

public interface CategoryDAO {
    Long save(Category category) throws DAOException;
    Long getIdByValues(Category category) throws DAOException;

}
