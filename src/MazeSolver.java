/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack<MazeCell> reversePath = new Stack<MazeCell>();
        reversePath.push(maze.getEndCell());
        MazeCell currentCell = maze.getEndCell();
        while (reversePath.peek() != maze.getStartCell())
        {
            currentCell = currentCell.getParent();
            reversePath.push(currentCell);
        }
        ArrayList<MazeCell> path = new ArrayList<MazeCell>();
        for (int i = 0; i < reversePath.size(); i++)
        {
            path.add(reversePath.pop());
        }
        return path;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell oldCell = maze.getStartCell();
        Stack<MazeCell> cells = new Stack<MazeCell>();
        MazeCell newCell = maze.getStartCell();
        while(newCell != maze.getEndCell())
        {
            int row = oldCell.getRow();
            int col = oldCell.getCol();
            if (maze.isValidCell(row+1, col))
            {
                oldCell = newCell;
                newCell = maze.getCell(row+1, col);
                newCell.setParent(oldCell);
                cells.push(newCell);
            }
            else if (maze.isValidCell(row, col+1))
            {
                oldCell = newCell;
                newCell = maze.getCell(row, col+1);
                newCell.setParent(oldCell);
                cells.push(newCell);
            }
            else if (maze.isValidCell(row-1, col))
            {
                oldCell = newCell;
                newCell = maze.getCell(row-1, col);
                newCell.setParent(oldCell);
                cells.push(newCell);
            }
            else if (maze.isValidCell(row, col-1))
            {
                oldCell = newCell;
                newCell = maze.getCell(row, col-1);
                newCell.setParent(oldCell);
                cells.push(newCell);
            }
            else
            {
                cells.pop();
            }
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell cell = maze.getStartCell();
        Queue<MazeCell> cells = new LinkedList<MazeCell>();
        cells.add(cell);
        while(!cells.isEmpty())
        {
            int row = cell.getRow();
            int col = cell.getCol();
            if (maze.isValidCell(row+1, col))
            {
                cells.add(maze.getCell(row+1, col));
                maze.getCell(row+1, col).setParent(cell);
            }
            else if (maze.isValidCell(row, col+1))
            {
                cells.add(maze.getCell(row, col+1));
                maze.getCell(row, col+1).setParent(cell);
            }
            else if (maze.isValidCell(row-1, col))
            {
                cells.add(maze.getCell(row-1, col));
                maze.getCell(row-1, col).setParent(cell);
            }
            else if (maze.isValidCell(row, col-1))
            {
                cells.add(maze.getCell(row, col-1));
                maze.getCell(row, col-1).setParent(cell);
            }
            cell.setExplored(true);
            cell = cells.remove();
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
