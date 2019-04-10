package com.kodillalibrary.kodillalibrary;


import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import com.kodillalibrary.kodillalibrary.repository.CopiesOfBookDao;
import com.kodillalibrary.kodillalibrary.repository.ReaderDao;
import com.kodillalibrary.kodillalibrary.repository.RentBooksDao;
import com.kodillalibrary.kodillalibrary.repository.TitleDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KodillaLibraryApplicationTests {
	@Autowired
	private ReaderDao readerDao;

	@Autowired
	private TitleDao titleDao;

	@Autowired
	private CopiesOfBookDao copiesOfBookDao;

	@Autowired
	private RentBooksDao rentBooksDao;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testReaderDaoSave(){
		//Given
		Reader janKowalski = new Reader("Jan","Kowalski");
		Reader dorotaKowalska = new Reader("Dorota", "Kowalska");

		//When
		readerDao.save(janKowalski);
		readerDao.save(dorotaKowalska);
		System.out.println("Data1: " + janKowalski.getDateOfAccountCreation());

		//then
		Optional<Reader> reader = readerDao.findByNameAndSurname("Jan", "Kowalski");
		Optional<Reader> reader1 = readerDao.findByNameAndSurname("Dorota", "Kowalska");
		int sizeReadersList = readerDao.findAll().size();
		Assert.assertEquals("Jan", reader.get().getName());
		Assert.assertEquals("Dorota", reader1.get().getName());
		Assert.assertEquals(sizeReadersList, readerDao.findAll().size());

		//CleanUo
		readerDao.deleteByNameAndSurname("Jan", "Kowalski");
		readerDao.deleteByNameAndSurname("Dorota", "Kowalska");
	}

	@Test
	public void testTitleDaoSave(){
		//Given
		Title title1 = new Title("Sztuka Elektorniki", "Horwitz", 2000);

		//When
		titleDao.save(title1);

		//Then
		long id = titleDao.findAll().get(titleDao.findAll().size()-1).getId();
		Assert.assertEquals("Horwitz", titleDao.findById(id).getAuthor());
		Assert.assertEquals("Sztuka Elektorniki", titleDao.findById(id).getTitle());
		Assert.assertEquals(2000, titleDao.findById(id).getYearOfPublishment());

		//CleanUp
		titleDao.delete(id);
	}

	@Test
	public void testSaveCopiesOfBooksSave(){
		//given
		Title title1 = new Title("Sztuka Elektorniki", "Horwitz", 2000);
		titleDao.save(title1);
		CopiesOfBooks copy1 = new CopiesOfBooks("In Circulation");
		CopiesOfBooks copy2 = new CopiesOfBooks("In Circulation");
		CopiesOfBooks copy3 = new CopiesOfBooks("Lost");
		copy1.setTitle(title1);
		copy2.setTitle(title1);
		copy3.setTitle(title1);
		title1.getCopiesOfBooksList().add(copy1);
		title1.getCopiesOfBooksList().add(copy2);
		title1.getCopiesOfBooksList().add(copy3);

		//When
		int titleId = titleDao.findAll().size()-1;
		copiesOfBookDao.save(copy1);
		copiesOfBookDao.save(copy2);
		copiesOfBookDao.save(copy3);

		//then
		Assert.assertEquals(3, copiesOfBookDao.findAll().size());

		//CleanUp
		copiesOfBookDao.delete(copy1.getId());
		copiesOfBookDao.delete(copy2.getId());
		copiesOfBookDao.delete(copy3.getId());
		titleDao.delete(title1.getId());
	}

//	@Test
//	public void testRentsBooksSave(){
//		//given
//		Reader janKowalski = new Reader("Jan","Kowalski");
//		Reader dorotaKowalska = new Reader("Dorota", "Kowalska");
//		readerDao.save(janKowalski);
//		readerDao.save(dorotaKowalska);
//
//		Title title = new Title("Sztuka Elektorniki", "Horwitz", 2000);
//		Title title1 = new Title("Hobbit", "Tolkien" , 2005);
//		Title title2 = new Title("Instalacje Elektryczne", "Markiewicz", 1995);
//		titleDao.save(title);
//		titleDao.save(title1);
//		titleDao.save(title2);
//
//		CopiesOfBooks copy1 = new CopiesOfBooks("Available");
//		CopiesOfBooks copy2 = new CopiesOfBooks("Not available");
//		CopiesOfBooks copy3 = new CopiesOfBooks("Available");
//		CopiesOfBooks copy4 = new CopiesOfBooks("Available");
//		CopiesOfBooks copy5 = new CopiesOfBooks("Available");
//		CopiesOfBooks copy6 = new CopiesOfBooks("Available");
//		CopiesOfBooks copy7 = new CopiesOfBooks("Not available");
//
//
//		copy1.setTitle(title);
//		copy2.setTitle(title);
//		copy3.setTitle(title);
//		copy4.setTitle(title1);
//		copy5.setTitle(title1);
//		copy6.setTitle(title2);
//		copy7.setTitle(title2);
//
//		title.getCopiesOfBooksList().add(copy1);
//		title.getCopiesOfBooksList().add(copy2);
//		title.getCopiesOfBooksList().add(copy3);
//		title.getCopiesOfBooksList().add(copy4);
//		title.getCopiesOfBooksList().add(copy5);
//		title.getCopiesOfBooksList().add(copy6);
//		title.getCopiesOfBooksList().add(copy7);
//
//		System.out.println("asasasas" + title.getId());
//		//When
//		copiesOfBookDao.save(copy1);
//		copiesOfBookDao.save(copy2);
//		copiesOfBookDao.save(copy3);
//		copiesOfBookDao.save(copy4);
//		copiesOfBookDao.save(copy5);
//		copiesOfBookDao.save(copy6);
//		copiesOfBookDao.save(copy7);
//
//		int idCopyBook = copy2.getId();
//		System.out.println("idCopyBook: " + idCopyBook );
//		long idReader = readerDao.findByNameAndSurname("Dorota", "Kowalska").get().getId();
//		System.out.println("idReader: " + idReader);
//
//		//then
//		RentBooks rentBooks = new RentBooks(idCopyBook,idReader);
//		rentBooks.setCopiesOfBooks(copiesOfBookDao.findById(idCopyBook));
//		rentBooks.setReader(readerDao.findOne(idReader));
//		copy1.getRentBooksList().add(rentBooks);
//		janKowalski.getRentBooksList().add(rentBooks);
//		rentBooksDao.save(rentBooks);
//
//		//CleanUp
//		rentBooksDao.delete(rentBooks);
//		copiesOfBookDao.delete(copy1.getId());
//		copiesOfBookDao.delete(copy2.getId());
//		copiesOfBookDao.delete(copy3.getId());
//		copiesOfBookDao.delete(copy4.getId());
//		copiesOfBookDao.delete(copy5.getId());
//		copiesOfBookDao.delete(copy6.getId());
//		copiesOfBookDao.delete(copy7.getId());
//		readerDao.deleteByNameAndSurname("Jan", "Kowalski");
//		readerDao.deleteByNameAndSurname("Dorota", "Kowalska");
//		titleDao.delete(title.getId());
//		titleDao.delete(title1.getId());
//		titleDao.delete(title2.getId());
//	}
}
