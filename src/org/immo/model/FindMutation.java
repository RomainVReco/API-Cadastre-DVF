package org.immo.model;

import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.adresseban.FeatureAdresseBAN;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FindMutation {

    private AbstractRequestAPI callAPI;
    private ResponseManagerHTTP<AdresseBAN> responseManagerAdresse;
    private ResponseManagerHTTP<Feuille> responseManagerFeuille;
    private ResponseManagerHTTP<Geomutation> responseManagerGeomutation;
    private final String EMPTY_RETURN = "No object in POJO";
    private Set<FeatureMutation> setOfGeomutations = new HashSet<>();
    Geomutation geomutation;
    GestionUser gestionUser = new GestionUser();

    public FindMutation(String query) {
        try {
            getAdressFromQuery(query);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String bboxOfFeuille = new String();

    }

    public void getAdressFromQuery(String adresse) throws IOException, URISyntaxException {
        callAPI = new AdresseAPI(adresse);
        responseManagerAdresse = new ResponseManagerHTTP<>();
        Optional<AdresseBAN> optionalAdresseBAN = responseManagerAdresse.getAPIReturn(callAPI, AdresseBAN.class);
        if (optionalAdresseBAN.isEmpty()){
            System.out.println("Erreur lors de la requête de cette adresse");
            return;
        } else getListOfAdress(optionalAdresseBAN);
    }

    private void getListOfAdress(Optional<AdresseBAN> optionalAdresseBAN) throws IOException, URISyntaxException {
        String bboxOfFeuille;
        AdresseBAN adresseBan;
        if (optionalAdresseBAN.isPresent()){
            adresseBan = optionalAdresseBAN.get();
        } else {
            System.out.println("Pas de résultat pour cette adresse : "+optionalAdresseBAN.get().getQuery());
            return;
        }
        if (adresseBan.getFeatures().size()==1) {
            String cityCode = adresseBan.getFeatures().get(0).getProperties().getCitycode();
            String geometryPoint = adresseBan.getFeatures().get(0).getGeometry().toString();
            bboxOfFeuille = getBboxOfFeuille(geometryPoint);
            getGeomutationsFromFeuille(bboxOfFeuille, cityCode);

        } else if (adresseBan.getFeatures().size()>1) {
            FeatureAdresseBAN selectedAdresse = selectAdressInList(adresseBan);
            String geometryPoint = selectedAdresse.getGeometry().toString();
            String cityCode = selectedAdresse.getProperties().getCitycode();
            bboxOfFeuille = getBboxOfFeuille(geometryPoint);
            getGeomutationsFromFeuille(bboxOfFeuille, cityCode);
        }
    }

    private FeatureAdresseBAN selectAdressInList(AdresseBAN adresseBan) {
        HashMap<Integer, FeatureAdresseBAN> listeOfAdress = new HashMap<>();
        int i = 1;
        System.out.println("\nSélectionnez l'adresse exacte : ");
        for (FeatureAdresseBAN adresse : adresseBan.getFeatures()) {
            listeOfAdress.put(i, adresse);
            System.out.printf("[%d] "+adresse.showAdressLabel()+"\n",i);
//            if (i < adresseBan.getFeatures().size()){
//                System.out.println("#####################################");
//            }
            i++;
        }
        String userChoice = gestionUser.promptSingleDigit("Numéro de ligne", adresseBan.getFeatures().size());
        return listeOfAdress.get(Integer.parseInt(userChoice));
    }

    private String getBboxOfFeuille(String geometryPoint) throws IOException, URISyntaxException {
        responseManagerFeuille = new ResponseManagerHTTP<>();
        callAPI = new FeuilleAPI(geometryPoint);
        Optional<Feuille> optionalFeuille = responseManagerFeuille.getAPIReturn(callAPI, Feuille.class);
        return optionalFeuille.get().convertBboxToString();
    }

    private void getGeomutationsFromFeuille(String bboxOfFeuille, String cityCode) throws URISyntaxException, IOException {
        System.out.println("Pour quelle année souhaitez-vous faire une recherche ? à partir de 2010");
        String inputYear = gestionUser.promptYear();
        callAPI = new GeomutationAPI(inputYear, cityCode, bboxOfFeuille);
        responseManagerGeomutation = new ResponseManagerHTTP<>();
        Optional<Geomutation> optionalGeomutation = responseManagerGeomutation.getAPIReturn(callAPI, Geomutation.class);
        if (optionalGeomutation.isPresent()) {
            geomutation = optionalGeomutation.orElse(new Geomutation());
            System.out.println(geomutation.showGeomutationContent());
                setOfGeomutations.addAll(geomutation.getFeatures());
        }
    }

    public Set<FeatureMutation> getSetOfGeomutations() {
        return setOfGeomutations;
    }

    public void setSetOfGeomutations(Set<FeatureMutation> setOfGeomutations) {
        this.setOfGeomutations = setOfGeomutations;
    }

    public Geomutation getGeomutation() {
        return geomutation;
    }

    public void setGeomutation(Geomutation geomutation) {
        this.geomutation = geomutation;
    }
}
