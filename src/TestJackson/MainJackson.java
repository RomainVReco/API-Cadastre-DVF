package TestJackson;

import org.immo.exceptions.UnknownResponseCode;
import org.immo.geojson.feuille.Feuille;
import org.immo.model.ResponseManagerHTTP;
import org.immo.servicepublicapi.FeuilleAPI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

public class MainJackson {

    public static void main(String[] args) throws IOException, URISyntaxException, UnknownResponseCode {
        FeuilleAPI feuilleAPI = new FeuilleAPI("92040", "code_insee");
        ResponseManagerHTTP<Feuille> responseManagerHTTP = new ResponseManagerHTTP<>();
        Feuille feuilleSection = responseManagerHTTP.getAPIReturn(feuilleAPI, Feuille.class).get();
        Set<List<Double>> setSection = feuilleSection.getListOfSectionsFromCity();
        for (List<Double> bbox : setSection) {
            System.out.println(bbox.toString());
        }




    }

}
