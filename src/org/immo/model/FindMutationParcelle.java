package org.immo.model;

import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.feuille.Feuille;
import org.immo.geojson.geomutation.FeatureMutation;
import org.immo.geojson.geomutation.Geomutation;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.servicepublicapi.AbstractRequestAPI;
import org.immo.userinput.GestionUser;

import java.util.HashSet;
import java.util.Set;

public class FindMutationParcelle extends FindMutation {
    private AbstractRequestAPI callAPI;
    private ResponseManagerHTTP<AdresseBAN> responseManagerAdresse;
    private ResponseManagerHTTP<Parcelle> responseManagerFeuille;
    private ResponseManagerHTTP<Geomutation> responseManagerGeomutation;
    private final String EMPTY_RETURN = "No object in POJO";
    private Set<FeatureMutation> setOfGeomutations = new HashSet<>();
    Geomutation geomutation;
    GestionUser gestionUser = new GestionUser();


}
