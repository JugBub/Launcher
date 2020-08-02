package com.jugbub;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Window {

    public static void window(ArrayList<File> games, ArrayList<JButton> buttons, ArrayList<Icon> icons){
        JFrame frame = new JFrame("Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JRootPane root = frame.getRootPane();

        root.setJMenuBar(createMenuBar());

        buttons.forEach(root.getContentPane()::add);
        frame.setSize(750,450);

        setupButtons(games, buttons, icons);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    private static JMenuBar createMenuBar(){
        JMenuBar bar = new JMenuBar();
        JMenu emulator = new JMenu("Emulator");
        JMenu game = new JMenu("Game");
        bar.add(emulator);
        bar.add(game);

        JMenuItem emulatorPath = new JMenuItem("Add Emulator");
        JMenuItem clearEmulatorPathsTXT = new JMenuItem("Clear Paths");
        JMenuItem gamePath = new JMenuItem("Add Games");

        EmulatorPaths.setupEmulatorPath(emulatorPath);
        EmulatorPaths.setupClearEmulatorPathsTXT(clearEmulatorPathsTXT);
/*        gamePath.addActionListener(this);*/

        emulator.add(emulatorPath);
        emulator.add(clearEmulatorPathsTXT);
        game.add(gamePath);

        return bar;
    }

    private static void setupButtons(ArrayList<File> games,ArrayList<JButton> buttons, ArrayList<Icon> icons){
        for (int i = 0; i < buttons.size(); i++) {
            final int ITERATION = i;
            buttons.get(i).setBounds(95*i,0,95,95);
            buttons.get(i).setIcon(icons.get(i));
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    try {
                        Runtime runtime = Runtime.getRuntime();
                        File game = new File("D:\\roms\\.GCN\\F-Zero GX (USA).iso");
                        String[] strings = {"F:\\Downloads\\dolphin-master-5.0-8830-x64\\dolphin-master-5.0-9320-x64\\Dolphin-x64\\Dolphin.exe",game.getPath()} ;

                        System.out.println(game);
                        Process process = runtime.exec(/*String.format("%s %s",game.getPath() , games.get(ITERATION).getPath())*/strings);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
    }
}
