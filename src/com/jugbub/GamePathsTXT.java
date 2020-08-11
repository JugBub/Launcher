package com.jugbub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public abstract class GamePathsTXT {

    public static void writeToFile(String string) throws IOException {
        File file = new File("src\\com\\jugbub\\GamePaths.txt");
        ArrayList<String> games = getPaths();


        games.add(string);

        setPaths(games);
    }

    public static ArrayList<String> getPaths() throws FileNotFoundException {
        ArrayList<String> games = new ArrayList<>();
        Scanner currentFile = new Scanner(new File("src/com/jugbub/GamePaths.txt"));

        while (currentFile.hasNextLine()) {
            String thisLine = String.format("%s", currentFile.nextLine());

            games.add(thisLine);

        }
        return games;
    }

    public static ArrayList<File> getDirs() throws  FileNotFoundException{
        ArrayList<File> gameDirs = new ArrayList<>();
        Scanner txtFile = new Scanner(new File("src/com/jugbub/GamePaths.txt"));

        while (txtFile.hasNextLine()){
            String currentLine = txtFile.nextLine();

            gameDirs.add(new File(currentLine));
        }

        return  gameDirs;
    }

    public static ArrayList<File> getAllFiles() throws FileNotFoundException {
        ArrayList<File> allPaths = new ArrayList<>();
        ArrayList<File> dirs = getDirs();

        for (int i = 0; i < dirs.size(); i++) {
            allPaths.addAll(Arrays.asList(Objects.requireNonNull(   dirs.get(i).listFiles()    )));
        }

        return allPaths;
    }

    public static ArrayList<String> getAllPaths() throws FileNotFoundException {
        ArrayList<String> allPaths = new ArrayList<>();
        ArrayList<File> dirs = getDirs();

        for (int i = 0; i < dirs.size(); i++) {
            allPaths.addAll(Arrays.asList(Objects.requireNonNull(   dirs.get(i).list()  )));
        }

        return allPaths;
    }

    public static void setPaths(ArrayList<String> games) throws IOException {
        File gamePaths = new File("src/com/jugbub/GamePaths.txt");
        String string = "";

        System.out.println(games.get(0));

        for (int i = 0; i < games.size(); i++) {
            string += games.get(i);

            string += "\n";
        }

        //System.out.println(gamePaths.delete());
        //Files.delete(Path.of(gamePaths.getPath()));
        clear();
        Files.write(Paths.get(gamePaths.getAbsolutePath()), string.getBytes(),StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        System.out.println("///" + string + "///");
    }

    public static void clear() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/com/jugbub/GamePaths.txt");
        writer.print("");
        writer.close();
    }

}
