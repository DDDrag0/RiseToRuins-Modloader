package rtrModGui;

import java.io.*;
import java.util.*;

public class ModStateManager {
    private static final File STATE_FILE = new File("mods/mod_state.properties");

    public static void saveState(List<ModInfo> mods) {
        Properties props = new Properties();
        for (ModInfo mod : mods) {
            props.setProperty(mod.getName(), String.valueOf(mod.isEnabled()));
        }
        try (FileOutputStream out = new FileOutputStream(STATE_FILE)) {
            props.store(out, "Mod enabled states");
        } catch (IOException e) {
            System.err.println("Unable to save mod status: " + e.getMessage());
        }
    }

    public static void loadState(List<ModInfo> mods) {
        if (!STATE_FILE.exists()) return;
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(STATE_FILE)) {
            props.load(in);
            for (ModInfo mod : mods) {
                String val = props.getProperty(mod.getName());
                if (val != null) {
                    mod.setEnabled(Boolean.parseBoolean(val));
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to load mod status: " + e.getMessage());
        }
    }
}