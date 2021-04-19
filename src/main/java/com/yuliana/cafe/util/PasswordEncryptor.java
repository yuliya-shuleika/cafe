package com.yuliana.cafe.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    private static final Logger logger = LogManager.getLogger();
    private static final String HASH_FUNCTION = "SHA-256";

    public static String encrypt(String password) {
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_FUNCTION);
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] resultBytes = messageDigest.digest(passwordBytes);
            for (byte resultByte : resultBytes) {
                result += resultByte;
            }
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.WARN, "Password wasn't encrypted.");
        }
        return result;
    }

}
