package com.chargepoint.services;

import com.chargepoint.config.GameOfLifeProperties;
import com.chargepoint.initializers.PatternInitializer;

public class GameOfLifeImpl implements GameOfLife {

    private final GameOfLifeProperties properties;
    private int[][] grid;

    public GameOfLifeImpl() {
        this.properties = new GameOfLifeProperties();

        PatternInitializer patternInitializer = properties.getPatternInitializer();
        this.grid = new int[properties.getSizeX()][properties.getSizeY()];
        if (properties.getStartX() == -1 || properties.getStartY() == -1) {
            patternInitializer.initialize(grid);
        } else {
            patternInitializer.initialize(grid, properties.getStartX(), properties.getStartY());
        }
    }

    @Override
    public void run() {
        for (int gen = 0; gen < this.properties.getNumberOfGenerations(); gen++) {
            System.out.println("Generation: " + gen);
            printGame();
            nextGeneration();
        }
    }

    public void nextGeneration() {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] nextGenerationGrid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countNeighbors(i, j);

                if (grid[i][j] == 1 && (neighbors < 2 || neighbors > 3)) {
                    // 1. Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
                    // 3. Any live cell with more than three live neighbors dies, as if by overcrowding.
                    nextGenerationGrid[i][j] = 0;
                } else if (grid[i][j] == 0 && (neighbors == 3)) {
                    // 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                    nextGenerationGrid[i][j] = 1;
                } else {
                    // 2. Any live cell with two or three live neighbors lives on to the next generation.
                    // All other cells remain the same
                    nextGenerationGrid[i][j] = grid[i][j];
                }
            }
        }

        this.grid = nextGenerationGrid;
    }

    private int countNeighbors(int collIndex, int rowIndex) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = collIndex - 1; i <= collIndex + 1; i++) {
            for (int j = rowIndex - 1; j <= rowIndex + 1; j++) {
                // ignore current cell
                if (i == collIndex && j == rowIndex) {
                    continue;
                }
                // make game infinite, calculate around the grid edges
                int r = (i + rows) % rows;
                int c = (j + cols) % cols;
                count += grid[r][c]; // accumulate the neighboring cell (0 or 1)
            }
        }
        return count;
    }

    public void printGame() {
        int cols = grid[0].length;
        for (int[] ints : grid) {
            for (int j = 0; j < cols; j++) {
                System.out.print(ints[j] == 1 ? "* " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
