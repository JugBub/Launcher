package com.jugbub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TxtFile {
    public static void createGameTXT() throws IOException {
        File gameTXT = new File("src/com/jugbub/game.txt");
        gameTXT.createNewFile();

    }

    public static void writeToGameTXT(File game) throws IOException {
        Scanner gameScanner = new Scanner(game);
        FileWriter writeToGame = new FileWriter(game);

        writeToGame.write(game.getPath());
        writeToGame.close();

        System.out.println(gameScanner.nextLine());
    }

    public static void deleteGameTXT(){
        File gameTXT = new File("src/com/jugbub/game.txt");
        gameTXT.delete();
    }
}
