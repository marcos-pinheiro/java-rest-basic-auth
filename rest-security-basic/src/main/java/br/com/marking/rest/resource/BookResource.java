/*
 *
 */
package br.com.marking.rest.resource;

import br.com.marking.repository.BookRepository;
import br.com.marking.repository.BookRepositoryImpl;
import br.com.marking.entity.Book;
import br.com.marking.entity.dto.BookDTO;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Marcos Pinheiro
 */
@Path("/books")
public class BookResource {
    
    private final BookRepository bookRepository;

    
    public BookResource() {
        bookRepository = new BookRepositoryImpl(); //Não façam isso, para fins de exemplo, inicialização dentro do construtor;
    }
    
    
    @GET
    @Path("/{isbn}") @Produces(MediaType.APPLICATION_JSON)
    public Response getBookByIsbn(@PathParam("isbn") String isbn) throws URISyntaxException {

        return Response.ok(bookRepository.getById(isbn)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Book book, @Context UriInfo uriInfo) {
        
        bookRepository.insert(book);
        
        return Response
                .created(uriInfo.getAbsolutePathBuilder().path("/" + book.getIsbn()).build())
                .build();
    }
    
    @PUT
    @Path("/{isbn}") @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("isbn") String isbn, BookDTO bookDTO) {
        
        bookRepository.update(isbn, Book.newInstance(isbn, bookDTO.getDescription(), bookDTO.getPrice()));
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{isbn}")
    public Response delete(@PathParam("isbn") String isbn) {
        
        bookRepository.delete(isbn);
        return Response.noContent().build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooksByFilter(@DefaultValue("") @QueryParam("description") String description) {
        
        List<Book> books = (description.isEmpty() ? bookRepository.getAll() : bookRepository.getAllByDescription(description));
        
        //Usada para retornar coleções quando utilizado juntamente com a classe Response
        GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(books) {};

        return Response.ok(entity).build();
    }
}
