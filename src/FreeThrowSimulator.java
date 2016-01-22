import java.text.NumberFormat;
import java.util.ArrayList;

public class FreeThrowSimulator {
    private static double made;
    private static double missed;
    private static double totalShots;
    private static double makePercentage;
    private static double sumMakePercentageArray;
    private static boolean isShotMade;
    private static ArrayList<Double> makePercentageArray = new ArrayList<>();

    private static NumberFormat formatter = NumberFormat.getPercentInstance();

    private static int numberOfSimulations = 100;

    public static void main(String[] args) {
        //I believe from the coaches perspective, the answer is 2/3 or 67% that the neurotic shooter makes the 100th shot, but I wanted to make a simulator...just because!!!

        //simulate the shootFreeThrow simulator for desired amount of times
        simulateNeuroticFreeThrowShooterRound(numberOfSimulations);

        // loop through make percentage array in order to get aggregate make percentage and solve for 100th shot probability
        aggregateSimulations();

        // Divide aggregate make percentage by the number of simulations to get expected probability of making the 100th shot
        outputResults();
    }

    private static void aggregateSimulations() {
        System.out.println("sumMakePercentageArray size: " + makePercentageArray.size());
        System.out.println("Number of Simluations: " + numberOfSimulations);

        for (int i = 0; i < makePercentageArray.size(); i++) {
            System.out.println("\nMakePercentage at index " + i + " is " + formatter.format(makePercentageArray.get(i)));
            sumMakePercentageArray = sumMakePercentageArray + (makePercentageArray.get(i));
        }
    }

    public static void shootFreeThrow(int shots) {
        for (int i = 1; i <= shots; i++) {
            System.out.println("************ " + i + " SIMULATED "+ (i+2)+ " ACTUAL SHOT ******************");
            double random = Math.random();
            System.out.println("Before shot make percentage: " + formatter.format(makePercentage));
            System.out.println("Random number: " + random);

            // random number is more likely to be less than make percentage the more the shooter makes a free throw
            if(random < makePercentage) {
                made = made + 1;
                System.out.println("Shot " + i + " was made! :)");
                isShotMade = true;
            } else {
                missed = missed +1;
                System.out.println("Shot " + i + " was missed :(");
                isShotMade = false;
            }

            totalShots = made + missed;
            makePercentage = made/totalShots;

            System.out.println("Made shots: " + made);
            System.out.println("Missed shots: " + missed);
            System.out.println("Total shots: " + totalShots);
            System.out.println("After Shot Make Percentage: " + formatter.format(makePercentage));
            System.out.println("************ END OF " + i + " SIMULATED "+ (i+2)+ " ACTUAL SHOT ************\n");
        }
    }

    private static void simulateNeuroticFreeThrowShooterRound(int numberOfsimulations) {
        for (int i = 1; i <= numberOfSimulations; i++) {
            System.out.println("\n$$$$$$$ START OF SIMULATION $$$$$$$$$$$$$");
            // The neurotic free throw shooter makes the first free throw and misses the second
            made = 1;
            missed = 1;
            totalShots = made + missed;
            makePercentage = made/totalShots;

            // simulate 96 unseen shots
            int simulatedShots = 97;
            shootFreeThrow(simulatedShots);
            System.out.println("\tMake percentage for "+ (simulatedShots + 2) +" shots was " + formatter.format(makePercentage) + "\n");

            // Print results, add simulation to array ONLY if 99 shot was made
            if (isShotMade) {
                makePercentageArray.add(makePercentage);
            }
            System.out.println("$$$$$$$ END OF SIMULATION " + i + " $$$$$$$$$\n");
        }
    }

    private static void outputResults() {
        System.out.println("\nProbability of making the 100th shot for " + makePercentageArray.size() + " kept simulations out of " + numberOfSimulations + " simulations: " + formatter.format(sumMakePercentageArray/(makePercentageArray.size())));
    }

}