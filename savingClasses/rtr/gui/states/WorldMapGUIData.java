package rtr.gui.states;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.newdawn.slick.SlickException;
import rtr.ModuleBase;
import rtr.SettingsParser;
import rtr.mobs.MobBase;
import rtr.mobs.friendly.human.HumanVillagerAdult;
import rtr.resources.ResourceBase;
import rtr.save.RegionalSavedGame;
import rtr.save.SavedGamesHandler;
import rtr.save.YMLDataMap;
import rtr.states.StateBase;
import rtr.system.Game;
import rtr.system.gamemodetemplates.GameModeTemplateBase;

public class WorldMapGUIData {
    private static GameModeTemplateBase.GameMode selectedGameMode;
    private static ArrayList<MobBase> fullVillagerList;
    private static ArrayList<MobBase> fullMigrantList;
    private static ArrayList<MobBase> fullMonsterList;
    private static ArrayList<ResourceBase> fullResourcesList;
    private static HashMap<RegionalSavedGame, ArrayList<MobBase>> villagerList;
    private static HashMap<RegionalSavedGame, ArrayList<MobBase>> migrantList;
    private static HashMap<RegionalSavedGame, ArrayList<MobBase>> monsterList;
    private static HashMap<RegionalSavedGame, Integer> objectCount;
    private static HashMap<RegionalSavedGame, Integer> campType;
    private static HashMap<RegionalSavedGame, ArrayList<ResourceBase>> resourcesList;
    private static HashMap<RegionalSavedGame, Integer> currentDay;
    private static HashMap<RegionalSavedGame, Boolean> mapLost;
    private static HashMap<RegionalSavedGame, Boolean> mapStarted;
    private static boolean newGame;
    private static boolean hasCustomConfig;

    static {
        fullVillagerList = new ArrayList();
        fullMigrantList = new ArrayList();
        fullMonsterList = new ArrayList();
        fullResourcesList = new ArrayList();
        villagerList = new HashMap();
        migrantList = new HashMap();
        monsterList = new HashMap();
        objectCount = new HashMap();
        campType = new HashMap();
        resourcesList = new HashMap();
        currentDay = new HashMap();
        mapLost = new HashMap();
        mapStarted = new HashMap();
    }

    public static ArrayList<MobBase> getFullVillagerList() {
        return fullVillagerList;
    }

    public static ArrayList<MobBase> getFullMigrantList() {
        return fullMigrantList;
    }

    public static ArrayList<MobBase> getFullMonsterList() {
        return fullMonsterList;
    }

    public static ArrayList<ResourceBase> getFullResourcesList() {
        return fullResourcesList;
    }

    public static ArrayList<MobBase> getVillagerList(RegionalSavedGame s) {
        return villagerList.get(s);
    }

    public static ArrayList<MobBase> getMigrantList(RegionalSavedGame s) {
        return migrantList.get(s);
    }

    public static HashMap<RegionalSavedGame, ArrayList<MobBase>> getAllMigrantLists() {
        return migrantList;
    }

    public static ArrayList<MobBase> getMonsterList(RegionalSavedGame s) {
        return monsterList.get(s);
    }

    public static ArrayList<ResourceBase> getResourcesList(RegionalSavedGame s) {
        return resourcesList.get(s);
    }

    public static boolean isNewGame() {
        return newGame;
    }

    public static int getObjectCount(RegionalSavedGame s) {
        return objectCount.get(s);
    }

    public static int getCampType(RegionalSavedGame s) {
        if (campType.containsKey(s)) {
            return campType.get(s);
        }
        return -1;
    }

    public static int getCurrentDay(RegionalSavedGame s) {
        if (currentDay.containsKey(s)) {
            return currentDay.get(s);
        }
        return -1;
    }

    public static boolean isMapLost(RegionalSavedGame s) {
        if (mapLost.containsKey(s)) {
            return mapLost.get(s);
        }
        return false;
    }

    public static boolean isMapStarted(RegionalSavedGame s) {
        if (mapStarted.containsKey(s)) {
            return mapStarted.get(s);
        }
        return false;
    }

    public static void refreshData() throws SlickException {
        selectedGameMode = SettingsParser.getLastGameMode();
        if (selectedGameMode == null) {
            return;
        }
        final HashMap<String, HashMap<StateBase.GameType, HashMap<GameModeTemplateBase.GameMode, RegionalSavedGame>>> saves = SavedGamesHandler.getRegionalSaves().get("WorldMaps");
        File configOut = new File("moddedProfiles/profile" + Game.getActiveProfile() + "/customConfig.gameMode");
        hasCustomConfig = configOut.exists();
        if (hasCustomConfig) {
            GameModeTemplateBase.GameMode.CUSTOM.getTemplate().loadCustomProfileConfig();
        }
        fullVillagerList.clear();
        fullMigrantList.clear();
        fullMonsterList.clear();
        fullResourcesList.clear();
        villagerList.clear();
        migrantList.clear();
        monsterList.clear();
        objectCount.clear();
        campType.clear();
        resourcesList.clear();
        currentDay.clear();
        mapLost.clear();
        mapStarted.clear();
        newGame = true;
        final AtomicInteger count = new AtomicInteger();
        for (final String mapFileName : saves.keySet()) {
            if (SavedGamesHandler.regionalSaveExist("WorldMaps", mapFileName, StateBase.GameType.WORLD_MAP, GameModeTemplateBase.GameMode.MAP_EDITOR)) {
                RegionalSavedGame saveCheck = saves.get(mapFileName).get((Object)StateBase.GameType.WORLD_MAP).get((Object)GameModeTemplateBase.GameMode.MAP_EDITOR);
                villagerList.put(saveCheck, new ArrayList());
                migrantList.put(saveCheck, new ArrayList());
                monsterList.put(saveCheck, new ArrayList());
                resourcesList.put(saveCheck, new ArrayList());
            }
            if (SavedGamesHandler.regionalSaveExist("WorldMaps", mapFileName, StateBase.GameType.WORLD_MAP, selectedGameMode)) {
                Thread thread = new Thread("Save Loading Thread " + mapFileName){

                    @Override
                    public void run() {
                        System.out.println("Save Loading Thread " + mapFileName + " Running.");
                        RegionalSavedGame saveCheck = (RegionalSavedGame)((HashMap)((HashMap)saves.get(mapFileName)).get((Object)StateBase.GameType.WORLD_MAP)).get((Object)selectedGameMode);
                        villagerList.put(saveCheck, new ArrayList());
                        migrantList.put(saveCheck, new ArrayList());
                        monsterList.put(saveCheck, new ArrayList());
                        resourcesList.put(saveCheck, new ArrayList());
                        YMLDataMap resourceModuleDataMap = saveCheck.getAndUnloadModuleSaveData(ModuleBase.ModuleType.RESOURCE);
                        int resourceCount = 0;
                        while (resourceModuleDataMap.containsKey("resource" + resourceCount)) {
                            newGame = false;
                            try {
                                ResourceBase r = new ResourceBase(resourceModuleDataMap.getSubMap("resource" + resourceCount));
                                if (r.isInBuilding() || r.isCarried() || r.isInInventory() || r.isOnGround()) {
                                    fullResourcesList.add(r);
                                    ((ArrayList)resourcesList.get(saveCheck)).add(r);
                                }
                                ++resourceCount;
                            }
                            catch (SlickException e) {
                                e.printStackTrace();
                            }
                        }
                        YMLDataMap mobModuleDataMap = saveCheck.getAndUnloadModuleSaveData(ModuleBase.ModuleType.MOB);
                        int mobCount = 0;
                        while (mobModuleDataMap.containsKey("mob" + mobCount)) {
                            newGame = false;
                            try {
                                HumanVillagerAdult m = new HumanVillagerAdult();
                                m.loadMob(mobModuleDataMap.getSubMap("mob" + mobCount));
                                if (m.getMobGroup() == MobBase.MobGroup.VILLAGER && !m.isDead()) {
                                    fullVillagerList.add(m);
                                    ((ArrayList)villagerList.get(saveCheck)).add(m);
                                }
                                if (m.getMobGroup() == MobBase.MobGroup.MIGRANT && !m.isDead()) {
                                    fullMigrantList.add(m);
                                    ((ArrayList)migrantList.get(saveCheck)).add(m);
                                }
                                if (m.getMobGroup() == MobBase.MobGroup.MONSTER && !m.isDead()) {
                                    fullMonsterList.add(m);
                                    ((ArrayList)monsterList.get(saveCheck)).add(m);
                                }
                                ++mobCount;
                            }
                            catch (SlickException e) {
                                e.printStackTrace();
                            }
                        }
                        YMLDataMap objectModuleDataMap = saveCheck.getAndUnloadModuleSaveData(ModuleBase.ModuleType.OBJECT);
                        objectCount.put(saveCheck, Integer.parseInt(objectModuleDataMap.getOrDefault((Object)"activeBuildings", 0)));
                        int objectCount = 0;
                        while (objectModuleDataMap.containsKey("object" + objectCount)) {
                            YMLDataMap thisObject = objectModuleDataMap.getSubMap("object" + objectCount);
                            if (thisObject.getOrDefault((Object)"currentType", "null").contains("CASTLE")) {
                                campType.put(saveCheck, Integer.parseInt(thisObject.getOrDefault((Object)"currentType", "null").replace("CASTLE_", "")));
                                break;
                            }
                            ++objectCount;
                        }
                        YMLDataMap timeModuleDataMap = saveCheck.getAndUnloadModuleSaveData(ModuleBase.ModuleType.TIME);
                        currentDay.put(saveCheck, Integer.parseInt(timeModuleDataMap.getOrDefault((Object)"day", 0)));
                        YMLDataMap mapModuleDataMap = saveCheck.getAndUnloadModuleSaveData(ModuleBase.ModuleType.MAP);
                        mapLost.put(saveCheck, Boolean.parseBoolean(mapModuleDataMap.getOrDefault((Object)"mapLost", false)));
                        mapStarted.put(saveCheck, Boolean.parseBoolean(mapModuleDataMap.getOrDefault((Object)"mapStarted", false)));
                        count.incrementAndGet();
                        System.out.println("Save Loading Thread " + mapFileName + " Complete.");
                    }
                };
                thread.start();
                continue;
            }
            count.incrementAndGet();
        }
        while (count.get() < saves.keySet().size()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static GameModeTemplateBase.GameMode getSelectedGameMode() {
        return selectedGameMode;
    }

    public static boolean hasCustomConfig() {
        return hasCustomConfig;
    }
}

