package com.jugbub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        JMenuItem gamePath = new JMenuItem("Add Game");

        setupEmulatorPath(emulatorPath);
/*        gamePath.addActionListener(this);*/

        emulator.add(emulatorPath);
        game.add(gamePath);

        return bar;
    }

    private static void setupEmulatorPath(JMenuItem emulatorPath) {
        emulatorPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Add Emulator");
                JTextField emulatorPath = new JTextField("c:\\");
                JTextField extension = new JTextField();
                JLabel path = new JLabel("Path:");
                JLabel type = new JLabel("type:");
                JLabel example = new JLabel("(txt, iso, etc)");
                JTree tree = new JTree();

                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setSize(160*2,90*2);

                emulatorPath.setBounds(40,30,200,24);
                extension.setBounds(emulatorPath.getX(),emulatorPath.getY()+54,35,24);
                System.out.println(emulatorPath.getX());

                path.setBounds(emulatorPath.getX(),emulatorPath.getY()-24,16*path.getText().length(),24);
                type.setBounds(emulatorPath.getX(),emulatorPath.getY()+30,16*type.getText().length(),24);
                example.setBounds(extension.getX()+extension.getWidth(),extension.getY(),16*example.getText().length(),extension.getHeight());



                frame.add(tree);
                frame.add(type);
                frame.add(path);
                frame.add(emulatorPath);
                frame.add(extension);
                frame.add(example);

                frame.setLayout(null);
                frame.setVisible(true);
            }
        });
    }

    private static void setupButtons(ArrayList<File> games,ArrayList<JButton> buttons, ArrayList<Icon> icons){
        for (int i = 0; i < buttons.size(); i++) {
            final int ITERATION = i;
            buttons.get(i).setBounds(95*i,0,95,95);
            buttons.get(i).setIcon(icons.get(i));
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        TxtFile.createGameTXT();
                        TxtFile.writeToGameTXT(games.get(ITERATION));
                        TxtFile.deleteGameTXT();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
    }
}
