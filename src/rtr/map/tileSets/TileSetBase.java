package rtr.map.tileSets;

import org.newdawn.slick.*;
import rtr.map.MapTilesLoader.TileSet;
import rtr.map.MapTilesLoader.TileSetType;
import rtr.resources.*;

public class TileSetBase
{
    protected String name;
    protected String fileName;
    protected String description;
    protected int tileGID;
    protected byte layer;
    protected TileSetType type;
    protected int upgradePhase;
    protected Color miniMapColor;
    protected ResourceModule.ResourceType resourceType;
    protected byte resourceAmount;
    protected byte resourceAmountMax;
    protected ResourceModule.ResourceColorSet resourceColorSet;
    protected int objectWidth;
    protected int objectHeight;
    protected TileSet canRegrow;
    protected int regrowRate;
    protected boolean regrowOnlyMotivated;
    protected boolean regrowIgnoreWeather;
    protected TileSet canSpread;
    protected int spreadRate;
    protected boolean spreadOnlyMotivated;
    protected boolean spreadIgnoreWeather;
    protected int essenceAmount;

    public TileSetBase() {
        super();
        this.upgradePhase = -1;
    }

    public TileSetBase(final int tGID, final TileSetType t) {
        super();
        this.upgradePhase = -1;
        this.tileGID = tGID;
        this.type = t;
    }

    public TileSetBase(final int tGID, final int uP, final TileSetType t) {
        super();
        this.upgradePhase = -1;
        this.tileGID = tGID;
        this.type = t;
        this.upgradePhase = uP;
    }

    public void initialize() {
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getDescription() {
        return this.description;
    }

    public int getTileGID() {
        return this.tileGID;
    }

    public byte getLayer() {
        return this.layer;
    }

    public TileSetType getType() {
        return this.type;
    }

    public Color getMiniMapColor() {
        if (this.miniMapColor == null) {
            return new Color(255, 0, 255, 255);
        }
        return this.miniMapColor;
    }

    public ResourceModule.ResourceType getResourceType() {
        return this.resourceType;
    }

    public byte getResourceAmount() {
        return this.resourceAmount;
    }

    public byte getResourceAmountMax() {
        return this.resourceAmountMax;
    }

    public ResourceModule.ResourceColorSet getResourceColorSet() {
        return this.resourceColorSet;
    }

    public int getObjectWidth() {
        return this.objectWidth;
    }

    public int getObjectHeight() {
        return this.objectHeight;
    }

    public TileSet canRegrow() {
        if (this.canRegrow == null) {
            return null;
        }
        return this.canRegrow;
    }

    public int getRegrowRate() {
        if (this.canRegrow == null) {
            return 0;
        }
        return this.regrowRate;
    }

    public boolean getRegrowOnlyMotivated() {
        return this.regrowOnlyMotivated;
    }

    public boolean getRegrowIgnoreWeather() {
        return this.regrowIgnoreWeather;
    }

    public TileSet canSpread() {
        if (this.canSpread == null) {
            return null;
        }
        return this.canSpread;
    }

    public boolean getSpreadOnlyMotivated() {
        return this.spreadOnlyMotivated;
    }

    public boolean getSpreadIgnoreWeather() {
        return this.spreadIgnoreWeather;
    }

    public int getSpreadRate() {
        if (this.canSpread == null) {
            return 0;
        }
        return this.spreadRate;
    }

    public int getEssenceAmount() {
        return this.essenceAmount;
    }
}
