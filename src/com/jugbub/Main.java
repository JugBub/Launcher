package com.jugbub;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<File> emulators = Inits.initEmulators(new File("emulators"));
        ArrayList<JButton> buttons = Inits.initButtons(emulators.size());
        ArrayList<Icon> icons = Inits.initIcons(emulators);

        Window.window(emulators,buttons,icons);
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
