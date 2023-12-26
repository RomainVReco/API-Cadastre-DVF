package org.immo.servicepublicapi;

import java.io.IOException;
import java.net.*;

public class NextPageAPI extends AbstractRequestAPI {
    URL URL;

    public NextPageAPI (String nextPageURL) {
        try {
            URL = new URI(nextPageURL).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(URL);
            try {
                this.conn = this.getRequestResult(this.URL);
            } catch (IOException e) {
                System.out.println("Pas de réponse du serveur");
            }
    }
}
