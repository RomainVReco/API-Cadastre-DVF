package org.immo.geojson.commune;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Communes {

    private List<Commune> listCommunes;
    private HashMap<Integer, Commune> communeHashMap;

    public List<Commune> getListCommunes() {
        return listCommunes;
    }

    public HashMap<Integer, Commune> getCommuneHashMap() {
        return communeHashMap;
    }

    public void setCommuneHashMap () {
        int i = 0;
        for (Commune c : listCommunes){
            communeHashMap.put(i, c);
        }
    }

    public void allowCommuneChoice(){
        for (Map.Entry<Integer, Commune> c : communeHashMap.entrySet()){
            System.out.println('['+c.getKey()+ "] "+c.getValue().getNom()+" : "+c.getValue().getCode());
        }

    }
}
