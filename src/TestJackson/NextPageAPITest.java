package TestJackson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextPageAPITest {

    @Test
    void testTransformWithBuilder(){
        String original = "http://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?anneemut=2017&code_insee=69388&in_bbox=4.85858796%2C45.74394296%2C4.86421921%2C45.74717595&page=2";
        String manual = "https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?anneemut=2017&code_insee=69388&in_bbox=4.85858796%2C45.74394296%2C4.86421921%2C45.74717595&page=2";
        String newString = transformToHTTPS(original);
        assertEquals(manual, newString);
    }

    private String transformToHTTPS(String nextPageURL) {
        StringBuilder sb = new StringBuilder(nextPageURL);
        char s = 's';
        sb.insert(4, s);
        return sb.toString();
    }
}