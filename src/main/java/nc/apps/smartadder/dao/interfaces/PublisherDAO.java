package nc.apps.smartadder.dao.interfaces;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Publisher;

public interface PublisherDAO {
    Long save(Publisher publisher) throws DAOException;
    Long getIdByValues(Publisher publisher) throws DAOException;
}
