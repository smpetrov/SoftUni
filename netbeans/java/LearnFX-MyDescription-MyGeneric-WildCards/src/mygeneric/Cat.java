package mygeneric;


public class Cat extends MyAnimal implements Animal{

    @Override
    public void eat(String str) {
        System.out.println("Cat eating " + str);
    }
    
}
