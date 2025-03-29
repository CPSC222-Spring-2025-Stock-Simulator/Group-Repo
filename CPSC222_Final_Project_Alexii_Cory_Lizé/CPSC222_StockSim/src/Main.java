import java.util.ArrayList;
import java.util.Random;

public class Main
{
    private static CLI cli;
    private static volatile boolean isRunning = false ;
    public static void main(String[] args)
    {
        Random random = new Random();


        cli = new CLI(random) ;
        Thread t = new Thread(cli);
        t.start();
        // Start and run the CLI

        // TODO: Start the GUI here

        while (!isRunning) {
            Thread.onSpinWait();
        }
        // Wait for start to be typed in the CLI
        //TODO: If a start button has been implemented it should also pass this


        int startingMoney = cli.getStartingMoney() ;
        int peopleCount = cli.getPeople() ;
        int cycleCount = cli.getCycleCount() ;
        // Grabs initial values. Will be the default values unless modified through the CLI

        System.out.print("PROGRAM STARTED\n$ ");
        //TODO: Remove this

        while (isRunning)
        {
            PersonManager personManager = new PersonManager(peopleCount, startingMoney) ;
            ArrayList<Person> people = personManager.getPeople() ;
            for (int i=0 ; i<cycleCount ; i++)                                       // this is where cycles happen
            {




                for (Person person : people)
                {

                }








            }
        }
    }

    public static void setIsRunning(boolean isRunning) {
        Main.isRunning = isRunning;
    }

    public static CLI getCli() {
        return cli;
    }
}