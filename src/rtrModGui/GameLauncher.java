package rtrModGui;

import java.io.*;
import java.util.*;

public class GameLauncher {

    public static Process launch(List<ModInfo> mods, GameLauncherCallback callback) throws IOException {
        // 1. Find the path to the Java executable
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";

        // 2. Builds the classpath: active mods (directories) + core.jar + libraries
        List<String> classpathEntries = new ArrayList<>();

        // Add the mod directories (in order; the first one takes priority)
        for (ModInfo mod : mods) {
            if (mod.isEnabled()) {
                classpathEntries.add(mod.getPath());
            }
        }

        // Add core.jar and libraries
        File currentDir = new File(".").getCanonicalFile();
        File coreJar = new File(currentDir, "core.jar");
        if (!coreJar.exists()) {
            throw new FileNotFoundException("core.jar non trovato in " + currentDir);
        }
        classpathEntries.add(coreJar.getAbsolutePath());

        File libFolder = new File(currentDir, "lib/jars");
        if (libFolder.exists()) {
            File[] jars = libFolder.listFiles((dir, name) -> name.endsWith(".jar"));
            if (jars != null) {
                for (File jar : jars) {
                    classpathEntries.add(jar.getAbsolutePath());
                }
            }
        }

        String classpath = String.join(File.pathSeparator, classpathEntries);

        // 3. Set the natives (environment variable)
        String nativePath = getNativePath(currentDir);
        List<String> command = new ArrayList<>();
        command.add(javaBin);
        command.add("-Djava.library.path=" + nativePath);
        command.add("-cp");
        command.add(classpath);
        command.add("rtr.system.Launcher");

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(currentDir);
        pb.redirectErrorStream(true); // Merges stdout and stderr

        Process process = pb.start();
        callback.onGameStarting();

        // Reads the process output and sends it to the callback (optional)
        Thread outputReader = createOutputReaderThread (callback, process);
        outputReader.start();

        // Waits for the process to finish in a separate thread
        Thread waitForThread = new Thread(() -> {
            try {
                int exitCode = process.waitFor();
                callback.onGameFinished(exitCode);
            } catch (InterruptedException e) {
                callback.onGameError(e);
            }
        });
        waitForThread.start();

        return process;
    }

    private static Thread createOutputReaderThread(GameLauncherCallback callback, Process process) {
        Thread outputReader = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    callback.onGameOutput(line);
                }
            } catch (IOException e) {
                // The process may already be finished; ignore it
                if (process.isAlive()) callback.onGameError(e);
            }
        });
        outputReader.setDaemon(true);
        return outputReader;
    }

    private static String getNativePath(File currentDir) {
        File natives = new File(currentDir, "natives");
        if (!natives.exists()) {
            natives = new File(currentDir, "lib/natives");
        }
        return natives.exists() ? natives.getAbsolutePath() : "";
    }

    public interface GameLauncherCallback {
        void onGameStarting();
        void onGameOutput(String line);
        void onGameFinished(int exitCode);
        void onGameError(Exception e);
    }
}