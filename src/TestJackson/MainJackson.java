package TestJackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainJackson {

    public static void main(String[] args) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("Ressources\\test.json"));
        System.out.println(jsonData.length);

        ObjectMapper objectMapper = new ObjectMapper();

        Employee emp = objectMapper.readValue(jsonData, Employee.class);

        System.out.println("Employee Object : \n"+emp);

    }
}
