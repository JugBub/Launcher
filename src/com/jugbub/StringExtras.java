package com.jugbub;

public class StringExtras {

    public static int countChars(String string, char _char){
        int amount = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == _char)
                amount++;
        }
        return amount;
    }

}
