package TestJackson;

import org.immo.servicepublicapi.AbstractRequestAPI;
import org.immo.servicepublicapi.CommuneAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class CommuneAPITest {

    @Test
    void testCommunityResponse() throws URISyntaxException, IOException {
        AbstractRequestAPI callAPI = new CommuneAPI("92130");
        String jsonResponse = callAPI.readReponseFromAPI(callAPI.getConn());
        System.out.println(jsonResponse.indexOf("code"));
        String code = jsonResponse.substring(38,43);
        System.out.println(code);
    }
}