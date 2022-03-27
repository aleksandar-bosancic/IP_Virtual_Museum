package com.museum.user.backend.util;

import org.apache.commons.lang3.RandomStringUtils;

public class Util {
    private static final int LENGTH = 10;

    private Util(){}

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static String generateTicketNumber(){
        return RandomStringUtils.random(LENGTH, false, true);
    }
}
