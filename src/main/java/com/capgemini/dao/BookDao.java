package com.capgemini.dao;

import java.util.List;

public interface BookDao extends Dao<BookEntity, Long> {

    List<BookEntity> findBookByTitle(String title);

	List<BookEntity> findBooksByAuthor(Long authorId);
}
