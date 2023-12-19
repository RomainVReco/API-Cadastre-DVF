package org.immo.geojson.feuille;

import org.immo.abstractcomponent.AbstractTerrain;
import org.immo.geojson.parcelle.FeatureParcelle;

public class Feuille extends AbstractTerrain<FeatureFeuille> {

    @Override
    public String showTerrainContent() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String limitLine = "\n################################################\n\n";
        System.out.println(getFeaturesTerrain().size());
        for (FeatureFeuille feature : getFeaturesTerrain()) {
            sb.append("Feuille id : ").append(feature.getId()).append(",\n");
            sb.append("Section : ").append(feature.getTerrainProperties().getSection()).append(",\n");
            sb.append("Code INSEE : ").append(feature.getTerrainProperties().getCodeInsee()).append(",\n");
            sb.append("Border box : ").append(this.convertBboxToString());
            if (!(i == getFeaturesTerrain().size()-1)) {
                sb.append(limitLine);
            }
            i++;
        }
        return sb.toString();
    }
}
