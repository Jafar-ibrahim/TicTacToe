import java.util.*;

public class Game {

    char[][] Board = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'}
    };
    private char winner;
    Map<Character, String> players = new HashMap<>(2) ;

    public void getData(){
        String player1,player2;

        Scanner s = new Scanner(System.in);
        System.out.println("Please Enter the first player's name (plays as (x) ): ");
        player1 =s.nextLine();
        System.out.println("Please Enter the second player's name (plays as (o) ): ");
        player2=s.nextLine();

        players.put('x',player1);
        players.put('o',player2);
    }

    public void play(){
        Scanner s = new Scanner(System.in);

        char player_turn = 'x';
        int turns = 0  , col , row, chosen_cell;
        while (turns<9){
            print();

                System.out.println(players.get(player_turn)+"'s turn ("+player_turn+") :  ");
                chosen_cell=s.nextInt();
                row = (chosen_cell-1)/3;
                col = (chosen_cell-1)%3;
               
                if(validMove(row,col)){
                    Board[row][col] = player_turn;
                    turns++;
                    player_turn = (player_turn == 'x')? 'o' : 'x';

                    if (CheckWinner(row,col)){
                        System.exit(0);
                    }
                }
                else{
                    System.out.println("Invalid input , please try again : ");
                }

        }
        System.out.println("Its a tie");
    }

    private boolean validMove(int r , int c) {

        return (r>=0 && r<=2 && c>=0 && c<=2 && Board[r][c] != 'x' && Board[r][c] != 'o') ;
    }

    public void print(){
        System.out.println();
        for (char[] c: Board) {
            for (char b:c) {
                System.out.printf("%5c" , b);
            }
            System.out.println();
        }
    }

    public  boolean CheckWinner( int row , int col){
        // array for 4 possible win lines (vertical/horizontal/ 2 diagonal)
        int[] count = new int[4];
        char target = Board[row][col];


        for(int i = 0 ; i< 3 ; i++){
            // to check for a horizontal line
            if (Board[row][i] == target){
                count[0]++;
            }// to check for a vertical line
            if (Board[i][col] == target) {
                count[1]++;
            }
        }
        // to check for a left-to-right diagonal line
        if( row == col){
            for(int i = 0 ; i< 3 ; i++){
                if (Board[i][i] == target){
                    count[3]++;
                }
            }
        }
        // to check for a right-to-left diagonal line
        else if ((row==0 && col==2) || (row==2 && col==0)) {
            count[3]++;
            if(Board[col][row] == target)
                count[3]++;
            if (Board[1][1] == target)
                count[3]++;
        }


        for (int t:count) {
            if(t==3){
                winner = target;
                break;
            }
        }
        if (winner != '\0'){
            print();
            System.out.println("-->Game Over !!");
            System.out.println("--> Winner is "+ winner);
            return true;
        }
        else{
            return false;

        }
    }
}
