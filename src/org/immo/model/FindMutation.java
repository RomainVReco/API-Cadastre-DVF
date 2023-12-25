package org.immo.model;

import org.immo.exceptions.UnknownResponseCode;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.feuille.Feuille;
import org.immo.geojson.geomutation.Geomutation;
import org.immo.servicepublicapi.AbstractRequestAPI;
import org.immo.servicepublicapi.AdresseAPI;
import org.immo.servicepublicapi.FeuilleAPI;
import org.immo.userinput.GestionUser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Scanner;

public class FindMutation {

    AbstractRequestAPI callAPI;

    ResponseManagerHTTP<AdresseBAN> responseManagerAdresse;
    ResponseManagerHTTP<Feuille> responseManagerFeuille;
    ResponseManagerHTTP<Geomutation> responseManagerGeomutation;

    public void getAdressFromQuery(String adresse) throws IOException, URISyntaxException {
        callAPI = new AdresseAPI(adresse);
        AdresseBAN adresseBAN;
//        String geomtryPoint = callAPI.readReponseFromAPI(callAPI.getConn());
        responseManagerAdresse = new ResponseManagerHTTP<>();
        Optional<AdresseBAN> optionalAdresseBAN = responseManagerAdresse.getAPIReturn(callAPI, AdresseBAN.class);
        if (optionalAdresseBAN.isPresent()){
            getListOfAdress(optionalAdresseBAN.get());
        } else {
            System.out.println("Pas de résultat avec cette adresse");
        }
    }

    private void getListOfAdress(AdresseBAN adresseBAN) throws IOException, URISyntaxException {
        Optional<Feuille> optionalFeuille;
        String bboxOfFeuille;
        if (adresseBAN.getFeatures().size()==1) {
            String geomtryPoint = adresseBAN.getFeatures().get(0).getGeometry().toString();
            responseManagerFeuille = new ResponseManagerHTTP<>();
            callAPI = new FeuilleAPI(geomtryPoint);
            optionalFeuille = responseManagerFeuille.getAPIReturn(callAPI, Feuille.class);
            if (optionalFeuille.isPresent()){
                bboxOfFeuille = optionalFeuille.get().convertBboxToString();
                getGeomutationsFromFeuille(bboxOfFeuille, adresseBAN.getFeatures().get(0).getProperties().getCitycode());
            }
        }
    }

    private void getGeomutationsFromFeuille(String bboxOfFeuille, String cityCode) {
        GestionUser gestionUser = new GestionUser();
        Scanner sc = new Scanner(System.in);
        System.out.println("Pour quelle année souhaitez-vous faire une recherche ? à partir de 2010");
        String inputYear = gestionUser.promptYear();
        

    }
}
