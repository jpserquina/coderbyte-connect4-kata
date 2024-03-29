import java.util.ArrayList;

public class Board {
    public static final int intHeight = 7;
    public static final int intWidth = 6;

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
        rows = Utils.splitString(input);
        columns = new ArrayList<>(Board.intHeight);
        for (int i = 0; i < Board.intHeight; i++) {
            columns.add(i, new Column(""));
        }
        rows.forEach((row) -> {
            if (row.length() < Board.intWidth) {
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
                    columns = new ArrayList<>(Board.intHeight);
                    break;
                }
            }
        }

        return columns;
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
                if (checkVerticalForWin(columns, i, j, disc)) {
                    System.out.println("debug: vertical: i=" + i + ", j=" + j);
                    result = true;
                    break;
                }

                if (checkHorizontalForWin(columns, i, j, disc)) {
                    System.out.println("debug: horizontal: i=" + i + ", j=" + j);
                    result = true;
                    break;
                }

                if (checkDiagonalForWin(columns, i, j, disc)) {
                    System.out.println("debug: diagonal: i=" + i + ", j=" + j);
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     *
     * @param columns array of columns
     * @param i column
     * @param j row
     * @param disc disc
     * @return if disc is found at column-row
     */
    private static boolean piece(ArrayList<Column> columns, int i, int j, String disc) {
        return columns.get(i).charAt(j).equals(disc);
    }

    /**
     *
     * @param columns array of columns
     * @param i column
     * @param j row
     * @param disc disc
     * @return if we win vertically
     */
    private static boolean checkVerticalForWin(ArrayList<Column> columns, int i, int j, String disc) {
        return j + 3 <= intHeight &&
            piece(columns, i, j, disc) &&
            piece(columns, i, j + 1, disc) &&
            piece(columns, i, j + 2, disc) &&
            piece(columns, i, j + 3, disc);
    }

    /**
     *
     * @param columns array of columns
     * @param i column
     * @param j row
     * @param disc disc
     * @return if we win horizontally
     */
    private static boolean checkHorizontalForWin(ArrayList<Column> columns, int i, int j, String disc) {
        return i + 3 <= intWidth &&
                piece(columns, i, j, disc) &&
                piece(columns, i + 1, j, disc) &&
                piece(columns, i + 2, j, disc) &&
                piece(columns, i + 3, j, disc);
    }

    /**
     *
     * @param columns array of columns
     * @param i column
     * @param j row
     * @param disc disc
     * @return if we win diagonally
     */
    private static boolean checkDiagonalForWin(ArrayList<Column> columns, int i, int j, String disc) {
        return i + 3 <= intWidth &&
                j + 3 <= intHeight &&
                piece(columns, i, j, disc) &&
                piece(columns, i + 1, j + 1, disc) &&
                piece(columns, i + 2, j + 2, disc) &&
                piece(columns, i + 3, j + 3, disc);
    }
}
