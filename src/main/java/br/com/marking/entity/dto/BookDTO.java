/*
 *
 */
package br.com.marking.entity.dto;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcos Pinheiro
 */
@XmlRootElement
public class BookDTO {
    
    @XmlElement
    private String description;
    
    @XmlElement
    private BigDecimal price;

    public BookDTO() {
    }

    public BookDTO(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }
    

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
