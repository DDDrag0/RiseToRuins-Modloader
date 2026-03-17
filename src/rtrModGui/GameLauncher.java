package rtrModGui;

import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import java.util.*;

public class GameLauncher {

    public static void launch(List<ModInfo> mods, GameLauncherCallback callback) throws Exception {
        File currentDir = new File(".").getCanonicalFile();
        List<URL> jarUrls = new ArrayList<>();
        File coreJar = new File(currentDir, "core.jar");
        if (!coreJar.exists()) {
            throw new FileNotFoundException("core.jar not found. Please place the mod loader in the game folder.");
        }
        jarUrls.add(coreJar.toURI().toURL());
        File libFolder = new File(currentDir, "lib/jars");
        if (libFolder.exists()) {
            File[] libJars = libFolder.listFiles((dir, name) -> name.endsWith(".jar"));
            if (libJars != null) {
                for (File jar : libJars) {
                    jarUrls.add(jar.toURI().toURL());
                }
            }
        }
        configureNatives(currentDir);
        ModClassLoader modLoader = new ModClassLoader(ClassLoader.getSystemClassLoader(), jarUrls.toArray(new URL[0]), mods);
        Thread.currentThread().setContextClassLoader(modLoader);
        Class<?> mainClass = modLoader.loadClass("rtr.system.Launcher");
        Method mainMethod = mainClass.getMethod("main", String[].class);
        callback.onGameStarting();
        Thread gameThread = new Thread(() -> {
            try {
                mainMethod.invoke(null, (Object) new String[]{});
                callback.onGameFinished();
            } catch (Exception e) {
                callback.onGameError(e);
            }
        });
        gameThread.setContextClassLoader(modLoader);
        gameThread.start();
    }

    private static void configureNatives(File currentDir) {
        File nativesFolder = new File(currentDir, "natives");
        if (!nativesFolder.exists()) {
            nativesFolder = new File(currentDir, "lib/natives");
        }
        if (nativesFolder.exists()) {
            String nativePath = nativesFolder.getAbsolutePath();
            String currentPath = System.getProperty("java.library.path", "");
            System.setProperty("java.library.path", nativePath + File.pathSeparator + currentPath);
        } else {
            System.err.println("⚠️ No natives folder found. LWJGL may fail to load.");
        }
    }

    public interface GameLauncherCallback {
        void onGameStarting();
        void onGameFinished();
        void onGameError(Exception e);
    }
}