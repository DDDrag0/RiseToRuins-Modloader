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

public class VegetableMotivatorFlags extends ObjectFlags {
    public VegetableMotivatorFlags(MapTilesLoader.TileSet currentType, ObjectBase.ObjectSubType subType) {
        super(currentType, subType);
    }

    @Override
    protected void configBasics() {
        this.baseType = MapTilesLoader.TileSet.VEGETABLE_MOTIVATOR;
        this.width = 2;
        this.height = 2;
        String systemName = "vegetableMotivator";
        this.baseName = Text.getText("Vegetable Motivator");
        this.name = Text.getText("Vegetable Motivator");
        this.description = Text.getText("Motivates vegetable tiles");
        this.resourceBaseValues.put(ResourceModule.ResourceType.ROCK, 4);
        this.resourceBaseValues.put(ResourceModule.ResourceType.CUT_STONE, 24);
        this.resourceBaseValues.put(ResourceModule.ResourceType.CRYSTAL, 8);
        this.requiredSupportBuildings.add(new MapTilesLoader.TileSet[]{MapTilesLoader.TileSet.ESSENCE_COLLECTOR});
        this.recommendedSupportBuildings.add(new MapTilesLoader.TileSet[]{MapTilesLoader.TileSet.LUMBER_SHACK});
        this.allowDismantle = true;
        this.allowPause = true;
    }

    @Override
    protected void configConstruction() {
        this.mobGroup = MobBase.MobGroup.VILLAGER;
    }

    @Override
    protected void configBuilt() {
        this.mobGroup = MobBase.MobGroup.VILLAGER;
        this.baseRange = 8;
        this.baseDesirability = 1;
        this.canAcceptEssence = true;
        this.essenceReturnCoordinates = OrderedPair.getOrderedPair(1, 1);
        this.essenceReturnCoordinatesPixelOffset = OrderedPair.getOrderedPair(0, 0);
        this.energyMax = 300;
        this.towerCoordinates = OrderedPair.getOrderedPair(1, 1);
        this.towerType = TowerBase.TowerType.MOTIVATOR;
        this.towerUpgradeStage = TowerBase.TowerUpgradeStage.BASE;
    }

    @Override
    protected void configPerkAdjustments() {
        this.perkAdjustment(PerkModule.PerkType.EFFICIENT_CONSTRUCTION);
    }
}

