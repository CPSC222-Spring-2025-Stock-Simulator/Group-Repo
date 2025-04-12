package Backend;

/**
 * This file is part of the final concurrency project
 * 		CPSC 222 Final Project Winter 2025
 *
 * Logic for handling Person class using thread pools
 *
 * @author Cory Stecyk
 * Student Number: 230154922
 * @version 2024.2.3
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * used to manage people and assign threads tasks for people to decide
 */
public class PersonManager
{
    private static ArrayList<Person> people;
    private final ExecutorService executor ;
    private final Random rng = new Random() ;

    /**
     * constructor for manager of a list of people
     */
    public PersonManager()
    {
        people = new ArrayList<>(API.getPeopleAmount()) ;

        for (int i=0 ; i<API.getPeopleAmount() ; i++)
        {
            people.add(new Person(i, rng)) ;
        }
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * assigns decisions as a task to threads in a thread pool
     * @param stock stock
     * @throws InterruptedException exception
     */
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
        updateCycleCount() ;
    }

    /**
     * updates what teh current cycle is to be displayed
     */
    private void updateCycleCount()
    {
        API.incrementCycleCounter() ;
    }

    /**
     * updates the best and worst person to be displayed
     */
    private void updateBestWorstPerson()
    {
        Person best = getBest() ;
        Person worst = getWorst() ;

        API.setBestPersonID(best.getID()) ;
        API.setBestPersonMoney(best.getMoney());
        API.setBestPersonShares(best.getShares());
        API.setBestPersonProfit(best.getProfit()) ;
        API.setBestPersonBuyPrice(best.getBuyPrice()) ;
        API.setBestPersonSellPrice(best.getSellPrice()) ;

        API.setWorstPersonID(worst.getID()) ;
        API.setWorstPersonMoney(worst.getMoney()) ;
        API.setWorstPersonShares(worst.getShares()) ;
        API.setWorstPersonProfit(worst.getProfit()) ;
        API.setWorstPersonBuyPrice(worst.getBuyPrice()) ;
        API.setWorstPersonSellPrice(worst.getSellPrice()) ;
    }

    /**
     * gets the best person
     * @return best person
     */
    private Person getBest()
    {
        Person best = people.getFirst() ;
        float bestProfit = best.getProfit() ;

        for (Person person : people)
        {
            float personProfit = person.getProfit() ;

            if (personProfit > bestProfit)
            {
                best = person ;
                bestProfit = personProfit ;
            }
        }

        return best ;
    }

    /**
     * gets the worst person
     * @return worst person
     */
    private Person getWorst()
    {
        Person worst = people.getFirst() ;
        float worstProfit = worst.getProfit() ;

        for (Person person : people)
        {
            float personProfit = person.getProfit() ;

            if (personProfit < worstProfit)
            {
                worst = person ;
                worstProfit = personProfit ;
            }
        }

        return worst ;
    }
}
