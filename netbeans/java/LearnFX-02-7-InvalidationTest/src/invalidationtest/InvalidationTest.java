
package invalidationtest;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

//HANDLING PROPERTY INVALIFATION EVENTS
//A property generates an invalidation event when the status of its value changes from valid to invalid for the
//first time. Properties in JavaFX use lazy evaluation. When an invalid property becomes invalid again, an
//invalidation event is not generated. An invalid property becomes valid when it is recomputed, for example,
//by calling its get() or getValue() method.
//The generation of an invalidation event by an Observable does not necessarily mean that its
//content has changed. All it means is that its content is invalid for some reason. For example, sorting an
//ObservableList may generate an invalidation event. Sorting does not change the contents of the list;
//it only reorders the contents.

public class InvalidationTest {

  
    public static void main(String[] args) {
        IntegerProperty counter = new SimpleIntegerProperty(100);
        
        //Add an invalidation listener to the counter property
        counter.addListener(InvalidationTest::invalidated);
        
        System.out.println("\nBefore changing the counter value to 101");
        counter.set(101);
        System.out.println("Set the value-101. Fire an invalidation event because the value was change and status become -has change-");
        System.out.println("After changing the counter value to 101");
        //At this point counter property is invalid and further changes
        //to its value will not generate invalidation events.
        
        System.out.println("\nBefore changing the counter value to 102");
        counter.set(102);
        System.out.println("Set the value-102. Not fire an invalidation event because the value was change and status already became -has not change-");
        System.out.println("After changing the counter value to 102");
        
        //Make the counter property valid by calling its get() method
        int myvalue = counter.get();
        System.out.println("\nGet the value-102. Make the counter property valid by calling its get() method. Thus make status -valid- ");
        System.out.println("Counter value = " + myvalue);
        
        //At this point counter property is valid and further chnages
        //to its value will generate invalidation events.
        //Try to set the same value
        System.out.println("\nBefore changing tne counter to 102");
        counter.set(102);
        System.out.println("Set the same value-102. Not fire an invalidation event because the value does not change and status stay  -has not change-");
        System.out.println("After changing the counter value to 102");
        
        //Try to set a differentvalue
        System.out.println("\nBefore changing tne counter to 103");
        counter.set(103);;
        System.out.println("Set the value-103. Fire an invalidation event because the value was change and status become -has change-");
        System.out.println("After changing the counter value to 103");
    }
    
    public static void invalidated(Observable prop){
        System.out.println("Counter is invalid.");
    }
    
}
