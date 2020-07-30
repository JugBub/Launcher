package com.jugbub;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.desktop.FilesEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<File> emulators = initEmulators(new File("emulators"));
        ArrayList<JButton> buttons = initButtons(emulators.size());
        ArrayList<Icon> icons = initIcons(emulators);

        window(emulators,buttons,icons);


        Scanner scanner = new Scanner(System.in);
        File test = new File("emulators");
        Desktop desktop = Desktop.getDesktop();

        int choice = scanner.nextInt();

        desktop.open(Objects.requireNonNull(test.listFiles())[choice]);



    }

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

    public static void window(ArrayList<File> emulators, ArrayList<JButton> buttons,ArrayList<Icon> icons){
        JFrame frame = new JFrame("Launcher");
        Desktop desktop = Desktop.getDesktop();
        for (int i = 0; i < buttons.size(); i++) {
            final int iteration = i;
            buttons.get(i).setBounds(95*i,0,95,95);
            buttons.get(i).setIcon(icons.get(i));
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        desktop.open(emulators.get(iteration));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
        buttons.forEach(frame::add);
        frame.setSize(750,450);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void testWindow() {
        JFrame frame = new JFrame("Launcher");
        JButton button = new JButton("Click");
        button.setBounds(0, 0, 95, 95);
        frame.add(button);
        frame.setSize((int)(500*1.5), (int)(300*1.5));

        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }

    public static void gridLayoutExample() {
        JFrame frame = new JFrame("Border Layout");
        JButton button,button1, button2, button3,button4;
        button = new JButton("left");
        button1 = new JButton("right");
        button2 = new JButton("top");
        button3 = new JButton("bottom");
        button4 = new JButton("center");
        frame.add(button,BorderLayout.WEST);
        frame.add(button1, BorderLayout.EAST);
        frame.add(button2, BorderLayout.NORTH);
        frame.add(button3, BorderLayout.SOUTH);
        frame.add(button4, BorderLayout.CENTER);

        frame.setSize(300,300);
        frame.setVisible(true);

    }
}
