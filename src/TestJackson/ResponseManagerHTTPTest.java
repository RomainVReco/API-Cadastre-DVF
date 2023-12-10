package TestJackson;

import org.immo.exceptions.UnknownResponseCode;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.model.ResponseManagerHTTP;
import org.immo.servicepublicapi.AdresseAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ResponseManagerHTTPTest {

    @Test
    void controleAdresseRetourMultiples() throws IOException, URISyntaxException, UnknownResponseCode {
        String queryAdresse = "202 avenue du Maine";
        AdresseAPI adresse = new AdresseAPI(queryAdresse);
        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
        AdresseBAN adresseBAN = gestionCodeRetour.controleAdresseRetour(adresse).get();
        assertNotNull(adresseBAN);
        assertEquals(5, adresseBAN.getFeatures().size());
    }

    @Test
    void controleAdresseRetourUnique() throws IOException, URISyntaxException, UnknownResponseCode {
        String queryAdresse = "31 avenue du Bas Meudon";
        AdresseAPI adresse = new AdresseAPI(queryAdresse);
        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
        AdresseBAN adresseBAN = gestionCodeRetour.controleAdresseRetour(adresse).get();
        assertNotNull(adresseBAN);
        assertEquals(1, adresseBAN.getFeatures().size());
    }

    @Test
    void controleAdresseErreur400() throws IOException, URISyntaxException, UnknownResponseCode{
        String queryAdresse = "&é\"&é\"&z";
        AdresseAPI adresse = new AdresseAPI(queryAdresse);
        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
        assertEquals(400, adresse.getConn().getResponseCode());
        assertThrows(NoSuchElementException.class, () -> gestionCodeRetour.controleAdresseRetour(adresse).get());
    }

    @Test
    void controleParcelleRetourUnique() throws IOException, URISyntaxException {
        String queryAdresse = "202 avenue du Maine";
        AdresseAPI adresse = new AdresseAPI(queryAdresse);

    }
}