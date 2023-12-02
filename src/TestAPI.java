
import javax.net.ssl.HttpsURLConnection;
import java.io.*;

public class TestAPI {


    public static void main(String[] args) throws IOException {

        String query = "31 avenue du bas meudon";
        AdresseFinder adresse = new AdresseFinder(query);

        String str = adresse.readReponseFromAPI(adresse.getConn());
        adresse.createJSONFile("Issy", str);

    }
}

