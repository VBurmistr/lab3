package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Publisher;

public interface PublisherDAO {
    Integer save(Publisher publisher) throws DAOException;
    Integer getIdByValues(Publisher publisher) throws DAOException;
}
