package com.jugbub;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
                JLabel type = new JLabel("types to execute:");
                JLabel example = new JLabel("(txt, iso, etc)");
                JButton browse = new JButton("Browse");
                JButton save = new JButton("Save");

                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


                emulatorPath.setBounds(20,30,200,24);
                extension.setBounds(emulatorPath.getX(),emulatorPath.getY()+54,70,24);
                browse.setBounds(emulatorPath.getX()+emulatorPath.getWidth(),emulatorPath.getY(),/*16*browse.getText().length()-16*/50,23);
                browse.setBackground(new Color(230, 230, 230));
                browse.setMargin(new Insets(0,0,0,0));

                setupSave(save, emulatorPath, extension);
                setupBrowse(browse,emulatorPath);

                System.out.println(Color.WHITE);

                path.setBounds(emulatorPath.getX(),emulatorPath.getY()-24,16*path.getText().length(),24);
                type.setBounds(emulatorPath.getX(),emulatorPath.getY()+30,16*type.getText().length(),24);
                example.setBounds(extension.getX()+extension.getWidth(),extension.getY(),5*example.getText().length(),extension.getHeight());

                save.setBounds(example.getX()+example.getWidth()+15,example.getY(),72,30);

                frame.setSize((int)(emulatorPath.getX()*2+emulatorPath.getWidth()+browse.getWidth()),90*2);

                frame.add(save);
                frame.add(browse);
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

    private static void setupSave(JButton save, JTextField emulatorPath, JTextField extensionP){
        File file = new File("src/com/jugbub/EmulatorPaths.txt");
        String emulator = emulatorPath.getText();
        String extension = extensionP.getText();
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner input = null;
                try {
                    input = new Scanner(new File("C:\\Users\\Sebastian\\Documents\\ALL MINECRAFT BLOCKS.txt"));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }


                try {
                    Files.write(Paths.get(file.getAbsolutePath()), "\nThank you very much".getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

/*                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file,true);
                    BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                    bufferWriter.write(data);
                    bufferWriter.close();
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/
                while (input.hasNextLine())
                {
                    System.out.println(input.nextLine());

                }
            }
        });
    }

    private static void setupBrowse(JButton browse,JTextField emulatorPath){
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
