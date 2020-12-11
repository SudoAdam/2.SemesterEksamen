/**
 * @author Adam
 * @version 1.0
 * @since 9-11-2020
 */
package com.example.demo.Service;

public class EncryptionLogic {

    public String toHash(String str) {
        return "" + str.hashCode();
    }
}
