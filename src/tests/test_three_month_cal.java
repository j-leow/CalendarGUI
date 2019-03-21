package tests;

import main.MultiMonthPanel;
import main.ThreeMonthPanel;
import main.TwelveMonthPanel;

public class test_three_month_cal {

    public static void main(String[] args){
        MultiMonthPanel panel = new MultiMonthPanel(3, 2010, 11);
        MultiMonthPanel p2 = new ThreeMonthPanel(2019, 0);
        MultiMonthPanel p3 = new TwelveMonthPanel(2015, 0);

        System.out.println(panel.month_calendars[0].mm);
    }
}
