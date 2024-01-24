package TestJackson;

import org.immo.exceptions.NoParcelleException;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.geojson.geomutation.FeatureMutation;
import org.immo.model.FindMutationFeuille;
import org.immo.model.FindMutationParcelle;
import org.immo.model.ResponseManagerHTTP;
import org.immo.servicepublicapi.AdresseAPI;
import org.immo.userinput.GestionUser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainJackson {

    public static void main(String[] args) {
//        FindMutationFeuille fm = new FindMutationFeuille();
//        System.out.println(fm.getSetOfGeomutations().size());
//

//        System.out.println("Je lance le stream\n");
//        Stream<FeatureMutation> fmit = fm.getSetOfGeomutations().stream();
//        List<FeatureMutation> listOfFiltered = fmit
//                .filter(x -> x.getGeomutationPoperties().getlIdpar().contains("920400000I0045"))
//                .filter(x -> x.getGeomutationPoperties().isVefa())
//                .collect(Collectors.toList());
//
//        System.out.println("Fin du Stream et début de la liste de lecture \n");
//        System.out.println(listOfFiltered.size()+" achats VEFA parcelle 0I-45");
//        for (FeatureMutation tg : listOfFiltered){
//            System.out.println(tg.toString());
//        }
//
//        AdresseAPI callAPI = new AdresseAPI("202 avenue du Maine");
//        ResponseManagerHTTP<AdresseBAN> adresseBANResponseManagerHTTP = new ResponseManagerHTTP<>();
//        Optional<AdresseBAN> optional = adresseBANResponseManagerHTTP.getAPIReturn(callAPI, AdresseBAN.class);
//        optional.get().showAdresseBANContent();
//        System.out.println(optional.get().toString());


        try {
            FindMutationParcelle findMutationParcelle = new FindMutationParcelle();
        } catch (IOException | URISyntaxException | NoParcelleException e) {
            throw new RuntimeException(e);
        }
    }

}
