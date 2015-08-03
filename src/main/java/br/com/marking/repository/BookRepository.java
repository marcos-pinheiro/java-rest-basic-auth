/*
 *
 */
package br.com.marking.repository;

import br.com.marking.entity.Book;
import java.util.List;

/**
 *
 * @author Marcos Pinheiro
 */
public interface BookRepository {
    
    public void insert(Book book);
    
    public void update(String isbn,Book book);
    
    public void delete(String isbn);
    
    public Book getById(String isbn);
    
    public List<Book> getAllByDescription(String description);
    
    public List<Book> getAll();
}
