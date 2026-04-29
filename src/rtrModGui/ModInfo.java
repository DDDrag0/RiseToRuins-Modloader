package rtrModGui;

import java.util.*;

public class ModInfo {
    private final String name;
    private final String path;
    private final List<String> modifiedClasses;
    private boolean enabled;
    private boolean core;

    public ModInfo(String name, String path) {
        this.name = name;
        this.path = path;
        this.modifiedClasses = new ArrayList<>();
        this.enabled = true;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void addClass(String className) {
        modifiedClasses.add(className);
    }

    public boolean isCore() {
        return core;
    }

    public void setCore(boolean core) {
        this.core = core;
    }


    public List<String> getModifiedClasses() {
        return modifiedClasses;
    }

    @Override
    public String toString() {
        return name + " (" + modifiedClasses.size() + " classes" +
                (enabled ? ", enabled" : ", disabled") + ")";
    }
}