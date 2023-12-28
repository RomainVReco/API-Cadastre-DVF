package TestJackson;

import org.immo.geojson.parcelle.FeatureParcelle;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.model.ResponseManagerHTTP;
import org.immo.servicepublicapi.ParcelleAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NextPageAPITest {

    @Test
    void testTransformWithBuilder(){
        String original = "http://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?anneemut=2017&code_insee=69388&in_bbox=4.85858796%2C45.74394296%2C4.86421921%2C45.74717595&page=2";
        String manual = "https://apidf-preprod.cerema.fr/dvf_opendata/geomutations/?anneemut=2017&code_insee=69388&in_bbox=4.85858796%2C45.74394296%2C4.86421921%2C45.74717595&page=2";
        String newString = transformToHTTPS(original);
        assertEquals(manual, newString);
    }

    @Test
    void calculateInsidePolygon() throws IOException, URISyntaxException {
        double latitude = 48.825897;
        double longitude = 2.266267;
        double[] R = {longitude, (latitude+1)};
        int compteur = 0;
        ParcelleAPI callAPI = new ParcelleAPI("","code_insee=92040&section=0E");
        ResponseManagerHTTP<Parcelle> responseManagerHTTP = new ResponseManagerHTTP<>();
        Optional<Parcelle> optionalParcelle = responseManagerHTTP.getAPIReturn(callAPI, Parcelle.class);
        for (FeatureParcelle ls : optionalParcelle.get().getFeaturesTerrain()){
            for (List<List<List<Object>>> doubleList_1 : ls.getGeometry().getCoordinates() ){
                System.out.println("Première liste : "+doubleList_1.size());
                for (List<List<Object>> doubleList_2 : doubleList_1){
                    System.out.println("Deuxième liste : "+doubleList_2.size());
                    for (int i =0; i<doubleList_2.size()-1; i++){
                        double m =  ((double) doubleList_2.get(i+1).get(1) - (double) doubleList_2.get(i).get(1)) /
                                ((double) doubleList_2.get(i+1).get(0) - (double) doubleList_2.get(i).get(0));
                        double p = ((double) doubleList_2.get(i).get(1)) - (m * (double) doubleList_2.get(i).get(0));
                        double xI = longitude;
                        double yI = m*xI+p;
                        if ((((xI-(double) doubleList_2.get(i).get(0))*(xI-(double) doubleList_2.get(i+1).get(0)))<0) &&
                        ((yI-R[1])*(latitude-yI)<0)) {
                            compteur++;
                            System.out.println("Compteur : "+compteur);
                        }
                    }
//                    for (List<Object> doubleList_3 : doubleList_2){
//                        System.out.println("Troisième liste : "+doubleList_3.size());
//                        System.out.println("Latitude : "+(double)doubleList_3.get(0)+ "Longitude : "+(double)doubleList_3.get(1));
//                        System.out.println("Coordonnées une : "+((double)doubleList_3.get(0)-longitude));
//                        System.out.println("Coordonnées deux : "+((double)doubleList_3.get(1)-latitude));
//                    }
                }
            }
        }
        if (compteur%2 == 0) System.out.println("Le point n'est pas dans le polygone");
         else System.out.println("Le point est dans le polygone");

//        assertTrue(optionalParcelle.get().getFeaturesTerrain().size()>1);
    }

    @Test
    void calculateInsidePolygonSecondTest() throws IOException, URISyntaxException {
        double longitude = 48.830378;
        double latitude = 2.3255700;
        ParcelleAPI callAPI = new ParcelleAPI("","code_insee=75056&section=CJ&code_arr=114");
        ResponseManagerHTTP<Parcelle> responseManagerHTTP = new ResponseManagerHTTP<>();
        Optional<Parcelle> optionalParcelle = responseManagerHTTP.getAPIReturn(callAPI, Parcelle.class);
        for (FeatureParcelle ls : optionalParcelle.get().getFeaturesTerrain()){
            System.out.println(ls.getTerrainProperties().getSection());
            for (List<List<List<Object>>> doubleList_1 : ls.getGeometry().getCoordinates() ){
                for (List<List<Object>> doubleList_2 : doubleList_1){
                    for (List<Object> doubleList_3 : doubleList_2){
                        System.out.println("Latitude : "+(double)doubleList_3.get(0)+ " Longitude : "+(double)doubleList_3.get(1));
                        System.out.println("Coordonnées une : "+((double)doubleList_3.get(0)-latitude));
                        System.out.println("Coordonnées deux : "+((double)doubleList_3.get(1)-longitude));
                    }
                    System.out.println("################################");
                }
            }
        }
        assertTrue(optionalParcelle.get().getFeaturesTerrain().size()>1);
    }

    @Test
    void calculateOutsidePolygon() throws IOException, URISyntaxException {
        double longitude = 48.825897;
        double latitude = 2.266267;
        ParcelleAPI callAPI = new ParcelleAPI("","code_insee=92040&section=0D");
        ResponseManagerHTTP<Parcelle> responseManagerHTTP = new ResponseManagerHTTP<>();
        Optional<Parcelle> optionalParcelle = responseManagerHTTP.getAPIReturn(callAPI, Parcelle.class);
        for (FeatureParcelle ls : optionalParcelle.get().getFeaturesTerrain()){
            for (List<List<List<Object>>> doubleList_1 : ls.getGeometry().getCoordinates() ){
                for (List<List<Object>> doubleList_2 : doubleList_1){
                    for (List<Object> doubleList_3 : doubleList_2){
                        System.out.println("Latitude : "+(double)doubleList_3.get(0)+ " Longitude : "+(double)doubleList_3.get(1));
                        System.out.println("Coordonnées une : "+((double)doubleList_3.get(0)-latitude));
                        System.out.println("Coordonnées deux : "+((double)doubleList_3.get(1)-longitude));
                    }
                }
            }
        }
        assertTrue(optionalParcelle.get().getFeaturesTerrain().size()>1);
    }

    private String transformToHTTPS(String nextPageURL) {
        StringBuilder sb = new StringBuilder(nextPageURL);
        char s = 's';
        sb.insert(4, s);
        return sb.toString();
    }
}