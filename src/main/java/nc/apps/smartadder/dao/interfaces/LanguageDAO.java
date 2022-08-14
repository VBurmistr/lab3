package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Language;

public interface LanguageDAO {
    Integer save(Language language) throws DAOException;
    Integer getIdByValues(Language language) throws DAOException;

}
