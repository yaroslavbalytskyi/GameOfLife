package com.chargepoint.initializers;

public class GliderInitializer implements PatternInitializer {

    @Override
    public void initialize(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        initialize(grid, (rows / 2) - 1, (cols / 2) - 1);
    }

    @Override
    public void initialize(int[][] grid, int startX, int startY) {
        //  [0, 1, 0]
        //  [0, 0, 1]
        //  [1, 1, 1]
        grid[startX][startY + 1] = 1;
        grid[startX + 1][startY + 2] = 1;
        grid[startX + 2][startY] = 1;
        grid[startX + 2][startY + 1] = 1;
        grid[startX + 2][startY + 2] = 1;
    }
}
