package com.jugbub;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Window {

    public static void window(ArrayList<File> games, ArrayList<JButton> buttons, ArrayList<Icon> icons){
        JFrame frame = new JFrame("Launcher");

        buttons.forEach(frame::add);
        frame.setSize(750,450);

        setupButtons(games, buttons, icons);

        frame.setLayout(null);
        frame.setVisible(true);
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
