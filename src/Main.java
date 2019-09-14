import Model.Board;

import java.util.ArrayList;

public class Main {

    private static int level = 1;
    private static int solutions = 0;
    private static long queensPlaced = 0;
    private static long timesRolledBack = 0;
    private static ArrayList<Integer> coords = new ArrayList();



    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        coords.add(null);
        coords.add(null);
        coords.set(0,2);
        coords.set(1,3);


        //modify unit lenght of the board here
        Board board = new Board(8);

        //start placing queens to solve the queens problem
        //put(board);

        System.out.println(coords.get(0) + " some "  + coords.get(1));

        //draw board with specific piece in specific position
        //board.drawCurrentBoard(board);
        board.placeFigure(board.getField(coords), "queen");
        board.drawCurrentBoard(board);
       // board.removeLastQueen();
        coords.set(0,5);
        coords.set(1,8);
        board.placeFigure(board.getField(coords), "rook");
        board.drawCurrentBoard(board);


        System.out.println("Queens placed: " + queensPlaced);
        //System.out.println("timesRolledBack: " + timesRolledBack);
        System.out.println("Amount of solutions: " + solutions);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("run time in ms: " + elapsedTime);
    }

    private static int put(Board board)
    {


            for (int i = 1; i <= board.getSize(); i++) {
                if (board.getField(coords).getThreatCount() > 0) {
                    board.placeFigure(board.getField(coords), "queen");
                    queensPlaced++;
                    board.drawCurrentBoard(board);
                    //System.out.println("placed " + level + " queen");

                    if (!(level == board.getSize())) {
                        level++;
                        put(board);
                    }
                }
            }

            if (board.countOccupied() == board.getSize()) {
                board.drawCurrentBoard(board);
                solutions++;
                board.removeLastQueen();
            }

            board.removeLastQueen();

            level--;

            return 0;


    }
}
