package info.sleeplessacorn.foody.lib.util.helper;

import com.google.common.collect.Maps;

import javax.annotation.Nonnull;
import java.util.TreeMap;

public class NumeralHelper {

    private static final TreeMap<Integer, String> ROMAN_NUMERALS = Maps.newTreeMap();

    static {
        ROMAN_NUMERALS.put(1000, "M");
        ROMAN_NUMERALS.put(900, "CM");
        ROMAN_NUMERALS.put(500, "D");
        ROMAN_NUMERALS.put(400, "CD");
        ROMAN_NUMERALS.put(100, "C");
        ROMAN_NUMERALS.put(90, "XC");
        ROMAN_NUMERALS.put(50, "L");
        ROMAN_NUMERALS.put(40, "XL");
        ROMAN_NUMERALS.put(10, "X");
        ROMAN_NUMERALS.put(9, "IX");
        ROMAN_NUMERALS.put(5, "V");
        ROMAN_NUMERALS.put(4, "IV");
        ROMAN_NUMERALS.put(1, "I");
    }

    @Nonnull
    public static String toRoman(int arabic) {
        int convert = ROMAN_NUMERALS.floorKey(arabic);
        if (arabic == convert)
            return ROMAN_NUMERALS.get(convert);

        return ROMAN_NUMERALS.get(convert) + toRoman(arabic - convert);
    }
}
