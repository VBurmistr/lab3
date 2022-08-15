package nc.apps.smartadder.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dao.interfaces.AuthorDAO;
import nc.apps.smartadder.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Slf4j
public class AuthorDAOImpl implements AuthorDAO {

    private final DataSource dataSource;

    @Autowired
    public AuthorDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static final String SQL_ADD_NEW = "INSERT INTO lab3_author_table (first_name,last_name) VALUES (?,?) ON CONFLICT DO NOTHING RETURNING id";
    public static final String SQL_GET_ID_BY_VALUES = "SELECT id from lab3_author_table where first_name = ? and last_name = ?";


    @Override
    public Integer save(Author author) throws DAOException {
        log.info("Add new author query:"+SQL_ADD_NEW);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)){
            statement.setString(1,author.getFirstName());
            statement.setString(2,author.getLastName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving author:"+author,e);
        }
    }

    @Override
    public Integer getIdByValues(Author author) throws DAOException {
        log.info("Add new author query:"+SQL_GET_ID_BY_VALUES);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ID_BY_VALUES)){
            statement.setString(1,author.getFirstName());
            statement.setString(2,author.getLastName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving author:"+author,e);
        }
    }
}
