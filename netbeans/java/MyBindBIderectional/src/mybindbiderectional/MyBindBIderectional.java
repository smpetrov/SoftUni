
package mybindbiderectional;

import javafx.scene.shape.Circle;

public class MyBindBIderectional {

    public static void main(String[] args) {
        //Define some circles
        Circle circle1 = new Circle(10.5);
        Circle circle2 = new Circle(15.5);
        //circle1 takes on value of circle2 radius
        circle1.radiusProperty().bindBidirectional(circle2.radiusProperty());
        System.out.println("Circle1: "+circle1.getRadius());
        System.out.println("Circle2: "+circle2.getRadius());
        //Both circles are now 20.5
        circle2.setRadius(20.5);
        System.out.println("Circle1: "+circle1.getRadius());
        System.out.println("Circle2: "+circle2.getRadius());
        //Both circles are now 30.5
        circle1.setRadius(30.5);
        System.out.println("Circle1: "+circle1.getRadius());
        System.out.println("Circle2: "+circle2.getRadius());
    }
    
}
