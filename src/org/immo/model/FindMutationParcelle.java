package org.immo.model;

import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.adresseban.FeatureAdresseBAN;
import org.immo.geojson.feuille.Feuille;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.servicepublicapi.FeuilleAPI;
import org.immo.servicepublicapi.ParcelleAPI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class FindMutationParcelle extends FindMutation {
    boolean hasFoundAddress = false;

    public FindMutationParcelle () throws IOException, URISyntaxException {
        FeatureAdresseBAN adressToLook = new FeatureAdresseBAN() ;
        while (!hasFoundAddress){
            AdresseBAN listOfAdress = getAdressFromQuery();
            if (listOfAdress.getFeatures().size()>0){
                adressToLook = selectAdressInList(listOfAdress);
                hasFoundAddress = true;
            } else hasFoundAddress = false;
        }
        String geometryPoint = getGeomtryPointFromAdress(adressToLook);
        String cityCode = getCityCodeFromAdress(adressToLook);
        String bbox = getBboxFromParcelle(geometryPoint);
        getGeomutationsFromTerrain(bbox, cityCode);
    }


    private String getCityCodeFromAdress(FeatureAdresseBAN adressToLook) {
        return adressToLook.getProperties().getCitycode();
    }

    private String getBboxFromParcelle(String geometryPoint) throws IOException, URISyntaxException {
        ResponseManagerHTTP<Parcelle> responseManagerParcelle = new ResponseManagerHTTP<>();
        callAPI = new ParcelleAPI(geometryPoint, "geom");
        Optional<Parcelle> optionalParcelle = responseManagerParcelle.getAPIReturn(callAPI, Parcelle.class);
        return optionalParcelle.orElse(new Parcelle()).convertBboxToString();
    }



}
