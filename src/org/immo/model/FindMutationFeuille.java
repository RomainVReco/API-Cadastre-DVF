package org.immo.model;

import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.adresseban.FeatureAdresseBAN;
import org.immo.geojson.feuille.Feuille;
import org.immo.geojson.geomutation.FeatureMutation;
import org.immo.geojson.geomutation.Geomutation;
import org.immo.servicepublicapi.*;
import org.immo.userinput.GestionUser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FindMutationFeuille extends FindMutation {

    private AbstractRequestAPI callAPI;
    private ResponseManagerHTTP<AdresseBAN> responseManagerAdresse;
    private ResponseManagerHTTP<Feuille> responseManagerFeuille;
    private ResponseManagerHTTP<Geomutation> responseManagerGeomutation;
    private final String EMPTY_RETURN = "No object in POJO";
    private Set<FeatureMutation> setOfGeomutations = new HashSet<>();
    Geomutation geomutation;
    GestionUser gestionUser = GestionUser.getInstance();

    public FindMutationFeuille() {
        try {
            getAdressFromQuery();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String bboxOfFeuille = new String();
    }

    public AdresseBAN getAdressFromQuery() throws IOException, URISyntaxException {
        String query = gestionUser.promptString("Donner une adresse : ");
        callAPI = new AdresseAPI(query);
        responseManagerAdresse = new ResponseManagerHTTP<>();
        Optional<AdresseBAN> optionalAdresseBAN = responseManagerAdresse.getAPIReturn(callAPI, AdresseBAN.class);
        if (optionalAdresseBAN.isEmpty()){
            System.out.println("Erreur lors de la requête de cette adresse");
        } else getListOfAdress(optionalAdresseBAN);
        return null;
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
            getGeomutationsFromTerrain(bboxOfFeuille, cityCode);

        } else if (adresseBan.getFeatures().size()>1) {
            FeatureAdresseBAN selectedAdresse = selectAdressInList(adresseBan);
            String geometryPoint = selectedAdresse.getGeometry().toString();
            String cityCode = selectedAdresse.getProperties().getCitycode();
            bboxOfFeuille = getBboxOfFeuille(geometryPoint);
            getGeomutationsFromTerrain(bboxOfFeuille, cityCode);
        }
    }

    public FeatureAdresseBAN selectAdressInList(AdresseBAN adresseBan) {
        HashMap<Integer, FeatureAdresseBAN> listeOfAdress = new HashMap<>();
        int i = 1;
        System.out.println("\nSélectionnez l'adresse exacte : ");
        for (FeatureAdresseBAN adresse : adresseBan.getFeatures()) {
            listeOfAdress.put(i, adresse);
            System.out.printf("[%d] "+adresse.showAdressLabel()+"\n",i);
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

}
