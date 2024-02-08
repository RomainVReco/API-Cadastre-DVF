package TestJackson;

import org.immo.exceptions.NoParcelleException;
import org.immo.model.FindMutationParcelle;
import org.immo.userinput.GestionUser;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) {

        boolean goingOn = true;
        GestionUser userInput = new GestionUser();
        String answer = "";

        while (goingOn) {
            FindMutationParcelle findMutationParcelle = null;
            try {
                findMutationParcelle = new FindMutationParcelle();
            } catch (IOException | URISyntaxException | NoParcelleException e) {
                throw new RuntimeException(e);
            }

            answer = userInput.promptYesNo("Voulez-vous continuer ? ");
            if (answer.equals('N')) {
                goingOn = false;
            } else System.out.println(" ");
        }



//        System.out.println("Je lance le stream\n");
//        Stream<FeatureMutation> fmit = findMutationParcelle.getSetOfGeomutations().stream();
//        List<FeatureMutation> listOfFiltered = fmit
//                .filter(x -> x.getGeomutationPoperties().getlIdpar().contains("920400000E0076"))
//                .collect(Collectors.toList());
//
//        System.out.println("Fin du Stream et début de la liste de lecture \n");
//        System.out.println(listOfFiltered.size()+" achats VEFA parcelle 0I-45");
//        for (FeatureMutation tg : listOfFiltered){
//            System.out.println(tg.toString());
//        }
    }
}
