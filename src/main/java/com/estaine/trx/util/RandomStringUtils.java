package com.estaine.trx.util;

import java.util.Random;

/**
 * Created by estaine on 30.11.2018.
 */
public class RandomStringUtils {

    private final static String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final static Random RNG = new Random();


    public static String generate(int length) {
        final char[] chars = new char[length];
        final int charsetSize = ALLOWED_CHARS.length();

        for(int i = 0; i < length; i++) {
            chars[i] = ALLOWED_CHARS.charAt(RNG.nextInt(charsetSize));
        }

        return new String(chars);
    }
}
