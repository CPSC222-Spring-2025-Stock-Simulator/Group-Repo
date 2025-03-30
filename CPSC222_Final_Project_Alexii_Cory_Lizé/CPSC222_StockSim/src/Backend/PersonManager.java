package Backend;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonManager
{
    private ArrayList<Person> people;
    private final ExecutorService executor ;

    public PersonManager()
    {
        people = new ArrayList<>(API.getPeopleAmount()) ;

        for (int i=0 ; i<API.getPeopleAmount() ; i++)
        {
            people.add(new Person(i)) ;
        }

        int coreCount = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(coreCount);

    }

    public void makeDecision()
    {
        for (Person person : people)
        {
            executor.submit(person::decision); // Submit each person's decision as a task
        }
    }


    public ArrayList<Person> getPeople()
    {
        return people ;
    }
}
