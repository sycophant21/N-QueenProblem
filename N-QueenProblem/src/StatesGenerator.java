import java.util.HashSet;
import java.util.Set;

public class StatesGenerator {
    private final BoardHandler boardHandler;

    public StatesGenerator(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
    }

    public State generatePossibleStates(State state) {
        Set<State> stateSet = generateChildStates(state, state.getDepth() + 1);
        for (State s : stateSet) {
            state.addChildState(s);
            generatePossibleStates(s);
        }
        deleteIncompleteStates(state);
        return state;

    }
    private Set<State> generateChildStates(State state, int depth) {
        Set<State> stateSet = new HashSet<>();
        int row = boardHandler.getEmptyRow(state.getCurrentBoard());
        int column = boardHandler.getEmptyColumn(state.getCurrentBoard());
        while (true) {
            State state1;
            if (row < boardHandler.getBoardSize(state.getCurrentBoard())) {
                Board board = boardHandler.getBoardCopy(state.getCurrentBoard());
                if (column == -1 || row == -1) {
                    break;
                }
                boardHandler.addMove(new Move(column, row), board);
                state1 = new State(boardHandler.getBoardCopy(board), new HashSet<>(), depth);
                boardHandler.removeMove(new Move(column , row), board);
                if (isStateAcceptable(state1, column, row)) {
                    stateSet.add(state1);
                }
                row++;
            }
            else {
                break;
            }
        }
        return stateSet;
    }
    private boolean isStateAcceptable(State state, int column, int row) {
        Board board = state.getCurrentBoard();
        boardHandler.removeMove(new Move(column, row), board);
        if (boardHandler.isBoardAcceptable(board, column, row)) {
            boardHandler.addMove(new Move(column, row), board);
            return true;
        }
        else {
            return false;
        }
    }

    private void deleteIncompleteStates(State state) {
        Set<State> stateSet = state.getChildStates();
        Set<State> statesToBeDeleted = new HashSet<>();
        for (State s : stateSet) {
            if (s.getDepth() < s.getCurrentBoard().getCurrentBoard().length) {
                if (s.getChildStates().size() == 0) {
                    //stateSet.remove(s);
                    statesToBeDeleted.add(s);
                }
            }
        }
        for (State s : statesToBeDeleted) {
            stateSet.remove(s);
        }
    }

}
