package com.jugbub;

import javax.swing.*;
import java.util.ArrayList;

public class Window {

    public static ArrayList<Emulator> createButton(int numberOfButtons){
        ArrayList<Emulator> buttonList = new ArrayList<>();

        for (int i = 0; i < numberOfButtons; i++) {
            buttonList.add(new Emulator());
        }

        return buttonList;
    }
}
