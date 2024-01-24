package TestJackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.servicepublicapi.ParcelleAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParcelleTest {

    @Test
    void convertBboxToString() throws IOException, URISyntaxException {
        ObjectMapper anotherMapper = new ObjectMapper();
        String queryParcelle = "{\"type\": \"Point\",\"coordinates\": [2.32557,48.830378]}";
        ParcelleAPI parcelleAPI = new ParcelleAPI(queryParcelle, "geom");
        String jsonParcelle = parcelleAPI.readReponseFromAPI(parcelleAPI.getConn());
        Parcelle parcelle = anotherMapper.readValue(jsonParcelle, Parcelle.class);
        String bboxAvMaine = parcelle.convertBboxToString();
        System.out.println(bboxAvMaine);
        assertEquals("2.32476735,48.83012991,2.32562559,48.83054392", bboxAvMaine);
    }

    @Test
    void getYear() {
        LocalDateTime dateForYear = LocalDateTime.now();
        int year = dateForYear.getYear();
        assertEquals(2024, year);
        assertEquals(2022, year-2);
    }


}