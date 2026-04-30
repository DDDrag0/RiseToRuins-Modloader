package rtr.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.newdawn.slick.SlickException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import rtr.console.Console;
import rtr.map.MapTilesLoader;
import rtr.states.StateBase;
import rtr.system.Game;
import rtr.system.gamemodetemplates.GameModeTemplateBase;

public class SavedGamesHandler {
    private static HashMap<String, Properties> mapPackProperties = new HashMap();
    private static HashMap<GameModeTemplateBase.GameMode, WorldSavedGame> worldMapSaves = new HashMap();
    private static HashMap<String, HashMap<String, HashMap<StateBase.GameType, HashMap<GameModeTemplateBase.GameMode, RegionalSavedGame>>>> regionalSaves = new HashMap();

    public static void loadData() throws SlickException {
        regionalSaves.clear();
        mapPackProperties.clear();
        worldMapSaves.clear();
        File[] listOfPacks = new File("maps/").listFiles();
        int i = 0;
        while (i < listOfPacks.length) {
            if (listOfPacks[i].isDirectory()) {
                SavedGamesHandler.loadMapPackProperties(listOfPacks[i].getName());
                SavedGamesHandler.loadRegionalSaves(listOfPacks[i].getName());
            }
            ++i;
        }
        GameModeTemplateBase.GameMode[] gameModeArray = GameModeTemplateBase.GameMode.values();
        int n = gameModeArray.length;
        int n2 = 0;
        while (n2 < n) {
            GameModeTemplateBase.GameMode g = gameModeArray[n2];
            if (g != GameModeTemplateBase.GameMode.MAIN_MENU && g != GameModeTemplateBase.GameMode.MAP_EDITOR && g != GameModeTemplateBase.GameMode.WORLD_MAP) {
                SavedGamesHandler.loadWorldSaves(g);
            }
            ++n2;
        }
    }

    public static void loadMapPackProperties(String mapPackFolderName) throws SlickException {
        try {
            Properties mapProperties = new Properties();
            FileInputStream in = new FileInputStream("maps/" + mapPackFolderName + "/" + mapPackFolderName + ".pack");
            mapProperties.load(in);
            in.close();
            mapPackProperties.put(mapPackFolderName, mapProperties);
            regionalSaves.put(mapPackFolderName, new HashMap());
        }
        catch (IOException ioe) {
            Console.out("Failed to load map pack " + mapPackFolderName + " properties file!", true);
        }
    }

    public static void loadRegionalSaves(String mapPackFolderName) throws SlickException {
        File[] listOfMapZips = new File("maps/" + mapPackFolderName).listFiles();
        ArrayList<File> listOfValidMaps = new ArrayList<File>();
        int i = 0;
        while (i < listOfMapZips.length) {
            if (listOfMapZips[i].isFile() && listOfMapZips[i].getName().contains(".zip")) {
                try {
                    ZipFile zip = new ZipFile(listOfMapZips[i]);
                    ZipEntry mapDataCheck = zip.getEntry("map.yml");
                    ZipEntry objectDataCheck = zip.getEntry("object.yml");
                    if (mapDataCheck == null || objectDataCheck == null) {
                        System.out.println("Found an invalid or corrupted map file in " + listOfMapZips[i].toString());
                        zip.close();
                    } else {
                        InputStream is = zip.getInputStream(mapDataCheck);
                        InputStreamReader in = new InputStreamReader(is, Charset.forName("UTF-8"));
                        YMLDataMap rawDataMap = new Yaml().loadAs(in, YMLDataMap.class);
                        String mapFileName = rawDataMap.getOrDefault((Object)"mapFileName", "null");
                        in.close();
                        zip.close();
                        if (!mapFileName.equals(listOfMapZips[i].getName().replace(".zip", ""))) {
                            System.out.println("Found an invalid or corrupted map file in " + listOfMapZips[i].toString());
                        } else {
                            listOfValidMaps.add(listOfMapZips[i]);
                            String currentMapFileName = listOfMapZips[i].getName().replace(".zip", "");
                            regionalSaves.get(mapPackFolderName).put(currentMapFileName, new HashMap());
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Found an invalid or corrupted map file in " + listOfMapZips[i].toString());
                    e.printStackTrace();
                }
            }
            ++i;
        }
        StateBase.GameType[] gameTypeArray = StateBase.GameType.values();
        int n = gameTypeArray.length;
        int n2 = 0;
        while (n2 < n) {
            StateBase.GameType gT = gameTypeArray[n2];
            int i2 = 0;
            while (i2 < listOfValidMaps.size()) {
                String currentMapFileName = ((File)listOfValidMaps.get(i2)).getName().replace(".zip", "");
                regionalSaves.get(mapPackFolderName).get(currentMapFileName).put(gT, new HashMap());
                GameModeTemplateBase.GameMode[] gameModeArray = GameModeTemplateBase.GameMode.values();
                int n3 = gameModeArray.length;
                int n4 = 0;
                while (n4 < n3) {
                    GameModeTemplateBase.GameMode gM = gameModeArray[n4];
                    if (gM == GameModeTemplateBase.GameMode.MAP_EDITOR) {
                        RegionalSavedGame mapEditorSave = new RegionalSavedGame(gM, gT, mapPackFolderName, currentMapFileName);
                        regionalSaves.get(mapPackFolderName).get(currentMapFileName).get((Object)gT).put(gM, mapEditorSave);
                    } else {
                        String workingBaseFolder = "moddedProfiles/profile" + Game.getActiveProfile() + "/saves/" + gT.getText() + "/" + mapPackFolderName + "/" + currentMapFileName;
                        ArrayList<File> allPossibleSaves = SavedGamesHandler.getSortedListOfSaves(workingBaseFolder, gM);
                        if (allPossibleSaves.size() != 0) {
                            for (File thisMap : allPossibleSaves) {
                                File saveCheck = new File(String.valueOf(workingBaseFolder) + "/" + thisMap.getName());
                                if (!saveCheck.exists()) continue;
                                try {
                                    ZipFile zip = new ZipFile(thisMap);
                                    ZipEntry mapDataCheck = zip.getEntry("map.yml");
                                    ZipEntry objectDataCheck = zip.getEntry("object.yml");
                                    ZipEntry mapArrayDataCheck = zip.getEntry("map.array");
                                    if (mapDataCheck == null || objectDataCheck == null || mapArrayDataCheck == null) {
                                        System.out.println("Found an invalid or corrupted map file in " + thisMap.toString());
                                        zip.close();
                                        continue;
                                    }
                                    zip.close();
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                                RegionalSavedGame thisSave = new RegionalSavedGame(gM, gT, mapPackFolderName, currentMapFileName);
                                regionalSaves.get(mapPackFolderName).get(currentMapFileName).get((Object)gT).put(gM, thisSave);
                                break;
                            }
                        }
                    }
                    ++n4;
                }
                ++i2;
            }
            ++n2;
        }
    }

    public static void loadWorldSaves(GameModeTemplateBase.GameMode gameMode) throws SlickException {
        String baseSaveFolder = "moddedProfiles/profile" + Game.getActiveProfile() + "/saves/worldMap/worldSaveData";
        ArrayList<File> allPossibleSaves = SavedGamesHandler.getSortedListOfSaves(baseSaveFolder, gameMode);
        if (allPossibleSaves.size() == 0) {
            return;
        }
        for (File thisMap : allPossibleSaves) {
            try {
                ZipFile zip = new ZipFile(thisMap);
                ZipEntry mapDataCheck = zip.getEntry("chest.yml");
                ZipEntry objectDataCheck = zip.getEntry("god.yml");
                ZipEntry mapArrayDataCheck = zip.getEntry("perk.yml");
                if (mapDataCheck == null || objectDataCheck == null || mapArrayDataCheck == null) {
                    System.out.println("Found an invalid or corrupted map file in " + thisMap.toString());
                    zip.close();
                    continue;
                }
                zip.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            WorldSavedGame save = new WorldSavedGame(gameMode);
            worldMapSaves.put(gameMode, save);
            break;
        }
    }

    public static ArrayList<File> getSortedListOfSaves(String baseSaveFolder, GameModeTemplateBase.GameMode gameMode) {
        ArrayList<File> savesFound = new ArrayList<File>();
        File folderCheck = new File(baseSaveFolder);
        if (!folderCheck.exists()) {
            return savesFound;
        }
        File[] fileArray = folderCheck.listFiles();
        int n = fileArray.length;
        int n2 = 0;
        while (n2 < n) {
            File f = fileArray[n2];
            if (f.getName().contains(String.valueOf(gameMode.getFolderName()) + "-") && f.getName().contains(".zip") && !f.getName().replaceAll("[^0-9]", "").equals("")) {
                savesFound.add(f);
            }
            ++n2;
        }
        Collections.sort(savesFound, new Comparator<File>(){

            @Override
            public int compare(File s1, File s2) {
                if (s1 == s2) {
                    return 0;
                }
                int numOne = Integer.parseInt(s1.getName().replaceAll("[^0-9]", ""));
                int numTwo = Integer.parseInt(s2.getName().replaceAll("[^0-9]", ""));
                return numTwo - numOne;
            }
        });
        return savesFound;
    }

    public static boolean regionalSaveExist(String mapPack, String mapFileName, StateBase.GameType gameType, GameModeTemplateBase.GameMode gameMode) {
        if (!regionalSaves.containsKey(mapPack)) {
            return false;
        }
        if (!regionalSaves.get(mapPack).containsKey(mapFileName)) {
            return false;
        }
        if (!regionalSaves.get(mapPack).get(mapFileName).containsKey((Object)gameType)) {
            return false;
        }
        return regionalSaves.get(mapPack).get(mapFileName).get((Object)gameType).containsKey((Object)gameMode);
    }

    public static boolean worldSaveExist(GameModeTemplateBase.GameMode gameMode) {
        return worldMapSaves.containsKey((Object)gameMode);
    }

    public static void createNewRegionalSave(RegionalSavedGame s, GameModeTemplateBase.GameMode gM, StateBase.GameType gT) throws SlickException {
        String mapPack = s.getMapPackFolderName();
        String mapFileName = s.getMapFileName();
        if (!regionalSaves.containsKey(mapPack)) {
            regionalSaves.put(mapPack, new HashMap());
        }
        if (!regionalSaves.get(mapPack).containsKey(mapFileName)) {
            regionalSaves.get(mapPack).put(mapFileName, new HashMap());
        }
        if (!regionalSaves.get(mapPack).get(mapFileName).containsKey((Object)gT)) {
            regionalSaves.get(mapPack).get(mapFileName).put(gT, new HashMap());
        }
        RegionalSavedGame save = new RegionalSavedGame(gM, gT, mapPack, mapFileName, true);
        regionalSaves.get(mapPack).get(mapFileName).get((Object)gT).put(gM, save);
    }

    public static void createNewWorldSave(GameModeTemplateBase.GameMode gM) throws SlickException {
        WorldSavedGame save = new WorldSavedGame(gM, true);
        worldMapSaves.put(gM, save);
    }

    public static void createNewMap(String mN, String mA, String mP, MapTilesLoader.TileSet bT) throws SlickException {
        String mapName = mN;
        String mapFileName = mN.replaceAll("[^a-zA-Z0-9]", "");
        String mapAuthor = mA;
        String mapPackName = mP;
        String mapPackFolderName = mP.replaceAll("[^a-zA-Z0-9]", "");
        String baseTerrain = bT.toString();
        File mapPackFolder = new File("maps/" + mapPackFolderName + "/");
        if (!mapPackFolder.exists()) {
            mapPackFolder.mkdirs();
            Properties mapPackProperties = new Properties();
            mapPackProperties.setProperty("mapPackName", mapPackName);
            mapPackProperties.setProperty("mapPackFolderName", mapPackFolderName);
            try {
                FileOutputStream out = new FileOutputStream("maps/" + mapPackFolderName + "/" + mapPackFolderName + ".pack");
                mapPackProperties.store(out, String.valueOf(mapPackName) + " Map Pack Data");
                out.flush();
                out.close();
            }
            catch (IOException ioe) {
                Console.out("Failed to save map pack properties file!", true);
            }
        }
        try {
            int count;
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("maps/" + mapPackFolderName + "/" + mapFileName + ".zip"));
            YMLDataMap dataMap = new YMLDataMap();
            dataMap.put("version", Game.getVersion());
            dataMap.put("mapName", mapName);
            dataMap.put("mapFileName", mapFileName);
            dataMap.put("mapAuthor", mapAuthor);
            dataMap.put("baseTerrain", baseTerrain);
            dataMap.put("mapX", -2048);
            dataMap.put("mapY", -2048);
            HashMap<String, String> connectedRegionsDataMap = new HashMap<String, String>();
            int x = 0;
            while (x < 8) {
                connectedRegionsDataMap.put(Integer.toString(x), "null");
                ++x;
            }
            dataMap.put("connectedRegion", connectedRegionsDataMap);
            dataMap.put("worldMapX", 0);
            dataMap.put("worldMapY", 0);
            DumperOptions options = new DumperOptions();
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            StringWriter writer = new StringWriter();
            yaml.dump(dataMap, writer);
            CharSequenceInputStream writerInputStream = new CharSequenceInputStream((CharSequence)writer.toString(), StandardCharsets.UTF_8);
            zipOut.putNextEntry(new ZipEntry("map.yml"));
            byte[] b = new byte[1024];
            while ((count = writerInputStream.read(b)) > 0) {
                zipOut.write(b, 0, count);
            }
            writerInputStream.close();
            HashMap<String, Object> dataObjectMap = new HashMap<String, Object>();
            dataObjectMap.put("version", Game.getVersion());
            dataObjectMap.put("objectLastID", 0);
            options = new DumperOptions();
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            yaml = new Yaml(options);
            writer = new StringWriter();
            yaml.dump(dataObjectMap, writer);
            writerInputStream = new CharSequenceInputStream((CharSequence)writer.toString(), StandardCharsets.UTF_8);
            zipOut.putNextEntry(new ZipEntry("object.yml"));
            count = 0;
            while ((count = writerInputStream.read(b)) > 0) {
                zipOut.write(b, 0, count);
            }
            writerInputStream.close();
            zipOut.flush();
            zipOut.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        SavedGamesHandler.loadData();
    }

    public static Properties getMapPackProperties(String mapPack) {
        return mapPackProperties.get(mapPack);
    }

    public static HashMap<String, Properties> getAllMapPackProperties() {
        return mapPackProperties;
    }

    public static HashMap<String, HashMap<String, HashMap<StateBase.GameType, HashMap<GameModeTemplateBase.GameMode, RegionalSavedGame>>>> getRegionalSaves() {
        return regionalSaves;
    }

    public static RegionalSavedGame getRegionalSave(String mapPack, String mapFileName, StateBase.GameType gameType, GameModeTemplateBase.GameMode gameMode) {
        if (SavedGamesHandler.regionalSaveExist(mapPack, mapFileName, gameType, gameMode)) {
            return regionalSaves.get(mapPack).get(mapFileName).get((Object)gameType).get((Object)gameMode);
        }
        return null;
    }

    public static WorldSavedGame getWorldSave(GameModeTemplateBase.GameMode gameMode) {
        if (!SavedGamesHandler.worldSaveExist(gameMode)) {
            return null;
        }
        return worldMapSaves.get((Object)gameMode);
    }
}