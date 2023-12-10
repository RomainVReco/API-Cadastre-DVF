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

        String encodedQuery = "&in_bbox="+new ConverterURL(bbox).getEncodedQuery();
        String code_insee = ("code_insee="+codeInsee);
        String annee_mutation = "&annee_mutation="+anneeMutation;
        /**
         * L'ordre des paramètres change les résultats...
         */
        //URL = new URI((URL_API+codeInsee+encodedQuery+anneeMutation)).toURL();
        //URL = new URI("https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?code_insee=75114&annee_mutation=2017&in_bbox=2.32476735,48.83012991,2.32562559,48.83054392").toURL();
        URL = new URI("https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?anneemut=2017&code_insee=75114&in_bbox=2.32476735%2C48.83012991%2C2.32562559%2C48.83054392").toURL();
        System.out.println(URL);
        //https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?code_insee=75114&annee_mutation=2017&in_bbox=2.32476735,48.83012991,2.32562559,48.83054392
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
    }
}
