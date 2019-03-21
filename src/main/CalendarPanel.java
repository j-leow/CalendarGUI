package main;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author siarasaylor
 */
public class CalendarPanel extends JPanel {
    /** The currently-interesting year (not modulo 1900!) */
    protected int yy;

    /** Currently-interesting month and day */
    public int mm;
    protected int dd;

    /** The buttons to be displayed */
    protected JButton labs[][];

    /** The number of day squares to leave blank at the start of this month */
    protected int leadGap = 0;

    /** A Calendar object used throughout */
    Calendar calendar = new GregorianCalendar();

    /** Today's year */
    protected final int thisYear = calendar.get(Calendar.YEAR);

    /** Today's month */
    protected final int firstMonth = 1;

    /** One of the buttons. We just keep its reference for getBackground(). */
    private JButton b0;

    /** The month choice */
    private JComboBox monthChoice;

    /** The year choice */
    private JComboBox yearChoice;

    /**
     * Construct a Cal, starting with today.
     */
    CalendarPanel() {
        super();
        setYYMM(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        buildGUI();
        recompute(); // TODO : rename to set_day_label()
    }


    CalendarPanel(int year, int month) {
        super();
        setYYMM(year, month);
        buildGUI();
        recompute();
    }

    private void setYYMM(int year, int month) {
        yy = year;
        mm = month;
    }

    String[] months = { "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" };

    /** Build the GUI. Assumes that setYYMM has been called. */
    private void buildGUI() {
//        getAccessibleContext().setAccessibleDescription("Calendar not accessible yet. Sorry!");
        setBorder(BorderFactory.createEtchedBorder());

        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(7, 7));
        labs = new JButton[6][7]; // first row is days

        bp.add(b0 = new JButton("S"));
        bp.add(new JButton("M"));
        bp.add(new JButton("T"));
        bp.add(new JButton("W"));
        bp.add(new JButton("R"));
        bp.add(new JButton("F"));
        bp.add(new JButton("S"));


        // Construct all the buttons, and add them.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                labs[i][j] = new JButton("");
                bp.add(labs[i][j]);
            }
        }
        add(BorderLayout.SOUTH, bp);
    }

    public final static int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
            31, 30, 31, 31, /* may jun jul aug */
            30, 31, 30, 31 /* sep oct nov dec */
    };

    /** Compute which days to put where, in the Cal panel */
    protected void recompute() {

        if (mm < 0 || mm > 11)
            throw new IllegalArgumentException("Month " + mm + " bad, must be 0-11");

        calendar = new GregorianCalendar(yy,mm,dd);

        // Compute how much to leave before the first.
        // getDay() returns 0 for Sunday, which is just right.
        leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;
        // System.out.println("leadGap = " + leadGap);

        int daysInMonth = dom[mm];
        if (isLeap(calendar.get(Calendar.YEAR)) && mm == 1)
            ++daysInMonth;

        // Blank out the labels before 1st day of month
        for (int i = 0; i < leadGap; i++) {
            labs[0][i].setText("");
        }

        // Fill in numbers for the day of month.
        for (int i = 1; i <= daysInMonth; i++) {
            JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];
            b.setText(Integer.toString(i));
        }

        // 7 days/week * up to 6 rows
        for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {
            labs[(i) / 7][(i) % 7].setText("");
        }

        // Say we need to be drawn on the screen
        repaint();
    }

    /**
     * isLeap() returns true if the given year is a Leap Year.
     *
     * "a year is a leap year if it is divisible by 4 but not by 100, except
     * that years divisible by 400 *are* leap years." -- Kernighan & Ritchie,
     * _The C Programming Language_, p 37.
     */
    public boolean isLeap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        return false;
    }

    /** Set the year, month */
    public void setDate(int yy, int mm) {

        this.yy = yy;
        this.mm = mm; // starts at 0, like Date

        recompute();
    }


}
