import java.util.*;

public class Game {

    char[][] Board = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'}
    };
    private char winner;
    int mode;
    Map<Character, String> players = new HashMap<>(2) ;

    public void getData(){
        String player1,player2="the computer";
        Scanner s = new Scanner(System.in);

        System.out.println("Please Select the game mode (0 -> player vs computer / 1 -> player vs player) : ");
        mode = read_integer_input();
        System.out.println("Please Enter the first player's name (plays as (x) ): ");
        player1 =s.nextLine();


        if(mode==1){
            s.nextLine();
            System.out.println("Please Enter the second player's name (plays as (o) ): ");
            player2=s.nextLine();
        }

        players.put('x',player1);
        players.put('o',player2);
    }

    public void play(){
        ArrayList<Integer> available =new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        char player_turn = 'x';
        int turns = 0  , col , row, chosen_cell ;

        while (turns<9){
                print();
                System.out.println(players.get(player_turn)+"'s turn ("+player_turn+") :  ");

                if(mode==0 && player_turn=='o'){// to let the computer play if the mode is pvc
                    chosen_cell= Computer_play(available);
                    System.out.println(chosen_cell);
                }
                else
                    chosen_cell = read_integer_input();

                // to turn the cell number into a valid index in a 2D array
                row = (chosen_cell-1)/3;
                col = (chosen_cell-1)%3;
               
                if(validMove(row,col)){
                    Board[row][col] = player_turn;
                    turns++;
                    available.remove(Integer.valueOf(chosen_cell));
                    player_turn = (player_turn == 'x')? 'o' : 'x';

                    if (CheckWinner(row,col)){
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


    private int read_integer_input(){
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
    private int Computer_play(ArrayList<Integer> available) {
        int max = available.size();
        Random r = new Random();
        int random_index = r.nextInt(max);
        return available.get(random_index);
    }

    // a method to check the validity of the move ( whether its in the range of 0-2 and if the cell selected is empty
    private boolean validMove(int r , int c) {

        return (r>=0 && r<=2 && c>=0 && c<=2 && Board[r][c] != 'x' && Board[r][c] != 'o') ;
    }

    // a method to print the board
    private void print(){
        System.out.println();
        for (char[] c: Board) {
            for (char b:c) {
                System.out.printf("%5c" , b);
            }
            System.out.println();
        }
    }

    private   boolean CheckWinner( int row , int col){
        // array to check 4 possible win lines (vertical/horizontal/ 2 diagonal)
        int[] hits_count = new int[4];
        char target = Board[row][col];


        for(int i = 0 ; i< 3 ; i++){
            // to check for a horizontal line
            if (Board[row][i] == target){
                hits_count[0]++;
            }// to check for a vertical line
            if (Board[i][col] == target) {
                hits_count[1]++;
            }
        }
        // to check for a left-to-right diagonal line
        if( row == col){
            for(int i = 0 ; i< 3 ; i++){
                if (Board[i][i] == target){
                    hits_count[3]++;
                }
            }
        }
        // to check for a right-to-left diagonal line
        else if ((row==0 && col==2) || (row==2 && col==0)) {
            hits_count[3]++;
            if(Board[col][row] == target)
                hits_count[3]++;
            if (Board[1][1] == target)
                hits_count[3]++;
        }

        // to check if any of the 4 lines has been completed with 3 identical symbols ( a winner has been decided)
        for (int t:hits_count) {
            if(t==3){
                winner = target;
                break;
            }
        }
        if (winner != '\0'){
            print();
            System.out.println("-->Game Over !!");
            System.out.println("--> The Winner is "+ players.get(winner));
            return true;
        }
        else{
            return false;

        }
    }
}
