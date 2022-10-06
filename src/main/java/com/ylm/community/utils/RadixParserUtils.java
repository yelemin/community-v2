package com.ylm.community.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author flyingwhale
 */

public final class RadixParserUtils {

    private static final int MAX_RADIX = 62;

    private RadixParserUtils() {
    }

    private static final char[] CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static final Map<Character, Integer> NUMBER_MAP_PER_CHAR;

    static {
        Map<Character, Integer> numberMapPerChar = new HashMap<>();
        for (int i = 0; i < CHARS.length; i++) {
            numberMapPerChar.put(CHARS[i] , i);
        }
        NUMBER_MAP_PER_CHAR = Map.copyOf(numberMapPerChar);
    }

    public static String encode(long b10, int radix) {
        if (radix <= 1 || radix > MAX_RADIX) {
            throw new IllegalArgumentException("radix not in scope.");
        }
        if (radix <= 36) {
            return Long.toString(b10, radix);
        }
        if (b10 < 0) {
            throw new IllegalArgumentException("b10 must be nonnegative");
        }
        StringBuilder ret = new StringBuilder();
        while (b10 > 0) {
            ret.insert(0, CHARS[(int) (b10 % radix)]);
            b10 /= radix;
        }
        return ret.toString();
    }

    public static long decode(String b, int radix) {
        if (radix <= 1 || radix > MAX_RADIX) {
            throw new IllegalArgumentException("radix not in scope.");
        }
        if (radix <= 36) {
            return Long.parseLong(b,radix);
        }
        long ret = 0;
        b = new StringBuffer(b).reverse().toString();
        long count = 1;
        for (char character : b.toCharArray()) {
            ret += NUMBER_MAP_PER_CHAR.get(character) * count;
            count *= radix;
        }
        return ret;
    }

}
