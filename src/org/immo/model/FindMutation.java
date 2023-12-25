package org.immo.model;

import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.feuille.Feuille;
import org.immo.geojson.geomutation.FeatureMutation;
import org.immo.geojson.geomutation.Geomutation;
import org.immo.servicepublicapi.AbstractRequestAPI;
import org.immo.servicepublicapi.AdresseAPI;
import org.immo.servicepublicapi.FeuilleAPI;
import org.immo.servicepublicapi.GeomutationAPI;
import org.immo.userinput.GestionUser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class FindMutation {

    private AbstractRequestAPI callAPI;
    private ResponseManagerHTTP<AdresseBAN> responseManagerAdresse;
    private ResponseManagerHTTP<Feuille> responseManagerFeuille;
    private ResponseManagerHTTP<Geomutation> responseManagerGeomutation;
    private final String EMPTY_RETURN = "No object in POJO";

    private Set<FeatureMutation> setOfGeomutations = new HashSet<>();

    public FindMutation(String query) {
        Optional<AdresseBAN> optionalAdresseBAN = getAdressFromQuery(query);
        String bboxOfFeuille = new String();

    }

    public Optional<AdresseBAN> getAdressFromQuery(String adresse) {
        callAPI = new AdresseAPI(adresse);
        responseManagerAdresse = new ResponseManagerHTTP<>();
        Optional<AdresseBAN> optionalAdresseBAN = responseManagerAdresse.getAPIReturn(callAPI, AdresseBAN.class);
        return optionalAdresseBAN;
    }

    private String getListOfAdress(Optional<AdresseBAN> optionalAdresseBAN) throws IOException, URISyntaxException {
        Optional<Feuille> optionalFeuille;
        String bboxOfFeuille;
        AdresseBAN adresseBan;
        if (optionalAdresseBAN.isPresent()){
            adresseBan = optionalAdresseBAN.get();
        } else return EMPTY_RETURN;
        if (adresseBan.getFeatures().size()==1) {
            String geomtryPoint = adresseBan.getFeatures().get(0).getGeometry().toString();
            responseManagerFeuille = new ResponseManagerHTTP<>();
            callAPI = new FeuilleAPI(geomtryPoint);
            optionalFeuille = responseManagerFeuille.getAPIReturn(callAPI, Feuille.class);
            if (optionalFeuille.isPresent()){
                bboxOfFeuille = optionalFeuille.get().convertBboxToString();
                getGeomutationsFromFeuille(bboxOfFeuille, adresseBan.getFeatures().get(0).getProperties().getCitycode());
            }
        }
        return null;
    }

    private void getGeomutationsFromFeuille(String bboxOfFeuille, String cityCode) throws URISyntaxException, IOException {
        GestionUser gestionUser = new GestionUser();
        System.out.println("Pour quelle année souhaitez-vous faire une recherche ? à partir de 2010");
        String inputYear = gestionUser.promptYear();
        callAPI = new GeomutationAPI(inputYear, cityCode, bboxOfFeuille);
        responseManagerGeomutation = new ResponseManagerHTTP<>();
        Optional<Geomutation> optionalGeomutation = responseManagerGeomutation.getAPIReturn(callAPI, Geomutation.class);
        if (optionalGeomutation.isPresent()) {
            Geomutation geomutation = optionalGeomutation.orElse(new Geomutation());
            System.out.println(geomutation.showGeomutationContent());
            if (geomutation.getNext().isEmpty()){
                setOfGeomutations.addAll(geomutation.getFeatures());
            }
        }
    }

}
