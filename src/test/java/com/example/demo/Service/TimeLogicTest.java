/**
 * Unit test of date context logic
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 11-12-2020
 */
package com.example.demo.Service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TimeLogicTest {
    private final TimeLogic timeLogic;

    TimeLogicTest() {
        this.timeLogic = new TimeLogic();
    }

    @Test
    void correctDate() {
        LocalDate start = LocalDate.of(2020,12,1);
        LocalDate finish = LocalDate.of(2020,12,15);

        assertTrue(timeLogic.correctDate(start, finish));
        assertFalse(timeLogic.correctDate(finish, start));
    }
}