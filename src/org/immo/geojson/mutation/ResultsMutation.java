package org.immo.geojson.mutation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultsMutation {
    private int idmutation;
    private String idmutinvar;
    private String idopendata;
    private String datemut;
    private short anneemut;
    private String coddep;
    private String libnatmut;
    private boolean vefa;
    private float valeurfonc;
    private int nbcomm;
    @JsonProperty("l_codinsee")
    private List<String> lCodinsee;
    private int nbpar ;
    @JsonProperty("l_idpar")
    private String lIdpar;
    private int nbparmut;
    private List<String> lIdparmut;
    private float sterr;
    private int nbvolmut;
    private int nblocmut;
    @JsonProperty ("l_idlocmut")
    private List<String> lIdlocmut;
    private float sbati;
    private String codtypbien;
    private String libtypbien;

}
