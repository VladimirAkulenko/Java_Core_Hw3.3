package com.company;

import game_file_hw2.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        openZip("C:\\Владимир\\Обучение\\Games\\savegames\\zip.zip");
        System.out.println(openProgress("C:\\Владимир\\Обучение\\Games\\savegames\\progress1.dat"));
    }

    public static void openZip(String pathZip) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(pathZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fileOutputStream = new FileOutputStream(name);
                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                    fileOutputStream.write(c);
                }
                fileOutputStream.flush();
                zipInputStream.closeEntry();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameProgress openProgress(String path) {
        GameProgress gameProgress = null;

        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            gameProgress = (GameProgress) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gameProgress;
    }
}
