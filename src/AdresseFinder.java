package HTTP;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class AdresseFinder {

    static final String PATH = "/Users/coding/Documents/Java/";
    static final String URL = "https://api-adresse.data.gouv.fr/search/?q=";
    URL url;
    HttpsURLConnection conn;

    public AdresseFinder(String query) throws IOException {
        String finalQuery = new ConverterURL(query).getEncodedQuery();
        String sb = URL +
                finalQuery;
        this.url = new URL(sb);
        this.conn = this.getRequestResult(this.url);
        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("conn : "+conn.getResponseMessage());
    }

    public URL getUrl() {
        return url;
    }

    public HttpsURLConnection getConn() {
        return conn;
    }

    private HttpsURLConnection getRequestResult(URL url) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
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
        File file = new File(PATH+nomFichier+".json");
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
