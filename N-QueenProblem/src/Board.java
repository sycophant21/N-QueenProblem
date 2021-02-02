import java.util.Arrays;

public class Board {
    private final int[][] currentBoard;

    public Board(int[][] currentBoard) {
        this.currentBoard = currentBoard;
    }

    public int[][] getCurrentBoard() {
        return currentBoard;
    }

    @Override
    public String toString() {
        String outputString = "";
        for (int[] ints : currentBoard) {
            for (int i : ints) {
                if (i == 1) {
                    outputString = outputString.concat("Q\t");
                }
                else {
                    outputString = outputString.concat("_\t");
                }
            }
            outputString = outputString.concat("\n");
        }
        return outputString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.equals(currentBoard, board.currentBoard);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(currentBoard);
    }
}
