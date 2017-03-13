package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public static void main(String[] args) {
        Main window = new Main();
        window.initWindow();
    }

    private void initWindow(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(640,480));
        this.setVisible(true);

    }
}
