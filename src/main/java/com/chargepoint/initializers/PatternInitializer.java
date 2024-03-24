package com.chargepoint.initializers;

public interface PatternInitializer {

    void initialize(int[][] grid);

    void initialize(int[][] grid, int startX, int startY);
}
