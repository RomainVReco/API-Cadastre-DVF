package org.immo.geojson.commune;

import javax.xml.stream.events.Comment;
import java.util.List;

public class Commune {

    private String nom;
    private String code;
    private String codeDepartement;
    private String siren;
    private String codeEpci;
    private String codeRegion;
    private List<String> codesPostaux;
    private int population;

    public Commune(String codeInsee) {
        this.code = codeInsee;
    }
    public Commune() {
    }

    public String showCommuneInformations(){
        StringBuilder sb = new StringBuilder();
        String limitLine = "\n################################################\n\n";
        sb.append("Nom de la commune : ").append(this.nom).append("\n");
        sb.append("Code Insee : ").append(this.code).append("\n");
        sb.append("Codes postaux : ").append(this.codesPostaux).append("\n");
        sb.append("Population : ").append(this.population).append("\n");
        sb.append(limitLine);
        return sb.toString();
    }

    public String getNom() {
        return nom;
    }

    public String getCode() {
        return code;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public String getSiren() {
        return siren;
    }

    public String getCodeEpci() {
        return codeEpci;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public List<String> getCodesPostaux() {
        return codesPostaux;
    }

    public int getPopulation() {
        return population;
    }
}
