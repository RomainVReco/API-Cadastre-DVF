package org.immo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.immo.exceptions.UnknownResponseCode;
import org.immo.geojson.adresseban.AdresseBAN;
import org.immo.servicepublicapi.AdresseAPI;

import java.io.IOException;
import java.util.Optional;

public class ResponseManagerHTTP {

    /**
     * A partir du code retour de l'appel à l'API Adresse, retourne ou non un POJO représentant les adresses contenues
     * dans la réponse
     * @param requeteAdresse
     * @return Un Optional contenant null ou un POJO AdresseBAN
     * @throws IOException
     */
    public Optional<AdresseBAN> controleAdresseRetour(AdresseAPI requeteAdresse) throws IOException, UnknownResponseCode {
        int codeRetour = requeteAdresse.getConn().getResponseCode();
        AdresseBAN adresseReponse;
        switch(codeRetour) {
            case 200:
                System.out.println("Code retour : 200. Requête OK");
                String jsonReponse = requeteAdresse.readReponseFromAPI(requeteAdresse.getConn());
                if (jsonReponse.isEmpty()) {
                    System.out.println("Le contenu de la réponse sur l'adresse est vide");
                    return Optional.empty();
                }
                else {
                    ObjectMapper objectMapper = new ObjectMapper();
                    adresseReponse = objectMapper.readValue(jsonReponse, AdresseBAN.class);
                    System.out.println(adresseReponse.showAdresseBANContent());
                }
                return Optional.of(adresseReponse);

            case 400:
                System.out.println("Code retour : 400. Les critères de recherche sont incorrectes");
                System.out.println("Motif : "+requeteAdresse.getConn().getResponseMessage());
                break;

            case 403:
                System.out.println("Code retour : 403. Vous ne pouvez pas accéder à ce contenu");
                break;

            case 404:
                System.out.println("Code retour : 404. L'API n'existe plus");
                break;
            /**
             * Limité à 50/secondes/IP
             */
            case 429:
                System.out.println("Code retour : 429. Trop de requêtes");
                break;

            case 500:
                System.out.println("Code retour : 500. Avez-vous une connexion internet ?");
                break;

            default:
                System.out.println("Cas non prévu, il faut checker les logs");
                throw new UnknownResponseCode("Code erreur inconnu");
        }
        return Optional.empty();
    }
}
