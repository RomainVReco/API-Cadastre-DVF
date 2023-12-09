package org.immo.servicepublicapi;

import org.immo.config.Config;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public abstract class AbstractRequestAPI {
    Config config;

    HttpsURLConnection conn;

    public AbstractRequestAPI() {

    }

    protected HttpsURLConnection getRequestResult(URL url) throws IOException {
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
        String PATH = Config.getInstance().givePath();
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


    public HttpsURLConnection getConn() {
        return conn;
    }

    public void setConn(HttpsURLConnection conn) {
        this.conn = conn;
    }

}
