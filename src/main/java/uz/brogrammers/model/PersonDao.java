package uz.brogrammers.model;

import org.springframework.stereotype.Component;
import uz.brogrammers.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    public static int PEOPLE_COUNT;

    private List<Person> list;

    {
        list = new ArrayList<>();

        list.add(new Person(++PEOPLE_COUNT, "Tom"));
        list.add(new Person(++PEOPLE_COUNT, "Bob"));
        list.add(new Person(++PEOPLE_COUNT, "Mike"));
        list.add(new Person(++PEOPLE_COUNT, "Katy"));

    }

    public List<Person> index() {
        return list;
    }

    public Person show(int id) {
        return list.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public boolean add(Person person) {
        person.setId(++PEOPLE_COUNT);
        return list.add(person);
    }

    public void update(int id, Person updatedPerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
    }

}
