
package bindingtest;

//това е клас, който не ще ползва неговите observable and 

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

//binding features в повечето случай
//тук инстанцираме the property object кагато ни е нужен
public class Item {
    private double _weight = 150;
    private DoubleProperty weight;
    
    //тук проверяваме дали weight пропъртито е инстанцирано и 
    //ако не е връщаме стойността на _weight ако е връщаме
    //стойността/velue/ на прпъртито weight
    public double getWeight(){
        return (weight == null) ? _weight : weight.get();
    }
    
    //тук ако не е инстанцирано пропъртито/weight/ то на
    //_weight приснояваме стойността newWeight а ако е 
    //инстанцирано пропъртито/weight/ то приснояваме на неговата
    //стойност/value/ стойността newWeight
    public void setWeight(double newWeight){
        if (weight == null){
            _weight = newWeight;
        } else {
            weight.set(newWeight);
        }
    }
    
    //тук инстанцираме weight пропъртито/обект/ първия път
    //когато го извикваме
    public DoubleProperty weightProperty(){
        if (weight == null){
            weight = new SimpleDoubleProperty(this,"weight",_weight);
        }
        return weight;
    }
}
