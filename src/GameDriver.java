import java.util.*;
public class GameDriver {

    Game game;
    BoardPrinter printer;
    InputReader reader;
    Checker checker;

    public GameDriver(Game game, BoardPrinter printer, InputReader reader, Checker checker) {
        this.game = game;
        this.printer = printer;
        this.reader = reader;
        this.checker = checker;
    }

    public void play(){
        ArrayList<Integer> available =new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        char player_turn = 'x';
        int turns = 0  , col , row, chosen_cell ;

        while (turns<9){
            printer.print(game.Board);
            System.out.println(game.players.get(player_turn)+"'s turn ("+player_turn+") :  ");

            if(game.mode==0 && player_turn=='o'){// to let the computer play if the mode is pvc
                chosen_cell= reader.Computer_play(available);
                System.out.println(chosen_cell);
            }
            else
                chosen_cell = reader.read_integer_input();

            // to turn the cell number into a valid index in a 2D array
            row = (chosen_cell-1)/3;
            col = (chosen_cell-1)%3;

            if(checker.validMove(row,col)){
                game.Board[row][col] = player_turn;
                turns++;
                available.remove(Integer.valueOf(chosen_cell));
                player_turn = (player_turn == 'x')? 'o' : 'x';

                if (checker.CheckWinner(row,col)){
                    System.exit(0);
                }
            }
            else{
                System.out.println("Invalid input , please enter one of the available numbers on the board : ");
            }

        }
        // if the method reaches this line , that means that there is no winner
        System.out.println("No winner , It's a tie !");
    }
}
