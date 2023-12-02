package HTTP;


import javax.net.ssl.HttpsURLConnection;
import java.io.*;

public class TestAPI {


    public static void main(String[] args) throws IOException {

        String query = "202 avenue du Maine Paris";
        AdresseFinder adresse = new AdresseFinder(query);

        String str = adresse.readReponseFromAPI(adresse.getConn());
        adresse.createJSONFile("Paris", str);


    }

}
