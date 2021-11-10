/**
 * Class Column
 */
public class Column {
    private String value;

    /**
     * constructor
     * @param value board column string
     */
    public Column(String value) {
        this.value = value;
    }

    /**
     *
     * @param newValue string value to concatenate
     * @return this
     */
    public Column concat(String newValue) {
        this.value = this.value.concat(newValue);

        return this;
    }

    /**
     *
     * @return current board column string
     */
    public String getValue() {
        return this.value;
    }

    /**
     *
     * @param index index of desired character in board column string
     * @return String desired character
     */
    public String charAt(int index) {
        String result = "";

        if (index > this.value.length() - 1) {
            return result;
        } else {
            result = String.valueOf(this.value.charAt(index));
        }

        return result;
    }

    /**
     *
     * @return boolean
     */
    public boolean hasFloatingDiscs() {
        boolean result = false;

        for (int i = this.value.length() - 1; i > 0; i--) {
            char currentChar = this.value.charAt(i);
            char previousChar = this.value.charAt(i - 1);

            if (currentChar == '.' && previousChar != '.') {
//                System.out.println("debug: currentChar=`" + currentChar + "`, previousChar=`" + previousChar + "`");
                result = true;
                break;
            }
        }

        return result;
    }
}
