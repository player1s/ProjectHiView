import Model.Board;

public class Main {

    private static int level = 1;
    private static int solutions = 0;
    private static long queensPlaced = 0;
    private static long timesRolledBack = 0;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Board board = new Board(8);
        put(board);

        /*
        board.drawCurrentBoard(board);
        board.placeQueen(board.getField(2,3));
        board.drawCurrentBoard(board);
        board.removeLastQueen();
        board.drawCurrentBoard(board);
        board.placeQueen(board.getField(3,3));
        board.drawCurrentBoard(board);
*/
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
                if (!board.getField(level, i).isDestroyed()) {
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
