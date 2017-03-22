
package myinvalidationlistener;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.shape.Circle;


public class MyInvalidationListener {

   
    public static void main(String[] args) {
        //Define some circles
        final Circle circle1 = new Circle(10.5);
        final Circle circle2 = new Circle(15.5);
        
        //Add an invalidation listener to circle2's redius property
        circle2.radiusProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable o){
                System.out.println("Invalidation detected for "+o);
                circle1.setRadius(circle2.getRadius());
            }
        });
        
        System.out.println("Circle1: "+circle1.getRadius());
        System.out.println("Circle2: "+circle2.getRadius());
        circle2.setRadius(20.5);
        System.out.println("Circle1: "+circle1.getRadius());
        System.out.println("Circle2: "+circle2.getRadius());
    }
    
}
