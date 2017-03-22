
package mychangelistener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Circle;


public class MyChangeListener {

    public static void main(String[] args) {
        //Define some circles
        final Circle circle1 = new Circle(10.5);
        final Circle circle2 = new Circle(15.5);
        
        //Add an change listener to circle2's redius property
        circle2.radiusProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                        Number ildValue, Number newValue){
                System.out.println("Change detected for "+ov);
                circle1.setRadius(newValue.doubleValue());
            }
        });
        
        System.out.println("Circle1: "+circle1.getRadius());
        System.out.println("Circle2: "+circle2.getRadius());
        circle2.setRadius(20.5);
        System.out.println("Circle1: "+circle1.getRadius());
        System.out.println("Circle2: "+circle2.getRadius());
    }
    
}
