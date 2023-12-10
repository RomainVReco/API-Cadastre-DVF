package org.immo.servicepublicapi;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AdresseAPI extends AbstractRequestAPI {

    final String URL_API = "https://api-adresse.data.gouv.fr/search/?q=";
    URL URL;

    public AdresseAPI(String query) throws IOException, URISyntaxException {
        String encodedQuery = new ConverterURL(query).getEncodedQuery();
        URL = new URI(URL_API+encodedQuery).toURL();
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
    }

}
