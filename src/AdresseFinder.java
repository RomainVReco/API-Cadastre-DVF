import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AdresseFinder extends AbstractRequestAPI {

    final String URL_API = "https://api-adresse.data.gouv.fr/search/?q=";
    URL URL;

    public AdresseFinder(String query) throws IOException, URISyntaxException {
        String encodedQuery = new ConverterURL(query).getEncodedQuery();
        URL = new URI(URL_API+encodedQuery).toURL();
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("conn : "+conn.getResponseMessage());
    }


    public HttpsURLConnection getConn() {
        return conn;
    }

    public String readReponseFromAPI(HttpsURLConnection conn){
        StringBuilder str = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
                str.append(line);
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        return str.toString();
    }

    public boolean createJSONFile(String nomFichier, String contenuJson){
        File file = new File(this.getPATH()+nomFichier+".json");
        FileWriter fw;

        try {
            fw = new FileWriter(file);
            fw.append(contenuJson);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
