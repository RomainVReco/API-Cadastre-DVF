import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FeuillePCIEXPRESS extends AbstractRequestAPI {
    final String URL_API = "https://apicarto.ign.fr/api/cadastre/feuille?geom=";
    URL URL;
    String parameters;
    public FeuillePCIEXPRESS(String query, String parameters) throws IOException, URISyntaxException {
        this.parameters = parameters;
        String encodedQuery = new ConverterURL(query).getEncodedQuery();
        URL = new URI(URL_API+encodedQuery).toURL();
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("conn : "+conn.getResponseMessage());
    }


}
