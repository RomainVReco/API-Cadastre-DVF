package org.immo.servicepublicapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class GeomutationAPI extends AbstractRequestAPI {
    final String URL_API = "https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?";
    URL URL;

    public GeomutationAPI (String anneeMutation, String codeInsee, String bbox) throws URISyntaxException, IOException {

        String encodedQuery = "in_bbox="+new ConverterURL(bbox).getEncodedQuery();
        String code_insee = "&code_insee="+codeInsee;
        String annee_mutation = "&annee_mutation="+anneeMutation;
        URL = new URI(URL_API+encodedQuery+codeInsee+anneeMutation).toURL();
        System.out.println(URL);
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
    }
}
