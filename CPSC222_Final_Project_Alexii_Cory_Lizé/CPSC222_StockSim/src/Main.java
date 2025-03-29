import java.util.ArrayList;
import java.util.Random;

public class Main
{
    private static CLI cli;
    public static void main(String[] args)
    {
        Random random = new Random();
        boolean isRunning = true ;

        cli = new CLI(random) ;
        int startingMoney = cli.getStartingMoney() ;
        int peopleCount = cli.getPeople() ;
        int cycleCount = cli.getCycleCount() ;
        Thread t = new Thread(cli);
        t.start();

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

    public static CLI getCli() {
        return cli;
    }
}