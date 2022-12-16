import java.util.ArrayList;

/**
 * Class ConnectFour
 */
public class ConnectFour {
    /**
     * main
     * @param args args
     */
    public static void main(String[] args) {
        String board = "......." + "......." + "..YR..." + "..RYR.." + "..YRYR." + ".YYRRYR";
        String player = "Y";
        System.out.println(Utils.displayParsedBoard(Board.parseBoard(board)));
        System.out.println(Board.checkForWin(Board.parseBoard(board), player));
    }
}
