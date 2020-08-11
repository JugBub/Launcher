package com.jugbub;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Inits {
    public static ArrayList<Icon> initIcons(ArrayList<File> emulators){
        ArrayList<Icon> icons = new ArrayList<>();

        emulators.forEach((n) -> icons.add(FileSystemView.getFileSystemView().getSystemIcon( n )));

        return icons;
    }

    public static ArrayList<File> initEmulators(File folder){
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(folder.listFiles())));
    }

    public static ArrayList<Launch> initButtons() throws FileNotFoundException {
        ArrayList<Launch> buttons = new ArrayList<>();
        ArrayList<File> gameDirs = GamePathsTXT.getDirs();

        /*
        for (int i = 0; i < amount; i++) {
            buttons.add(new JButton());
        }*/

        for (int i = 0; i < gameDirs.size(); i++) {
            ArrayList<File> currentDir = new ArrayList<>(Arrays.asList(Objects.requireNonNull(  gameDirs.get(i).listFiles() )));

            for (int j = 0; j < currentDir.size(); j++) {
                if (!currentDir.get(j).isDirectory())
                    buttons.add(new Launch());
            }
        }

        return buttons;
    }
}
