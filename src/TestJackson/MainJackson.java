package TestJackson;

import org.immo.exceptions.UnknownResponseCode;
import org.immo.geojson.geomutation.FeatureMutation;
import org.immo.model.FindMutation;
import org.immo.userinput.GestionUser;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;


public class MainJackson {

    public static void main(String[] args) throws IOException, URISyntaxException, UnknownResponseCode {
        GestionUser gu = new GestionUser();
        String inputStreet = gu.promptString("Donnez une adresse");
        FindMutation fm = new FindMutation(inputStreet);
        System.out.println(fm.getSetOfGeomutations().size());

//        Stream<FeatureMutation> fmit = fm.getSetOfGeomutations().stream();
//        fmit.filter(x -> x.getGeomutationPoperties().getlIdpar().contains("[920400000I0045]"));
//        fmit.forEach(System.out::println);

    }

}
