package rtr.objects.objectflags.village;

import rtr.PerkModule;
import rtr.font.Text;
import rtr.map.MapTilesLoader;
import rtr.mobs.MobBase;
import rtr.objects.ObjectBase;
import rtr.objects.objectflags.ObjectFlags;
import rtr.objects.towerhead.TowerBase;
import rtr.resources.ResourceModule;
import rtr.utilities.OrderedPair;

public class RockMotivatorFlags extends ObjectFlags {
    public RockMotivatorFlags(MapTilesLoader.TileSet currentType, ObjectBase.ObjectSubType subType) {
        super(currentType, subType);
    }

    @Override
    protected void configBasics() {
        this.baseType = MapTilesLoader.TileSet.MOD_STRUCTURE_2;
        this.width = 2;
        this.height = 2;
        this.requiredSupportBuildings.add(new MapTilesLoader.TileSet[]{MapTilesLoader.TileSet.ESSENCE_COLLECTOR});
        this.recommendedSupportBuildings.add(new MapTilesLoader.TileSet[]{MapTilesLoader.TileSet.LUMBER_SHACK});
        this.baseName = Text.getText("Rock Motivator");

        switch (this.currentType) {
            case MOD_STRUCTURE_2: {
                this.name = Text.getText("Rock Motivator");
                this.description = Text.getText("Motivates rock tiles");
                this.resourceBaseValues.put(ResourceModule.ResourceType.ROCK, 4);
                this.resourceBaseValues.put(ResourceModule.ResourceType.CUT_STONE, 24);
                this.resourceBaseValues.put(ResourceModule.ResourceType.CRYSTAL, 8);
                break;
            }
            case MOD_STRUCTURE_2_1: {
                this.name = Text.getText("Rock Motivator 2");
                this.description = Text.getText("Motivates rock tiles 2");
                this.resourceBaseValues.put(ResourceModule.ResourceType.ROCK, 8);
                this.resourceBaseValues.put(ResourceModule.ResourceType.CUT_STONE, 34);
                this.resourceBaseValues.put(ResourceModule.ResourceType.CRYSTAL, 18);
                break;
            }
            case MOD_STRUCTURE_2_2: {
                this.name = Text.getText("Rock Motivator 3");
                this.description = Text.getText("Motivates rock tiles 3");
                this.resourceBaseValues.put(ResourceModule.ResourceType.ROCK, 8);
                this.resourceBaseValues.put(ResourceModule.ResourceType.CUT_STONE, 34);
                this.resourceBaseValues.put(ResourceModule.ResourceType.CRYSTAL, 18);
                break;
            }
        }

        this.allowDismantle = true;
        this.allowPause = true;
    }

    @Override
    protected void configConstruction() {
        this.mobGroup = MobBase.MobGroup.VILLAGER;
        if (this.currentType != MapTilesLoader.TileSet.MOD_STRUCTURE_2) {
            this.baseRange = 8;
            this.baseDesirability = 1;
            this.canAcceptEssence = true;
            this.essenceReturnCoordinates = OrderedPair.getOrderedPair(1, 1);
            this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0);
            this.energyMax = 300;
            this.towerCoordinates = OrderedPair.getOrderedPair(1, 1);
            this.towerType = TowerBase.TowerType.MOTIVATOR;
        }
        switch (this.currentType) {
            case MOD_STRUCTURE_2_1:
            case MOD_STRUCTURE_2_2: {
                this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE;
            }
        }


    }

    @Override
    protected void configBuilt() {
        this.mobGroup = MobBase.MobGroup.VILLAGER;
        this.baseRange = 8;
        this.baseDesirability = 1;
        switch (this.currentType) {
            case MOD_STRUCTURE_2: {
                this.canAcceptEssence = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(1, 1);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0);
                this.energyMax = 300;
                this.towerCoordinates = OrderedPair.getOrderedPair(1, 1);
                this.towerType = TowerBase.TowerType.MOTIVATOR;
                this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE;
                this.canUpgrade = true;
                this.nextUpgradeType = MapTilesLoader.TileSet.MOD_STRUCTURE_2_1;
                this.nextUpgradeType2 = MapTilesLoader.TileSet.MOD_STRUCTURE_2_2;
                break;
            }
            case MOD_STRUCTURE_2_1: {
                this.canAcceptEssence = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(1, 1);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0);
                this.energyMax = 400;
                this.towerCoordinates = OrderedPair.getOrderedPair(1, 1);
                this.towerType = TowerBase.TowerType.MOTIVATOR;
                this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE;
                break;
            }
            case MOD_STRUCTURE_2_2: {
                this.canAcceptEssence = true;
                this.essenceReturnCoordinates = OrderedPair.getOrderedPair(1, 1);
                this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0);
                this.energyMax = 500;
                this.towerCoordinates = OrderedPair.getOrderedPair(1, 1);
                this.towerType = TowerBase.TowerType.MOTIVATOR;
                this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE;
                break;
            }
        }
    }

    @Override
    protected void configPerkAdjustments() {
        this.perkAdjustment(PerkModule.PerkType.EFFICIENT_CONSTRUCTION);
    }
}

