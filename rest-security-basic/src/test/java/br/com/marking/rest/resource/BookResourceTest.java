/*
 *
 */
package br.com.marking.rest.resource;

import br.com.marking.entity.Book;
import br.com.marking.entity.dto.BookDTO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.junit.Test;
import static br.com.marking.rest.resource.filter.HttpBasicAuthenticator.newAuthenticator;
import java.math.BigDecimal;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos Pinheiro
 */
public class BookResourceTest {
    
    private static final String TARGET      = "http://localhost:8080/rest-security-basic/rest";
    private static final String USERNAME    = "admin";
    private static final String PASSWORD    = "java";

    @Test
    public void testGetBookByIsbn() throws Exception {
        
        Client client = ClientBuilder.newClient();
        Book book = client
                .target(TARGET)
                .path("/books/0321356683")
                .register(newAuthenticator(USERNAME, PASSWORD))
                .request()
                .get(Book.class);
        
        assertEquals("Java Effective", book.getDescription());
        client.close();
    }

    @Test
    public void testRegister() {
        
        Client client = ClientBuilder.newClient();
        Response firstResponse = client
                .target(TARGET)
                .path("/books")
                //.register(newAuthenticator(USERNAME, PASSWORD)) -> BASIC Necessário somente nos métodos PUT, GET e DELETE segundo a configuração no web.xml
                .request()
                .post(Entity.json(
                        Book.newInstance("9788566250374", "REST - Construa APIs inteligentes de maneira simples", new BigDecimal("39.90"))));
        
        assertEquals(Response.Status.CREATED.getStatusCode(), firstResponse.getStatus());
        assertNotNull(firstResponse.getHeaderString("Location"));
        client.close();
        
        client = ClientBuilder.newClient();
        Response secondResponse = client
                .target(firstResponse.getHeaderString("Location"))
                .register(newAuthenticator(USERNAME, PASSWORD))
                .request()
                .get();
        
        assertEquals(Response.Status.OK.getStatusCode(), secondResponse.getStatus());
        client.close();
    }

    @Test
    public void testUpdate() {
        
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(TARGET)
                .path("/books/0987654321")
                .register(newAuthenticator(USERNAME, PASSWORD))
                .request()
                .put(Entity.json(new BookDTO("REST - Construa APIs inteligentes de maneira simples", new BigDecimal("59.90"))));
        
        
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        client.close();
        
    }

    @Test
    public void testDelete() {
        
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(TARGET)
                .path("/books/9999999999")
                .register(newAuthenticator(USERNAME, PASSWORD))
                .request()
                .delete();
        
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        client.close();        
    }

    @Test
    public void testGetAllBooksByFilter() {
        
        Client client = ClientBuilder.newClient();
        List<Book> books = client
                .target(TARGET)
                .path("/books")
                .queryParam("description", "JAVA")
                .register(newAuthenticator(USERNAME, PASSWORD))
                .request()
                .get(new GenericType<List<Book>>(){});
        
        books.stream().forEach(book -> {
            assertTrue("Java EE 7 Essentials".equals(book.getDescription()) || "Java Effective".equals(book.getDescription()));
        });
    }
}
