package org.immo.servicepublicapi;

import org.immo.geojson.geomutation.Geomutation;

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
        String code_insee = ("&code_insee="+codeInsee);
        String annee_mutation = "anneemut="+anneeMutation;
        /**
         * L'ordre des paramètres change les résultats...
         */
        URL = new URI((URL_API+anneeMutation+codeInsee+encodedQuery)).toURL();
        //URL = new URI("https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?code_insee=75114&annee_mutation=2017&in_bbox=2.32476735,48.83012991,2.32562559,48.83054392").toURL();
        //URL = new URI("https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?anneemut=2017&code_insee=75114&in_bbox=2.32476735%2C48.83012991%2C2.32562559%2C48.83054392").toURL();
        System.out.println(URL);
        //https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?code_insee=75114&annee_mutation=2017&in_bbox=2.32476735,48.83012991,2.32562559,48.83054392
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
    }

    /**
     * A partir de l'année minimum et de la zone, retourne l'ensemble des mutations
     * @param anneeMutMin
     * @param bbox
     * @throws URISyntaxException
     * @throws IOException
     */
    public GeomutationAPI (String anneeMutMin, String bbox) throws URISyntaxException, IOException {
        String encodedQuery = "&in_bbox="+new ConverterURL(bbox).getEncodedQuery();
        String annee_mutmin = "annee_mutmin="+anneeMutMin;
        /**
         * L'ordre des paramètres change les résultats...
         */
        URL = new URI((URL_API+anneeMutMin+encodedQuery)).toURL();
        System.out.println(URL);
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
    }

    public GeomutationAPI (String anneemut, String contains_geom, String codeInsee, String bbox) throws URISyntaxException, IOException {
        String encodedQuery = "&in_bbox="+new ConverterURL(bbox).getEncodedQuery();
        String annee_mut = "anneemut="+anneemut;
//        String geom = "&contains_geom="+contains_geom;
        String encodedGeom = "&contains_geom="+new ConverterURL(contains_geom).getEncodedQuery();
        /**
         * L'ordre des paramètres change les résultats...
         */
        URL = new URI((URL_API+annee_mut+encodedGeom+encodedQuery)).toURL();
        System.out.println(URL);
        this.conn = this.getRequestResult(this.URL);
        System.out.println("Response code: " + conn.getResponseCode());
    }
}
