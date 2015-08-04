/*
 *
 */
package br.com.marking.entity;

import com.google.common.base.Objects;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcos Pinheiro
 */
@XmlRootElement
public final class Book {
    
    @XmlElement
    private final String isbn;
    
    @XmlElement
    private final String description;
    
    @XmlElement
    private final BigDecimal price;

    
    /*
     * Classe Imutável mapeada com anotações do JAXB
     */
    Book() {
        this(null, null, null);
    }
    
    private Book(String isbn, String description, BigDecimal price) {
        this.isbn = isbn;
        this.description = description;
        this.price = price;
    }
    
    public static Book newInstance(String isbn, String description, BigDecimal price){
        return new Book(isbn, description, price);
    }

    
    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    
    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Book other = (Book) obj;        
        return Objects.equal(this.isbn, other.isbn)
            && Objects.equal(this.description, other.description)
            && Objects.equal(this.price, other.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.isbn, this.description, this.price);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("Isbn", this.isbn)
                .add("Description", this.description)
                .add("Price", this.price)
                .toString();
    }
}
