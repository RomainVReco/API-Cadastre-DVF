
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;

public class TestAPI {


    public static void main(String[] args) throws IOException, URISyntaxException {

        String query = "31 avenue du bas meudon";
        AdresseFinder adresse = new AdresseFinder(query);

        String str = adresse.readReponseFromAPI(adresse.getConn());
        adresse.createJSONFile("Issy", str);

        String queryPCI = "{\"type\": \"Point\",\"coordinates\": [2.247021,48.822554]}";
        FeuillePCIEXPRESS pciE = new FeuillePCIEXPRESS(queryPCI, "geom=");
        String strPCI = pciE.readReponseFromAPI(pciE.getConn());
        pciE.createJSONFile("AdresseMeudon", strPCI);




    }
}

