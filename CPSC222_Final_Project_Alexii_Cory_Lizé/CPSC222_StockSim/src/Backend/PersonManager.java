package Backend;

import java.util.ArrayList;

public class PersonManager
{
    private ArrayList<Person> people;

    public PersonManager(int peopleAmount, int startingMoney)
    {
        people = new ArrayList<>(peopleAmount) ;

        for (int i=0 ; i<peopleAmount ; i++)
        {
            people.add(new Person(i, startingMoney)) ;
        }
    }

    public ArrayList<Person> getPeople()
    {
        return people ;
    }
}
