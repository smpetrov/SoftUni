
package mygeneric;


public class MyAnimal implements Animal {
    @Override
    public void eat(String str) {
        System.out.println("Dog eating " + str);
    }
}
