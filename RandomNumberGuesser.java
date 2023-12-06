import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class RandomNumberGuesser {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_ROUNDS = 3;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int totalScore = 0;

        System.out.println("Welcome,to the Number Guessing Game!");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Total number of rounds : 3 \n" + "Attempts to guess number in each round : 5\n");
        String name;
        System.out.println("What is your name?");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();



        for(int i = 1 ; i<= MAX_ROUNDS; i++){
           int number = random.nextInt(MAX_RANGE) + MIN_RANGE;
           int attempts = 0;

            System.out.println("-------------------------------------------------------------------");
            System.out.printf("Round %d:\n" + " Well," + name + ",I'm thinking of a number between  %d and %d.", i,MIN_RANGE,MAX_RANGE);

            while(attempts < MAX_ATTEMPTS){
                System.out.println("Enter your guess : ");
                int guess_number = sc.nextInt();
                attempts = attempts+1;

                if(guess_number == number){
                    int score = MAX_ATTEMPTS - attempts;
                    totalScore = totalScore + score;
                    System.out.printf("Hurray!! Number Guessed successfully. Attempts = %d. Round Score = %d\n", attempts, score);
                    break;
                }
                else if(guess_number < number){
                    System.out.printf("Nope! The number is greater than %d. Attempts left = %d.\n",guess_number, MAX_ATTEMPTS - attempts);
                }
                else {
                    PrintStream printf = System.out.printf("Nope! The number is less than %d. Attempts left = %d.\n", guess_number, MAX_ATTEMPTS - attempts);
                }
            }
            if (attempts == MAX_ATTEMPTS){
                System.out.printf("\nRound = %d\n", i );
                System.out.println("Attempts = 0 ");
                System.out.printf("The Random Number is : %d\n\n", number);
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("Game Over. Total Score = %d\n", totalScore);
        System.out.println("----------------------------------------------------------------------------------");
        sc.close();

    }

}
