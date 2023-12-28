package TestJackson;

import org.immo.geojson.parcelle.FeatureParcelle;
import org.immo.geojson.parcelle.Parcelle;
import org.immo.model.ResponseManagerHTTP;
import org.immo.servicepublicapi.ParcelleAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
                System.out.println("Numéro de parcelle : "+ls.getTerrainProperties().getIdu());

                for (List<List<Object>> doubleList_2 : doubleList_1){
                    System.out.println("Nombre points polygone : "+doubleList_2.size());
                    for (int i =0; i<doubleList_2.size()-1; i++){
                        double m =  ((double) doubleList_2.get(i+1).get(1) - (double) doubleList_2.get(i).get(1)) /
                                ((double) doubleList_2.get(i+1).get(0) - (double) doubleList_2.get(i).get(0));
                        double p = ((double) doubleList_2.get(i).get(1)) - (m * (double) doubleList_2.get(i).get(0));
                        double xI = longitude;
                        double yI = m*xI+p;
                        if ((((xI-(double) doubleList_2.get(i).get(0))*(xI-(double) doubleList_2.get(i+1).get(0)))>0) &&
                        ((yI-R[1])*(latitude-yI)>0)) {
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
                    if (compteur%2 == 0) System.out.println("Le point n'est pas dans le polygone");
                    else System.out.println("Le point est dans le polygone");
                    compteur =0;
                }
            }
        }

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

//    @Test
//    void secondTestChatGPTSolution() throws IOException, URISyntaxException {
//        double latitude = 48.825897;
//        double longitude = 2.266267;
//        ParcelleAPI callAPI = new ParcelleAPI("","code_insee=92040&section=0E");
//        ResponseManagerHTTP<Parcelle> responseManagerHTTP = new ResponseManagerHTTP<>();
//        Optional<Parcelle> optionalParcelle = responseManagerHTTP.getAPIReturn(callAPI, Parcelle.class);
//        for (FeatureParcelle ls : optionalParcelle.get().getFeaturesTerrain()){
//            System.out.println("Numéro de parcelle : "+ls.getTerrainProperties().getIdu());
//            boolean isInside = isPointInsidePolygon(longitude, latitude, ls.getGeometry().getCoordinates());
//            // Output the result
//            if (isInside) {
//                System.out.println("The point is inside the polygon.");
//            } else {
//                System.out.println("The point is outside the polygon.");
//            }
//        }
//
//    }
//
//    private static boolean isPointInsidePolygon(double x, double y, List<List<List<List<Object>>>> polygon) {
//        int intersectCount = 0;
//        for (List<List<List<Object>>> outerList : polygon) {
//            for (List<List<Object>> innerList : outerList) {
//                for (int i = 0; i < innerList.size() - 1; i++) {
//                    double[] point1 = new double[]{(double) innerList.get(i).get(0), (double)innerList.get(i).get(1)};
//                    double[] point2 = new double[]{(double) innerList.get(i + 1).get(0), (double) innerList.get(i + 1).get(1)};
//                    if ((point1[1] > y) != (point2[1] > y) &&
//                            x < (point2[0] - point1[0]) * (y - point1[1]) / (point2[1] - point1[1]) + point1[0]) {
//                        intersectCount++;
//                        System.out.println("Compteur : "+intersectCount);
//                    }
//                }
//            }
//        }
//        return intersectCount % 2 == 1;
//    }

//    @Test
//    void testChatGPTsolution() throws IOException, URISyntaxException {
//        double latitude = 48.825897;
//        double longitude = 2.266267;
//        double[] R = {longitude, (latitude + 1)};
//        int compteur = 0;
//        ParcelleAPI callAPI = new ParcelleAPI("", "code_insee=92040&section=0E");
//        ResponseManagerHTTP<Parcelle> responseManagerHTTP = new ResponseManagerHTTP<>();
//        Optional<Parcelle> optionalParcelle = responseManagerHTTP.getAPIReturn(callAPI, Parcelle.class);
//        for (FeatureParcelle ls : optionalParcelle.get().getFeaturesTerrain()) {
//            System.out.println("Numéro de parcelle : "+ls.getTerrainProperties().getIdu());
//            for (List<List<List<Object>>> doubleList_1 : ls.getGeometry().getCoordinates()) {
//                System.out.println("Première liste : " + doubleList_1.size());
//                for (List<List<Object>> doubleList_2 : doubleList_1) {
//                    System.out.println("Deuxième liste : " + doubleList_2.size());
//                    boolean isInside = isPointInsidePolygon(longitude, latitude, doubleList_2);
//                    // Output the result
//                    if (isInside) {
//                        System.out.println("The point is inside the polygon.");
//                    } else {
//                        System.out.println("The point is outside the polygon.");
//                    }
//                }
//            }
//        }
//    }

    private String transformToHTTPS(String nextPageURL) {
        StringBuilder sb = new StringBuilder(nextPageURL);
        char s = 's';
        sb.insert(4, s);
        return sb.toString();
    }
//    private static boolean isPointInsidePolygon(double x, double y, List<List<Object>> polygon) {
//        int intersectCount = 0;
//        for (int i = 0; i < polygon.size() - 1; i++) {
//        double[] point1 = new double[]{(double) polygon.get(i).get(0), (double) polygon.get(i).get(1)};
//        double[] point2 = new double[]{(double) polygon.get(i + 1).get(0), (double) polygon.get(i + 1).get(1)};
//        if ((point1[1] > y) != (point2[1] > y) &&
//                x < (point2[0] - point1[0]) * (y - point1[1]) / (point2[1] - point1[1]) + point1[0]) {
//            intersectCount++;
//        }
//    }
//        return intersectCount % 2 == 1;
//}

    @Test
    void thirdTestChatGPTSolution(){
        double latitude = 48.825897;
        double longitude = 2.266267;

        // Define the polygon vertices (latitude and longitude coordinates)
        List<List<List<List<Double>>>> polygon = new ArrayList<>();
        List<List<List<Double>>> outerList = new ArrayList<>();
        List<List<Double>> innerList = new ArrayList<>();

        innerList.add(List.of(2.266198, 48.82594504));
        innerList.add(List.of(2.26620733, 48.82594987));
        innerList.add(List.of(2.26628429, 48.82598919));
        innerList.add(List.of(2.26632608, 48.8260105));
        innerList.add(List.of(2.26637625, 48.82603608));
        innerList.add(List.of(2.26659372, 48.82614732));
        innerList.add(List.of(2.26669029, 48.82619656));
        innerList.add(List.of(2.26675414, 48.8261215));
        innerList.add(List.of(2.26677148, 48.82612826));
        innerList.add(List.of(2.26708319, 48.82625042));
        innerList.add(List.of(2.26712805, 48.82619862));
        innerList.add(List.of(2.26718634, 48.82613134));
        innerList.add(List.of(2.266907, 48.8258671));
        innerList.add(List.of(2.26689423, 48.82585497));
        innerList.add(List.of(2.26688765, 48.82584881));
        innerList.add(List.of(2.26687582, 48.82583768));
        innerList.add(List.of(2.26677285, 48.82574018));
        innerList.add(List.of(2.26672661, 48.82569646));
        innerList.add(List.of(2.26657031, 48.82576853));
        innerList.add(List.of(2.26622826, 48.82592481));
        innerList.add(List.of(2.266198, 48.82594504));

        outerList.add(innerList);
        polygon.add(outerList);

        // Check if the point is inside the polygon
        boolean isInside = isPointInsidePolygon(longitude, latitude, polygon);

        // Output the result
        if (isInside) {
            System.out.println("The point is inside the polygon.");
        } else {
            System.out.println("The point is outside the polygon.");
        }
    }

    private static boolean isPointInsidePolygon(double x, double y, List<List<List<List<Double>>>> polygon) {
        int intersectCount = 0;
        for (List<List<List<Double>>> outerList : polygon) {
            for (List<Double> innerList : outerList.get(0)) {
                for (int i = 0, j = innerList.size() - 2; i < innerList.size(); i += 2) {
                    double xi = innerList.get(i);
                    double yi = innerList.get(i + 1);
                    double xj = innerList.get(j);
                    double yj = innerList.get(j + 1);

                    if ((yi > y) != (yj > y) && x < (xj - xi) * (y - yi) / (yj - yi) + xi) {
                        intersectCount++;
                    }
                }
            }
        }
        return intersectCount % 2 == 1;
    }
    }
