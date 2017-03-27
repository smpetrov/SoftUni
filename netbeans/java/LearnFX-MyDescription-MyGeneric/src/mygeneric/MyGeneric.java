
package mygeneric;

//Generic class -  параметризиран клас - клас с рапаметри, които са типове
//В Java параметри не могат да бъдат примитивни типове /int.double i т.н./
// вместо тях зе ползмат Integer, Double и т.н.

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//Пр. ArrayList<Integer>
//generic в java не подържат типа на данните runtime - това е type erasure
// т.е. при стартиране на програмата типа на generic типовете изчезва
//затова при извеждане трябва да се каства типа

public class MyGeneric {
    
    public static void main(String[] args) {
        //преди Java 5
        //може да добавим всеки тип
        MyClass myClass = new MyClass();
        myClass.add(123);
        myClass.add(12.345);
        myClass.add("abc");
        myClass.add(new MyGenericClass());
        
        //от Java 5
        MyGenericClass<String> myGenericClass = new MyGenericClass<String>();
        
        //от Java 7
        MyGenericClass<String> myGenericClassNew = new MyGenericClass<>();
        
        //declaration array with generic - ползва се reflection /Integer.class/
        MyGenericClass<Integer> arrayListIntegers = new MyGenericClass<>(Integer.class, 
                                                            MyGenericClass.DEFAULT_CAPACITY);
        
        
        //при List има инвариантност 
        List<Object> objects = new ArrayList<>();
        List<MyAnimal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Kitten> kittens = new ArrayList<>();
        printAnimals(animals);
        //долното не работи т.к. List от Cat не e List от MyAnimal
        //долното не е възможно т.к. при List runtime не се знае типа 
        //printAnimals(cats);
        
        //долното не работи т.к. List от Cat не e List от MyAnimal
        //долното не е възможно т.к. при List runtime не се знае типа 
        //animals=cats;
        
        //при масиви има коинвариантност
        MyAnimal[] arAnimals = new MyAnimal[10];
        Dog[] arDogs = new Dog[10];    
        Cat[] arCats = new Cat[10];    
        Kitten[] arKittens = new Kitten[10];
        //долното работи т.к. array от Cat e array от MyAnimal
        //долното е възможно т.к. при масивите runtime се знае типа 
        arAnimals=arCats;
        
        //долното работи т.к. array от Cat e array от MyAnimal
        //долното е възможно т.к. при масивите runtime се знае типа 
        printArAnimals(arCats);
        
        //долното работи т.к. array от Cat му дадохме да e array от Kitten
        //долното е възможно т.к. при масивите runtime се знае типа 
        arCats=arKittens;
        arCats[0] = new Kitten();
        arCats[1] = new Cat();
        
        //?????
        //долното не работи т.к. сме казали, че arCats е array от Cat 
        //т.е само Cat и негови наследници могат да се добавят
        //arCats[3] = new Object(); не работи
        //arCats[4] = new MyAnimal(); не работи
        
        
        //долното работи т.к. с думата extends постаняме горна граница до Animal
        //даваме горна граница
        //т.е. ще работи с всички които наследяват Animal
        //printAnimalsExtends(objects); //не работи
        printAnimalsExtends(animals);
        printAnimalsExtends(dogs);
        printAnimalsExtends(cats);
        printAnimalsExtends(kittens);
        
        //долното работи т.к. с думата super поставяме долна граница до Cat 
        //даваме долна граница
        //т.е. ще работи с всички които са super класове на Cat
        printAnimalSuper2(objects);
        printAnimalSuper2(animals);
        //printAnimalSuper2(dogs); //не работи 
        printAnimalSuper2(cats);
        //printAnimalSuper2(kittens); //не работи
    }
    
    public static <T> void printAnimals(List<MyAnimal> animals){
        //code
    }
    
    public static <T extends MyAnimal> void printAnimalsExtends(List<T> animals){
        //това не става т.к. ние тук не знаем дали листа е от котки/Cat/, кучета/Dog/ или котенца/Kitten/
        //т.к. List<MyAnimal> не е List<Cat> и т.н.- т.к. при List runtime не се знае типа 
        //animals.add(new Object()); //не работи 
        //animals.add(new MyAnimal());  //не работи 
        //animals.add(new Cat());  //не работи 
        //animals.add(new Dog()); //не работи 
        //animals.add(new Kitten()); //не работи 
        //code
    }
    public static void printAnimalsExtends2(List<? extends MyAnimal> animals){
        //това не става т.к. ние тук не знаем дали листа е от котки/Cat/, кучета/Dog/ или котенца/Kitten/
        //т.к. List<MyAnimal> не е List<Cat> и т.н.- т.к. при List runtime не се знае типа 
        //animals.add(new Object()); //не работи 
        //animals.add(new MyAnimal());  //не работи 
        //animals.add(new Cat());  //не работи 
        //animals.add(new Dog()); //не работи 
        //animals.add(new Kitten()); //не работи 
        //code
    }
    public static <T super Cat> void printAnimalsSuper(List<T> animals){
        //това не става т.к. ние тук не знаем дали листа е от котки/Cat/, кучета/Dog/ или котенца/Kitten/
        //т.к. List<MyAnimal> не е List<Cat> и т.н.- т.к. при List runtime не се знае типа 
        //animals.add(new Object()); //не работи 
        //animals.add(new MyAnimal());  //не работи 
        //animals.add(new Cat());  //не работи 
        //animals.add(new Dog()); //не работи 
        //animals.add(new Kitten()); //не работи 
        //code
    }
    
    //проблема в горния митод си решава като
    public static void printAnimalSuper2(List<? super Cat> animals){
        //? - generic тип, който не зваем какъв ще бъде
        //със super казваме, че масива animals e от Cat и тези на, който той е super
        //animals.add(new Object()); //не работи 
        //animals.add(new MyAnimal()); //не работи 
        //animals.add(new Dog()); //не работи 
        animals.add(new Cat());
        animals.add(new Kitten());
        //code
    }
          
    //пример за множествени граници
    public static <T extends MyAnimal,Cat> void printAnimalsMulti(List<T> animals){
        //code
    }
    public static void printArAnimals(MyAnimal[] animals){
       //code     
    }
}
