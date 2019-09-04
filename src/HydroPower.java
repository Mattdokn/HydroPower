/**
* Calculates power output from a hydroelectric generator
*/
import java.util.Scanner;
public class HydroPower {
    
    /** gravity constant (m/s^2) */
    public static final double GRAVITY = 9.81;
    /** PI constant */
    public static final float PI = 3.14159265359f;
    /** water density constant (kg/m^3) */
    public static final int WATER_DENSITY = 997;
    /** How many watts are in a single megawatt */
    public static final int WATTS_IN_MEGAWATT = 1000000;
    
    public static final int VEL_MIN = 1;
    public static final int VEL_MAX = 10;
    
    public static final int HEAD_MIN = 5;
    public static final int HEAD_MAX = 100;
    public static final int HEAD_INCREMENT = 5;
    
    /**
    * Entrypoint of the program
    * @param args arguments for starting the program
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the pipe diameter of your generator: ");
        double pipeDiameter = 0.0;
        
        while(pipeDiameter == 0.0) {
            String input = sc.nextLine();
            try { pipeDiameter = Double.parseDouble(input); } catch (Exception e) { pipeDiameter = 0.0; }
        }
        
        System.out.println();
        printHeader();
        
        for (int head = HEAD_MIN; head <= HEAD_MAX; head += HEAD_INCREMENT) {
            System.out.printf("\n %8d |   ", head);
            for (int vel = VEL_MIN; vel <= VEL_MAX; vel++) {
                double waterFlow = flowRate(vel, pipeDiameter);
                double powerOutput = hydroPower(waterFlow, head) / WATTS_IN_MEGAWATT;
                System.out.printf("%5.1f ", powerOutput);
            }
        }
    }
    /**
    * Prints the header to format the data table
    */
    public static void printHeader() {
        System.out.println();
        System.out.println("                   HydroElectric                               ");
        System.out.println("                     Power (MW)                                ");
        System.out.println("                                                               ");
        System.out.println("                Water Velocity (m/s)                         \n");
        System.out.print(" Head (m) |");
        
        for (int vel = VEL_MIN; vel <= VEL_MAX; vel++) {
            System.out.printf("%6d", vel);
        }
        System.out.print("\n          |  ");
        for (int vel = VEL_MIN; vel <= VEL_MAX; vel++) {
            System.out.printf(" -----");
        }
    }
    /**
    * Calculates the power from a hydroelectric generator
    * @param waterFlow how much water is going into the generators input
    * @param head distance from top water level to bottom
    * @return power generated from the arguments passed in
    */
    public static double hydroPower(double waterFlow, double head) {
        return WATER_DENSITY * waterFlow * GRAVITY * head;
    }
    /**
    * Calculates the flow rate of the water going into the generator
    * @param waterVelocity how fast the water is moving through the input
    * @param pipeDiameter how thicc the pipe is ;)
    * @return flowRate How much water is going into the generator
    */
    public static double flowRate(double waterVelocity, double pipeDiameter) {
        return ((1.0 / 4.0) * PI * (pipeDiameter * pipeDiameter) * waterVelocity);
    }
}