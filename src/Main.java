import java.lang.*;

public class Main {
    public static void main(String[] args){
        Game game = new Game();
        InputReader reader = new InputReader(game);
        BoardPrinter printer =new BoardPrinter();
        Checker checker = new Checker(game,printer);
        GameDriver driver = new GameDriver(game,printer,reader,checker);

        reader.getData();
        driver.play();

    }





}



