import java.util.HashSet;
import java.util.Set;

public class StateHandler {
    private final Set<State> stateSet;

    public StateHandler(Set<State> stateSet) {
        this.stateSet = stateSet;
    }

    public void printStates() {
        for (State state : stateSet) {
            System.out.println(state);
        }
    }


    public int deleteStates(State state, int numberOfStates) {
        if (state.getDepth() == state.getCurrentBoard().getCurrentBoard().length) {
            /*stateSet.add(state);
            findMirrorStates();*/
            System.out.println(state);
            numberOfStates++;
        }
        else {
            Set<State> stateSet = state.getChildStates();
            for (State s : stateSet) {
                numberOfStates = deleteStates(s,numberOfStates);
            }
        }
        return numberOfStates;
    }
    private void findMirrorStates() {
        Set<State> states = new HashSet<>();
        boolean isStateMirror = true;
        boolean isStateMirror1 = true;
        for (State s : stateSet) {
            int[][] sBoard = s.getCurrentBoard().getCurrentBoard();
            for (State state : stateSet) {
                if (!s.equals(state)) {
                    int[][] stateBoard = state.getCurrentBoard().getCurrentBoard();
                    int length = sBoard.length;
                    for (int i = 0 ; i < length; i++) {
                        for (int j = 0 ; j < length ; j++) {
                            if (sBoard[i][j] != stateBoard[i][length - 1 - j]) {
                                isStateMirror = false;
                                break;
                            }
                            if (sBoard[i][j] != stateBoard[length - 1 - i][j]) {
                                isStateMirror1 = false;
                                break;
                            }
                        }
                        if (!isStateMirror || !isStateMirror1) {
                            break;
                        }
                    }
                    if (isStateMirror || isStateMirror1) {
                        states.add(state);
                    }
                }
            }
        }
        deleteStates(states);
    }
    private void deleteStates(Set<State> stateSet1) {
        for (State s : stateSet1) {
            stateSet.remove(s);
        }
    }

}
