package com.jugbub;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class searchFiles {

    public static ArrayList<File> getChildren(File parent){
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(parent.listFiles())));
    }

}
