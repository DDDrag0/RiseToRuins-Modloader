package rtr.save;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.imageout.ImageOut;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import rtr.ImageLoader;
import rtr.ModuleBase;
import rtr.console.Console;
import rtr.font.Text;
import rtr.map.MapModule;
import rtr.map.MiniMapModule;
import rtr.save.SavedGamesHandler;
import rtr.save.YMLDataMap;
import rtr.states.StateBase;
import rtr.system.Game;
import rtr.system.gamemodetemplates.GameModeTemplateBase;
import rtr.system.threadpool.RTRThreadPool;

public class RegionalSavedGame {
    private GameModeTemplateBase.GameMode gameMode;
    private StateBase.GameType gameType;
    private String mapFileName;
    private String mapPackFolderName;
    private String mapEditorFolder;
    private String baseSaveFolder;
    private int highestSaveNumber = 0;
    private HashMap<ModuleBase.ModuleType, Map<String, Object>> preparedModuleSaveData = new HashMap();
    private int[][][] preparedMapData;
    private HashMap<ModuleBase.ModuleType, YMLDataMap> loadedModuleSaveData = new HashMap();
    private int[][][] loadedMapData;
    private Image miniMapImagePreview;
    private boolean newGame;
    private boolean saveRunning;

    public RegionalSavedGame(GameModeTemplateBase.GameMode gM, StateBase.GameType gT, String mPFN, String mFN, boolean nG) throws SlickException {
        this(gM, gT, mPFN, mFN, nG, -1);
    }

    public RegionalSavedGame(GameModeTemplateBase.GameMode gM, StateBase.GameType gT, String mPFN, String mFN) throws SlickException {
        this(gM, gT, mPFN, mFN, false, -1);
    }

    public RegionalSavedGame(GameModeTemplateBase.GameMode gM, StateBase.GameType gT, String mPFN, String mFN, int saveNumber) throws SlickException {
        this(gM, gT, mPFN, mFN, false, saveNumber);
    }

    public RegionalSavedGame(GameModeTemplateBase.GameMode gM, StateBase.GameType gT, String mPFN, String mFN, boolean nG, int saveNumber) throws SlickException {
        this.gameMode = gM;
        this.gameType = gT;
        this.mapPackFolderName = mPFN;
        this.mapFileName = mFN;
        this.newGame = nG;
        this.baseSaveFolder = "moddedProfiles/profile" + Game.getActiveProfile() + "/saves/" + this.gameType.getText() + "/" + this.mapPackFolderName + "/" + this.mapFileName;
        this.mapEditorFolder = "maps/" + this.mapPackFolderName + "/" + this.mapFileName;
        if (saveNumber == -1) {
            ArrayList<File> savesFound = SavedGamesHandler.getSortedListOfSaves(this.baseSaveFolder, this.gameMode);
            if (savesFound.size() > 0) {
                this.highestSaveNumber = Integer.parseInt(savesFound.get(0).getName().replaceAll("[^0-9]", ""));
            }
        } else {
            this.highestSaveNumber = saveNumber;
        }
    }

    public void fullSaveSingleThreaded() throws SlickException {
        long startTotalTime = System.currentTimeMillis();
        if (this.gameMode != GameModeTemplateBase.GameMode.MAP_EDITOR) {
            while (this.saveRunning || RTRThreadPool.getPathRequests().size() > 0) {
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ModuleBase.ModuleType[] moduleTypeArray = ModuleBase.ModuleType.values();
        int n = moduleTypeArray.length;
        int n2 = 0;
        while (n2 < n) {
            ModuleBase.ModuleType mT = moduleTypeArray[n2];
            this.gatherModuleData(mT);
            ++n2;
        }
        this.gatherMapData();
        this.writeDataToDisk(true);
        long endTotalTime = System.currentTimeMillis();
        Text.setVariableText("time", String.valueOf(endTotalTime - startTotalTime) + "ms");
        Console.out(Text.getText("console.panel.save.region.full"), false);
    }

    public void runAutosave() throws SlickException {
        while (this.saveRunning || RTRThreadPool.getPathRequests().size() > 0) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long startTotalTime = System.currentTimeMillis();
        ModuleBase.ModuleType[] moduleTypeArray = ModuleBase.ModuleType.values();
        int n = moduleTypeArray.length;
        int n2 = 0;
        while (n2 < n) {
            ModuleBase.ModuleType mT = moduleTypeArray[n2];
            this.gatherModuleData(mT);
            ++n2;
        }
        this.gatherMapData();
        Runnable r = new Runnable(){

            @Override
            public void run() {
                RegionalSavedGame.this.writeDataToDisk(false);
            }
        };
        new Thread(r).start();
        long endTotalTime = System.currentTimeMillis();
        Text.setVariableText("time", String.valueOf(endTotalTime - startTotalTime) + "ms");
        Console.out(Text.getText("console.panel.save.region.auto.dataGather"), false);
    }

    private void writeDataToDisk(boolean writeMinimap) {
        long startTotalTimeThread = System.currentTimeMillis();
        this.saveRunning = true;
        try {
            ZipOutputStream zipOut = null;
            if (this.gameMode != GameModeTemplateBase.GameMode.MAP_EDITOR) {
                File folderCheck = new File(this.baseSaveFolder);
                if (!folderCheck.exists()) {
                    folderCheck.mkdirs();
                }
                zipOut = new ZipOutputStream(new FileOutputStream(String.valueOf(this.baseSaveFolder) + "/" + this.gameMode.getFolderName() + "-NEW.zip"));
            } else {
                zipOut = new ZipOutputStream(new FileOutputStream(String.valueOf(this.mapEditorFolder) + "-NEW.zip"));
            }
            for (ModuleBase.ModuleType mT : this.preparedModuleSaveData.keySet()) {
                int count;
                Map<String, Object> dataMap = this.preparedModuleSaveData.get((Object)mT);
                if (dataMap == null) continue;
                String parsedFileName = mT.getSystemName().replaceAll(" ", "");
                char[] c = parsedFileName.toCharArray();
                c[0] = Character.toLowerCase(c[0]);
                parsedFileName = new String(c);
                DumperOptions options = new DumperOptions();
                options.setPrettyFlow(true);
                options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                Yaml yaml = new Yaml(options);
                StringWriter writer = new StringWriter();
                yaml.dump(dataMap, writer);
                CharSequenceInputStream writerInputStream = new CharSequenceInputStream((CharSequence)writer.toString(), StandardCharsets.UTF_8);
                zipOut.putNextEntry(new ZipEntry(String.valueOf(parsedFileName) + ".yml"));
                byte[] b = new byte[1024];
                while ((count = writerInputStream.read(b)) > 0) {
                    zipOut.write(b, 0, count);
                }
                writerInputStream.close();
            }
            this.preparedModuleSaveData.clear();
            if (writeMinimap) {
                long startTime = System.currentTimeMillis();
                MiniMapModule miniMap = (MiniMapModule)StateBase.getModule(ModuleBase.ModuleType.MINI_MAP);
                zipOut.putNextEntry(new ZipEntry("minimap.bmp"));
                ImageOut.write(miniMap.getMiniMapFull(), "BMP", zipOut);
                long endTime = System.currentTimeMillis();
                Console.out("Saved Map Preview, took " + (endTime - startTime) + "ms", true);
            }
            long startTime = System.currentTimeMillis();
            try {
                zipOut.putNextEntry(new ZipEntry("map.array"));
                ObjectOutputStream mapDataOut = new ObjectOutputStream(zipOut);
                mapDataOut.writeObject(this.preparedMapData);
                mapDataOut.flush();
                mapDataOut.close();
            }
            catch (IOException npe) {
                Console.out("There was an error saving the map.", true);
            }
            long endTime = System.currentTimeMillis();
            Console.out("Saved Map Data, took " + (endTime - startTime) + "ms", true);
            zipOut.flush();
            zipOut.close();
            if (this.gameMode == GameModeTemplateBase.GameMode.MAP_EDITOR) {
                this.runSaveCleanupMapEditor();
            } else {
                this.runSaveCleanupPlay();
            }
        }
        catch (IOException | SlickException e) {
            this.saveRunning = false;
            e.printStackTrace();
        }
        long endTotalTimeThread = System.currentTimeMillis();
        Text.setVariableText("time", String.valueOf(endTotalTimeThread - startTotalTimeThread) + "ms");
        Text.setVariableText("saveNumber", this.highestSaveNumber);
        Console.out(Text.getText("console.panel.save.region.auto.dataWritten"), false);
        this.saveRunning = false;
    }

    private void gatherModuleData(ModuleBase.ModuleType mT) throws SlickException {
        long startTime = System.currentTimeMillis();
        ModuleBase m = StateBase.getModule(mT);
        YMLDataMap dataMap = null;
        dataMap = this.gameMode == GameModeTemplateBase.GameMode.MAP_EDITOR ? m.getEditorSaveData() : m.getRegionalSaveData();
        if (dataMap != null) {
            this.preparedModuleSaveData.put(mT, dataMap);
        }
        long endTime = System.currentTimeMillis();
        if (dataMap != null) {
            Console.out("Saved " + mT.getSystemName() + " module regional data, took " + (endTime - startTime) + "ms", true);
        } else {
            Console.out("No regional data inside " + mT.getSystemName() + " module to save, took " + (endTime - startTime) + "ms", true);
        }
    }

    private void gatherMapData() throws SlickException {
        MapModule map = (MapModule)StateBase.getModule(ModuleBase.ModuleType.MAP);
        this.preparedMapData = new int[256][256][12];
        int[][][] originalMapData = map.getMap();
        int x = 0;
        while (x < this.preparedMapData.length) {
            int y = 0;
            while (y < this.preparedMapData[x].length) {
                int l = 0;
                while (l < this.preparedMapData[x][y].length) {
                    this.preparedMapData[x][y][l] = originalMapData[x][y][l];
                    ++l;
                }
                ++y;
            }
            ++x;
        }
    }

    protected void runSaveCleanupPlay() {
        ArrayList<File> savesFound;
        long startTime = System.currentTimeMillis();
        File newFile = new File(String.valueOf(this.baseSaveFolder) + "/" + this.gameMode.getFolderName() + "-NEW.zip");
        if (newFile.exists() && !newFile.renameTo(new File(String.valueOf(this.baseSaveFolder) + "/" + this.gameMode.getFolderName() + "-" + (this.highestSaveNumber + 1) + ".zip"))) {
            System.out.println("Failed to rename save. Running garbage collection and retrying.");
            System.gc();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!newFile.renameTo(new File(String.valueOf(this.baseSaveFolder) + "/" + this.gameMode.getFolderName() + "-" + (this.highestSaveNumber + 1) + ".zip"))) {
                ++this.highestSaveNumber;
                System.out.println("Failed to rename save again. Incrementing save slot number to " + this.highestSaveNumber + " and retrying.");
                System.gc();
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if ((savesFound = SavedGamesHandler.getSortedListOfSaves(this.baseSaveFolder, this.gameMode)).size() == 0) {
            return;
        }
        this.highestSaveNumber = Integer.parseInt(savesFound.get(0).getName().replaceAll("[^0-9]", ""));
        if (savesFound.size() > 8) {
            int x = 8;
            while (x < savesFound.size()) {
                savesFound.get(x).delete();
                ++x;
            }
        }
        long endTime = System.currentTimeMillis();
        Console.out("Save Clean Up, took " + (endTime - startTime) + "ms", true);
    }

    protected void runSaveCleanupMapEditor() {
        File deleteFile;
        File newFile;
        long startTime = System.currentTimeMillis();
        File oldFile = new File(String.valueOf(this.mapEditorFolder) + ".zip");
        if (oldFile.exists()) {
            while (!oldFile.renameTo(new File(String.valueOf(this.mapEditorFolder) + "-BAK.zip"))) {
                System.out.println("Failed to create backup. Running garbage collection and retrying.");
                System.gc();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if ((newFile = new File(String.valueOf(this.mapEditorFolder) + "-NEW.zip")).exists() && newFile.renameTo(new File(String.valueOf(this.mapEditorFolder) + ".zip")) && (deleteFile = new File(String.valueOf(this.mapEditorFolder) + "-BAK.zip")).exists()) {
            deleteFile.delete();
        }
        long endTime = System.currentTimeMillis();
        Console.out("Save Clean Up, took " + (endTime - startTime) + "ms", true);
    }

    public void deleteSaveData() throws SlickException {
        ArrayList<File> savesFound = SavedGamesHandler.getSortedListOfSaves(this.baseSaveFolder, this.gameMode);
        int x = 0;
        while (x < savesFound.size()) {
            savesFound.get(x).delete();
            ++x;
        }
    }

    public GameModeTemplateBase.GameMode getGameMode() {
        return this.gameMode;
    }

    public StateBase.GameType getGameType() {
        return this.gameType;
    }

    public String getMapFileName() {
        return this.mapFileName;
    }

    public String getMapPackFolderName() {
        return this.mapPackFolderName;
    }

    public String getBaseSaveFolder() {
        return this.baseSaveFolder;
    }

    public YMLDataMap getModuleSaveData(ModuleBase.ModuleType mT) {
        String rawSaveData;
        if (!this.loadedModuleSaveData.containsKey((Object)mT) && (rawSaveData = this.getRawModuleSavaData(mT)) != null) {
            YMLDataMap rawDataMap = new Yaml().loadAs(rawSaveData, YMLDataMap.class);
            rawDataMap.setName(mT.getSystemName());
            this.loadedModuleSaveData.put(mT, rawDataMap);
        }
        return this.loadedModuleSaveData.get((Object)mT);
    }

    public YMLDataMap getAndUnloadModuleSaveData(ModuleBase.ModuleType mT) {
        String rawSaveData;
        if (!this.loadedModuleSaveData.containsKey((Object)mT) && (rawSaveData = this.getRawModuleSavaData(mT)) != null) {
            YMLDataMap rawDataMap = new Yaml().loadAs(rawSaveData, YMLDataMap.class);
            rawDataMap.setName(mT.getSystemName());
            this.loadedModuleSaveData.put(mT, rawDataMap);
        }
        YMLDataMap mapOut = this.loadedModuleSaveData.get((Object)mT);
        this.loadedModuleSaveData.remove((Object)mT);
        return mapOut;
    }

    private String getRawModuleSavaData(final ModuleBase.ModuleType mT) {
        String rawDataOut = null;
        try {
            File zipFile = null;
            if (this.newGame || this.gameMode == GameModeTemplateBase.GameMode.MAP_EDITOR) {
                zipFile = new File(String.valueOf(this.mapEditorFolder) + ".zip");
            }
            else {
                zipFile = new File(String.valueOf(this.baseSaveFolder) + "/" + this.gameMode.getFolderName() + "-" + this.highestSaveNumber + ".zip");
            }
            final ZipFile zip = new ZipFile(zipFile);
            final ZipEntry zipEntry = zip.getEntry(String.valueOf(mT.getSystemName()) + ".yml");
            if (zipEntry == null) {
                zip.close();
                return null;
            }
            final InputStream is = zip.getInputStream(zipEntry);
            final InputStreamReader in = new InputStreamReader(is, Charset.forName("UTF-8"));
            final char[] buffer = new char[4096];
            final StringBuilder sb = new StringBuilder();
            int len;
            while ((len = in.read(buffer)) > 0) {
                sb.append(buffer, 0, len);
            }
            rawDataOut = sb.toString();
            is.close();
            zip.close();
        }
        catch (final Exception e) {
            e.printStackTrace();
            if (this.newGame || this.gameMode == GameModeTemplateBase.GameMode.MAP_EDITOR) {
                Console.out("Failed to create " + mT.getSystemName() + " data for: " + this.mapEditorFolder + "!", true);
            }
            else {
                Console.out("Failed to create " + mT.getSystemName() + " data for: " + this.baseSaveFolder + "!", true);
            }
        }
        return rawDataOut;
    }

    public int[][][] getMapData() {
        if (this.loadedMapData == null) {
            try {
                File zipFile = null;
                zipFile = this.newGame || this.gameMode == GameModeTemplateBase.GameMode.MAP_EDITOR ? new File(String.valueOf(this.mapEditorFolder) + ".zip") : new File(String.valueOf(this.baseSaveFolder) + "/" + this.gameMode.getFolderName() + "-" + this.highestSaveNumber + ".zip");
                ZipFile zip = new ZipFile(zipFile);
                ZipEntry zipEntry = zip.getEntry("map.array");
                InputStream is = zip.getInputStream(zipEntry);
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(is));
                try {
                    this.loadedMapData = (int[][][])in.readObject();
                    in.close();
                }
                catch (ClassNotFoundException e) {
                    Console.out("There was an error loading the map array for: " + this.mapFileName + "!", true);
                    in.close();
                }
                zip.close();
            }
            catch (Exception e) {
                Console.out("There was an error loading the map array for: " + this.mapFileName + "!", true);
            }
        }
        return this.loadedMapData;
    }

    public Image getMiniMapImagePreview() throws SlickException {
        if (this.miniMapImagePreview == null) {
            File zipFile = null;
            zipFile = this.newGame || this.gameMode == GameModeTemplateBase.GameMode.MAP_EDITOR ? new File(String.valueOf(this.mapEditorFolder) + ".zip") : new File(String.valueOf(this.baseSaveFolder) + "/" + this.gameMode.getFolderName() + "-" + this.highestSaveNumber + ".zip");
            try {
                ZipFile zip = new ZipFile(zipFile);
                ZipEntry zipEntry = zip.getEntry("minimap.bmp");
                if (zipEntry == null) {
                    zipFile = new File(String.valueOf(this.mapEditorFolder) + ".zip");
                    zip.close();
                    zip = new ZipFile(zipFile);
                    zipEntry = zip.getEntry("minimap.bmp");
                    if (zipEntry == null) {
                        this.miniMapImagePreview = ImageLoader.getImage("res/ungeneratedMap.bmp");
                        zip.close();
                        return this.miniMapImagePreview;
                    }
                }
                InputStream minimapInputStream = zip.getInputStream(zipEntry);
                this.miniMapImagePreview = new Image(minimapInputStream, minimapInputStream.toString(), false);
                zip.close();
                minimapInputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.miniMapImagePreview;
    }

    public boolean isNewGame() {
        return this.newGame;
    }
}