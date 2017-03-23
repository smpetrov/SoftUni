
package mymethodreference;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MyMethodReference {
 
    // The method transferElements copies elements from one collection to
    // another
  
    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
        DEST transferElements(
            SOURCE sourceCollection,
            Supplier<DEST> collectionFactory) {
        
            DEST result = collectionFactory.get();
            for (T t : sourceCollection) {
                result.add(t);
            }
            return result;
    }  
  
        
    public static void main(String... args) {

        List<Person> roster = Person.createRoster();        

        System.out.println("\nPrinting list roster:");
        for (Person p : roster) {
            p.printPerson();
        }
        System.out.println("end list roster");
     
        System.out.println("\nPrinting array rosterArray:");
        Person[] rosterAsArray = 
            roster.toArray(new Person[roster.size()]);
        for (Person p : rosterAsArray) {
                 p.printPerson();
             }
        System.out.println("end array rosterAsArray");

        class PersonAgeComparator
            implements Comparator<Person> {
            public int compare(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        
        // Without method reference
        Arrays.sort(rosterAsArray, new PersonAgeComparator());
        
        // With lambda expression
        Arrays.sort(rosterAsArray,
            (Person a, Person b) -> {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        );
        
        // With method reference
        Arrays.sort(rosterAsArray, Person::compareByAge);
        
        // Reference to an instance method of a particular object
        class ComparisonProvider {
            public int compareByName(Person a,
                Person b) {
                return a.getName().compareTo(b.getName());
            }
        
            public int compareByAge(Person a,
                Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);
        
        // Reference to an instance method
        // of an arbitrary object of a particular type
        
        String[] stringArray = { "Barbara", "James", "Mary", "John",
            "Patricia", "Robert", "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        Set<Person> rosterSetLambda =
            transferElements(roster, () -> { return new HashSet<>(); });
        
        Set<Person> rosterSet = transferElements(
            roster, HashSet::new);
        System.out.println("\nPrinting rosterSet:");
        rosterSet.stream().forEach(p -> p.printPerson());
        System.out.println("end set rosterSet");
    }
}
