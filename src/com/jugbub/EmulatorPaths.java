package com.jugbub;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public abstract class EmulatorPaths {

    public static void setupClearEmulatorPathsTXT(JMenuItem clearEmulatorPathsTXT){
        clearEmulatorPathsTXT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EmulatorPathsTXT.clear();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
    }

    public static void setupEmulatorPath(JMenuItem emulatorPath) {
        emulatorPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupWindow();
            }
        });
    }

    private static void setupWindow(){
        JFrame frame = new JFrame("Add Emulator");

        JTextField emulatorPath = new JTextField("c:\\");
        JLabel path = new JLabel("Path:");
        JButton browse = new JButton("Browse");

        JLabel type = new JLabel("types to execute:");
        JTextField extension = new JTextField();
        JLabel example = new JLabel("(txt, iso, etc)");
        JButton save = new JButton("Save");

        // the emulator label, textField and button

        emulatorPath.setBounds(20,30,200,24);

        path.setBounds(emulatorPath.getX(),emulatorPath.getY()-24,16*path.getText().length(),24);

        setupBrowse(browse,emulatorPath);

        // the extension label and textField

        type.setBounds(emulatorPath.getX(),emulatorPath.getY()+30,16*type.getText().length(),24);
        extension.setBounds(emulatorPath.getX(),emulatorPath.getY()+54,70,24);
        example.setBounds(extension.getX()+extension.getWidth(),extension.getY(),5*example.getText().length(),extension.getHeight());

        // the save button

        saveAction(save, emulatorPath, extension);
        save.setBounds(example.getX()+example.getWidth()+15,example.getY(),72,30);

        //

        setupFrame(frame, emulatorPath, browse);

        frame.add(save);
        frame.add(browse);
        frame.add(type);
        frame.add(path);
        frame.add(emulatorPath);
        frame.add(extension);
        frame.add(example);
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
        browseAction(browse,emulatorPath);
    }

    private static void saveAction(JButton save, JTextField emulatorPath, JTextField extension){
        File file = new File("src/com/jugbub/EmulatorPaths.txt");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Files.write(Paths.get(file.getAbsolutePath()), String.format("%s;%s",emulatorPath.getText(),extension.getText()).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    EmulatorPathsTXT.setEmulatorPathsTXT(EmulatorPathsTXT.getEmulatorPathsTXT());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                emulatorPath.setText("c:\\");
                extension.setText("");
            }
        });
    }

    private static void browseAction(JButton browse,JTextField emulatorPath){
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("C:\\users");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".exe","exe");
                fileChooser.setFileFilter(filter);

                int i = fileChooser.showOpenDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filepath = file.getPath();

                    emulatorPath.setText(filepath);

                }
            }
        });
    }
}
