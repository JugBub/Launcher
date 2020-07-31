package com.jugbub;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
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

    public static ArrayList<JButton> initButtons(int amount){
        ArrayList<JButton> buttons = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            buttons.add(new JButton());
        }

        return buttons;
    }
}
