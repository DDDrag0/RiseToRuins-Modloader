package rtr.map;
import rtr.map.tileSets.TileSetBase;
import rtr.map.tileSets.objects.AmmoStorage;
import rtr.map.tileSets.objects.AncientCullisGate;
import rtr.map.tileSets.objects.AncientRadiancePool;
import rtr.map.tileSets.objects.Ancillary;
import rtr.map.tileSets.objects.AnimalPen;
import rtr.map.tileSets.objects.Armorsmithy;
import rtr.map.tileSets.objects.AttractTower;
import rtr.map.tileSets.objects.BallistaTower;
import rtr.map.tileSets.objects.BanishTower;
import rtr.map.tileSets.objects.Bottler;
import rtr.map.tileSets.objects.BowTower;
import rtr.map.tileSets.objects.Bowyer;
import rtr.map.tileSets.objects.BulletTower;
import rtr.map.tileSets.objects.Burner;
import rtr.map.tileSets.objects.Castle;
import rtr.map.tileSets.objects.Clinic;
import rtr.map.tileSets.objects.CluckerCoop;
import rtr.map.tileSets.objects.CourierStation;
import rtr.map.tileSets.objects.CrylithiumCurtainWall;
import rtr.map.tileSets.objects.CrylithiumFirePit;
import rtr.map.tileSets.objects.CrylithiumWall;
import rtr.map.tileSets.objects.CrylithiumWallGateNS;
import rtr.map.tileSets.objects.CrylithiumWallGateWE;
import rtr.map.tileSets.objects.CrystalGolemCombobulator;
import rtr.map.tileSets.objects.CrystalHarvestry;
import rtr.map.tileSets.objects.CrystalMotivator;
import rtr.map.tileSets.objects.CrystalStorage;
import rtr.map.tileSets.objects.Crystillery;
import rtr.map.tileSets.objects.CubeEGolemCombobulator;
import rtr.map.tileSets.objects.CullisGate;
import rtr.map.tileSets.objects.CurtainWall;
import rtr.map.tileSets.objects.DoggoHouse;
import rtr.map.tileSets.objects.ElementalBoltTower;
import rtr.map.tileSets.objects.EquipmentStorage;
import rtr.map.tileSets.objects.EssenceAltar;
import rtr.map.tileSets.objects.EssenceCollector;
import rtr.map.tileSets.objects.Farm;
import rtr.map.tileSets.objects.FirePit;
import rtr.map.tileSets.objects.FoodStorage;
import rtr.map.tileSets.objects.Forge;
import rtr.map.tileSets.objects.GodTower;
import rtr.map.tileSets.objects.GodWall;
import rtr.map.tileSets.objects.GoldStorage;
import rtr.map.tileSets.objects.Housing;
import rtr.map.tileSets.objects.KeyShack;
import rtr.map.tileSets.objects.Kitchen;
import rtr.map.tileSets.objects.Landfill;
import rtr.map.tileSets.objects.LargeCrylithiumFirePit;
import rtr.map.tileSets.objects.LargeFirePit;
import rtr.map.tileSets.objects.LargeFountain;
import rtr.map.tileSets.objects.LightningRod;
import rtr.map.tileSets.objects.LootBox;
import rtr.map.tileSets.objects.LumberMill;
import rtr.map.tileSets.objects.LumberShack;
import rtr.map.tileSets.objects.MaintenanceBuilding;
import rtr.map.tileSets.objects.Marketplace;
import rtr.map.tileSets.objects.MigrationWayStation;
import rtr.map.tileSets.objects.MineralStorage;
import rtr.map.tileSets.objects.MiningFacility;
import rtr.map.tileSets.objects.MiscellaneousStorage;
import rtr.map.tileSets.objects.Outpost;
import rtr.map.tileSets.objects.PhantomDartTower;
import rtr.map.tileSets.objects.Processor;
import rtr.map.tileSets.objects.RainCatcher;
import rtr.map.tileSets.objects.RangerLodge;
import rtr.map.tileSets.objects.RecombobulatorTower;
import rtr.map.tileSets.objects.Reliquary;
import rtr.map.tileSets.objects.RockStorage;
import rtr.map.tileSets.objects.RockTumbler;
import rtr.map.tileSets.objects.SlingTower;
import rtr.map.tileSets.objects.SmallFountain;
import rtr.map.tileSets.objects.SprayTower;
import rtr.map.tileSets.objects.StaticTower;
import rtr.map.tileSets.objects.StoneCuttery;
import rtr.map.tileSets.objects.StoneGolemCombobulator;
import rtr.map.tileSets.objects.StoneWall;
import rtr.map.tileSets.objects.StoneWallGateNS;
import rtr.map.tileSets.objects.StoneWallGateWE;
import rtr.map.tileSets.objects.Toolsmithy;
import rtr.map.tileSets.objects.TrashCan;
import rtr.map.tileSets.objects.TrashyCubePile;
import rtr.map.tileSets.objects.TrashyCubeWall;
import rtr.map.tileSets.objects.WaterPurifier;
import rtr.map.tileSets.objects.WayMakerShack;
import rtr.map.tileSets.objects.Well;
import rtr.map.tileSets.objects.WoodFence;
import rtr.map.tileSets.objects.WoodFenceGateNS;
import rtr.map.tileSets.objects.WoodFenceGateWE;
import rtr.map.tileSets.objects.WoodGolemCombobulator;
import rtr.map.tileSets.objects.WoodStorage;
import rtr.map.tileSets.objects.corrupted.CorruptedFireBowTower;
import rtr.map.tileSets.objects.corrupted.CorruptedFirePhantomDartTower;
import rtr.map.tileSets.objects.corrupted.CorruptedFirePit;
import rtr.map.tileSets.objects.corrupted.CorruptedGraveyardLarge;
import rtr.map.tileSets.objects.corrupted.CorruptedGraveyardMedium;
import rtr.map.tileSets.objects.corrupted.CorruptedGraveyardSmall;
import rtr.map.tileSets.objects.corrupted.CorruptedIceBowTower;
import rtr.map.tileSets.objects.corrupted.CorruptedIcePhantomDartTower;
import rtr.map.tileSets.objects.corrupted.CorruptedLargeFirePit;
import rtr.map.tileSets.objects.corrupted.CorruptedLightningBowTower;
import rtr.map.tileSets.objects.corrupted.CorruptedLightningPhantomDartTower;
import rtr.map.tileSets.objects.corrupted.CorruptedPoisonBowTower;
import rtr.map.tileSets.objects.corrupted.CorruptedPoisonPhantomDartTower;
import rtr.map.tileSets.roads.CobbleAndBoardRoad;
import rtr.map.tileSets.roads.CobbleAndBoardRoadDebris;
import rtr.map.tileSets.roads.CobbleAndLogPath;
import rtr.map.tileSets.roads.CobbleAndLogPathDebris;
import rtr.map.tileSets.roads.CutStoneAndBoardRoad;
import rtr.map.tileSets.roads.CutStoneAndBoardRoadDebris;
import rtr.map.tileSets.roads.LogPath;
import rtr.map.tileSets.roads.LogPathDebris;
import rtr.map.tileSets.roads.Path;
import rtr.map.tileSets.terrain.Bricks;
import rtr.map.tileSets.terrain.BricksAccent;
import rtr.map.tileSets.terrain.CactusGreen;
import rtr.map.tileSets.terrain.CactusGreenAccent;
import rtr.map.tileSets.terrain.CactusGreenCollected;
import rtr.map.tileSets.terrain.Carrots;
import rtr.map.tileSets.terrain.CarrotsAccent;
import rtr.map.tileSets.terrain.CarrotsCollected;
import rtr.map.tileSets.terrain.Corruption;
import rtr.map.tileSets.terrain.CorruptionAccent;
import rtr.map.tileSets.terrain.CrystalsBlue;
import rtr.map.tileSets.terrain.CrystalsBlueAccent;
import rtr.map.tileSets.terrain.CrystalsBlueCollected;
import rtr.map.tileSets.terrain.CrystalsGreen;
import rtr.map.tileSets.terrain.CrystalsGreenAccent;
import rtr.map.tileSets.terrain.CrystalsGreenCollected;
import rtr.map.tileSets.terrain.CrystalsPurple;
import rtr.map.tileSets.terrain.CrystalsPurpleAccent;
import rtr.map.tileSets.terrain.CrystalsPurpleCollected;
import rtr.map.tileSets.terrain.CrystalsRed;
import rtr.map.tileSets.terrain.CrystalsRedAccent;
import rtr.map.tileSets.terrain.CrystalsRedCollected;
import rtr.map.tileSets.terrain.DirtBrown;
import rtr.map.tileSets.terrain.DirtBrownAccent;
import rtr.map.tileSets.terrain.DirtDarkBrown;
import rtr.map.tileSets.terrain.DirtDarkBrownAccent;
import rtr.map.tileSets.terrain.DirtLightBrown;
import rtr.map.tileSets.terrain.DirtLightBrownAccent;
import rtr.map.tileSets.terrain.FlowersBlack;
import rtr.map.tileSets.terrain.FlowersBlackAccent;
import rtr.map.tileSets.terrain.FlowersBlue;
import rtr.map.tileSets.terrain.FlowersBlueAccent;
import rtr.map.tileSets.terrain.FlowersPurple;
import rtr.map.tileSets.terrain.FlowersPurpleAccent;
import rtr.map.tileSets.terrain.FlowersRed;
import rtr.map.tileSets.terrain.FlowersRedAccent;
import rtr.map.tileSets.terrain.FlowersWhite;
import rtr.map.tileSets.terrain.FlowersWhiteAccent;
import rtr.map.tileSets.terrain.FlowersYellow;
import rtr.map.tileSets.terrain.FlowersYellowAccent;
import rtr.map.tileSets.terrain.GrassEmeraldGreen;
import rtr.map.tileSets.terrain.GrassEmeraldGreenAccent;
import rtr.map.tileSets.terrain.GrassGreen;
import rtr.map.tileSets.terrain.GrassGreenAccent;
import rtr.map.tileSets.terrain.GrassTeal;
import rtr.map.tileSets.terrain.GrassTealAccent;
import rtr.map.tileSets.terrain.GrassYellowBrown;
import rtr.map.tileSets.terrain.GrassYellowBrownAccent;
import rtr.map.tileSets.terrain.GravelBlue;
import rtr.map.tileSets.terrain.GravelBlueAccent;
import rtr.map.tileSets.terrain.GravelGray;
import rtr.map.tileSets.terrain.GravelGrayAccent;
import rtr.map.tileSets.terrain.GravelRed;
import rtr.map.tileSets.terrain.GravelRedAccent;
import rtr.map.tileSets.terrain.Lava;
import rtr.map.tileSets.terrain.LavaAccent;
import rtr.map.tileSets.terrain.Melons;
import rtr.map.tileSets.terrain.MelonsAccent;
import rtr.map.tileSets.terrain.MelonsCollected;
import rtr.map.tileSets.terrain.Mushrooms;
import rtr.map.tileSets.terrain.MushroomsAccent;
import rtr.map.tileSets.terrain.MushroomsCollected;
import rtr.map.tileSets.terrain.Potatoes;
import rtr.map.tileSets.terrain.PotatoesAccent;
import rtr.map.tileSets.terrain.PotatoesCollected;
import rtr.map.tileSets.terrain.PotatoesHoly;
import rtr.map.tileSets.terrain.PotatoesHolyAccent;
import rtr.map.tileSets.terrain.PotatoesHolyCollected;
import rtr.map.tileSets.terrain.RockBlack;
import rtr.map.tileSets.terrain.RockBlackAccent;
import rtr.map.tileSets.terrain.RockBlackCollected;
import rtr.map.tileSets.terrain.RockBrown;
import rtr.map.tileSets.terrain.RockBrownAccent;
import rtr.map.tileSets.terrain.RockBrownCollected;
import rtr.map.tileSets.terrain.RockGray;
import rtr.map.tileSets.terrain.RockGrayAccent;
import rtr.map.tileSets.terrain.RockGrayCollected;
import rtr.map.tileSets.terrain.RockRed;
import rtr.map.tileSets.terrain.RockRedAccent;
import rtr.map.tileSets.terrain.RockRedCollected;
import rtr.map.tileSets.terrain.RockWhite;
import rtr.map.tileSets.terrain.RockWhiteAccent;
import rtr.map.tileSets.terrain.RockWhiteCollected;
import rtr.map.tileSets.terrain.SandBlack;
import rtr.map.tileSets.terrain.SandBlackAccent;
import rtr.map.tileSets.terrain.SandRed;
import rtr.map.tileSets.terrain.SandRedAccent;
import rtr.map.tileSets.terrain.SandTan;
import rtr.map.tileSets.terrain.SandTanAccent;
import rtr.map.tileSets.terrain.SandstoneBlack;
import rtr.map.tileSets.terrain.SandstoneBlackAccent;
import rtr.map.tileSets.terrain.SandstoneBlackCollected;
import rtr.map.tileSets.terrain.SandstoneRed;
import rtr.map.tileSets.terrain.SandstoneRedAccent;
import rtr.map.tileSets.terrain.SandstoneRedCollected;
import rtr.map.tileSets.terrain.SandstoneTan;
import rtr.map.tileSets.terrain.SandstoneTanAccent;
import rtr.map.tileSets.terrain.SandstoneTanCollected;
import rtr.map.tileSets.terrain.SnowWhite;
import rtr.map.tileSets.terrain.SnowWhiteAccent;
import rtr.map.tileSets.terrain.Tar;
import rtr.map.tileSets.terrain.TarAccent;
import rtr.map.tileSets.terrain.Tiles;
import rtr.map.tileSets.terrain.TilesAccent;
import rtr.map.tileSets.terrain.TreesBlue;
import rtr.map.tileSets.terrain.TreesBlueAccent;
import rtr.map.tileSets.terrain.TreesBlueCollected;
import rtr.map.tileSets.terrain.TreesBlueDead;
import rtr.map.tileSets.terrain.TreesBrown;
import rtr.map.tileSets.terrain.TreesBrownAccent;
import rtr.map.tileSets.terrain.TreesBrownCollected;
import rtr.map.tileSets.terrain.TreesBrownDead;
import rtr.map.tileSets.terrain.TreesDarkGreen;
import rtr.map.tileSets.terrain.TreesDarkGreenAccent;
import rtr.map.tileSets.terrain.TreesDarkGreenCollected;
import rtr.map.tileSets.terrain.TreesDarkGreenDead;
import rtr.map.tileSets.terrain.TreesGreen;
import rtr.map.tileSets.terrain.TreesGreenAccent;
import rtr.map.tileSets.terrain.TreesGreenCollected;
import rtr.map.tileSets.terrain.TreesGreenDead;
import rtr.map.tileSets.terrain.TreesLavender;
import rtr.map.tileSets.terrain.TreesLavenderAccent;
import rtr.map.tileSets.terrain.TreesLavenderCollected;
import rtr.map.tileSets.terrain.TreesLavenderDead;
import rtr.map.tileSets.terrain.TreesPaleBlue;
import rtr.map.tileSets.terrain.TreesPaleBlueAccent;
import rtr.map.tileSets.terrain.TreesPaleBlueCollected;
import rtr.map.tileSets.terrain.TreesPaleBlueDead;
import rtr.map.tileSets.terrain.TreesRed;
import rtr.map.tileSets.terrain.TreesRedAccent;
import rtr.map.tileSets.terrain.TreesRedCollected;
import rtr.map.tileSets.terrain.TreesRedDead;
import rtr.map.tileSets.terrain.Turnips;
import rtr.map.tileSets.terrain.TurnipsAccent;
import rtr.map.tileSets.terrain.TurnipsCollected;
import rtr.map.tileSets.terrain.Water;
import rtr.map.tileSets.terrain.WaterAccent;
import rtr.map.tileSets.terrain.WaterDry;
import rtr.map.tileSets.terrain.WaterDryAccent;
import rtr.map.tileSets.terrain.WaterFrozen;
import rtr.map.tileSets.terrain.WaterFrozenAccent;
import rtr.map.tileSets.terrain.WaterPartiallyDry;
import rtr.map.tileSets.terrain.WaterPartiallyDryAccent;
import rtr.map.tileSets.terrain.WaterPartiallyDryFrozen;
import rtr.map.tileSets.terrain.WaterPartiallyDryFrozenAccent;
import rtr.map.tileSets.terrain.WaterPartiallyFrozen;
import rtr.map.tileSets.terrain.WaterPartiallyFrozenAccent;

public enum TileSet {
            CASTLE_1(new Castle(MapTilesLoader.access$0(0, 0), 0)),
            CASTLE_2(new Castle(MapTilesLoader.access$0(0, 1), 1)),
            CASTLE_3(new Castle(MapTilesLoader.access$0(0, 2), 2)),
            CASTLE_4(new Castle(MapTilesLoader.access$0(0, 3), 3)),
            CASTLE_5(new Castle(MapTilesLoader.access$0(0, 4), 4)),
            CASTLE_6(new Castle(MapTilesLoader.access$0(0, 5), 5)),
            CASTLE_7(new Castle(MapTilesLoader.access$0(0, 6), 6)),
            CASTLE_8(new Castle(MapTilesLoader.access$0(0, 7), 7)),
            CASTLE_9(new Castle(MapTilesLoader.access$0(0, 8), 8)),
            CASTLE_10(new Castle(MapTilesLoader.access$0(0, 9), 9)),
            CASTLE_11(new Castle(MapTilesLoader.access$0(0, 10), 10)),
            CASTLE_12(new Castle(MapTilesLoader.access$0(0, 11), 11)),
            CASTLE_13(new Castle(MapTilesLoader.access$0(0, 12), 12)),
            CASTLE_14(new Castle(MapTilesLoader.access$0(0, 13), 13)),
            CASTLE_15(new Castle(MapTilesLoader.access$0(0, 14), 14)),
            ANCILLARY(new Ancillary(MapTilesLoader.access$0(1, 0), 0)),
            ANCILLARY_UPGRADE_1(new Ancillary(MapTilesLoader.access$0(1, 1), 1)),
            ANCILLARY_UPGRADE_2(new Ancillary(MapTilesLoader.access$0(1, 2), 2)),
            ANCILLARY_UPGRADE_3(new Ancillary(MapTilesLoader.access$0(1, 3), 3)),
            ANCILLARY_UPGRADE_4(new Ancillary(MapTilesLoader.access$0(1, 4), 4)),
            ANCIENT_CULLIS_GATE(new AncientCullisGate(MapTilesLoader.access$0(2, 0))),
            ANCIENT_RADIANCE_POOL(new AncientRadiancePool(MapTilesLoader.access$0(3, 0))),
            ARMORSMITHY(new Armorsmithy(MapTilesLoader.access$0(4, 0), 0)),
            ARMORSMITHY_UPGRADE_1(new Armorsmithy(MapTilesLoader.access$0(4, 1), 1)),
            ARMORSMITHY_UPGRADE_2(new Armorsmithy(MapTilesLoader.access$0(4, 2), 2)),
            AMMO_STORAGE(new AmmoStorage(MapTilesLoader.access$0(5, 0), 0)),
            AMMO_STORAGE_UPGRADE_1(new AmmoStorage(MapTilesLoader.access$0(5, 1), 1)),
            AMMO_STORAGE_UPGRADE_2(new AmmoStorage(MapTilesLoader.access$0(5, 2), 2)),
            AMMO_STORAGE_UPGRADE_3(new AmmoStorage(MapTilesLoader.access$0(5, 3), 3)),
            AMMO_STORAGE_UPGRADE_4(new AmmoStorage(MapTilesLoader.access$0(5, 4), 4)),
            ATTRACT_TOWER(new AttractTower(MapTilesLoader.access$0(6, 0), 0)),
            ATTRACT_TOWER_UPGRADE_1(new AttractTower(MapTilesLoader.access$0(6, 1), 1)),
            ATTRACT_TOWER_UPGRADE_2(new AttractTower(MapTilesLoader.access$0(6, 2), 2)),
            ATTRACT_TOWER_UPGRADE_3(new AttractTower(MapTilesLoader.access$0(6, 3), 3)),
            BALLISTA_TOWER(new BallistaTower(MapTilesLoader.access$0(7, 0), 0)),
            BALLISTA_TOWER_UPGRADE_1(new BallistaTower(MapTilesLoader.access$0(7, 1), 1)),
            BALLISTA_TOWER_UPGRADE_2(new BallistaTower(MapTilesLoader.access$0(7, 2), 2)),
            BALLISTA_TOWER_UPGRADE_3_FIRE(new BallistaTower(MapTilesLoader.access$0(7, 3), 3)),
            BALLISTA_TOWER_UPGRADE_3_POISON(new BallistaTower(MapTilesLoader.access$0(7, 4), 4)),
            BALLISTA_TOWER_UPGRADE_3_ICE(new BallistaTower(MapTilesLoader.access$0(7, 5), 5)),
            BANISH_TOWER(new BanishTower(MapTilesLoader.access$0(8, 0), 0)),
            BANISH_TOWER_UPGRADE_1(new BanishTower(MapTilesLoader.access$0(8, 1), 1)),
            BANISH_TOWER_UPGRADE_2(new BanishTower(MapTilesLoader.access$0(8, 2), 2)),
            BANISH_TOWER_UPGRADE_3(new BanishTower(MapTilesLoader.access$0(8, 3), 3)),
            RANGER_LODGE(new RangerLodge(MapTilesLoader.access$0(9, 0), 0)),
            RANGER_LODGE_UPGRADE_1(new RangerLodge(MapTilesLoader.access$0(9, 1), 1)),
            RANGER_LODGE_UPGRADE_2(new RangerLodge(MapTilesLoader.access$0(9, 2), 2)),
            BOTTLER(new Bottler(MapTilesLoader.access$0(10, 0), 0)),
            BOTTLER_UPGRADE_1(new Bottler(MapTilesLoader.access$0(10, 1), 1)),
            BOTTLER_UPGRADE_2(new Bottler(MapTilesLoader.access$0(10, 2), 2)),
            BOW_TOWER(new BowTower(MapTilesLoader.access$0(11, 0), 0)),
            BOW_TOWER_UPGRADE_1(new BowTower(MapTilesLoader.access$0(11, 1), 1)),
            BOW_TOWER_UPGRADE_2(new BowTower(MapTilesLoader.access$0(11, 2), 2)),
            BOW_TOWER_UPGRADE_3_FIRE(new BowTower(MapTilesLoader.access$0(11, 3), 3)),
            BOW_TOWER_UPGRADE_3_POISON(new BowTower(MapTilesLoader.access$0(11, 4), 4)),
            BOW_TOWER_UPGRADE_3_ICE(new BowTower(MapTilesLoader.access$0(11, 5), 5)),
            BOWYER(new Bowyer(MapTilesLoader.access$0(12, 0), 0)),
            BOWYER_UPGRADE_1(new Bowyer(MapTilesLoader.access$0(12, 1), 1)),
            BOWYER_UPGRADE_2(new Bowyer(MapTilesLoader.access$0(12, 2), 2)),
            BULLET_TOWER(new BulletTower(MapTilesLoader.access$0(13, 0), 0)),
            BULLET_TOWER_UPGRADE_1(new BulletTower(MapTilesLoader.access$0(13, 1), 1)),
            BULLET_TOWER_UPGRADE_2(new BulletTower(MapTilesLoader.access$0(13, 2), 2)),
            BULLET_TOWER_UPGRADE_3_FIRE(new BulletTower(MapTilesLoader.access$0(13, 3), 3)),
            BULLET_TOWER_UPGRADE_3_LIGHTNING(new BulletTower(MapTilesLoader.access$0(13, 4), 4)),
            BULLET_TOWER_UPGRADE_3_ICE(new BulletTower(MapTilesLoader.access$0(13, 5), 5)),
            CLINIC(new Clinic(MapTilesLoader.access$0(14, 0), 0)),
            CLINIC_UPGRADE_1(new Clinic(MapTilesLoader.access$0(14, 1), 1)),
            CLINIC_UPGRADE_2(new Clinic(MapTilesLoader.access$0(14, 2), 2)),
            CRYSTAL_GOLEM_COMBOBULATOR(new CrystalGolemCombobulator(MapTilesLoader.access$0(15, 0), 0)),
            CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_1(new CrystalGolemCombobulator(MapTilesLoader.access$0(15, 1), 1)),
            CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_2(new CrystalGolemCombobulator(MapTilesLoader.access$0(15, 2), 2)),
            CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING(new CrystalGolemCombobulator(MapTilesLoader.access$0(15, 3), 3)),
            CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_ICE(new CrystalGolemCombobulator(MapTilesLoader.access$0(15, 4), 4)),
            CRYSTAL_GOLEM_COMBOBULATOR_UPGRADE_3_FIRE(new CrystalGolemCombobulator(MapTilesLoader.access$0(15, 5), 5)),
            CRYSTAL_HARVESTRY(new CrystalHarvestry(MapTilesLoader.access$0(16, 0), 0)),
            CRYSTAL_HARVESTRY_UPGRADE_1(new CrystalHarvestry(MapTilesLoader.access$0(16, 1), 1)),
            CRYSTAL_HARVESTRY_UPGRADE_2(new CrystalHarvestry(MapTilesLoader.access$0(16, 2), 2)),
            CRYSTAL_MOTIVATOR(new CrystalMotivator(MapTilesLoader.access$0(17, 0))),
            CRYSTAL_STORAGE(new CrystalStorage(MapTilesLoader.access$0(18, 0), 0)),
            CRYSTAL_STORAGE_UPGRADE_1(new CrystalStorage(MapTilesLoader.access$0(18, 1), 1)),
            CRYSTAL_STORAGE_UPGRADE_2(new CrystalStorage(MapTilesLoader.access$0(18, 2), 2)),
            CRYSTAL_STORAGE_UPGRADE_3(new CrystalStorage(MapTilesLoader.access$0(18, 3), 3)),
            CRYSTAL_STORAGE_UPGRADE_4(new CrystalStorage(MapTilesLoader.access$0(18, 4), 4)),
            CRYSTILLERY(new Crystillery(MapTilesLoader.access$0(19, 0), 0)),
            CRYSTILLERY_UPGRADE_1(new Crystillery(MapTilesLoader.access$0(19, 1), 1)),
            CRYSTILLERY_UPGRADE_2(new Crystillery(MapTilesLoader.access$0(19, 2), 2)),
            CULLIS_GATE(new CullisGate(MapTilesLoader.access$0(20, 0))),
            DOGGO_HOUSE(new DoggoHouse(MapTilesLoader.access$0(21, 0), 0)),
            DOGGO_HOUSE_UPGRADE_1(new DoggoHouse(MapTilesLoader.access$0(21, 1), 1)),
            DOGGO_HOUSE_UPGRADE_2(new DoggoHouse(MapTilesLoader.access$0(21, 2), 2)),
            EQUIPMENT_STORAGE(new EquipmentStorage(MapTilesLoader.access$0(22, 0), 0)),
            EQUIPMENT_STORAGE_UPGRADE_1(new EquipmentStorage(MapTilesLoader.access$0(22, 1), 1)),
            EQUIPMENT_STORAGE_UPGRADE_2(new EquipmentStorage(MapTilesLoader.access$0(22, 2), 2)),
            EQUIPMENT_STORAGE_UPGRADE_3(new EquipmentStorage(MapTilesLoader.access$0(22, 3), 3)),
            EQUIPMENT_STORAGE_UPGRADE_4(new EquipmentStorage(MapTilesLoader.access$0(22, 4), 4)),
            ESSENCE_COLLECTOR(new EssenceCollector(MapTilesLoader.access$0(23, 0), 0)),
            ESSENCE_COLLECTOR_UPGRADE_1(new EssenceCollector(MapTilesLoader.access$0(23, 1), 1)),
            ESSENCE_COLLECTOR_UPGRADE_2(new EssenceCollector(MapTilesLoader.access$0(23, 2), 2)),
            ELEMENTAL_BOLT_TOWER(new ElementalBoltTower(MapTilesLoader.access$0(24, 0), 0)),
            ELEMENTAL_BOLT_TOWER_UPGRADE_1(new ElementalBoltTower(MapTilesLoader.access$0(24, 1), 1)),
            ELEMENTAL_BOLT_TOWER_UPGRADE_2(new ElementalBoltTower(MapTilesLoader.access$0(24, 2), 2)),
            ELEMENTAL_BOLT_TOWER_UPGRADE_3_FIRE(new ElementalBoltTower(MapTilesLoader.access$0(24, 3), 3)),
            ELEMENTAL_BOLT_TOWER_UPGRADE_3_LIGHTNING(new ElementalBoltTower(MapTilesLoader.access$0(24, 4), 4)),
            ELEMENTAL_BOLT_TOWER_UPGRADE_3_ICE(new ElementalBoltTower(MapTilesLoader.access$0(24, 5), 5)),
            FARM(new Farm(MapTilesLoader.access$0(25, 0), 0)),
            FARM_UPGRADE_1(new Farm(MapTilesLoader.access$0(25, 1), 1)),
            FARM_UPGRADE_2(new Farm(MapTilesLoader.access$0(25, 2), 2)),
            FIRE_PIT(new FirePit(MapTilesLoader.access$0(26, 0))),
            LARGE_FIRE_PIT(new LargeFirePit(MapTilesLoader.access$0(27, 0))),
            FOOD_STORAGE(new FoodStorage(MapTilesLoader.access$0(28, 0), 0)),
            FOOD_STORAGE_UPGRADE_1(new FoodStorage(MapTilesLoader.access$0(28, 1), 1)),
            FOOD_STORAGE_UPGRADE_2(new FoodStorage(MapTilesLoader.access$0(28, 2), 2)),
            FOOD_STORAGE_UPGRADE_3(new FoodStorage(MapTilesLoader.access$0(28, 3), 3)),
            FOOD_STORAGE_UPGRADE_4(new FoodStorage(MapTilesLoader.access$0(28, 4), 4)),
            FORGE(new Forge(MapTilesLoader.access$0(29, 0), 0)),
            FORGE_UPGRADE_1(new Forge(MapTilesLoader.access$0(29, 1), 1)),
            FORGE_UPGRADE_2(new Forge(MapTilesLoader.access$0(29, 2), 2)),
            GOLD_STORAGE(new GoldStorage(MapTilesLoader.access$0(30, 0), 0)),
            GOLD_STORAGE_UPGRADE_1(new GoldStorage(MapTilesLoader.access$0(30, 1), 1)),
            GOLD_STORAGE_UPGRADE_2(new GoldStorage(MapTilesLoader.access$0(30, 2), 2)),
            GOLD_STORAGE_UPGRADE_3(new GoldStorage(MapTilesLoader.access$0(30, 3), 3)),
            GOLD_STORAGE_UPGRADE_4(new GoldStorage(MapTilesLoader.access$0(30, 4), 4)),
            HOUSING(new Housing(MapTilesLoader.access$0(31, 0), 0)),
            HOUSING_UPGRADE_1_OCCUPANCY(new Housing(MapTilesLoader.access$0(31, 1), 1)),
            HOUSING_UPGRADE_2_OCCUPANCY(new Housing(MapTilesLoader.access$0(31, 2), 2)),
            HOUSING_UPGRADE_3_OCCUPANCY(new Housing(MapTilesLoader.access$0(31, 3), 3)),
            HOUSING_UPGRADE_4_OCCUPANCY(new Housing(MapTilesLoader.access$0(31, 4), 4)),
            HOUSING_UPGRADE_1_STANDARD(new Housing(MapTilesLoader.access$0(31, 5), 5)),
            HOUSING_UPGRADE_2_STANDARD(new Housing(MapTilesLoader.access$0(31, 6), 6)),
            HOUSING_UPGRADE_3_STANDARD(new Housing(MapTilesLoader.access$0(31, 7), 7)),
            HOUSING_UPGRADE_4_STANDARD(new Housing(MapTilesLoader.access$0(31, 8), 8)),
            HOUSING_UPGRADE_1_QUALITY(new Housing(MapTilesLoader.access$0(31, 9), 9)),
            HOUSING_UPGRADE_2_QUALITY(new Housing(MapTilesLoader.access$0(31, 10), 10)),
            HOUSING_UPGRADE_3_QUALITY(new Housing(MapTilesLoader.access$0(31, 11), 11)),
            HOUSING_UPGRADE_4_QUALITY(new Housing(MapTilesLoader.access$0(31, 12), 12)),
            KEY_SHACK(new KeyShack(MapTilesLoader.access$0(32, 0), 0)),
            KEY_SHACK_UPGRADE_1(new KeyShack(MapTilesLoader.access$0(32, 1), 1)),
            KEY_SHACK_UPGRADE_2(new KeyShack(MapTilesLoader.access$0(32, 2), 2)),
            KEY_SHACK_UPGRADE_3(new KeyShack(MapTilesLoader.access$0(32, 3), 3)),
            KEY_SHACK_UPGRADE_4(new KeyShack(MapTilesLoader.access$0(32, 4), 4)),
            LARGE_FOUNTAIN(new LargeFountain(MapTilesLoader.access$0(33, 0))),
            LOOT_BOX(new LootBox(MapTilesLoader.access$0(34, 0))),
            LIGHTNING_ROD(new LightningRod(MapTilesLoader.access$0(35, 0), 0)),
            LIGHTNING_ROD_UPGRADE_1(new LightningRod(MapTilesLoader.access$0(35, 1), 1)),
            LIGHTNING_ROD_UPGRADE_2(new LightningRod(MapTilesLoader.access$0(35, 2), 2)),
            CORRUPTED_GRAVEYARD_SMALL(new CorruptedGraveyardSmall(MapTilesLoader.access$0(36, 0))),
            CORRUPTED_GRAVEYARD_MEDIUM(new CorruptedGraveyardMedium(MapTilesLoader.access$0(37, 0))),
            CORRUPTED_GRAVEYARD_LARGE(new CorruptedGraveyardLarge(MapTilesLoader.access$0(38, 0))),
            CORRUPTED_FIRE_BOW_TOWER(new CorruptedFireBowTower(MapTilesLoader.access$0(39, 0))),
            CORRUPTED_ICE_BOW_TOWER(new CorruptedIceBowTower(MapTilesLoader.access$0(40, 0))),
            CORRUPTED_POISON_BOW_TOWER(new CorruptedPoisonBowTower(MapTilesLoader.access$0(41, 0))),
            CORRUPTED_LIGHTNING_BOW_TOWER(new CorruptedLightningBowTower(MapTilesLoader.access$0(42, 0))),
            CORRUPTED_FIRE_PHANTOM_DART_TOWER(new CorruptedFirePhantomDartTower(MapTilesLoader.access$0(43, 0))),
            CORRUPTED_ICE_PHANTOM_DART_TOWER(new CorruptedIcePhantomDartTower(MapTilesLoader.access$0(44, 0))),
            CORRUPTED_POISON_PHANTOM_DART_TOWER(new CorruptedPoisonPhantomDartTower(MapTilesLoader.access$0(45, 0))),
            CORRUPTED_LIGHTNING_PHANTOM_DART_TOWER(new CorruptedLightningPhantomDartTower(MapTilesLoader.access$0(46, 0))),
            CORRUPTED_LARGE_FIRE_PIT(new CorruptedLargeFirePit(MapTilesLoader.access$0(47, 0))),
            CORRUPTED_FIRE_PIT(new CorruptedFirePit(MapTilesLoader.access$0(48, 0))),
            COURIER_STATION(new CourierStation(MapTilesLoader.access$0(49, 0), 0)),
            COURIER_STATION_UPGRADE_1(new CourierStation(MapTilesLoader.access$0(49, 1), 1)),
            COURIER_STATION_UPGRADE_2(new CourierStation(MapTilesLoader.access$0(49, 2), 2)),
            KITCHEN(new Kitchen(MapTilesLoader.access$0(50, 0), 0)),
            KITCHEN_UPGRADE_1(new Kitchen(MapTilesLoader.access$0(50, 1), 1)),
            KITCHEN_UPGRADE_2(new Kitchen(MapTilesLoader.access$0(50, 2), 2)),
            LUMBER_MILL(new LumberMill(MapTilesLoader.access$0(51, 0), 0)),
            LUMBER_MILL_UPGRADE_1(new LumberMill(MapTilesLoader.access$0(51, 1), 1)),
            LUMBER_MILL_UPGRADE_2(new LumberMill(MapTilesLoader.access$0(51, 2), 2)),
            LUMBER_SHACK(new LumberShack(MapTilesLoader.access$0(52, 0), 0)),
            LUMBER_SHACK_UPGRADE_1(new LumberShack(MapTilesLoader.access$0(52, 1), 1)),
            LUMBER_SHACK_UPGRADE_2(new LumberShack(MapTilesLoader.access$0(52, 2), 2)),
            OUTPOST(new Outpost(MapTilesLoader.access$0(53, 0), 0)),
            OUTPOST_UPGRADE_1(new Outpost(MapTilesLoader.access$0(53, 1), 1)),
            OUTPOST_UPGRADE_2(new Outpost(MapTilesLoader.access$0(53, 2), 2)),
            PHANTOM_DART_TOWER(new PhantomDartTower(MapTilesLoader.access$0(54, 0), 0)),
            PHANTOM_DART_TOWER_UPGRADE_1(new PhantomDartTower(MapTilesLoader.access$0(54, 1), 1)),
            PHANTOM_DART_TOWER_UPGRADE_2(new PhantomDartTower(MapTilesLoader.access$0(54, 2), 2)),
            PHANTOM_DART_TOWER_UPGRADE_3_FIRE(new PhantomDartTower(MapTilesLoader.access$0(54, 3), 3)),
            PHANTOM_DART_TOWER_UPGRADE_3_LIGHTNING(new PhantomDartTower(MapTilesLoader.access$0(54, 4), 4)),
            PHANTOM_DART_TOWER_UPGRADE_3_ICE(new PhantomDartTower(MapTilesLoader.access$0(54, 5), 5)),
            MAINTENANCE_BUILDING(new MaintenanceBuilding(MapTilesLoader.access$0(55, 0), 0)),
            MAINTENANCE_BUILDING_UPGRADE_1(new MaintenanceBuilding(MapTilesLoader.access$0(55, 1), 1)),
            MAINTENANCE_BUILDING_UPGRADE_2(new MaintenanceBuilding(MapTilesLoader.access$0(55, 2), 2)),
            MARKETPLACE(new Marketplace(MapTilesLoader.access$0(56, 0), 0)),
            MARKETPLACE_UPGRADE_1(new Marketplace(MapTilesLoader.access$0(56, 1), 1)),
            MARKETPLACE_UPGRADE_2(new Marketplace(MapTilesLoader.access$0(56, 2), 2)),
            MIGRATION_WAY_STATION(new MigrationWayStation(MapTilesLoader.access$0(57, 0), 0)),
            MIGRATION_WAY_STATION_UPGRADE_1(new MigrationWayStation(MapTilesLoader.access$0(57, 1), 1)),
            MIGRATION_WAY_STATION_UPGRADE_2(new MigrationWayStation(MapTilesLoader.access$0(57, 2), 2)),
            MINERAL_STORAGE(new MineralStorage(MapTilesLoader.access$0(58, 0), 0)),
            MINERAL_STORAGE_UPGRADE_1(new MineralStorage(MapTilesLoader.access$0(58, 1), 1)),
            MINERAL_STORAGE_UPGRADE_2(new MineralStorage(MapTilesLoader.access$0(58, 2), 2)),
            MINERAL_STORAGE_UPGRADE_3(new MineralStorage(MapTilesLoader.access$0(58, 3), 3)),
            MINERAL_STORAGE_UPGRADE_4(new MineralStorage(MapTilesLoader.access$0(58, 4), 4)),
            MINING_FACILITY(new MiningFacility(MapTilesLoader.access$0(59, 0), 0)),
            MINING_FACILITY_UPGRADE_1(new MiningFacility(MapTilesLoader.access$0(59, 1), 1)),
            MINING_FACILITY_UPGRADE_2(new MiningFacility(MapTilesLoader.access$0(59, 2), 2)),
            MISCELLANEOUS_STORAGE(new MiscellaneousStorage(MapTilesLoader.access$0(60, 0), 0)),
            MISCELLANEOUS_STORAGE_UPGRADE_1(new MiscellaneousStorage(MapTilesLoader.access$0(60, 1), 1)),
            MISCELLANEOUS_STORAGE_UPGRADE_2(new MiscellaneousStorage(MapTilesLoader.access$0(60, 2), 2)),
            MISCELLANEOUS_STORAGE_UPGRADE_3(new MiscellaneousStorage(MapTilesLoader.access$0(60, 3), 3)),
            MISCELLANEOUS_STORAGE_UPGRADE_4(new MiscellaneousStorage(MapTilesLoader.access$0(60, 4), 4)),
            SLING_TOWER(new SlingTower(MapTilesLoader.access$0(61, 0), 0)),
            SLING_TOWER_UPGRADE_1(new SlingTower(MapTilesLoader.access$0(61, 1), 1)),
            SLING_TOWER_UPGRADE_2(new SlingTower(MapTilesLoader.access$0(61, 2), 2)),
            SLING_TOWER_UPGRADE_3_EXPLODING(new SlingTower(MapTilesLoader.access$0(61, 3), 3)),
            SLING_TOWER_UPGRADE_3_LIGHTNING(new SlingTower(MapTilesLoader.access$0(61, 4), 4)),
            SLING_TOWER_UPGRADE_3_ICE(new SlingTower(MapTilesLoader.access$0(61, 5), 5)),
            SMALL_FOUNTAIN(new SmallFountain(MapTilesLoader.access$0(62, 0))),
            SPRAY_TOWER(new SprayTower(MapTilesLoader.access$0(63, 0), 0)),
            SPRAY_TOWER_UPGRADE_1(new SprayTower(MapTilesLoader.access$0(63, 1), 1)),
            SPRAY_TOWER_UPGRADE_2(new SprayTower(MapTilesLoader.access$0(63, 2), 2)),
            SPRAY_TOWER_UPGRADE_3_FIRE(new SprayTower(MapTilesLoader.access$0(63, 3), 3)),
            SPRAY_TOWER_UPGRADE_3_LIGHTNING(new SprayTower(MapTilesLoader.access$0(63, 4), 4)),
            SPRAY_TOWER_UPGRADE_3_ICE(new SprayTower(MapTilesLoader.access$0(63, 5), 5)),
            STATIC_TOWER(new StaticTower(MapTilesLoader.access$0(64, 0), 0)),
            STATIC_TOWER_UPGRADE_1(new StaticTower(MapTilesLoader.access$0(64, 1), 1)),
            STATIC_TOWER_UPGRADE_2(new StaticTower(MapTilesLoader.access$0(64, 2), 2)),
            STATIC_TOWER_UPGRADE_3(new StaticTower(MapTilesLoader.access$0(64, 3), 3)),
            RAIN_CATCHER(new RainCatcher(MapTilesLoader.access$0(65, 0), 0)),
            RAIN_CATCHER_UPGRADE_1(new RainCatcher(MapTilesLoader.access$0(65, 1), 1)),
            RAIN_CATCHER_UPGRADE_2(new RainCatcher(MapTilesLoader.access$0(65, 2), 2)),
            RECOMBOBULATOR_TOWER(new RecombobulatorTower(MapTilesLoader.access$0(66, 0), 0)),
            RECOMBOBULATOR_TOWER_UPGRADE_1(new RecombobulatorTower(MapTilesLoader.access$0(66, 1), 1)),
            RECOMBOBULATOR_TOWER_UPGRADE_2(new RecombobulatorTower(MapTilesLoader.access$0(66, 2), 2)),
            RECOMBOBULATOR_TOWER_UPGRADE_3(new RecombobulatorTower(MapTilesLoader.access$0(66, 3), 3)),
            ROCK_STORAGE(new RockStorage(MapTilesLoader.access$0(67, 0), 0)),
            ROCK_STORAGE_UPGRADE_1(new RockStorage(MapTilesLoader.access$0(67, 1), 1)),
            ROCK_STORAGE_UPGRADE_2(new RockStorage(MapTilesLoader.access$0(67, 2), 2)),
            ROCK_STORAGE_UPGRADE_3(new RockStorage(MapTilesLoader.access$0(67, 3), 3)),
            ROCK_STORAGE_UPGRADE_4(new RockStorage(MapTilesLoader.access$0(67, 4), 4)),
            ROCK_TUMBLER(new RockTumbler(MapTilesLoader.access$0(68, 0), 0)),
            ROCK_TUMBLER_UPGRADE_1(new RockTumbler(MapTilesLoader.access$0(68, 1), 1)),
            ROCK_TUMBLER_UPGRADE_2(new RockTumbler(MapTilesLoader.access$0(68, 2), 2)),
            STONE_CUTTERY(new StoneCuttery(MapTilesLoader.access$0(69, 0), 0)),
            STONE_CUTTERY_UPGRADE_1(new StoneCuttery(MapTilesLoader.access$0(69, 1), 1)),
            STONE_CUTTERY_UPGRADE_2(new StoneCuttery(MapTilesLoader.access$0(69, 2), 2)),
            STONE_GOLEM_COMBOBULATOR(new StoneGolemCombobulator(MapTilesLoader.access$0(70, 0), 0)),
            STONE_GOLEM_COMBOBULATOR_UPGRADE_1(new StoneGolemCombobulator(MapTilesLoader.access$0(70, 1), 1)),
            STONE_GOLEM_COMBOBULATOR_UPGRADE_2(new StoneGolemCombobulator(MapTilesLoader.access$0(70, 2), 2)),
            STONE_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING(new StoneGolemCombobulator(MapTilesLoader.access$0(70, 3), 3)),
            STONE_GOLEM_COMBOBULATOR_UPGRADE_3_ICE(new StoneGolemCombobulator(MapTilesLoader.access$0(70, 4), 4)),
            STONE_GOLEM_COMBOBULATOR_UPGRADE_3_FIRE(new StoneGolemCombobulator(MapTilesLoader.access$0(70, 5), 5)),
            TOOLSMITHY(new Toolsmithy(MapTilesLoader.access$0(71, 0), 0)),
            TOOLSMITHY_UPGRADE_1(new Toolsmithy(MapTilesLoader.access$0(71, 1), 1)),
            TOOLSMITHY_UPGRADE_2(new Toolsmithy(MapTilesLoader.access$0(71, 2), 2)),
            WATER_PURIFIER(new WaterPurifier(MapTilesLoader.access$0(72, 0), 0)),
            WATER_PURIFIER_UPGRADE_1(new WaterPurifier(MapTilesLoader.access$0(72, 1), 1)),
            WATER_PURIFIER_UPGRADE_2(new WaterPurifier(MapTilesLoader.access$0(72, 2), 2)),
            WAY_MAKER_SHACK(new WayMakerShack(MapTilesLoader.access$0(73, 0), 0)),
            WAY_MAKER_SHACK_UPGRADE_1(new WayMakerShack(MapTilesLoader.access$0(73, 1), 1)),
            WAY_MAKER_SHACK_UPGRADE_2(new WayMakerShack(MapTilesLoader.access$0(73, 2), 2)),
            WELL(new Well(MapTilesLoader.access$0(74, 0), 0)),
            WELL_UPGRADE_1(new Well(MapTilesLoader.access$0(74, 1), 1)),
            WELL_UPGRADE_2(new Well(MapTilesLoader.access$0(74, 2), 2)),
            WOOD_GOLEM_COMBOBULATOR(new WoodGolemCombobulator(MapTilesLoader.access$0(75, 0), 0)),
            WOOD_GOLEM_COMBOBULATOR_UPGRADE_1(new WoodGolemCombobulator(MapTilesLoader.access$0(75, 1), 1)),
            WOOD_GOLEM_COMBOBULATOR_UPGRADE_2(new WoodGolemCombobulator(MapTilesLoader.access$0(75, 2), 2)),
            WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_LIGHTNING(new WoodGolemCombobulator(MapTilesLoader.access$0(75, 3), 3)),
            WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_ICE(new WoodGolemCombobulator(MapTilesLoader.access$0(75, 4), 4)),
            WOOD_GOLEM_COMBOBULATOR_UPGRADE_3_POISON(new WoodGolemCombobulator(MapTilesLoader.access$0(75, 5), 5)),
            WOOD_STORAGE(new WoodStorage(MapTilesLoader.access$0(76, 0), 0)),
            WOOD_STORAGE_UPGRADE_1(new WoodStorage(MapTilesLoader.access$0(76, 1), 1)),
            WOOD_STORAGE_UPGRADE_2(new WoodStorage(MapTilesLoader.access$0(76, 2), 2)),
            WOOD_STORAGE_UPGRADE_3(new WoodStorage(MapTilesLoader.access$0(76, 3), 3)),
            WOOD_STORAGE_UPGRADE_4(new WoodStorage(MapTilesLoader.access$0(76, 4), 4)),
            WOOD_FENCE_GATE_NS(new WoodFenceGateNS(MapTilesLoader.access$0(77, 0))),
            WOOD_FENCE_GATE_WE(new WoodFenceGateWE(MapTilesLoader.access$0(77, 1))),
            STONE_WALL_GATE_NS(new StoneWallGateNS(MapTilesLoader.access$0(78, 0))),
            STONE_WALL_GATE_WE(new StoneWallGateWE(MapTilesLoader.access$0(78, 1))),
            BURNER(new Burner(MapTilesLoader.access$0(79, 0), 0)),
            BURNER_UPGRADE_1(new Burner(MapTilesLoader.access$0(79, 1), 1)),
            BURNER_UPGRADE_2(new Burner(MapTilesLoader.access$0(79, 2), 2)),
            LANDFILL(new Landfill(MapTilesLoader.access$0(80, 0), 0)),
            LANDFILL_UPGRADE_1(new Landfill(MapTilesLoader.access$0(80, 1), 1)),
            LANDFILL_UPGRADE_2(new Landfill(MapTilesLoader.access$0(80, 2), 2)),
            PROCESSOR(new Processor(MapTilesLoader.access$0(81, 0), 0)),
            PROCESSOR_UPGRADE_1(new Processor(MapTilesLoader.access$0(81, 1), 1)),
            PROCESSOR_UPGRADE_2(new Processor(MapTilesLoader.access$0(81, 2), 2)),
            TRASH_CAN(new TrashCan(MapTilesLoader.access$0(82, 0), 0)),
            TRASH_CAN_UPGRADE_1(new TrashCan(MapTilesLoader.access$0(82, 1), 1)),
            TRASH_CAN_UPGRADE_2(new TrashCan(MapTilesLoader.access$0(82, 2), 2)),
            TRASHY_CUBE_PILE(new TrashyCubePile(MapTilesLoader.access$0(83, 0))),
            CUBE_E_GOLEM_COMBOBULATOR(new CubeEGolemCombobulator(MapTilesLoader.access$0(84, 0), 0)),
            CUBE_E_GOLEM_COMBOBULATOR_UPGRADE_1(new CubeEGolemCombobulator(MapTilesLoader.access$0(84, 1), 1)),
            CUBE_E_GOLEM_COMBOBULATOR_UPGRADE_2(new CubeEGolemCombobulator(MapTilesLoader.access$0(84, 2), 2)),
            ANIMAL_PEN(new AnimalPen(MapTilesLoader.access$0(85, 0), 0)),
            ANIMAL_PEN_UPGRADE_1(new AnimalPen(MapTilesLoader.access$0(85, 1), 1)),
            ANIMAL_PEN_UPGRADE_2(new AnimalPen(MapTilesLoader.access$0(85, 2), 2)),
            CLUCKER_COOP(new CluckerCoop(MapTilesLoader.access$0(86, 0), 0)),
            CLUCKER_COOP_UPGRADE_1(new CluckerCoop(MapTilesLoader.access$0(86, 1), 1)),
            CLUCKER_COOP_UPGRADE_2(new CluckerCoop(MapTilesLoader.access$0(86, 2), 2)),
            CRYLITHIUM_WALL_GATE_NS(new CrylithiumWallGateNS(MapTilesLoader.access$0(87, 0))),
            CRYLITHIUM_WALL_GATE_WE(new CrylithiumWallGateWE(MapTilesLoader.access$0(87, 1))),
            GOD_TOWER(new GodTower(MapTilesLoader.access$0(88, 0), 0)),
            ESSENCE_ALTAR(new EssenceAltar(MapTilesLoader.access$0(89, 0), 0)),
            ESSENCE_ALTAR_UPGRADE_1(new EssenceAltar(MapTilesLoader.access$0(89, 1), 1)),
            ESSENCE_ALTAR_UPGRADE_2(new EssenceAltar(MapTilesLoader.access$0(89, 2), 2)),
            CRYLITHIUM_FIRE_PIT(new CrylithiumFirePit(MapTilesLoader.access$0(90, 0))),
            LARGE_CRYLITHIUM_FIRE_PIT(new LargeCrylithiumFirePit(MapTilesLoader.access$0(91, 0))),
            RELIQUARY(new Reliquary(MapTilesLoader.access$0(92, 0), 0)),
            RELIQUARY_UPGRADE_1(new Reliquary(MapTilesLoader.access$0(92, 1), 1)),
            RELIQUARY_UPGRADE_2(new Reliquary(MapTilesLoader.access$0(92, 2), 2)),
            TREES_BLUE_COLLECTED(new TreesBlueCollected(MapTilesLoader.access$1(0, 3))),
            TREES_BLUE_ACCENT(new TreesBlueAccent(MapTilesLoader.access$1(0, 2))),
            TREES_BLUE_DEAD(new TreesBlueDead(MapTilesLoader.access$1(0, 1)), null, TREES_BLUE_COLLECTED),
            TREES_BLUE(new TreesBlue(MapTilesLoader.access$1(0, 0)), TREES_BLUE_ACCENT, TREES_BLUE_COLLECTED, TREES_BLUE_DEAD),
            TREES_BROWN_COLLECTED(new TreesBrownCollected(MapTilesLoader.access$1(0, 13))),
            TREES_BROWN_ACCENT(new TreesBrownAccent(MapTilesLoader.access$1(0, 12))),
            TREES_BROWN_DEAD(new TreesBrownDead(MapTilesLoader.access$1(0, 11)), null, TREES_BROWN_COLLECTED),
            TREES_BROWN(new TreesBrown(MapTilesLoader.access$1(0, 10)), TREES_BROWN_ACCENT, TREES_BROWN_COLLECTED, TREES_BROWN_DEAD),
            TREES_DARK_GREEN_COLLECTED(new TreesDarkGreenCollected(MapTilesLoader.access$1(0, 23))),
            TREES_DARK_GREEN_ACCENT(new TreesDarkGreenAccent(MapTilesLoader.access$1(0, 22))),
            TREES_DARK_GREEN_DEAD(new TreesDarkGreenDead(MapTilesLoader.access$1(0, 21)), null, TREES_DARK_GREEN_COLLECTED),
            TREES_DARK_GREEN(new TreesDarkGreen(MapTilesLoader.access$1(0, 20)), TREES_DARK_GREEN_ACCENT, TREES_DARK_GREEN_COLLECTED, TREES_DARK_GREEN_DEAD),
            TREES_GREEN_COLLECTED(new TreesGreenCollected(MapTilesLoader.access$1(0, 33))),
            TREES_GREEN_ACCENT(new TreesGreenAccent(MapTilesLoader.access$1(0, 32))),
            TREES_GREEN_DEAD(new TreesGreenDead(MapTilesLoader.access$1(0, 31)), null, TREES_GREEN_COLLECTED),
            TREES_GREEN(new TreesGreen(MapTilesLoader.access$1(0, 30)), TREES_GREEN_ACCENT, TREES_GREEN_COLLECTED, TREES_GREEN_DEAD),
            TREES_RED_COLLECTED(new TreesRedCollected(MapTilesLoader.access$1(0, 43))),
            TREES_RED_ACCENT(new TreesRedAccent(MapTilesLoader.access$1(0, 42))),
            TREES_RED_DEAD(new TreesRedDead(MapTilesLoader.access$1(0, 41)), null, TREES_RED_COLLECTED),
            TREES_RED(new TreesRed(MapTilesLoader.access$1(0, 40)), TREES_RED_ACCENT, TREES_RED_COLLECTED, TREES_RED_DEAD),
            TREES_LAVENDER_COLLECTED(new TreesLavenderCollected(MapTilesLoader.access$1(0, 53))),
            TREES_LAVENDER_ACCENT(new TreesLavenderAccent(MapTilesLoader.access$1(0, 52))),
            TREES_LAVENDER_DEAD(new TreesLavenderDead(MapTilesLoader.access$1(0, 51)), null, TREES_LAVENDER_COLLECTED),
            TREES_LAVENDER(new TreesLavender(MapTilesLoader.access$1(0, 50)), TREES_LAVENDER_ACCENT, TREES_LAVENDER_COLLECTED, TREES_LAVENDER_DEAD),
            TREES_PALE_BLUE_COLLECTED(new TreesPaleBlueCollected(MapTilesLoader.access$1(0, 63))),
            TREES_PALE_BLUE_ACCENT(new TreesPaleBlueAccent(MapTilesLoader.access$1(0, 62))),
            TREES_PALE_BLUE_DEAD(new TreesPaleBlueDead(MapTilesLoader.access$1(0, 61)), null, TREES_PALE_BLUE_COLLECTED),
            TREES_PALE_BLUE(new TreesPaleBlue(MapTilesLoader.access$1(0, 60)), TREES_PALE_BLUE_ACCENT, TREES_PALE_BLUE_COLLECTED, TREES_PALE_BLUE_DEAD),
            ROCK_BLACK_COLLECTED(new RockBlackCollected(MapTilesLoader.access$1(1, 2))),
            ROCK_BLACK_ACCENT(new RockBlackAccent(MapTilesLoader.access$1(1, 1))),
            ROCK_BLACK(new RockBlack(MapTilesLoader.access$1(1, 0)), ROCK_BLACK_ACCENT, ROCK_BLACK_COLLECTED),
            ROCK_BROWN_COLLECTED(new RockBrownCollected(MapTilesLoader.access$1(1, 12))),
            ROCK_BROWN_ACCENT(new RockBrownAccent(MapTilesLoader.access$1(1, 11))),
            ROCK_BROWN(new RockBrown(MapTilesLoader.access$1(1, 10)), ROCK_BROWN_ACCENT, ROCK_BROWN_COLLECTED),
            ROCK_GRAY_COLLECTED(new RockGrayCollected(MapTilesLoader.access$1(1, 22))),
            ROCK_GRAY_ACCENT(new RockGrayAccent(MapTilesLoader.access$1(1, 21))),
            ROCK_GRAY(new RockGray(MapTilesLoader.access$1(1, 20)), ROCK_GRAY_ACCENT, ROCK_GRAY_COLLECTED),
            ROCK_RED_COLLECTED(new RockRedCollected(MapTilesLoader.access$1(1, 32))),
            ROCK_RED_ACCENT(new RockRedAccent(MapTilesLoader.access$1(1, 31))),
            ROCK_RED(new RockRed(MapTilesLoader.access$1(1, 30)), ROCK_RED_ACCENT, ROCK_RED_COLLECTED),
            ROCK_WHITE_COLLECTED(new RockWhiteCollected(MapTilesLoader.access$1(1, 42))),
            ROCK_WHITE_ACCENT(new RockWhiteAccent(MapTilesLoader.access$1(1, 41))),
            ROCK_WHITE(new RockWhite(MapTilesLoader.access$1(1, 40)), ROCK_WHITE_ACCENT, ROCK_WHITE_COLLECTED),
            SANDSTONE_BLACK_COLLECTED(new SandstoneBlackCollected(MapTilesLoader.access$1(2, 2))),
            SANDSTONE_BLACK_ACCENT(new SandstoneBlackAccent(MapTilesLoader.access$1(2, 1))),
            SANDSTONE_BLACK(new SandstoneBlack(MapTilesLoader.access$1(2, 0)), SANDSTONE_BLACK_ACCENT, SANDSTONE_BLACK_COLLECTED),
            SANDSTONE_RED_COLLECTED(new SandstoneRedCollected(MapTilesLoader.access$1(2, 12))),
            SANDSTONE_RED_ACCENT(new SandstoneRedAccent(MapTilesLoader.access$1(2, 11))),
            SANDSTONE_RED(new SandstoneRed(MapTilesLoader.access$1(2, 10)), SANDSTONE_RED_ACCENT, SANDSTONE_RED_COLLECTED),
            SANDSTONE_TAN_COLLECTED(new SandstoneTanCollected(MapTilesLoader.access$1(2, 22))),
            SANDSTONE_TAN_ACCENT(new SandstoneTanAccent(MapTilesLoader.access$1(2, 21))),
            SANDSTONE_TAN(new SandstoneTan(MapTilesLoader.access$1(2, 20)), SANDSTONE_TAN_ACCENT, SANDSTONE_TAN_COLLECTED),
            CRYSTAL_RED_COLLECTED(new CrystalsRedCollected(MapTilesLoader.access$1(3, 2))),
            CRYSTAL_RED_ACCENT(new CrystalsRedAccent(MapTilesLoader.access$1(3, 1))),
            CRYSTAL_RED(new CrystalsRed(MapTilesLoader.access$1(3, 0)), CRYSTAL_RED_ACCENT, CRYSTAL_RED_COLLECTED),
            CRYSTAL_GREEN_COLLECTED(new CrystalsGreenCollected(MapTilesLoader.access$1(3, 12))),
            CRYSTAL_GREEN_ACCENT(new CrystalsGreenAccent(MapTilesLoader.access$1(3, 11))),
            CRYSTAL_GREEN(new CrystalsGreen(MapTilesLoader.access$1(3, 10)), CRYSTAL_GREEN_ACCENT, CRYSTAL_GREEN_COLLECTED),
            CRYSTAL_BLUE_COLLECTED(new CrystalsBlueCollected(MapTilesLoader.access$1(3, 22))),
            CRYSTAL_BLUE_ACCENT(new CrystalsBlueAccent(MapTilesLoader.access$1(3, 21))),
            CRYSTAL_BLUE(new CrystalsBlue(MapTilesLoader.access$1(3, 20)), CRYSTAL_BLUE_ACCENT, CRYSTAL_BLUE_COLLECTED),
            CRYSTAL_PURPLE_COLLECTED(new CrystalsPurpleCollected(MapTilesLoader.access$1(3, 32))),
            CRYSTAL_PURPLE_ACCENT(new CrystalsPurpleAccent(MapTilesLoader.access$1(3, 31))),
            CRYSTAL_PURPLE(new CrystalsPurple(MapTilesLoader.access$1(3, 30)), CRYSTAL_PURPLE_ACCENT, CRYSTAL_PURPLE_COLLECTED),
            POTATOES_COLLECTED(new PotatoesCollected(MapTilesLoader.access$1(4, 2))),
            POTATOES_ACCENT(new PotatoesAccent(MapTilesLoader.access$1(4, 1))),
            POTATOES(new Potatoes(MapTilesLoader.access$1(4, 0)), POTATOES_ACCENT, POTATOES_COLLECTED),
            HOLY_POTATOES_COLLECTED(new PotatoesHolyCollected(MapTilesLoader.access$1(4, 5))),
            HOLY_POTATOES_ACCENT(new PotatoesHolyAccent(MapTilesLoader.access$1(4, 4))),
            HOLY_POTATOES(new PotatoesHoly(MapTilesLoader.access$1(4, 3)), HOLY_POTATOES_ACCENT, HOLY_POTATOES_COLLECTED),
            TURNIPS_COLLECTED(new TurnipsCollected(MapTilesLoader.access$1(5, 2))),
            TURNIPS_ACCENT(new TurnipsAccent(MapTilesLoader.access$1(5, 1))),
            TURNIPS(new Turnips(MapTilesLoader.access$1(5, 0)), TURNIPS_ACCENT, TURNIPS_COLLECTED),
            CACTUS_GREEN_COLLECTED(new CactusGreenCollected(MapTilesLoader.access$1(6, 2))),
            CACTUS_GREEN_ACCENT(new CactusGreenAccent(MapTilesLoader.access$1(6, 1))),
            CACTUS_GREEN(new CactusGreen(MapTilesLoader.access$1(6, 0)), CACTUS_GREEN_ACCENT, CACTUS_GREEN_COLLECTED),
            CARROTS_COLLECTED(new CarrotsCollected(MapTilesLoader.access$1(7, 2))),
            CARROTS_ACCENT(new CarrotsAccent(MapTilesLoader.access$1(7, 1))),
            CARROTS(new Carrots(MapTilesLoader.access$1(7, 0)), CARROTS_ACCENT, CARROTS_COLLECTED),
            MELONS_COLLECTED(new MelonsCollected(MapTilesLoader.access$1(8, 2))),
            MELONS_ACCENT(new MelonsAccent(MapTilesLoader.access$1(8, 1))),
            MELONS(new Melons(MapTilesLoader.access$1(8, 0)), MELONS_ACCENT, MELONS_COLLECTED),
            BRICKS_ACCENT(new BricksAccent(MapTilesLoader.access$1(9, 1))),
            BRICKS(new Bricks(MapTilesLoader.access$1(9, 0)), BRICKS_ACCENT),
            DIRT_BROWN_ACCENT(new DirtBrownAccent(MapTilesLoader.access$1(10, 1))),
            DIRT_BROWN(new DirtBrown(MapTilesLoader.access$1(10, 0)), DIRT_BROWN_ACCENT),
            DIRT_DARK_BROWN_ACCENT(new DirtDarkBrownAccent(MapTilesLoader.access$1(10, 11))),
            DIRT_DARK_BROWN(new DirtDarkBrown(MapTilesLoader.access$1(10, 10)), DIRT_DARK_BROWN_ACCENT),
            DIRT_LIGHT_BROWN_ACCENT(new DirtLightBrownAccent(MapTilesLoader.access$1(10, 21))),
            DIRT_LIGHT_BROWN(new DirtLightBrown(MapTilesLoader.access$1(10, 20)), DIRT_LIGHT_BROWN_ACCENT),
            FLOWERS_BLACK_ACCENT(new FlowersBlackAccent(MapTilesLoader.access$1(11, 1))),
            FLOWERS_BLACK(new FlowersBlack(MapTilesLoader.access$1(11, 0)), FLOWERS_BLACK_ACCENT),
            FLOWERS_BLUE_ACCENT(new FlowersBlueAccent(MapTilesLoader.access$1(11, 11))),
            FLOWERS_BLUE(new FlowersBlue(MapTilesLoader.access$1(11, 10)), FLOWERS_BLUE_ACCENT),
            FLOWERS_PURPLE_ACCENT(new FlowersPurpleAccent(MapTilesLoader.access$1(11, 21))),
            FLOWERS_PURPLE(new FlowersPurple(MapTilesLoader.access$1(11, 20)), FLOWERS_PURPLE_ACCENT),
            FLOWERS_RED_ACCENT(new FlowersRedAccent(MapTilesLoader.access$1(11, 31))),
            FLOWERS_RED(new FlowersRed(MapTilesLoader.access$1(11, 30)), FLOWERS_RED_ACCENT),
            FLOWERS_WHITE_ACCENT(new FlowersWhiteAccent(MapTilesLoader.access$1(11, 41))),
            FLOWERS_WHITE(new FlowersWhite(MapTilesLoader.access$1(11, 40)), FLOWERS_WHITE_ACCENT),
            FLOWERS_YELLOW_ACCENT(new FlowersYellowAccent(MapTilesLoader.access$1(11, 51))),
            FLOWERS_YELLOW(new FlowersYellow(MapTilesLoader.access$1(11, 50)), FLOWERS_YELLOW_ACCENT),
            GRASS_EMERALD_GREEN_ACCENT(new GrassEmeraldGreenAccent(MapTilesLoader.access$1(12, 1))),
            GRASS_EMERALD_GREEN(new GrassEmeraldGreen(MapTilesLoader.access$1(12, 0)), GRASS_EMERALD_GREEN_ACCENT),
            GRASS_GREEN_ACCENT(new GrassGreenAccent(MapTilesLoader.access$1(12, 11))),
            GRASS_GREEN(new GrassGreen(MapTilesLoader.access$1(12, 10)), GRASS_GREEN_ACCENT),
            GRASS_YELLOW_BROWN_ACCENT(new GrassYellowBrownAccent(MapTilesLoader.access$1(12, 21))),
            GRASS_YELLOW_BROWN(new GrassYellowBrown(MapTilesLoader.access$1(12, 20)), GRASS_YELLOW_BROWN_ACCENT),
            GRASS_TEAL_ACCENT(new GrassTealAccent(MapTilesLoader.access$1(12, 31))),
            GRASS_TEAL(new GrassTeal(MapTilesLoader.access$1(12, 30)), GRASS_TEAL_ACCENT),
            GRAVEL_BLUE_ACCENT(new GravelBlueAccent(MapTilesLoader.access$1(13, 31))),
            GRAVEL_BLUE(new GravelBlue(MapTilesLoader.access$1(13, 30)), GRAVEL_BLUE_ACCENT),
            GRAVEL_GRAY_ACCENT(new GravelGrayAccent(MapTilesLoader.access$1(13, 41))),
            GRAVEL_GRAY(new GravelGray(MapTilesLoader.access$1(13, 40)), GRAVEL_GRAY_ACCENT),
            GRAVEL_RED_ACCENT(new GravelRedAccent(MapTilesLoader.access$1(13, 51))),
            GRAVEL_RED(new GravelRed(MapTilesLoader.access$1(13, 50)), GRAVEL_RED_ACCENT),
            LAVA_ACCENT(new LavaAccent(MapTilesLoader.access$1(14, 1))),
            LAVA(new Lava(MapTilesLoader.access$1(14, 0)), LAVA_ACCENT),
            SAND_BLACK_ACCENT(new SandBlackAccent(MapTilesLoader.access$1(15, 1))),
            SAND_BLACK(new SandBlack(MapTilesLoader.access$1(15, 0)), SAND_BLACK_ACCENT),
            SAND_RED_ACCENT(new SandRedAccent(MapTilesLoader.access$1(15, 11))),
            SAND_RED(new SandRed(MapTilesLoader.access$1(15, 10)), SAND_RED_ACCENT),
            SAND_TAN_ACCENT(new SandTanAccent(MapTilesLoader.access$1(15, 21))),
            SAND_TAN(new SandTan(MapTilesLoader.access$1(15, 20)), SAND_TAN_ACCENT),
            TAR_ACCENT(new TarAccent(MapTilesLoader.access$1(16, 1))),
            TAR(new Tar(MapTilesLoader.access$1(16, 0)), TAR_ACCENT),
            TILES_ACCENT(new TilesAccent(MapTilesLoader.access$1(17, 1))),
            TILES(new Tiles(MapTilesLoader.access$1(17, 0)), TILES_ACCENT),
            WATER_ACCENT(new WaterAccent(MapTilesLoader.access$1(18, 1))),
            WATER(new Water(MapTilesLoader.access$1(18, 0)), WATER_ACCENT),
            WATER_DRY_ACCENT(new WaterDryAccent(MapTilesLoader.access$1(18, 11))),
            WATER_DRY(new WaterDry(MapTilesLoader.access$1(18, 10)), WATER_DRY_ACCENT),
            WATER_PARTIALLY_DRY_ACCENT(new WaterPartiallyDryAccent(MapTilesLoader.access$1(18, 21))),
            WATER_PARTIALLY_DRY(new WaterPartiallyDry(MapTilesLoader.access$1(18, 20)), WATER_PARTIALLY_DRY_ACCENT),
            WATER_PARTIALLY_DRY_FROZEN_ACCENT(new WaterPartiallyDryFrozenAccent(MapTilesLoader.access$1(18, 31))),
            WATER_PARTIALLY_DRY_FROZEN(new WaterPartiallyDryFrozen(MapTilesLoader.access$1(18, 30)), WATER_PARTIALLY_DRY_FROZEN_ACCENT),
            WATER_PARTIALLY_FROZEN_ACCENT(new WaterPartiallyFrozenAccent(MapTilesLoader.access$1(18, 41))),
            WATER_PARTIALLY_FROZEN(new WaterPartiallyFrozen(MapTilesLoader.access$1(18, 40)), WATER_PARTIALLY_FROZEN_ACCENT),
            WATER_FROZEN_ACCENT(new WaterFrozenAccent(MapTilesLoader.access$1(18, 51))),
            WATER_FROZEN(new WaterFrozen(MapTilesLoader.access$1(18, 50)), WATER_FROZEN_ACCENT),
            CORRUPTION_ACCENT(new CorruptionAccent(MapTilesLoader.access$1(19, 1))),
            CORRUPTION(new Corruption(MapTilesLoader.access$1(19, 0)), CORRUPTION_ACCENT),
            PATH(new Path(MapTilesLoader.access$1(20, 0))),
            LOG_PATH_DEBRIS(new LogPathDebris(MapTilesLoader.access$1(20, 1))),
            LOG_PATH(new LogPath(MapTilesLoader.access$1(20, 2))),
            COBBLE_AND_LOG_PATH_DEBRIS(new CobbleAndLogPathDebris(MapTilesLoader.access$1(20, 3))),
            COBBLE_AND_LOG_PATH(new CobbleAndLogPath(MapTilesLoader.access$1(20, 4))),
            COBBLE_AND_BOARD_ROAD_DEBRIS(new CobbleAndBoardRoadDebris(MapTilesLoader.access$1(20, 5))),
            COBBLE_AND_BOARD_ROAD(new CobbleAndBoardRoad(MapTilesLoader.access$1(20, 6))),
            CUT_STONE_AND_BOARD_ROAD_DEBRIS(new CutStoneAndBoardRoadDebris(MapTilesLoader.access$1(20, 7))),
            CUT_STONE_AND_BOARD_ROAD(new CutStoneAndBoardRoad(MapTilesLoader.access$1(20, 8))),
            CURTAIN_WALL_OUTLINE(new CurtainWall(MapTilesLoader.access$1(21, 0), 0)),
            CURTAIN_WALL_CONSTRUCTION(new CurtainWall(MapTilesLoader.access$1(21, 1), 1)),
            CURTAIN_WALL(new CurtainWall(MapTilesLoader.access$1(21, 2), 2), CURTAIN_WALL_OUTLINE, CURTAIN_WALL_CONSTRUCTION),
            STONE_WALL_OUTLINE(new StoneWall(MapTilesLoader.access$1(21, 3), 0)),
            STONE_WALL_CONSTRUCTION(new StoneWall(MapTilesLoader.access$1(21, 4), 1)),
            STONE_WALL(new StoneWall(MapTilesLoader.access$1(21, 5), 2), STONE_WALL_OUTLINE, STONE_WALL_CONSTRUCTION),
            WOOD_FENCE_OUTLINE(new WoodFence(MapTilesLoader.access$1(21, 6), 0)),
            WOOD_FENCE_CONSTRUCTION(new WoodFence(MapTilesLoader.access$1(21, 7), 1)),
            WOOD_FENCE(new WoodFence(MapTilesLoader.access$1(21, 8), 2), WOOD_FENCE_OUTLINE, WOOD_FENCE_CONSTRUCTION),
            TRASHY_CUBE_WALL_OUTLINE(new TrashyCubeWall(MapTilesLoader.access$1(21, 9), 0)),
            TRASHY_CUBE_WALL_CONSTRUCTION(new TrashyCubeWall(MapTilesLoader.access$1(21, 10), 1)),
            TRASHY_CUBE_WALL(new TrashyCubeWall(MapTilesLoader.access$1(21, 11), 2), TRASHY_CUBE_WALL_OUTLINE, TRASHY_CUBE_WALL_CONSTRUCTION),
            CRYLITHIUM_WALL_OUTLINE(new CrylithiumWall(MapTilesLoader.access$1(21, 12), 0)),
            CRYLITHIUM_WALL_CONSTRUCTION(new CrylithiumWall(MapTilesLoader.access$1(21, 13), 1)),
            CRYLITHIUM_WALL(new CrylithiumWall(MapTilesLoader.access$1(21, 14), 2), CRYLITHIUM_WALL_OUTLINE, CRYLITHIUM_WALL_CONSTRUCTION),
            CRYLITHIUM_CURTAIN_WALL_OUTLINE(new CrylithiumCurtainWall(MapTilesLoader.access$1(21, 15), 0)),
            CRYLITHIUM_CURTAIN_WALL_CONSTRUCTION(new CrylithiumCurtainWall(MapTilesLoader.access$1(21, 16), 1)),
            CRYLITHIUM_CURTAIN_WALL(new CrylithiumCurtainWall(MapTilesLoader.access$1(21, 17), 2), CRYLITHIUM_CURTAIN_WALL_OUTLINE, CRYLITHIUM_CURTAIN_WALL_CONSTRUCTION),
            GOD_WALL_OUTLINE(new GodWall(MapTilesLoader.access$1(21, 18), 0)),
            GOD_WALL_CONSTRUCTION(new GodWall(MapTilesLoader.access$1(21, 19), 1)),
            GOD_WALL(new GodWall(MapTilesLoader.access$1(21, 20), 2), CRYLITHIUM_CURTAIN_WALL_OUTLINE, CRYLITHIUM_CURTAIN_WALL_CONSTRUCTION),
            SNOW_WHITE_ACCENT(new SnowWhiteAccent(MapTilesLoader.access$1(22, 1))),
            SNOW_WHITE(new SnowWhite(MapTilesLoader.access$1(22, 0)), SNOW_WHITE_ACCENT),
            MUSHROOMS_COLLECTED(new MushroomsCollected(MapTilesLoader.access$1(23, 2))),
            MUSHROOMS_ACCENT(new MushroomsAccent(MapTilesLoader.access$1(23, 1))),
            MUSHROOMS(new Mushrooms(MapTilesLoader.access$1(23, 0)), MUSHROOMS_ACCENT, MUSHROOMS_COLLECTED);

    private TileSetBase tileSetTemplate;
    private TileSet accentTileSet;
    private TileSet collectedTileSet;
    private TileSet deadTileSet;

    private TileSet(TileSetBase template) {
        this.tileSetTemplate = template;
    }

    private TileSet(TileSetBase template, TileSet accent, TileSet collected, TileSet dead) {
        this.tileSetTemplate = template;
        this.accentTileSet = accent;
        this.collectedTileSet = collected;
        this.deadTileSet = dead;
    }

    private TileSet(TileSetBase template, TileSet accent, TileSet collected) {
        this.tileSetTemplate = template;
        this.accentTileSet = accent;
        this.collectedTileSet = collected;
    }

    private TileSet(TileSetBase template, TileSet accent) {
        this.tileSetTemplate = template;
        this.accentTileSet = accent;
    }

    public TileSetBase getTileSetTemplate() {
        return this.tileSetTemplate;
    }

    public TileSet getAccent() {
        return this.accentTileSet;
    }

    public TileSet getCollected() {
        return this.collectedTileSet;
    }

    public TileSet getWallOutline() {
        return this.accentTileSet;
    }

    public TileSet getWallConstruction() {
        return this.collectedTileSet;
    }

    public TileSet getDead() {
        return this.deadTileSet;
    }
}
