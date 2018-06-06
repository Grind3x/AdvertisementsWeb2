package com.gmail.grind3x.dao;

import com.gmail.grind3x.model.Author;

public interface AuthorDAO {
    void insertAuthor(Author author);

    void deleteAuthor(Author author);

    Author findAuthorByName(String name);

    void updateAuthor(Author author);
}
