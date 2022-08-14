package nc.apps.smartadder.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dao.interfaces.LanguageDAO;
import nc.apps.smartadder.domain.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Slf4j
public class LanguageDAOImpl implements LanguageDAO {

    private final DataSource dataSource;

    @Autowired
    public LanguageDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static final String SQL_ADD_NEW = "INSERT INTO lab3_language_table ( language) VALUES (?) ON CONFLICT DO NOTHING RETURNING id";
    public static final String SQL_GET_ID_BY_VALUES = "SELECT id from lab3_language_table where language = ? ";


    @Override
    public Long save(Language language) throws DAOException {
        log.info("Add new author query:"+SQL_ADD_NEW);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)){
            statement.setString(1,language.getLanguageName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getLong("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving language:"+language,e);
        }    }

    @Override
    public Long getIdByValues(Language language) throws DAOException {
        log.info("Add new author query:"+SQL_GET_ID_BY_VALUES);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ID_BY_VALUES)){
            statement.setString(1,language.getLanguageName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getLong("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving language:"+language,e);
        }
    }
}
