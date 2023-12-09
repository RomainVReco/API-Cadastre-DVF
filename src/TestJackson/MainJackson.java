package TestJackson;

import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.mutation.Geomutation;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.servicepublicapi.AdresseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.immo.servicepublicapi.FeuilleAPI;
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
        String queryAdresse = "202 avenue du Maine";
        AdresseAPI adresse = new AdresseAPI(queryAdresse);

        String jsonResponse = adresse.readReponseFromAPI(adresse.getConn());

        ObjectMapper anotherMapper = new ObjectMapper();
        JsonNode jsonNode = anotherMapper.readTree(jsonResponse);

        System.out.println("Parsed JSON:");
        System.out.println(jsonNode.toString());

        ObjectMapper newMapper = new ObjectMapper();
        AdresseBAN ader = anotherMapper.readValue(jsonResponse, AdresseBAN.class);
        System.out.println(" ");

        // Parcelle API : 202 avenue du Maine
        String queryParcelle = "{\"type\": \"Point\",\"coordinates\": [2.32557,48.830378]}";
        ParcelleAPI parcelleAPI = new ParcelleAPI(queryParcelle, "geom");
        String jsonParcelle = parcelleAPI.readReponseFromAPI(parcelleAPI.getConn());
        JsonNode jsonNodeParcelle = anotherMapper.readTree(jsonParcelle);

        Parcelle parcelle = anotherMapper.readValue(jsonParcelle, Parcelle.class);
        System.out.println(parcelle.getFeaturesParcelle().get(0).getParcelleProperties().toString());
        String bboxAvMaine = parcelle.convertBboxToString();

        // Geomutation API : bbox 202 avenue du Maine 2017
        GeomutationAPI geomutationAPI = new GeomutationAPI("2017","92040", bboxAvMaine);
        String jsonGeoMutation = geomutationAPI.readReponseFromAPI(geomutationAPI.getConn());
        JsonNode jsonNodeGeoMutation = anotherMapper.readTree(jsonGeoMutation);
        Geomutation geomutation = anotherMapper.readValue(jsonGeoMutation, Geomutation.class);
        geomutation.getResults().toString();

    }

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("Using System Property: " + os);
        return os;
    }
}
