
package changetest;

import java.util.function.IntBinaryOperator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

//HANDLING PROPERTY CHANGE EVENTS
//You can register a ChangeListener to receive notifications about property change events. A property change
//event is fired every time the value of a property changes. The changed() method of a ChangeListener
//receives three values: the reference of the property object, the old value, and the new value.
public class ChangeTest {

    public static void main(String[] args) {
        IntegerProperty counter = new SimpleIntegerProperty(100);
        
        //Add an change listener to the counter property
        counter.addListener(ChangeTest::changed);
        
        System.out.println("\nBefore changing the counter value to 101");
        counter.set(101);
        System.out.println("Set the value-101. Fire an change event because the value was change and status become -has change-");
        System.out.println("After changing the counter value to 101");
        //At this point counter property is changed and further changes
        //to its value will generate change events.
        
        System.out.println("\nBefore changing the counter value to 102");
        counter.set(102);
        System.out.println("Set the value-102.Fire an change event because the value was change and status become -has change-");
        System.out.println("After changing the counter value to 102");
        
        //Try to set the same value
        System.out.println("\nBefore changing tne counter to 102");
        counter.set(102);
        System.out.println("Set the same value-102. Not fire an change event because the value does not change and status stay  -has not change-");
        System.out.println("After changing the counter value to 102");
        
        //Try to set a differentvalue
        System.out.println("\nBefore changing tne counter to 103");
        counter.set(103);;
        System.out.println("Set the value-103. Fire an change event because the value was change and status become -has change-");
        System.out.println("After changing the counter value to 103");
        
    }
    
    public static void changed(
            ObservableValue<? extends Number> prop,
            Number oldValue,
            Number newValue){
        System.out.print("Counter changed:");
        System.out.println("Old = " + oldValue + " New = " + newValue);
    }

}
