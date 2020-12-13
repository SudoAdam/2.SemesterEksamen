/**
 * @author Adam Madsen
 * @version 1.0
 * @since 9-12-2020
 */
package com.example.demo.Service;

import java.time.LocalDate;

public class TimeLogic {

    public boolean correctDate(LocalDate kickoff, LocalDate deadline) {
        boolean result = false;
        if (kickoff.isBefore(deadline)) {
            result = true;
        }
        return result;
    }

}
