package ru.arhiser.visual.raster;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RasterWindow extends JFrame {

    BufferedImage image;
    Raster raster;

    public static void main(String[] arg) {
        int width = 800;
        int height = 600;

        Raster raster = new Raster(width, height);

        for (int i = 0; i < raster.getSize(); i++) {
            raster.pixels[i] = 0xff000000 | i;
        }

        RasterWindow rasterWindow = new RasterWindow(raster);
    }

    public RasterWindow(Raster raster) {
        super("Raster");
        this.raster = raster;

        image = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_INT_ARGB);

        setSize(raster.getWidth(), raster.getHeight());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel picLabel = new JLabel(new ImageIcon(image));

        BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        getContentPane().add(picLabel, BorderLayout.CENTER);

        setVisible(true);

        updateRaster();
    }

    public void updateRaster() {
        int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < raster.width * raster.height; i++) {
            pixels[i] = raster.pixels[i];
        }
        repaint();
    }
}
