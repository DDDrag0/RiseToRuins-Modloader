package rtr.objects.objectflags.village;

import rtr.PerkModule;
import rtr.font.Text;
import rtr.map.MapTilesLoader;
import rtr.resources.ResourceModule;
import rtr.objects.objectflags.ObjectFlags;
import rtr.mobs.MobBase;
import rtr.mobs.jobs.MobJobBase;
import rtr.objects.ObjectBase;
import rtr.objects.gates.GateBase;
import rtr.objects.towerhead.TowerBase;
import rtr.utilities.OrderedPair;
import rtr.utilities.Utilities;

public class CrystalMotivatorFlags extends ObjectFlags {
    public CrystalMotivatorFlags(MapTilesLoader.TileSet currentType, ObjectBase.ObjectSubType subType) {
        super(currentType, subType);
    }

    @Override
    protected void configBasics() {
        //These are the standard specifications for the first building and the requirements for the other buildings; even when they can be upgraded, they remain the same
        this.baseType = MapTilesLoader.TileSet.CRYSTAL_MOTIVATOR;
        this.width = 2;
        this.height = 2;
        this.baseName = Text.getText("Nome Struttura");
        this.name = Text.getText("Nome Struttura");
        this.description = Text.getText("Descrizione Struttura");
        //resourceBaseValues: WOOD, BOARD, ROCK, CRYSTAL, CUT_STONE, CRYLITHIUM, IRON_INGOT, TRASHY_CUBE, GOLD_INGOT, GOD_DUST (for godshit)
        this.resourceBaseValues.put(ResourceModule.ResourceType.ROCK, 4);
        //requiredSupportBuildings & recommendedSupportBuildings: STONE_CUTTERY, LUMBER_MILL, CLUCKER_COOP, ANIMAL_PEN, TOOLSMITHY, MINING_FACILITY, CLINIC, ESSENCE_COLLECTOR, BOWYER, ROCK_TUMBLER, ARMORSMITHY, FARM, BOTTLER, (RAIN_CATCHER, WATER_PURIFIER, WELL), (RANGER_LODGE, OUTPOST), KITCHEN, FORGE, LUMBER_SHACK, TRASH_CAN, LANDFILL, CRYSTAL_HARVESTRY
        this.recommendedSupportBuildings.add(new MapTilesLoader.TileSet[]{MapTilesLoader.TileSet.RAIN_CATCHER, MapTilesLoader.TileSet.WATER_PURIFIER, MapTilesLoader.TileSet.WELL});
        this.requiredSupportBuildings.add(new MapTilesLoader.TileSet[]{MapTilesLoader.TileSet.RANGER_LODGE, MapTilesLoader.TileSet.OUTPOST});
        this.allowDismantle = true;
        this.allowPause = true;

        // Only for Wall/FirePit structures, LootBoxes
        this.canNotBeAttacked = true;

        //Only for combobulators
        if (Utilities.randomInt(100) == 0) {
            this.combobulatorEasterEggMode = true;
            this.invulnerable = true;
        }

        //LootBoxes
        this.canNotSelect = true;

    }

    @Override
    protected void configConstruction() {
        this.mobGroup = MobBase.MobGroup.VILLAGER;
    }

    @Override
    protected void configBuilt() { //quando è costruito
        //Required Standards
        this.mobGroup = MobBase.MobGroup.VILLAGER; // a chi appartiene la struttura: VILLAGER, NONE (CullisGate e RadiancePool), no idea cosa cambi
        this.baseRange = 8; //quanto range la struttura genera
        this.baseDesirability = 1; //quanto i villageri vogliono avvicinarsi a quella struttura

        this.hasFillArt = true; // Check the description down to use this:
        // By default you can omit this parameter and it will be set to false so the PNG image of the structure's rendering consists
        // of a single column containing 8 sections, listed from bottom to top as follows:
        // ABANDONED, CONSTRUCTION_OUTLINE, CONSTRUCTION_PHASE_1, CONSTRUCTION_PHASE_2, CONSTRUCTION_PHASE_3, CONSTRUCTION_PHASE_4, CONSTRUCTION_PHASE_5, BASE,
        // if this parameter is set to true, it uses two columns instead, where
        // the left column contains, again from bottom to top:
        // ABANDONED, BASE, FILL_PHASE_1 (25%), FILL_PHASE_2 (50%), FILL_PHASE_3 (75), FILL_PHASE_4 (full);
        // and the right column contains, again from bottom to top:
        // CONSTRUCTION_OUTLINE, CONSTRUCTION_PHASE_1, CONSTRUCTION_PHASE_2, CONSTRUCTION_PHASE_3, CONSTRUCTION_PHASE_4, CONSTRUCTION_PHASE_5.

        //JOBS
        switch (this.currentType) {
            case ANCILLARY:
            case CASTLE_1: {
                ResourceModule.ResourceType t;
                this.workerJobType = MobJobBase.MobJobType.BUILDER; //ANCILLARY: MobJobBase.MobJobType.ORGANIZER
                ResourceModule.ResourceType[] resourceTypeArray = ResourceModule.ResourceType.values();
                int n = resourceTypeArray.length;
                int n2 = 0;
                while (n2 < n) {
                    t = resourceTypeArray[n2];
                    if (!t.getTemplate().dontAddMisc() && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.TRASH && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.FOOD_AND_WATER && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.BYPRODUCT && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.RECOVERY && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.TOWER_AMMO) {
                        this.buildingStorePriority.put(t, ObjectBase.ResourceStorePriority.LOW);
                        this.canBuildingStore.add(t);
                    }
                    ++n2;
                }
                //Switch
                this.workersProvided = 26;  //ANCILLARY: 6
                this.buildingsProvided = 86;    //ANCILLARY: 14
                this.ancillariesProvided = 15;  //not in ANCILLARY
                this.baseRange = 60;    //ANCILLARY: 28
                resourceTypeArray = ResourceModule.ResourceType.values();
                n = resourceTypeArray.length;
                n2 = 0;
                while (n2 < n) {
                    t = resourceTypeArray[n2];
                    if (!t.getTemplate().dontAddMisc() && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.TRASH && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.FOOD_AND_WATER && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.BYPRODUCT && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.RECOVERY && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.TOWER_AMMO) {
                        this.resourceStorageMax.put(t, 80); //ANCILLARY: 16
                    }
                    ++n2;
                }
                this.globalWorkSpeedReduction = 15.0;   //ANCILLARY: 2.0
                this.buildingSpeedReduction = 30.0; //ANCILLARY: 2.0
            }
            case ANIMAL_PEN: {
                this.canCatchRain = true;
                this.rainCatchRate = 300;
                this.workerJobType = MobJobBase.MobJobType.FARMER;
                this.occupantsTypes.clear();
                this.occupantsTypes.add(MobBase.MobType.BEEFALO); // ROUS, ENTLER
                this.gateCoordinates = OrderedPair.getOrderedPair(3, 5); //this.width = 8; this.height = 9;
                this.gateType = GateBase.GateType.WOOD_GATE_NS;
                this.buildingStorePriority.put(ResourceModule.ResourceType.RAW_VEGETABLE, ObjectBase.ResourceStorePriority.MEDIUM); //WATER_BUCKET
                this.canBuildingStore.add(ResourceModule.ResourceType.RAW_VEGETABLE); //WATER_BUCKET
                // Switch
                this.workersProvided = 4;
                this.occupantsMax = 10;
                this.resourceStorageMax.put(ResourceModule.ResourceType.RAW_VEGETABLE, 32); //WATER_BUCKET
            }
            case ARMORSMITHY: {
                this.workerJobType = MobJobBase.MobJobType.ARMORSMITH;
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_CHEST_ARMOR, ObjectBase.ResourceStorePriority.VERY_LOW); //IRON_HELMET, IRON_SHIELD, LEATHER_CHEST_ARMOR, LEATHER_HELMET, LEATHER_SHIELD, IRON_INGOT, RAW_HIDE
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_CHEST_ARMOR); //IRON_HELMET, IRON_SHIELD, LEATHER_CHEST_ARMOR, LEATHER_HELMET, LEATHER_SHIELD, IRON_INGOT, RAW_HIDE
                // Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_CHEST_ARMOR, 16); //IRON_HELMET, IRON_SHIELD, LEATHER_CHEST_ARMOR, LEATHER_HELMET, LEATHER_SHIELD
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_INGOT, 40); //RAW_HIDE
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.IRON_CHEST_ARMOR, 20.0); //IRON_HELMET, IRON_SHIELD, LEATHER_CHEST_ARMOR, LEATHER_HELMET, LEATHER_SHIELD
            }
            case BOWYER: {
                this.workerJobType = MobJobBase.MobJobType.FLETCHER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOOD, ObjectBase.ResourceStorePriority.VERY_HIGH); //FEATHER, SILK, BALLISTAE_BOLTS, BOW, QUIVER
                this.canBuildingStore.add(ResourceModule.ResourceType.WOOD); //FEATHER, SILK, BALLISTAE_BOLTS, BOW, QUIVER
                // Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOOD, 40); //FEATHER, SILK, BALLISTAE_BOLTS, 16, BOW, 16, QUIVER, 16
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, 20.0); // BOW, QUIVER
            }
            case BURNER: { //uses essenceReturnCoordinates, essenceReturnCoordinatesPixelOffset, energyMax = 50
                this.workerJobType = MobJobBase.MobJobType.TRASHER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.CRYSTALLY_TRASH, ObjectBase.ResourceStorePriority.HIGH); //ORGANICY_TRASH, ROCKY_TRASH, SUSPICICIOUSY_TRASH, TRASHY_CUBE, TRASHY_TRASH, WOODY_TRASH
                this.canBuildingStore.add(ResourceModule.ResourceType.CRYSTALLY_TRASH); //ORGANICY_TRASH, ROCKY_TRASH, SUSPICICIOUSY_TRASH, TRASHY_CUBE, TRASHY_TRASH, WOODY_TRASH
                // Switch
                this.workersProvided = 4;
                this.isBurner = true;
                this.burnerRate = 500;
                this.burnerEnergyDissipationRate = 100;
                this.resourceStorageMax.put(ResourceModule.ResourceType.CRYSTALLY_TRASH, 20);//ORGANICY_TRASH, ROCKY_TRASH, SUSPICICIOUSY_TRASH, TRASHY_CUBE, TRASHY_TRASH, WOODY_TRASH
            }
            case CLINIC:{
                this.workerJobType = MobJobBase.MobJobType.MEDIC;
                this.buildingStorePriority.put(ResourceModule.ResourceType.SILK, ObjectBase.ResourceStorePriority.VERY_HIGH); //WOOD, BANDAGE, MEDKIT
                this.canBuildingStore.add(ResourceModule.ResourceType.SILK); //WOOD, BANDAGE, MEDKIT
                // Switch
                this.workersProvided = 6;
                this.resourceStorageMax.put(ResourceModule.ResourceType.SILK, 40); //WOOD, BANDAGE, 16, MEDKIT, 8
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.BANDAGE, 20.0); // MEDKIT
            }
            case CLUCKER_COOP:{
                this.canCatchRain = true;
                this.rainCatchRate = 300;
                this.workerJobType = MobJobBase.MobJobType.FARMER;
                this.occupantsTypes.clear();
                this.occupantsTypes.add(MobBase.MobType.CLUCKER);
                this.gateCoordinates = OrderedPair.getOrderedPair(4, 3); // this.width = 6; this.height = 7;
                this.gateType = GateBase.GateType.WOOD_GATE_WE;
                this.buildingStorePriority.put(ResourceModule.ResourceType.RAW_VEGETABLE, ObjectBase.ResourceStorePriority.MEDIUM); //WATER_BUCKET
                this.canBuildingStore.add(ResourceModule.ResourceType.RAW_VEGETABLE); //WATER_BUCKET
                // Switch
                this.workersProvided = 4;
                this.occupantsMax = 6;
                this.resourceStorageMax.put(ResourceModule.ResourceType.RAW_VEGETABLE, 32); //WATER_BUCKET
            }
            case CRYSTAL_HARVESTRY:{
                this.workerJobType = MobJobBase.MobJobType.CRYSTAL_HARVESTER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.CRYSTAL, ObjectBase.ResourceStorePriority.MEDIUM);
                this.canBuildingStore.add(ResourceModule.ResourceType.CRYSTAL);
                // Switch
                this.workersProvided = 16;
                this.resourceStorageMax.put(ResourceModule.ResourceType.CRYSTAL, 128);
                this.harvestingSpeedReduction.put(ResourceModule.ResourceType.CRYSTAL, 20.0);
            }
            case CRYSTILLERY:{
                this.workerJobType = MobJobBase.MobJobType.CRYSILLER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.CRYSTAL, ObjectBase.ResourceStorePriority.VERY_HIGH); //CRYLITHIUM, VERY_LOW
                this.canBuildingStore.add(ResourceModule.ResourceType.CRYSTAL); //CRYLITHIUM
                // Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.CRYSTAL, 38); //CRYLITHIUM
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.CRYLITHIUM, 20.0);
            }
            case ESSENCE_ALTAR:{
                this.workerJobType = MobJobBase.MobJobType.OCCULTIST;
                this.canDeliverEssenceToCollectors = true;
                this.canReleaseEssenceToGod = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(2, 2);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(8, 8);
                //Switch
                this.workersProvided = 4;
                this.prayingSpeedReduction = 4.0;
                this.energyMax = 400;
            }
            case FARM:{
                this.canCatchRain = true;
                this.rainCatchRate = 300;
                this.workerJobType = MobJobBase.MobJobType.FARMER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.RAW_VEGETABLE, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.WATER_BUCKET, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.DIRTY_WATER_BUCKET, ObjectBase.ResourceStorePriority.HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.RAW_VEGETABLE);
                this.canBuildingStore.add(ResourceModule.ResourceType.WATER_BUCKET);
                this.canBuildingStore.add(ResourceModule.ResourceType.DIRTY_WATER_BUCKET);
                //Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.RAW_VEGETABLE, 40);
                this.resourceStorageMax.put(ResourceModule.ResourceType.WATER_BUCKET, 32);
                this.resourceStorageMax.put(ResourceModule.ResourceType.DIRTY_WATER_BUCKET, 32);
                this.harvestingSpeedReduction.put(ResourceModule.ResourceType.RAW_VEGETABLE, 20.0);
                this.farmingSpeedReduction = 10.0;
                this.tendAnimalsSpeedReduction = 10.0;
            }
            case FORGE:{
                this.workerJobType = MobJobBase.MobJobType.SMELTER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_INGOT, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_ORE, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.GOLD_INGOT, ObjectBase.ResourceStorePriority.HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.GOLD_ORE, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.GOLD_COIN_SACK, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_INGOT);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_ORE);
                this.canBuildingStore.add(ResourceModule.ResourceType.GOLD_INGOT);
                this.canBuildingStore.add(ResourceModule.ResourceType.GOLD_ORE);
                this.canBuildingStore.add(ResourceModule.ResourceType.GOLD_COIN_SACK);
                //Switch
                this.workersProvided = 6;
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_INGOT, 32);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_ORE, 48);
                this.resourceStorageMax.put(ResourceModule.ResourceType.GOLD_INGOT, 32);
                this.resourceStorageMax.put(ResourceModule.ResourceType.GOLD_ORE, 48);
                this.resourceStorageMax.put(ResourceModule.ResourceType.GOLD_COIN_SACK, 32);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.IRON_INGOT, 20.0);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.GOLD_INGOT, 20.0);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.GOLD_COIN_SACK, 20.0);
            }
            case KITCHEN:{
                this.workerJobType = MobJobBase.MobJobType.COOK;
                this.buildingStorePriority.put(ResourceModule.ResourceType.RAW_VEGETABLE, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.RATION, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.RAW_MEAT, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.COOKED_MEAT, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.EGG, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BOILED_EGG, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.RAW_VEGETABLE);
                this.canBuildingStore.add(ResourceModule.ResourceType.RATION);
                this.canBuildingStore.add(ResourceModule.ResourceType.RAW_MEAT);
                this.canBuildingStore.add(ResourceModule.ResourceType.COOKED_MEAT);
                this.canBuildingStore.add(ResourceModule.ResourceType.EGG);
                this.canBuildingStore.add(ResourceModule.ResourceType.BOILED_EGG);
                //Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.RAW_VEGETABLE, 40);
                this.resourceStorageMax.put(ResourceModule.ResourceType.RATION, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.RAW_MEAT, 40);
                this.resourceStorageMax.put(ResourceModule.ResourceType.COOKED_MEAT, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.EGG, 40);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BOILED_EGG, 16);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.RATION, 20.0);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.COOKED_MEAT, 20.0);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.BOILED_EGG, 20.0);
            }
            case LANDFILL:{
                this.workerJobType = MobJobBase.MobJobType.TRASHER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.CRYSTALLY_TRASH, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.ORGANICY_TRASH, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.ROCKY_TRASH, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.SUSPICICIOUSY_TRASH, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.TRASHY_CUBE, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.TRASHY_TRASH, ObjectBase.ResourceStorePriority.MEDIUM);
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOODY_TRASH, ObjectBase.ResourceStorePriority.MEDIUM);
                this.canBuildingStore.add(ResourceModule.ResourceType.CRYSTALLY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.ORGANICY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.ROCKY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.SUSPICICIOUSY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.TRASHY_CUBE);
                this.canBuildingStore.add(ResourceModule.ResourceType.TRASHY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.WOODY_TRASH);
                //Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.CRYSTALLY_TRASH, 512);
                this.resourceStorageMax.put(ResourceModule.ResourceType.ORGANICY_TRASH, 512);
                this.resourceStorageMax.put(ResourceModule.ResourceType.ROCKY_TRASH, 512);
                this.resourceStorageMax.put(ResourceModule.ResourceType.SUSPICICIOUSY_TRASH, 512);
                this.resourceStorageMax.put(ResourceModule.ResourceType.TRASHY_CUBE, 512);
                this.resourceStorageMax.put(ResourceModule.ResourceType.TRASHY_TRASH, 512);
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOODY_TRASH, 512);
            }
            case LUMBER_MILL:{
                this.workerJobType = MobJobBase.MobJobType.CARPENTER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOOD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BOARD, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.WOOD);
                this.canBuildingStore.add(ResourceModule.ResourceType.BOARD);
                //Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOOD, 96);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BOARD, 32);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.BOARD, 20.0);
            }
            case LUMBER_SHACK:{
                this.workerJobType = MobJobBase.MobJobType.LUMBERJACK;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOOD, ObjectBase.ResourceStorePriority.MEDIUM);
                this.canBuildingStore.add(ResourceModule.ResourceType.WOOD);
                //Switch
                this.workersProvided = 14;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOOD, 64);
                this.harvestingSpeedReduction.put(ResourceModule.ResourceType.WOOD, 20.0);
            }
            case MAINTENANCE_BUILDING:{
                this.workerJobType = MobJobBase.MobJobType.MAINTAINER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.AXE, ObjectBase.ResourceStorePriority.LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.HAMMER, ObjectBase.ResourceStorePriority.LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.PICK_AXE, ObjectBase.ResourceStorePriority.LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.SHOVEL, ObjectBase.ResourceStorePriority.LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.AXE);
                this.canBuildingStore.add(ResourceModule.ResourceType.HAMMER);
                this.canBuildingStore.add(ResourceModule.ResourceType.PICK_AXE);
                this.canBuildingStore.add(ResourceModule.ResourceType.SHOVEL);
                //Switch
                this.workersProvided = 8;
                this.resourceStorageMax.put(ResourceModule.ResourceType.AXE, 12);
                this.resourceStorageMax.put(ResourceModule.ResourceType.HAMMER, 12);
                this.resourceStorageMax.put(ResourceModule.ResourceType.PICK_AXE, 12);
                this.resourceStorageMax.put(ResourceModule.ResourceType.SHOVEL, 12);
                this.dismantlingSpeedReduction = 15.0;
                this.clearingSpeedReduction = 15.0;
                this.repairingSpeedReduction = 15.0;
                this.repairingRoadSpeedReduction = 15.0;
            }
            case MARKETPLACE:{
                this.workerJobType = MobJobBase.MobJobType.PROVISIONER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.GOLD_COIN_SACK, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.GOLD_COIN_SACK);
                //Switch
                this.workersProvided = 16;
                this.catjeetProvisionersProvided = 16;
                this.resourceStorageMax.put(ResourceModule.ResourceType.GOLD_COIN_SACK, 96);
            }
            case MINING_FACILITY:{
                this.workerJobType = MobJobBase.MobJobType.MINER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.ROCK, ObjectBase.ResourceStorePriority.MEDIUM);
                this.canBuildingStore.add(ResourceModule.ResourceType.ROCK);
                //Switch
                this.workersProvided = 15;
                this.resourceStorageMax.put(ResourceModule.ResourceType.ROCK, 128);
                this.harvestingSpeedReduction.put(ResourceModule.ResourceType.ROCK, 20.0);
            }
            case OUTPOST:{
                this.workerJobType = MobJobBase.MobJobType.RANGER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_CHEST_ARMOR, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_HELMET, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_SHIELD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.LEATHER_SHIELD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.LEATHER_CHEST_ARMOR, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.LEATHER_HELMET, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BANDAGE, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.HEALING_POTION, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_SWORD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOOD_SWORD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BOW, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.QUIVER, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.BALLISTAE_BOLTS);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_CHEST_ARMOR);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_HELMET);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_SHIELD);
                this.canBuildingStore.add(ResourceModule.ResourceType.LEATHER_SHIELD);
                this.canBuildingStore.add(ResourceModule.ResourceType.LEATHER_CHEST_ARMOR);
                this.canBuildingStore.add(ResourceModule.ResourceType.LEATHER_HELMET);
                this.canBuildingStore.add(ResourceModule.ResourceType.BANDAGE);
                this.canBuildingStore.add(ResourceModule.ResourceType.HEALING_POTION);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_SWORD);
                this.canBuildingStore.add(ResourceModule.ResourceType.WOOD_SWORD);
                this.canBuildingStore.add(ResourceModule.ResourceType.BOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.QUIVER);
                //Switch
                this.ammoMax = 20;
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_CHEST_ARMOR, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_HELMET, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_SHIELD, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.LEATHER_SHIELD, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.LEATHER_CHEST_ARMOR, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.LEATHER_HELMET, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BANDAGE, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.HEALING_POTION, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_SWORD, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOOD_SWORD, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BOW, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.QUIVER, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, 6);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.BALLISTAE_BOLTS);
                this.towerCoordinates = OrderedPair.getOrderedPair(2, 2);
                this.towerType = TowerBase.TowerType.BOW;
                this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE;
                this.canBuildingFire.add(ResourceModule.ResourceType.BALLISTAE_BOLTS);
                this.hasFillArt = true;
            }
            case PROCESSOR:{
                this.workerJobType = MobJobBase.MobJobType.TRASHER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.CRYSTALLY_TRASH, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.ORGANICY_TRASH, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.ROCKY_TRASH, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.SUSPICICIOUSY_TRASH, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.TRASHY_CUBE, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.TRASHY_TRASH, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOODY_TRASH, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.CRYSTALLY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.ORGANICY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.ROCKY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.SUSPICICIOUSY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.TRASHY_CUBE);
                this.canBuildingStore.add(ResourceModule.ResourceType.TRASHY_TRASH);
                this.canBuildingStore.add(ResourceModule.ResourceType.WOODY_TRASH);
                this.isProcessor = true;
                //Switch
                this.workersProvided = 4;
                this.processingTrashSpeedReduction = 20.0;
                this.resourceStorageMax.put(ResourceModule.ResourceType.CRYSTALLY_TRASH, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.ORGANICY_TRASH, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.ROCKY_TRASH, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.SUSPICICIOUSY_TRASH, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.TRASHY_CUBE, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.TRASHY_TRASH, 40);
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOODY_TRASH, 16);
            }
            case BOTTLER: {
                this.isFountain = true;
                this.canCatchRain = true;
                this.rainCatchRate = 500;
                this.workerJobType = MobJobBase.MobJobType.BOTTLER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WATER_BUCKET, ObjectBase.ResourceStorePriority.VERY_HIGH); //WATER_BOTTLE
                this.canBuildingStore.add(ResourceModule.ResourceType.WATER_BUCKET); //WATER_BOTTLE
                // Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WATER_BUCKET, 40); //WATER_BOTTLE, 20
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.WATER_BOTTLE, 20.0);
            }
            case WELL:{
                this.canCatchRain = true;
                this.rainCatchRate = 200;
                this.canGenerateWater = true;
                this.workerJobType = MobJobBase.MobJobType.WATER_MASTER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WATER_BUCKET, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.WATER_BUCKET);
                //Switch
                this.waterGenerationRate = 50;
                this.workersProvided = 2;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WATER_BUCKET, 4);
            }
            case WATER_PURIFIER:{
                this.canPurifyWater = true;
                this.workerJobType = MobJobBase.MobJobType.WATER_MASTER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WATER_BUCKET, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.DIRTY_WATER_BUCKET, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.WATER_BUCKET);
                this.canBuildingStore.add(ResourceModule.ResourceType.DIRTY_WATER_BUCKET);
                //Switch
                this.purifyWaterRate = 20;
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WATER_BUCKET, 20);
                this.resourceStorageMax.put(ResourceModule.ResourceType.DIRTY_WATER_BUCKET, 60);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.WATER_BUCKET, 20.0);
                this.harvestingSpeedReduction.put(ResourceModule.ResourceType.DIRTY_WATER_BUCKET, 20.0);
            }
            case RAIN_CATCHER:{
                this.canCatchRain = true;
                this.rainCatchRate = 10;
                this.workerJobType = MobJobBase.MobJobType.WATER_MASTER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WATER_BUCKET, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.WATER_BUCKET);
                //Switch
                this.workersProvided = 2;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WATER_BUCKET, 12);
            }
            case RANGER_LODGE:{
                this.workerJobType = MobJobBase.MobJobType.RANGER;
                this.occupantsJob = MobJobBase.MobJobType.RANGER;
                this.occupantsSleepBonusPercentOff = 15;
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_CHEST_ARMOR, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_HELMET, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_SHIELD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.LEATHER_SHIELD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.LEATHER_CHEST_ARMOR, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.LEATHER_HELMET, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BANDAGE, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.HEALING_POTION, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_SWORD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOOD_SWORD, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BOW, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.QUIVER, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_CHEST_ARMOR);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_HELMET);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_SHIELD);
                this.canBuildingStore.add(ResourceModule.ResourceType.LEATHER_SHIELD);
                this.canBuildingStore.add(ResourceModule.ResourceType.LEATHER_CHEST_ARMOR);
                this.canBuildingStore.add(ResourceModule.ResourceType.LEATHER_HELMET);
                this.canBuildingStore.add(ResourceModule.ResourceType.BANDAGE);
                this.canBuildingStore.add(ResourceModule.ResourceType.HEALING_POTION);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_SWORD);
                this.canBuildingStore.add(ResourceModule.ResourceType.WOOD_SWORD);
                this.canBuildingStore.add(ResourceModule.ResourceType.BOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.QUIVER);
                //Switch
                this.ammoMax = 20;
                this.workersProvided = 16;
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_CHEST_ARMOR, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_HELMET, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_SHIELD, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.LEATHER_SHIELD, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.LEATHER_CHEST_ARMOR, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.LEATHER_HELMET, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BANDAGE, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.HEALING_POTION, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_SWORD, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOOD_SWORD, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BOW, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.QUIVER, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, 6);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.BALLISTAE_BOLTS);
                this.occupantsMax = this.workersProvided;
                this.occupantsHealthRegenBonusTicks = 5;
                this.towerCoordinates = OrderedPair.getOrderedPair(6, 1); //this.width = 8; this.height = 9;
                this.towerType = TowerBase.TowerType.BOW;
                this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE;
                this.canBuildingFire.add(ResourceModule.ResourceType.BALLISTAE_BOLTS);
            }
            case RELIQUARY:{
                this.workerJobType = MobJobBase.MobJobType.OCCULTIST;
                this.canDeliverEssenceToCollectors = true;
                this.canReleaseEssenceToGod = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(8, 2);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0);
                this.buildingStorePriority.put(ResourceModule.ResourceType.EMPTY_EERIE_VESSEL, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_INGOT, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.GOLD_INGOT, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.EMPTY_EERIE_VESSEL);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_INGOT);
                this.canBuildingStore.add(ResourceModule.ResourceType.GOLD_INGOT);
                this.resourceStorageMax.put(ResourceModule.ResourceType.EMPTY_EERIE_VESSEL, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_INGOT, 4);
                this.resourceStorageMax.put(ResourceModule.ResourceType.GOLD_INGOT, 4);
                //Switch
                this.workersProvided = 2;
                this.prayingSpeedReduction = 4.0;
                this.capturingSoulsSpeedReduction = 4.0;
                this.energyMax = 400;
            }
            case ROCK_TUMBLER:{
                this.workerJobType = MobJobBase.MobJobType.TUMBLER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.ROCK, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.STONE_BALLS, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.ROCK);
                this.canBuildingStore.add(ResourceModule.ResourceType.STONE_BALLS);
                //Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.ROCK, 24);
                this.resourceStorageMax.put(ResourceModule.ResourceType.STONE_BALLS, 16);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.STONE_BALLS, 20.0);
            }
            case STONE_CUTTERY:{
                this.workerJobType = MobJobBase.MobJobType.STONE_CUTTER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.ROCK, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.buildingStorePriority.put(ResourceModule.ResourceType.CUT_STONE, ObjectBase.ResourceStorePriority.VERY_LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.ROCK);
                this.canBuildingStore.add(ResourceModule.ResourceType.CUT_STONE);
                //Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.ROCK, 42);
                this.resourceStorageMax.put(ResourceModule.ResourceType.CUT_STONE, 24);
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.CUT_STONE, 20.0);
            }
            case TOOLSMITHY:{
                this.workerJobType = MobJobBase.MobJobType.TOOLSMITH;
                this.buildingStorePriority.put(ResourceModule.ResourceType.AXE, ObjectBase.ResourceStorePriority.VERY_LOW); // HAMMER, HOE, PICK_AXE, SHOVEL, IRON_SWORD VERY_HIGH, IRON_INGOT, WOOD_SWORD, BOARD VERY_HIGH
                this.canBuildingStore.add(ResourceModule.ResourceType.AXE); // HAMMER, HOE, PICK_AXE, SHOVEL, IRON_SWORD, IRON_INGOT, WOOD_SWORD, BOARD
                //Switch
                this.workersProvided = 4;
                this.resourceStorageMax.put(ResourceModule.ResourceType.AXE, 16); // HAMMER, HOE, PICK_AXE, SHOVEL, IRON_SWORD, IRON_INGOT 40, WOOD_SWORD, BOARD 40
                this.refiningSpeedReduction.put(ResourceModule.ResourceType.AXE, 20.0); // HAMMER, HOE, PICK_AXE, SHOVEL, IRON_SWORD, WOOD_SWORD (for some reasons is not here)
            }
            case WAY_MAKER_SHACK:{
                this.workerJobType = MobJobBase.MobJobType.WAY_MAKER;
                this.buildingStorePriority.put(ResourceModule.ResourceType.WOOD, ObjectBase.ResourceStorePriority.LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.BOARD, ObjectBase.ResourceStorePriority.LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.ROCK, ObjectBase.ResourceStorePriority.LOW);
                this.buildingStorePriority.put(ResourceModule.ResourceType.CUT_STONE, ObjectBase.ResourceStorePriority.LOW);
                this.canBuildingStore.add(ResourceModule.ResourceType.WOOD);
                this.canBuildingStore.add(ResourceModule.ResourceType.BOARD);
                this.canBuildingStore.add(ResourceModule.ResourceType.ROCK);
                this.canBuildingStore.add(ResourceModule.ResourceType.CUT_STONE);
                //Switch
                this.workersProvided = 14;
                this.resourceStorageMax.put(ResourceModule.ResourceType.WOOD, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.BOARD, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.ROCK, 16);
                this.resourceStorageMax.put(ResourceModule.ResourceType.CUT_STONE, 16);
                this.buildingRoadSpeedReduction = 15.0;
                this.repairingRoadSpeedReduction = 15.0;
            }
            case COURIER_STATION:{
                this.combobulatorSpawnTypes.clear();
                this.canAcceptEssence = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(2, 5);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(8, 8);
                this.energyMax = 200;
                this.workerJobType = MobJobBase.MobJobType.COURIER_SUPPLIER;
                this.isCombobulator = true;
                this.combobulatorEnergyLossPerGolem = 1;
                this.combobulatorEnergyLossPerGolemRate = 2500;
                this.combobulatorResourceDistance = 32;
                this.combobulatorResourceType = ResourceModule.ResourceType.ROCK;
                this.combobulatorSpawnTypes.add(MobBase.MobType.COURIER_GOLEM);
                // Switch
                this.workersProvided = 4;
                this.combobulatorMax = 8;
                this.combobulatorBonusLevel = 8;
                this.combobulatorEnergyRequired = 50;
                this.combobulatorEnergyRate = 40;
            }
            case WOOD_GOLEM_COMBOBULATOR:
            case STONE_GOLEM_COMBOBULATOR:
            case CRYSTAL_GOLEM_COMBOBULATOR:{ //Only needs (mobGroup = MobBase.MobGroup.VILLAGER, baseRange, baseDesirability, hasFillArt = true)
                this.canAcceptEssence = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(2, 2);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0);
                this.energyMax = 200;
                this.isCombobulator = true;
                this.combobulatorEnergyLossPerGolem = 1;
                this.combobulatorEnergyLossPerGolemRate = 500;
                this.combobulatorResourceDistance = 32;
                this.combobulatorResourceType = ResourceModule.ResourceType.CRYSTAL; // WOOD, ROCK
                this.resourceStorageMax.put(ResourceModule.ResourceType.CRYSTAL, 6); // WOOD, ROCK
                this.buildingStorePriority.put(ResourceModule.ResourceType.CRYSTAL, ObjectBase.ResourceStorePriority.VERY_HIGH); // WOOD, ROCK
                this.canBuildingStore.add(ResourceModule.ResourceType.CRYSTAL); // WOOD, ROCK
                this.combobulatorSpawnTypes.clear();
                switch (this.currentType) {
                    case CRYSTAL_GOLEM_COMBOBULATOR: { // WOOD_GOLEM_COMBOBULATOR, STONE_GOLEM_COMBOBULATOR
                        this.canUpgrade = true;
                        this.nextUpgradeType = MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_1; // WOOD_GOLEM_COMBOBULATOR_UPGRADE_1, STONE_GOLEM_COMBOBULATOR_UPGRADE_1
                        this.combobulatorMax = 2;
                        this.combobulatorEnergyRequired = 100;
                        this.combobulatorEnergyRate = 160;
                        if (this.combobulatorEasterEggMode) {
                            this.combobulatorSpawnTypes.add(MobBase.MobType.CRYGGO_GOLEM); // LOGGO_GOLEM, ROGGO_GOLEM
                            break;
                        }
                        this.combobulatorSpawnTypes.add(MobBase.MobType.CRYSTAL_GOLEM); // WOOD_GOLEM, STONE_GOLEM
                        break;
                    }
                    case CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_1: { // WOOD_GOLEM_COMBOBULATOR_UPGRADE_1, STONE_GOLEM_COMBOBULATOR_UPGRADE_1
                        this.canUpgrade = true;
                        this.nextUpgradeType = MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_2; // WOOD_GOLEM_COMBOBULATOR_UPGRADE_2, STONE_GOLEM_COMBOBULATOR_UPGRADE_2
                        this.combobulatorMax = 3;
                        this.combobulatorBonusLevel = 4;
                        this.combobulatorEnergyRequired = 125;
                        this.combobulatorEnergyRate = 120;
                        if (this.combobulatorEasterEggMode) {
                            this.combobulatorSpawnTypes.add(MobBase.MobType.CRYGGO_GOLEM); // LOGGO_GOLEM, ROGGO_GOLEM
                            break;
                        }
                        this.combobulatorSpawnTypes.add(MobBase.MobType.CRYSTAL_GOLEM); // WOOD_GOLEM, STONE_GOLEM
                        break;
                    }
                    case CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_2: { // WOOD_GOLEM_COMBOBULATOR_UPGRADE_2, STONE_GOLEM_COMBOBULATOR_UPGRADE_2
                        this.canUpgrade = true;
                        this.nextUpgradeType = MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING; // WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING, STONE_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING
                        this.nextUpgradeType2 = MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_ICE; // WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_ICE, STONE_GOLEM_COMBOBULATOR_UPGRADE_3_ICE
                        this.nextUpgradeType3 = MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_FIRE; // WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_POISON, STONE_GOLEM_COMBOBULATOR_UPGRADE_3_FIRE
                        this.combobulatorMax = 4;
                        this.combobulatorBonusLevel = 8;
                        this.combobulatorEnergyRequired = 150;
                        this.combobulatorEnergyRate = 160;
                        if (this.combobulatorEasterEggMode) {
                            this.combobulatorSpawnTypes.add(MobBase.MobType.CRYGGO_GOLEM); // LOGGO_GOLEM, ROGGO_GOLEM
                            break;
                        }
                        this.combobulatorSpawnTypes.add(MobBase.MobType.CRYSTAL_GOLEM); // WOOD_GOLEM, STONE_GOLEM
                        break;
                    }
                    case CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING: { // WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING, STONE_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING
                        this.combobulatorMax = 5;
                        this.combobulatorBonusLevel = 12;
                        this.combobulatorEnergyRequired = 200;
                        this.combobulatorEnergyRate = 120;
                        if (this.combobulatorEasterEggMode) {
                            this.combobulatorSpawnTypes.add(MobBase.MobType.LIGHTNING_CRYGGO_GOLEM); // LIGHTNING_LOGGO_GOLEM, LIGHTNING_ROGGO_GOLEM
                            break;
                        }
                        this.combobulatorSpawnTypes.add(MobBase.MobType.LIGHTNING_CRYSTAL_GOLEM); // LIGHTNING_WOOD_GOLEM, LIGHTNING_STONE_GOLEM
                        break;
                    }
                    case CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_ICE: { // WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_ICE, STONE_GOLEM_COMBOBULATOR_UPGRADE_3_ICE
                        this.combobulatorMax = 5;
                        this.combobulatorBonusLevel = 12;
                        this.combobulatorEnergyRequired = 200;
                        this.combobulatorEnergyRate = 60;
                        if (this.combobulatorEasterEggMode) {
                            this.combobulatorSpawnTypes.add(MobBase.MobType.ICE_CRYGGO_GOLEM); // ICE_LOGGO_GOLEM, ICE_ROGGO_GOLEM
                            break;
                        }
                        this.combobulatorSpawnTypes.add(MobBase.MobType.ICE_CRYSTAL_GOLEM); // ICE_WOOD_GOLEM, ICE_STONE_GOLEM
                        break;
                    }
                    case CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_FIRE: { // WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_POISON, STONE_GOLEM_COMBOBULATOR_UPGRADE_3_FIRE
                        this.combobulatorMax = 5;
                        this.combobulatorBonusLevel = 12;
                        this.combobulatorEnergyRequired = 200;
                        this.combobulatorEnergyRate = 60;
                        if (this.combobulatorEasterEggMode) {
                            this.combobulatorSpawnTypes.add(MobBase.MobType.FIRE_CRYGGO_GOLEM); // POISON_LOGGO_GOLEM, FIRE_ROGGO_GOLEM
                            break;
                        }
                        this.combobulatorSpawnTypes.add(MobBase.MobType.FIRE_CRYSTAL_GOLEM); // POISON_WOOD_GOLEM, FIRE_STONE_GOLEM
                    }
                }
            }
        }

        //Storage
        // For Turrets  ResourceStorePriority.VERY_HIGH     &   Ammo ResourceType:  STONE_BALLS, BALLISTAE_BOLTS
        // For Storage  ResourceStorePriority.MEDIUM        &   ResourceType:       EMPTY_EERIE_VESSEL, HEALING_POTION, SUSPICICIOUS_KEY, WOOD_SWORD, IRON_ORE, GOLD_ORE, GOLD_COIN_SACK, GOLD_INGOT, IRON_SWORD, SHOVEL, PICK_AXE, HOE, HAMMER, AXE, BOILED_EGG, EGG, COOKED_MEAT, RAW_MEAT, BALLISTAE_BOLTS, RATION, STONE_BALLS, QUIVER, RAW_VEGETABLE, WATER_BUCKET, IRON_CHEST_ARMOR, IRON_HELMET, IRON_SHIELD, LEATHER_CHEST_ARMOR, LEATHER_HELMET, LEATHER_SHIELD, IRON_INGOT, RAW_HIDE, WATER_BOTTLE, WOOD, FEATHER, SILK, BOW, CRYSTALLY_TRASH, ORGANICY_TRASH, ROCKY_TRASH, SUSPICICIOUSY_TRASH, TRASHY_CUBE, TRASHY_TRASH, WOODY_TRASH, ROCK, CUT_STONE, BOARD, CRYSTAL, CRYLITHIUM, BANDAGE, MEDKIT
        this.buildingStorePriority.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, ObjectBase.ResourceStorePriority.VERY_HIGH);
        this.canBuildingStore.add(ResourceModule.ResourceType.BALLISTAE_BOLTS);
        this.resourceStorageMax.put(ResourceModule.ResourceType.BALLISTAE_BOLTS, 4);
        // Tower
        this.towerCoordinates = OrderedPair.getOrderedPair(1, 1); //è la metà della grandezza della tower, qui risiede la capocchia della torretta
        this.towerType = TowerBase.TowerType.MOTIVATOR; //  Le towerType possono essere: ELEMENTAL_BOLT, BOW, BALLISTA, BULLET, SLING, MOTIVATOR, SPRAY, BANISH, ATTRACT, RECOMBOBULATOR, PHANTOM_DART, STATIC, GOD_BOW
        this.ammoMax = 10;
        this.canBuildingFire.add(ResourceModule.ResourceType.BALLISTAE_BOLTS); // For Turrets Ammo Type: STONE_BALLS, BALLISTAE_BOLTS
        // Essence
        this.canAcceptEssence = true; // essence tower
        this.canCollectEssence = true; // essence collector
        this.canDeliverEssence = true; // essence collector
        this.canReleaseEssenceToGod = true; // essence collector
        this.essenceReturnCoordinates = OrderedPair.getOrderedPair(1, 1); // essence tower & essence collector metà della grandezza della tower/struct
        this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0); // per tutti quelli che utilizzano essence è (8, 8), ad eccezzione di: Combobulator, Reliquary e CrystalMotivator, dove è (0, 0)
        this.energyMax = 300; // essence tower & essence collector
        //CullisGate
        this.isCullisGate = true;
        this.canInstability = true;
        this.instabilityAmountMax = 5000;
        //RadiancePool
        this.canEnergyRegenerateFree = true;
        this.energyRegenerationRate = 500;
        //Richieste che possono esserci post costruzione, ad esempio nei livelli o richieste particolari
        this.requiredSupportBuildings.add(new MapTilesLoader.TileSet[]{MapTilesLoader.TileSet.ESSENCE_COLLECTOR});
        this.influenceMaintainCost = 1000; //godbow
        //Upgrades
        this.canUpgrade = true;
        this.nextUpgradeType = MapTilesLoader.TileSet.ATTRACT_TOWER_UPGRADE_1;
        this.nextUpgradeType2 = MapTilesLoader.TileSet.ELEMENTAL_BOLT_TOWER_UPGRADE_3_LIGHTNING;
        this.nextUpgradeType3 = MapTilesLoader.TileSet.ELEMENTAL_BOLT_TOWER_UPGRADE_3_ICE;
        this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE; // Le towerUpgrade possono essere BASE, UPGRADE_1, UPGRADE_2, UPGRADE_3, FIRE, POISON, ICE, LIGHTNING;
        //Gates
        this.gateCoordinates = OrderedPair.getOrderedPair(1, 1);
        this.gateType = GateBase.GateType.STONE_GATE_WE; //Type: STONE_GATE_WE, STONE_GATE_NS, WOOD_GATE_NS, WOOD_GATE_WE
        this.allowPause = true;//viene dichiarato qui nei gates e non prima boo
        //Lighting Rod: create essence so add: essenceReturnCoordinates, essenceReturnCoordinatesPixelOffset, energyMax
        this.isLightningRod = true;
        this.lightningRodRange = 32;
        this.lightningRodCoordinates = OrderedPair.getOrderedPair(1, 1);
        this.lightningRodDissipationRate = 250;
        //Housing: can be done for any friendly mob: DOGGO, DOOFY_DOGGO, BEEFALO, ROUS, ENTLER, CLUCKER
        //Housing is a little bit tricky, but for Villagers I think it only needs: this.occupantsMax = 36; this.occupantsSleepBonusPercentOff = 65; this.occupantsHealthRegenBonusTicks = 10; this.occupantsFaithBonus = 5;
        this.occupantsTypes.clear();
        this.occupantsTypes.add(MobBase.MobType.DOGGO);
        this.occupantsMax = 18;
        this.occupantsSleepBonusPercentOff = 80;
        this.occupantsHealthRegenBonusTicks = 4;
        this.occupantsFaithBonus = 3;
        //fountain
        this.isFountain = true;
        this.canCatchRain = true;
        this.rainCatchRate = 300;
        this.resourceStorageMax.put(ResourceModule.ResourceType.WATER_BUCKET, 96);
        this.buildingStorePriority.put(ResourceModule.ResourceType.WATER_BUCKET, ObjectBase.ResourceStorePriority.HIGH);
        this.canBuildingStore.add(ResourceModule.ResourceType.WATER_BUCKET);
        //LootBoxes
        this.buildingStorePriority.put(ResourceModule.ResourceType.SUSPICICIOUS_KEY, ObjectBase.ResourceStorePriority.VERY_HIGH);
        this.canBuildingStore.add(ResourceModule.ResourceType.SUSPICICIOUS_KEY);
        this.isLootBox = true;
        this.resourceStorageMax.put(ResourceModule.ResourceType.SUSPICICIOUS_KEY, 1);
        //Trash Can
        this.trashCan = true; //Storage with all trash types


        //Quando le intere classi sono necessarie per capire le metto qui
        switch (this.currentType) {
            case CUBE_E_GOLEM_COMBOBULATOR:{
                this.combobulatorSpawnTypes.clear();
                this.canAcceptEssence = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(1, 1);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(8, 8);
                this.energyMax = 500;
                this.isCombobulator = true;
                this.combobulatorEnergyLossPerGolem = 1;
                this.combobulatorEnergyLossPerGolemRate = 1500;
                this.combobulatorResourceDistance = 32;
                this.combobulatorResourceType = ResourceModule.ResourceType.IRON_INGOT;
                this.combobulatorSpawnTypes.add(MobBase.MobType.CUBE_E_GOLEM);
                this.resourceStorageMax.put(ResourceModule.ResourceType.IRON_INGOT, 6);
                this.buildingStorePriority.put(ResourceModule.ResourceType.IRON_INGOT, ObjectBase.ResourceStorePriority.VERY_HIGH);
                this.canBuildingStore.add(ResourceModule.ResourceType.IRON_INGOT);
                //Switch
                this.combobulatorMax = 4;
                this.combobulatorBonusLevel = 8;
                this.combobulatorEnergyRequired = 150;
                this.combobulatorEnergyRate = 40;
            }
            case MIGRATION_WAY_STATION:
            case MISCELLANEOUS_STORAGE:{
                ResourceModule.ResourceType t;
                ResourceModule.ResourceType[] resourceTypeArray = ResourceModule.ResourceType.values();
                int n = resourceTypeArray.length;
                int n2 = 0;
                while (n2 < n) {
                    t = resourceTypeArray[n2];
                    if (!t.getTemplate().dontAddMisc() && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.TRASH) {
                        this.buildingStorePriority.put(t, ObjectBase.ResourceStorePriority.LOW); //MISCELLANEOUS_STORAGE: ObjectBase.ResourceStorePriority.MEDIUM
                        this.canBuildingStore.add(t);
                    }
                    ++n2;
                }
                //Switch
                this.migrationsPerDayProvided = 14;     //not in MISCELLANEOUS_STORAGE
                resourceTypeArray = ResourceModule.ResourceType.values();
                n = resourceTypeArray.length;
                n2 = 0;
                while (n2 < n) {
                    t = resourceTypeArray[n2];
                    if (!t.getTemplate().dontAddMisc() && t.getTemplate().getResourceCategory() != ResourceModule.ResourceCategory.TRASH) {
                        this.resourceStorageMax.put(t, 14);  //MISCELLANEOUS_STORAGE: this.resourceStorageMax.put(t, 30);
                    }
                    ++n2;
                }
                break;
            }
        }
    }

    @Override
    protected void configPerkAdjustments() {
        this.perkAdjustment(PerkModule.PerkType.EFFICIENT_CONSTRUCTION); //construction base
        this.perkAdjustment(PerkModule.PerkType.ESSENCE_COLLECTOR_CAPACITY); // Essence Collector
        this.perkAdjustment(PerkModule.PerkType.TOWER_STORAGE); // Tower
        this.perkAdjustment(PerkModule.PerkType.IMPROVED_STORAGE); // Storage
        this.perkAdjustment(PerkModule.PerkType.EFFICIENT_TRASH_BURNING); // Trash
        this.perkAdjustment(PerkModule.PerkType.INCREASED_CULLIS_GATE_STABILITY); // Cullis Gate
        this.perkAdjustment(PerkModule.PerkType.EFFICIENT_HOUSING); // Housing
        this.perkAdjustment(PerkModule.PerkType.WATER_STORAGE); // Fountain, Rain Catcher, contain water somehow
        this.perkAdjustment(PerkModule.PerkType.MARKET_CATJEET_CAPACITY); // Marketplace
        // Combombulator
        this.perkAdjustment(PerkModule.PerkType.CRYSTAL_GOLEM_LEVEL);  // Crystal Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.CRYSTAL_GOLEM_CAPACITY); // Crystal Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.CRYSTAL_GOLEM_CHARGE_TIME); // Crystal Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.STONE_GOLEM_LEVEL);  // Stone Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.STONE_GOLEM_CAPACITY);  // Stone Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.STONE_GOLEM_CHARGE_TIME);  // Stone Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.WOOD_GOLEM_LEVEL);  // Wood Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.WOOD_GOLEM_CAPACITY);  // Wood Golem Combombulator
        this.perkAdjustment(PerkModule.PerkType.WOOD_GOLEM_CHARGE_TIME);  // Wood Golem Combombulator
        // Only For God Spells
        this.perkAdjustment(PerkModule.PerkType.GOD_TOWER_SPELL_COST);
        this.perkAdjustment(PerkModule.PerkType.GOD_WALL_SPELL_COST);

    }
}