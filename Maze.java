// Generating maze using Mark Allen Weiss's DisjSets class
//      @author Kellyn Mendez
import java.lang.Math;
import java.util.Scanner;

public class Maze
{
    private static class Cell
    {
        boolean leftWall;
        boolean downWall;

        Cell()
        {
            // Every cell starts with a wall
            leftWall = true;
            downWall = true;
        }
    }
    public static void main(String[] args)
    {
        System.out.println("Maze program by Kellyn Mendez\n");
        System.out.println("Note: Maze must be 2x2 or larger");
        int numRows = 0;
        int numCols = 0;
        Scanner scan = new Scanner(System.in);
        boolean correctInput = false;
        
        while (!correctInput)
        {
            System.out.print("Enter number of rows: ");
            if (scan.hasNextInt())
            {
                numRows = scan.nextInt();
                if (numRows >= 2)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.println("Invalid input, row number cannot be less than 2. Please try again.");
                }
            }
            else
            {
                System.out.println("Invalid input, must enter an integer. Please try again.");
                scan.next();
            }
        }

        correctInput = false;
        while (!correctInput)
        {
            System.out.print("Enter number of columns: ");
            correctInput = false;
            if (scan.hasNextInt())
            {
                numCols = scan.nextInt();
                if (numCols >= 2)
                {
                    correctInput = true;
                }
                else
                {
                    System.out.println("Invalid input, column number cannot be less than 2. Please try again.");
                }
            }
            else
            {
                System.out.println("Invalid input, must enter an integer. Please try again.");
                scan.next();
            }
        }
        scan.close();
        System.out.println();
        

        int numCells = numRows * numCols;

        Cell[][] maze = new Cell[numRows][numCols];
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                maze[i][j] = new Cell();
            }
        }

        maze[0][0].leftWall = false;
        maze[numRows - 1][numCols - 1].downWall = false;


        DisjSets dsMaze = new DisjSets(numCells);

        boolean done = false;
        int numUnions = 0;
        while(!done)
        {
            /******** Randomly choosing two adjacent cells ********/

            // Generating random number between 0 and N-1
            int cellNum = (int)(Math.random() * numCells);
            int row = cellNum / numCols;    // Row of number generated
            int col = cellNum % numCols;    // Column of number generated

            int pickAdj;    // Random generated number to choose adjacent cell
            int adjRow = -1;     // Row number of adjacent cell
            int adjCol = -1;     // Column number of adjacent cell

            // If cell is a border cell
            if (row == 0 || col == 0 || row == numRows - 1 || col == numCols - 1)
            {
                if (row == 0 && col == 0)  // top left corner
                {
                    // Generate a random number, either 1 or 2
                    pickAdj = (int)(Math.random() * 2 + 1);
                    // If 1, then right cell
                    if (pickAdj == 1)
                    {
                        adjRow = row;
                        adjCol = col + 1;
                    }
                    // If 2, then bottom cell
                    else
                    {
                        adjRow = row + 1;
                        adjCol = col;
                    }
                }
                else if (row == 0 && col == numCols-1)  // top right corner
                {
                    // Generate a random number, either 1 or 2
                    pickAdj = (int)(Math.random() * 2 + 1);
                    // If 1, then bottom cell
                    if (pickAdj == 1)
                    {
                        adjRow = row + 1;
                        adjCol = col;
                    }
                    // If 2, then left cell
                    else
                    {
                        adjRow = row;
                        adjCol = col - 1;
                    }
                }
                else if (row == numRows - 1 && col == 0)  // bottom left corner
                {
                    // Generate a random number, either 1 or 2
                    pickAdj = (int)(Math.random() * 2 + 1);
                    // If 1, then top cell
                    if (pickAdj == 1)
                    {
                        adjRow = row - 1;
                        adjCol = col;
                    }
                    // If 2, then right cell
                    else
                    {
                        adjRow = row;
                        adjCol = col + 1;
                    }
                }
                else if(row == numRows - 1 && col == numCols - 1)  // bottom right corner
                {
                    // Generate a random number, either 1 or 2
                    pickAdj = (int)(Math.random() * 2 + 1);
                    // If 1, then left cell
                    if (pickAdj == 1)
                    {
                        adjRow = row;
                        adjCol = col - 1;
                    }
                    // If 2, then top cell
                    else
                    {
                        adjRow = row - 1;
                        adjCol = col;
                    }
                }
                else if (col == 0)  // left side
                {
                    // Generate a random number, either 1, 2, or 3
                    pickAdj = (int)(Math.random() * 3 + 1);
                    // If 1, then top cell
                    if (pickAdj == 1)
                    {
                        adjRow = row - 1;
                        adjCol = col;
                    }
                    // If 2, then right cell
                    else if (pickAdj == 2)
                    {
                        adjRow = row;
                        adjCol = col + 1;
                    }
                    // If 3, then bottom cell
                    else
                    {
                        adjRow = row + 1;
                        adjCol = col;
                    }
                }
                else if (col == numCols - 1)  // right side
                {
                    // Generate a random number, either 1, 2, or 3
                    pickAdj = (int)(Math.random() * 3 + 1);
                    // If 1, then bottom cell
                    if (pickAdj == 1)
                    {
                        adjRow = row + 1;
                        adjCol = col;
                    }
                    // If 2, then left cell
                    else if (pickAdj == 2)
                    {
                        adjRow = row;
                        adjCol = col - 1;
                    }
                    // If 3, then top cell
                    else
                    {
                        adjRow = row - 1;
                        adjCol = col;
                    }
                }
                else if (row == 0)  // top
                {
                    // Generate a random number, either 1, 2, or 3
                    pickAdj = (int)(Math.random() * 3 + 1);
                    // If 1, then right cell
                    if (pickAdj == 1)
                    {
                        adjRow = row;
                        adjCol = col + 1;
                    }
                    // If 2, then bottom cell
                    else if (pickAdj == 2)
                    {
                        adjRow = row + 1;
                        adjCol = col;
                    }
                    // If 3, then left cell
                    else
                    {
                        adjRow = row;
                        adjCol = col - 1;
                    }
                }
                else if (row == numRows - 1)  // bottom
                {
                    // Generate a random number, either 1, 2, or 3
                    pickAdj = (int)(Math.random() * 3 + 1);
                    // If 1, then left cell
                    if (pickAdj == 1)
                    {
                        adjRow = row;
                        adjCol = col - 1;
                    }
                    // If 2, then top cell
                    else if (pickAdj == 2)
                    {
                        adjRow = row - 1;
                        adjCol = col;
                    }
                    // If 3, then right cell
                    else
                    {
                        adjRow = row;
                        adjCol = col + 1;
                    }
                }
            }
            // Cell is an inner cell
            else
            {
                // Generate a random number, 1-4
                pickAdj = (int)(Math.random() * 4 + 1);
                // If 1, then top cell
                if (pickAdj == 1)
                {
                    adjRow = row - 1;
                    adjCol = col;
                }
                // If 2, then right cell
                else if (pickAdj == 2)
                {
                    adjRow = row;
                    adjCol = col + 1;
                }
                // If 3, then bottom cell
                else if (pickAdj == 3)
                {
                    adjRow = row + 1;
                    adjCol = col;
                }
                // If 4, then left cell
                else
                {
                    adjRow = row;
                    adjCol = col - 1;
                }
            }

            /******** Unioning adjacent cells if they are not in the same set  ********/

            // Finding the number of the adjacency from array indices
            int adjNum = (numCols)*(adjRow) + (adjCol);
            // Finding sets
            int cellSet = dsMaze.find(cellNum);
            int adjSet = dsMaze.find(adjNum);

            // If cells are not in same set, union and remove wall
            //      Notice: if cells are in same set, check another pair of adjacent cells
            if (cellSet != adjSet)
            {
                // Union the two sets
                dsMaze.union(cellSet, adjSet);
                if (row + 1 == adjRow) // If adjacent cell is the cell below
                {
                    // Removing original cell's bottom wall
                    maze[row][col].downWall = false;
                }
                else if (row - 1 == adjRow) // If adjacent cell is the cell above
                {
                    // Removing adjacent cell's bottom wall
                    maze[adjRow][adjCol].downWall = false;
                }
                else if (col + 1 == adjCol) // If adjacent cell is the cell to the right
                {
                    // Removing adjacent cell's left wall
                    maze[adjRow][adjCol].leftWall = false;
                }
                else if (col - 1 == adjCol) // If adjacent cell is the cell to the left
                {
                    // Removing original cell's left wall
                    maze[row][col].leftWall = false;
                }

                // Increment union counter
                numUnions++;
                // Once union counter = N-1, maze is done
                if (numUnions == numCells - 1)
                    done = true;
            }

        }

        /******** Printing the maze ********/

        // When printing remember to get rid of top and left of first cell, and bottom and right of last cell
        // First print line of underscores (except for the first cell)
        // Print left wall, then check the bottom and left for rest of cells until end cell, at which print right wall

        // First cell has no walls
        System.out.print("   ");
        // Printing rest of top of maze
        for (int i = 0; i < numCols - 1; i++)
        {
            System.out.print("_ ");
        }
        System.out.println();

        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                Cell myCell = maze[r][c];
                if (myCell.leftWall == true && myCell.downWall == true)
                    System.out.print("|_");
                else if (myCell.leftWall == false && myCell.downWall == true)
                    System.out.print(" _");
                else if (myCell.leftWall == true && myCell.downWall == false)
                    System.out.print("| ");
                else
                    System.out.print("  ");
            }
            if (r == numRows - 1)
                System.out.println(" ");
            else
                System.out.println("|");
        }
        System.out.println();
        System.out.println();
    }
}
