package ServicePublicAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ParcelleAPI extends AbstractRequestAPI{
    final String URL_API = "https://apicarto.ign.fr/api/cadastre/parcelle?";
    java.net.URL URL;
    String parameters;
    public ParcelleAPI(String query, String parameters) throws IOException, URISyntaxException {
        this.parameters = parameters;
        String preparedParameter = parameters+"=";
        String encodedQuery = new ConverterURL(query).getEncodedQuery();
        URL = new URI(URL_API+preparedParameter+encodedQuery).toURL();
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("conn : "+conn.getResponseMessage());
    }

}
