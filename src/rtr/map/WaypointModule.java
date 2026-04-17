package rtr.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import rtr.ModuleBase;
import rtr.font.Text;
import rtr.mobs.MobBase;
import rtr.objects.ObjectBase;
import rtr.objects.ObjectModule;
import rtr.objects.objectflags.ObjectFlags;
import rtr.objects.towerhead.TowerBase;
import rtr.resources.ResourceModule;
import rtr.states.StateBase;
import rtr.utilities.OrderedPair;
import rtr.utilities.Utilities;

import java.util.*;

public class WaypointModule
extends ModuleBase {
    private ArrayList<OrderedPair> rangerPatrolWaypoints;
    private ArrayList<OrderedPair> golemWaypoints;
    private HashMap<ResourceModule.ResourceType, ArrayList<OrderedPair>> animalWaypoints;
    private ArrayList<OrderedPair> rangerPatrolWaypointsRaw;
    private ArrayList<OrderedPair> golemWaypointsRaw;
    private ArrayList<OrderedPair> rangerPatrolWaypointsNew;
    private ArrayList<OrderedPair> golemWaypointsNew;
    private HashMap<ResourceModule.ResourceType, ArrayList<OrderedPair>> animalWaypointsNew;
    private int updateDelayTick;
    private int updateDelay = 200;
    private boolean done;
    private boolean sortingDone;
    private int step;

    @Override
    public void initModule(ModuleType mT, StateBase cS) throws SlickException {
        super.initModule(mT, cS);
        if (this.rangerPatrolWaypoints == null) {
            this.rangerPatrolWaypoints = new ArrayList();
        }
        if (this.golemWaypoints == null) {
            this.golemWaypoints = new ArrayList();
        }
        if (this.animalWaypoints == null) {
            this.animalWaypoints = new HashMap();
        }
        if (this.rangerPatrolWaypointsRaw == null) {
            this.rangerPatrolWaypointsRaw = new ArrayList();
        }
        if (this.golemWaypointsRaw == null) {
            this.golemWaypointsRaw = new ArrayList();
        }
        if (this.rangerPatrolWaypointsNew == null) {
            this.rangerPatrolWaypointsNew = new ArrayList();
        }
        if (this.golemWaypointsNew == null) {
            this.golemWaypointsNew = new ArrayList();
        }
        if (this.animalWaypointsNew == null) {
            this.animalWaypointsNew = new HashMap();
        }
        this.requiresSync = true;
        this.initLoad();
    }

    @Override
    public void resetModule() throws SlickException {
        super.resetModule();
        this.rangerPatrolWaypoints.clear();
        this.golemWaypoints.clear();
        this.animalWaypoints.clear();
    }

    @Override
    protected void newMainMenu() throws SlickException {
    }

    @Override
    protected void newPlay() throws SlickException {
    }

    @Override
    protected void loadPlay() throws SlickException {
    }

    @Override
    protected void newMapEditor() throws SlickException {
    }

    @Override
    protected void loadMapEditor() throws SlickException {
    }

    @Override
    protected void newWorldMap() throws SlickException {
    }

    @Override
    protected void loadWorldMap() throws SlickException {
    }

    @Override
    protected void loadRegionalSaveData() throws SlickException {
    }

    @Override
    protected void loadWorldSaveData() throws SlickException {
    }

    @Override
    public void initLoadSync() throws SlickException {
        this.generateWaypointsNow();
        this.synced = true;
    }

    public void update() throws SlickException {
        ++this.updateDelayTick;
        if (this.updateDelayTick > this.updateDelay) {
            this.generateWaypoints();
            if (this.done) {
                this.done = false;
                this.updateDelayTick = 0;
            }
        }
    }

    public void render(Graphics g) {
        this.renderPath(g, this.rangerPatrolWaypoints, Color.green);
        this.renderPath(g, this.golemWaypoints, Color.yellow);
        g.setColor(Color.white);
        for (ArrayList<OrderedPair> list : this.animalWaypoints.values()) {
            for (OrderedPair s : list) {
                float x1 = (float)(s.getX() * 16) + this.map.getMapX();
                float y1 = (float)(s.getY() * 16) + this.map.getMapY();
                g.setColor(Color.black);
                g.fillOval(x1 + 2.0f, y1 + 2.0f, 12.0f, 12.0f);
                g.setColor(Color.green);
                g.fillOval(x1 + 3.0f, y1 + 3.0f, 10.0f, 10.0f);
            }
        }
    }

    private void renderPath(Graphics g, ArrayList<OrderedPair> waypoints, Color color) {
        g.setColor(color);
        int o = 0;
        while (o < waypoints.size()) {
            OrderedPair s = waypoints.get(o);
            OrderedPair e = null;
            e = o < waypoints.size() - 1 ? waypoints.get(o + 1) : waypoints.get(0);
            float x1 = (float)(s.getX() * 16) + this.map.getMapX();
            float y1 = (float)(s.getY() * 16) + this.map.getMapY();
            float x2 = (float)(e.getX() * 16) + this.map.getMapX();
            float y2 = (float)(e.getY() * 16) + this.map.getMapY();
            g.drawLine(x1, y1, x2, y2);
            this.font.drawString(x1, y1, String.valueOf(o), Text.FontType.BODY, 2);
            ++o;
        }
    }

    private void generateWaypointsNow() throws SlickException {
        ResourceModule.ResourceType type;
        this.rangerPatrolWaypointsRaw.clear();
        this.golemWaypointsRaw.clear();
        ArrayList<ObjectBase> objectList = this.object.getObjectMasterBuildingPriorityList();
        for (ObjectBase o : objectList) {
            ObjectFlags objectFlags = o.getObjectFlags();
            if (objectFlags.getSubType() != ObjectBase.ObjectSubType.BUILT) continue;
            if (objectFlags.getBaseType() == MapTilesLoader.TileSet.OUTPOST || objectFlags.getBaseType() == MapTilesLoader.TileSet.RANGER_LODGE) {
                this.rangerPatrolWaypointsRaw.add(o.getBestRandomInteractCoordinates(true));
                continue;
            }
            if (objectFlags.getBaseType() != MapTilesLoader.TileSet.WOOD_GOLEM_COMBOBULATOR && objectFlags.getBaseType() != MapTilesLoader.TileSet.STONE_GOLEM_COMBOBULATOR && objectFlags.getBaseType() != MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR && o.getTower() == null) continue;
            this.golemWaypointsRaw.add(o.getBestRandomInteractCoordinates(true));
        }
        this.sortWaypointsNow(this.rangerPatrolWaypointsRaw, this.rangerPatrolWaypoints);
        this.sortWaypointsNow(this.golemWaypointsRaw, this.golemWaypoints);
        this.animalWaypoints.clear();
        int nearbySearchDistance = 32;
        int x = 0;
        while (x < 256) {
            int y = 0;
            while (y < 256) {
                type = this.resource.getResourceType()[x][y];
                if (type != null) {
                    int x2 = -1;
                    block3: while (x2 < 2) {
                        int y2 = -1;
                        while (y2 < 2) {
                            int thisY;
                            int thisX;
                            if (!(x2 == 0 && y2 == 0 || x2 == -1 && y2 == -1 || x2 == 1 && y2 == 1 || x2 == -1 && y2 == 1 || x2 == 1 && y2 == -1 || Utilities.isOutOfBoundsOfUsableMap(thisX = x + x2, thisY = y + y2) || this.map.getMapData().getBlockMap(MapData.BlockMapGroup.STANDARD_NO_CORRUPTION)[thisX][thisY] != 0 || this.map.getMap()[thisX][thisY][6] > 0)) {
                                ArrayList<OrderedPair> thisTypeList;
                                if (this.animalWaypoints.get((Object)type) == null) {
                                    this.animalWaypoints.put(type, new ArrayList());
                                }
                                if (!(thisTypeList = this.animalWaypoints.get((Object)type)).contains(OrderedPair.getOrderedPair(thisX, thisY))) {
                                    for (OrderedPair o : thisTypeList) {
                                        if (Utilities.coordinatesWithin(o.getX(), o.getY(), thisX, thisY, nearbySearchDistance)) break block3;
                                    }
                                    thisTypeList.add(OrderedPair.getOrderedPair(thisX, thisY));
                                    break block3;
                                }
                            }
                            ++y2;
                        }
                        ++x2;
                    }
                }
                ++y;
            }
            ++x;
        }
        ArrayList<OrderedPair> foodList = this.animalWaypoints.get((Object)ResourceModule.ResourceType.RAW_VEGETABLE);
        ArrayList<OrderedPair> waterList = this.animalWaypoints.get((Object)ResourceModule.ResourceType.DIRTY_WATER_BUCKET);
        if (foodList == null || foodList.size() == 0 || waterList == null || waterList.size() == 0) {
            this.animalWaypoints.clear();
        }
        ResourceModule.ResourceType[] resourceTypeArray = ResourceModule.ResourceType.values();
        int n = resourceTypeArray.length;
        int n2 = 0;
        while (n2 < n) {
            type = resourceTypeArray[n2];
            if (this.animalWaypoints.get((Object)type) != null) {
                if (this.animalWaypoints.get((Object)type).size() == 0) {
                    this.animalWaypoints.remove((Object)type);
                } else if (type != ResourceModule.ResourceType.DIRTY_WATER_BUCKET && type != ResourceModule.ResourceType.RAW_VEGETABLE) {
                    ArrayList<OrderedPair> thisTypeList = this.animalWaypoints.get((Object)type);
                    boolean foundWater = false;
                    boolean foundFood = false;
                    ArrayList<OrderedPair> toRemoveFromList = new ArrayList<OrderedPair>();
                    for (OrderedPair o : thisTypeList) {
                        for (OrderedPair oF : foodList) {
                            if (!Utilities.coordinatesWithin(o.getX(), o.getY(), oF.getX(), oF.getY(), nearbySearchDistance)) continue;
                            foundFood = true;
                        }
                        for (OrderedPair oW : waterList) {
                            if (!Utilities.coordinatesWithin(o.getX(), o.getY(), oW.getX(), oW.getY(), nearbySearchDistance)) continue;
                            foundWater = true;
                        }
                        if (foundFood && foundWater) {
                            ArrayList<OrderedPair> nearbyCoordinates = this.mob.getPathfinder().getCoordinatesInRange(o.getX(), o.getY(), nearbySearchDistance, false, MapData.BlockMapGroup.STANDARD_NO_CORRUPTION);
                            foundWater = false;
                            foundFood = false;
                            for (OrderedPair oN : nearbyCoordinates) {
                                ResourceModule.ResourceType thisType = this.resource.getResourceType()[oN.getX()][oN.getY()];
                                if (thisType == null) continue;
                                if (thisType == ResourceModule.ResourceType.RAW_VEGETABLE) {
                                    foundFood = true;
                                }
                                if (thisType == ResourceModule.ResourceType.DIRTY_WATER_BUCKET) {
                                    foundWater = true;
                                }
                                if (foundFood && foundWater) break;
                            }
                        }
                        if (foundFood && foundWater) continue;
                        toRemoveFromList.add(o);
                    }
                    thisTypeList.removeAll(toRemoveFromList);
                    if (this.animalWaypoints.get((Object)type).size() == 0) {
                        this.animalWaypoints.remove((Object)type);
                    }
                }
            }
            ++n2;
        }
        this.animalWaypoints.remove((Object)ResourceModule.ResourceType.RAW_VEGETABLE);
        this.animalWaypoints.remove((Object)ResourceModule.ResourceType.DIRTY_WATER_BUCKET);
    }

    private void sortWaypointsNow(ArrayList<OrderedPair> raw, ArrayList<OrderedPair> sorted) throws SlickException {
        sorted.clear();
        Collections.sort(raw, new Comparator<OrderedPair>(){

            @Override
            public int compare(OrderedPair o1, OrderedPair o2) {
                if (o1 == o2) {
                    return 0;
                }
                return o1.getY() - o2.getY();
            }
        });
        if (raw.size() > 0) {
            sorted.add(raw.get(0));
            while (raw.size() > 0) {
                OrderedPair source = raw.get(0);
                raw.remove(0);
                OrderedPair o = this.mob.getPathfinder().searchFor(source.getX(), source.getY(), raw, false, true, MapData.BlockMapGroup.STANDARD);
                if (o == null) break;
                raw.remove(o);
                raw.add(0, o);
                sorted.add(o);
            }
        }
    }

    private void generateWaypoints() throws SlickException {
        switch (this.step) {
            case 0: {
                this.rangerPatrolWaypointsRaw.clear();
                this.golemWaypointsRaw.clear();
                this.rangerPatrolWaypointsNew.clear();
                this.golemWaypointsNew.clear();
                ArrayList<ObjectBase> objectList = this.object.getObjectMasterBuildingPriorityList();
                for (ObjectBase o : objectList) {
                    ObjectFlags objectFlags = o.getObjectFlags();
                    if (objectFlags.getSubType() != ObjectBase.ObjectSubType.BUILT) continue;
                    if (objectFlags.getBaseType() == MapTilesLoader.TileSet.OUTPOST || objectFlags.getBaseType() == MapTilesLoader.TileSet.RANGER_LODGE) {
                        this.rangerPatrolWaypointsRaw.add(o.getBestRandomInteractCoordinates(false));
                        continue;
                    }
                    if (objectFlags.getBaseType() != MapTilesLoader.TileSet.WOOD_GOLEM_COMBOBULATOR && objectFlags.getBaseType() != MapTilesLoader.TileSet.STONE_GOLEM_COMBOBULATOR && objectFlags.getBaseType() != MapTilesLoader.TileSet.CRYSTAL_GOLEM_COMBOBULATOR && (o.getTower() == null || o.getTower().getTowerFlags().getTowerType() == TowerBase.TowerType.MOTIVATOR || objectFlags.getMobGroup() != MobBase.MobGroup.VILLAGER)) continue;
                    this.golemWaypointsRaw.add(o.getBestRandomInteractCoordinates(false));
                }
                ++this.step;
                break;
            }
            case 1: {
                ResourceModule.ResourceType type;
                this.animalWaypointsNew.clear();
                int x = 0;
                while (x < 256) {
                    int y = 0;
                    while (y < 256) {
                        type = this.resource.getResourceType()[x][y];
                        if (type != null) {
                            int x2 = -1;
                            block12: while (x2 < 2) {
                                int y2 = -1;
                                while (y2 < 2) {
                                    int thisY;
                                    int thisX;
                                    if (!(x2 == 0 && y2 == 0 || x2 == -1 && y2 == -1 || x2 == 1 && y2 == 1 || x2 == -1 && y2 == 1 || x2 == 1 && y2 == -1 || Utilities.isOutOfBoundsOfUsableMap(thisX = x + x2, thisY = y + y2) || this.map.getMapData().getBlockMap(MapData.BlockMapGroup.STANDARD_NO_CORRUPTION)[thisX][thisY] != 0 || this.map.getMap()[thisX][thisY][6] > 0)) {
                                        ArrayList<OrderedPair> thisTypeList;
                                        if (this.animalWaypointsNew.get((Object)type) == null) {
                                            this.animalWaypointsNew.put(type, new ArrayList());
                                        }
                                        if (!(thisTypeList = this.animalWaypointsNew.get((Object)type)).contains(OrderedPair.getOrderedPair(thisX, thisY))) {
                                            for (OrderedPair o : thisTypeList) {
                                                if (Utilities.coordinatesWithin(o.getX(), o.getY(), thisX, thisY, 32)) break block12;
                                            }
                                            thisTypeList.add(OrderedPair.getOrderedPair(thisX, thisY));
                                            break block12;
                                        }
                                    }
                                    ++y2;
                                }
                                ++x2;
                            }
                        }
                        ++y;
                    }
                    ++x;
                }
                ArrayList<OrderedPair> foodList = this.animalWaypointsNew.get((Object)ResourceModule.ResourceType.RAW_VEGETABLE);
                ArrayList<OrderedPair> waterList = this.animalWaypointsNew.get((Object)ResourceModule.ResourceType.DIRTY_WATER_BUCKET);
                if (foodList == null || foodList.size() == 0 || waterList == null || waterList.size() == 0) {
                    this.animalWaypointsNew.clear();
                }
                ResourceModule.ResourceType[] resourceTypeArray = ResourceModule.ResourceType.values();
                int n = resourceTypeArray.length;
                int n2 = 0;
                while (n2 < n) {
                    type = resourceTypeArray[n2];
                    if (this.animalWaypointsNew.get((Object)type) != null) {
                        if (this.animalWaypointsNew.get((Object)type).size() == 0) {
                            this.animalWaypointsNew.remove((Object)type);
                        } else if (type != ResourceModule.ResourceType.DIRTY_WATER_BUCKET && type != ResourceModule.ResourceType.RAW_VEGETABLE) {
                            ArrayList<OrderedPair> thisTypeList = this.animalWaypointsNew.get((Object)type);
                            boolean foundWater = false;
                            boolean foundFood = false;
                            ArrayList<OrderedPair> toRemoveFromList = new ArrayList<OrderedPair>();
                            for (OrderedPair o : thisTypeList) {
                                for (OrderedPair oF : foodList) {
                                    if (!Utilities.coordinatesWithin(o.getX(), o.getY(), oF.getX(), oF.getY(), 32)) continue;
                                    foundFood = true;
                                }
                                for (OrderedPair oW : waterList) {
                                    if (!Utilities.coordinatesWithin(o.getX(), o.getY(), oW.getX(), oW.getY(), 32)) continue;
                                    foundWater = true;
                                }
                                if (foundFood && foundWater) {
                                    ArrayList<OrderedPair> nearbyCoordinates = this.mob.getPathfinder().getCoordinatesInRange(o.getX(), o.getY(), 32, false, MapData.BlockMapGroup.STANDARD_NO_CORRUPTION);
                                    foundWater = false;
                                    foundFood = false;
                                    for (OrderedPair oN : nearbyCoordinates) {
                                        ResourceModule.ResourceType thisType = this.resource.getResourceType()[oN.getX()][oN.getY()];
                                        if (thisType == null) continue;
                                        if (thisType == ResourceModule.ResourceType.RAW_VEGETABLE) {
                                            foundFood = true;
                                        }
                                        if (thisType == ResourceModule.ResourceType.DIRTY_WATER_BUCKET) {
                                            foundWater = true;
                                        }
                                        if (foundFood && foundWater) break;
                                    }
                                }
                                if (foundFood && foundWater) continue;
                                toRemoveFromList.add(o);
                            }
                            thisTypeList.removeAll(toRemoveFromList);
                            if (this.animalWaypointsNew.get((Object)type).size() == 0) {
                                this.animalWaypointsNew.remove((Object)type);
                            }
                        }
                    }
                    ++n2;
                }
                this.animalWaypointsNew.remove((Object)ResourceModule.ResourceType.RAW_VEGETABLE);
                this.animalWaypointsNew.remove((Object)ResourceModule.ResourceType.DIRTY_WATER_BUCKET);
                ++this.step;
                break;
            }
            case 2: {
                this.sortWaypointsStepOne(this.rangerPatrolWaypointsRaw);
                ++this.step;
                break;
            }
            case 3: {
                this.sortWaypointsStepTwo(this.rangerPatrolWaypointsRaw, this.rangerPatrolWaypointsNew);
                if (!this.sortingDone) break;
                ++this.step;
                this.sortingDone = false;
                break;
            }
            case 4: {
                this.sortWaypointsStepOne(this.golemWaypointsRaw);
                ++this.step;
                break;
            }
            case 5: {
                this.sortWaypointsStepTwo(this.golemWaypointsRaw, this.golemWaypointsNew);
                if (!this.sortingDone) break;
                ++this.step;
                this.sortingDone = false;
                break;
            }
            case 6: {
                this.rangerPatrolWaypoints.clear();
                this.rangerPatrolWaypoints.addAll(this.rangerPatrolWaypointsNew);
                this.golemWaypoints.clear();
                this.golemWaypoints.addAll(this.golemWaypointsNew);
                this.animalWaypoints.clear();
                for (ResourceModule.ResourceType t : this.animalWaypointsNew.keySet()) {
                    this.animalWaypoints.put(t, new ArrayList());
                    this.animalWaypoints.get((Object)t).addAll((Collection<OrderedPair>)this.animalWaypointsNew.get((Object)t));
                }
                this.step = 0;
                this.done = true;
            }
        }
    }

    private void sortWaypointsStepOne(ArrayList<OrderedPair> raw) throws SlickException {
        Collections.sort(raw, new Comparator<OrderedPair>(){

            @Override
            public int compare(OrderedPair o1, OrderedPair o2) {
                if (o1 == o2) {
                    return 0;
                }
                return o1.getY() - o2.getY();
            }
        });
    }

    private void sortWaypointsStepTwo(ArrayList<OrderedPair> raw, ArrayList<OrderedPair> sorted) throws SlickException {
        if (raw.size() > 0) {
            sorted.add(raw.get(0));
            int x = 0;
            while (x < 5) {
                if (raw.size() > 0) {
                    OrderedPair source = raw.get(0);
                    raw.remove(0);
                    OrderedPair o = this.mob.getPathfinder().searchFor(source.getX(), source.getY(), raw, false, true, MapData.BlockMapGroup.STANDARD);
                    if (o == null) {
                        return;
                    }
                    raw.remove(o);
                    raw.add(0, o);
                    sorted.add(o);
                }
                ++x;
            }
        } else {
            this.sortingDone = true;
        }
    }

    public ArrayList<OrderedPair> getRangerWaypoints() {
        return this.rangerPatrolWaypoints;
    }

    public ArrayList<OrderedPair> getGolemWaypoints() {
        return this.golemWaypoints;
    }

    public OrderedPair getRandomFrontLinesWaypoint(MobBase m) {
        ArrayList<ObjectBase> nearbyObjects = this.object.getObjectsNearby(m, 8);
        ArrayList<ObjectBase> validObjects = new ArrayList<ObjectBase>();
        for (ObjectBase o : nearbyObjects) {
            if (o.getTower() == null || o.getObjectFlags().getMobGroup() != m.getMobGroup()) continue;
            validObjects.add(o);
        }
        if (validObjects.size() <= 0) {
            ArrayList<ObjectBase> towers = this.object.getObjectsBySortGroup(ObjectModule.ObjectSortGroup.TOWERS);
            for (ObjectBase o : towers) {
                if (!m.canReach(o) || o.getObjectFlags().getMobGroup() != m.getMobGroup()) continue;
                validObjects.add(o);
            }
        }
        if (validObjects.size() <= 0) {
            return null;
        }
        return ((ObjectBase)validObjects.get(Utilities.randomInt(validObjects.size()))).getRandomCoordinates();
    }

    public HashMap<ResourceModule.ResourceType, ArrayList<OrderedPair>> getAnimalResourceWaypointsMap() {
        return this.animalWaypoints;
    }

    public ArrayList<OrderedPair> getAnimalResourceWaypoints(ResourceModule.ResourceType t) {
        return this.animalWaypoints.get((Object)t);
    }
}

