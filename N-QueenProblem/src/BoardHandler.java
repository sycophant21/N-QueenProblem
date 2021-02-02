public class BoardHandler {

    public Board initialiseBoard(Board board) {
        int[][] boardArray = board.getCurrentBoard();
        int size = boardArray.length;
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                boardArray[i][j] = 0;
            }
        }
        return board;
    }

    public void addMove(Move move , Board board) {
        board.getCurrentBoard()[move.getRow()][move.getColumn()] = 1;
    }
    public void removeMove(Move move , Board board) {
        board.getCurrentBoard()[move.getRow()][move.getColumn()] = 0;
    }
    private boolean ifRowContains(Board board , int row, int length) {
        int[][] currentBoard = board.getCurrentBoard();
        for (int i = 0 ; i < length ; i++) {
            if (currentBoard[row][i] == 1) {
                return true;
            }
        }
        return false;
    }
    private boolean ifColumnContains(Board board , int column, int length) {
        int[][] currentBoard = board.getCurrentBoard();
        for (int i = 0 ; i < length ; i++) {
            if (currentBoard[i][column] == 1) {
                return true;
            }
        }
        return false;
    }
    public int getEmptyRow(Board board) {
        int length = board.getCurrentBoard().length;
        for (int i = 0 ; i < length ; i++) {
            if (!ifRowContains(board, i, board.getCurrentBoard().length)) {
                return i;
            }
        }
        return -1;
    }
    public int getEmptyColumn(Board board) {
        int length = board.getCurrentBoard().length;
        for (int i = 0 ; i < length ; i++) {
            if (!ifColumnContains(board, i, board.getCurrentBoard().length)) {
                return i;
            }
        }
        return -1;
    }
    public Board getBoardCopy(Board board) {
        Board boardCopy = new Board(new int[board.getCurrentBoard().length][board.getCurrentBoard().length]);
        int[][] boardArray = board.getCurrentBoard();
        int[][] copyBoardArray = boardCopy.getCurrentBoard();
        for (int i = 0 ; i < boardArray.length ; i++) {
            System.arraycopy(boardArray[i], 0, copyBoardArray[i], 0, boardArray.length);
        }
        return boardCopy;

    }

    public int getBoardSize(Board board) {
        return board.getCurrentBoard().length;
    }

    private boolean ifDiagonalContains(Board board, int column, int row) {
        return ifDiagonal1Contains(board, column, row)  || ifDiagonal2Contains(board, column, row);
    }
    private boolean ifDiagonal1Contains(Board board, int column, int row) {
        int length = board.getCurrentBoard().length;
        int zerothRow ;
        int zerothColumn ;
        int nthRow ;
        int nthColumn ;
        if (row < column) {
            zerothRow = 0;
            zerothColumn = column - row;
            nthRow = length - zerothColumn - 1;
            nthColumn = length - 1;
        }
        else if (column < row) {
            zerothColumn = 0;
            zerothRow = row - column;
            nthRow = length - 1;
            nthColumn = length - zerothRow - 1;
        }
        else {
            zerothColumn = 0;
            zerothRow = 0;
            nthColumn = length - 1;
            nthRow = length - 1;
        }
        int[][] boardArray = board.getCurrentBoard();
        while (zerothRow != nthRow && zerothColumn != nthColumn) {
            if (boardArray[zerothRow][zerothColumn] == 1) {
                return true;
            }
            zerothColumn++;
            zerothRow++;
        }
        return false;
    }
    private boolean ifDiagonal2Contains(Board board, int column, int row) {
        int length = board.getCurrentBoard().length;
        int zerothRow ;
        int zerothColumn ;
        int nthRow ;
        int nthColumn ;
        if (row + column < length - 1) {
            zerothRow = (column + row);
            zerothColumn = 0;
            nthRow = zerothColumn;
            nthColumn = zerothRow;
        }
        else if (column + row > length - 1) {
            zerothColumn = (column + row) - (length - 1);
            zerothRow = length - 1;
            nthColumn = zerothRow;
            nthRow = zerothColumn;
        }
        else {
            zerothColumn = 0;
            zerothRow = length - 1;
            nthColumn = zerothRow;
            nthRow = zerothColumn;
        }
        int[][] boardArray = board.getCurrentBoard();
        while (zerothRow != nthRow && zerothColumn != nthColumn) {
            if (boardArray[zerothRow][zerothColumn] == 1) {
                return true;
            }
            zerothColumn++;
            zerothRow--;
        }
        return false;
    }

    public boolean isBoardAcceptable(Board board, int column, int row) {
        return (!ifColumnContains(board, column, board.getCurrentBoard().length) && !ifRowContains(board, row, board.getCurrentBoard().length) && !ifDiagonalContains(board, column, row));
    }

}
