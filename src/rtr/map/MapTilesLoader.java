package rtr.map;

import java.util.*;
import rtr.resources.*;
import org.newdawn.slick.*;
import rtr.utilities.*;
import rtr.map.tileSets.*;
import rtr.*;
import r2d.image.*;
import java.io.*;

public class MapTilesLoader
{
    private HashMap<Integer, Image> tiles;
    private HashMap<Integer, byte[][]> collisionTiles;
    private HashMap<Integer, byte[][]> depthTiles;
    private HashMap<Integer, byte[][]> particleTiles;
    private HashMap<Integer, byte[][][]> shadowTiles;
    private HashMap<Integer, Image> objects;
    private HashMap<Integer, TileSet> GIDToTileSet;
    private HashMap<Integer, Boolean> isLoaded;
    private Properties properties;
    private HashMap<Integer, String> tileName;
    private HashMap<Integer, String> tileDescription;
    private HashMap<Integer, Byte> tileBlockLarge;
    private HashMap<Integer, Byte> tileBlockSmall;
    private HashMap<Integer, Byte> tileBlockRoad;
    private HashMap<Integer, Byte> tileBlockGate;
    private HashMap<Integer, Byte> tileBlockLight;
    private HashMap<Integer, Byte> tileBlockWater;
    private HashMap<Integer, Byte> tileBlockCorruption;
    private HashMap<Integer, Byte> tileBlockGhost;
    private HashMap<Integer, Integer> tileMovementCost;
    private HashMap<Integer, Byte> tileDepth;
    private HashMap<Integer, Byte> tileParticles;
    private HashMap<Integer, Byte> tileShadow;
    private HashMap<Integer, Byte> tileLight;
    private HashMap<Integer, Byte> tileLayer;
    private HashMap<Integer, TileSetType> tileType;
    private HashMap<Integer, Byte> tileCollision;
    private HashMap<Integer, ResourceModule.ResourceType> tileResourceType;
    private HashMap<Integer, Byte> tileResourceAmount;
    private HashMap<Integer, Byte> tileResourceAmountMax;
    private HashMap<Integer, ResourceModule.ResourceColorSet> tileResourceColorSet;
    private HashMap<Integer, Byte> tileCanClear;
    private HashMap<Integer, Byte> tileInvulnerable;
    private HashMap<Integer, Byte> tileFrozen;
    private HashMap<Integer, Byte> tileWater;
    private HashMap<Integer, Byte> tileCourierGolemParking;
    private HashMap<Integer, Byte> tileInteractPoint;
    private HashMap<Integer, Byte> tileFunctionalPoint;
    private HashMap<Integer, Byte> tilePrayPoint;
    private HashMap<Integer, Byte> tileVesselPoint;
    private HashMap<Integer, Byte> tileCrops;
    private HashMap<Integer, Color> tileMiniMapColor;
    private HashMap<Integer, TileSet> tileCanRegrow;
    private HashMap<Integer, Integer> tileRegrowRate;
    private HashMap<Integer, Byte> tileRegrowOnlyMotivated;
    private HashMap<Integer, Byte> tileRegrowIgnoreWeather;
    private HashMap<Integer, TileSet> tileCanSpread;
    private HashMap<Integer, Integer> tileSpreadRate;
    private HashMap<Integer, Byte> tileSpreadOnlyMotivated;
    private HashMap<Integer, Byte> tileSpreadIgnoreWeather;
    private HashMap<Integer, Integer> tileEssenceAmount;
    private HashMap<Integer, Byte> tilePropHeight;
    private HashMap<Integer, Byte> tilePropWidth;
    private Color[] colorNorth;
    private Color[] colorEast;
    private Color[] colorSouth;
    private Color[] colorWest;
    private Color[] colorAll;
    private static final byte COLLISION_NONE = 0;
    private static final byte COLLISION_NORTH_LOW = 1;
    private static final byte COLLISION_NORTH_MEDIUM = 2;
    private static final byte COLLISION_NORTH_HIGH = 3;
    private static final byte COLLISION_EAST_LOW = 4;
    private static final byte COLLISION_EAST_MEDIUM = 5;
    private static final byte COLLISION_EAST_HIGH = 6;
    private static final byte COLLISION_SOUTH_LOW = 7;
    private static final byte COLLISION_SOUTH_MEDIUM = 8;
    private static final byte COLLISION_SOUTH_HIGH = 9;
    private static final byte COLLISION_WEST_LOW = 10;
    private static final byte COLLISION_WEST_MEDIUM = 11;
    private static final byte COLLISION_WEST_HIGH = 12;
    private static final byte COLLISION_ALL_LOW = 13;
    private static final byte COLLISION_ALL_MEDIUM = 14;
    private static final byte COLLISION_ALL_HIGH = 15;
    private static final Color fireRedVeryLow;
    private static final Color fireRedLow;
    private static final Color fireRedMedium;
    private static final Color fireRedHigh;
    private static final Color fireYellowVeryLow;
    private static final Color fireYellowLow;
    private static final Color fireYellowMedium;
    private static final Color fireYellowHigh;
    private static final Color fireGreenVeryLow;
    private static final Color fireGreenLow;
    private static final Color fireGreenMedium;
    private static final Color fireGreenHigh;
    private static final Color fireBlueVeryLow;
    private static final Color fireBlueLow;
    private static final Color fireBlueMedium;
    private static final Color fireBlueHigh;
    private static final Color firePurpleVeryLow;
    private static final Color firePurpleLow;
    private static final Color firePurpleMedium;
    private static final Color firePurpleHigh;
    private static final Color magicRedLow;
    private static final Color magicRedMedium;
    private static final Color magicRedHigh;
    private static final Color magicYellowLow;
    private static final Color magicYellowMedium;
    private static final Color magicYellowHigh;
    private static final Color magicGreenLow;
    private static final Color magicGreenMedium;
    private static final Color magicGreenHigh;
    private static final Color magicBlueLow;
    private static final Color magicBlueMedium;
    private static final Color magicBlueHigh;
    private static final Color magicPurpleLow;
    private static final Color magicPurpleMedium;
    private static final Color magicPurpleHigh;
    private static final Color smokeLow;
    private static final Color smokeMedium;
    private static final Color smokeHigh;
    private static final Color redSparklesVeryLow;
    private static final Color redSparklesLow;
    private static final Color redSparklesMedium;
    private static final Color redSparklesHigh;
    private static final Color greenSparklesVeryLow;
    private static final Color greenSparklesLow;
    private static final Color greenSparklesMedium;
    private static final Color greenSparklesHigh;
    private static final Color blueSparklesVeryLow;
    private static final Color blueSparklesLow;
    private static final Color blueSparklesMedium;
    private static final Color blueSparklesHigh;
    private static final Color purpleSparklesVeryLow;
    private static final Color purpleSparklesLow;
    private static final Color purpleSparklesMedium;
    private static final Color purpleSparklesHigh;
    private static final Color yellowSparklesVeryLow;
    private static final Color yellowSparklesLow;
    private static final Color yellowSparklesMedium;
    private static final Color yellowSparklesHigh;
    private static final Color magicFireRedLow;
    private static final Color magicFireRedMedium;
    private static final Color magicFireRedHigh;
    private static final Color magicFireYellowLow;
    private static final Color magicFireYellowMedium;
    private static final Color magicFireYellowHigh;
    private static final Color magicFireGreenLow;
    private static final Color magicFireGreenMedium;
    private static final Color magicFireGreenHigh;
    private static final Color magicFireBlueLow;
    private static final Color magicFireBlueMedium;
    private static final Color magicFireBlueHigh;
    private static final Color magicFirePurpleLow;
    private static final Color magicFirePurpleMedium;
    private static final Color magicFirePurpleHigh;
    private static int TERRAIN_TILE_RANGE;
    private static int TERRAIN_TILE_SPACING;
    public static int TERRAIN_TILE_SUB;
    private static int OBJECT_TILE_RANGE;
    private static int OBJECT_TILE_SPACING;
    private static int OBJECT_TILE_SUB;

    public enum TileSetType {
        TERRAIN,
        OBJECT,
        WALL,
        ROAD
    }

    public enum TileSetObjectPhase {
        ABANDONED(5500),
        BASE(0),
        CONSTRUCTION_OUTLINE(5000),
        CONSTRUCTION_PHASE_5(4500),
        CONSTRUCTION_PHASE_4(4000),
        CONSTRUCTION_PHASE_3(3500),
        CONSTRUCTION_PHASE_2(3000),
        CONSTRUCTION_PHASE_1(2500),
        FILL_PHASE_4(2000),
        FILL_PHASE_3(1500),
        FILL_PHASE_2(1000),
        FILL_PHASE_1(500);

        private final int tileOffset;

        TileSetObjectPhase(int tileOffset) {
            this.tileOffset = tileOffset;
        }

        public int getTileOffset() {
            return tileOffset;
        }
    }

    static {
        fireRedVeryLow = new Color(100, 50, 50);
        fireRedLow = new Color(150, 50, 50);
        fireRedMedium = new Color(200, 50, 50);
        fireRedHigh = new Color(250, 50, 50);
        fireYellowVeryLow = new Color(100, 100, 50);
        fireYellowLow = new Color(150, 150, 50);
        fireYellowMedium = new Color(200, 200, 50);
        fireYellowHigh = new Color(250, 250, 50);
        fireGreenVeryLow = new Color(50, 100, 50);
        fireGreenLow = new Color(50, 150, 50);
        fireGreenMedium = new Color(50, 200, 50);
        fireGreenHigh = new Color(50, 250, 50);
        fireBlueVeryLow = new Color(50, 50, 100);
        fireBlueLow = new Color(50, 50, 150);
        fireBlueMedium = new Color(50, 50, 200);
        fireBlueHigh = new Color(50, 50, 250);
        firePurpleVeryLow = new Color(100, 50, 100);
        firePurpleLow = new Color(150, 50, 150);
        firePurpleMedium = new Color(200, 50, 200);
        firePurpleHigh = new Color(250, 50, 250);
        magicRedLow = new Color(250, 100, 100);
        magicRedMedium = new Color(250, 150, 150);
        magicRedHigh = new Color(250, 200, 200);
        magicYellowLow = new Color(250, 250, 100);
        magicYellowMedium = new Color(250, 250, 150);
        magicYellowHigh = new Color(250, 250, 200);
        magicGreenLow = new Color(100, 250, 100);
        magicGreenMedium = new Color(150, 250, 150);
        magicGreenHigh = new Color(200, 250, 200);
        magicBlueLow = new Color(100, 100, 250);
        magicBlueMedium = new Color(150, 150, 250);
        magicBlueHigh = new Color(200, 200, 250);
        magicPurpleLow = new Color(250, 100, 250);
        magicPurpleMedium = new Color(250, 150, 250);
        magicPurpleHigh = new Color(250, 200, 250);
        smokeLow = new Color(150, 150, 150);
        smokeMedium = new Color(200, 200, 200);
        smokeHigh = new Color(250, 250, 250);
        redSparklesVeryLow = new Color(100, 0, 0);
        redSparklesLow = new Color(150, 0, 0);
        redSparklesMedium = new Color(200, 0, 0);
        redSparklesHigh = new Color(250, 0, 0);
        greenSparklesVeryLow = new Color(0, 100, 0);
        greenSparklesLow = new Color(0, 150, 0);
        greenSparklesMedium = new Color(0, 200, 0);
        greenSparklesHigh = new Color(0, 250, 0);
        blueSparklesVeryLow = new Color(0, 0, 100);
        blueSparklesLow = new Color(0, 0, 150);
        blueSparklesMedium = new Color(0, 0, 200);
        blueSparklesHigh = new Color(0, 0, 250);
        purpleSparklesVeryLow = new Color(100, 0, 100);
        purpleSparklesLow = new Color(150, 0, 150);
        purpleSparklesMedium = new Color(200, 0, 200);
        purpleSparklesHigh = new Color(250, 0, 250);
        yellowSparklesVeryLow = new Color(100, 100, 0);
        yellowSparklesLow = new Color(150, 150, 0);
        yellowSparklesMedium = new Color(200, 200, 0);
        yellowSparklesHigh = new Color(250, 250, 0);
        magicFireRedLow = new Color(240, 100, 100);
        magicFireRedMedium = new Color(240, 150, 150);
        magicFireRedHigh = new Color(240, 200, 200);
        magicFireYellowLow = new Color(240, 240, 100);
        magicFireYellowMedium = new Color(240, 240, 150);
        magicFireYellowHigh = new Color(240, 240, 200);
        magicFireGreenLow = new Color(100, 240, 100);
        magicFireGreenMedium = new Color(150, 240, 150);
        magicFireGreenHigh = new Color(200, 240, 200);
        magicFireBlueLow = new Color(100, 100, 240);
        magicFireBlueMedium = new Color(150, 150, 240);
        magicFireBlueHigh = new Color(200, 200, 240);
        magicFirePurpleLow = new Color(240, 100, 240);
        magicFirePurpleMedium = new Color(240, 150, 240);
        magicFirePurpleHigh = new Color(240, 200, 240);
        MapTilesLoader.TERRAIN_TILE_RANGE = 10000;
        MapTilesLoader.TERRAIN_TILE_SPACING = 200000;
        MapTilesLoader.TERRAIN_TILE_SUB = 500;
        MapTilesLoader.OBJECT_TILE_RANGE = 200000000;
        MapTilesLoader.OBJECT_TILE_SPACING = 200000;
        MapTilesLoader.OBJECT_TILE_SUB = 10000;
    }

    private static int calcTerrainTileID(final int rangePosition, final int subPosition) {
        int tileOut = MapTilesLoader.TERRAIN_TILE_RANGE;
        tileOut += MapTilesLoader.TERRAIN_TILE_SPACING * rangePosition;
        tileOut += MapTilesLoader.TERRAIN_TILE_SUB * subPosition;
        return tileOut;
    }

    private static int calcObjectTileID(final int rangePosition, final int subPosition) {
        int tileOut = MapTilesLoader.OBJECT_TILE_RANGE;
        tileOut += MapTilesLoader.OBJECT_TILE_SPACING * rangePosition;
        tileOut += MapTilesLoader.OBJECT_TILE_SUB * subPosition;
        return tileOut;
    }

    MapTilesLoader() throws SlickException {
        super();
        this.tiles = new HashMap<Integer, Image>();
        this.collisionTiles = new HashMap<Integer, byte[][]>();
        this.depthTiles = new HashMap<Integer, byte[][]>();
        this.particleTiles = new HashMap<Integer, byte[][]>();
        this.shadowTiles = new HashMap<Integer, byte[][][]>();
        this.objects = new HashMap<Integer, Image>();
        this.GIDToTileSet = new HashMap<Integer, TileSet>();
        this.isLoaded = new HashMap<Integer, Boolean>();
        this.properties = new Properties();
        this.tileName = new HashMap<Integer, String>();
        this.tileDescription = new HashMap<Integer, String>();
        this.tileBlockLarge = new HashMap<Integer, Byte>();
        this.tileBlockSmall = new HashMap<Integer, Byte>();
        this.tileBlockRoad = new HashMap<Integer, Byte>();
        this.tileBlockGate = new HashMap<Integer, Byte>();
        this.tileBlockLight = new HashMap<Integer, Byte>();
        this.tileBlockWater = new HashMap<Integer, Byte>();
        this.tileBlockCorruption = new HashMap<Integer, Byte>();
        this.tileBlockGhost = new HashMap<Integer, Byte>();
        this.tileMovementCost = new HashMap<Integer, Integer>();
        this.tileDepth = new HashMap<Integer, Byte>();
        this.tileParticles = new HashMap<Integer, Byte>();
        this.tileShadow = new HashMap<Integer, Byte>();
        this.tileLight = new HashMap<Integer, Byte>();
        this.tileLayer = new HashMap<Integer, Byte>();
        this.tileType = new HashMap<Integer, TileSetType>();
        this.tileCollision = new HashMap<Integer, Byte>();
        this.tileResourceType = new HashMap<Integer, ResourceModule.ResourceType>();
        this.tileResourceAmount = new HashMap<Integer, Byte>();
        this.tileResourceAmountMax = new HashMap<Integer, Byte>();
        this.tileResourceColorSet = new HashMap<Integer, ResourceModule.ResourceColorSet>();
        this.tileCanClear = new HashMap<Integer, Byte>();
        this.tileInvulnerable = new HashMap<Integer, Byte>();
        this.tileFrozen = new HashMap<Integer, Byte>();
        this.tileWater = new HashMap<Integer, Byte>();
        this.tileCourierGolemParking = new HashMap<Integer, Byte>();
        this.tileInteractPoint = new HashMap<Integer, Byte>();
        this.tileFunctionalPoint = new HashMap<Integer, Byte>();
        this.tilePrayPoint = new HashMap<Integer, Byte>();
        this.tileVesselPoint = new HashMap<Integer, Byte>();
        this.tileCrops = new HashMap<Integer, Byte>();
        this.tileMiniMapColor = new HashMap<Integer, Color>();
        this.tileCanRegrow = new HashMap<Integer, TileSet>();
        this.tileRegrowRate = new HashMap<Integer, Integer>();
        this.tileRegrowOnlyMotivated = new HashMap<Integer, Byte>();
        this.tileRegrowIgnoreWeather = new HashMap<Integer, Byte>();
        this.tileCanSpread = new HashMap<Integer, TileSet>();
        this.tileSpreadRate = new HashMap<Integer, Integer>();
        this.tileSpreadOnlyMotivated = new HashMap<Integer, Byte>();
        this.tileSpreadIgnoreWeather = new HashMap<Integer, Byte>();
        this.tileEssenceAmount = new HashMap<Integer, Integer>();
        this.tilePropHeight = new HashMap<Integer, Byte>();
        this.tilePropWidth = new HashMap<Integer, Byte>();
        if (this.colorNorth == null) {
            (this.colorNorth = new Color[3])[0] = new Color(50, 0, 0, 255);
            this.colorNorth[1] = new Color(150, 0, 0, 255);
            this.colorNorth[2] = new Color(250, 0, 0, 255);
        }
        if (this.colorEast == null) {
            (this.colorEast = new Color[3])[0] = new Color(50, 50, 0, 255);
            this.colorEast[1] = new Color(150, 150, 0, 255);
            this.colorEast[2] = new Color(250, 250, 0, 255);
        }
        if (this.colorSouth == null) {
            (this.colorSouth = new Color[3])[0] = new Color(0, 50, 0, 255);
            this.colorSouth[1] = new Color(0, 150, 0, 255);
            this.colorSouth[2] = new Color(0, 250, 0, 255);
        }
        if (this.colorWest == null) {
            (this.colorWest = new Color[3])[0] = new Color(0, 0, 50, 255);
            this.colorWest[1] = new Color(0, 0, 150, 255);
            this.colorWest[2] = new Color(0, 0, 250, 255);
        }
        if (this.colorAll == null) {
            (this.colorAll = new Color[3])[0] = new Color(50, 50, 50, 255);
            this.colorAll[1] = new Color(150, 150, 150, 255);
            this.colorAll[2] = new Color(250, 250, 250, 255);
        }
        TileSet[] values;
        for (int length = (values = TileSet.values()).length, i = 0; i < length; ++i) {
            final TileSet t = values[i];
            if (t.getTileSetTemplate().getType() == TileSetType.OBJECT || t.getTileSetTemplate().getType() == TileSetType.WALL) {
                TileSetObjectPhase[] values2;
                for (int length2 = (values2 = TileSetObjectPhase.values()).length, j = 0; j < length2; ++j) {
                    final TileSetObjectPhase p = values2[j];
                    this.GIDToTileSet.put(t.getTileSetTemplate().getTileGID() + p.getTileOffset(), t);
                }
            }
            else {
                this.GIDToTileSet.put(t.getTileSetTemplate().getTileGID(), t);
            }
        }
    }

    public void checkAndloadTileset(final int tileID) throws SlickException {
        final int globalID = this.getTileSetGID(tileID);
        if (this.isLoaded.get(globalID) == null || !this.isLoaded.get(globalID)) {
            Utilities.startTimer();
            final TileSet t = this.getTileSetByTileID(globalID);
            if (t == null) {
                System.out.println("Failed to Load Tileset: (" + globalID + ") ");
                return;
            }
            final TileSetBase template = t.getTileSetTemplate();
            template.initialize();
            if (template.getType() != TileSetType.OBJECT) {
                try {
                    this.loadTerrain(template);
                }
                catch (final FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                this.loadObject(template);
            }
            System.out.print("Loaded Tileset: " + t + " (" + globalID + ") ");
            Utilities.endTimer();
        }
    }

    private void loadTerrain(final TileSetBase tileSetBase) throws SlickException, FileNotFoundException {
        this.properties.clear();
        final Image baseImage = ImageLoader.getImage("res/maps/base/" + tileSetBase.getFileName() + ".png");
        final SpriteSheet baseSheet = new SpriteSheet(baseImage, 16, 16);
        final int startGID = tileSetBase.getTileGID();
        final int tileSheetH = baseSheet.getHorizontalCount();
        final int tileSheetV = baseSheet.getVerticalCount();
        this.isLoaded.put(startGID, true);
        for (int y = 0; y < tileSheetV; ++y) {
            for (int x = 0; x < tileSheetH; ++x) {
                final int tileID = y * tileSheetH + x;
                this.tiles.put(startGID + tileID, baseSheet.getSprite(x, y));
                this.tileName.put(startGID + tileID, tileSetBase.getName());
                this.tileDescription.put(startGID + tileID, tileSetBase.getDescription());
                this.tileLayer.put(startGID + tileID, tileSetBase.getLayer());
                this.tileType.put(startGID + tileID, tileSetBase.getType());
                if (tileSetBase.getResourceType() != null) {
                    this.tileResourceType.put(startGID + tileID, tileSetBase.getResourceType());
                    this.tileResourceAmount.put(startGID + tileID, tileSetBase.getResourceAmount());
                    this.tileResourceAmountMax.put(startGID + tileID, tileSetBase.getResourceAmountMax());
                    this.tileResourceColorSet.put(startGID + tileID, tileSetBase.getResourceColorSet());
                }
                this.tileMiniMapColor.put(startGID + tileID, tileSetBase.getMiniMapColor());
                if (tileSetBase.canRegrow() != null) {
                    this.tileCanRegrow.put(startGID + tileID, tileSetBase.canRegrow());
                    this.tileRegrowRate.put(startGID + tileID, tileSetBase.getRegrowRate());
                    if (tileSetBase.getRegrowOnlyMotivated()) {
                        this.tileRegrowOnlyMotivated.put(startGID + tileID, (byte)1);
                    }
                    if (tileSetBase.getRegrowIgnoreWeather()) {
                        this.tileRegrowIgnoreWeather.put(startGID + tileID, (byte)1);
                    }
                }
                if (tileSetBase.canSpread() != null) {
                    this.tileCanSpread.put(startGID + tileID, tileSetBase.canSpread());
                    this.tileSpreadRate.put(startGID + tileID, tileSetBase.getSpreadRate());
                    if (tileSetBase.getSpreadOnlyMotivated()) {
                        this.tileSpreadOnlyMotivated.put(startGID + tileID, (byte)1);
                    }
                    if (tileSetBase.getSpreadIgnoreWeather()) {
                        this.tileSpreadIgnoreWeather.put(startGID + tileID, (byte)1);
                    }
                }
                if (tileSetBase.getEssenceAmount() > 0) {
                    this.tileEssenceAmount.put(startGID + tileID, tileSetBase.getEssenceAmount());
                }
                this.tilePropHeight.put(startGID + tileID, (byte)tileSetBase.getObjectHeight());
                this.tilePropWidth.put(startGID + tileID, (byte)tileSetBase.getObjectWidth());
            }
        }
        File file = new File("res/maps/data/" + tileSetBase.getFileName() + ".png");
        if (file.exists()) {
            final FileInputStream fileStream = new FileInputStream(file);
            if (fileStream != null) {
                final Image dataImage = ImageLoader.getImage("res/maps/data/" + tileSetBase.getFileName() + ".png");
                final SpriteSheet dataSheet = new SpriteSheet(dataImage, 16, 16);
                for (int y2 = 0; y2 < tileSheetV; ++y2) {
                    for (int x2 = 0; x2 < tileSheetH; ++x2) {
                        final int tileID2 = y2 * tileSheetH + x2;
                        this.generateBasicTileData(tileSetBase, x2, y2, startGID, tileID2, dataSheet);
                    }
                }
                dataSheet.flushPixelData();
                ImageLoader.unloadImage("res/maps/data/" + tileSetBase.getFileName() + ".png");
            }
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
        file = new File("res/maps/collision/" + tileSetBase.getFileName() + ".png");
        if (file.exists()) {
            final FileInputStream fileStream = new FileInputStream(file);
            if (fileStream != null) {
                final Image collisionImage = ImageLoader.getImage("res/maps/collision/" + tileSetBase.getFileName() + ".png");
                final SpriteSheet collisionSheet = new SpriteSheet(collisionImage, 16, 16);
                for (int y2 = 0; y2 < tileSheetV; ++y2) {
                    for (int x2 = 0; x2 < tileSheetH; ++x2) {
                        final int tileID2 = y2 * tileSheetH + x2;
                        this.generateCollisionData(tileSetBase, x2, y2, startGID, tileID2, collisionSheet);
                    }
                }
                collisionSheet.flushPixelData();
                ImageLoader.unloadImage("res/maps/collision/" + tileSetBase.getFileName() + ".png");
            }
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
        file = new File("res/maps/depth/" + tileSetBase.getFileName() + ".png");
        if (file.exists()) {
            final FileInputStream fileStream = new FileInputStream(file);
            if (fileStream != null) {
                final Image depthImage = ImageLoader.getImage("res/maps/depth/" + tileSetBase.getFileName() + ".png");
                final SpriteSheet depthSheet = new SpriteSheet(depthImage, 16, 16);
                for (int y2 = 0; y2 < tileSheetV; ++y2) {
                    for (int x2 = 0; x2 < tileSheetH; ++x2) {
                        final int tileID2 = y2 * tileSheetH + x2;
                        this.generateDepthData(tileSetBase, x2, y2, startGID, tileID2, depthSheet);
                    }
                }
                depthSheet.flushPixelData();
                ImageLoader.unloadImage("res/maps/depth/" + tileSetBase.getFileName() + ".png");
            }
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
        file = new File("res/maps/particle/" + tileSetBase.getFileName() + ".png");
        if (file.exists()) {
            final FileInputStream fileStream = new FileInputStream(file);
            if (fileStream != null) {
                final Image particleImage = ImageLoader.getImage("res/maps/particle/" + tileSetBase.getFileName() + ".png");
                final SpriteSheet particleSheet = new SpriteSheet(particleImage, 16, 16);
                for (int y2 = 0; y2 < tileSheetV; ++y2) {
                    for (int x2 = 0; x2 < tileSheetH; ++x2) {
                        final int tileID2 = y2 * tileSheetH + x2;
                        this.generateParticleData(tileSetBase, x2, y2, startGID, tileID2, particleSheet);
                    }
                }
                particleSheet.flushPixelData();
                ImageLoader.unloadImage("res/maps/particle/" + tileSetBase.getFileName() + ".png");
            }
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
        file = new File("res/maps/shadow/" + tileSetBase.getFileName() + ".png");
        if (file.exists()) {
            final FileInputStream fileStream = new FileInputStream(file);
            if (fileStream != null) {
                final Image shadowImage = ImageLoader.getImage("res/maps/shadow/" + tileSetBase.getFileName() + ".png");
                final SpriteSheet shadowSheet = new SpriteSheet(shadowImage, 16, 16);
                for (int y2 = 0; y2 < tileSheetV; ++y2) {
                    for (int x2 = 0; x2 < tileSheetH; ++x2) {
                        final int tileID2 = y2 * tileSheetH + x2;
                        this.generateShadowData(tileSetBase, x2, y2, startGID, tileID2, shadowSheet);
                    }
                }
                shadowSheet.flushPixelData();
                ImageLoader.unloadImage("res/maps/shadow/" + tileSetBase.getFileName() + ".png");
            }
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadObject(final TileSetBase tileSetBase) throws SlickException {
        this.properties.clear();
        final int startGID = tileSetBase.getTileGID();
        int currentGID = 0;
        final int objW = tileSetBase.getObjectWidth();
        final int objH = tileSetBase.getObjectHeight();
        boolean hasFill = false;
        final Image baseImage = ImageLoader.getImage("res/maps/base/" + tileSetBase.getFileName() + ".png");
        if (baseImage.getWidth() > objW * 16) {
            hasFill = true;
        }
        int hMax = 1;
        int vMax = 8;
        if (hasFill) {
            hMax = 2;
            vMax = 6;
        }
        for (int spriteY = 0; spriteY < vMax; ++spriteY) {
            for (int spriteX = 0; spriteX < hMax; ++spriteX) {
                currentGID = this.tempGetCurrentGID(spriteX, spriteY, startGID, hMax == 2, false);
                final Image subBaseImage = baseImage.getSubImage(spriteX * objW * 16, spriteY * objH * 16, objW * 16, objH * 16);
                final SpriteSheet baseSheet = new SpriteSheet(subBaseImage, 16, 16);
                final int tileSheetH = baseSheet.getHorizontalCount();
                final int tileSheetV = baseSheet.getVerticalCount();
                this.objects.put(currentGID, subBaseImage);
                this.isLoaded.put(currentGID, true);
                for (int y = 0; y < tileSheetV; ++y) {
                    for (int x = 0; x < tileSheetH; ++x) {
                        final int tileID = y * tileSheetH + x;
                        this.tiles.put(currentGID + tileID, baseSheet.getSprite(x, y));
                        this.tileName.put(currentGID + tileID, tileSetBase.getName());
                        this.tileDescription.put(currentGID + tileID, tileSetBase.getDescription());
                        this.tileLayer.put(currentGID + tileID, tileSetBase.getLayer());
                        this.tileType.put(currentGID + tileID, tileSetBase.getType());
                        if (tileSetBase.getResourceType() != null) {
                            this.tileResourceType.put(currentGID + tileID, tileSetBase.getResourceType());
                            this.tileResourceAmount.put(currentGID + tileID, tileSetBase.getResourceAmount());
                            this.tileResourceAmountMax.put(currentGID + tileID, tileSetBase.getResourceAmountMax());
                            this.tileResourceColorSet.put(currentGID + tileID, tileSetBase.getResourceColorSet());
                        }
                        this.tileMiniMapColor.put(currentGID + tileID, tileSetBase.getMiniMapColor());
                        if (tileSetBase.canRegrow() != null) {
                            this.tileCanRegrow.put(currentGID + tileID, tileSetBase.canRegrow());
                            this.tileRegrowRate.put(currentGID + tileID, tileSetBase.getRegrowRate());
                        }
                        if (tileSetBase.canSpread() != null) {
                            this.tileCanSpread.put(currentGID + tileID, tileSetBase.canSpread());
                            this.tileSpreadRate.put(currentGID + tileID, tileSetBase.getSpreadRate());
                        }
                        if (tileSetBase.getEssenceAmount() > 0) {
                            this.tileEssenceAmount.put(currentGID + tileID, tileSetBase.getEssenceAmount());
                        }
                        this.tilePropHeight.put(currentGID + tileID, (byte)tileSetBase.getObjectHeight());
                        this.tilePropWidth.put(currentGID + tileID, (byte)tileSetBase.getObjectWidth());
                    }
                }
            }
        }
        final File dataCheck = new File("res/maps/data/" + tileSetBase.getFileName() + ".png");
        Image dataImage = null;
        if (dataCheck.exists()) {
            dataImage = ImageLoader.getImage("res/maps/data/" + tileSetBase.getFileName() + ".png");
        }
        final File collisionCheck = new File("res/maps/collision/" + tileSetBase.getFileName() + ".png");
        Image collisionImage = null;
        if (collisionCheck.exists()) {
            collisionImage = ImageLoader.getImage("res/maps/collision/" + tileSetBase.getFileName() + ".png");
        }
        final File depthCheck = new File("res/maps/depth/" + tileSetBase.getFileName() + ".png");
        Image depthImage = null;
        if (depthCheck.exists()) {
            depthImage = ImageLoader.getImage("res/maps/depth/" + tileSetBase.getFileName() + ".png");
        }
        final File particleCheck = new File("res/maps/particle/" + tileSetBase.getFileName() + ".png");
        Image particleImage = null;
        if (particleCheck.exists()) {
            particleImage = ImageLoader.getImage("res/maps/particle/" + tileSetBase.getFileName() + ".png");
        }
        final File shadowCheck = new File("res/maps/shadow/" + tileSetBase.getFileName() + ".png");
        Image shadowImage = null;
        if (shadowCheck.exists()) {
            shadowImage = ImageLoader.getImage("res/maps/shadow/" + tileSetBase.getFileName() + ".png");
        }
        if (dataCheck.exists()) {
            for (int spriteY2 = 0; spriteY2 < vMax; ++spriteY2) {
                for (int spriteX2 = 0; spriteX2 < hMax; ++spriteX2) {
                    currentGID = this.tempGetCurrentGID(spriteX2, spriteY2, startGID, hMax == 2, false);
                    final Image subDataImage = dataImage.getSubImage(spriteX2 * objW * 16, spriteY2 * objH * 16, objW * 16, objH * 16);
                    final SpriteSheet dataSheet = new SpriteSheet(subDataImage, 16, 16);
                    final int tileSheetH2 = dataSheet.getHorizontalCount();
                    for (int tileSheetV2 = dataSheet.getVerticalCount(), y2 = 0; y2 < tileSheetV2; ++y2) {
                        for (int x2 = 0; x2 < tileSheetH2; ++x2) {
                            final int tileID2 = y2 * tileSheetH2 + x2;
                            this.generateBasicTileData(tileSetBase, x2, y2, currentGID, tileID2, dataSheet);
                        }
                    }
                    dataSheet.flushPixelData();
                }
            }
        }
        if (collisionCheck.exists()) {
            for (int spriteY2 = 0; spriteY2 < vMax; ++spriteY2) {
                for (int spriteX2 = 0; spriteX2 < hMax; ++spriteX2) {
                    currentGID = this.tempGetCurrentGID(spriteX2, spriteY2, startGID, hMax == 2, false);
                    final Image subCollisionImage = collisionImage.getSubImage(spriteX2 * objW * 16, spriteY2 * objH * 16, objW * 16, objH * 16);
                    final SpriteSheet collisionSheet = new SpriteSheet(subCollisionImage, 16, 16);
                    final int tileSheetH2 = collisionSheet.getHorizontalCount();
                    for (int tileSheetV2 = collisionSheet.getVerticalCount(), y2 = 0; y2 < tileSheetV2; ++y2) {
                        for (int x2 = 0; x2 < tileSheetH2; ++x2) {
                            final int tileID2 = y2 * tileSheetH2 + x2;
                            this.generateCollisionData(tileSetBase, x2, y2, currentGID, tileID2, collisionSheet);
                        }
                    }
                    collisionSheet.flushPixelData();
                }
            }
        }
        if (depthCheck.exists()) {
            for (int spriteY2 = 0; spriteY2 < vMax; ++spriteY2) {
                for (int spriteX2 = 0; spriteX2 < hMax; ++spriteX2) {
                    currentGID = this.tempGetCurrentGID(spriteX2, spriteY2, startGID, hMax == 2, false);
                    final Image subDepthImage = depthImage.getSubImage(spriteX2 * objW * 16, spriteY2 * objH * 16, objW * 16, objH * 16);
                    final SpriteSheet depthSheet = new SpriteSheet(subDepthImage, 16, 16);
                    final int tileSheetH2 = depthSheet.getHorizontalCount();
                    for (int tileSheetV2 = depthSheet.getVerticalCount(), y2 = 0; y2 < tileSheetV2; ++y2) {
                        for (int x2 = 0; x2 < tileSheetH2; ++x2) {
                            final int tileID2 = y2 * tileSheetH2 + x2;
                            this.generateDepthData(tileSetBase, x2, y2, currentGID, tileID2, depthSheet);
                        }
                    }
                    depthSheet.flushPixelData();
                }
            }
        }
        if (particleCheck.exists()) {
            for (int spriteY2 = 0; spriteY2 < vMax; ++spriteY2) {
                for (int spriteX2 = 0; spriteX2 < hMax; ++spriteX2) {
                    currentGID = this.tempGetCurrentGID(spriteX2, spriteY2, startGID, hMax == 2, false);
                    final Image subParticleImage = particleImage.getSubImage(spriteX2 * objW * 16, spriteY2 * objH * 16, objW * 16, objH * 16);
                    final SpriteSheet particleSheet = new SpriteSheet(subParticleImage, 16, 16);
                    final int tileSheetH2 = particleSheet.getHorizontalCount();
                    for (int tileSheetV2 = particleSheet.getVerticalCount(), y2 = 0; y2 < tileSheetV2; ++y2) {
                        for (int x2 = 0; x2 < tileSheetH2; ++x2) {
                            final int tileID2 = y2 * tileSheetH2 + x2;
                            this.generateParticleData(tileSetBase, x2, y2, currentGID, tileID2, particleSheet);
                        }
                    }
                    particleSheet.flushPixelData();
                }
            }
        }
        if (shadowCheck.exists()) {
            for (int spriteY2 = 0; spriteY2 < vMax; ++spriteY2) {
                for (int spriteX2 = 0; spriteX2 < hMax; ++spriteX2) {
                    currentGID = this.tempGetCurrentGID(spriteX2, spriteY2, startGID, hMax == 2, false);
                    final Image subShadowImage = shadowImage.getSubImage(spriteX2 * objW * 16, spriteY2 * objH * 16, objW * 16, objH * 16);
                    final SpriteSheet shadowSheet = new SpriteSheet(subShadowImage, 16, 16);
                    final int tileSheetH2 = shadowSheet.getHorizontalCount();
                    for (int tileSheetV2 = shadowSheet.getVerticalCount(), y2 = 0; y2 < tileSheetV2; ++y2) {
                        for (int x2 = 0; x2 < tileSheetH2; ++x2) {
                            final int tileID2 = y2 * tileSheetH2 + x2;
                            this.generateShadowData(tileSetBase, x2, y2, currentGID, tileID2, shadowSheet);
                        }
                    }
                    shadowSheet.flushPixelData();
                }
            }
        }
        if (dataCheck.exists()) {
            ImageLoader.unloadImage("res/maps/data/" + tileSetBase.getFileName() + ".png");
        }
        if (collisionCheck.exists()) {
            ImageLoader.unloadImage("res/maps/collision/" + tileSetBase.getFileName() + ".png");
        }
        if (depthCheck.exists()) {
            ImageLoader.unloadImage("res/maps/depth/" + tileSetBase.getFileName() + ".png");
        }
        if (particleCheck.exists()) {
            ImageLoader.unloadImage("res/maps/particle/" + tileSetBase.getFileName() + ".png");
        }
        if (shadowCheck.exists()) {
            ImageLoader.unloadImage("res/maps/shadow/" + tileSetBase.getFileName() + ".png");
        }
    }

    private int tempGetCurrentGID(final int spriteX, final int spriteY, final int startGID, final boolean buildingHasFill, final boolean isWall) {
        if (buildingHasFill) {
            if (spriteX == 0 && spriteY == 0) {
                return startGID + TileSetObjectPhase.FILL_PHASE_4.getTileOffset();
            }
            if (spriteX == 0 && spriteY == 1) {
                return startGID + TileSetObjectPhase.FILL_PHASE_3.getTileOffset();
            }
            if (spriteX == 0 && spriteY == 2) {
                return startGID + TileSetObjectPhase.FILL_PHASE_2.getTileOffset();
            }
            if (spriteX == 0 && spriteY == 3) {
                return startGID + TileSetObjectPhase.FILL_PHASE_1.getTileOffset();
            }
            if (spriteX == 0 && spriteY == 4) {
                return startGID;
            }
            if (spriteX == 0 && spriteY == 5) {
                return startGID + TileSetObjectPhase.ABANDONED.getTileOffset();
            }
            if (spriteX == 1 && spriteY == 0) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_5.getTileOffset();
            }
            if (spriteX == 1 && spriteY == 1) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_4.getTileOffset();
            }
            if (spriteX == 1 && spriteY == 2) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_3.getTileOffset();
            }
            if (spriteX == 1 && spriteY == 3) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_2.getTileOffset();
            }
            if (spriteX == 1 && spriteY == 4) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_1.getTileOffset();
            }
            if (spriteX == 1 && spriteY == 5) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_OUTLINE.getTileOffset();
            }
        }
        else if (isWall) {
            if (spriteY == 0) {
                return startGID;
            }
            if (spriteY == 1) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_1.getTileOffset();
            }
            if (spriteY == 2) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_OUTLINE.getTileOffset();
            }
            if (spriteY == 3) {
                return startGID + TileSetObjectPhase.ABANDONED.getTileOffset();
            }
        }
        else {
            if (spriteY == 0) {
                return startGID;
            }
            if (spriteY == 1) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_5.getTileOffset();
            }
            if (spriteY == 2) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_4.getTileOffset();
            }
            if (spriteY == 3) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_3.getTileOffset();
            }
            if (spriteY == 4) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_2.getTileOffset();
            }
            if (spriteY == 5) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_PHASE_1.getTileOffset();
            }
            if (spriteY == 6) {
                return startGID + TileSetObjectPhase.CONSTRUCTION_OUTLINE.getTileOffset();
            }
            if (spriteY == 7) {
                return startGID + TileSetObjectPhase.ABANDONED.getTileOffset();
            }
        }
        return startGID;
    }

    private void generateBasicTileData(final TileSetBase tileSetBase, final int x, final int y, final int startGID, final int tileID, final SpriteSheet dataSheet) {
        Color colorCheck = null;
        colorCheck = dataSheet.getColor(x * 16, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockLarge.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16, y * 16 + 1);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockSmall.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16, y * 16 + 2);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockRoad.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16, y * 16 + 3);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockGate.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16, y * 16 + 4);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockLight.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16, y * 16 + 5);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockWater.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16, y * 16 + 6);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockCorruption.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16, y * 16 + 7);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileBlockGhost.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 1, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileLight.put(startGID + tileID, (byte)(colorCheck.getRedByte() / 10));
        }
        colorCheck = dataSheet.getColor(x * 16 + 2, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileInvulnerable.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 3, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileCanClear.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 4, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileCrops.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 5, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            if (colorCheck.getRedByte() > 0) {
                this.tileMovementCost.put(startGID + tileID, colorCheck.getRedByte() * 10);
            }
            else {
                this.tileMovementCost.put(startGID + tileID, colorCheck.getGreenByte() * 10 * -1);
            }
        }
        colorCheck = dataSheet.getColor(x * 16 + 6, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileFrozen.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 7, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileWater.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 8, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileCourierGolemParking.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 9, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileInteractPoint.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 10, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileFunctionalPoint.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 11, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tilePrayPoint.put(startGID + tileID, (byte)1);
        }
        colorCheck = dataSheet.getColor(x * 16 + 12, y * 16);
        if (colorCheck.getAlphaByte() > 0) {
            this.tileVesselPoint.put(startGID + tileID, (byte)1);
        }
    }

    private void generateCollisionData(final TileSetBase tileSetBase, final int x, final int y, final int startGID, final int tileID, final SpriteSheet collisionSheet) {
        final byte[][] tileOut = new byte[16][16];
        final int startX = x * 16;
        final int startY = y * 16;
        boolean foundCollision = false;
        for (int w = 0; w < 16; ++w) {
            for (int h = 0; h < 16; ++h) {
                final Color colorCheck = collisionSheet.getColor(startX + w, startY + h);
                if (colorCheck.getAlpha() != 0) {
                    foundCollision = true;
                    if (colorCheck.equals((Object)this.colorNorth[0])) {
                        tileOut[w][h] = 1;
                    }
                    else if (colorCheck.equals((Object)this.colorNorth[1])) {
                        tileOut[w][h] = 2;
                    }
                    else if (colorCheck.equals((Object)this.colorNorth[2])) {
                        tileOut[w][h] = 3;
                    }
                    else if (colorCheck.equals((Object)this.colorEast[0])) {
                        tileOut[w][h] = 4;
                    }
                    else if (colorCheck.equals((Object)this.colorEast[1])) {
                        tileOut[w][h] = 5;
                    }
                    else if (colorCheck.equals((Object)this.colorEast[2])) {
                        tileOut[w][h] = 6;
                    }
                    else if (colorCheck.equals((Object)this.colorSouth[0])) {
                        tileOut[w][h] = 7;
                    }
                    else if (colorCheck.equals((Object)this.colorSouth[1])) {
                        tileOut[w][h] = 8;
                    }
                    else if (colorCheck.equals((Object)this.colorSouth[2])) {
                        tileOut[w][h] = 9;
                    }
                    else if (colorCheck.equals((Object)this.colorWest[0])) {
                        tileOut[w][h] = 10;
                    }
                    else if (colorCheck.equals((Object)this.colorWest[1])) {
                        tileOut[w][h] = 11;
                    }
                    else if (colorCheck.equals((Object)this.colorWest[2])) {
                        tileOut[w][h] = 12;
                    }
                    else if (colorCheck.equals((Object)this.colorAll[0])) {
                        tileOut[w][h] = 13;
                    }
                    else if (colorCheck.equals((Object)this.colorAll[1])) {
                        tileOut[w][h] = 14;
                    }
                    else if (colorCheck.equals((Object)this.colorAll[2])) {
                        tileOut[w][h] = 15;
                    }
                    else {
                        tileOut[w][h] = 0;
                    }
                }
            }
        }
        if (foundCollision) {
            this.tileCollision.put(startGID + tileID, (byte)1);
            this.collisionTiles.put(startGID + tileID, tileOut);
        }
    }

    private void generateDepthData(final TileSetBase tileSetBase, final int x, final int y, final int startGID, final int tileID, final SpriteSheet depthSheet) {
        final byte[][] tileOut = new byte[16][16];
        final int startX = x * 16;
        final int startY = y * 16;
        boolean foundDepth = false;
        for (int w = 0; w < 16; ++w) {
            for (int h = 0; h < 16; ++h) {
                if (depthSheet.getColor(startX + w, startY + h).getAlpha() != 0) {
                    foundDepth = true;
                    final int colorCheck = depthSheet.getColor(startX + w, startY + h).getBlueByte();
                    tileOut[w][h] = (byte)(colorCheck / 10);
                }
            }
        }
        if (foundDepth) {
            this.tileDepth.put(startGID + tileID, (byte)1);
            this.depthTiles.put(startGID + tileID, tileOut);
        }
    }

    public byte[][] generateWorldMapParticleData() throws SlickException {
        final Image worldMapParticleMap = ImageLoader.getImage("res/GUI/worldMap/worldMapParticles.png");
        final byte[][] worldMapByteArray = new byte[worldMapParticleMap.getWidth()][worldMapParticleMap.getHeight()];
        for (int w = 0; w < worldMapParticleMap.getWidth(); ++w) {
            for (int h = 0; h < worldMapParticleMap.getHeight(); ++h) {
                final Color colorCheck = worldMapParticleMap.getColor(w, h);
                if (colorCheck.getAlpha() != 0) {
                    worldMapByteArray[w][h] = this.getParticleInt(colorCheck);
                }
            }
        }
        return worldMapByteArray;
    }

    private void generateParticleData(final TileSetBase tileSetBase, final int x, final int y, final int startGID, final int tileID, final SpriteSheet particleSheet) {
        final byte[][] tileOut = new byte[16][16];
        final int startX = x * 16;
        final int startY = y * 16;
        boolean foundParticle = false;
        for (int w = 0; w < 16; ++w) {
            for (int h = 0; h < 16; ++h) {
                final Color colorCheck = particleSheet.getColor(startX + w, startY + h);
                if (colorCheck.getAlpha() != 0) {
                    foundParticle = true;
                    tileOut[w][h] = this.getParticleInt(colorCheck);
                }
            }
            if (foundParticle) {
                this.tileParticles.put(startGID + tileID, (byte)1);
                this.particleTiles.put(startGID + tileID, tileOut);
            }
        }
    }

    private byte getParticleInt(final Color colorCheck) {
        if (colorCheck.equals((Object)MapTilesLoader.fireRedVeryLow)) {
            return 1;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireRedLow)) {
            return 2;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireRedMedium)) {
            return 3;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireRedHigh)) {
            return 4;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireYellowVeryLow)) {
            return 5;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireYellowLow)) {
            return 6;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireYellowMedium)) {
            return 7;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireYellowHigh)) {
            return 8;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireGreenVeryLow)) {
            return 9;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireGreenLow)) {
            return 10;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireGreenMedium)) {
            return 11;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireGreenHigh)) {
            return 12;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireBlueVeryLow)) {
            return 13;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireBlueLow)) {
            return 14;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireBlueMedium)) {
            return 15;
        }
        if (colorCheck.equals((Object)MapTilesLoader.fireBlueHigh)) {
            return 16;
        }
        if (colorCheck.equals((Object)MapTilesLoader.smokeLow)) {
            return 17;
        }
        if (colorCheck.equals((Object)MapTilesLoader.smokeMedium)) {
            return 18;
        }
        if (colorCheck.equals((Object)MapTilesLoader.smokeHigh)) {
            return 19;
        }
        if (colorCheck.equals((Object)MapTilesLoader.redSparklesVeryLow)) {
            return 20;
        }
        if (colorCheck.equals((Object)MapTilesLoader.redSparklesLow)) {
            return 21;
        }
        if (colorCheck.equals((Object)MapTilesLoader.redSparklesMedium)) {
            return 22;
        }
        if (colorCheck.equals((Object)MapTilesLoader.redSparklesHigh)) {
            return 23;
        }
        if (colorCheck.equals((Object)MapTilesLoader.greenSparklesVeryLow)) {
            return 24;
        }
        if (colorCheck.equals((Object)MapTilesLoader.greenSparklesLow)) {
            return 25;
        }
        if (colorCheck.equals((Object)MapTilesLoader.greenSparklesMedium)) {
            return 26;
        }
        if (colorCheck.equals((Object)MapTilesLoader.greenSparklesHigh)) {
            return 27;
        }
        if (colorCheck.equals((Object)MapTilesLoader.blueSparklesVeryLow)) {
            return 28;
        }
        if (colorCheck.equals((Object)MapTilesLoader.blueSparklesLow)) {
            return 29;
        }
        if (colorCheck.equals((Object)MapTilesLoader.blueSparklesMedium)) {
            return 30;
        }
        if (colorCheck.equals((Object)MapTilesLoader.blueSparklesHigh)) {
            return 31;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicRedLow)) {
            return 32;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicRedMedium)) {
            return 33;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicRedHigh)) {
            return 34;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicYellowLow)) {
            return 35;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicYellowMedium)) {
            return 36;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicYellowHigh)) {
            return 37;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicGreenLow)) {
            return 38;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicGreenMedium)) {
            return 39;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicGreenHigh)) {
            return 40;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicBlueLow)) {
            return 41;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicBlueMedium)) {
            return 42;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicBlueHigh)) {
            return 43;
        }
        if (colorCheck.equals((Object)MapTilesLoader.purpleSparklesVeryLow)) {
            return 44;
        }
        if (colorCheck.equals((Object)MapTilesLoader.purpleSparklesLow)) {
            return 45;
        }
        if (colorCheck.equals((Object)MapTilesLoader.purpleSparklesMedium)) {
            return 46;
        }
        if (colorCheck.equals((Object)MapTilesLoader.purpleSparklesHigh)) {
            return 47;
        }
        if (colorCheck.equals((Object)MapTilesLoader.firePurpleVeryLow)) {
            return 48;
        }
        if (colorCheck.equals((Object)MapTilesLoader.firePurpleLow)) {
            return 49;
        }
        if (colorCheck.equals((Object)MapTilesLoader.firePurpleMedium)) {
            return 50;
        }
        if (colorCheck.equals((Object)MapTilesLoader.firePurpleHigh)) {
            return 51;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicPurpleLow)) {
            return 52;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicPurpleMedium)) {
            return 53;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicPurpleHigh)) {
            return 54;
        }
        if (colorCheck.equals((Object)MapTilesLoader.yellowSparklesVeryLow)) {
            return 55;
        }
        if (colorCheck.equals((Object)MapTilesLoader.yellowSparklesLow)) {
            return 56;
        }
        if (colorCheck.equals((Object)MapTilesLoader.yellowSparklesMedium)) {
            return 57;
        }
        if (colorCheck.equals((Object)MapTilesLoader.yellowSparklesHigh)) {
            return 58;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireRedLow)) {
            return 59;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireRedMedium)) {
            return 60;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireRedHigh)) {
            return 61;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireYellowLow)) {
            return 62;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireYellowMedium)) {
            return 63;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireYellowHigh)) {
            return 64;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireGreenLow)) {
            return 65;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireGreenMedium)) {
            return 66;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireGreenHigh)) {
            return 67;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireBlueLow)) {
            return 68;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireBlueMedium)) {
            return 69;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFireBlueHigh)) {
            return 70;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFirePurpleLow)) {
            return 71;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFirePurpleMedium)) {
            return 72;
        }
        if (colorCheck.equals((Object)MapTilesLoader.magicFirePurpleHigh)) {
            return 73;
        }
        return 0;
    }

    private void generateShadowData(final TileSetBase tileSetBase, final int x, final int y, final int startGID, final int tileID, final SpriteSheet shadowSheet) {
        final byte[][][] tileOut = new byte[16][16][3];
        final int startX = x * 16;
        final int startY = y * 16;
        boolean foundShadow = false;
        for (int w = 0; w < 16; ++w) {
            for (int h = 0; h < 16; ++h) {
                if (shadowSheet.getColor(startX + w, startY + h).getAlpha() != 0) {
                    foundShadow = true;
                    final int colorCheckBase = shadowSheet.getColor(startX + w, startY + h).getRedByte();
                    final int colorCheckStart = shadowSheet.getColor(startX + w, startY + h).getGreenByte();
                    final int colorCheckStop = shadowSheet.getColor(startX + w, startY + h).getBlueByte();
                    tileOut[w][h][0] = (byte)(colorCheckBase / 5);
                    tileOut[w][h][1] = (byte)(colorCheckStart / 5);
                    tileOut[w][h][2] = (byte)(colorCheckStop / 5);
                }
            }
        }
        if (foundShadow) {
            this.tileShadow.put(startGID + tileID, (byte)1);
            this.shadowTiles.put(startGID + tileID, tileOut);
        }
    }

    public String getTileName(final int tileID) {
        return this.tileName.get(tileID);
    }

    public String getTileDescription(final int tileID) {
        return this.tileDescription.get(tileID);
    }

    public boolean isTileBlockLarge(final int tileID) {
        return this.tileBlockLarge.get(tileID) != null;
    }

    public boolean isTileBlockSmall(final int tileID) {
        return this.tileBlockSmall.get(tileID) != null;
    }

    public boolean isTileBlockRoad(final int tileID) {
        return this.tileBlockRoad.get(tileID) != null;
    }

    public boolean isTileBlockGate(final int tileID) {
        return this.tileBlockGate.get(tileID) != null;
    }

    public boolean isTileBlockLight(final int tileID) {
        return this.tileBlockLight.get(tileID) != null;
    }

    public boolean isTileBlockWater(final int tileID) {
        return this.tileBlockWater.get(tileID) != null;
    }

    public boolean isTileBlockCorruption(final int tileID) {
        return this.tileBlockCorruption.get(tileID) != null;
    }

    public boolean isTileBlockGhost(final int tileID) {
        return this.tileBlockGhost.get(tileID) != null;
    }

    public int getMovementCost(final int tileID) {
        if (this.tileMovementCost.get(tileID) != null) {
            return this.tileMovementCost.get(tileID);
        }
        return 0;
    }

    public boolean isTileDepth(final int tileID) {
        return this.tileDepth.get(tileID) != null;
    }

    public boolean isTileParticles(final int tileID) {
        return this.tileParticles.get(tileID) != null;
    }

    public boolean isTileShadow(final int tileID) {
        return this.tileShadow.get(tileID) != null;
    }

    public byte getTileLight(final int tileID) {
        if (this.tileLight.get(tileID) != null) {
            return this.tileLight.get(tileID);
        }
        return 0;
    }

    public byte getTileLayer(final int tileID) {
        return this.tileLayer.get(tileID);
    }

    public TileSetType getTileType(final int tileID) {
        return this.tileType.get(tileID);
    }

    public boolean isTileCollision(final int tileID) {
        return this.tileCollision.get(tileID) != null;
    }

    public ResourceModule.ResourceType getTileResourceType(final int tileID) {
        return this.tileResourceType.get(tileID);
    }

    public byte getTileResourceAmount(final int tileID) {
        if (this.tileResourceAmount.get(tileID) != null) {
            return this.tileResourceAmount.get(tileID);
        }
        return 0;
    }

    public byte getTileResourceAmountMax(final int tileID) {
        if (this.tileResourceAmountMax.get(tileID) != null) {
            return this.tileResourceAmountMax.get(tileID);
        }
        return 0;
    }

    public ResourceModule.ResourceColorSet getTileResourceColorSet(final int tileID) {
        return this.tileResourceColorSet.get(tileID);
    }

    public boolean isTileCrop(final int tileID) {
        return this.tileCrops.get(tileID) != null;
    }

    public boolean canClear(final int tileID) {
        return this.tileCanClear.get(tileID) != null;
    }

    public boolean isTileInvulnerable(final int tileID) {
        return this.tileInvulnerable.get(tileID) != null;
    }

    public boolean isTileFrozen(final int tileID) {
        return this.tileFrozen.get(tileID) != null;
    }

    public boolean isTileWater(final int tileID) {
        return this.tileWater.get(tileID) != null;
    }

    public boolean isTileCourierGolemParking(final int tileID) {
        return this.tileCourierGolemParking.get(tileID) != null;
    }

    public boolean isTileInteractPoint(final int tileID) {
        return this.tileInteractPoint.get(tileID) != null;
    }

    public boolean isTileFunctionalPoint(final int tileID) {
        return this.tileFunctionalPoint.get(tileID) != null;
    }

    public boolean isTilePrayPoint(final int tileID) {
        return this.tilePrayPoint.get(tileID) != null;
    }

    public boolean isTileVesselPoint(final int tileID) {
        return this.tileVesselPoint.get(tileID) != null;
    }

    public Color getTileMiniMapColor(final int tileID) {
        return this.tileMiniMapColor.get(tileID);
    }

    public TileSet canTileRegrow(final int tileID) {
        return this.tileCanRegrow.get(tileID);
    }

    public int getRegrowRate(final int tileID) {
        return this.tileRegrowRate.get(tileID);
    }

    public boolean getRegrowOnlyMotivated(final int tileID) {
        return this.tileRegrowOnlyMotivated.get(tileID) != null;
    }

    public boolean getRegrowIgnoreWeather(final int tileID) {
        return this.tileRegrowIgnoreWeather.get(tileID) != null;
    }

    public TileSet canTileSpread(final int tileID) {
        return this.tileCanSpread.get(tileID);
    }

    public int getSpreadRate(final int tileID) {
        return this.tileSpreadRate.get(tileID);
    }

    public boolean getSpreadOnlyMotivated(final int tileID) {
        return this.tileSpreadOnlyMotivated.get(tileID) != null;
    }

    public boolean getSpreadIgnoreWeather(final int tileID) {
        return this.tileSpreadIgnoreWeather.get(tileID) != null;
    }

    public int getTileEssenceAmount(final int tileID) {
        if (this.tileEssenceAmount.get(tileID) != null) {
            return this.tileEssenceAmount.get(tileID);
        }
        return 0;
    }

    public byte getTileHeight(final int tileID) {
        return this.tilePropHeight.get(tileID);
    }

    public byte getTileWidth(final int tileID) {
        return this.tilePropWidth.get(tileID);
    }

    public int getTileSetGID(final TileSet tileSet) {
        return tileSet.getTileSetTemplate().getTileGID();
    }

    public int getTileSetGID(final int tileID) {
        if (tileID == 0) {
            return 0;
        }
        if (tileID >= MapTilesLoader.OBJECT_TILE_RANGE && tileID <= MapTilesLoader.OBJECT_TILE_RANGE) {
            return tileID / MapTilesLoader.OBJECT_TILE_SPACING * MapTilesLoader.OBJECT_TILE_SPACING;
        }
        return tileID / MapTilesLoader.TERRAIN_TILE_SUB * MapTilesLoader.TERRAIN_TILE_SUB;
    }

    public int getTileSetGID(final TileSet tileSet, final TileSetObjectPhase tileSetObjectPhase) {
        return tileSet.getTileSetTemplate().getTileGID() + tileSetObjectPhase.getTileOffset();
    }

    public int getTileSetGID(final int tileID, final TileSetObjectPhase tileSetObjectPhase) {
        if (tileID == 0) {
            return 0;
        }
        if (tileID >= MapTilesLoader.OBJECT_TILE_RANGE && tileID <= MapTilesLoader.OBJECT_TILE_RANGE) {
            return tileID / MapTilesLoader.OBJECT_TILE_SPACING * MapTilesLoader.OBJECT_TILE_SPACING + tileSetObjectPhase.getTileOffset();
        }
        return tileID / MapTilesLoader.TERRAIN_TILE_SUB * MapTilesLoader.TERRAIN_TILE_SUB + tileSetObjectPhase.getTileOffset();
    }

    public TileSet getTileSetByTileID(final int tileId) {
        return this.GIDToTileSet.get(this.getTileSetGID(tileId));
    }

    public HashMap<Integer, Image> getTiles() {
        return this.tiles;
    }

    public HashMap<Integer, byte[][]> getCollisionTiles() {
        return this.collisionTiles;
    }

    public HashMap<Integer, byte[][]> getDepthTiles() {
        return this.depthTiles;
    }

    public HashMap<Integer, byte[][]> getParticleTiles() {
        return this.particleTiles;
    }

    public HashMap<Integer, byte[][][]> getShadowTiles() {
        return this.shadowTiles;
    }

    public Image getObjectImage(final int tileGID) throws SlickException {
        this.checkAndloadTileset(tileGID);
        return this.objects.get(tileGID);
    }

    public Image getWallImage(final int tileGID) throws SlickException {
        this.checkAndloadTileset(tileGID);
        return this.tiles.get(tileGID + 250);
    }

    static /* synthetic */ int access$0(final int rangePosition, final int subPosition) {
        return calcObjectTileID(rangePosition, subPosition);
    }

    static /* synthetic */ int access$1(final int rangePosition, final int subPosition) {
        return calcTerrainTileID(rangePosition, subPosition);
    }
}
