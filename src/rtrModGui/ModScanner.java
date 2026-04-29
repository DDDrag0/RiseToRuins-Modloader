package rtrModGui;

import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.*;
import java.util.*;

public class ModScanner {
    private static final String MODS_FOLDER = "mods";

    public static List<ModInfo> scanMods() {
        List<ModInfo> mods = new ArrayList<>();
        File modsFolder = new File(MODS_FOLDER);
        if (!modsFolder.exists()) {
            if (!modsFolder.mkdir()) {
                ModLogger.error("Failed to create mods folder. Please check permissions.");
                return mods;
            }
        }
        File[] modFolders = modsFolder.listFiles(File::isDirectory);
        if (modFolders == null) return mods;
        for (File modFolder : modFolders) {
            ModInfo mod = new ModInfo(modFolder.getName(), modFolder.getAbsolutePath());
            scanModFolder(modFolder, mod, "");
            mods.add(mod);
        }
        return mods;
    }

    private static void scanModFolder(File folder, ModInfo mod, String packagePath) {
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                String newPackage = packagePath.isEmpty() ? file.getName() : packagePath + "." + file.getName();
                scanModFolder(file, mod, newPackage);
            } else if (file.getName().endsWith(".class")) {
                String className = file.getName().substring(0, file.getName().length() - 6);
                String fullClassName = packagePath.isEmpty() ? className : packagePath + "." + className;
                mod.addClass(fullClassName);
            }
        }
    }

    public static boolean installMod(File sourceFile) {
        try {
            String fileName = sourceFile.getName().toLowerCase();
            ModLogger.info("📦 Installation: " + sourceFile.getName());
            if (!fileName.endsWith(".zip")) {
                ModLogger.error("Format not supported. Please use a .zip file.");
                return false;
            }
            boolean success = extractZip(sourceFile);
            if (success) {
                ModLogger.info("✅ Mod installed successfully");
            } else {
                ModLogger.error("❌ Installation failed");
            }
            return success;
        } catch (Exception e) {
            ModLogger.error("Install error: " + e.getMessage());
            return false;
        }
    }

    private static boolean extractZip(File zipFile) throws IOException {
        String baseName = zipFile.getName();
        baseName = baseName.substring(0, baseName.length() - 4);
        File modFolder = new File(MODS_FOLDER + "/" + baseName);
        if (modFolder.exists()) {
            ModLogger.warn("Mod folder already exists: " + baseName + " – adding timestamp");
            modFolder = new File(MODS_FOLDER + "/" + baseName + "_" + System.currentTimeMillis());
        }
        if (!modFolder.mkdirs()) {
            ModLogger.error("Failed to create directory: " + modFolder);
            return false;
        }
        int fileCount = 0;
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile.toPath()))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File destFile = new File(modFolder, entry.getName());
                if (entry.isDirectory()) {
                    if (!destFile.mkdirs()) {
                        ModLogger.warn("Could not create directory: " + destFile);
                    }
                } else {
                    if (!destFile.getParentFile().mkdirs() && !destFile.getParentFile().exists()) {
                        ModLogger.warn("Could not create parent directories for: " + destFile);
                    }
                    try (FileOutputStream fos = new FileOutputStream(destFile)) {
                        byte[] buffer = new byte[4096];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                    fileCount++;
                    if (entry.getName().endsWith(".class")) {
                        ModLogger.debug("  📄 " + entry.getName());
                    }
                }
                zis.closeEntry();
            }
        }
        if (!validateModStructure(modFolder)) {
            ModLogger.error("Invalid mod structure (no .class files). Deleting folder.");
            deleteDirectory(modFolder);
            return false;
        }
        ModLogger.info("✅ " + fileCount + " files extracted to " + modFolder.getPath());
        return true;
    }

    private static boolean validateModStructure(File modFolder) {
        boolean hasClassFile = findAnyClassFile(modFolder);
        if (!hasClassFile) {
            ModLogger.warn("No .class files found in the mod.");
            return false;
        }
        return true;
    }

    public static Map<String, List<ModInfo>> checkConflicts(List<ModInfo> mods) {
        Map<String, List<ModInfo>> classToMods = new HashMap<>();
        for (ModInfo mod : mods) {
            for (String cls : mod.getModifiedClasses()) {
                classToMods.computeIfAbsent(cls, k -> new ArrayList<>()).add(mod);
            }
        }
        return classToMods.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void deleteDirectory(File dir) {
        if (!dir.exists()) return;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) deleteDirectory(f);
                else if (!f.delete()) ModLogger.warn("Failed to delete file: " + f);
            }
        }
        if (!dir.delete()) ModLogger.warn("Failed to delete directory: " + dir);
    }

    private static boolean findAnyClassFile(File folder) {
        File[] files = folder.listFiles();
        if (files == null) return false;
        for (File file : files) {
            if (file.isDirectory()) {
                if (findAnyClassFile(file)) return true;
            } else if (file.getName().endsWith(".class")) {
                return true;
            }
        }
        return false;
    }
}