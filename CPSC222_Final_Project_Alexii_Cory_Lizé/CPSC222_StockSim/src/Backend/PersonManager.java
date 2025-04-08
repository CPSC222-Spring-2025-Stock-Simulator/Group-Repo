package Backend;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonManager
{
    private static ArrayList<Person> people;
    private final ExecutorService executor ;
    private final Random rng = new Random() ;

    public PersonManager()
    {
        people = new ArrayList<>(API.getPeopleAmount()) ;

        for (int i=0 ; i<API.getPeopleAmount() ; i++)
        {
            people.add(new Person(i, rng)) ;
        }
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void startDecisionProcess(Stock stock) throws InterruptedException
    {
        CountDownLatch latch = new CountDownLatch(people.size());

        for (Person person : people) {
            executor.submit(() ->
            {
                person.decision(stock, rng);
                latch.countDown();
            });
        }

        latch.await() ; // wait for everyone

        synchronized (stock)
        {
            stock.update() ; // apply velocity and acceleration to price
        }

        updateBestWorstPerson() ;
    }

    public ArrayList<Person> getPeople()
    {
        return people ;
    }

    public void updateBestWorstPerson()
    {
        Person best = getBest() ;
        Person worst = getWorst() ;

        API.setBestPersonID(best.getID()) ;
        API.setBestPersonProfit(best.getProfit()) ;
        API.setBestPersonBuyPrice(best.getBuyPrice()) ;
        API.setBestPersonSellPrice(best.getSellPrice()) ;

        API.setWorstPersonID(worst.getID()) ;
        API.setWorstPersonProfit(worst.getProfit()) ;
        API.setWorstPersonBuyPrice(worst.getBuyPrice()) ;
        API.setWorstPersonSellPrice(worst.getSellPrice()) ;
    }

    private Person getBest()
    {
        Person best = people.getFirst() ;
        double bestProfit = best.getProfit() ;

        for (Person person : people)
        {
            double personProfit = person.getProfit() ;

            if (personProfit > bestProfit)
            {
                best = person ;
                bestProfit = personProfit ;
            }
        }

        return best ;
    }

    private Person getWorst()
    {
        Person worst = people.getFirst() ;
        double worstProfit = worst.getProfit() ;

        for (Person person : people)
        {
            double personProfit = person.getProfit() ;

            if (personProfit < worstProfit)
            {
                worst = person ;
                worstProfit = personProfit ;
            }
        }

        return worst ;
    }
}
