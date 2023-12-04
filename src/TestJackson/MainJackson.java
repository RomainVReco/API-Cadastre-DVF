package TestJackson;

import GeoJSON.AdresseBAN;
import ServicePublicAPI.AdresseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        String query = "31 avenue du bas meudon";
        AdresseAPI adresse = new AdresseAPI(query);

        String jsonResponse = adresse.readReponseFromAPI(adresse.getConn());

        ObjectMapper anotherMapper = new ObjectMapper();
        JsonNode jsonNode = anotherMapper.readTree(jsonResponse);

        System.out.println("Parsed JSON:");
        System.out.println(jsonNode.toString());

        ObjectMapper newMapper = new ObjectMapper();
        AdresseBAN ader = newMapper.readValue(jsonResponse, AdresseBAN.class);
        System.out.println(ader.getFeatures().get(0).getGeometry().toString());
        System.out.println(ader.getFeatures().get(0).getGeometry().getCoordinates());
        System.out.println(getOperatingSystem());




    }

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("Using System Property: " + os);
        return os;
    }
}
