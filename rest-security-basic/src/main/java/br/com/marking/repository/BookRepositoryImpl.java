/*
 *
 */
package br.com.marking.repository;

import br.com.marking.entity.Book;
import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Marcos Pinheiro
 */
public class BookRepositoryImpl implements BookRepository {
    
    //Para fins de exemplo, um mapa estático
    private static final Map<String, Book> SOURCE = new HashMap<>();
    
    static {
        SOURCE.put("0321356683", Book.newInstance("0321356683", "Java Effective",       new BigDecimal("129.90")));
        SOURCE.put("1449370179", Book.newInstance("1449370179", "Java EE 7 Essentials", new BigDecimal("79.90")));
        SOURCE.put("0321125215", Book.newInstance("0321125215", "Domain Driven Design", new BigDecimal("299.90")));
        SOURCE.put("9999999999", Book.newInstance("9999999999", "Livro do titio",       new BigDecimal("9.90")));
        SOURCE.put("9999999999", Book.newInstance("0192837465", "RESTful Java Patterns and Best Practices", new BigDecimal("9.90")));
    }
    

    @Override
    public void insert(Book book) {
        SOURCE.put(book.getIsbn(), book);
    }

    @Override
    public void update(String isbn, Book book) {
        SOURCE.replace(book.getIsbn(), book);
    }

    @Override
    public void delete(String isbn) {
        SOURCE.remove(isbn);
    }

    @Override
    public Book getById(String isbn) {
        return SOURCE.get(isbn);
    }

    @Override
    public List<Book> getAllByDescription(String description) {
        return SOURCE.values().stream()
                .filter(book -> book.getDescription().toUpperCase().contains(description.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAll() {
        //uso da API Collections do guava
        return ImmutableList.copyOf(SOURCE.values());
    }
}
