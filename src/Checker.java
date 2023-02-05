public class Checker {

    Game game;
    BoardPrinter printer;

    public Checker(Game game, BoardPrinter printer) {
        this.game = game;
        this.printer = printer;
    }

    // a method to check the validity of the move ( whether its in the
    // range of 0-2 and if the cell selected is empty)
    public boolean validMove(int r , int c) {
        return (r>=0 && r<=2 && c>=0 && c<=2 && game.Board[r][c] != 'x' && game.Board[r][c] != 'o') ;
    }

    public boolean CheckWinner( int row , int col){

        // array to check 4 possible win lines (vertical/horizontal/ 2 diagonal)
        int[] hits_count = new int[4];
        char target = game.Board[row][col];


        for(int i = 0 ; i< 3 ; i++){
            // to check for a horizontal line
            if (game.Board[row][i] == target){
                hits_count[0]++;
            }// to check for a vertical line
            if (game.Board[i][col] == target) {
                hits_count[1]++;
            }
        }
        // to check for a left-to-right diagonal line
        if( row == col){
            for(int i = 0 ; i< 3 ; i++){
                if (game.Board[i][i] == target){
                    hits_count[3]++;
                }
            }
        }
        // to check for a right-to-left diagonal line
        else if ((row==0 && col==2) || (row==2 && col==0)) {
            hits_count[3]++;
            if(game.Board[col][row] == target)
                hits_count[3]++;
            if (game.Board[1][1] == target)
                hits_count[3]++;
        }

        // to check if any of the 4 lines has been completed with 3 identical symbols ( a winner has been decided)
        for (int t:hits_count) {
            if(t==3){
                game.winner = target;
                break;
            }
        }
        if (game.winner != '\0'){
            printer.print(game.Board);
            System.out.println("-->Game Over !!");
            System.out.println("--> The Winner is "+ game.players.get(game.winner));
            return true;
        }
        else{
            return false;

        }
    }
}
