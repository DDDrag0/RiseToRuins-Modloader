package rtr.gui.states.playstate;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import rtr.ImageLoader;
import rtr.SettingsParser;
import rtr.font.Text;
import rtr.gui.GUIEnums;
import rtr.gui.buttons.GUIButtonHide;
import rtr.gui.buttons.GUIButtonPage;
import rtr.gui.buttons.GUIPanelButton;
import rtr.gui.buttons.GUIPanelSubButton;
import rtr.gui.buttons.GUISquarePanelButton;
import rtr.gui.buttons.GUIToolTipOverlay;
import rtr.gui.states.PlayStateGUIController;
import rtr.gui.states.PlayStateGUIData;
import rtr.gui.states.PlayStateGUIPanelBase;
import rtr.map.MapTilesLoader;
import rtr.missiles.MissileModule;
import rtr.mobs.MobBase;
import rtr.objects.ObjectBase;
import rtr.particles.ParticleModule;
import rtr.resources.ResourceModule;
import rtr.states.PlayState;
import rtr.states.StateBase;
import rtr.system.Game;
import rtr.system.ScaleControl;
import rtr.system.gamemodetemplates.GameModeTemplateBase;

public class RightPanel
extends PlayStateGUIPanelBase {
    private Rectangle mask;
    private Image blankLargePanel1;
    private Image blankLargePanel2;
    private Image blankLargePanel3;
    private Image blankLargePanel4;
    private Image blankLargePanel5;
    private Image blankLargePanel6;
    private GUIEnums.GUIPanelPage panelCurrentPage;
    private GUIEnums.GUIPanelPage nextPage;
    private GUIEnums.GUIPanelPage lastPage;
    private GUIEnums.GUIPanelPage backPage;
    private GUIButtonPage pageBackTop;
    private GUIButtonPage pageUpTop;
    private GUIButtonPage pageDownTop;
    private GUIButtonPage pageBackBottom;
    private GUIButtonPage pageUpBottom;
    private GUIButtonPage pageDownBottom;
    private GUIButtonHide hidePanel;
    private GUISquarePanelButton buildTab;
    private GUISquarePanelButton roadsAndDiggingTab;
    private GUISquarePanelButton roadsAndDiggingTabMock;
    private GUISquarePanelButton tilesTab;
    private GUISquarePanelButton sandboxToolsTab;
    private boolean hidden;
    private GUIPanelButton panelCategoryBuild;
    private GUIPanelButton panelCategoryTiles;
    private GUIPanelButton panelCategoryTime;
    private GUIPanelButton panelCategorySpawning;
    private GUIPanelButton panelCategoryParticles;
    private GUIPanelButton panelCategoryMisc;
    private GUIPanelButton panelCategoryCivics;
    private GUIPanelButton panelCategoryDefense;
    private GUIPanelButton panelCategoryFoodAndWater;
    private GUIPanelButton panelCategoryGatesAndWalls;
    private GUIPanelButton panelCategoryHarvesting;
    private GUIPanelButton panelCategoryHousing;
    private GUIPanelButton panelCategoryLighting;
    private GUIPanelButton panelCategoryMagic;
    private GUIPanelButton panelCategoryManufacturing;
    private GUIPanelButton panelCategoryRefining;
    private GUIPanelButton panelCategoryStorage;
    private GUIPanelButton panelCategoryTrash;
    private GUIPanelButton panelCategorySpecial;
    private GUIPanelButton panelAncillary;
    private GUIPanelButton panelClinic;
    private GUIPanelButton panelCourierStation;
    private GUIPanelButton panelMaintenanceBuilding;
    private GUIPanelButton panelMarketplace;
    private GUIPanelButton panelWayMakerShack;
    private GUIPanelButton panelMigrationWayStation;
    private GUIPanelButton panelCategoryDefenseGolems;
    private GUIPanelButton panelCategoryDefenseTowers;
    private GUIPanelButton panelCategoryDefenseMiscellaneous;
    private GUIPanelButton panelCrystalGolemCombobulator;
    private GUIPanelButton panelStoneGolemCombobulator;
    private GUIPanelButton panelWoodGolemCombobulator;
    private GUIPanelButton panelBanishTower;
    private GUIPanelButton panelAttractTower;
    private GUIPanelButton panelBallistaTower;
    private GUIPanelButton panelBowTower;
    private GUIPanelButton panelBulletTower;
    private GUIPanelButton panelElementalBoltTower;
    private GUIPanelButton panelPhantomDartTower;
    private GUIPanelButton panelSlingTower;
    private GUIPanelButton panelSprayTower;
    private GUIPanelButton panelStaticTower;
    private GUIPanelButton panelLightningRod;
    private GUIPanelButton panelRecombobulatorTower;
    private GUIPanelButton panelAnimalPen;
    private GUIPanelButton panelBottler;
    private GUIPanelButton panelCluckerCoop;
    private GUIPanelButton panelFarm;
    private GUIPanelButton panelFishingHut;
    private GUIPanelButton panelSmallFountain;
    private GUIPanelButton panelLargeFountain;
    private GUIPanelButton panelKitchen;
    private GUIPanelButton panelOutpost;
    private GUIPanelButton panelRainCatcher;
    private GUIPanelButton panelRangerLodge;
    private GUIPanelButton panelWaterPurifier;
    private GUIPanelButton panelWell;
    private GUIPanelButton panelCategoryGatesAndWallsGates;
    private GUIPanelButton panelCategoryGatesAndWallsWalls;
    private GUIPanelButton panelCrylithiumWallGateNS;
    private GUIPanelButton panelCrylithiumWallGateWE;
    private GUIPanelButton panelStoneWallGateNS;
    private GUIPanelButton panelStoneWallGateWE;
    private GUIPanelButton panelWoodFenceGateNS;
    private GUIPanelButton panelWoodFenceGateWE;
    private GUIPanelButton panelCrylithiumWall;
    private GUIPanelButton panelCrylithiumCurtainWall;
    private GUIPanelButton panelCurtainWall;
    private GUIPanelButton panelStoneWall;
    private GUIPanelButton panelTrashyCubeWall;
    private GUIPanelButton panelWoodFence;
    private GUIPanelButton panelCrystalHarvestry;
    private GUIPanelButton panelLumberShack;
    private GUIPanelButton panelMiningFacility;
    private GUIPanelButton panelDoggoHouse;
    private GUIPanelButton panelHousing;
    private GUIPanelButton panelCrylithiumFirePit;
    private GUIPanelButton panelLargeCrylithiumFirePit;
    private GUIPanelButton panelFirePit;
    private GUIPanelButton panelLargeFirePit;
    private GUIPanelButton panelCrystalMotivator;
    private GUIPanelButton panelCullisGate;
    private GUIPanelButton panelEssenceAltar;
    private GUIPanelButton panelEssenceCollector;
    private GUIPanelButton panelReliquary;
    private GUIPanelButton panelArmorsmithy;
    private GUIPanelButton panelBowyer;
    private GUIPanelButton panelRockTumbler;
    private GUIPanelButton panelToolsmithy;
    private GUIPanelButton panelCrystillery;
    private GUIPanelButton panelLumberMill;
    private GUIPanelButton panelStoneCuttery;
    private GUIPanelButton panelForge;
    private GUIPanelButton panelAmmoStorage;
    private GUIPanelButton panelCrystalStorage;
    private GUIPanelButton panelEquipmentStorage;
    private GUIPanelButton panelFoodStorage;
    private GUIPanelButton panelGoldStorage;
    private GUIPanelButton panelKeyShack;
    private GUIPanelButton panelMineralStorage;
    private GUIPanelButton panelMiscellaneousStorage;
    private GUIPanelButton panelRockStorage;
    private GUIPanelButton panelWoodStorage;
    private GUIPanelButton panelBurner;
    private GUIPanelButton panelCubeEGolemCombobulator;
    private GUIPanelButton panelLandfill;
    private GUIPanelButton panelProcessor;
    private GUIPanelButton panelTrashCan;
    private GUIPanelButton panelTrashyCubePile;
    private GUIPanelButton panelCamp;
    private GUIPanelButton panelLootBox;
    private GUIPanelButton panelPavePath;
    private GUIPanelButton panelPaveLogPath;
    private GUIPanelButton panelPaveCobbleAndLogPath;
    private GUIPanelButton panelPaveCobbleAndBoardRoad;
    private GUIPanelButton panelPaveCutStoneAndBoardRoad;
    private GUIPanelButton panelDigHole;
    private GUIPanelButton panelDismantleRoads;
    private GUIPanelButton panelCategoryBricks;
    private GUIPanelButton panelCategoryCrystals;
    private GUIPanelButton panelCategoryDirt;
    private GUIPanelButton panelCategoryFlowers;
    private GUIPanelButton panelCategoryFood;
    private GUIPanelButton panelCategoryGrass;
    private GUIPanelButton panelCategoryGravel;
    private GUIPanelButton panelCategoryLava;
    private GUIPanelButton panelCategoryRoads;
    private GUIPanelButton panelCategoryRock;
    private GUIPanelButton panelCategorySand;
    private GUIPanelButton panelCategorySandstone;
    private GUIPanelButton panelCategorySnow;
    private GUIPanelButton panelCategoryTar;
    private GUIPanelButton panelCategoryTrees;
    private GUIPanelButton panelCategoryWater;
    private GUIPanelButton panelGrayBricks;
    private GUIPanelButton panelGrayTiles;
    private GUIPanelButton panelRedCrystals;
    private GUIPanelButton panelGreenCrystals;
    private GUIPanelButton panelBlueCrystals;
    private GUIPanelButton panelPurpleCrystals;
    private GUIPanelButton panelBrownDirt;
    private GUIPanelButton panelDarkBrownDirt;
    private GUIPanelButton panelLightBrownDirt;
    private GUIPanelButton panelBlackFlowers;
    private GUIPanelButton panelBlueFlowers;
    private GUIPanelButton panelPurpleFlowers;
    private GUIPanelButton panelRedFlowers;
    private GUIPanelButton panelWhiteFlowers;
    private GUIPanelButton panelYellowFlowers;
    private GUIPanelButton panelCactus;
    private GUIPanelButton panelCarrots;
    private GUIPanelButton panelHolyPotatoes;
    private GUIPanelButton panelMelons;
    private GUIPanelButton panelMushrooms;
    private GUIPanelButton panelPotatoes;
    private GUIPanelButton panelTurnips;
    private GUIPanelButton panelEmeraldGreenGrass;
    private GUIPanelButton panelGreenGrass;
    private GUIPanelButton panelTealGrass;
    private GUIPanelButton panelYellowBrownGrass;
    private GUIPanelButton panelBlueGravel;
    private GUIPanelButton panelGrayGravel;
    private GUIPanelButton panelRedGravel;
    private GUIPanelButton panelLava;
    private GUIPanelButton panelPath;
    private GUIPanelButton panelLogPathDebris;
    private GUIPanelButton panelLogPath;
    private GUIPanelButton panelCobbleAndLogPathDebris;
    private GUIPanelButton panelCobbleAndLogPath;
    private GUIPanelButton panelCobbleAndBoardRoadDebris;
    private GUIPanelButton panelCobbleAndBoardRoad;
    private GUIPanelButton panelCutStoneAndBoardRoadDebris;
    private GUIPanelButton panelCutStoneAndBoardRoad;
    private GUIPanelButton panelBlackRock;
    private GUIPanelButton panelBrownRock;
    private GUIPanelButton panelGrayRock;
    private GUIPanelButton panelRedRock;
    private GUIPanelButton panelWhiteRock;
    private GUIPanelButton panelBlackSand;
    private GUIPanelButton panelRedSand;
    private GUIPanelButton panelTanSand;
    private GUIPanelButton panelBlackSandstone;
    private GUIPanelButton panelRedSandstone;
    private GUIPanelButton panelTanSandstone;
    private GUIPanelButton panelWhiteSnow;
    private GUIPanelButton panelTar;
    private GUIPanelButton panelBlueDeadTrees;
    private GUIPanelButton panelBlueStumps;
    private GUIPanelButton panelBlueTrees;
    private GUIPanelButton panelBrownDeadTrees;
    private GUIPanelButton panelBrownStumps;
    private GUIPanelButton panelBrownTrees;
    private GUIPanelButton panelDarkGreenDeadTrees;
    private GUIPanelButton panelDarkGreenStumps;
    private GUIPanelButton panelDarkGreenTrees;
    private GUIPanelButton panelGreenDeadTrees;
    private GUIPanelButton panelGreenStumps;
    private GUIPanelButton panelGreenTrees;
    private GUIPanelButton panelLavenderDeadTrees;
    private GUIPanelButton panelLavenderStumps;
    private GUIPanelButton panelLavenderTrees;
    private GUIPanelButton panelPaleBlueDeadTrees;
    private GUIPanelButton panelPaleBlueStumps;
    private GUIPanelButton panelPaleBlueTrees;
    private GUIPanelButton panelRedDeadTrees;
    private GUIPanelButton panelRedStumps;
    private GUIPanelButton panelRedTrees;
    private GUIPanelButton panelWater;
    private GUIPanelButton panelCurrentDay;
    private GUIPanelSubButton panelCurrentDayMinus;
    private GUIPanelSubButton panelCurrentDayPlus;
    private GUIPanelButton panelTimeOfDay;
    private GUIPanelSubButton panelTimeOfDayLeft;
    private GUIPanelSubButton panelTimeOfDayRight;
    private GUIPanelButton panelSeason;
    private GUIPanelSubButton panelSeasonLeft;
    private GUIPanelSubButton panelSeasonRight;
    private GUIPanelButton panelToggleTimeFrozen;
    private GUIPanelButton panelToggleTime;
    private GUIPanelButton panelAdvanceTime;
    private GUIPanelSubButton panelAdvanceTimeMinus;
    private GUIPanelSubButton panelAdvanceTimePlus;
    private GUIPanelButton panelAdvanceTimeStart;
    private GUIPanelButton panelMobType;
    private GUIPanelSubButton panelMobTypeLeft;
    private GUIPanelSubButton panelMobTypeRight;
    private GUIPanelButton panelMobAmount;
    private GUIPanelSubButton panelMobAmountMinus;
    private GUIPanelSubButton panelMobAmountPlus;
    private GUIPanelButton panelSpawnMob;
    private GUIPanelButton panelResourceType;
    private GUIPanelSubButton panelResourceTypeLeft;
    private GUIPanelSubButton panelResourceTypeRight;
    private GUIPanelButton panelResourceAmount;
    private GUIPanelSubButton panelResourceAmountMinus;
    private GUIPanelSubButton panelResourceAmountPlus;
    private GUIPanelButton panelSpawnResource;
    private GUIPanelButton panelSpawnChance;
    private GUIPanelSubButton panelSpawnChanceMinus;
    private GUIPanelSubButton panelSpawnChancePlus;
    private GUIPanelButton panelMonsterCount;
    private MobBase.MobType selectedSpawnMobType = MobBase.MobType.values()[0];
    private int selectedSpawnMobAmount = 1;
    private ResourceModule.ResourceType selectedSpawnResourceType = ResourceModule.ResourceType.values()[0];
    private int selectedSpawnResourceAmount = 1;
    private GUIPanelButton panelParticleType;
    private GUIPanelSubButton panelParticleTypeLeft;
    private GUIPanelSubButton panelParticleTypeRight;
    private GUIPanelButton panelParticleGroup;
    private GUIPanelSubButton panelParticleGroupLeft;
    private GUIPanelSubButton panelParticleGroupRight;
    private GUIPanelButton panelParticleColor;
    private GUIPanelSubButton panelParticleColorLeft;
    private GUIPanelSubButton panelParticleColorRight;
    private GUIPanelButton panelParticleAmount;
    private GUIPanelSubButton panelParticleAmountMinus;
    private GUIPanelSubButton panelParticleAmountPlus;
    private GUIPanelButton panelParticleSpread;
    private GUIPanelSubButton panelParticleSpreadMinus;
    private GUIPanelSubButton panelParticleSpreadPlus;
    private GUIPanelButton panelParticleAngle;
    private GUIPanelSubButton panelParticleAngleMinus;
    private GUIPanelSubButton panelParticleAnglePlus;
    private GUIPanelButton panelEmitSingleParticles;
    private GUIPanelButton panelEmitGroupParticles;
    private ParticleModule.ParticleType selectedSpawnParticleType = ParticleModule.ParticleType.values()[0];
    private ParticleModule.ParticleGroup selectedSpawnParticleGroup = ParticleModule.ParticleGroup.values()[0];
    private ParticleModule.ParticleSet selectedSpawnParticleColor = ParticleModule.ParticleSet.values()[0];
    private int selectedSpawnParticleAmount = 20;
    private int selectedSpawnParticleSpread = 10;
    private int selectedSpawnParticleAngle = 270;
    private GUIPanelButton panelEssenceAmount;
    private GUIPanelSubButton panelEssenceAmountMinus;
    private GUIPanelSubButton panelEssenceAmountPlus;
    private GUIPanelButton panelEssenceValue;
    private GUIPanelSubButton panelEssenceValueMinus;
    private GUIPanelSubButton panelEssenceValuePlus;
    private GUIPanelButton panelEmitEssence;
    private int spawnEssenceAmount = 5;
    private int spawnEssenceValue = 1;
    private GUIPanelButton panelToggleWeatherOn;
    private GUIPanelButton panelToggleWeatherOff;
    private GUIPanelButton panelLightning;
    private GUIPanelButton panelRestoreBasicNeeds;
    private GUIPanelButton panelRestoreHealth;
    private GUIPanelButton panelMissileType;
    private GUIPanelSubButton panelMissileTypeMinus;
    private GUIPanelSubButton panelMissileTypePlus;
    private GUIPanelButton panelFireMissile;
    private GUIPanelButton panelGenerateCloud;
    private MissileModule.MissileType missileType = MissileModule.MissileType.BASIC_ARROW;

    //Questo è il bottone del menù quadrato delle categorie, quelle come buildings e strade ecc...
    private GUISquarePanelButton modTab;

    //@@Inizio_Dichiarazione_Buttons@@

    //Questo è il bottone per le categorie delle mod, utile per distinguere le varie mod che aggiungono strutture, updatable
    private GUIPanelButton panelCategoryMod_1;

    //Questo è il bottone per le strutture aggiunte, updatable
    private GUIPanelButton panelModStructure_1_1;
    private GUIPanelButton panelModStructure_1_2;
    private GUIPanelButton panelModStructure_1_3;

    //@@Fine_Dichiarazione_Buttons@@

    public RightPanel(PlayStateGUIController controller, PlayStateGUIData guiData, Graphics g, GameContainer gc, Rectangle mouse) throws SlickException {
        super(controller, guiData, g, gc, mouse);
        this.mask = new Rectangle(0.0f, 0.0f, 190.0f, 530.0f);
        this.masks.add(this.mask);
        this.panelCurrentPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
        this.buildTab = new GUISquarePanelButton("buildTab", 0, "build");
        this.buildTab.setToolTip(Text.getText("mapEditorPlayRightPanel.button.tooltip.buildHeader"));
        this.guiButtons.add(this.buildTab);
        this.roadsAndDiggingTab = new GUISquarePanelButton("roadsAndDiggingTab", 0, "roadsAndDigging");
        this.roadsAndDiggingTab.setToolTip(Text.getText("mapEditorPlayRightPanel.button.tooltip.roadsAndDiggingHeader"));
        this.guiButtons.add(this.roadsAndDiggingTab);
        this.roadsAndDiggingTabMock = new GUISquarePanelButton("roadsAndDiggingTabMock", 0, "roadsAndDigging");
        this.roadsAndDiggingTabMock.setToolTip(Text.getText("mapEditorPlayRightPanel.button.tooltip.roadsAndDiggingHeader"), Text.getText("mapEditorPlayRightPanel.button.tooltip.roadsAndDigging"));
        this.guiButtons.add(this.roadsAndDiggingTabMock);
        this.tilesTab = new GUISquarePanelButton("tilesTab", 0, "tiles");
        this.tilesTab.setToolTip(Text.getText("mapEditorPlayRightPanel.button.tooltip.tilesHeader"));
        this.guiButtons.add(this.tilesTab);
        this.sandboxToolsTab = new GUISquarePanelButton("sandboxToolsTab", 0, "sandboxTools");
        this.sandboxToolsTab.setToolTip(Text.getText("mapEditorPlayRightPanel.button.tooltip.sandboxToolsHeader"));
        this.guiButtons.add(this.sandboxToolsTab);
        this.blankLargePanel1 = ImageLoader.getImage("res/GUI/gameState/panelLarge1.png");
        this.blankLargePanel2 = ImageLoader.getImage("res/GUI/gameState/panelLarge2.png");
        this.blankLargePanel3 = ImageLoader.getImage("res/GUI/gameState/panelLarge3.png");
        this.blankLargePanel4 = ImageLoader.getImage("res/GUI/gameState/panelLarge4.png");
        this.blankLargePanel5 = ImageLoader.getImage("res/GUI/gameState/panelLarge5.png");
        this.blankLargePanel6 = ImageLoader.getImage("res/GUI/gameState/panelLarge6.png");
        this.pageBackTop = new GUIButtonPage("pageBackTop", "back");
        this.pageBackTop.setToolTip(Text.getText("mapEditorPlayRightPanel.button.navigation.back"));
        this.guiButtons.add(this.pageBackTop);
        this.pageUpTop = new GUIButtonPage("pageUpTop", "up");
        this.pageUpTop.setToolTip(Text.getText("mapEditorPlayRightPanel.button.navigation.pageUp"));
        this.guiButtons.add(this.pageUpTop);
        this.pageDownTop = new GUIButtonPage("pageDownTop", "down");
        this.pageDownTop.setToolTip(Text.getText("mapEditorPlayRightPanel.button.navigation.pageDown"));
        this.guiButtons.add(this.pageDownTop);
        this.pageBackBottom = new GUIButtonPage("pageBackBottom", "back");
        this.pageBackBottom.setToolTip(Text.getText("mapEditorPlayRightPanel.button.navigation.back"));
        this.guiButtons.add(this.pageBackBottom);
        this.pageUpBottom = new GUIButtonPage("pageUpBottom", "up");
        this.pageUpBottom.setToolTip(Text.getText("mapEditorPlayRightPanel.button.navigation.pageUp"));
        this.guiButtons.add(this.pageUpBottom);
        this.pageDownBottom = new GUIButtonPage("pageDownBottom", "down");
        this.pageDownBottom.setToolTip(Text.getText("mapEditorPlayRightPanel.button.navigation.pageDown"));
        this.guiButtons.add(this.pageDownBottom);
        this.hidePanel = new GUIButtonHide("hidePanel", false);
        this.hidePanel.setToolTip(Text.getText("mapEditorPlayRightPanel.button.navigation.hidePanel"));
        this.guiButtons.add(this.hidePanel);
        this.panelCategoryBuild = new GUIPanelButton("panelCategoryBuild", 1, "lumberMill", Text.getText("mapEditorPlayRightPanel.button.main.build"), true);
        this.guiButtons.add(this.panelCategoryBuild);
        this.panelCategoryTiles = new GUIPanelButton("panelCategoryTiles", 1, "grayRock", Text.getText("mapEditorPlayRightPanel.button.main.tiles"), true);
        this.guiButtons.add(this.panelCategoryTiles);
        this.panelCategoryTime = new GUIPanelButton("panelCategoryTime", 0, "categoryTime", Text.getText("mapEditorPlayRightPanel.button.main.time"), true);
        this.guiButtons.add(this.panelCategoryTime);
        this.panelCategorySpawning = new GUIPanelButton("panelCategorySpawning", 0, "categorySpawning", Text.getText("mapEditorPlayRightPanel.button.main.spawning"), true);
        this.guiButtons.add(this.panelCategorySpawning);
        this.panelCategoryParticles = new GUIPanelButton("panelCategoryParticles", 0, "categoryParticles", Text.getText("mapEditorPlayRightPanel.button.main.particles"), true);
        this.guiButtons.add(this.panelCategoryParticles);
        this.panelCategoryMisc = new GUIPanelButton("panelCategoryMisc", 0, "lightning", Text.getText("mapEditorPlayRightPanel.button.main.miscellaneous"), true);
        this.guiButtons.add(this.panelCategoryMisc);
        this.panelCategoryCivics = new GUIPanelButton("panelCategoryCivics", 1, "clinic", Text.getText("mapEditorPlayRightPanel.button.build.civics"), true);
        this.guiButtons.add(this.panelCategoryCivics);
        this.panelCategoryDefense = new GUIPanelButton("panelCategoryDefense", 1, "bowTower", Text.getText("mapEditorPlayRightPanel.button.build.defense"), true);
        this.guiButtons.add(this.panelCategoryDefense);
        this.panelCategoryFoodAndWater = new GUIPanelButton("panelCategoryFoodAndWater", 1, "farm", Text.getText("mapEditorPlayRightPanel.button.build.foodAndWater"), true);
        this.guiButtons.add(this.panelCategoryFoodAndWater);
        this.panelCategoryGatesAndWalls = new GUIPanelButton("panelCategoryGatesAndWalls", 1, "curtainWall", Text.getText("mapEditorPlayRightPanel.button.build.gatesAndWalls"), true);
        this.guiButtons.add(this.panelCategoryGatesAndWalls);
        this.panelCategoryHarvesting = new GUIPanelButton("panelCategoryHarvesting", 1, "lumberShack", Text.getText("mapEditorPlayRightPanel.button.build.harvesting"), true);
        this.guiButtons.add(this.panelCategoryHarvesting);
        this.panelCategoryHousing = new GUIPanelButton("panelCategoryHousing", 1, "housing", Text.getText("mapEditorPlayRightPanel.button.build.housing"), true);
        this.guiButtons.add(this.panelCategoryHousing);
        this.panelCategoryLighting = new GUIPanelButton("panelCategoryLighting", 1, "largeFirePit", Text.getText("mapEditorPlayRightPanel.button.build.lighting"), true);
        this.guiButtons.add(this.panelCategoryLighting);
        this.panelCategoryMagic = new GUIPanelButton("panelCategoryMagic", 1, "cullisGate", Text.getText("mapEditorPlayRightPanel.button.build.magic"), true);
        this.guiButtons.add(this.panelCategoryMagic);
        this.panelCategoryManufacturing = new GUIPanelButton("panelCategoryManufacturing", 1, "bowyer", Text.getText("mapEditorPlayRightPanel.button.build.manufacturing"), true);
        this.guiButtons.add(this.panelCategoryManufacturing);
        this.panelCategoryRefining = new GUIPanelButton("panelCategoryRefining", 1, "stoneCuttery", Text.getText("mapEditorPlayRightPanel.button.build.refining"), true);
        this.guiButtons.add(this.panelCategoryRefining);
        this.panelCategoryStorage = new GUIPanelButton("panelCategoryStorage", 1, "miscellaneousStorage", Text.getText("mapEditorPlayRightPanel.button.build.storage"), true);
        this.guiButtons.add(this.panelCategoryStorage);
        this.panelCategoryTrash = new GUIPanelButton("panelCategoryTrash", 1, "landfill", Text.getText("mapEditorPlayRightPanel.button.build.trash"), true);
        this.guiButtons.add(this.panelCategoryTrash);
        this.panelCategorySpecial = new GUIPanelButton("panelCategorySpecial", 1, "camp", Text.getText("mapEditorPlayRightPanel.button.build.special"), true);
        this.guiButtons.add(this.panelCategorySpecial);
        this.panelAncillary = new GUIPanelButton("panelAncillary", 1, "ancillary", Text.getText("mapEditorPlayRightPanel.button.civics.ancillary"), false);
        this.guiButtons.add(this.panelAncillary);
        this.panelClinic = new GUIPanelButton("panelClinic", 1, "clinic", Text.getText("mapEditorPlayRightPanel.button.civics.clinic"), false);
        this.guiButtons.add(this.panelClinic);
        this.panelCourierStation = new GUIPanelButton("panelCourierStation", 1, "courierStation", Text.getText("mapEditorPlayRightPanel.button.civics.courierStation"), false);
        this.guiButtons.add(this.panelCourierStation);
        this.panelMaintenanceBuilding = new GUIPanelButton("panelMaintenanceBuilding", 1, "maintenanceBuilding", Text.getText("mapEditorPlayRightPanel.button.civics.maintenanceBuilding"), false);
        this.guiButtons.add(this.panelMaintenanceBuilding);
        this.panelMarketplace = new GUIPanelButton("panelMarketplace", 1, "marketplace", Text.getText("mapEditorPlayRightPanel.button.civics.marketplace"), false);
        this.guiButtons.add(this.panelMarketplace);
        this.panelWayMakerShack = new GUIPanelButton("panelWayMakerShack", 1, "wayMakerShack", Text.getText("mapEditorPlayRightPanel.button.civics.wayMakerShack"), false);
        this.guiButtons.add(this.panelWayMakerShack);
        this.panelMigrationWayStation = new GUIPanelButton("panelMigrationWayStation", 1, "migrationWayStation", Text.getText("mapEditorPlayRightPanel.button.civics.migrationWayStation"), false);
        this.guiButtons.add(this.panelMigrationWayStation);
        this.panelCategoryDefenseGolems = new GUIPanelButton("panelCategoryDefenseGolems", 1, "woodGolemCombobulator", Text.getText("mapEditorPlayRightPanel.button.defense.golems"), true);
        this.guiButtons.add(this.panelCategoryDefenseGolems);
        this.panelCategoryDefenseTowers = new GUIPanelButton("panelCategoryDefenseTowers", 1, "ballistaTower", Text.getText("mapEditorPlayRightPanel.button.defense.towers"), true);
        this.guiButtons.add(this.panelCategoryDefenseTowers);
        this.panelCategoryDefenseMiscellaneous = new GUIPanelButton("panelCategoryDefenseMiscellaneous", 1, "lightningRod", Text.getText("mapEditorPlayRightPanel.button.defense.miscellaneous"), true);
        this.guiButtons.add(this.panelCategoryDefenseMiscellaneous);
        this.panelCrystalGolemCombobulator = new GUIPanelButton("panelCrystalGolemCombobulator", 1, "crystalGolemCombobulator", Text.getText("mapEditorPlayRightPanel.button.golems.crystalGolemCombobulator"), false);
        this.guiButtons.add(this.panelCrystalGolemCombobulator);
        this.panelStoneGolemCombobulator = new GUIPanelButton("panelStoneGolemCombobulator", 1, "stoneGolemCombobulator", Text.getText("mapEditorPlayRightPanel.button.golems.stoneGolemCombobulator"), false);
        this.guiButtons.add(this.panelStoneGolemCombobulator);
        this.panelWoodGolemCombobulator = new GUIPanelButton("panelWoodGolemCombobulator", 1, "woodGolemCombobulator", Text.getText("mapEditorPlayRightPanel.button.golems.woodGolemCombobulator"), false);
        this.guiButtons.add(this.panelWoodGolemCombobulator);
        this.panelAttractTower = new GUIPanelButton("panelAttractTower", 1, "attractTower", Text.getText("mapEditorPlayRightPanel.button.towers.attractTower"), false);
        this.guiButtons.add(this.panelAttractTower);
        this.panelBanishTower = new GUIPanelButton("panelBanishTower", 1, "banishTower", Text.getText("mapEditorPlayRightPanel.button.towers.banishTower"), false);
        this.guiButtons.add(this.panelBanishTower);
        this.panelBallistaTower = new GUIPanelButton("panelBallistaTower", 1, "ballistaTower", Text.getText("mapEditorPlayRightPanel.button.towers.ballistaTower"), false);
        this.guiButtons.add(this.panelBallistaTower);
        this.panelBowTower = new GUIPanelButton("panelBowTower", 1, "bowTower", Text.getText("mapEditorPlayRightPanel.button.towers.bowTower"), false);
        this.guiButtons.add(this.panelBowTower);
        this.panelBulletTower = new GUIPanelButton("panelBulletTower", 1, "bulletTower", Text.getText("mapEditorPlayRightPanel.button.towers.bulletTower"), false);
        this.guiButtons.add(this.panelBulletTower);
        this.panelElementalBoltTower = new GUIPanelButton("panelElementalBoltTower", 1, "elementalBoltTower", Text.getText("mapEditorPlayRightPanel.button.towers.elementalBoltTower"), false);
        this.guiButtons.add(this.panelElementalBoltTower);
        this.panelPhantomDartTower = new GUIPanelButton("panelPhantomDartTower", 1, "phantomDartTower", Text.getText("mapEditorPlayRightPanel.button.towers.phantomDartTower"), false);
        this.guiButtons.add(this.panelPhantomDartTower);
        this.panelSlingTower = new GUIPanelButton("panelSlingTower", 1, "slingTower", Text.getText("mapEditorPlayRightPanel.button.towers.slingTower"), false);
        this.guiButtons.add(this.panelSlingTower);
        this.panelSprayTower = new GUIPanelButton("panelSprayTower", 1, "sprayTower", Text.getText("mapEditorPlayRightPanel.button.towers.sprayTower"), false);
        this.guiButtons.add(this.panelSprayTower);
        this.panelStaticTower = new GUIPanelButton("panelStaticTower", 1, "staticTower", Text.getText("mapEditorPlayRightPanel.button.towers.staticTower"), false);
        this.guiButtons.add(this.panelStaticTower);
        this.panelLightningRod = new GUIPanelButton("panelLightningRod", 1, "lightningRod", Text.getText("mapEditorPlayRightPanel.button.defenseMiscellaneous.lightningRod"), false);
        this.guiButtons.add(this.panelLightningRod);
        this.panelRecombobulatorTower = new GUIPanelButton("panelRecombobulatorTower", 1, "recombobulatorTower", Text.getText("mapEditorPlayRightPanel.button.defenseMiscellaneous.recombobulatorTower"), false);
        this.guiButtons.add(this.panelRecombobulatorTower);
        this.panelAnimalPen = new GUIPanelButton("panelAnimalPen", 1, "animalPen", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.animalPen"), false);
        this.guiButtons.add(this.panelAnimalPen);
        this.panelBottler = new GUIPanelButton("panelBottler", 1, "bottler", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.bottler"), false);
        this.guiButtons.add(this.panelBottler);
        this.panelCluckerCoop = new GUIPanelButton("panelCluckerCoop", 1, "cluckerCoop", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.cluckerCoop"), false);
        this.guiButtons.add(this.panelCluckerCoop);
        this.panelFarm = new GUIPanelButton("panelFarm", 1, "farm", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.farm"), false);
        this.guiButtons.add(this.panelFarm);
        this.panelFishingHut = new GUIPanelButton("panelFishingHut", 1, "fishingHut", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.fishingHut"), false);
        this.guiButtons.add(this.panelFishingHut);
        this.panelSmallFountain = new GUIPanelButton("panelSmallFountain", 1, "smallFountain", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.smallFountain"), false);
        this.guiButtons.add(this.panelSmallFountain);
        this.panelLargeFountain = new GUIPanelButton("panelLargeFountain", 1, "largeFountain", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.largeFountain"), false);
        this.guiButtons.add(this.panelLargeFountain);
        this.panelKitchen = new GUIPanelButton("panelKitchen", 1, "kitchen", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.kitchen"), false);
        this.guiButtons.add(this.panelKitchen);
        this.panelOutpost = new GUIPanelButton("panelOutpost", 1, "outpost", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.outpost"), false);
        this.guiButtons.add(this.panelOutpost);
        this.panelRainCatcher = new GUIPanelButton("panelRainCatcher", 1, "rainCatcher", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.rainCatcher"), false);
        this.guiButtons.add(this.panelRainCatcher);
        this.panelRangerLodge = new GUIPanelButton("panelRangerLodge", 1, "rangerLodge", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.rangerLodge"), false);
        this.guiButtons.add(this.panelRangerLodge);
        this.panelWaterPurifier = new GUIPanelButton("panelWaterPurifier", 1, "waterPurifier", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.waterPurifier"), false);
        this.guiButtons.add(this.panelWaterPurifier);
        this.panelWell = new GUIPanelButton("panelWell", 1, "well", Text.getText("mapEditorPlayRightPanel.button.foodAndWater.well"), false);
        this.guiButtons.add(this.panelWell);
        this.panelCategoryGatesAndWallsGates = new GUIPanelButton("panelCategoryGatesAndWallsGates", 1, "woodFenceGateNS", Text.getText("mapEditorPlayRightPanel.button.gatesAndWalls.gates"), false);
        this.guiButtons.add(this.panelCategoryGatesAndWallsGates);
        this.panelCategoryGatesAndWallsWalls = new GUIPanelButton("panelCategoryGatesAndWallsWalls", 1, "curtainWall", Text.getText("mapEditorPlayRightPanel.button.gatesAndWalls.walls"), false);
        this.guiButtons.add(this.panelCategoryGatesAndWallsWalls);
        this.panelCrylithiumWallGateNS = new GUIPanelButton("panelCrylithiumWallGateNS", 1, "crylithiumWallGateNS", Text.getText("mapEditorPlayRightPanel.button.gates.crylithiumWallGateNS"), false);
        this.guiButtons.add(this.panelCrylithiumWallGateNS);
        this.panelCrylithiumWallGateWE = new GUIPanelButton("panelCrylithiumWallGateWE", 1, "crylithiumWallGateWE", Text.getText("mapEditorPlayRightPanel.button.gates.crylithiumWallGateWE"), false);
        this.guiButtons.add(this.panelCrylithiumWallGateWE);
        this.panelStoneWallGateNS = new GUIPanelButton("panelStoneWallGateNS", 1, "stoneWallGateNS", Text.getText("mapEditorPlayRightPanel.button.gates.stoneWallGateNS"), false);
        this.guiButtons.add(this.panelStoneWallGateNS);
        this.panelStoneWallGateWE = new GUIPanelButton("panelStoneWallGateWE", 1, "stoneWallGateWE", Text.getText("mapEditorPlayRightPanel.button.gates.stoneWallGateWE"), false);
        this.guiButtons.add(this.panelStoneWallGateWE);
        this.panelWoodFenceGateNS = new GUIPanelButton("panelWoodFenceGateNS", 1, "woodFenceGateNS", Text.getText("mapEditorPlayRightPanel.button.gates.woodFenceGateNS"), false);
        this.guiButtons.add(this.panelWoodFenceGateNS);
        this.panelWoodFenceGateWE = new GUIPanelButton("panelWoodFenceGateWE", 1, "woodFenceGateWE", Text.getText("mapEditorPlayRightPanel.button.gates.woodFenceGateWE"), false);
        this.guiButtons.add(this.panelWoodFenceGateWE);
        this.panelCrylithiumWall = new GUIPanelButton("panelCrylithiumWall", 1, "crylithiumWall", Text.getText("mapEditorPlayRightPanel.button.walls.crylithiumWall"), false);
        this.guiButtons.add(this.panelCrylithiumWall);
        this.panelCrylithiumCurtainWall = new GUIPanelButton("panelCrylithiumCurtainWall", 1, "crylithiumCurtainWall", Text.getText("mapEditorPlayRightPanel.button.walls.crylithiumCurtainWall"), false);
        this.guiButtons.add(this.panelCrylithiumCurtainWall);
        this.panelCurtainWall = new GUIPanelButton("panelCurtainWall", 1, "curtainWall", Text.getText("mapEditorPlayRightPanel.button.walls.curtainWall"), false);
        this.guiButtons.add(this.panelCurtainWall);
        this.panelStoneWall = new GUIPanelButton("panelStoneWall", 1, "stoneWall", Text.getText("mapEditorPlayRightPanel.button.walls.stoneWall"), false);
        this.guiButtons.add(this.panelStoneWall);
        this.panelTrashyCubeWall = new GUIPanelButton("panelTrashyCubeWall", 1, "trashyCubeWall", Text.getText("mapEditorPlayRightPanel.button.walls.trashyCubeWall"), false);
        this.guiButtons.add(this.panelTrashyCubeWall);
        this.panelWoodFence = new GUIPanelButton("panelWoodFence", 1, "woodFence", Text.getText("mapEditorPlayRightPanel.button.walls.woodFence"), false);
        this.guiButtons.add(this.panelWoodFence);
        this.panelCrystalHarvestry = new GUIPanelButton("panelCrystalHarvestry", 1, "crystalHarvestry", Text.getText("mapEditorPlayRightPanel.button.harvesting.crystalHarvestry"), false);
        this.guiButtons.add(this.panelCrystalHarvestry);
        this.panelLumberShack = new GUIPanelButton("panelLumberShack", 1, "lumberShack", Text.getText("mapEditorPlayRightPanel.button.harvesting.lumberShack"), false);
        this.guiButtons.add(this.panelLumberShack);
        this.panelMiningFacility = new GUIPanelButton("panelMiningFacility", 1, "miningFacility", Text.getText("mapEditorPlayRightPanel.button.harvesting.miningFacility"), false);
        this.guiButtons.add(this.panelMiningFacility);
        this.panelDoggoHouse = new GUIPanelButton("panelDoggoHouse", 1, "doggoHouse", Text.getText("mapEditorPlayRightPanel.button.housing.doggoHouse"), false);
        this.guiButtons.add(this.panelDoggoHouse);
        this.panelHousing = new GUIPanelButton("panelHousing", 1, "housing", Text.getText("mapEditorPlayRightPanel.button.housing.housing"), false);
        this.guiButtons.add(this.panelHousing);
        this.panelCrylithiumFirePit = new GUIPanelButton("panelCrylithiumFirePit", 1, "crylithiumFirePit", Text.getText("mapEditorPlayRightPanel.button.lighting.crylithiumFirePit"), false);
        this.guiButtons.add(this.panelCrylithiumFirePit);
        this.panelLargeCrylithiumFirePit = new GUIPanelButton("panelLargeCrylithiumFirePit", 1, "largeCrylithiumFirePit", Text.getText("mapEditorPlayRightPanel.button.lighting.largeCrylithiumFirePit"), false);
        this.guiButtons.add(this.panelLargeCrylithiumFirePit);
        this.panelFirePit = new GUIPanelButton("panelFirePit", 1, "firePit", Text.getText("mapEditorPlayRightPanel.button.lighting.firePit"), false);
        this.guiButtons.add(this.panelFirePit);
        this.panelLargeFirePit = new GUIPanelButton("panelLargeFirePit", 1, "largeFirePit", Text.getText("mapEditorPlayRightPanel.button.lighting.largeFirePit"), false);
        this.guiButtons.add(this.panelLargeFirePit);
        this.panelCrystalMotivator = new GUIPanelButton("panelCrystalMotivator", 1, "crystalMotivator", Text.getText("mapEditorPlayRightPanel.button.magic.crystalMotivator"), false);
        this.guiButtons.add(this.panelCrystalMotivator);
        this.panelCullisGate = new GUIPanelButton("panelCullisGate", 1, "cullisGate", Text.getText("mapEditorPlayRightPanel.button.magic.cullisGate"), false);
        this.guiButtons.add(this.panelCullisGate);
        this.panelEssenceAltar = new GUIPanelButton("panelEssenceAltar", 1, "essenceAltar", Text.getText("mapEditorPlayRightPanel.button.magic.essenceAltar"), false);
        this.guiButtons.add(this.panelEssenceAltar);
        this.panelEssenceCollector = new GUIPanelButton("panelEssenceCollector", 1, "essenceCollector", Text.getText("mapEditorPlayRightPanel.button.magic.essenceCollector"), false);
        this.guiButtons.add(this.panelEssenceCollector);
        this.panelReliquary = new GUIPanelButton("panelReliquary", 1, "reliquary", Text.getText("mapEditorPlayRightPanel.button.magic.reliquary"), false);
        this.guiButtons.add(this.panelReliquary);
        this.panelArmorsmithy = new GUIPanelButton("panelArmorsmithy", 1, "armorsmithy", Text.getText("mapEditorPlayRightPanel.button.manufacturing.armorsmithy"), false);
        this.guiButtons.add(this.panelArmorsmithy);
        this.panelBowyer = new GUIPanelButton("panelBowyer", 1, "bowyer", Text.getText("mapEditorPlayRightPanel.button.manufacturing.bowyer"), false);
        this.guiButtons.add(this.panelBowyer);
        this.panelRockTumbler = new GUIPanelButton("panelRockTumbler", 1, "rockTumbler", Text.getText("mapEditorPlayRightPanel.button.manufacturing.rockTumbler"), false);
        this.guiButtons.add(this.panelRockTumbler);
        this.panelToolsmithy = new GUIPanelButton("panelToolsmithy", 1, "toolsmithy", Text.getText("mapEditorPlayRightPanel.button.manufacturing.toolsmithy"), false);
        this.guiButtons.add(this.panelToolsmithy);
        this.panelCrystillery = new GUIPanelButton("panelCrystillery", 1, "crystillery", Text.getText("mapEditorPlayRightPanel.button.refining.crystillery"), false);
        this.guiButtons.add(this.panelCrystillery);
        this.panelForge = new GUIPanelButton("panelForge", 1, "forge", Text.getText("mapEditorPlayRightPanel.button.refining.forge"), false);
        this.guiButtons.add(this.panelForge);
        this.panelLumberMill = new GUIPanelButton("panelLumberMill", 1, "lumberMill", Text.getText("mapEditorPlayRightPanel.button.refining.lumberMill"), false);
        this.guiButtons.add(this.panelLumberMill);
        this.panelStoneCuttery = new GUIPanelButton("panelStoneCuttery", 1, "stoneCuttery", Text.getText("mapEditorPlayRightPanel.button.refining.stoneCuttery"), false);
        this.guiButtons.add(this.panelStoneCuttery);
        this.panelAmmoStorage = new GUIPanelButton("panelAmmoStorage", 1, "ammoStorage", Text.getText("mapEditorPlayRightPanel.button.storage.ammoStorage"), false);
        this.guiButtons.add(this.panelAmmoStorage);
        this.panelCrystalStorage = new GUIPanelButton("panelCrystalStorage", 1, "crystalStorage", Text.getText("mapEditorPlayRightPanel.button.storage.crystalStorage"), false);
        this.guiButtons.add(this.panelCrystalStorage);
        this.panelEquipmentStorage = new GUIPanelButton("panelEquipmentStorage", 1, "equipmentStorage", Text.getText("mapEditorPlayRightPanel.button.storage.equipmentStorage"), false);
        this.guiButtons.add(this.panelEquipmentStorage);
        this.panelFoodStorage = new GUIPanelButton("panelFoodStorage", 1, "foodStorage", Text.getText("mapEditorPlayRightPanel.button.storage.foodStorage"), false);
        this.guiButtons.add(this.panelFoodStorage);
        this.panelGoldStorage = new GUIPanelButton("panelGoldStorage", 1, "goldStorage", Text.getText("mapEditorPlayRightPanel.button.storage.goldStorage"), false);
        this.guiButtons.add(this.panelGoldStorage);
        this.panelKeyShack = new GUIPanelButton("panelKeyShack", 1, "keyShack", Text.getText("mapEditorPlayRightPanel.button.storage.keyShack"), false);
        this.guiButtons.add(this.panelKeyShack);
        this.panelMineralStorage = new GUIPanelButton("panelMineralStorage", 1, "mineralStorage", Text.getText("mapEditorPlayRightPanel.button.storage.mineralStorage"), false);
        this.guiButtons.add(this.panelMineralStorage);
        this.panelMiscellaneousStorage = new GUIPanelButton("panelMiscellaneousStorage", 1, "miscellaneousStorage", Text.getText("mapEditorPlayRightPanel.button.storage.miscellaneousStorage"), false);
        this.guiButtons.add(this.panelMiscellaneousStorage);
        this.panelRockStorage = new GUIPanelButton("panelRockStorage", 1, "rockStorage", Text.getText("mapEditorPlayRightPanel.button.storage.rockStorage"), false);
        this.guiButtons.add(this.panelRockStorage);
        this.panelWoodStorage = new GUIPanelButton("panelWoodStorage", 1, "woodStorage", Text.getText("mapEditorPlayRightPanel.button.storage.woodStorage"), false);
        this.guiButtons.add(this.panelWoodStorage);
        this.panelBurner = new GUIPanelButton("panelBurner", 1, "burner", Text.getText("mapEditorPlayRightPanel.button.trash.burner"), false);
        this.guiButtons.add(this.panelBurner);
        this.panelCubeEGolemCombobulator = new GUIPanelButton("panelCubeEGolemCombobulator", 1, "cubeEGolemCombobulator", Text.getText("mapEditorPlayRightPanel.button.trash.cubeEGolemCombobulator"), false);
        this.guiButtons.add(this.panelCubeEGolemCombobulator);
        this.panelLandfill = new GUIPanelButton("panelLandfill", 1, "landfill", Text.getText("mapEditorPlayRightPanel.button.trash.landfill"), false);
        this.guiButtons.add(this.panelLandfill);
        this.panelProcessor = new GUIPanelButton("panelProcessor", 1, "processor", Text.getText("mapEditorPlayRightPanel.button.trash.processor"), false);
        this.guiButtons.add(this.panelProcessor);
        this.panelTrashCan = new GUIPanelButton("panelTrashCan", 1, "trashCan", Text.getText("mapEditorPlayRightPanel.button.trash.trashCan"), false);
        this.guiButtons.add(this.panelTrashCan);
        this.panelTrashyCubePile = new GUIPanelButton("panelTrashyCubePile", 1, "trashyCubePile", Text.getText("mapEditorPlayRightPanel.button.trash.trashyCubePile"), false);
        this.guiButtons.add(this.panelTrashyCubePile);
        this.panelCamp = new GUIPanelButton("panelCamp", 1, "camp", Text.getText("mapEditorPlayRightPanel.button.special.camp"), false);
        this.guiButtons.add(this.panelCamp);
        this.panelLootBox = new GUIPanelButton("panelLootBox", 1, "lootBox", Text.getText("mapEditorPlayRightPanel.button.special.lootBox"), false);
        this.guiButtons.add(this.panelLootBox);
        this.panelPavePath = new GUIPanelButton("panelPavePath", 1, "path", Text.getText("mapEditorPlayRightPanel.button.roads.path"), false);
        this.guiButtons.add(this.panelPavePath);
        this.panelPaveLogPath = new GUIPanelButton("panelPaveLogPath", 1, "logPath", Text.getText("mapEditorPlayRightPanel.button.roads.logPath"), false);
        this.guiButtons.add(this.panelPaveLogPath);
        this.panelPaveCobbleAndLogPath = new GUIPanelButton("panelPaveCobbleAndLogPath", 1, "cobbleAndLogPath", Text.getText("mapEditorPlayRightPanel.button.roads.cobbleAndLogPath"), false);
        this.guiButtons.add(this.panelPaveCobbleAndLogPath);
        this.panelPaveCobbleAndBoardRoad = new GUIPanelButton("panelPaveCobbleAndBoardRoad", 1, "cobbleAndBoardRoad", Text.getText("mapEditorPlayRightPanel.button.roads.cobbleAndBoardRoad"), false);
        this.guiButtons.add(this.panelPaveCobbleAndBoardRoad);
        this.panelPaveCutStoneAndBoardRoad = new GUIPanelButton("panelPaveCutStoneAndBoardRoad", 1, "cutStoneAndBoardRoad", Text.getText("mapEditorPlayRightPanel.button.roads.cutStoneAndBoardRoad"), false);
        this.guiButtons.add(this.panelPaveCutStoneAndBoardRoad);
        this.panelDigHole = new GUIPanelButton("panelDigHole", 0, "digHole", Text.getText("mapEditorPlayRightPanel.button.roads.digHole"), false);
        this.panelDigHole.setToolTip(Text.getText("mapEditorPlayRightPanel.button.roadsAndDigging.tooltip.digHoleHeader"), Text.getText("mapEditorPlayRightPanel.button.roadsAndDigging.tooltip.digHole"), GUIToolTipOverlay.ToolTipPosition.LEFT);
        this.guiButtons.add(this.panelDigHole);
        this.panelDismantleRoads = new GUIPanelButton("panelDismantleRoads", 0, "dismantleRoads", Text.getText("mapEditorPlayRightPanel.button.roads.dismantleRoads"), false);
        this.panelDismantleRoads.setToolTip(Text.getText("mapEditorPlayRightPanel.button.roadsAndDigging.tooltip.dismantleRoadsHeader"), Text.getText("mapEditorPlayRightPanel.button.roadsAndDigging.tooltip.dismantleRoads"), GUIToolTipOverlay.ToolTipPosition.LEFT);
        this.guiButtons.add(this.panelDismantleRoads);
        this.panelCategoryBricks = new GUIPanelButton("panelCategoryBricks", 1, "grayBricks", Text.getText("mapEditorPlayRightPanel.button.tiles.bricks"), true);
        this.guiButtons.add(this.panelCategoryBricks);
        this.panelCategoryCrystals = new GUIPanelButton("panelCategoryCrystals", 1, "blueCrystals", Text.getText("mapEditorPlayRightPanel.button.tiles.crystals"), true);
        this.guiButtons.add(this.panelCategoryCrystals);
        this.panelCategoryDirt = new GUIPanelButton("panelCategoryDirt", 1, "brownDirt", Text.getText("mapEditorPlayRightPanel.button.tiles.dirt"), true);
        this.guiButtons.add(this.panelCategoryDirt);
        this.panelCategoryFlowers = new GUIPanelButton("panelCategoryFlowers", 1, "redFlowers", Text.getText("mapEditorPlayRightPanel.button.tiles.flowers"), true);
        this.guiButtons.add(this.panelCategoryFlowers);
        this.panelCategoryFood = new GUIPanelButton("panelCategoryFood", 1, "carrots", Text.getText("mapEditorPlayRightPanel.button.tiles.food"), true);
        this.guiButtons.add(this.panelCategoryFood);
        this.panelCategoryGrass = new GUIPanelButton("panelCategoryGrass", 1, "greenGrass", Text.getText("mapEditorPlayRightPanel.button.tiles.grass"), true);
        this.guiButtons.add(this.panelCategoryGrass);
        this.panelCategoryGravel = new GUIPanelButton("panelCategoryGravel", 1, "grayGravel", Text.getText("mapEditorPlayRightPanel.button.tiles.gravel"), true);
        this.guiButtons.add(this.panelCategoryGravel);
        this.panelCategoryLava = new GUIPanelButton("panelCategoryLava", 1, "lava", Text.getText("mapEditorPlayRightPanel.button.tiles.lava"), true);
        this.guiButtons.add(this.panelCategoryLava);
        this.panelCategoryRoads = new GUIPanelButton("panelCategoryRoads", 1, "cutStoneAndBoardRoad", Text.getText("mapEditorPlayRightPanel.button.tiles.roads"), true);
        this.guiButtons.add(this.panelCategoryRoads);
        this.panelCategoryRock = new GUIPanelButton("panelCategoryRock", 1, "grayRock", Text.getText("mapEditorPlayRightPanel.button.tiles.rock"), true);
        this.guiButtons.add(this.panelCategoryRock);
        this.panelCategorySand = new GUIPanelButton("panelCategorySand", 1, "tanSand", Text.getText("mapEditorPlayRightPanel.button.tiles.sand"), true);
        this.guiButtons.add(this.panelCategorySand);
        this.panelCategorySandstone = new GUIPanelButton("panelCategorySandstone", 1, "tanSandstone", Text.getText("mapEditorPlayRightPanel.button.tiles.sandstone"), true);
        this.guiButtons.add(this.panelCategorySandstone);
        this.panelCategorySnow = new GUIPanelButton("panelCategorySnow", 1, "whiteSnow", Text.getText("mapEditorPlayRightPanel.button.tiles.snow"), true);
        this.guiButtons.add(this.panelCategorySnow);
        this.panelCategoryTar = new GUIPanelButton("panelCategoryTar", 1, "tar", Text.getText("mapEditorPlayRightPanel.button.tiles.tar"), true);
        this.guiButtons.add(this.panelCategoryTar);
        this.panelCategoryTrees = new GUIPanelButton("panelCategoryTrees", 1, "greenTrees", Text.getText("mapEditorPlayRightPanel.button.tiles.trees"), true);
        this.guiButtons.add(this.panelCategoryTrees);
        this.panelCategoryWater = new GUIPanelButton("panelCategoryWater", 1, "water", Text.getText("mapEditorPlayRightPanel.button.tiles.water"), true);
        this.guiButtons.add(this.panelCategoryWater);
        this.panelGrayBricks = new GUIPanelButton("panelGrayBricks", 1, "grayBricks", Text.getText("mapEditorPlayRightPanel.button.bricks.grayBricks"), false);
        this.guiButtons.add(this.panelGrayBricks);
        this.panelGrayTiles = new GUIPanelButton("panelGrayTiles", 1, "grayTiles", Text.getText("mapEditorPlayRightPanel.button.bricks.grayTiles"), false);
        this.guiButtons.add(this.panelGrayTiles);
        this.panelRedCrystals = new GUIPanelButton("panelRedCrystals", 1, "redCrystals", Text.getText("mapEditorPlayRightPanel.button.crystals.redCrystals"), false);
        this.guiButtons.add(this.panelRedCrystals);
        this.panelGreenCrystals = new GUIPanelButton("panelGreenCrystals", 1, "greenCrystals", Text.getText("mapEditorPlayRightPanel.button.crystals.greenCrystals"), false);
        this.guiButtons.add(this.panelGreenCrystals);
        this.panelBlueCrystals = new GUIPanelButton("panelBlueCrystals", 1, "blueCrystals", Text.getText("mapEditorPlayRightPanel.button.crystals.blueCrystals"), false);
        this.guiButtons.add(this.panelBlueCrystals);
        this.panelPurpleCrystals = new GUIPanelButton("panelPurpleCrystals", 1, "purpleCrystals", Text.getText("mapEditorPlayRightPanel.button.crystals.purpleCrystals"), false);
        this.guiButtons.add(this.panelPurpleCrystals);
        this.panelBrownDirt = new GUIPanelButton("panelBrownDirt", 1, "brownDirt", Text.getText("mapEditorPlayRightPanel.button.dirt.brownDirt"), false);
        this.guiButtons.add(this.panelBrownDirt);
        this.panelDarkBrownDirt = new GUIPanelButton("panelDarkBrownDirt", 1, "darkBrownDirt", Text.getText("mapEditorPlayRightPanel.button.dirt.darkBrownDirt"), false);
        this.guiButtons.add(this.panelDarkBrownDirt);
        this.panelLightBrownDirt = new GUIPanelButton("panelLightBrownDirt", 1, "lightBrownDirt", Text.getText("mapEditorPlayRightPanel.button.dirt.lightBrownDirt"), false);
        this.guiButtons.add(this.panelLightBrownDirt);
        this.panelBlackFlowers = new GUIPanelButton("panelBlackFlowers", 1, "blackFlowers", Text.getText("mapEditorPlayRightPanel.button.flowers.blackFlowers"), false);
        this.guiButtons.add(this.panelBlackFlowers);
        this.panelBlueFlowers = new GUIPanelButton("panelBlueFlowers", 1, "blueFlowers", Text.getText("mapEditorPlayRightPanel.button.flowers.blueFlowers"), false);
        this.guiButtons.add(this.panelBlueFlowers);
        this.panelPurpleFlowers = new GUIPanelButton("panelPurpleFlowers", 1, "purpleFlowers", Text.getText("mapEditorPlayRightPanel.button.flowers.purpleFlowers"), false);
        this.guiButtons.add(this.panelPurpleFlowers);
        this.panelRedFlowers = new GUIPanelButton("panelRedFlowers", 1, "redFlowers", Text.getText("mapEditorPlayRightPanel.button.flowers.redFlowers"), false);
        this.guiButtons.add(this.panelRedFlowers);
        this.panelWhiteFlowers = new GUIPanelButton("panelWhiteFlowers", 1, "whiteFlowers", Text.getText("mapEditorPlayRightPanel.button.flowers.whiteFlowers"), false);
        this.guiButtons.add(this.panelWhiteFlowers);
        this.panelYellowFlowers = new GUIPanelButton("panelYellowFlowers", 1, "yellowFlowers", Text.getText("mapEditorPlayRightPanel.button.flowers.yellowFlowers"), false);
        this.guiButtons.add(this.panelYellowFlowers);
        this.panelCactus = new GUIPanelButton("panelCactus", 1, "cactus", Text.getText("mapEditorPlayRightPanel.button.food.cactus"), false);
        this.guiButtons.add(this.panelCactus);
        this.panelCarrots = new GUIPanelButton("panelCarrots", 1, "carrots", Text.getText("mapEditorPlayRightPanel.button.food.carrots"), false);
        this.guiButtons.add(this.panelCarrots);
        this.panelHolyPotatoes = new GUIPanelButton("panelHolyPotatoes", 1, "holyPotatoes", Text.getText("mapEditorPlayRightPanel.button.food.holyPotatoes"), false);
        this.guiButtons.add(this.panelHolyPotatoes);
        this.panelMelons = new GUIPanelButton("panelMelons", 1, "melons", Text.getText("mapEditorPlayRightPanel.button.food.melons"), false);
        this.guiButtons.add(this.panelMelons);
        this.panelMushrooms = new GUIPanelButton("panelMushrooms", 1, "mushrooms", Text.getText("mapEditorPlayRightPanel.button.food.mushrooms"), false);
        this.guiButtons.add(this.panelMushrooms);
        this.panelPotatoes = new GUIPanelButton("panelPotatoes", 1, "potatoes", Text.getText("mapEditorPlayRightPanel.button.food.potatoes"), false);
        this.guiButtons.add(this.panelPotatoes);
        this.panelTurnips = new GUIPanelButton("panelTurnips", 1, "turnips", Text.getText("mapEditorPlayRightPanel.button.food.turnips"), false);
        this.guiButtons.add(this.panelTurnips);
        this.panelEmeraldGreenGrass = new GUIPanelButton("panelEmeraldGreenGrass", 1, "emeraldGreenGrass", Text.getText("mapEditorPlayRightPanel.button.grass.emeraldGreenGrass"), false);
        this.guiButtons.add(this.panelEmeraldGreenGrass);
        this.panelGreenGrass = new GUIPanelButton("panelGreenGrass", 1, "greenGrass", Text.getText("mapEditorPlayRightPanel.button.grass.greenGrass"), false);
        this.guiButtons.add(this.panelGreenGrass);
        this.panelTealGrass = new GUIPanelButton("panelTealGrass", 1, "tealGrass", Text.getText("mapEditorPlayRightPanel.button.grass.tealGrass"), false);
        this.guiButtons.add(this.panelTealGrass);
        this.panelYellowBrownGrass = new GUIPanelButton("panelYellowBrownGrass", 1, "yellowBrownGrass", Text.getText("mapEditorPlayRightPanel.button.grass.yellowBrownGrass"), false);
        this.guiButtons.add(this.panelYellowBrownGrass);
        this.panelBlueGravel = new GUIPanelButton("panelBlueGravel", 1, "blueGravel", Text.getText("mapEditorPlayRightPanel.button.gravel.blueGravel"), false);
        this.guiButtons.add(this.panelBlueGravel);
        this.panelGrayGravel = new GUIPanelButton("panelGrayGravel", 1, "grayGravel", Text.getText("mapEditorPlayRightPanel.button.gravel.grayGravel"), false);
        this.guiButtons.add(this.panelGrayGravel);
        this.panelRedGravel = new GUIPanelButton("panelRedGravel", 1, "redGravel", Text.getText("mapEditorPlayRightPanel.button.gravel.redGravel"), false);
        this.guiButtons.add(this.panelRedGravel);
        this.panelLava = new GUIPanelButton("panelLava", 1, "lava", Text.getText("mapEditorPlayRightPanel.button.lava.lava"), false);
        this.guiButtons.add(this.panelLava);
        this.panelPath = new GUIPanelButton("panelPath", 1, "path", Text.getText("mapEditorPlayRightPanel.button.roads.path"), false);
        this.guiButtons.add(this.panelPath);
        this.panelLogPathDebris = new GUIPanelButton("panelLogPathDebris", 1, "logPathDebris", Text.getText("mapEditorPlayRightPanel.button.roads.logPathDebris"), false);
        this.guiButtons.add(this.panelLogPathDebris);
        this.panelLogPath = new GUIPanelButton("panelLogPath", 1, "logPath", Text.getText("mapEditorPlayRightPanel.button.roads.logPath"), false);
        this.guiButtons.add(this.panelLogPath);
        this.panelCobbleAndLogPathDebris = new GUIPanelButton("panelCobbleAndLogPathDebris", 1, "cobbleAndLogPathDebris", Text.getText("mapEditorPlayRightPanel.button.roads.cobbleAndLogPathDebris"), false);
        this.guiButtons.add(this.panelCobbleAndLogPathDebris);
        this.panelCobbleAndLogPath = new GUIPanelButton("panelCobbleAndLogPath", 1, "cobbleAndLogPath", Text.getText("mapEditorPlayRightPanel.button.roads.cobbleAndLogPath"), false);
        this.guiButtons.add(this.panelCobbleAndLogPath);
        this.panelCobbleAndBoardRoadDebris = new GUIPanelButton("panelCobbleAndBoardRoadDebris", 1, "cobbleAndBoardRoadDebris", Text.getText("mapEditorPlayRightPanel.button.roads.cobbleAndBoardRoadDebris"), false);
        this.guiButtons.add(this.panelCobbleAndBoardRoadDebris);
        this.panelCobbleAndBoardRoad = new GUIPanelButton("panelCobbleAndBoardRoad", 1, "cobbleAndBoardRoad", Text.getText("mapEditorPlayRightPanel.button.roads.cobbleAndBoardRoad"), false);
        this.guiButtons.add(this.panelCobbleAndBoardRoad);
        this.panelCutStoneAndBoardRoadDebris = new GUIPanelButton("panelCutStoneAndBoardRoadDebris", 1, "cutStoneAndBoardRoadDebris", Text.getText("mapEditorPlayRightPanel.button.roads.cutStoneAndBoardRoadDebris"), false);
        this.guiButtons.add(this.panelCutStoneAndBoardRoadDebris);
        this.panelCutStoneAndBoardRoad = new GUIPanelButton("panelCutStoneAndBoardRoad", 1, "cutStoneAndBoardRoad", Text.getText("mapEditorPlayRightPanel.button.roads.cutStoneAndBoardRoad"), false);
        this.guiButtons.add(this.panelCutStoneAndBoardRoad);
        this.panelBlackRock = new GUIPanelButton("panelBlackRock", 1, "blackRock", Text.getText("mapEditorPlayRightPanel.button.rock.blackRock"), false);
        this.guiButtons.add(this.panelBlackRock);
        this.panelBrownRock = new GUIPanelButton("panelBrownRock", 1, "brownRock", Text.getText("mapEditorPlayRightPanel.button.rock.brownRock"), false);
        this.guiButtons.add(this.panelBrownRock);
        this.panelGrayRock = new GUIPanelButton("panelGrayRock", 1, "grayRock", Text.getText("mapEditorPlayRightPanel.button.rock.grayRock"), false);
        this.guiButtons.add(this.panelGrayRock);
        this.panelRedRock = new GUIPanelButton("panelRedRock", 1, "redRock", Text.getText("mapEditorPlayRightPanel.button.rock.redRock"), false);
        this.guiButtons.add(this.panelRedRock);
        this.panelWhiteRock = new GUIPanelButton("panelWhiteRock", 1, "whiteRock", Text.getText("mapEditorPlayRightPanel.button.rock.whiteRock"), false);
        this.guiButtons.add(this.panelWhiteRock);
        this.panelBlackSand = new GUIPanelButton("panelBlackSand", 1, "blackSand", Text.getText("mapEditorPlayRightPanel.button.sand.blackSand"), false);
        this.guiButtons.add(this.panelBlackSand);
        this.panelRedSand = new GUIPanelButton("panelRedSand", 1, "redSand", Text.getText("mapEditorPlayRightPanel.button.sand.redSand"), false);
        this.guiButtons.add(this.panelRedSand);
        this.panelTanSand = new GUIPanelButton("panelTanSand", 1, "tanSand", Text.getText("mapEditorPlayRightPanel.button.sand.tanSand"), false);
        this.guiButtons.add(this.panelTanSand);
        this.panelBlackSandstone = new GUIPanelButton("panelBlackSandstone", 1, "blackSandstone", Text.getText("mapEditorPlayRightPanel.button.sandstone.blackSandstone"), false);
        this.guiButtons.add(this.panelBlackSandstone);
        this.panelRedSandstone = new GUIPanelButton("panelRedSandstone", 1, "redSandstone", Text.getText("mapEditorPlayRightPanel.button.sandstone.redSandstone"), false);
        this.guiButtons.add(this.panelRedSandstone);
        this.panelTanSandstone = new GUIPanelButton("panelTanSandstone", 1, "tanSandstone", Text.getText("mapEditorPlayRightPanel.button.sandstone.tanSandstone"), false);
        this.guiButtons.add(this.panelTanSandstone);
        this.panelWhiteSnow = new GUIPanelButton("panelWhiteSnow", 1, "whiteSnow", Text.getText("mapEditorPlayRightPanel.button.snow.whiteSnow"), false);
        this.guiButtons.add(this.panelWhiteSnow);
        this.panelTar = new GUIPanelButton("panelTar", 1, "tar", Text.getText("mapEditorPlayRightPanel.button.tar.tar"), false);
        this.guiButtons.add(this.panelTar);
        this.panelBlueDeadTrees = new GUIPanelButton("panelBlueDeadTrees", 1, "blueDeadTrees", Text.getText("mapEditorPlayRightPanel.button.trees.blueDeadTrees"), false);
        this.guiButtons.add(this.panelBlueDeadTrees);
        this.panelBlueStumps = new GUIPanelButton("panelBlueStumps", 1, "blueStumps", Text.getText("mapEditorPlayRightPanel.button.trees.blueStumps"), false);
        this.guiButtons.add(this.panelBlueStumps);
        this.panelBlueTrees = new GUIPanelButton("panelBlueTrees", 1, "blueTrees", Text.getText("mapEditorPlayRightPanel.button.trees.blueTrees"), false);
        this.guiButtons.add(this.panelBlueTrees);
        this.panelBrownDeadTrees = new GUIPanelButton("panelBrownDeadTrees", 1, "brownDeadTrees", Text.getText("mapEditorPlayRightPanel.button.trees.brownDeadTrees"), false);
        this.guiButtons.add(this.panelBrownDeadTrees);
        this.panelBrownStumps = new GUIPanelButton("panelBrownStumps", 1, "brownStumps", Text.getText("mapEditorPlayRightPanel.button.trees.brownStumps"), false);
        this.guiButtons.add(this.panelBrownStumps);
        this.panelBrownTrees = new GUIPanelButton("panelBrownTrees", 1, "brownTrees", Text.getText("mapEditorPlayRightPanel.button.trees.brownTrees"), false);
        this.guiButtons.add(this.panelBrownTrees);
        this.panelDarkGreenDeadTrees = new GUIPanelButton("panelDarkGreenDeadTrees", 1, "darkGreenDeadTrees", Text.getText("mapEditorPlayRightPanel.button.trees.darkGreenDeadTrees"), false);
        this.guiButtons.add(this.panelDarkGreenDeadTrees);
        this.panelDarkGreenStumps = new GUIPanelButton("panelDarkGreenStumps", 1, "darkGreenStumps", Text.getText("mapEditorPlayRightPanel.button.trees.darkGreenStumps"), false);
        this.guiButtons.add(this.panelDarkGreenStumps);
        this.panelDarkGreenTrees = new GUIPanelButton("panelDarkGreenTrees", 1, "darkGreenTrees", Text.getText("mapEditorPlayRightPanel.button.trees.darkGreenTrees"), false);
        this.guiButtons.add(this.panelDarkGreenTrees);
        this.panelGreenDeadTrees = new GUIPanelButton("panelGreenDeadTrees", 1, "greenDeadTrees", Text.getText("mapEditorPlayRightPanel.button.trees.greenDeadTrees"), false);
        this.guiButtons.add(this.panelGreenDeadTrees);
        this.panelGreenStumps = new GUIPanelButton("panelGreenStumps", 1, "greenStumps", Text.getText("mapEditorPlayRightPanel.button.trees.greenStumps"), false);
        this.guiButtons.add(this.panelGreenStumps);
        this.panelGreenTrees = new GUIPanelButton("panelGreenTrees", 1, "greenTrees", Text.getText("mapEditorPlayRightPanel.button.trees.greenTrees"), false);
        this.guiButtons.add(this.panelGreenTrees);
        this.panelLavenderDeadTrees = new GUIPanelButton("panelLavenderDeadTrees", 1, "lavenderDeadTrees", Text.getText("mapEditorPlayRightPanel.button.trees.lavenderDeadTrees"), false);
        this.guiButtons.add(this.panelLavenderDeadTrees);
        this.panelLavenderStumps = new GUIPanelButton("panelLavenderStumps", 1, "lavenderStumps", Text.getText("mapEditorPlayRightPanel.button.trees.lavenderStumps"), false);
        this.guiButtons.add(this.panelLavenderStumps);
        this.panelLavenderTrees = new GUIPanelButton("panelLavenderTrees", 1, "lavenderTrees", Text.getText("mapEditorPlayRightPanel.button.trees.lavenderTrees"), false);
        this.guiButtons.add(this.panelLavenderTrees);
        this.panelPaleBlueDeadTrees = new GUIPanelButton("panelPaleBlueDeadTrees", 1, "paleBlueDeadTrees", Text.getText("mapEditorPlayRightPanel.button.trees.paleBlueDeadTrees"), false);
        this.guiButtons.add(this.panelPaleBlueDeadTrees);
        this.panelPaleBlueStumps = new GUIPanelButton("panelPaleBlueStumps", 1, "paleBlueStumps", Text.getText("mapEditorPlayRightPanel.button.trees.paleBlueStumps"), false);
        this.guiButtons.add(this.panelPaleBlueStumps);
        this.panelPaleBlueTrees = new GUIPanelButton("panelPaleBlueTrees", 1, "paleBlueTrees", Text.getText("mapEditorPlayRightPanel.button.trees.paleBlueTrees"), false);
        this.guiButtons.add(this.panelPaleBlueTrees);
        this.panelRedDeadTrees = new GUIPanelButton("panelRedDeadTrees", 1, "redDeadTrees", Text.getText("mapEditorPlayRightPanel.button.trees.redDeadTrees"), false);
        this.guiButtons.add(this.panelRedDeadTrees);
        this.panelRedStumps = new GUIPanelButton("panelRedStumps", 1, "redStumps", Text.getText("mapEditorPlayRightPanel.button.trees.redStumps"), false);
        this.guiButtons.add(this.panelRedStumps);
        this.panelRedTrees = new GUIPanelButton("panelRedTrees", 1, "redTrees", Text.getText("mapEditorPlayRightPanel.button.trees.redTrees"), false);
        this.guiButtons.add(this.panelRedTrees);
        this.panelWater = new GUIPanelButton("panelWater", 1, "water", Text.getText("mapEditorPlayRightPanel.button.water.water"), false);
        this.guiButtons.add(this.panelWater);
        this.panelCurrentDay = new GUIPanelButton("panelCurrentDay", 0, Text.getText("mapEditorPlayRightPanel.header.time.currentDay"));
        this.guiButtons.add(this.panelCurrentDay);
        this.panelCurrentDayMinus = new GUIPanelSubButton("panelCurrentDayMinus", "minus");
        this.guiButtons.add(this.panelCurrentDayMinus);
        this.panelCurrentDayPlus = new GUIPanelSubButton("panelCurrentDayPlus", "plus");
        this.guiButtons.add(this.panelCurrentDayPlus);
        this.panelTimeOfDay = new GUIPanelButton("panelTimeOfDay", 0, Text.getText("mapEditorPlayRightPanel.header.time.timeOfDay"));
        this.guiButtons.add(this.panelTimeOfDay);
        this.panelTimeOfDayLeft = new GUIPanelSubButton("panelTimeOfDayLeft", "left");
        this.guiButtons.add(this.panelTimeOfDayLeft);
        this.panelTimeOfDayRight = new GUIPanelSubButton("panelTimeOfDayRight", "right");
        this.guiButtons.add(this.panelTimeOfDayRight);
        this.panelSeason = new GUIPanelButton("panelSeason", 0, Text.getText("mapEditorPlayRightPanel.header.time.season"));
        this.guiButtons.add(this.panelSeason);
        this.panelSeasonLeft = new GUIPanelSubButton("panelSeasonLeft", "left");
        this.guiButtons.add(this.panelSeasonLeft);
        this.panelSeasonRight = new GUIPanelSubButton("panelSeasonRight", "right");
        this.guiButtons.add(this.panelSeasonRight);
        this.panelToggleTimeFrozen = new GUIPanelButton("panelToggleTimeFrozen", 0, "timeFrozen", Text.getText("mapEditorPlayRightPanel.button.time.toggleTime"), false);
        this.guiButtons.add(this.panelToggleTimeFrozen);
        this.panelToggleTime = new GUIPanelButton("panelToggleTime", 0, "time", Text.getText("mapEditorPlayRightPanel.button.time.toggleTime"), false);
        this.guiButtons.add(this.panelToggleTime);
        this.panelAdvanceTime = new GUIPanelButton("panelAdvanceTime", 1, Text.getText("mapEditorPlayRightPanel.header.time.advanceTime"));
        this.guiButtons.add(this.panelAdvanceTime);
        this.panelAdvanceTimeMinus = new GUIPanelSubButton("panelAdvanceTimeMinus", "minus");
        this.guiButtons.add(this.panelAdvanceTimeMinus);
        this.panelAdvanceTimePlus = new GUIPanelSubButton("panelAdvanceTimePlus", "plus");
        this.guiButtons.add(this.panelAdvanceTimePlus);
        this.panelAdvanceTimeStart = new GUIPanelButton("panelAdvanceTimeStart", 1, Text.getText("mapEditorPlayRightPanel.header.time.startAdvanceTime"));
        this.guiButtons.add(this.panelAdvanceTimeStart);
        this.panelMobType = new GUIPanelButton("panelMobType", 0, Text.getText("mapEditorPlayRightPanel.header.spawning.mobType"));
        this.guiButtons.add(this.panelMobType);
        this.panelMobTypeLeft = new GUIPanelSubButton("panelMobTypeLeft", "left");
        this.guiButtons.add(this.panelMobTypeLeft);
        this.panelMobTypeRight = new GUIPanelSubButton("panelMobTypeRight", "right");
        this.guiButtons.add(this.panelMobTypeRight);
        this.panelMobAmount = new GUIPanelButton("panelMobAmount", 0, Text.getText("mapEditorPlayRightPanel.header.spawning.mobAmount"));
        this.guiButtons.add(this.panelMobAmount);
        this.panelMobAmountMinus = new GUIPanelSubButton("panelMobAmountMinus", "minus");
        this.guiButtons.add(this.panelMobAmountMinus);
        this.panelMobAmountPlus = new GUIPanelSubButton("panelMobAmountPlus", "plus");
        this.guiButtons.add(this.panelMobAmountPlus);
        this.panelSpawnMob = new GUIPanelButton("panelSpawnMob", 1, "spawnMob", Text.getText("mapEditorPlayRightPanel.button.spawning.spawnMob"), false);
        this.guiButtons.add(this.panelSpawnMob);
        this.panelResourceType = new GUIPanelButton("panelResourceType", 0, Text.getText("mapEditorPlayRightPanel.header.spawning.resourceType"));
        this.guiButtons.add(this.panelResourceType);
        this.panelResourceTypeLeft = new GUIPanelSubButton("panelResourceTypeLeft", "left");
        this.guiButtons.add(this.panelResourceTypeLeft);
        this.panelResourceTypeRight = new GUIPanelSubButton("panelResourceTypeRight", "right");
        this.guiButtons.add(this.panelResourceTypeRight);
        this.panelResourceAmount = new GUIPanelButton("panelResourceAmount", 0, Text.getText("mapEditorPlayRightPanel.header.spawning.resourceAmount"));
        this.guiButtons.add(this.panelResourceAmount);
        this.panelResourceAmountMinus = new GUIPanelSubButton("panelResourceAmountMinus", "minus");
        this.guiButtons.add(this.panelResourceAmountMinus);
        this.panelResourceAmountPlus = new GUIPanelSubButton("panelResourceAmountPlus", "plus");
        this.guiButtons.add(this.panelResourceAmountPlus);
        this.panelSpawnResource = new GUIPanelButton("panelSpawnResource", 1, "spawnResource", Text.getText("mapEditorPlayRightPanel.button.spawning.spawnResource"), false);
        this.guiButtons.add(this.panelSpawnResource);
        this.panelSpawnChance = new GUIPanelButton("panelSpawnChance", 0, Text.getText("mapEditorPlayRightPanel.header.spawning.spawnChance"));
        this.guiButtons.add(this.panelSpawnChance);
        this.panelSpawnChanceMinus = new GUIPanelSubButton("panelSpawnChanceMinus", "minus");
        this.guiButtons.add(this.panelSpawnChanceMinus);
        this.panelSpawnChancePlus = new GUIPanelSubButton("panelSpawnChancePlus", "plus");
        this.guiButtons.add(this.panelSpawnChancePlus);
        this.panelMonsterCount = new GUIPanelButton("panelMonsterCount", 1, Text.getText("mapEditorPlayRightPanel.header.spawning.monsterCount"));
        this.guiButtons.add(this.panelMonsterCount);
        this.panelParticleType = new GUIPanelButton("panelParticleType", 0, Text.getText("mapEditorPlayRightPanel.header.particles.particleType"));
        this.guiButtons.add(this.panelParticleType);
        this.panelParticleTypeLeft = new GUIPanelSubButton("panelParticleTypeLeft", "left");
        this.guiButtons.add(this.panelParticleTypeLeft);
        this.panelParticleTypeRight = new GUIPanelSubButton("panelParticleTypeRight", "right");
        this.guiButtons.add(this.panelParticleTypeRight);
        this.panelParticleGroup = new GUIPanelButton("panelParticleGroup", 0, Text.getText("mapEditorPlayRightPanel.header.particles.particleGroup"));
        this.guiButtons.add(this.panelParticleGroup);
        this.panelParticleGroupLeft = new GUIPanelSubButton("panelParticleGroupLeft", "left");
        this.guiButtons.add(this.panelParticleGroupLeft);
        this.panelParticleGroupRight = new GUIPanelSubButton("panelParticleGroupRight", "right");
        this.guiButtons.add(this.panelParticleGroupRight);
        this.panelParticleColor = new GUIPanelButton("panelParticleColor", 1, Text.getText("mapEditorPlayRightPanel.header.particles.particleColor"));
        this.guiButtons.add(this.panelParticleColor);
        this.panelParticleColorLeft = new GUIPanelSubButton("panelParticleColorLeft", "left");
        this.guiButtons.add(this.panelParticleColorLeft);
        this.panelParticleColorRight = new GUIPanelSubButton("panelParticleColorRight", "right");
        this.guiButtons.add(this.panelParticleColorRight);
        this.panelParticleAmount = new GUIPanelButton("panelParticleAmount", 1, Text.getText("mapEditorPlayRightPanel.header.particles.particleAmount"));
        this.guiButtons.add(this.panelParticleAmount);
        this.panelParticleAmountMinus = new GUIPanelSubButton("panelParticleAmountMinus", "minus");
        this.guiButtons.add(this.panelParticleAmountMinus);
        this.panelParticleAmountPlus = new GUIPanelSubButton("panelParticleAmountPlus", "plus");
        this.guiButtons.add(this.panelParticleAmountPlus);
        this.panelParticleSpread = new GUIPanelButton("panelParticleSpread", 0, Text.getText("mapEditorPlayRightPanel.header.particles.particleSpread"));
        this.guiButtons.add(this.panelParticleSpread);
        this.panelParticleSpreadMinus = new GUIPanelSubButton("panelParticleSpreadMinus", "minus");
        this.guiButtons.add(this.panelParticleSpreadMinus);
        this.panelParticleSpreadPlus = new GUIPanelSubButton("panelParticleSpreadPlus", "plus");
        this.guiButtons.add(this.panelParticleSpreadPlus);
        this.panelParticleAngle = new GUIPanelButton("panelParticleAngle", 0, Text.getText("mapEditorPlayRightPanel.header.particles.particleAngle"));
        this.guiButtons.add(this.panelParticleAngle);
        this.panelParticleAngleMinus = new GUIPanelSubButton("panelParticleAngleMinus", "minus");
        this.guiButtons.add(this.panelParticleAngleMinus);
        this.panelParticleAnglePlus = new GUIPanelSubButton("panelParticleAnglePlus", "plus");
        this.guiButtons.add(this.panelParticleAnglePlus);
        this.panelEmitSingleParticles = new GUIPanelButton("panelEmitSingleParticles", 0, "emitParticles", Text.getText("mapEditorPlayRightPanel.button.particles.emitSingleParticles"), false);
        this.guiButtons.add(this.panelEmitSingleParticles);
        this.panelEmitGroupParticles = new GUIPanelButton("panelEmitGroupParticles", 0, "emitParticles", Text.getText("mapEditorPlayRightPanel.button.particles.emitGroupParticles"), false);
        this.guiButtons.add(this.panelEmitGroupParticles);
        this.panelEssenceAmount = new GUIPanelButton("panelEssenceAmount", 0, Text.getText("mapEditorPlayRightPanel.header.miscellaneous.essenceAmount"));
        this.guiButtons.add(this.panelEssenceAmount);
        this.panelEssenceAmountMinus = new GUIPanelSubButton("panelEssenceAmountMinus", "minus");
        this.guiButtons.add(this.panelEssenceAmountMinus);
        this.panelEssenceAmountPlus = new GUIPanelSubButton("panelEssenceAmountPlus", "plus");
        this.guiButtons.add(this.panelEssenceAmountPlus);
        this.panelEssenceValue = new GUIPanelButton("panelEssenceValue", 0, Text.getText("mapEditorPlayRightPanel.header.miscellaneous.essenceValue"));
        this.guiButtons.add(this.panelEssenceValue);
        this.panelEssenceValueMinus = new GUIPanelSubButton("panelEssenceValueMinus", "minus");
        this.guiButtons.add(this.panelEssenceValueMinus);
        this.panelEssenceValuePlus = new GUIPanelSubButton("panelEssenceValuePlus", "plus");
        this.guiButtons.add(this.panelEssenceValuePlus);
        this.panelEmitEssence = new GUIPanelButton("panelEmitEssence", 1, "emitEssence", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.emitEssence"), false);
        this.guiButtons.add(this.panelEmitEssence);
        this.panelToggleWeatherOn = new GUIPanelButton("panelToggleWeatherOn", 0, "toggleWeatherOn", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.toggleWeather"), false);
        this.guiButtons.add(this.panelToggleWeatherOn);
        this.panelToggleWeatherOff = new GUIPanelButton("panelToggleWeatherOff", 0, "toggleWeatherOff", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.toggleWeather"), false);
        this.guiButtons.add(this.panelToggleWeatherOff);
        this.panelLightning = new GUIPanelButton("panelLightning", 0, "lightning", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.lightning"), false);
        this.guiButtons.add(this.panelLightning);
        this.panelRestoreBasicNeeds = new GUIPanelButton("panelRestoreBasicNeeds", 0, "restoreBasicNeeds", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.restoreBasicNeeds"), false);
        this.guiButtons.add(this.panelRestoreBasicNeeds);
        this.panelRestoreHealth = new GUIPanelButton("panelRestoreHealth", 0, "restoreHealth", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.restoreHealth"), false);
        this.guiButtons.add(this.panelRestoreHealth);
        this.panelMissileType = new GUIPanelButton("panelMissileType", 0, Text.getText("mapEditorPlayRightPanel.header.miscellaneous.missileType"));
        this.guiButtons.add(this.panelMissileType);
        this.panelMissileTypeMinus = new GUIPanelSubButton("panelMissileTypeMinus", "minus");
        this.guiButtons.add(this.panelMissileTypeMinus);
        this.panelMissileTypePlus = new GUIPanelSubButton("panelMissileTypePlus", "plus");
        this.guiButtons.add(this.panelMissileTypePlus);
        this.panelFireMissile = new GUIPanelButton("panelFireMissile", 0, "fireMissile", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.fireMissile"), false);
        this.guiButtons.add(this.panelFireMissile);
        this.panelGenerateCloud = new GUIPanelButton("panelGenerateCloud", 1, "generateCloud", Text.getText("mapEditorPlayRightPanel.button.miscellaneous.generateCloud"), false);
        this.guiButtons.add(this.panelGenerateCloud);
    //Crea gli effettivi button

        //Qui quello della categoria generale
        this.modTab = new GUISquarePanelButton("modTab", 0, "mod");
        this.modTab.setToolTip(Text.getText("Mod Structures"));
        this.guiButtons.add(this.modTab);

        //@@Inizio_UpdatePreRender_Buttons@@

        //Qui quello della categoria specifica per mod, updatabile
        this.panelCategoryMod_1 = new GUIPanelButton("panelCategoryMod_1", 1, "modlongpanel", Text.getText("More Motivation"), true);
        this.guiButtons.add(this.panelCategoryMod_1);

        //Qui quello delle strutture aggiuntive, updatabile
        this.panelModStructure_1_1 = new GUIPanelButton("panelModStructure_1_1", 1, "modlongpanel", Text.getText("Wood Motivator"), false);
        this.guiButtons.add(this.panelModStructure_1_1);
        this.panelModStructure_1_2 = new GUIPanelButton("panelModStructure_1_2", 1, "modlongpanel", Text.getText("Rock Motivator"), false);
        this.guiButtons.add(this.panelModStructure_1_2);
        this.panelModStructure_1_3 = new GUIPanelButton("panelModStructure_1_3", 1, "modlongpanel", Text.getText("Vegetable Motivator"), false);
        this.guiButtons.add(this.panelModStructure_1_3);

        //@@Fine_UpdatePreRender_Buttons@@

    }

    public void render(boolean debug) throws SlickException {
        boolean canBuildRoads;
        this.x = ScaleControl.getInterfaceWidth() - 196;
        this.y = ScaleControl.getInterfaceCenterY() - 265;
        if (this.y < 134) {
            this.y = 134;
        }
        if (this.hidden) {
            this.x = ScaleControl.getInterfaceWidth() - 10;
        }
        this.mask.setX(this.x);
        this.mask.setY(this.y);
        if (this.hidden) {
            this.hidePanel.render(this.g, this.mouse, this.x - 28, this.y, false, debug);
        } else {
            this.hidePanel.render(this.g, this.mouse, this.x + 162, this.y, false, debug);
        }
        if (this.backPage == null) {
            this.pageBackTop.render(this.g, this.mouse, this.x, this.y, true, debug);
        } else {
            this.pageBackTop.render(this.g, this.mouse, this.x, this.y, false, debug);
        }
        if (this.lastPage == null) {
            this.pageUpTop.render(this.g, this.mouse, this.x + 67, this.y, true, debug);
        } else {
            this.pageUpTop.render(this.g, this.mouse, this.x + 67, this.y, false, debug);
        }
        if (this.nextPage == null) {
            this.pageDownTop.render(this.g, this.mouse, this.x + 96, this.y, true, debug);
        } else {
            this.pageDownTop.render(this.g, this.mouse, this.x + 96, this.y, false, debug);
        }
        int yShift = 0;
        this.buildTab.render(this.g, this.mouse, this.x - 46, this.y + 28 + yShift, false, debug);
        yShift += this.buildTab.getHeight();
        boolean bl = canBuildRoads = this.object.objectCount(MapTilesLoader.TileSet.WAY_MAKER_SHACK, ObjectBase.ObjectSubType.BUILT) > 0 || this.object.objectCount(MapTilesLoader.TileSet.WAY_MAKER_SHACK, ObjectBase.ObjectSubType.CONSTRUCTION) > 0;
        if (!canBuildRoads) {
            this.roadsAndDiggingTabMock.render(this.g, this.mouse, this.x - 46, this.y + 28 + yShift, true, debug);
        } else {
            this.roadsAndDiggingTab.render(this.g, this.mouse, this.x - 46, this.y + 28 + yShift, false, debug);
        }
        yShift += this.roadsAndDiggingTab.getHeight();

        //Qui il render del quadrato per selezionare la categoria delle strutture moddate
        this.modTab.render(this.g, this.mouse, this.x - 46, this.y + 28 + yShift, false, debug);
        yShift += this.modTab.getHeight();

        if (this.save.getActiveRegionalSave().getGameMode() == GameModeTemplateBase.GameMode.SANDBOX) {
            this.tilesTab.render(this.g, this.mouse, this.x - 46, this.y + 28 + yShift, false, debug);
            this.sandboxToolsTab.render(this.g, this.mouse, this.x - 46, this.y + 28 + (yShift += this.tilesTab.getHeight()), false, debug);
            yShift += this.sandboxToolsTab.getHeight();
        }
        yShift = 0;
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.backPage = null;
            this.panelCategoryCivics.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategoryDefense.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryCivics.getHeight()), false, debug);
            this.panelCategoryFoodAndWater.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryDefense.getHeight()), false, debug);
            this.panelCategoryGatesAndWalls.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryFoodAndWater.getHeight()), false, debug);
            this.panelCategoryHarvesting.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryGatesAndWalls.getHeight()), false, debug);
            this.panelCategoryHousing.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryHarvesting.getHeight()), false, debug);
            yShift += this.panelCategoryHousing.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
            this.nextPage = this.save.getActiveRegionalSave().getGameMode() == GameModeTemplateBase.GameMode.SANDBOX ? GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_3 : null;
            this.backPage = null;
            this.panelCategoryLighting.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategoryMagic.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryLighting.getHeight()), false, debug);
            this.panelCategoryManufacturing.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryMagic.getHeight()), false, debug);
            this.panelCategoryRefining.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryManufacturing.getHeight()), false, debug);
            this.panelCategoryStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryRefining.getHeight()), false, debug);
            this.panelCategoryTrash.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryStorage.getHeight()), false, debug);
            yShift += this.panelCategoryTrash.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_3) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.nextPage = null;
            this.backPage = null;
            if (this.save.getActiveRegionalSave().getGameMode() == GameModeTemplateBase.GameMode.SANDBOX) {
                this.panelCategorySpecial.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
                yShift += this.panelCategorySpecial.getHeight();
                yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
            } else {
                yShift = this.renderBlankPanels(6, this.x, this.y, yShift, this.g);
            }
        }
        if (this.save.getActiveRegionalSave().getGameType() == StateBase.GameType.SKIRMISH) {
            if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CIVICS_1) {
                this.lastPage = null;
                this.nextPage = null;
                this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
                this.panelAncillary.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.ANCILLARY), false, debug);
                this.panelClinic.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelAncillary.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CLINIC), false, debug);
                this.panelMaintenanceBuilding.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelClinic.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.MAINTENANCE_BUILDING), false, debug);
                this.panelMarketplace.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMaintenanceBuilding.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.MARKETPLACE), false, debug);
                this.panelWayMakerShack.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMarketplace.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WAY_MAKER_SHACK), false, debug);
                yShift += this.panelWayMakerShack.getHeight();
                yShift = this.renderBlankPanels(1, this.x, this.y, yShift, this.g);
            }
        } else {
            if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CIVICS_1) {
                this.lastPage = null;
                this.nextPage = GUIEnums.GUIPanelPage.RIGHT_CIVICS_2;
                this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
                this.panelAncillary.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.ANCILLARY), false, debug);
                this.panelClinic.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelAncillary.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CLINIC), false, debug);
                this.panelCourierStation.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelClinic.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.COURIER_STATION), false, debug);
                this.panelMaintenanceBuilding.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCourierStation.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.MAINTENANCE_BUILDING), false, debug);
                this.panelMarketplace.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMaintenanceBuilding.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.MARKETPLACE), false, debug);
                this.panelWayMakerShack.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMarketplace.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WAY_MAKER_SHACK), false, debug);
                yShift += this.panelWayMakerShack.getHeight();
            }
            if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CIVICS_2) {
                this.lastPage = GUIEnums.GUIPanelPage.RIGHT_CIVICS_1;
                this.nextPage = null;
                this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
                this.panelMigrationWayStation.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.MIGRATION_WAY_STATION), false, debug);
                yShift += this.panelMigrationWayStation.getHeight();
                yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
            }
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_DEFENSE) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
            this.panelCategoryDefenseGolems.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategoryDefenseTowers.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryDefenseGolems.getHeight()), false, debug);
            this.panelCategoryDefenseMiscellaneous.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryDefenseTowers.getHeight()), false, debug);
            yShift += this.panelCategoryDefenseMiscellaneous.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_DEFENSE_GOLEMS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_DEFENSE;
            this.panelCrystalGolemCombobulator.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR, true), false, debug);
            this.panelStoneGolemCombobulator.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrystalGolemCombobulator.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.STONE_GOLEM_COMBOBULATOR, true), false, debug);
            this.panelWoodGolemCombobulator.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelStoneGolemCombobulator.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WOOD_GOLEM_COMBOBULATOR, true), false, debug);
            yShift += this.panelWoodGolemCombobulator.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_DEFENSE_TOWERS_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_DEFENSE_TOWERS_2;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_DEFENSE;
            this.panelAttractTower.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.ATTRACT_TOWER, true), false, debug);
            this.panelBanishTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelAttractTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.BANISH_TOWER, true), false, debug);
            this.panelBallistaTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBanishTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.BALLISTA_TOWER, true), false, debug);
            this.panelBowTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBallistaTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.BOW_TOWER, true), false, debug);
            this.panelBulletTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBowTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.BULLET_TOWER, true), false, debug);
            this.panelElementalBoltTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBulletTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.ELEMENTAL_BOLT_TOWER, true), false, debug);
            yShift += this.panelElementalBoltTower.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_DEFENSE_TOWERS_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_DEFENSE_TOWERS_1;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_DEFENSE;
            this.panelPhantomDartTower.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.PHANTOM_DART_TOWER, true), false, debug);
            this.panelSlingTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPhantomDartTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.SLING_TOWER, true), false, debug);
            this.panelSprayTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelSlingTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.SPRAY_TOWER, true), false, debug);
            this.panelStaticTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelSprayTower.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.STATIC_TOWER, true), false, debug);
            yShift += this.panelStaticTower.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_DEFENSE_MISCELLANEOUS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_DEFENSE;
            this.panelLightningRod.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.LIGHTNING_ROD, true), false, debug);
            this.panelRecombobulatorTower.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLightningRod.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.RECOMBOBULATOR_TOWER, true), false, debug);
            yShift += this.panelRecombobulatorTower.getHeight();
            yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_FOOD_AND_WATER_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_FOOD_AND_WATER_2;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
            this.panelAnimalPen.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.ANIMAL_PEN, true), false, debug);
            this.panelBottler.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelAnimalPen.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.BOTTLER, true), false, debug);
            this.panelCluckerCoop.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBottler.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CLUCKER_COOP, true), false, debug);
            this.panelFarm.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCluckerCoop.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.FARM, true), false, debug);
            this.panelSmallFountain.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelFarm.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.SMALL_FOUNTAIN, true), false, debug);
            this.panelLargeFountain.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelSmallFountain.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.LARGE_FOUNTAIN, true), false, debug);
            yShift += this.panelLargeFountain.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_FOOD_AND_WATER_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_FOOD_AND_WATER_1;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
            this.panelKitchen.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.KITCHEN, true), false, debug);
            this.panelOutpost.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelKitchen.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.OUTPOST, true), false, debug);
            this.panelRainCatcher.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelOutpost.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.RAIN_CATCHER, true), false, debug);
            this.panelRangerLodge.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRainCatcher.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.RANGER_LODGE, true), false, debug);
            this.panelWaterPurifier.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRangerLodge.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WATER_PURIFIER, true), false, debug);
            this.panelWell.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelWaterPurifier.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WELL, true), false, debug);
            yShift += this.panelWell.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_GATES_AND_WALLS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.panelCategoryGatesAndWallsGates.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategoryGatesAndWallsWalls.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryGatesAndWallsGates.getHeight()), false, debug);
            yShift += this.panelCategoryGatesAndWallsWalls.getHeight();
            yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_GATES_AND_WALLS_GATES) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_GATES_AND_WALLS;
            this.panelCrylithiumWallGateNS.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CRYLITHIUM_WALL_GATE_NS, true), false, debug);
            this.panelCrylithiumWallGateWE.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrylithiumWallGateNS.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CRYLITHIUM_WALL_GATE_WE, true), false, debug);
            this.panelStoneWallGateNS.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrylithiumWallGateWE.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.STONE_WALL_GATE_NS, true), false, debug);
            this.panelStoneWallGateWE.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelStoneWallGateNS.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.STONE_WALL_GATE_WE, true), false, debug);
            this.panelWoodFenceGateNS.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelStoneWallGateWE.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WOOD_FENCE_GATE_NS, true), false, debug);
            this.panelWoodFenceGateWE.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelWoodFenceGateNS.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WOOD_FENCE_GATE_WE, true), false, debug);
            yShift += this.panelWoodFenceGateWE.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_GATES_AND_WALLS_WALLS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_GATES_AND_WALLS;
            this.panelCrylithiumWall.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CRYLITHIUM_WALL, true), false, debug);
            this.panelCrylithiumCurtainWall.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrylithiumWall.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CRYLITHIUM_CURTAIN_WALL, true), false, debug);
            this.panelCurtainWall.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrylithiumCurtainWall.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CURTAIN_WALL, true), false, debug);
            this.panelStoneWall.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCurtainWall.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.STONE_WALL, true), false, debug);
            this.panelTrashyCubeWall.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelStoneWall.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.TRASHY_CUBE_WALL, true), false, debug);
            this.panelWoodFence.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelTrashyCubeWall.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WOOD_FENCE, true), false, debug);
            yShift += this.panelWoodFence.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_HARVESTING) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
            this.panelCrystalHarvestry.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CRYSTAL_HARVESTRY, true), false, debug);
            this.panelLumberShack.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrystalHarvestry.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.LUMBER_SHACK, true), false, debug);
            this.panelMiningFacility.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLumberShack.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.MINING_FACILITY, true), false, debug);
            yShift += this.panelMiningFacility.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_HOUSING) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
            this.panelDoggoHouse.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.DOGGO_HOUSE, true), false, debug);
            this.panelHousing.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelDoggoHouse.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.HOUSING, true), false, debug);
            yShift += this.panelHousing.getHeight();
            yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_LIGHTING) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_1;
            this.panelCrylithiumFirePit.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CRYLITHIUM_FIRE_PIT, true), false, debug);
            this.panelLargeCrylithiumFirePit.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrylithiumFirePit.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.LARGE_CRYLITHIUM_FIRE_PIT, true), false, debug);
            this.panelFirePit.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLargeCrylithiumFirePit.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.FIRE_PIT, true), false, debug);
            this.panelLargeFirePit.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelFirePit.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.LARGE_FIRE_PIT, true), false, debug);
            yShift += this.panelLargeFirePit.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_MAGIC) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.panelCrystalMotivator.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CRYSTAL_MOTIVATOR, true), false, debug);
            this.panelCullisGate.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrystalMotivator.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CULLIS_GATE, true), false, debug);
            this.panelEssenceAltar.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCullisGate.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.ESSENCE_ALTAR, true), false, debug);
            this.panelEssenceCollector.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelEssenceAltar.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.ESSENCE_COLLECTOR, true), false, debug);
            this.panelReliquary.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelEssenceCollector.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.RELIQUARY, true), false, debug);
            yShift += this.panelReliquary.getHeight();
            yShift = this.renderBlankPanels(1, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_MANUFACTURING) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.panelArmorsmithy.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.ARMORSMITHY, true), false, debug);
            this.panelBowyer.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelArmorsmithy.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.BOWYER, true), false, debug);
            this.panelRockTumbler.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBowyer.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.ROCK_TUMBLER, true), false, debug);
            this.panelToolsmithy.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRockTumbler.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.TOOLSMITHY, true), false, debug);
            yShift += this.panelToolsmithy.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_REFINING) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.panelCrystillery.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CRYSTILLERY, true), false, debug);
            this.panelForge.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrystillery.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.FORGE, true), false, debug);
            this.panelLumberMill.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelForge.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.LUMBER_MILL, true), false, debug);
            this.panelStoneCuttery.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLumberMill.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.STONE_CUTTERY, true), false, debug);
            yShift += this.panelStoneCuttery.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_STORAGE_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_STORAGE_2;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.panelAmmoStorage.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.AMMO_STORAGE, true), false, debug);
            this.panelCrystalStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelAmmoStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CRYSTAL_STORAGE, true), false, debug);
            this.panelEquipmentStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCrystalStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.EQUIPMENT_STORAGE, true), false, debug);
            this.panelFoodStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelEquipmentStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.FOOD_STORAGE, true), false, debug);
            this.panelGoldStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelFoodStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.GOLD_STORAGE, true), false, debug);
            this.panelKeyShack.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGoldStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.KEY_SHACK, true), false, debug);
            yShift += this.panelKeyShack.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_STORAGE_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_STORAGE_1;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.panelMineralStorage.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.MINERAL_STORAGE, true), false, debug);
            this.panelMiscellaneousStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMineralStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.MISCELLANEOUS_STORAGE, true), false, debug);
            this.panelRockStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMiscellaneousStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.ROCK_STORAGE, true), false, debug);
            this.panelWoodStorage.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRockStorage.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.WOOD_STORAGE, true), false, debug);
            yShift += this.panelWoodStorage.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_TRASH) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_2;
            this.panelBurner.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.BURNER), false, debug);
            this.panelCubeEGolemCombobulator.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBurner.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.CUBE_E_GOLEM_COMBOBULATOR), false, debug);
            this.panelLandfill.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCubeEGolemCombobulator.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.LANDFILL), false, debug);
            this.panelProcessor.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLandfill.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.PROCESSOR), false, debug);
            this.panelTrashCan.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelProcessor.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.TRASH_CAN), false, debug);
            this.panelTrashyCubePile.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelTrashCan.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.TRASHY_CUBE_PILE), false, debug);
            yShift += this.panelTrashyCubePile.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_SPECIAL) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_BUILD_3;
            this.panelCamp.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.CASTLE_1, true), false, debug);
            this.panelLootBox.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCamp.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.LOOT_BOX, true), false, debug);
            yShift += this.panelCamp.getHeight();
            yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_ROADS_AND_DIGGING) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = null;
            this.panelPavePath.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.PATH, true), !canBuildRoads, debug);
            this.panelPaveLogPath.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPavePath.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.LOG_PATH, true) + this.map.getTileCount(MapTilesLoader.TileSet.LOG_PATH_DEBRIS, true), !canBuildRoads, debug);
            this.panelPaveCobbleAndLogPath.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPaveLogPath.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_LOG_PATH, true) + this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_LOG_PATH_DEBRIS, true), !canBuildRoads, debug);
            this.panelPaveCobbleAndBoardRoad.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPaveCobbleAndLogPath.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_BOARD_ROAD, true) + this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_BOARD_ROAD_DEBRIS, true), !canBuildRoads, debug);
            this.panelPaveCutStoneAndBoardRoad.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPaveCobbleAndBoardRoad.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.CUT_STONE_AND_BOARD_ROAD, true) + this.map.getTileCount(MapTilesLoader.TileSet.CUT_STONE_AND_BOARD_ROAD_DEBRIS, true), !canBuildRoads, debug);
            this.panelDigHole.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPaveCutStoneAndBoardRoad.getHeight()), !canBuildRoads, debug);
            this.panelDismantleRoads.render(this.g, this.mouse, this.x + this.panelCurrentDay.getWidth(), this.y + 28 + yShift, !canBuildRoads, debug);
            yShift += this.panelCurrentDay.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.backPage = null;
            this.panelCategoryBricks.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategoryCrystals.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryBricks.getHeight()), false, debug);
            this.panelCategoryDirt.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryCrystals.getHeight()), false, debug);
            this.panelCategoryFlowers.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryDirt.getHeight()), false, debug);
            this.panelCategoryFood.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryFlowers.getHeight()), false, debug);
            this.panelCategoryGrass.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryFood.getHeight()), false, debug);
            yShift += this.panelCategoryGrass.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3;
            this.backPage = null;
            this.panelCategoryGravel.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategoryLava.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryGravel.getHeight()), false, debug);
            this.panelCategoryRoads.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryLava.getHeight()), false, debug);
            this.panelCategoryRock.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryRoads.getHeight()), false, debug);
            this.panelCategorySand.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryRock.getHeight()), false, debug);
            this.panelCategorySandstone.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategorySand.getHeight()), false, debug);
            yShift += this.panelCategorySandstone.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.nextPage = null;
            this.backPage = null;
            this.panelCategorySnow.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategoryTar.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategorySnow.getHeight()), false, debug);
            this.panelCategoryTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryTar.getHeight()), false, debug);
            this.panelCategoryWater.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryTrees.getHeight()), false, debug);
            yShift += this.panelCategoryWater.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_BRICKS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.panelGrayBricks.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.BRICKS), false, debug);
            this.panelGrayTiles.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGrayBricks.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TILES), false, debug);
            yShift += this.panelGrayTiles.getHeight();
            yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CRYSTALS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.panelRedCrystals.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.CRYSTAL_RED), false, debug);
            this.panelGreenCrystals.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRedCrystals.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.CRYSTAL_GREEN), false, debug);
            this.panelBlueCrystals.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGreenCrystals.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.CRYSTAL_BLUE), false, debug);
            this.panelPurpleCrystals.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlueCrystals.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.CRYSTAL_PURPLE), false, debug);
            yShift += this.panelPurpleCrystals.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_DIRT) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.panelBrownDirt.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.DIRT_BROWN), false, debug);
            this.panelDarkBrownDirt.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBrownDirt.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.DIRT_DARK_BROWN), false, debug);
            this.panelLightBrownDirt.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelDarkBrownDirt.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.DIRT_LIGHT_BROWN), false, debug);
            yShift += this.panelLightBrownDirt.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_FLOWERS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.panelBlackFlowers.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.FLOWERS_BLACK), false, debug);
            this.panelBlueFlowers.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlackFlowers.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.FLOWERS_BLUE), false, debug);
            this.panelPurpleFlowers.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlueFlowers.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.FLOWERS_PURPLE), false, debug);
            this.panelRedFlowers.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPurpleFlowers.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.FLOWERS_RED), false, debug);
            this.panelWhiteFlowers.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRedFlowers.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.FLOWERS_WHITE), false, debug);
            this.panelYellowFlowers.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelWhiteFlowers.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.FLOWERS_YELLOW), false, debug);
            yShift += this.panelYellowFlowers.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_FOOD_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_FOOD_2;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.panelCactus.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.CACTUS_GREEN), false, debug);
            this.panelCarrots.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCactus.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.CARROTS), false, debug);
            this.panelHolyPotatoes.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCarrots.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.HOLY_POTATOES), false, debug);
            this.panelMelons.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelHolyPotatoes.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.MELONS), false, debug);
            this.panelMushrooms.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMelons.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.MUSHROOMS), false, debug);
            this.panelPotatoes.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMushrooms.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.POTATOES), false, debug);
            yShift += this.panelPotatoes.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_FOOD_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_FOOD_1;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.panelTurnips.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.TURNIPS), false, debug);
            yShift += this.panelTurnips.getHeight();
            yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_GRASS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_1;
            this.panelEmeraldGreenGrass.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.GRASS_EMERALD_GREEN), false, debug);
            this.panelGreenGrass.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelEmeraldGreenGrass.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.GRASS_GREEN), false, debug);
            this.panelTealGrass.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGreenGrass.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.GRASS_TEAL), false, debug);
            this.panelYellowBrownGrass.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelTealGrass.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.GRASS_YELLOW_BROWN), false, debug);
            yShift += this.panelYellowBrownGrass.getHeight();
            yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_GRAVEL) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelBlueGravel.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.GRAVEL_BLUE), false, debug);
            this.panelGrayGravel.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlueGravel.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.GRAVEL_GRAY), false, debug);
            this.panelRedGravel.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGrayGravel.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.GRAVEL_RED), false, debug);
            yShift += this.panelRedGravel.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_LAVA) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelLava.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.LAVA), false, debug);
            yShift += this.panelLava.getHeight();
            yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_ROADS_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_ROADS_2;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelPath.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.PATH), false, debug);
            this.panelLogPathDebris.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPath.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.LOG_PATH_DEBRIS), false, debug);
            this.panelLogPath.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLogPathDebris.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.LOG_PATH), false, debug);
            this.panelCobbleAndLogPathDebris.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLogPath.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_LOG_PATH_DEBRIS), false, debug);
            this.panelCobbleAndLogPath.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCobbleAndLogPathDebris.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_LOG_PATH), false, debug);
            this.panelCobbleAndBoardRoadDebris.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCobbleAndLogPath.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_BOARD_ROAD_DEBRIS), false, debug);
            yShift += this.panelCobbleAndBoardRoadDebris.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_ROADS_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_ROADS_1;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelCobbleAndBoardRoad.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.COBBLE_AND_BOARD_ROAD), false, debug);
            this.panelCutStoneAndBoardRoadDebris.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCobbleAndBoardRoad.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.CUT_STONE_AND_BOARD_ROAD_DEBRIS), false, debug);
            this.panelCutStoneAndBoardRoad.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCutStoneAndBoardRoadDebris.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.CUT_STONE_AND_BOARD_ROAD), false, debug);
            yShift += this.panelCutStoneAndBoardRoad.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_ROCK) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelBlackRock.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.ROCK_BLACK), false, debug);
            this.panelBrownRock.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlackRock.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.ROCK_BROWN), false, debug);
            this.panelGrayRock.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBrownRock.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.ROCK_GRAY), false, debug);
            this.panelRedRock.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGrayRock.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.ROCK_RED), false, debug);
            this.panelWhiteRock.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRedRock.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.ROCK_WHITE), false, debug);
            yShift += this.panelWhiteRock.getHeight();
            yShift = this.renderBlankPanels(1, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_SAND) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelBlackSand.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.SAND_BLACK), false, debug);
            this.panelRedSand.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlackSand.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.SAND_RED), false, debug);
            this.panelTanSand.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRedSand.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.SAND_TAN), false, debug);
            yShift += this.panelTanSand.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_SANDSTONE) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelBlackSandstone.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.SANDSTONE_BLACK), false, debug);
            this.panelRedSandstone.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlackSandstone.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.SANDSTONE_RED), false, debug);
            this.panelTanSandstone.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRedSandstone.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.SANDSTONE_TAN), false, debug);
            yShift += this.panelTanSandstone.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_SNOW) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_2;
            this.panelWhiteSnow.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.SNOW_WHITE), false, debug);
            yShift += this.panelWhiteSnow.getHeight();
            yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_TAR) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3;
            this.panelTar.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.TAR), false, debug);
            yShift += this.panelTar.getHeight();
            yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_TREES_1) {
            this.lastPage = null;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_TREES_2;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3;
            this.panelBlueDeadTrees.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.TREES_BLUE_DEAD), false, debug);
            this.panelBlueStumps.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlueDeadTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_BLUE_COLLECTED), false, debug);
            this.panelBlueTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlueStumps.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_BLUE), false, debug);
            this.panelBrownDeadTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBlueTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_BROWN_DEAD), false, debug);
            this.panelBrownStumps.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBrownDeadTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_BROWN_COLLECTED), false, debug);
            this.panelBrownTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelBrownStumps.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_BROWN), false, debug);
            yShift += this.panelBrownTrees.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_TREES_2) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_TREES_1;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_TREES_3;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3;
            this.panelDarkGreenDeadTrees.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.TREES_DARK_GREEN_DEAD), false, debug);
            this.panelDarkGreenStumps.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelDarkGreenDeadTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_DARK_GREEN_COLLECTED), false, debug);
            this.panelDarkGreenTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelDarkGreenStumps.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_DARK_GREEN), false, debug);
            this.panelGreenDeadTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelDarkGreenTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_GREEN_DEAD), false, debug);
            this.panelGreenStumps.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGreenDeadTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_GREEN_COLLECTED), false, debug);
            this.panelGreenTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelGreenStumps.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_GREEN), false, debug);
            yShift += this.panelGreenTrees.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_TREES_3) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_TREES_2;
            this.nextPage = GUIEnums.GUIPanelPage.RIGHT_TREES_4;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3;
            this.panelLavenderDeadTrees.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.TREES_LAVENDER_DEAD), false, debug);
            this.panelLavenderStumps.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLavenderDeadTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_LAVENDER_COLLECTED), false, debug);
            this.panelLavenderTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLavenderStumps.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_LAVENDER), false, debug);
            this.panelPaleBlueDeadTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelLavenderTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_PALE_BLUE_DEAD), false, debug);
            this.panelPaleBlueStumps.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPaleBlueDeadTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_PALE_BLUE_COLLECTED), false, debug);
            this.panelPaleBlueTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelPaleBlueStumps.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_PALE_BLUE), false, debug);
            yShift += this.panelPaleBlueTrees.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_TREES_4) {
            this.lastPage = GUIEnums.GUIPanelPage.RIGHT_TREES_3;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3;
            this.panelRedDeadTrees.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.TREES_RED_DEAD), false, debug);
            this.panelRedStumps.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRedDeadTrees.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_RED_COLLECTED), false, debug);
            this.panelRedTrees.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRedStumps.getHeight()), this.map.getTileCount(MapTilesLoader.TileSet.TREES_RED), false, debug);
            yShift += this.panelRedTrees.getHeight();
            yShift = this.renderBlankPanels(3, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_WATER) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TILES_3;
            this.panelWater.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.map.getTileCount(MapTilesLoader.TileSet.WATER), false, debug);
            yShift += this.panelWater.getHeight();
            yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SANDBOX_TOOLS) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = null;
            this.panelCategoryTime.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCategorySpawning.render(this.g, this.mouse, this.x + this.panelCategoryTime.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelCategoryParticles.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCategoryTime.getHeight()), false, debug);
            this.panelCategoryMisc.render(this.g, this.mouse, this.x + this.panelCategoryParticles.getWidth(), this.y + 28 + yShift, false, debug);
            yShift += this.panelCategoryParticles.getHeight();
            yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_TIME) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SANDBOX_TOOLS;
            this.panelCurrentDay.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelCurrentDayMinus.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelCurrentDayPlus.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 51 + yShift, "$YEL1" + this.time.getDay(), Text.FontType.BODY, 4, true);
            this.panelTimeOfDay.render(this.g, this.mouse, this.x + this.panelCurrentDay.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelTimeOfDayLeft.render(this.g, this.mouse, this.x + this.panelCurrentDay.getWidth() + 9, this.y + 82 + yShift, false, debug);
            this.panelTimeOfDayRight.render(this.g, this.mouse, this.x + this.panelCurrentDay.getWidth() + 48, this.y + 82 + yShift, false, debug);
            if (this.time.isFullMoon()) {
                this.font.drawString(this.x + this.panelCurrentDay.getWidth() + 47, this.y + 55 + yShift, "$BLU0Full Moon", Text.FontType.BODY, 2, true);
            } else if (this.time.isBloodMoon()) {
                this.font.drawString(this.x + this.panelCurrentDay.getWidth() + 47, this.y + 55 + yShift, "$RED1Blood Moon", Text.FontType.BODY, 1, true);
            } else if (this.time.isEclipse()) {
                this.font.drawString(this.x + this.panelCurrentDay.getWidth() + 47, this.y + 55 + yShift, "$YEL1Eclipse", Text.FontType.BODY, 1, true);
            } else {
                this.font.drawString(this.x + this.panelCurrentDay.getWidth() + 47, this.y + 55 + yShift, "$YEL1" + this.time.getTimeOfDay().getText(), Text.FontType.BODY, 2, true);
            }
            this.panelSeason.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelCurrentDay.getHeight()), false, debug);
            this.panelSeasonLeft.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelSeasonRight.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 55 + yShift, "$GRE1" + this.time.getSeason().getText(), Text.FontType.BODY, 2, true);
            if (this.time.getFreezeTime()) {
                this.panelToggleTimeFrozen.render(this.g, this.mouse, this.x + this.panelSeason.getWidth(), this.y + 28 + yShift, false, debug);
            } else {
                this.panelToggleTime.render(this.g, this.mouse, this.x + this.panelSeason.getWidth(), this.y + 28 + yShift, false, debug);
            }
            yShift += this.panelSeason.getHeight();
            if (SettingsParser.getDebug()) {
                this.panelAdvanceTime.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
                try {
                    this.font.drawString(this.x + 95, this.y + 53 + yShift, "$YEL1" + ((PlayState)Game.getCS()).getAdvanceTime(), Text.FontType.BODY, 4, true);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.panelAdvanceTimeMinus.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
                this.panelAdvanceTimePlus.render(this.g, this.mouse, this.x + 143, this.y + 82 + yShift, false, debug);
                this.panelAdvanceTimeStart.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelAdvanceTime.getHeight()), false, debug);
                this.font.drawString(this.x + 97, this.y + 52 + yShift, "$RED1WARNING: WILL CAUSE THE~GAME TO STOP RESPONDING FOR~A VERY LONG TIME", Text.FontType.BODY, 0, true);
                yShift += this.panelAdvanceTimeStart.getHeight();
                yShift = this.renderBlankPanels(2, this.x, this.y, yShift, this.g);
            } else {
                yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
            }
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SPAWNING) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SANDBOX_TOOLS;
            this.panelMobType.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelMobTypeLeft.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelMobTypeRight.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 50 + yShift, Text.wrapString("$YEL1" + this.selectedSpawnMobType.getName(), Text.FontType.BODY, 0, 67), Text.FontType.BODY, 0, true);
            this.panelMobAmount.render(this.g, this.mouse, this.x + this.panelMobType.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelMobAmountMinus.render(this.g, this.mouse, this.x + this.panelMobType.getWidth() + 9, this.y + 82 + yShift, false, debug);
            this.panelMobAmountPlus.render(this.g, this.mouse, this.x + this.panelMobType.getWidth() + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + this.panelMobType.getWidth() + 47, this.y + 52 + yShift, "$YEL1" + this.selectedSpawnMobAmount, Text.FontType.BODY, 3, true);
            this.panelSpawnMob.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelMobType.getHeight()), false, debug);
            this.panelResourceType.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelSpawnMob.getHeight()), false, debug);
            this.panelResourceTypeLeft.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelResourceTypeRight.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 50 + yShift, Text.wrapString("$YEL1" + this.selectedSpawnResourceType.getTemplate().getName(), Text.FontType.BODY, 0, 67), Text.FontType.BODY, 0, true);
            this.panelResourceAmount.render(this.g, this.mouse, this.x + this.panelResourceType.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelResourceAmountMinus.render(this.g, this.mouse, this.x + this.panelResourceType.getWidth() + 9, this.y + 82 + yShift, false, debug);
            this.panelResourceAmountPlus.render(this.g, this.mouse, this.x + this.panelResourceType.getWidth() + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + this.panelResourceType.getWidth() + 47, this.y + 52 + yShift, "$YEL1" + this.selectedSpawnResourceAmount, Text.FontType.BODY, 3, true);
            this.panelSpawnResource.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelResourceType.getHeight()), false, debug);
            this.panelSpawnChance.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelResourceAmount.getHeight()), false, debug);
            this.panelSpawnChanceMinus.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelSpawnChancePlus.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 52 + yShift, "$YEL1" + this.spawn.getSpawnRate(), Text.FontType.BODY, 3, true);
            this.panelMonsterCount.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelSpawnChance.getHeight()), false, debug);
            this.font.drawString(this.x + 95, this.y + 53 + yShift, "$CYA1" + this.mob.getMobsByGroup(MobBase.MobGroup.MONSTER, false).size(), Text.FontType.HEADER, 5, true);
            yShift += this.panelMonsterCount.getHeight();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_PARTICLES) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SANDBOX_TOOLS;
            this.panelParticleType.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelParticleTypeLeft.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelParticleTypeRight.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 50 + yShift, "$YEL1" + this.selectedSpawnParticleType.toString(), Text.FontType.BODY, 0, true);
            this.panelParticleGroup.render(this.g, this.mouse, this.x + this.panelParticleType.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelParticleGroupLeft.render(this.g, this.mouse, this.x + this.panelParticleType.getWidth() + 9, this.y + 82 + yShift, false, debug);
            this.panelParticleGroupRight.render(this.g, this.mouse, this.x + this.panelParticleType.getWidth() + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + this.panelParticleSpread.getWidth() + 47, this.y + 50 + yShift, "$YEL1" + this.selectedSpawnParticleGroup.toString(), Text.FontType.BODY, 0, true);
            this.panelParticleColor.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelParticleType.getHeight()), false, debug);
            this.panelParticleColorLeft.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelParticleColorRight.render(this.g, this.mouse, this.x + 143, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 95, this.y + 53 + yShift, "$YEL1" + (Object)((Object)this.selectedSpawnParticleColor), Text.FontType.HEADER, 3, true);
            this.panelParticleAmount.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelParticleColor.getHeight()), false, debug);
            this.panelParticleAmountMinus.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelParticleAmountPlus.render(this.g, this.mouse, this.x + 143, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 95, this.y + 53 + yShift, "$YEL1" + this.selectedSpawnParticleAmount, Text.FontType.HEADER, 5, true);
            this.panelParticleSpread.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelParticleAmount.getHeight()), false, debug);
            this.panelParticleSpreadMinus.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelParticleSpreadPlus.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 52 + yShift, "$YEL1" + this.selectedSpawnParticleSpread, Text.FontType.BODY, 3, true);
            this.panelParticleAngle.render(this.g, this.mouse, this.x + this.panelParticleSpread.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelParticleAngleMinus.render(this.g, this.mouse, this.x + this.panelParticleSpread.getWidth() + 9, this.y + 82 + yShift, false, debug);
            this.panelParticleAnglePlus.render(this.g, this.mouse, this.x + this.panelParticleSpread.getWidth() + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + this.panelParticleSpread.getWidth() + 47, this.y + 52 + yShift, "$YEL1" + this.selectedSpawnParticleAngle, Text.FontType.BODY, 3, true);
            this.panelEmitSingleParticles.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelParticleSpread.getHeight()), false, debug);
            this.panelEmitGroupParticles.render(this.g, this.mouse, this.x + this.panelEmitSingleParticles.getWidth(), this.y + 28 + yShift, false, debug);
            yShift += this.panelEmitSingleParticles.getHeight();
            yShift = this.renderBlankPanels(1, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_MISC) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SANDBOX_TOOLS;
            this.panelEssenceAmount.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            this.panelEssenceAmountMinus.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelEssenceAmountPlus.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 52 + yShift, "$YEL1" + this.spawnEssenceAmount, Text.FontType.BODY, 3, true);
            this.panelEssenceValue.render(this.g, this.mouse, this.x + this.panelEssenceAmount.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelEssenceValueMinus.render(this.g, this.mouse, this.x + this.panelEssenceAmount.getWidth() + 9, this.y + 82 + yShift, false, debug);
            this.panelEssenceValuePlus.render(this.g, this.mouse, this.x + this.panelEssenceAmount.getWidth() + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + this.panelEssenceAmount.getWidth() + 47, this.y + 52 + yShift, "$YEL1" + this.spawnEssenceValue, Text.FontType.BODY, 3, true);
            this.panelEmitEssence.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelEssenceAmount.getHeight()), false, debug);
            yShift += this.panelEmitEssence.getHeight();
            if (this.weather.isWeather()) {
                this.panelToggleWeatherOn.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            } else {
                this.panelToggleWeatherOff.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            }
            this.panelLightning.render(this.g, this.mouse, this.x + this.panelToggleWeatherOff.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelRestoreBasicNeeds.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelToggleWeatherOn.getHeight()), false, debug);
            this.panelRestoreHealth.render(this.g, this.mouse, this.x + this.panelRestoreBasicNeeds.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelMissileType.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelRestoreBasicNeeds.getHeight()), false, debug);
            this.panelMissileTypeMinus.render(this.g, this.mouse, this.x + 9, this.y + 82 + yShift, false, debug);
            this.panelMissileTypePlus.render(this.g, this.mouse, this.x + 48, this.y + 82 + yShift, false, debug);
            this.font.drawString(this.x + 47, this.y + 52 + yShift, Text.wrapString("$YEL1" + this.missileType.getText(), Text.FontType.BODY, 0, 67), Text.FontType.BODY, 0, true);
            this.panelFireMissile.render(this.g, this.mouse, this.x + this.panelEssenceAmount.getWidth(), this.y + 28 + yShift, false, debug);
            this.panelGenerateCloud.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelFireMissile.getHeight()), false, debug);
            yShift += this.panelGenerateCloud.getHeight();
        }

        //@@Inizio_Creazione_Pannelli@@

        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_MOD_1) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = null;
            this.panelCategoryMod_1.render(this.g, this.mouse, this.x, this.y + 28 + yShift, false, debug);
            yShift += this.panelCategoryMod_1.getHeight();
            yShift = this.renderBlankPanels(5, this.x, this.y, yShift, this.g);
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_MOD_1_1) {
            this.lastPage = null;
            this.nextPage = null;
            this.backPage = GUIEnums.GUIPanelPage.RIGHT_CATEGORY_MOD_1;
            this.panelModStructure_1_1.render(this.g, this.mouse, this.x, this.y + 28 + yShift, this.object.objectCount(MapTilesLoader.TileSet.WOOD_MOTIVATOR, true), false, debug);
            this.panelModStructure_1_2.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelModStructure_1_1.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.ROCK_MOTIVATOR, true), false, debug);
            this.panelModStructure_1_3.render(this.g, this.mouse, this.x, this.y + 28 + (yShift += this.panelModStructure_1_2.getHeight()), this.object.objectCount(MapTilesLoader.TileSet.VEGETABLE_MOTIVATOR, true), false, debug);
            yShift += this.panelCullisGate.getHeight();
            yShift = this.renderBlankPanels(4, this.x, this.y, yShift, this.g);
        }

        //@@Fine_Creazione_Pannelli@@

        if (this.backPage == null) {
            this.pageBackBottom.render(this.g, this.mouse, this.x, this.y + yShift + 28, true, debug);
        } else {
            this.pageBackBottom.render(this.g, this.mouse, this.x, this.y + yShift + 28, false, debug);
        }
        if (this.lastPage == null) {
            this.pageUpBottom.render(this.g, this.mouse, this.x + 67, this.y + yShift + 28, true, debug);
        } else {
            this.pageUpBottom.render(this.g, this.mouse, this.x + 67, this.y + yShift + 28, false, debug);
        }
        if (this.nextPage == null) {
            this.pageDownBottom.render(this.g, this.mouse, this.x + 96, this.y + yShift + 28, true, debug);
        } else {
            this.pageDownBottom.render(this.g, this.mouse, this.x + 96, this.y + yShift + 28, false, debug);
        }
    }

    private int renderBlankPanels(int i, int x, int y, int yShift, Graphics g) {
        if (i >= 6) {
            g.setColor(new Color(26, 26, 26, 255));
            g.fillRect(x + 10, y + 28 + yShift + 19, this.blankLargePanel1.getWidth() - 20, this.blankLargePanel1.getHeight() - 29);
            this.blankLargePanel1.draw(x, y + 28 + yShift);
            this.blankLargePanel1.drawFlash(x, y + 28 + yShift, this.blankLargePanel1.getWidth(), this.blankLargePanel1.getHeight(), new Color(0, 0, 0, 200));
            yShift += this.blankLargePanel1.getHeight();
        }
        if (i >= 5) {
            g.setColor(new Color(26, 26, 26, 255));
            g.fillRect(x + 10, y + 28 + yShift + 19, this.blankLargePanel2.getWidth() - 20, this.blankLargePanel2.getHeight() - 29);
            this.blankLargePanel2.draw(x, y + 28 + yShift);
            this.blankLargePanel2.drawFlash(x, y + 28 + yShift, this.blankLargePanel2.getWidth(), this.blankLargePanel2.getHeight(), new Color(0, 0, 0, 200));
            yShift += this.blankLargePanel2.getHeight();
        }
        if (i >= 4) {
            g.setColor(new Color(26, 26, 26, 255));
            g.fillRect(x + 10, y + 28 + yShift + 19, this.blankLargePanel3.getWidth() - 20, this.blankLargePanel3.getHeight() - 29);
            this.blankLargePanel3.draw(x, y + 28 + yShift);
            this.blankLargePanel3.drawFlash(x, y + 28 + yShift, this.blankLargePanel3.getWidth(), this.blankLargePanel3.getHeight(), new Color(0, 0, 0, 200));
            yShift += this.blankLargePanel3.getHeight();
        }
        if (i >= 3) {
            g.setColor(new Color(26, 26, 26, 255));
            g.fillRect(x + 10, y + 28 + yShift + 19, this.blankLargePanel4.getWidth() - 20, this.blankLargePanel4.getHeight() - 29);
            this.blankLargePanel4.draw(x, y + 28 + yShift);
            this.blankLargePanel4.drawFlash(x, y + 28 + yShift, this.blankLargePanel4.getWidth(), this.blankLargePanel4.getHeight(), new Color(0, 0, 0, 200));
            yShift += this.blankLargePanel4.getHeight();
        }
        if (i >= 2) {
            g.setColor(new Color(26, 26, 26, 255));
            g.fillRect(x + 10, y + 28 + yShift + 19, this.blankLargePanel5.getWidth() - 20, this.blankLargePanel5.getHeight() - 29);
            this.blankLargePanel5.draw(x, y + 28 + yShift);
            this.blankLargePanel5.drawFlash(x, y + 28 + yShift, this.blankLargePanel5.getWidth(), this.blankLargePanel5.getHeight(), new Color(0, 0, 0, 200));
            yShift += this.blankLargePanel5.getHeight();
        }
        if (i >= 1) {
            g.setColor(new Color(26, 26, 26, 255));
            g.fillRect(x + 10, y + 28 + yShift + 19, this.blankLargePanel6.getWidth() - 20, this.blankLargePanel6.getHeight() - 29);
            this.blankLargePanel6.draw(x, y + 28 + yShift);
            this.blankLargePanel6.drawFlash(x, y + 28 + yShift, this.blankLargePanel6.getWidth(), this.blankLargePanel6.getHeight(), new Color(0, 0, 0, 200));
            yShift += this.blankLargePanel6.getHeight();
        }
        return yShift;
    }

    @Override
    public void update() {
        if (this.panelCurrentPage != GUIEnums.GUIPanelPage.RIGHT_CATEGORY_ROADS_AND_DIGGING &&
                !this.panelCurrentPage.toString().contains("RIGHT_CATEGORY_MOD") &&
                !this.panelCurrentPage.toString().contains("RIGHT_MOD") &&
                !this.panelCurrentPage.toString().contains("RIGHT_CATEGORY_TILES") &&
                this.panelCurrentPage != GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SANDBOX_TOOLS) {
            this.buildTab.select();
        } else {
            this.buildTab.deselect();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_ROADS_AND_DIGGING) {
            this.roadsAndDiggingTab.select();
            this.roadsAndDiggingTabMock.select();
        } else {
            this.roadsAndDiggingTab.deselect();
            this.roadsAndDiggingTabMock.deselect();
        }
        if (this.panelCurrentPage.toString().contains("RIGHT_CATEGORY_TILES")) {
            this.tilesTab.select();
        } else {
            this.tilesTab.deselect();
        }
        if (this.panelCurrentPage == GUIEnums.GUIPanelPage.RIGHT_CATEGORY_SANDBOX_TOOLS) {
            this.sandboxToolsTab.select();
        } else {
            this.sandboxToolsTab.deselect();
        }

        if (this.panelCurrentPage.toString().contains("RIGHT_CATEGORY_MOD") ||
                this.panelCurrentPage.toString().contains("RIGHT_MOD")) {
            this.modTab.select();
        } else {
            this.modTab.deselect();
        }
    }

    public boolean isPanelHidden() {
        return this.hidden;
    }

    public void setPanelPage(GUIEnums.GUIPanelPage p) {
        this.panelCurrentPage = p;
    }

    public void nextPage() {
        if (this.nextPage != null) {
            this.panelCurrentPage = this.nextPage;
        }
    }

    public void lastPage() {
        if (this.lastPage != null) {
            this.panelCurrentPage = this.lastPage;
        }
    }

    public void backPage() {
        if (this.backPage != null) {
            this.panelCurrentPage = this.backPage;
        }
    }

    public void togglePanelHidden() {
        this.hidden = !this.hidden;
        this.hidePanel.cycleButton();
    }

    public void setSelectedSpawnMobTypeLeft() {
        this.selectedSpawnMobType = this.selectedSpawnMobType.ordinal() != MobBase.MobType.values().length - 1 ? MobBase.MobType.values()[this.selectedSpawnMobType.ordinal() + 1] : MobBase.MobType.values()[0];
    }

    public void setSelectedSpawnMobTypeRight() {
        this.selectedSpawnMobType = this.selectedSpawnMobType.ordinal() != 0 ? MobBase.MobType.values()[this.selectedSpawnMobType.ordinal() - 1] : MobBase.MobType.values()[MobBase.MobType.values().length - 1];
    }

    public MobBase.MobType getSelectedSpawnMobType() {
        return this.selectedSpawnMobType;
    }

    public void increaseSelectedSpawnMobAmount(int i) {
        this.selectedSpawnMobAmount = this.selectedSpawnMobAmount + i < 100 ? (this.selectedSpawnMobAmount += i) : 100;
    }

    public void decreaseSelectedSpawnMobAmount(int i) {
        this.selectedSpawnMobAmount = this.selectedSpawnMobAmount - i > 1 ? (this.selectedSpawnMobAmount -= i) : 1;
    }

    public int getSelectedMobAmount() {
        return this.selectedSpawnMobAmount;
    }

    public void setSelectedSpawnResourceTypeLeft() {
        this.selectedSpawnResourceType = this.selectedSpawnResourceType.ordinal() != ResourceModule.ResourceType.values().length - 1 ? ResourceModule.ResourceType.values()[this.selectedSpawnResourceType.ordinal() + 1] : ResourceModule.ResourceType.values()[0];
    }

    public void setSelectedSpawnResourceTypeRight() {
        this.selectedSpawnResourceType = this.selectedSpawnResourceType.ordinal() != 0 ? ResourceModule.ResourceType.values()[this.selectedSpawnResourceType.ordinal() - 1] : ResourceModule.ResourceType.values()[ResourceModule.ResourceType.values().length - 1];
    }

    public ResourceModule.ResourceType getSelectedSpawnResourceType() {
        return this.selectedSpawnResourceType;
    }

    public void increaseSelectedSpawnResourceAmount(int i) {
        this.selectedSpawnResourceAmount = this.selectedSpawnResourceAmount + i < 100 ? (this.selectedSpawnResourceAmount += i) : 100;
    }

    public void decreaseSelectedSpawnResourceAmount(int i) {
        this.selectedSpawnResourceAmount = this.selectedSpawnResourceAmount - i > 1 ? (this.selectedSpawnResourceAmount -= i) : 1;
    }

    public int getSelectedResourceAmount() {
        return this.selectedSpawnResourceAmount;
    }

    public void setSelectedSpawnParticleTypeRight() {
        this.selectedSpawnParticleType = this.selectedSpawnParticleType.ordinal() != ParticleModule.ParticleType.values().length - 1 ? ParticleModule.ParticleType.values()[this.selectedSpawnParticleType.ordinal() + 1] : ParticleModule.ParticleType.values()[0];
    }

    public void setSelectedSpawnParticleTypeLeft() {
        this.selectedSpawnParticleType = this.selectedSpawnParticleType.ordinal() != 0 ? ParticleModule.ParticleType.values()[this.selectedSpawnParticleType.ordinal() - 1] : ParticleModule.ParticleType.values()[ParticleModule.ParticleType.values().length - 1];
    }

    public ParticleModule.ParticleType getSelectedSpawnParticleType() {
        return this.selectedSpawnParticleType;
    }

    public void setSelectedSpawnParticleGroupRight() {
        this.selectedSpawnParticleGroup = this.selectedSpawnParticleGroup.ordinal() != ParticleModule.ParticleGroup.values().length - 1 ? ParticleModule.ParticleGroup.values()[this.selectedSpawnParticleGroup.ordinal() + 1] : ParticleModule.ParticleGroup.values()[0];
    }

    public void setSelectedSpawnParticleGroupLeft() {
        this.selectedSpawnParticleGroup = this.selectedSpawnParticleGroup.ordinal() != 0 ? ParticleModule.ParticleGroup.values()[this.selectedSpawnParticleGroup.ordinal() - 1] : ParticleModule.ParticleGroup.values()[ParticleModule.ParticleGroup.values().length - 1];
    }

    public ParticleModule.ParticleGroup getSelectedSpawnParticleGroup() {
        return this.selectedSpawnParticleGroup;
    }

    public void setSelectedSpawnParticleColorRight() {
        this.selectedSpawnParticleColor = this.selectedSpawnParticleColor.ordinal() != ParticleModule.ParticleSet.values().length - 1 ? ParticleModule.ParticleSet.values()[this.selectedSpawnParticleColor.ordinal() + 1] : ParticleModule.ParticleSet.values()[0];
    }

    public void setSelectedSpawnParticleColorLeft() {
        this.selectedSpawnParticleColor = this.selectedSpawnParticleColor.ordinal() != 0 ? ParticleModule.ParticleSet.values()[this.selectedSpawnParticleColor.ordinal() - 1] : ParticleModule.ParticleSet.values()[ParticleModule.ParticleSet.values().length - 1];
    }

    public ParticleModule.ParticleSet getSelectedSpawnParticleColor() {
        return this.selectedSpawnParticleColor;
    }

    public void increaseSelectedSpawnParticleAmount(int i) {
        this.selectedSpawnParticleAmount = this.selectedSpawnParticleAmount + i < 1000 ? (this.selectedSpawnParticleAmount += i) : 1000;
    }

    public void decreaseSelectedSpawnParticleAmount(int i) {
        this.selectedSpawnParticleAmount = this.selectedSpawnParticleAmount - i > 1 ? (this.selectedSpawnParticleAmount -= i) : 1;
    }

    public int getSelectedSpawnParticleAmount() {
        return this.selectedSpawnParticleAmount;
    }

    public void increaseSelectedSpawnParticleSpread(int i) {
        this.selectedSpawnParticleSpread = this.selectedSpawnParticleSpread + i < 500 ? (this.selectedSpawnParticleSpread += i) : 500;
    }

    public void decreaseSelectedSpawnParticleSpread(int i) {
        this.selectedSpawnParticleSpread = this.selectedSpawnParticleSpread - i > 1 ? (this.selectedSpawnParticleSpread -= i) : 1;
    }

    public int getSelectedSpawnParticleSpread() {
        return this.selectedSpawnParticleSpread;
    }

    public void increaseSelectedSpawnParticleAngle(int i) {
        this.selectedSpawnParticleAngle = this.selectedSpawnParticleAngle + i < 360 ? (this.selectedSpawnParticleAngle += i) : 360;
    }

    public void decreaseSelectedSpawnParticleAngle(int i) {
        this.selectedSpawnParticleAngle = this.selectedSpawnParticleAngle - i > 1 ? (this.selectedSpawnParticleAngle -= i) : 1;
    }

    public int getSelectedSpawnParticleAngle() {
        return this.selectedSpawnParticleAngle;
    }

    public void increaseEssenceAmount(int i) {
        this.spawnEssenceAmount = this.spawnEssenceAmount + i < 100 ? (this.spawnEssenceAmount += i) : 100;
    }

    public void decreaseEssenceAmount(int i) {
        this.spawnEssenceAmount = this.spawnEssenceAmount - i > 1 ? (this.spawnEssenceAmount -= i) : 1;
    }

    public int getEssenceAmount() {
        return this.spawnEssenceAmount;
    }

    public void increaseEssenceValue(int i) {
        this.spawnEssenceValue = this.spawnEssenceValue + i < 25 ? (this.spawnEssenceValue += i) : 25;
    }

    public void decreaseEssenceValue(int i) {
        this.spawnEssenceValue = this.spawnEssenceValue - i > 1 ? (this.spawnEssenceValue -= i) : 1;
    }

    public int getEssenceValue() {
        return this.spawnEssenceValue;
    }

    public void increaseMissileType() {
        this.missileType = this.missileType.ordinal() != MissileModule.MissileType.values().length - 1 ? MissileModule.MissileType.values()[this.missileType.ordinal() + 1] : MissileModule.MissileType.values()[0];
    }

    public void decreaseMissileType() {
        this.missileType = this.missileType.ordinal() != 0 ? MissileModule.MissileType.values()[this.missileType.ordinal() - 1] : MissileModule.MissileType.values()[MissileModule.MissileType.values().length - 1];
    }

    public MissileModule.MissileType getMissileType() {
        return this.missileType;
    }

    @Override
    public int getCenterX() {
        return (int)((float)this.x + this.mask.getWidth() / 2.0f);
    }

    @Override
    public int getCenterY() {
        return (int)((float)this.y + this.mask.getHeight() / 2.0f);
    }

    @Override
    public int getLeftX() {
        return this.x;
    }

    @Override
    public int getTopY() {
        return this.y;
    }

    @Override
    public int getRightX() {
        return (int)((float)this.x + this.mask.getWidth());
    }

    @Override
    public int getBottomY() {
        return (int)((float)this.y + this.mask.getHeight());
    }
}

