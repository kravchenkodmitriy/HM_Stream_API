import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> randomHuman = generatePerson();

        long youngPeople = randomHuman.stream()
                .filter(person -> person.getAge() < 18 )
                .count();
        System.out.println(youngPeople);

        List<String> militaryFamily = randomHuman.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .limit(100)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println(militaryFamily);

        List<Person> peopleWithHigherEducation = randomHuman.stream()
                .filter(person -> person.getEducation() == Education.HIGHER && person.getAge() >= 18)
                .filter(person -> person.getAge() <= 60 && person.getSex() == Sex.WOMAN
                        || person.getAge() <= 65 && person.getSex() == Sex.MAN)
                .limit(100)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(peopleWithHigherEducation);
    };
    public static List<Person> generatePerson(){
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        return (List<Person>) persons;
    }
}
