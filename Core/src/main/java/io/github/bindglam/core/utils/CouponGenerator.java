package io.github.bindglam.core.utils;

import java.util.Random;

public class CouponGenerator {
    private static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateCouponCode(int length) {
        StringBuilder couponCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            couponCode.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return couponCode.toString();
    }
}
