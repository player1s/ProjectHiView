import Model.Board;

public class Main {

    private static int level = 1;
    private static int solutions = 0;
    private static long queensPlaced = 0;
    private static long timesRolledBack = 0;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        //modify unit lenght of the board here
        Board board = new Board(8);

        //start placing queens to solve the queens problem
        //put(board);



        //draw board with specific piece in specific position
        board.drawCurrentBoard(board);
        board.placeFigure(board.getField(2,3), "queen");
        board.drawCurrentBoard(board);
       // board.removeLastQueen();
        board.drawCurrentBoard(board);
        board.placeFigure(board.getField(5,8), "rook");
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
                if (!board.getField(level, i).isThreatened()) {
                    board.placeQueen(board.getField(level, i));
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
