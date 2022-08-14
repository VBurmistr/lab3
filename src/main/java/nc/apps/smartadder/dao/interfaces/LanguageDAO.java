package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Language;

public interface LanguageDAO {
    Long save(Language language) throws DAOException;
    Long getIdByValues(Language language) throws DAOException;

}
