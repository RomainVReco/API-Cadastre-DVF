package org.immo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import org.immo.exceptions.NoParcelleException;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.adresseban.FeatureAdresseBAN;
import org.immo.geojson.commune.Commune;
import org.immo.geojson.commune.Communes;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.servicepublicapi.CommuneAPI;
import org.immo.servicepublicapi.ParcelleAPI;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class FindMutationParcelle extends FindMutation {
    boolean hasFoundAddress = false;

    public FindMutationParcelle () throws IOException, URISyntaxException, NoParcelleException {
        FeatureAdresseBAN adressToLook = new FeatureAdresseBAN() ;
        while (!hasFoundAddress){
            AdresseBAN listOfAdress = getAdressFromQuery();
            if (listOfAdress.getFeatures().size() !=0){
                adressToLook = selectAdressInList(listOfAdress);
                hasFoundAddress = true;
            } else hasFoundAddress = false;
        }
        String geometryPoint = getGeomtryPointFromAdress(adressToLook);
        String postCode = getCityCodeFromAdress(adressToLook);
        String codeInsee = getCodeInseeFromPostCode(postCode);
        String bbox = getBboxFromParcelle(geometryPoint);
        if (bbox.equals("empty")) {
            System.out.println("Oups, je suis vide");
            String section = getNearestSection(codeInsee, geometryPoint);
            bbox = getParecelleBboxFromSection(codeInsee, section, geometryPoint);
        }
        getGeomutationsFromTerrain(bbox, codeInsee);
    }

    private String getCodeInseeFromPostCode(String postCode) throws URISyntaxException, IOException {
        ResponseManagerHTTP<Commune[]> responseManagerHTTPCommune =  new ResponseManagerHTTP<>();
        callAPI = new CommuneAPI(postCode);
        Optional<Commune[]> optionalCommunes = responseManagerHTTPCommune.getAPIReturn(callAPI, Commune[].class);
        Commune[] communes = optionalCommunes.get();
        if (communes.length>1) {
            for (int i = 0; i < communes.length; i++) {
                System.out.println("[" + (i + 1) + "] " + communes[i].getNom() + " : " + communes[i].getCode() + "");
            }
            int positionCodeInsee = Integer.parseInt(gestionUser.promptSingleDigit("Votre choix : ", communes.length));
            return communes[positionCodeInsee - 1].getCode();
        } else return communes[0].getCode();



//        if (optionalCommunes.isPresent() && optionalCommunes.get().getListCommunes().size()>=1) {
//           Communes communes = optionalCommunes.get();
//           communes.setCommuneHashMap();
//           communes.allowCommuneChoice();
//           System.out.println(" ");
//           int positionCodeInsee = Integer.parseInt(gestionUser.promptSingleDigit("Choisissez la commune : ", communes.getListCommunes().size()));
//           return communes.getCommuneHashMap().get(positionCodeInsee).getCode();
//        } else return "00000";
//        String jsonResponse = callAPI.readReponseFromAPI(callAPI.getConn());
//        int indexOfCode = jsonResponse.indexOf("code");
//        System.out.println(jsonResponse);
//        System.out.println(jsonResponse.substring(indexOfCode+7,43));
//        return jsonResponse.substring(indexOfCode+7,43);
    }

    private String getCityCodeFromAdress(FeatureAdresseBAN adressToLook) {
        return adressToLook.getProperties().getPostcode();
    }

    private String getBboxFromParcelle(String geometryPoint) throws IOException, URISyntaxException {
        ResponseManagerHTTP<Parcelle> responseManagerParcelle = new ResponseManagerHTTP<>();
        callAPI = new ParcelleAPI(geometryPoint, "geom=");
        Optional<Parcelle> optionalParcelle = responseManagerParcelle.getAPIReturn(callAPI, Parcelle.class);
        if (optionalParcelle.isPresent()& optionalParcelle.get().getNumberReturned()!=0){
            return optionalParcelle.get().convertBboxToString();
        } else return "empty";
    }



}
