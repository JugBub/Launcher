package com.jugbub;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class GamePaths {

    public static void setupEmulatorPath(JMenuItem emulatorPath) {
        emulatorPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> filepath = new ArrayList<>();

                //setupWindow();
                try {
                    JFileChooser fileChooser = new JFileChooser("C:\\users");
                    fileChooser.setDialogTitle("Select Directory");
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileChooser.setAcceptAllFileFilterUsed(false);

                    int i = fileChooser.showOpenDialog(null);
                    if (i == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();

                        /*System.out.println(1);

                        if(file.isDirectory()){
                            for (int j = 0; j < Objects.requireNonNull(file.listFiles()).length; j++) {
                                System.out.println(2);
                                if(!file.isDirectory())
                                    System.out.println(4);
                                    filepath.add(Objects.requireNonNull(file.listFiles())[j].getPath());
                            }
                        }else
                            System.out.println(3);
                            filepath.add(file.getPath());*/


                        GamePathsTXT.writeToFile(file.getPath());
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private static void setupWindow(){
        JFrame frame = new JFrame("Add Games");

        JTextField emulatorPath = new JTextField("c:\\");
        JLabel path = new JLabel("Path:");
        JButton browse = new JButton("Browse");

        JButton save = new JButton("Save");

        // the emulator label, textField and button

        emulatorPath.setBounds(20,30,200,24);

        path.setBounds(emulatorPath.getX(),emulatorPath.getY()-24,16*path.getText().length(),24);

        setupBrowse(browse,emulatorPath);

        // setup the frame

        setupFrame(frame, emulatorPath, browse);

        // the save button

        saveAction(save, emulatorPath);
        save.setBounds(emulatorPath.getX(),emulatorPath.getY()+40,72,30);

        // add

        frame.add(save);
        frame.add(browse);
        frame.add(path);
        frame.add(emulatorPath);
    }

    private static void setupFrame(JFrame frame, JTextField emulatorPath, JButton browse){
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize((emulatorPath.getX()*2+emulatorPath.getWidth()+browse.getWidth()),90*2);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private static void setupBrowse(JButton browse,JTextField emulatorPath){
        browse.setBounds(emulatorPath.getX()+emulatorPath.getWidth(),emulatorPath.getY(),/*16*browse.getText().length()-16*/50,23);
        browse.setBackground(new Color(230, 230, 230));
        browse.setMargin(new Insets(0,0,0,0));
        browseAction(browse);
    }

    private static void saveAction(JButton save, JTextField emulatorPath){
        File file = new File("src/com/jugbub/EmulatorPaths.txt");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Files.write(Paths.get(file.getAbsolutePath()), emulatorPath.getText().getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try {
                    //System.out.println(Arrays.deepToString(EmulatorPathsTXT.getEmulatorPathsTXT().toArray()));
                    //System.out.println("------------------------------");
                    EmulatorPathsTXT.setEmulatorPathsTXT(EmulatorPathsTXT.getEmulatorPathsTXT());
                    //System.out.println(Arrays.deepToString(EmulatorPathsTXT.getEmulatorPathsTXT().toArray()));
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                emulatorPath.setText("c:\\");
            }
        });
    }

    private static void browseAction(JButton browse){
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser("C:\\users");
                    fileChooser.setAcceptAllFileFilterUsed(false);

                    int i = fileChooser.showOpenDialog(null);
                    if (i == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        String filepath = file.getPath();

                        Files.write(Paths.get(file.getAbsolutePath()), filepath.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    }
                } catch (IOException ioException) {
                        ioException.printStackTrace();
                }
            }
        });
    }
}
