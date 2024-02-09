package com.anp.dao;

import com.anp.entities.Library;

public interface LibraryDao {
	void create();
	void addBook(Library library);
	void display();
	void displaybook(int book_id, String book_name);
	void update(Library library, int book_id, String book_name );
	void bookDetails(int book_id);
	void deletebook(Library library, int book_id);

}
