package org.immo.servicepublicapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CommuneAPI extends AbstractRequestAPI {

    final String URL_API = "https://geo.api.gouv.fr/communes?codePostal=";
    public CommuneAPI(String query) throws URISyntaxException, IOException {
        String finalQuery = URL_API+query;
        URL = new URI(finalQuery).toURL();
        this.conn = this.getRequestResult(this.URL);
    }
}
