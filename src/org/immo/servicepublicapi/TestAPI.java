package org.immo.servicepublicapi;

import java.io.*;
import java.net.URISyntaxException;

public class TestAPI {


    public static void main(String[] args) throws IOException, URISyntaxException {

        String query = "31 avenue du bas meudon";
        AdresseAPI adresse = new AdresseAPI(query);

        String str = adresse.readReponseFromAPI(adresse.getConn());
        str.replaceAll("\"","\\\"");
        System.out.println("Contenu réponse conn:"+str);

//        String str = adresse.readReponseFromAPI(adresse.getConn());
//
        adresse.createJSONFile("Test3", str);
//
//        String queryPCI = "{\"type\": \"Point\",\"coordinates\": [2.247021,48.822554]}";
//        ServicePublicAPI.FeuillePCIEXPRESS pciE = new ServicePublicAPI.FeuillePCIEXPRESS(queryPCI, "geom=");
//        String strPCI = pciE.readReponseFromAPI(pciE.getConn());
//        pciE.createJSONFile("AdresseMeudon", strPCI);




    }
}

