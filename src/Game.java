import java.util.*;

public class Game {

    ArrayList<ArrayList<Character>> Board2 = new ArrayList<>();


    char[][] Board = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'}
    };
    private char winner;

    public Game(){
        Board2.add( new ArrayList<>(Arrays.asList('1','2','3')));
        Board2.add( new ArrayList<>(Arrays.asList('4','5','6')));
        Board2.add( new ArrayList<>(Arrays.asList('7','8','9')));
    }

    public void getData(){
        Scanner s = new Scanner(System.in);
        int turns = 0 , turn=0 , col , row,a;
        while (turns<9){
            print();
            if(turn == 0){
                System.out.println("Player1's turn (x) :  ");
                a=s.nextInt();
                row = (a-1)/3;
                col = (a-1)%3;
               
                if(validMove(row,col)){
                    Board[row][col] = 'x';
                    turns++;
                    turn = 1;
                    if (CheckWinner(row,col)){
                        System.exit(0);
                    }
                }
                else{
                    System.out.println("Invalid input , please try again : ");
                    getData();
                }
            }
            else {
                System.out.println("Player2's turn (o) :  ");
                a=s.nextInt();
                row = (a-1)/3;
                col = (a-1)%3;

                if(validMove(row,col)){
                    Board[row][col] = 'o';
                    turns++;
                    turn = 0;
                    if (CheckWinner(row,col)){
                        System.exit(0);
                    }
                }
                else{
                    System.out.println("Invalid input , please try again : ");
                    getData();
                }
            }
        }
        System.out.println("Its a tie");
    }

    private boolean validMove(int r , int c) {

        return (Board[r][c] != 'x' && Board[r][c] != 'o') ;
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