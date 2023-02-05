import java.util.*;

public class InputReader {

    Game game;

    public InputReader(Game game) {
        this.game = game;
    }

    public void getData(){
        String player1,player2="the computer";
        Scanner s = new Scanner(System.in);

        System.out.println("Please Select the game mode (0 -> player vs computer / 1 -> player vs player) : ");
        do {
            game.mode = read_integer_input();
            if(!(game.mode==0 || game.mode==1))
                System.out.println("Invalid input ,Please enter only either 0 or 1 :");
        } while(!(game.mode==0 || game.mode==1));

        System.out.println("Please Enter the first player's name (plays as (x) ): ");
        player1 =s.nextLine();


        if(game.mode==1){
            System.out.println("Please Enter the second player's name (plays as (o) ): ");
            player2=s.nextLine();
        }

        game.players.put('x',player1);
        game.players.put('o',player2);
    }

    public int read_integer_input(){
        boolean validInput = false;
        int input=0;
        Scanner s = new Scanner(System.in);
        while (!validInput){
            try {
                input = s.nextInt();
                validInput=true;
            }catch (InputMismatchException e){
                System.out.println("Invalid Input , Please enter only an integer :");
                s.nextLine();
            }
        }
        return input;
    }

    // a method that picks a randomized number from the available cells/numbers on the board
    public int Computer_play(ArrayList<Integer> available) {
        int max = available.size();
        Random r = new Random();
        int random_index = r.nextInt(max);
        return available.get(random_index);
    }
}
