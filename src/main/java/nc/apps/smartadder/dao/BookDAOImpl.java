package nc.apps.smartadder.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dao.interfaces.BookDAO;
import nc.apps.smartadder.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class BookDAOImpl implements BookDAO {

    private final DataSource dataSource;
    public static final String SQL_ADD_NEW = "INSERT INTO\n" +
            "                lab3_book_table (\n" +
            "                    title,\n" +
            "                    author_id,\n" +
            "                    category_id,\n" +
            "                    language_id,\n" +
            "                    publisher_id\n" +
            "                )\n" +
            "            VALUES\n" +
            "                (?, ?, ?, ?, ?)";

    public BookDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Book book) throws DAOException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)) {
            statement.setString(1, book.getTitle());
            statement.setLong(2, book.getAuthor().getId());
            statement.setLong(3, book.getCategory().getId());
            statement.setLong(4, book.getLanguage().getId());
            statement.setLong(5, book.getPublisher().getId());
            log.info("Query for adding book:" + SQL_ADD_NEW);
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant add book for some reason, book:"+book);
            }
        } catch (SQLException e) {
            throw new DAOException("Error on saving book:"+book,e);
        }
    }
}
