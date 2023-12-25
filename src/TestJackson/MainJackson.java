package TestJackson;

import org.immo.exceptions.UnknownResponseCode;
import org.immo.model.FindMutation;
import org.immo.userinput.GestionUser;
import java.io.IOException;
import java.net.URISyntaxException;


public class MainJackson {

    public static void main(String[] args) throws IOException, URISyntaxException, UnknownResponseCode {
        GestionUser gu = new GestionUser();
        String inputStreet = gu.promptString("Donnez une adresse");
        FindMutation fm = new FindMutation(inputStreet);

    }

}
