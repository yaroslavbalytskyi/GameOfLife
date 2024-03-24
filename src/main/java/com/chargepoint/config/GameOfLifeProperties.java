package com.chargepoint.config;

import com.chargepoint.initializers.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameOfLifeProperties {

    private static final String PROPERTIES_FILE = "app.properties";
    private int sizeX;
    private int sizeY;
    private PatternType pattern;
    private int startX;
    private int startY;
    private int squareSize;
    private int numberOfGenerations;

    public GameOfLifeProperties() {
        setProperties();
    }

    private void setProperties() {
        Properties properties = loadProperties();

        sizeX = NumberUtils.toInt(properties.getProperty("sizeX"), 25);
        sizeY = NumberUtils.toInt(properties.getProperty("sizeY"), 25);
        startX = NumberUtils.toInt(properties.getProperty("startX"), -1);
        startY = NumberUtils.toInt(properties.getProperty("startY"), -1);
        squareSize = NumberUtils.toInt(properties.getProperty("squareSize"), 3);
        numberOfGenerations = NumberUtils.toInt(properties.getProperty("numberOfGenerations"), 100);
        try {
            pattern = PatternType.valueOf(properties.getProperty("pattern"));
        } catch (Exception ignored) {
            pattern = PatternType.GLIDER;
        }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            System.out.println("Failed to load properties. Fallback to default");
        }
        return properties;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public PatternInitializer getPatternInitializer() {
        return switch (pattern) {
            case RANDOM -> new RandomInitializer();
            case SQUARE -> new SquareInitializer(squareSize);
            default -> new GliderInitializer();
        };
    }

}
