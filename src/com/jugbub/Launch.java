package com.jugbub;

import javax.swing.*;
import java.io.File;

public class Launch {
    JButton button = new JButton();
    File application;
    String extension;
    Icon icon;

    public static void Launch(){

    }

    public static void setApp(Launch launch, File app){
        launch.application = new File(app.getAbsolutePath());
    }

}
