package org.immo.servicepublicapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ParcelleAPI extends AbstractRequestAPI{
    final String URL_API = "https://apicarto.ign.fr/api/cadastre/parcelle?";

    public ParcelleAPI(String query, String parameters) throws IOException, URISyntaxException {
        String preparedParameter = parameters;
        String encodedQuery = new ConverterURL(query).getEncodedQuery();
        URL = new URI(URL_API+preparedParameter+encodedQuery).toURL();
        this.conn = this.getRequestResult(this.URL);
    }

    public ParcelleAPI(String cityCode, String section, int number) throws IOException, URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append("code_insee=").append(cityCode).append("&").append("section=").append(section);
        URL = new URI(URL_API+sb).toURL();
        this.conn = this.getRequestResult(this.URL);
    }

}
