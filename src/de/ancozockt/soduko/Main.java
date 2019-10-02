package de.ancozockt.soduko;


import de.ancozockt.soduko.frontend.Interface;

import java.awt.*;

public class Main {

    public static void main(String[] args){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        System.out.println(width + "x" + height);
        new Interface();
    }
}
