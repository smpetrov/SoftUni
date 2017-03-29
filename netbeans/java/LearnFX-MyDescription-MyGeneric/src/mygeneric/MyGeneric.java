
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
        //към наш клас може да добавим всеки тип
        MyClass myClass = new MyClass();
        myClass.add(123);
        myClass.add(12.345);
        myClass.add("abc");
        myClass.add(new MyGenericClass());

        //от Java 5
        //добавени generic т.е. може да дефинираме шаблони класове/generic/
        //и при инстанцията да определим точно типа
        MyGenericClass<String> myGenericClass = new MyGenericClass<String>();

        //от Java 7
        //при инстанциране може да изпуснем типа при вторите <> скоби
        MyGenericClass<String> myGenericClassNew = new MyGenericClass<>();

        //declaration array with generic - ползва се reflection /Integer.class/
        MyGenericClass<Integer> arrayListIntegers = new MyGenericClass<>(Integer.class,
                                                            MyGenericClass.DEFAULT_CAPACITY);




        //             MyAnimal
        //          /              \
        //     Cat                  Dog
        //     /
        //  Kitten

        MyAnimal singleAnimal = new MyAnimal();
        Dog singleDog = new Dog();
        Cat singleCat = new Cat();
        Kitten singleKitten = new Kitten();

        //Родителите на Kitten не могат да го инициализират
        //Родителите не могат да инициализират децата си
        //Kitten singleKitten1 = new MyAnimal(); //не работи compilation error
        //Kitten singleKitten2 = new Cat(); //не работи compilation error

        //Родителите на Kitten не могат да се кастнат до Kitten и да го инициализират
        //Родителите не могат да инициализират децата си чрез кастване
        //Kitten singleKitten3 = (Kitten)new MyAnimal(); //не работи runtime error
        //Kitten singleKitten4 = (Kitten) new Cat();  // не работи runtime error

        //Децата на MyAnimal могат да го инициализират
        //Децата могат да инициализират родителите си
        MyAnimal singleMyAnimal1 = new Dog();
        MyAnimal singleMyAnimal2 = new Cat();
        MyAnimal singleMyAnimal3 = new Kitten();

        //Децата на MyAnimal могат да го инициализират чрeз кастване
        //Децата могат да инициализират родителите си чрeз кастване
        MyAnimal singleMyAnimal4 = (MyAnimal) new Dog();
        MyAnimal singleMyAnimal5 =(MyAnimal) new Cat();
        MyAnimal singleMyAnimal6 =(MyAnimal) new Kitten();
        MyAnimal singleMyAnimal7 =(Cat) new Kitten();


        //Родитиле могат да приемат стойността на свойте деца
        MyAnimal singleMyAnima8 = singleDog;
        //singleMyAnima8.move(); // не работи т.к. ние инициализираме singleAnimal5
                                 // с SingleDog но не му предаваме и методите и полетата на Dog класа

        //Децата не могат да приемат стойността на родителите
        Kitten singleKitten5 = new Kitten();
        //singleKitten5 = singleCat; //не работи


        //при List има инвариантност
        List<Object> objects = new ArrayList<>();
        List<MyAnimal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Kitten> kittens = new ArrayList<>();

        printAnimals(animals);
        //долното не работи т.к. List от Cat не e List от MyAnimal
        //долното не е възможно т.к. при List runtime не се знае типа
        //т.е. знаем само типа определен в параметрите на метода
        //printAnimals а именно MyAnimal
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
        //arCats[1] = new Cat(); // не работи runtime error

        //долното не работи т.к. децата не могат да приемат стойността на родителя
        //arCats[3] = new Object(); не работи compilation error
        //arCats[4] = new MyAnimal(); не работи compilation error


        //долното работи т.к. с думата extends постаняме горна граница до Animal
        //даваме горна граница
        //т.е. ще работи с всички които наследяват Animal
        //You can't add any object to List<? extends T> because you can't
        //guarantee what kind of List it is really pointing to, so you can't
        //guarantee that the object is allowed in that List. The only "guarantee"
        //is that you can only read from it and you'll get a T or subclass of T.
        //т.е. параметъра с тип List<? extends MyAnimal> от параметрите на метода printAnimalsExtends
        //може да се инициализира от всеки наследник на MyAnimal
        //printAnimalsExtends(objects); //не работи compilation error - object не е наследник на MyAnimal
        printAnimalsExtends(animals);
        printAnimalsExtends(dogs);
        printAnimalsExtends(cats);
        printAnimalsExtends(kittens);

        //долното работи т.к. с думата super поставяме долна граница до Cat
        //даваме долна граница
        //т.е. ще работи с всички които са super класове на Cat
        //т.е. параметъра с тип List<? super Cat> от параметрите на метода printAnimalsSuper
        //може да се инициализира от всеки клас, който е super на Cat
        printAnimalSuper(objects);
        printAnimalSuper(animals);
        //printAnimalSuper2(dogs); //не работи compilation error
        printAnimalSuper(cats);
        //printAnimalSuper2(kittens); //не работи compilation error


        //PECS
        //Remember PECS: "Producer Extends, Consumer Super".
        //    "Producer Extends" - If you need a List to produce T values (you want to read Ts from the list), you need to declare it with ? extends T, e.g. List<? extends Integer>. But you cannot add to this list.
        //    "Consumer Super" - If you need a List to consume T values (you want to write Ts into the list), you need to declare it with ? super T, e.g. List<? super Integer>. But there are no guarantees what type of object you may read from this list.
        // If you need to both read from and write to a list, you need to declare
        //it exactly with no wildcards, e.g. List<Integer>.

    }

    public static <T> void printAnimals(List<MyAnimal> animals){
        //code
    }
    public static void printArAnimals(MyAnimal[] animals){
       //code
    }

    //public static <T extends MyAnimal> void printAnimalsExtends(List<T> animals){
    //еднакво с долното
    public static void printAnimalsExtends(List<? extends MyAnimal> animals){    
        //т.к. ние не знаем типа на List runtime
        //защото при стартиране на програмата generic типовете в java изчезват
        //т.е. като параметър на метода е зададен с <T extends MyAnimal>
        //тогава от дума extends следва ,чето List може да е MyAnimal или Cat 
        //или Dog или Kitten - всички наследници на MyAnimal
        //т.е. от List-та може само да четем след което да проверим /instanceof/
        //дали типа и този, които очакваме
        //animals.add((Object) new MyAnimal());  //не работи compilation error
        //animals.add(new Cat());  //не работи compilation error
        //animals.add(new Dog()); //не работи compilation error
        //animals.add(new Kitten()); //не работи compilation error
        //code
    }
    
    //проблема в горния мeтод сe решава като
    /// ????? защо не може public static <T super Cat> void printAnimalsSuper(List<T> Cat){
    public static void printAnimalSuper(List<? super Cat> animals){
        //т.к. ние не знаем типа на List runtime
        //защото при стартиране на програмата generic типовете в java изчезват
        //т.е. като параметър на метода е зададен с <T super Cat>
        //тогава от дума super следва ,че List може да е или Object или MyAnimal 
        //или Cat  - всички super класове на Cat
        //от тук следва, че всички елементи на List са такива, че могат да се 
        //инициализилат с Cat или неговите деца /Kitten/ (т.к. е сигурно че всеки елемент на 
        //List е super клас на Cat а децата могат да инициализират свойте родители)
        //animals.add(new Object()); //не работи  compilation error
        //animals.add(new MyAnimal()); //не работи  compilation error
        animals.add(new Cat()); //децата могат да инициализират свойте родители
        animals.add(new Kitten());  //децата могат да инициализират свойте родители
        //code
    }

    //пример за множествени граници
    public static <T extends MyAnimal,Cat> void printAnimalsMulti(List<T> animals){
        //code
    }
}    
