package com.mwu.utils;

import java.util.Random;

public class AccountUtil {

    public static String generateRandomStringAccount() {
        Random random = new Random();
        Long number = 10000 + random.nextLong(90000L);
        return "27" + number;
    }
}
