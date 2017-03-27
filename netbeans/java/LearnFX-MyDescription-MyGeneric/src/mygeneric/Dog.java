package mygeneric;

public class Dog extends MyAnimal implements Animal{

    @Override
    public void eat(String str) {
        System.out.println("Dog eating " + str);
    }
    
}
