import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        boolean isRunning = true ;

        CLI cli = new CLI() ;
        int startingMoney = cli.getStartingMoney() ;
        int peopleCount = cli.getPeople() ;
        int cycleCount = cli.getCycleCount() ;

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
}