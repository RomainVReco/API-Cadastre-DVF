package org.immo.model;

import org.immo.exceptions.NoParcelleException;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.adresseban.FeatureAdresseBAN;
import org.immo.geojson.commune.Commune;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.servicepublicapi.CommuneAPI;
import org.immo.servicepublicapi.ParcelleAPI;
import java.io.IOException;
import java.net.URISyntaxException;
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
//        ResponseManagerHTTP<List<Commune>> responseManagerHTTPCommune =  new ResponseManagerHTTP<>();
        callAPI = new CommuneAPI(postCode);
//        Optional<List<Commune>> commune = responseManagerHTTPCommune.getAPIReturn(callAPI, List.class);
        String jsonResponse = callAPI.readReponseFromAPI(callAPI.getConn());
        int indexOfCode = jsonResponse.indexOf("code");
        System.out.println(jsonResponse);
        System.out.println(jsonResponse.substring(indexOfCode+7,43));
        return jsonResponse.substring(indexOfCode+7,43);
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
