package nc.apps.smartadder.mappers;

import nc.apps.smartadder.domain.*;
import nc.apps.smartadder.dto.VolumeInfo;

import java.util.Arrays;

public class VolumeInfoToBookDomain {
    public static Book map(VolumeInfo volumeInfo){
        if(volumeInfo ==null){
            return null;
        }
        Book book = new Book();

        book.setTitle(volumeInfo.getTitle());

        book.setCategory(new Category(volumeInfo.getCategories()[0]));

        Author author = new Author();
        String[] authorNameSplited = volumeInfo.getAuthors()[0].split(" ");
        if(authorNameSplited.length>1){
            author.setLastName(authorNameSplited[authorNameSplited.length-1]);
            author.setFirstName(String.join(" ",
                    Arrays.copyOf(authorNameSplited, authorNameSplited.length - 1)));
        }else{
            author.setFirstName(authorNameSplited[0]);
        }
        book.setAuthor(author);

        book.setLanguage(new Language(volumeInfo.getLanguage()));

        book.setPublisher(new Publisher(volumeInfo.getPublisher()));

        return book;
    }
}
