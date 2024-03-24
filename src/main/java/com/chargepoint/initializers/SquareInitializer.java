package com.chargepoint.initializers;

public class SquareInitializer implements PatternInitializer {

    private final int size;

    public SquareInitializer() {
        size = 3;
    }

    public SquareInitializer(int size) {
        this.size = size;
    }

    @Override
    public void initialize(int[][] grid) {
        initialize(grid, 0, 0);
    }

    @Override
    public void initialize(int[][] grid, int startX, int startY) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[startX + i][startY + j] = 1;
            }
        }
    }

}
