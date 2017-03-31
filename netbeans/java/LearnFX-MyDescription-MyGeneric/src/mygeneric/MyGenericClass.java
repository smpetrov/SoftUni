package mygeneric;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyGenericClass<T> implements MyGenericInterface<T>{
    public static final int DEFAULT_CAPACITY = 10;
    Class<T> classParm;
    
    T genericParameter;    
    //declaration generic array in Java - version 1
    private  T[] genericArray1;

    //declaration generic array in Java - version 2 - recommend - проверка за типовете при компилация
    private  T[] genericArray2;

    public MyGenericClass(){
    }

    //construvtor generic array in Java - version 1
    public MyGenericClass(Class<T> classParm){
        this.classParm= classParm;
        genericArray1 = (T[]) new Object[DEFAULT_CAPACITY];
    }

    //construvtor generic array in Java - version 2 - recommend - проверка за типовете при компилация
    public MyGenericClass(Class<T> classParm, int capacity){
        this.classParm= classParm;
        genericArray1 = (T[]) Array.newInstance(classParm, capacity);
    }

    @Override
    public void add(T element){
        //code
    }

    @Override
    public T get(int index) {
        return genericArray1[index];
    }


    //generic method, който не връша данни 
    public <T> void myGenericMethod(){
    }
    
    //generic method, който не връша T
    public <T extends MyClass> T myGenericMethodReturn(T parm){
        return parm;
    }

    //generic method, който връша String
    //вътре в метода може да се ползва тип T
    public <T> String myGenericMethodString(T parm){
        return "abv";
    }

    //generic method, който връша generic /list<T>
    //<T> generic type, който ше се ползва в метода
    //List<T> метода ще върне лист от generic
    public <T> List<T> myGenericMethodList(){
        List<T> genericList = new ArrayList();
        return genericList;
    }
    
    //generic method, който връша String
    //вътре в метода може да се ползва тип T
    //огваничаваме T до тип, който може да бъде сравняван /Comparable/
    public <T extends Comparable<T>> String myGenericMethodCompare(T parm){
        return "abv";
    }
    
    //демонстрация на type erasure
    //В Java параметри не могат да бъдат примитивни типове /int.double i т.н./
    // вместо тях зе ползмат Integer, Double и т.н.
    public void demoTypeErasure(Object obj){
        //тези изрази ще дадат грешка т.к. runtime на generic типа не се знае типа на T
        //if (obj instanceof T){}
        //T[] array = new T[10];
        //T newInstance = new T();
    }
 
}
