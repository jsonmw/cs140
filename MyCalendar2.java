// Jason Wild
// 11/14/21
// CS 141
// Assignment 2: Calendar pt. 2

// Probably took around an additional 3/4 hours, but a lot of that was tweaking things to be
// more efficient

import java.util.*;

//    This program queries a benevolent TIME LORD to provide a calendar of the user's
//    choosing for the current year, either specified or current. Once a calendar is
//    displayed, the user can navigate forward or backwards one month, or choose to
//    exit the program.

public class MyCalendar {

    public static final int SIZE = 10;
    public static final int DAY_SHIFT = 6;
    public static final int WIDTH = 7 * SIZE;

    public static void main(String[] args) {

        menu();

    }

//    Displays the menu to the user and handles options based on validated user input.

    public static void menu() {
        Calendar calendar = Calendar.getInstance();
        Scanner console = new Scanner(System.in);

        int m = 0;
        int d = 0;
        int currentMonth = -1;

        boolean exec = true;
        while (exec) {

            displayOptions();
            String input = console.nextLine().toLowerCase();

            if(input.equals("e")) {
                String date = getInput();
                m = monthFromDate(date);
                d = dayFromDate(date);

                drawMonth(m, d);
                currentMonth = m;

            } else if (input.equals("t")) {
                m = calendar.get(Calendar.MONTH) +1;
                d = calendar.get(Calendar.DATE);

                drawMonth(m, d);
                currentMonth = m;

            } else if (input.equals("n")) {
                if(currentMonth == -1) {
                    System.out.println("You must have a calendar displayed.");
                } else {
                    m = currentMonth + 1;
                    if (m > 12) {
                        m -= 12;
                    }
                    drawMonth(m, -1);
                    currentMonth = m;
                }

            } else if (input.equals("p")) {
                if(currentMonth == -1) {
                    System.out.println("You must have a calendar displayed.");
                } else {
                    m = currentMonth - 1;
                    if (m == 0) {
                        m = 12;
                    }
                    drawMonth(m, -1);
                    currentMonth = m;
                }

            } else if (input.equals("q")) {
                exec = false;

            } else {
                System.out.println("Please enter a valid command.");
            }
        }
    }

//    Displays calendar to the console from user input month and day, calling the appropriate
//    methods to assemble the pieces in the correct format.

    public static void drawMonth(int month, int select) {
        System.out.println();
        drawHeader(month);

        int length = getLength(month);
        int day = 1;
        int row = 1;

        while(day <= length) {
            day = drawRow(row, day, getShift(month, DAY_SHIFT), length, select);
            row++;
        }
        drawLine();
        displayDate(month, select);
    }

//    Draws an individual 7 day week using a "row" parameter to be sure that the first week
//    the month is shifted to the appropriate starting day. Takes the date the row starts on
//    and returns the date that the row ended on. Additionally, takes the day that was
//    selected in the menu, to pass to the formatDate function to mark appropriately. Will
//    only display dates for the number of days in the month, in their correct position, per
//    the shift and length variable passed in.

    public static int drawRow(int row, int start, int shift, int length, int select) {
        drawLine();

        int finish = start;
        int numDays = 7 - shift;

        // pads first row date and sets remaining rows at full size

        if(row != 1) {
            numDays = 6;
        } else {
            for(int i = 1; i < shift; i++){
                System.out.print("|");
                for(int j = 1; j < SIZE; j++) {
                    System.out.print(" ");
                }
            }
        }

        // iterates through row, passing to formatDate and letting the function know if it's the
        // selected date needing to be marked

        boolean isSelected;
        for(int i = start; i <= start + numDays; i++) {
            isSelected = false;
            if (finish <= length) {
                if (i == select) {
                    isSelected = true;
                }

                System.out.print(formatDate(i, isSelected));
                finish++;

            } else {
                System.out.print("|");
                for(int j = 1; j < SIZE; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println("|");

        // outputs the remaining white space to complete the row

        for(int i =1; i < SIZE/2; i++) {
            for (int j = 1; j <= 7; j++) {
                String s = "|";
                for (int k = s.length(); k < SIZE; k++) {
                    s += " ";
                }
                System.out.print(s);
            }
            System.out.println("|");
        }
        return finish;
    }

//       Draws the ASCII header, drawing the vaguely freemason pyramidy drawing and message from the
//       TIME LORD at the beginning of the chosen month's calendar, centering it according to SIZE.

    public static void drawHeader(int month) {
        String s = "";
        for(int i = s.length(); i < WIDTH/2 -13; i++) {
            s += " ";
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

	// centers the month numeral

        s = "";
        for(int i = s.length(); i < WIDTH/2 -1; i++) {
            s = s + " ";
        }
        s += month;
        for(int i = s.length(); i < WIDTH/2; i++) {
            s += " ";
        }
        System.out.println(s);
        System.out.println();

        displayDayNames();
    }

//      Creates the line that tops each row of days in a week and closes the bottom of the calendar.    

    public static void drawLine() {
        for(int i = 0; i <= WIDTH; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

                    //    UTILITY METHODS \\

//      Takes user's input, ensures that it represents a valid date and format, then returns the date.

    public static String getInput () {
        Scanner userDate = new Scanner(System.in);
        boolean valid = false;
        System.out.print("Which date would you like to look at? ");
        String date = userDate.nextLine();
        int testM = -1;
        int testD = -1;

        while(!valid) {

            if(date.length() > 0) {
                testM = monthFromDate(date);
                testD = dayFromDate(date);
             }

            if ((testM >= 1 && testM <= 12) && (testD <= getLength(testM) && testD >= 1)) {
                valid = true;

            } else {
                System.out.print("Please enter a valid date. ");
                date = userDate.nextLine();
            }
        }
        return date;
    }

//      Takes the input date, isolates the month and, if it is an integer, returns the parsed value.

    public static int monthFromDate(String date) {

        if(date.contains("/") && !date.startsWith("/")) {
            int delim = date.indexOf("/");
            String month = date.substring(0, delim);

            // NOTE TO THE PROFESSOR: why a while instead of for? The while loop allows the
            // checking of two conditions, so that the loop can exit the first time an invalid
            // character is found, instead of continuing and potentially setting valid back to
            // "true" in situation such as "a5", and without need for a break statement. This
            // took a minute for me to figure out how to solve when I was testing-- and that edge
            // case kept creating exceptions in the following parseInt()!

            boolean valid = true;
            int i = 0;
            while(i < month.length() && valid) {
                valid = Character.isDigit(month.charAt(i));
                i++;
            }
            if(valid) {
                return Integer.parseInt(month);
            }
        }
        return -1;
    }

//       Takes the input date, isolates the day and, if it is an integer, returns the parsed value.

    public static int dayFromDate(String date) {

        if(date.contains("/") && !date.startsWith("/")) {
            int delim = date.indexOf("/");
            String day = date.substring(delim + 1);

            boolean valid = true;
            int i = 0;
            while(i < day.length() && valid) {
                valid = Character.isDigit(day.charAt(i));
                i++;
            }
            if(valid) {
                return Integer.parseInt(day);
            }
        }
        return -1;
    }

//       Given the month passed to it, this returns the number of days in that month.

    public static int getLength(int month) {
        int length;

        if(month == 2) {
            length = 28;
        } else if ((month <=7 && month % 2 == 0) || (month > 7 && month % 2 == 1)) {
            length = 30;
        } else {
            length = 31;
        }
        return length;
    }

//       Takes a month and initial shifted first day of the year as input and returns the total amount
//       amount of cumulative shift for the first day of the given month.

    public static int getShift(int month, int shift) {
        if(month != 1) {
            for (int i = 1; i < month; i++) {
                shift += (getLength(i));
            }
            shift %=7;
            if(shift == 0) {
                shift = 7;
            }
        }
        return shift;
    }

//       This prints the month/day given to it at the end of each calendar. If the user is navigating to
//       the next or previous month, only the month is displayed, to avoid errors such as "February 31st".

    public static void displayDate(int month, int day) {
        System.out.println("Month: " + month);
        if(day > 0) {
            System.out.println("Day:   " + day);
        }
        System.out.println();
    }

//       This displays the menu options to the console.

    public static void displayOptions() {
        System.out.println(

            "Please type a command\n" +
                    "\t\"e\" to enter a date and display the corresponding calendar\n" +
                    "\t\"t\" to get today's date and display today's calendar\n" +
                    "\t\"n\" to display the next month\n" +
                    "\t\"p\" to display the previous month\n" +
                    "\t\"q\" to quit the program"
        );
    }

//       This outputs a horizontal banner of the day names.

    public static void displayDayNames() {
        System.out.println(

                formatDayName("SUN") +
                formatDayName("MON") +
                formatDayName("TUE") +
                formatDayName("WED") +
                formatDayName("THU") +
                formatDayName("FRI") +
                formatDayName("SAT")
        );
    }

//       This takes the numeric date and handles the positioning of that number within each 
//       day's cell, marking the selected day if appropriate as per the boolean flag passed 
//       to it. It returns the formatted String.

    public static String formatDate(int date, boolean mark) {
        String s = "| " + date + "";

        if(mark) {
            s += " <";
        }

        for(int i = s.length(); i < SIZE; i++) {
            s += " ";
        }
        return s;
    }

//       Pads the day names according to SIZE constant to align with columns, adjusted for
//       even/odd SIZE variance. Returns formatted String.

    public static String formatDayName(String day) {
        String s = "";

        for(int i = 1; i < (SIZE/2); i++) {
            s += " ";
        }
        s += day;

        if( SIZE % 2 == 0 ) {
            for (int i = 1; i < ((SIZE / 2) - 1); i++) {
                s += " ";
            }
        } else {
            for (int i = 1; i < (SIZE / 2); i++) {
                s += " ";
            }
        }
        return s;
    }
}
