/**
 * Unit test of encryption logic
 *
 * @author Patrick Vincent Højstrøm
 * @since 11-12-2020
 */
package com.example.demo.Service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionLogicTest {
    private final EncryptionLogic encryptionLogic;
    private final String password;

    EncryptionLogicTest() {
        this.encryptionLogic = new EncryptionLogic();
        this.password = "mock_password_88_!#¤%&/()=?_@£$€{[]}|";
    }

    @Test
    void toHash() {
        String hash = encryptionLogic.toHash(password);
        assertNotEquals(password, hash);
        // System.out.println("Non-encrypted  :     " + password);
        // System.out.println("Hash-encrypted :     " + hash);
    }
}