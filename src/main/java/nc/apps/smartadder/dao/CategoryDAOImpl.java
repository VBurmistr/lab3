package nc.apps.smartadder.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dao.interfaces.CategoryDAO;
import nc.apps.smartadder.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Slf4j
public class CategoryDAOImpl implements CategoryDAO {

    private final DataSource dataSource;

    @Autowired
    public CategoryDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static final String SQL_ADD_NEW = "INSERT INTO lab3_category_table (category_name) VALUES (?) ON CONFLICT DO NOTHING RETURNING id";
    public static final String SQL_GET_ID_BY_VALUES = "SELECT id from lab3_category_table where category_name = ? ";

    @Override
    public Long save(Category category) throws DAOException {
        log.info("Add new author query:"+SQL_ADD_NEW);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)){
            statement.setString(1,category.getCategoryName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getLong("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving category:"+category,e);
        }
    }

    @Override
    public Long getIdByValues(Category category) throws DAOException {
        log.info("Add new author query:"+SQL_GET_ID_BY_VALUES);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ID_BY_VALUES)){
            statement.setString(1,category.getCategoryName());
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getLong("id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving category:"+category,e);
        }
    }
}
