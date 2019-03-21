package main;

import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jewuelsls
 */
public class MultiMonthPanel extends JPanel{
    private final int months_in_panel; // We can set this to 1, 3, or 12, or any number of months
    private final int start_year;
    private final int start_month;

    /** The buttons to be displayed */
    public CalendarPanel[] month_calendars;

    public MultiMonthPanel(int num_months, int start_year, int start_month) {
        this.months_in_panel = num_months;
        this.start_year = start_year;
        this.start_month = start_month;

        this.month_calendars = new CalendarPanel[months_in_panel];

        for (int i = start_month; i < start_month + months_in_panel; ++i) {
            int month_cal_idx = (i - start_month) % 12;

            int month = i % 12;
            int year = start_year + (i / 12);

            this.month_calendars[month_cal_idx] = new CalendarPanel(year, month);
        }
        int a = 0;
    }
}
