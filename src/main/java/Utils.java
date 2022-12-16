import java.util.ArrayList;

public class Utils {
    /**
     *
     * @param columns parsed columns
     * @return String
     */
    public static String displayParsedBoard(ArrayList<Column> columns) {
        String result = "";

        for (Column column : columns) {
            result = result.concat("`" + column.getValue() + "` (length = " + column.getValue().length() + "), \n");
        }

        return result.substring(0, result.length() - 3);
    }

    /**
     * @param input board string
     * @return ArrayList<String> raw rows
     */
    public static ArrayList<String> splitString(String input) {
        ArrayList<String> result = new ArrayList<>();
        int inputIndex = 0;
        int inputLength = input.length();

        while (inputIndex < inputLength) {
            result.add(input.substring(inputIndex, inputIndex + Board.intHeight));
            inputIndex += Board.intHeight;
        }

        return result;
    }
}
