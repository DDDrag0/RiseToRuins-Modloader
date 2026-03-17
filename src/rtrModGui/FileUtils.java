package rtrModGui;

import java.io.File;

public class FileUtils {
    public static void deleteDirectory(File dir) {
        if (!dir.exists()) return;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    if (!f.delete()) {
                        System.err.println("⚠️ Failed to delete file: " + f);
                    }
                }
            }
        }
        if (!dir.delete()) {
            System.err.println("⚠️ Failed to delete directory: " + dir);
        }
    }
}