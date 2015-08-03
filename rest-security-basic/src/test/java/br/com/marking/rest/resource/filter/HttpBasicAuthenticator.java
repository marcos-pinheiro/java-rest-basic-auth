/*
 *
 */
package br.com.marking.rest.resource.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.xml.bind.DatatypeConverter;

/**
 * @author Marcos Pinheiro
 * 
 * <a href="http://www.adam-bien.com/roller/abien/entry/client_side_http_basic_access">Referencia em como criar o header para autenticaçao 
 * Basic para usar na Client API do JAX-RS</a>
 */
public class HttpBasicAuthenticator implements ClientRequestFilter {

    private static final String DEFAULT_CHARSET = "UTF-8";
    
    private final String username;
    private final String password;
    
    private HttpBasicAuthenticator (String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    public static HttpBasicAuthenticator newAuthenticator(String username, String password) {
        return new HttpBasicAuthenticator(username, password);
    }
    
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        
        requestContext.getHeaders().add("Authorization", getAuthentication());
        
        
    }

    private String getAuthentication() {
        
        String token = String.format("%s:%s", this.username, this.password);
        try {
            return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes(DEFAULT_CHARSET));
        } 
        catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
}
