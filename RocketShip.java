public class RocketShip {

// This program will utilize nested for loops to produce rocket ships of any size. 
// It does not include launch functionality.

    public static final int SIZE = 6;

    public static void main(String[] args) {

        rocketCone();
        divider();
        bodyUp();
        bodyDown();
        divider();
        bodyDown();
        bodyUp();
        divider();
        rocketCone();
    }

// This method produces the cone shape at the top and bottom of the rocket.

    public static void rocketCone() {
        for(int i = 1; i <= (2* SIZE -1); i++ ) {
            for (int j = 1; j <= +(2 * SIZE- i); j++) {
                System.out.print(" ");
            }
            for(int j = 1; j <= i; j++) {
                System.out.print("/");
            }
            System.out.print("**");
            for(int j = 1; j <= i; j++) {
                System.out.print("\\");
            }
            System.out.println();
        }
    }

// This method produces the dividing lines of variable length that separate
// the rocket components.

    public static void divider() {
        System.out.print("+");
        for(int i = 1; i <= (2 * SIZE); i++) {
            System.out.print("=*");
        }
        System.out.print("+");
        System.out.println();
    }

// This method produces the body components where the /\ face upwards.

    public static void bodyUp() {
        for(int i = 1; i <= SIZE; i++) {
            System.out.print("|");
            for(int j = 1; j <= (SIZE-i); j++) {
                System.out.print(".");
            }
            for(int j = 1; j <= i; j++) {
                System.out.print("/\\");
            }
            for(int j = 1; j <= 2* (SIZE-i); j++) {
                System.out.print(".");
            }
            for(int j = 1; j <= i; j++) {
                System.out.print("/\\");
            }
            for(int j = 1; j <= (SIZE-i); j++) {
                System.out.print(".");
            }
            System.out.print("|");

            System.out.println();
        }
    }

// This method produces the body components where the \/ face downwards.

    public static void bodyDown() {
        for(int i = 1; i <= SIZE; i++) {
            System.out.print("|");
            for(int j = 1; j <= (i - 1); j++) {
                System.out.print(".");
            }
            for(int j = 1; j <= (SIZE+1) - i; j++) {
                System.out.print("\\/");
            }
            for(int j = 1; j <= 2* (i -1); j++) {
                System.out.print(".");
            }
            for(int j = 1; j <= (SIZE+1) - i ; j++) {
                System.out.print("\\/");
            }
            for(int j = 1; j <= (i -1); j++) {
                System.out.print(".");
            }
            System.out.print("|");

            System.out.println();
        }
    }
}