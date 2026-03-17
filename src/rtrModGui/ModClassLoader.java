package rtrModGui;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class ModClassLoader extends URLClassLoader {
    private final Map<String, byte[]> modifiedClasses = new HashMap<>();

    public ModClassLoader(ClassLoader parent, URL[] jarUrls, List<ModInfo> mods) throws Exception {
        super(jarUrls, parent);
        loadMods(mods);
    }

    private void loadMods(List<ModInfo> mods) throws IOException {
        for (ModInfo mod : mods) {
            if (!mod.isEnabled()) continue;
            File modFolder = new File(mod.getPath());
            loadModFromFolder(modFolder, "");
        }
    }

    private void loadModFromFolder(File folder, String packageName) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                String newPackage = packageName.isEmpty() ? file.getName() : packageName + "." + file.getName();
                loadModFromFolder(file, newPackage);
            } else if (file.getName().endsWith(".class")) {
                byte[] bytes = Files.readAllBytes(file.toPath());
                String className = file.getName().substring(0, file.getName().length() - 6);
                String fullName = packageName.isEmpty() ? className : packageName + "." + className;
                modifiedClasses.put(fullName, bytes);
            }
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (modifiedClasses.containsKey(name)) {
            byte[] bytes = modifiedClasses.get(name);
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.loadClass(name);
    }
}