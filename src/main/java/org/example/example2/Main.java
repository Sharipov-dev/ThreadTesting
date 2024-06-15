package org.example.example2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String SOURCE_FILE = "./src/main/resources/many-flowers.jpg";
    public static final String DESTINATION_FILE = "./src/main/out/many-flowers.jpg";

    public static void main(String[] args) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        typeThreads type = typeThreads.SINGLETHREADED;
        switch (type){
            case SINGLETHREADED:
                long startTime = System.currentTimeMillis();

                recolorSingleThreaded(originalImage, resultImage);

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;


                System.out.println("Time needed to recolor image for single threaded: " + String.valueOf(duration));
                break;
            case MULTITHREADED:
                long startTimeM = System.currentTimeMillis();

                int numberOfThreads = 4;
                recolorSingleThreaded(originalImage, resultImage);

                long endTimeM = System.currentTimeMillis();
                long durationM = endTimeM - startTimeM;
                System.out.println("Time needed to recolor image for multithreaded: " + String.valueOf(durationM));
        }




        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);

    }
    public static void recolorMultithreaded(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads){
        List<Thread> threads = new ArrayList<>();
        int width  = originalImage.getWidth();
        int height = originalImage.getHeight()/numberOfThreads;

        for (int i = 0; i<numberOfThreads; i++){
            final int threadMultiplier = i;

            Thread thread = new Thread ( () -> {
              int leftCorner = 0;
              int topCorner = threadMultiplier*height;

              recolorImage(originalImage, resultImage, leftCorner,topCorner, width, height);
                    }
            );
            threads.add(thread);
        }

        for(Thread thread : threads){
            thread.start();
        }
        for(Thread thread : threads){

            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public static void recolorSingleThreaded(BufferedImage originalImage, BufferedImage outputImage){
        recolorImage(originalImage, outputImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
    }
    public static void recolorImage(BufferedImage originalImage, BufferedImage outputImage, int leftCorner, int topCorner,
                                    int width, int height){
        for(int x = leftCorner; x<leftCorner + width && x < originalImage.getWidth(); x++){
            for(int y = topCorner; y < topCorner + height && y<originalImage.getHeight();y++){
                recolorPixel(originalImage, outputImage, x,y);
            }
        }
    }

    public static void recolorPixel(BufferedImage originalImage, BufferedImage resultImage, int x, int y){
        int rgb = originalImage.getRGB(x,y);
        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue  = getBlue(rgb);

        int newRed;
        int newGreen;
        int newBlue;

        if(isShadeOfGray(red, green, blue)){
            newRed =  Math.min(255,red + 10);
            newGreen = Math.max(0, green-80);
            newBlue = Math.max(0, blue - 20);

        } else{
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }
        int newRGB = createRGBFromColors(newRed, newGreen, newBlue);
        setRGB(resultImage, x,y,newRGB);
    }
    public static void setRGB(BufferedImage image, int x, int y, int rgb){
        image.getRaster().setDataElements(x,y,image.getColorModel().getDataElements(rgb, null));
    }
    public static boolean isShadeOfGray(int red, int green, int blue){
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
    }

    public static int createRGBFromColors(int red, int green, int blue){
        int rgb = 0;

        rgb |= blue;
        rgb |= green << 8;
        rgb |= red << 16;

        rgb |= 0xFF000000;

        return rgb;
    }
    public static int getRed(int rgb){

        return (rgb & 0x00FF0000) >> 16;

    }
    public static int getGreen(int rgb){
        return (rgb & 0x0000FF00) >> 8;
    }
    public static int getBlue(int rgb){
        return rgb & 0x000000FF;
    }
    public enum typeThreads{
        SINGLETHREADED, MULTITHREADED
    }
}