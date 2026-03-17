package rtrModGui;

import java.nio.file.Files;
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
                System.err.println("❌ Failed to create mods folder. Please check permissions.");
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
            System.out.println("📦 Installation: " + sourceFile.getName());
            if (!fileName.endsWith(".zip")) {
                System.err.println("❌ Format not supported. Please use a .zip file.");
                return false;
            }
            boolean success = extractZip(sourceFile);
            if (success) {
                System.out.println("✅ Mod installed successfully");
            } else {
                System.err.println("❌ Installation failed");
            }
            return success;
        } catch (Exception e) {
            System.err.println("❌ Install error: " + e.getMessage());
            // For more details in dev:
            // e.printStackTrace();
            return false;
        }
    }

    private static boolean extractZip(File zipFile) throws IOException {
        String baseName = zipFile.getName();
        baseName = baseName.substring(0, baseName.length() - 4);
        File modFolder = new File(MODS_FOLDER + "/" + baseName);
        if (modFolder.exists()) {
            // Should not happen because GUI asks before, but just in case:
            System.out.println("⚠️ Mod folder already exists: " + baseName + " – adding timestamp");
            modFolder = new File(MODS_FOLDER + "/" + baseName + "_" + System.currentTimeMillis());
        }
        if (!modFolder.mkdirs()) {
            System.err.println("❌ Failed to create directory: " + modFolder);
            return false;
        }
        int fileCount = 0;
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile.toPath()))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File destFile = new File(modFolder, entry.getName());
                if (entry.isDirectory()) {
                    if (!destFile.mkdirs()) {
                        System.err.println("⚠️ Could not create directory: " + destFile);
                    }
                } else {
                    if (!destFile.getParentFile().mkdirs() && !destFile.getParentFile().exists()) {
                        System.err.println("⚠️ Could not create parent directories for: " + destFile);
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
                        System.out.println("  📄 " + entry.getName());
                    }
                }
                zis.closeEntry();
            }
        }
        if (!validateModStructure(modFolder)) {
            System.err.println("❌ Invalid mod structure (no .class files). Deleting folder.");
            FileUtils.deleteDirectory(modFolder);
            return false;
        }
        System.out.println("✅ " + fileCount + " files extracted to " + modFolder.getPath());
        return true;
    }

    private static boolean validateModStructure(File modFolder) {
        boolean hasClassFile = findAnyClassFile(modFolder);
        if (!hasClassFile) {
            System.err.println("⚠️ No .class files found in the mod.");
            return false;
        }
        return true;
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