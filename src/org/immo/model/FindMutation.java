package org.immo.model;

import org.immo.exceptions.NoParcelleException;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.adresseban.FeatureAdresseBAN;
import org.immo.geojson.feuille.Feuille;
import org.immo.geojson.geomutation.FeatureMutation;
import org.immo.geojson.geomutation.Geomutation;
import org.immo.geojson.parcelle.FeatureParcelle;
import org.immo.geojson.parcelle.OrderByDistanceReference;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.geojson.parcelle.SimplifiedParcelle;
import org.immo.servicepublicapi.*;
import org.immo.userinput.GestionUser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public abstract class FindMutation {
    protected AbstractRequestAPI callAPI;
    protected ResponseManagerHTTP<AdresseBAN> responseManagerAdresse;
    protected ResponseManagerHTTP<Geomutation> responseManagerGeomutation;
    protected final String EMPTY_RETURN = "No object in POJO";
    protected Set<FeatureMutation> setOfGeomutations = new HashSet<>();
    Geomutation geomutation;
    GestionUser gestionUser = GestionUser.getInstance();


    public AdresseBAN getAdressFromQuery() throws IOException, URISyntaxException {
        String query = gestionUser.promptString("Donner une adresse");
        callAPI = new AdresseAPI(query);
        responseManagerAdresse = new ResponseManagerHTTP<>();
        Optional<AdresseBAN> optionalAdresseBAN = responseManagerAdresse.getAPIReturn(callAPI, AdresseBAN.class);
        if (optionalAdresseBAN.isEmpty()){
            System.out.println("Erreur lors de la requête de cette adresse");
        }
        return optionalAdresseBAN.orElse(new AdresseBAN());
    }

    public FeatureAdresseBAN selectAdressInList(AdresseBAN adresseBan) throws IOException, URISyntaxException {
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

    public String getGeomtryPointFromAdress(FeatureAdresseBAN adressToLook) {
        return adressToLook.getGeometry().toString();
    }

    public void getGeomutationsFromTerrain(String bboxOfFeuille, String cityCode) throws URISyntaxException, IOException {
        System.out.println("Pour quelle année souhaitez-vous faire une recherche ? à partir de 2010");
        String inputYear = gestionUser.promptYear();
        callAPI = new GeomutationAPI(inputYear, cityCode, bboxOfFeuille);
        responseManagerGeomutation = new ResponseManagerHTTP<>();
        Optional<Geomutation> optionalGeomutation = responseManagerGeomutation.getAPIReturn(callAPI, Geomutation.class);
        if (optionalGeomutation.isPresent()) {
            geomutation = optionalGeomutation.orElse(new Geomutation());
            System.out.println(geomutation.showGeomutationContent());
            setOfGeomutations.addAll(geomutation.getFeatures());
            while (geomutation.getNext() != null) {
                callAPI = new NextPageAPI(geomutation.getNext());
                geomutation = responseManagerGeomutation.getAPIReturn(callAPI, Geomutation.class).get();
                System.out.println(geomutation.showGeomutationContent());
                setOfGeomutations.addAll(geomutation.getFeatures());
            }
        } else {
            System.out.println("Pas de mutation pout cette adresse");
        }
    }

    public String getNearestSection(String cityCode, String geometryPoint) throws IOException, URISyntaxException, NoParcelleException {
        callAPI = new FeuilleAPI(cityCode, geometryPoint, 2);
        ResponseManagerHTTP<Feuille> feuilleResponseManagerHTTP = new ResponseManagerHTTP<>();
        Optional<Feuille> optionalFeuille = feuilleResponseManagerHTTP.getAPIReturn(callAPI, Feuille.class);
        if (optionalFeuille.isPresent() && optionalFeuille.get().getNumberReturned()!=0) {
            return optionalFeuille.get().getFeaturesTerrain().get(0).getTerrainProperties().getSection();
        } else throw new NoParcelleException("Pas de section trouvée");
    }

    public String getParecelleBboxFromSection(String cityCode, String section, String geometryPoint) throws IOException, URISyntaxException, NoParcelleException {
        callAPI = new ParcelleAPI(cityCode, section, 2);
        ResponseManagerHTTP<Parcelle> parcelleResponseManagerHTTP = new ResponseManagerHTTP<>();
        Optional<Parcelle> optionalParcelle = parcelleResponseManagerHTTP.getAPIReturn(callAPI, Parcelle.class);
        if (optionalParcelle.isPresent()) {
            Parcelle parcelleToReview = optionalParcelle.get();
            return checkForNearestParcelle(parcelleToReview, geometryPoint);
        } else throw new NoParcelleException("Pas de parcelle trouvée");

    }

    private String checkForNearestParcelle(Parcelle parcelleToReview, String geometryPoint) {
        List<Double> longLat = new ArrayList<>();
        longLat = extractLatitudeLongitude(geometryPoint);
        List<SimplifiedParcelle> simplifiedParcelleList = new ArrayList<>();
        List<FeatureParcelle> parcellesToScan = parcelleToReview.getFeaturesTerrain();
        for (FeatureParcelle featureTerrain : parcellesToScan) {
            SimplifiedParcelle subparcelle = new SimplifiedParcelle();
            subparcelle.setBbox(featureTerrain.getBbox());
            subparcelle.setGeometryPolygon(featureTerrain.getGeometry());
            subparcelle.setId(featureTerrain.getTerrainProperties().getIdu());
            subparcelle.setConvertedBbox(featureTerrain.convertBboxToString());
            // racine((xb-xa)²+(yb-ya)²)
            double distanceMinEucliX = Math.pow(subparcelle.getBbox().get(0)-longLat.get(0),2);
            double distanceMinEucliY = Math.pow(subparcelle.getBbox().get(1)-longLat.get(1),2);
            double distanceMinLongitude = Math.sqrt(distanceMinEucliX+distanceMinEucliY);

            double distanceMaxEucliX = Math.pow(subparcelle.getBbox().get(2)-longLat.get(0),2);
            double distanceMaxEucliY = Math.pow(subparcelle.getBbox().get(3)-longLat.get(1),2);
            double distanceMaxLongitude = Math.sqrt(distanceMaxEucliX+distanceMaxEucliY);
            subparcelle.setDistanceFromReference(Math.abs(distanceMinLongitude)+Math.abs(distanceMaxLongitude));
            simplifiedParcelleList.add(subparcelle);
        }
        Collections.sort(simplifiedParcelleList, new OrderByDistanceReference());
        System.out.println(simplifiedParcelleList.get(0).toString());
        return simplifiedParcelleList.get(0).getConvertedBbox();
    }

    private List<Double> extractLatitudeLongitude(String geometryPoint) {
        int firstBrace = geometryPoint.indexOf("[");
        String removeFirstBrace = geometryPoint.substring(firstBrace+1);
        int secondBrace = removeFirstBrace.indexOf("]");
        String removeSecondBrace = removeFirstBrace.substring(0, secondBrace);
        String[] coordinatesToParse = removeSecondBrace.split(",");
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(Double.valueOf(coordinatesToParse[0]));
        doubleList.add(Double.valueOf(coordinatesToParse[1]));
        return doubleList;
    }

    public AbstractRequestAPI getCallAPI() {
        return callAPI;
    }

    public void setCallAPI(AbstractRequestAPI callAPI) {
        this.callAPI = callAPI;
    }

    public ResponseManagerHTTP<AdresseBAN> getResponseManagerAdresse() {
        return responseManagerAdresse;
    }

    public void setResponseManagerAdresse(ResponseManagerHTTP<AdresseBAN> responseManagerAdresse) {
        this.responseManagerAdresse = responseManagerAdresse;
    }

    public ResponseManagerHTTP<Geomutation> getResponseManagerGeomutation() {
        return responseManagerGeomutation;
    }

    public void setResponseManagerGeomutation(ResponseManagerHTTP<Geomutation> responseManagerGeomutation) {
        this.responseManagerGeomutation = responseManagerGeomutation;
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

    public GestionUser getGestionUser() {
        return gestionUser;
    }
}
