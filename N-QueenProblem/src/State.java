import java.util.Objects;
import java.util.Set;

public class State {
    private final Board currentBoard;
    private final Set<State> childStates;
    private final int depth;

    public State(Board currentBoard, Set<State> childStates, int depth) {
        this.currentBoard = currentBoard;
        this.childStates = childStates;
        this.depth = depth;
    }

    public Set<State> getChildStates() {
        return childStates;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }
    public void addChildState(State state) {
        childStates.add(state);
    }

    @Override
    public String toString() {
        return "State{" +
                "currentBoard = \n" + currentBoard.toString() +
                '}';
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(currentBoard, state.currentBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentBoard);
    }
}
