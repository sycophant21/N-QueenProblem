import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BoardHandler boardHandler = new BoardHandler();
        StatesGenerator statesGenerator = new StatesGenerator(boardHandler);
        int boardSize = scanner.nextInt();
        Board board = boardHandler.initialiseBoard(new Board(new int[boardSize][boardSize]));
        State state = new State(board, new HashSet<>(), 0);
        state = statesGenerator.generatePossibleStates(state);
        StateHandler stateHandler = new StateHandler(new HashSet<>());
        System.out.println(stateHandler.deleteStates(state,0));
        stateHandler.printStates();
        //System.out.println(state);


    }
}
