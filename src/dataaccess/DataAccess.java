package dataaccess;

import business.Book;
import business.LibraryMember;

import java.util.HashMap;
import java.util.Optional;

public interface DataAccess {
	public HashMap<String, Book> readBooksMap();

	public HashMap<String, User> readUserMap();

	public HashMap<String, LibraryMember> readMemberMap();

	public void saveNewMember(LibraryMember member);

	public void deleteMember(String memberId);

	public void saveBook(Book book);

	Optional<Book> findBookByIsbn(String isbn);

	Optional<LibraryMember> findMemberById(String memberId);
}
