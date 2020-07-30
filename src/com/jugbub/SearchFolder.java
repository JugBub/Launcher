package com.jugbub;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchFolder {

    public static File findExecutable(File file){

        ArrayList<File> folder = new ArrayList<>(Arrays.asList(file.listFiles()));

        int size =folder.size();

        for (int i = 0; i < size ; i++) {
            if(!(folder.get(size -1 -i).toString().endsWith(".exe"))) {
                folder.remove(size - 1 - i);
                System.out.println(size -1 -i);
            }
            System.out.println(i + "i");
        }

/*        folder.forEach((n) -> removeWithout(folder,n,".exe"));*/

        folder.forEach(System.out::println);

        System.out.println(folder.get(0).length());

        int[] fileAndSize = new int[2];

        for (int i = 0; i < folder.size(); i++) {
            if (folder.get(i).length() > fileAndSize[1]){
                fileAndSize[0] = i;
                fileAndSize[1] =(int) folder.get(i).length();
            }
            System.out.println(folder.get(i) + "  " + folder.get(i).length());
        }


        return folder.get(fileAndSize[0]);
    }

    private static void removeWithout(ArrayList<File> folder, File subject, String suffix){
        if(!(subject.toString().endsWith(suffix)))
            folder.remove(subject);
        System.out.println("go");
    }

}
