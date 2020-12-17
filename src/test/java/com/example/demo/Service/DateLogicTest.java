/**
 * Unit test of date context logic
 *
 * @author Patrick Vincent Højstrøm
 * @since 11-12-2020
 */
package com.example.demo.Service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateLogicTest {
    private final DateLogic dateLogic;
    private final LocalDate first;
    private final LocalDate last;

    DateLogicTest() {
        this.dateLogic = new DateLogic();
        this.first = LocalDate.of(2020,12,1);
        this.last = LocalDate.of(2020,12,15);
    }

    @Test
    void correctDate_success() {
        assertTrue(dateLogic.correctDate(first, last));
    }

    @Test
    void correctDate_failure() {
        assertFalse(dateLogic.correctDate(last, first));
    }
}