package org.immo.geojson;

import org.immo.geojson.adresseban.AdresseBAN;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TestLectureJSON{

        public static void main(String[] args) throws IOException {
                byte[] jsonData = Files.readAllBytes(Paths.get("Ressources\\Paris.json"));
                System.out.printf(String.valueOf(jsonData.length));
                ObjectMapper objectMapper = new ObjectMapper();

                AdresseBAN fab = objectMapper.readValue(jsonData, AdresseBAN.class);
                System.out.println("FeatureAdresseBan : "+fab);


        }
}
