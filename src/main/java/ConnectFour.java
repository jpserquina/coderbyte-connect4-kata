import java.util.ArrayList;

/**
 * Class ConnectFour
 */
public class ConnectFour {
    private static final int intHeight = 7;
    private static final int intWidth = 6;

    /**
     *
     * @param input board string
     * @return ArrayList<Column> parsed columns
     */
    public static ArrayList<Column> parseBoard(String input) {
        ArrayList<String> rows;
        ArrayList<Column> columns;

        // wrap semaphore in an Object, to allow using inside anonymous function
        var ref = new Object() {
            boolean result = true;
        };

        // turn board string into columns, and do guard check
        rows = splitString(input, intHeight);
        columns = new ArrayList<>(intHeight);
        for (int i = 0; i < intHeight; i++) {
            columns.add(i, new Column(""));
        }
        rows.forEach((row) -> {
            if (row.length() < intWidth) {
                ref.result = false;
            }
        });

        // if we haven't kicked up an error yet, continue
        if (ref.result) {
            // parcel out each character of row string to respective columns
            for (String currentRow : rows) {
                for (int columnIndex = 0; columnIndex < currentRow.length(); columnIndex++) {
                    String currentChar = String.valueOf(currentRow.charAt(columnIndex));
                    columns.set(columnIndex, columns.get(columnIndex).concat(currentChar));
                }
            }

            // guard check for floating discs
            for (Column currentColumn : columns) {
                if (currentColumn.hasFloatingDiscs()) {
                    ref.result = false;
                    columns = new ArrayList<>(intHeight);
                    break;
                }
            }
        }

        return columns;
    }

    /**
     *
     * @param columns parsed columns
     * @return String
     */
    public static String displayParsedBoard(ArrayList<Column> columns) {
        String result = "";

        for (Column column : columns) {
            result = result.concat("`" + column.getValue() + "` (length = " + column.getValue().length() + "), ");
        }

        return result.substring(0, result.length() - 2);
    }

    /**
     *
     * @param columns parsed columns
     * @return boolean
     */
    public static boolean checkForWin(ArrayList<Column> columns, String disc) {
        boolean result = false;

        if (columns.size() == 0) {
            return false;
        }

        // vertical
        for (int i = 0; i < intWidth; i++) {
            if (result) {
                break;
            }
            for (int j = 0; j < intHeight; j++) {
//                System.out.println("debug: vertical: i=" + i + ", j=" + j);
                if (columns.get(i).charAt(j).equals(disc) &&
                        columns.get(i).charAt(j + 1).equals(disc) &&
                        columns.get(i).charAt(j + 2).equals(disc) &&
                        columns.get(i).charAt(j + 3).equals(disc)) {
                    result = true;
                    break;
                }
            }
        }

        // horizontal
        for (int i = 0; i < intWidth; i++) {
            if (result) {
                break;
            }
            for (int j = 0; j < intHeight; j++) {
//                System.out.println("debug: horizontal: i=" + i + ", j=" + j);
                if (columns.get(i).charAt(j).equals(disc) &&
                        columns.get(i + 1).charAt(j).equals(disc) &&
                        columns.get(i + 2).charAt(j).equals(disc) &&
                        columns.get(i + 3).charAt(j).equals(disc)) {
                    result = true;
                    break;
                }
            }
        }

        // ascending diagonal
        for (int i = 0; i < intWidth; i++) {
            if (result) {
                break;
            }
            for (int j = 0; j < intHeight; j++) {
//                System.out.println("debug: ascending diagonal: i=" + i + ", j=" + j);
                if (columns.get(i).charAt(j).equals(disc) &&
                        columns.get(i - 1).charAt(j + 1).equals(disc) &&
                        columns.get(i - 2).charAt(j + 2).equals(disc) &&
                        columns.get(i - 3).charAt(j + 3).equals(disc)) {
                    result = true;
                    break;
                }
            }
        }

        // descending diagonal
        for (int i = 0; i < intWidth; i++) {
            if (result) {
                break;
            }
            for (int j = 0; j < intHeight; j++) {
//                System.out.println("debug: descending diagonal: i=" + i + ", j=" + j);
                if (columns.get(i).charAt(j).equals(disc) &&
                        columns.get(i - 1).charAt(j - 1).equals(disc) &&
                        columns.get(i - 2).charAt(j - 2).equals(disc) &&
                        columns.get(i - 3).charAt(j - 3).equals(disc)) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     *
     * @param input board string
     * @param chunks length of substring
     * @return ArrayList<String> raw rows
     */
    public static ArrayList<String> splitString(String input, int chunks) {
        ArrayList<String> result = new ArrayList<>();
        int inputIndex = 0;
        int inputLength = input.length();

        while (inputIndex < inputLength) {
            result.add(input.substring(inputIndex, inputIndex + chunks));
            inputIndex += chunks;
        }

        return result;
    }

    /**
     * main
     * @param args args
     */
    public static void main(String[] args) {
        String board = "......." + "......." + "..YR..." + "..RYR.." + "..YRYR." + ".YYRRYR";
        String player = "Y";
        System.out.println(displayParsedBoard(parseBoard(board)));
        System.out.println(checkForWin(parseBoard(board), player));
    }
}
