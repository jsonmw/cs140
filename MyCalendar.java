// Jason Wild
// 10/24/21
// CS 141
// Assignment 1: Calendar

// Probably took around 3? 4? hours. At least one of those was getting the spacing
// right for my beautiful header to center :'( It ended up being pretty much what
// I first tried, but I must have made a typo or something in the logic and tried.

import java.util.*;

public class MyCalendar {

//    This program queries a benevolent TIME LORD to provide a pair of calendars.
//    One displays the queried date and the other the current date. It can be programmed
//    to display calendars in different sizes.

    public static final int SIZE = 10;
    public static final int WIDTH = 7 * SIZE;

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        System.out.print("Which date would you like to look at? ");
        String date = console.next();

        int m = monthFromDate(date);
        int d = dayFromDate(date);

        drawMonth(m);
        displayDate(m, d);

        Calendar calendar = Calendar.getInstance();

        int currentMonth = calendar.get(Calendar.MONTH) +1;
        int currentDay = calendar.get(Calendar.DATE);

        System.out.println("This month: ");
        drawMonth(currentMonth);
        displayDate(currentMonth, currentDay);

    }

//    Displays from user input: drawing the header image, padding
//    the month numeral at the top appropriately, and listing 5 rows
//    of sequential days with a last line to close off bottom row.

    public static void drawMonth(int month) {
        System.out.println();
        drawHeader();

        String s = "";
        for(int i = s.length(); i < WIDTH/2 -1; i++) {
            s = s + " ";
        }
        s += month;
        for(int i = s.length(); i < WIDTH/2; i++) {
            s += " ";
        }
        System.out.println(s);

        for(int i = 1; i <= 5; i++) {
            drawRow(i);
        }

        drawLine();
    }

//    Creates an individual week using a "row" paramter to be sure
//    each next week will increment + 7 from the previous, and pads 
//    the days within their rectangular cells per SIZE.

    public static void drawRow(int row) {
        int adjust = (row-1)*7;
        drawLine();

        for(int i = 1; i <= 7; i++) {
            System.out.print(paddedDay(i+adjust, SIZE));

        }
        System.out.println("|");

        for(int i =1; i < SIZE/2; i++) {
            for (int j = 1; j <= 7; j++) {
                String s = "|";
                for (int k = s.length(); k < SIZE; k++) {
                    s = s + " ";
                }
                System.out.print(s);
            }
            System.out.println("|");
        }
    }

//    Creates the line that tops each row of days in a week and closes
//    the bottom of the calendar.

    public static void drawLine() {
        for(int i = 0; i < 7* SIZE; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

//    This prints the month/day input at the end of each calendar.

    public static void displayDate(int month, int day) {
        System.out.println("Month: " + month);
        System.out.println("Day " + day);
        System.out.println();
    }

//    This isolates and returns the month out of the user input.

    public static int monthFromDate(String date) {
        int delim = date.indexOf("/");
        return Integer.parseInt(date.substring(0, delim));
    }

//    This isolates and returns the day from the user input.

    public static int dayFromDate(String date) {
        int delim = date.indexOf("/");
        return Integer.parseInt(date.substring(delim+1, delim+3));
    }

//    Creates the vaguely freemason pyramidy drawing and message from the 
//    TIME LORD at the beginning of each calendar, centering it according to SIZE.

    public static void drawHeader() {
        String s = "";
        for(int i = s.length(); i < WIDTH/2 -13; i++) {
            s = s + " ";
        }

        System.out.println(s + "      /\\****(<>)****/\\");
        for(int i = 1; i<=6; i++) {
            for(int j = 0; j < 6-i; j++) {
                System.out.print(" ");
            }
            System.out.print(s + "/");
            for(int j = 1; j <= 7; j++) {
                System.out.print("*");
            }
            for(int j = 1; j <= (2*i); j++) {
                System.out.print("=");
            }
            for(int j = 1; j <= 7; j++) {
                System.out.print("*");
            }
            System.out.println("\\");
        }
        System.out.println(s + "\\*BENEVOLENT TIMELORD SAYS*/");
        System.out.println(s + "    ~~~~~~ |   |  ~~~~~~");
        System.out.println();
    }

//    This handles the positioning of the date number within each day cell.

    public static String paddedDay(int n, int padding) {
        String s = "| " + n + "";
        for(int i = s.length(); i < padding; i++) {
            s = s + " ";
        }
        return s;
    }
}
