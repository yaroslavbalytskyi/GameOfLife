package com.chargepoint.initializers;

public class RandomInitializer implements PatternInitializer {

    @Override
    public void initialize(int[][] grid) {
        initialize(grid, 0, 0);
    }

    @Override
    public void initialize(int[][] grid, int startX, int startY) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = startX; i < rows; i++) {
            for (int j = startY; j < cols; j++) {
                grid[i][j] = Math.random() > 0.5 ? 1 : 0;
            }
        }
    }
}
