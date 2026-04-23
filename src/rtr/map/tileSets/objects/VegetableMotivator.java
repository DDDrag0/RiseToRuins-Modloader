package rtr.map.tileSets.objects;

import org.newdawn.slick.Color;
import rtr.font.Text;
import rtr.map.MapTilesLoader;
import rtr.map.tileSets.TileSetBase;

public class VegetableMotivator extends TileSetBase {
    public VegetableMotivator(int tGID) {
        super(tGID, MapTilesLoader.TileSetType.OBJECT);
    }

    @Override
    public void initialize() {
        this.miniMapColor = new Color(-16719391);
        this.layer = (byte)11;
        this.objectWidth = 2;
        this.objectHeight = 2;
        this.fileName = "vegetableMotivator"; //nome del file png dal quale prende l'immagine la torre
        this.name = Text.getText("Vegetable Motivator");
        this.description = Text.getText("Motivates raw vegetable tiles");
    }
}

