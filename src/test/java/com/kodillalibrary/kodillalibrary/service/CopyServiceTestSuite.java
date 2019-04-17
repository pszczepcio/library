package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyServiceTestSuite {

    @Autowired
    private TitleService titleService;

    @Autowired
    private CopyService copyService;

    @Test
    public void shouldSave(){
        //Given
        Title title = new Title("title", "author", 2000);
        CopiesOfBooks copy = new CopiesOfBooks("available");
        title.getCopiesOfBooksList().add(copy);
        copy.setTitle(title);

        //When
        titleService.saveBook(title);
        copyService.save(copy);
        long titleId = title.getId();
        long copyId = copy.getId();

        //Then
        assertEquals("title", copyService.getCopyById(copyId).get().getTitle().getTitle());
        assertEquals("author", copyService.getCopyById(copyId).get().getTitle().getAuthor());
        assertEquals("available", copyService.getCopyById(copyId).get().getStatus());

        //Clean up
        copyService.deleteById(copyId);
        titleService.deleteBookById(titleId);
    }

    @Test
    public void shouldGetAllCopies(){
        //Given
        Title title = new Title("title1", "author1", 2000);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        CopiesOfBooks copy2 = new CopiesOfBooks("not available");
        copy1.setTitle(title);
        copy2.setTitle(title);
        title.getCopiesOfBooksList().add(copy1);
        title.getCopiesOfBooksList().add(copy2);

        //When
        titleService.saveBook(title);
        copyService.save(copy1);
        copyService.save(copy2);
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();
        long titleId = title.getId();

        //Then
        assertEquals(2, copyService.getAllCopies().size());

        //Clean up
        copyService.deleteById(copy2Id);
        copyService.deleteById(copy1Id);
        titleService.deleteBookById(titleId);
    }

    @Test
    public void shouldGetCopyById(){
        //Given
        Title title = new Title("title1", "author1", 2000);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        CopiesOfBooks copy2 = new CopiesOfBooks("not available");
        copy1.setTitle(title);
        copy2.setTitle(title);
        title.getCopiesOfBooksList().add(copy1);
        title.getCopiesOfBooksList().add(copy2);

        //When
        titleService.saveBook(title);
        copyService.save(copy1);
        copyService.save(copy2);
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();
        long titleId = title.getId();

        //Then
        assertEquals("available", copyService.getCopyById(copy1Id).get().getStatus());
        assertEquals("title1", copyService.getCopyById(copy1Id).get().getTitle().getTitle());
        assertEquals("author1", copyService.getCopyById(copy1Id).get().getTitle().getAuthor());
        assertEquals("not available", copyService.getCopyById(copy2Id).get().getStatus());
        assertEquals("title1", copyService.getCopyById(copy2Id).get().getTitle().getTitle());
        assertEquals("author1", copyService.getCopyById(copy2Id).get().getTitle().getAuthor());

        //Clean up
        copyService.deleteById(copy2Id);
        copyService.deleteById(copy1Id);
        titleService.deleteBookById(titleId);
    }

    @Test
    public void shouldDeleteById(){
        //Given
        Title title = new Title("title1", "author1", 2000);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        CopiesOfBooks copy2 = new CopiesOfBooks("not available");
        copy1.setTitle(title);
        copy2.setTitle(title);
        title.getCopiesOfBooksList().add(copy1);
        title.getCopiesOfBooksList().add(copy2);

        //When
        titleService.saveBook(title);
        copyService.save(copy1);
        copyService.save(copy2);
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();
        long titleId = title.getId();

        //Then
        assertEquals(2, copyService.getAllCopies().size());
        copyService.deleteById(copy2Id);
        assertEquals(1, copyService.getAllCopies().size());
        copyService.deleteById(copy1Id);
        assertEquals(0, copyService.getAllCopies().size());

        //Clean up
        titleService.deleteBookById(titleId);
    }
}
