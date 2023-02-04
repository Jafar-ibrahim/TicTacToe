public class BoardPrinter {

    public void print(char[][] Board){
        System.out.println();
        for (char[] c: Board) {
            for (char b:c) {
                System.out.printf("%5c" , b);
            }
            System.out.println();
        }
    }
}
