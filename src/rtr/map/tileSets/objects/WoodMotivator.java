package rtr.map.tileSets.objects;

import org.newdawn.slick.Color;
import rtr.font.Text;
import rtr.map.MapTilesLoader;
import rtr.map.tileSets.TileSetBase;

public class WoodMotivator extends TileSetBase {
    public WoodMotivator(int tGID) {
        super(tGID, MapTilesLoader.TileSetType.OBJECT);
    }

    @Override
    public void initialize() {
        this.miniMapColor = new Color(-16719391);
        this.layer = (byte)11;
        this.objectWidth = 2;
        this.objectHeight = 2;
        this.fileName = "woodMotivator"; //nome del file png dal quale prende l'immagine la torre
        this.name = Text.getText("Wood Motivator");
        this.description = Text.getText("Motivates wood tiles");
    }
}

