package nc.apps.smartadder.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dao.interfaces.PublisherDAO;
import nc.apps.smartadder.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class PublisherDAOImpl implements PublisherDAO {

    private final DataSource dataSource;

    public PublisherDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static final String SQL_ADD_NEW = "INSERT INTO lab3_publisher_table (publisher_name) VALUES (?) ON CONFLICT DO NOTHING RETURNING id";
    public static final String SQL_GET_ID_BY_VALUES = "SELECT id from lab3_publisher_table where publisher_name = ? ";

    @Override
    public Integer save(Publisher publisher) throws DAOException {
        log.info("Add new author query:"+SQL_ADD_NEW);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)){
            statement.setString(1,publisher.getPublisherName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving publisher:"+publisher,e);
        }
    }

    @Override
    public Integer getIdByValues(Publisher publisher) throws DAOException {
        log.info("Add new author query:"+SQL_GET_ID_BY_VALUES);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ID_BY_VALUES)){
            statement.setString(1,publisher.getPublisherName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving publisher:"+publisher,e);
        }
    }
}
