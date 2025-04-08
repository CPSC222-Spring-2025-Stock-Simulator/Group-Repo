package Backend;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonManager
{
    private static ArrayList<Person> people;
    private final ExecutorService executor ;

    public PersonManager()
    {
        people = new ArrayList<>(API.getPeopleAmount()) ;

        for (int i=0 ; i<API.getPeopleAmount() ; i++)
        {
            people.add(new Person(i)) ;
        }
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void startDecisionProcess(Stock stock) throws InterruptedException
    {
        CountDownLatch latch = new CountDownLatch(people.size());

        for (Person person : people) {
            executor.submit(() ->
            {
                person.decision(stock);
                latch.countDown();
            });
        }

        latch.await(); // Wait for everyone

        synchronized (stock)
        {
            stock.update(); // apply velocity + acceleration to price
        }
    }

    public ArrayList<Person> getPeople()
    {
        return people ;
    }

    public void updateBestWorstPerson()
    {
        Person best = getBest() ;
        Person worst = getWorst() ;


    }

    private Person getBest()
    {
        Person best = people.getFirst() ;

        for (Person person : people)
        {

        }

        return null ;
    }

    private Person getWorst()
    {
        return null ;
    }
}
