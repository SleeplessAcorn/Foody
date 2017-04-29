package info.sleeplessacorn.foody.lib.util.helper;

import javax.annotation.Nonnull;

public class StringHelper {

    public static boolean isInteger(String str) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Provides an "indent" string with a given size.
     *
     * @param size - Size of the indent
     * @return the indent string
     */
    @Nonnull
    public static String indent(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
            sb.append(" ");
        return sb.toString();
    }

    @Nonnull
    public static String toPretty(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean toUpper = true;
        for (char c : string.toCharArray()) {
            if (toUpper) {
                stringBuilder.append(Character.toUpperCase(c));
                toUpper = false;
            } else if (Character.isDigit(c)) {
                stringBuilder.append(' ').append(c);
            } else if ((c == '-' || c == '_') || Character.isWhitespace(c)) {
                stringBuilder.append(' ');
                toUpper = true;
            } else {
                stringBuilder.append(Character.toLowerCase(c));
            }

        }

        return stringBuilder.toString();
    }
}
