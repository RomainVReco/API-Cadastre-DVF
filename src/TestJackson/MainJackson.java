package TestJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.geomutation.Geomutation;
import org.immo.servicepublicapi.AdresseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.immo.servicepublicapi.GeomutationAPI;
import org.immo.servicepublicapi.ParcelleAPI;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainJackson {

    public static void main(String[] args) throws IOException, URISyntaxException {
//        byte[] jsonData = Files.readAllBytes(Paths.get("Ressources/test.json"));
//        System.out.println(jsonData.length);
//
//        ObjectMapper objectMapper = new ObjectMapper();

//        Employee emp = objectMapper.readValue(jsonData, Employee.class);
//        System.out.println("Employee Object : \n"+emp);

//        byte[] otherData = Files.readAllBytes(Paths.get("Ressources/Issy2.json"));
//        ObjectMapper otherMapper = new ObjectMapper();
//        AdresseBAN ab = otherMapper.readValue(otherData, AdresseBAN.class);

        // AdresseAPI : 202 avenue du Maine
        String queryAdresse = "21 rue du captinaine Ferber 92130 Issy les Moulineaux";
        AdresseAPI adresse = new AdresseAPI(queryAdresse);

        String jsonResponse = adresse.readReponseFromAPI(adresse.getConn());

        ObjectMapper anotherMapper = new ObjectMapper();
        JsonNode jsonNode = anotherMapper.readTree(jsonResponse);

        ObjectMapper newMapper = new ObjectMapper();
        AdresseBAN ader = anotherMapper.readValue(jsonResponse, AdresseBAN.class);
//        System.out.println(ader.getFeatures().get(0).getGeometry().getCoordinates());
        System.out.println(" ");

        // Parcelle API : 202 avenue du Maine
        String queryParcelle = "{\"type\": \"Point\",\"coordinates\": [2.266267,48.825897]}";
        ParcelleAPI parcelleAPI = new ParcelleAPI(queryParcelle, "geom");
        String jsonParcelle = parcelleAPI.readReponseFromAPI(parcelleAPI.getConn());
        System.out.println(jsonParcelle);
        JsonNode jsonNodeParcelle = anotherMapper.readTree(jsonParcelle);


//        Parcelle parcelle = anotherMapper.readValue(jsonParcelle, Parcelle.class);
//        System.out.println(parcelle.getFeaturesParcelle().get(0).getParcelleProperties().toString());
//        String bboxAvMaine = parcelle.convertBboxToString();
//        System.out.println(bboxAvMaine);
        String bboxAvMaine = "    2.26434199,\n" +
                "    48.82464423,\n" +
                "    2.26776318,\n" +
                "    48.8290586";

        // Geomutation API : bbox 202 avenue du Maine 2017
        ObjectMapper geoMutationMapper = new ObjectMapper();
        geoMutationMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GeomutationAPI geomutationAPI = new GeomutationAPI("2017","92040", bboxAvMaine);
        String jsonGeoMutation = geomutationAPI.readReponseFromAPI(geomutationAPI.getConn());
        JsonNode jsonNodeGeoMutation = geoMutationMapper.readTree(jsonGeoMutation);
        Geomutation geomutation = geoMutationMapper.readValue(jsonGeoMutation, Geomutation.class);
        System.out.println(geomutation.getCount());
//        System.out.println(geomutation.getFeatures().size());
//        System.out.println(geomutation.getFeatures().get(0).getGeomutationPoperties().getValeurfonc());

    }

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("Using System Property: " + os);
        return os;
    }
}
