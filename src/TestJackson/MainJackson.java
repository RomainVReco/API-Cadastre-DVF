package TestJackson;

import org.immo.geojson.geomutation.FeatureMutation;
import org.immo.model.FindMutationFeuille;
import org.immo.userinput.GestionUser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainJackson {

    public static void main(String[] args) {
        GestionUser gu = new GestionUser();
        String inputStreet = gu.promptString("Donnez une adresse");
        FindMutationFeuille fm = new FindMutationFeuille(inputStreet);
        System.out.println(fm.getSetOfGeomutations().size());

        System.out.println("Je lance le stream\n");
        Stream<FeatureMutation> fmit = fm.getSetOfGeomutations().stream();
        List<FeatureMutation> listOfFiltered = fmit
                .filter(x -> x.getGeomutationPoperties().getlIdpar().contains("920400000I0045"))
                .filter(x -> x.getGeomutationPoperties().isVefa())
                .collect(Collectors.toList());

        System.out.println("Fin du Stream et début de la liste de lecture \n");
        System.out.println(listOfFiltered.size()+" achats VEFA parcelle 0I-45");
        for (FeatureMutation tg : listOfFiltered){
            System.out.println(tg.toString());
        }

    }

}
