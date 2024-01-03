package org.immo.servicepublicapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FeuilleAPI extends AbstractRequestAPI {
    final String URL_API = "https://apicarto.ign.fr/api/cadastre/feuille?";

    /**
     * Pour geom, le format attendu est {"type":"Point","coordinates":[2.247021,48.822554]}
     */
    String parameters;
    public FeuilleAPI(String query, String parameters) throws IOException, URISyntaxException {
        this.parameters = parameters;
        String preparedParameter = parameters+"=";
        String encodedQuery = new ConverterURL(query).getEncodedQuery();
        URL = new URI(URL_API+preparedParameter+encodedQuery).toURL();
        this.conn = this.getRequestResult(this.URL);
    }

    public FeuilleAPI(String codeInsee, String geometryPoint, int number) throws IOException, URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append("code_insee=").append(codeInsee).append("&");
        String encodedQuery = new ConverterURL(geometryPoint).getEncodedQuery();
        sb.append("geom=").append(encodedQuery);
        URL = new URI(URL_API+ sb).toURL();
        this.conn = this.getRequestResult(this.URL);
    }

    public FeuilleAPI(String query) throws IOException, URISyntaxException {
        StringBuilder sb = new StringBuilder();
        String encodedQuery = new ConverterURL(query).getEncodedQuery();
        sb.append(URL_API).append("geom=").append(encodedQuery);
        URL = new URI(sb.toString()).toURL();
        this.conn = this.getRequestResult(this.URL);
    }

}
