package com.jugbub;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class EmulatorPathsTXT extends EmulatorPaths{
    public static ArrayList<String[]> getEmulatorPathsTXT() throws FileNotFoundException {
        ArrayList<String[]> emulators = new ArrayList<>();
        Scanner currentFile = new Scanner(new File("src/com/jugbub/EmulatorPaths.txt"));

        int i = 0;
        while (currentFile.hasNextLine()) {
            String thisLine = String.format("%s", currentFile.nextLine());

            emulators.add(new String[thisLine.split(";").length]);

            System.arraycopy(thisLine.split(";"), 0, emulators.get(i), 0, thisLine.split(";").length);

            i++;
        }
        return emulators;
    }

    public static void setEmulatorPathsTXT(ArrayList<String[]> emulators) throws IOException {
        File emulatorPaths = new File("src/com/jugbub/EmulatorPaths.txt");
        String string = "";

        for (int i = 0; i < emulators.size(); i++) {
            for (int j = 0; j < emulators.get(i).length; j++) {
                if(!(j == emulators.get(i).length-1))
                    string += emulators.get(i)[j] + ";";
                else
                    string += emulators.get(i)[j];
            }
            string += "\n";
        }
        System.out.println("(((" + string + ")))");
        //System.out.println(emulatorPaths.delete());
        //Files.delete(Path.of(emulatorPaths.getPath()));
        clear();
        Files.write(Paths.get(emulatorPaths.getAbsolutePath()), string.getBytes(),StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }

    public static void clear() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/com/jugbub/EmulatorPaths.txt");
        writer.print("");
        writer.close();
    }
}
