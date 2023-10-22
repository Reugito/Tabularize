package com.tabularize.app.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;


@Service
public class PasswordService {
    private static final String ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16; // You can adjust the salt length as needed

    public String encryptPassword(String plainPassword) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(plainPassword, salt);
        return bytesToHex(salt) + ":" + bytesToHex(hashedPassword);
    }

    public boolean verifyPassword(String plainPassword, String storedPassword) {
        String[] parts = storedPassword.split(":");
        if (parts.length != 2) {
            return false; // Invalid stored password format
        }
        byte[] salt = hexToBytes(parts[0]);
        byte[] hashedPassword = hexToBytes(parts[1]);
        byte[] inputHash = hashPassword(plainPassword, salt);

        return MessageDigest.isEqual(hashedPassword, inputHash);
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    private byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not available", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    private byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return bytes;
    }
}
