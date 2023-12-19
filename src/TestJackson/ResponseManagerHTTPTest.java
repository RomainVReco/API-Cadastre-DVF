package TestJackson;

import org.immo.exceptions.UnknownResponseCode;
import org.immo.geojson.feuille.Feuille;
import org.immo.model.ResponseManagerHTTP;
import org.immo.servicepublicapi.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ResponseManagerHTTPTest {

//    @Test
//    void controleAdresseRetourMultiples() throws IOException, URISyntaxException, UnknownResponseCode {
//        String queryAdresse = "202 avenue du Maine";
//        AdresseAPI adresse = new AdresseAPI(queryAdresse);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        AdresseBAN adresseBAN = gestionCodeRetour.controleAdresseRetour(adresse).get();
//        assertNotNull(adresseBAN);
//        assertEquals(5, adresseBAN.getFeatures().size());
//    }
//
//    @Test
//    void controleAdresseRetourUnique() throws IOException, URISyntaxException, UnknownResponseCode {
//        String queryAdresse = "31 avenue du Bas Meudon";
//        AdresseAPI adresse = new AdresseAPI(queryAdresse);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        AdresseBAN adresseBAN = gestionCodeRetour.controleAdresseRetour(adresse).get();
//        assertNotNull(adresseBAN);
//        assertEquals(1, adresseBAN.getFeatures().size());
//    }
//
//    @Test
//    void controleAdresseErreur400() throws IOException, URISyntaxException, UnknownResponseCode{
//        String queryAdresse = "&é\"&é\"&z";
//        AdresseAPI adresse = new AdresseAPI(queryAdresse);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        assertEquals(400, adresse.getConn().getResponseCode());
//        assertThrows(NoSuchElementException.class, () -> gestionCodeRetour.controleAdresseRetour(adresse).get());
//    }
//
//    @Test
//    void controleParcelleRetourUnique() throws IOException, URISyntaxException, UnknownResponseCode {
//        String queryAdresse = "202 avenue du Maine";
//        AdresseAPI adresse = new AdresseAPI(queryAdresse);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        AdresseBAN newAdress = gestionCodeRetour.controleAdresseRetour(adresse).get();
//        String pointQuery = newAdress.getFeatures().get(0).getGeometry().toString();
//        ParcelleAPI newParcelleQuery = new ParcelleAPI(pointQuery,"geom");
//        Parcelle newParcelle = gestionCodeRetour.controleParcelleRetour(newParcelleQuery).get();
//        assertNotNull(newParcelle);
//        assertEquals(1, newParcelle.getNumberReturned());
//    }
//
//    @Test
//    void controleParcelleRetourMultiples() throws IOException, URISyntaxException, UnknownResponseCode {
//        ParcelleAPI newParcelleQuery = new ParcelleAPI("CJ","section");
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        Parcelle newParcelle = gestionCodeRetour.controleParcelleRetour(newParcelleQuery).get();
//        assertTrue(newParcelle.getNumberReturned()>1);
//    }
//
//    @Test
//    void controleParcelleRetourVide() throws IOException, URISyntaxException, UnknownResponseCode {
//        ParcelleAPI newParcelleQuery = new ParcelleAPI("cj","section");
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        Optional<Parcelle> newParcelle = gestionCodeRetour.controleParcelleRetour(newParcelleQuery);
//        assertTrue(newParcelle.isEmpty());
//    }
//
//    @Test
//    void controleGeomutationRetourMultiples () throws IOException, URISyntaxException, UnknownResponseCode {
//        String queryAdresse = "202 avenue du Maine";
//        AdresseAPI adresse = new AdresseAPI(queryAdresse);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        AdresseBAN adresseBAN = gestionCodeRetour.controleAdresseRetour(adresse).get();
//        String pointQuery = adresseBAN.getFeatures().get(0).getGeometry().toString();
//        ParcelleAPI newParcelleQuery = new ParcelleAPI(pointQuery,"geom");
//        Parcelle newParcelle = gestionCodeRetour.controleParcelleRetour(newParcelleQuery).get();
//        String bbox = newParcelle.convertBboxToString();
//
//        GeomutationAPI geomutationAPI = new GeomutationAPI("2017", "75114", bbox);
//        Geomutation newGeomutation = gestionCodeRetour.controleGeomutationRetour(geomutationAPI).get();
//        assertTrue(newGeomutation.getCount()>1);
//    }
//
//    //Le test sera KO lorsque la vente de septembre 2023 sera intégrée
//    @Test
//    void controleGeomutationRetourVide() throws IOException, URISyntaxException, UnknownResponseCode {
//        String queryAdresse = "31 avenue du Bas Meudon";
//        AdresseAPI adresse = new AdresseAPI(queryAdresse);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        AdresseBAN adresseBAN = gestionCodeRetour.controleAdresseRetour(adresse).get();
//        String pointQuery = adresseBAN.getFeatures().get(0).getGeometry().toString();
//        ParcelleAPI newParcelleQuery = new ParcelleAPI(pointQuery,"geom");
//        Parcelle newParcelle = gestionCodeRetour.controleParcelleRetour(newParcelleQuery).get();
//        String bbox = newParcelle.convertBboxToString();
//        GeomutationAPI geomutationAPI = new GeomutationAPI("2023", pointQuery, "92040", bbox);
//        assertTrue(gestionCodeRetour.controleGeomutationRetour(geomutationAPI).isEmpty());
//    }
//
//    @Test
//    void controleMutationRetour () throws IOException, URISyntaxException, UnknownResponseCode {
//        MutationAPI mutationAPI = new MutationAPI(9957202);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        Mutation mutation = gestionCodeRetour.controleMutationRetour(mutationAPI).get();
//        mutation.showMutationContent();
//    }
//
//    @Test
//    void controleMutationRetourVide () throws IOException, URISyntaxException, UnknownResponseCode {
//        MutationAPI mutationAPI = new MutationAPI(0);
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        assertThrows(NoSuchElementException.class, () ->gestionCodeRetour.controleMutationRetour(mutationAPI).get());
//        assertEquals(404, mutationAPI.getConn().getResponseCode());
//    }
//
//    @Test
//    void controleFeuilleRetourMultiples () throws IOException, URISyntaxException, UnknownResponseCode {
//        String query = "{     \"type\": \"Point\",     \"coordinates\": [      2.247021,      48.822554     ]    }";
//        FeuilleAPI feuilleAPI = new FeuilleAPI(query, "geom");
//        ResponseManagerHTTP gestionCodeRetour = new ResponseManagerHTTP();
//        Optional<Feuille> optionalFeuille = gestionCodeRetour.controleFeuilleRetour(feuilleAPI);
//        assertEquals(200, feuilleAPI.getConn().getResponseCode());
//        assertTrue(optionalFeuille.isPresent());
//        assertEquals("urn:ogc:def:crs:EPSG::4326", optionalFeuille.get().getCrs().getCrsProperties().getName());
//    }

    @Test
    void checkReturn () throws IOException, URISyntaxException, UnknownResponseCode {
        String query = "{     \"type\": \"Point\",     \"coordinates\": [      2.247021,      48.822554     ]    }";
        FeuilleAPI feuilleAPI = new FeuilleAPI(query, "geom");
        ResponseManagerHTTP<Feuille> gestionCodeRetour = new ResponseManagerHTTP();
        Optional<Feuille> optionalFeuille = gestionCodeRetour.getAPIReturn(feuilleAPI, Feuille.class);
        System.out.println(optionalFeuille.get().showTerrainContent());
        assertEquals(200, feuilleAPI.getConn().getResponseCode());
        assertTrue(optionalFeuille.isPresent());
        assertEquals("urn:ogc:def:crs:EPSG::4326", optionalFeuille.get().getCrs().getCrsProperties().getName());
    }
}
