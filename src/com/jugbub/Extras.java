package com.jugbub;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Extras {

    public static void runViaConsole() throws IOException {
        Scanner scanner = new Scanner(System.in);
        File test = new File("emulators");
        Desktop desktop = Desktop.getDesktop();

        int choice = scanner.nextInt();

        desktop.open(Objects.requireNonNull(test.listFiles())[choice]);
    }

}
