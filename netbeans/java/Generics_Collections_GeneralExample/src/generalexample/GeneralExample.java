
package generalexample;

//Arrays and List are part of Collections Framework
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class GeneralExample {

    public static void main(String[] args) {
        //GENERIC
        //BEFORE Java 5 WITHOUT GENERICS
        List intsBefore = Arrays.asList(new Integer[]{
            new Integer(1), new Integer(2), new Integer(3)
        });
        int sb = 0;
        for (Iterator it = intsBefore.iterator(); it.hasNext();){
            int n = ((Integer) it.next()).intValue();
            sb += n;
        }
        System.out.println(sb);
        //AFTER Java 5 WITH GENERICS
        //List is generic type
        //Integer is wrapper of primitive type int
        //Boxing and unboxing operations, used to convert from the primitive type 
        //to the wrapper class, are automatically inserted.
        List<Integer> intsAfter = Arrays.asList(1,2,3);
        int sa =0 ;
        //n is bind successively to each element of the list ints
        for(int n : intsAfter){sa += n;}
        System.out.println(sa);
        
        //Reading this code is not quite so easy. Without generics, there is no 
        //way to indicate in the type declaration what kind of elements you intend 
        //to store in the list, so instead of writing List<Integer>, you write 
        //List. Now it is the coder rather than the compiler who is responsible 
        //for remembering the type of the list elements, so you must write the 
        //cast to (Integer) when extracting elements from the list. Without boxing 
        //and unboxing, you must explicitly allocate each object belonging 
        //to the wrapper class Integer and use the intValue method to extract 
        //the corresponding primitive int. Without functions that accept a variable 
        //number of arguments, you must explicitly allocate an array to pass 
        //to the asList method. Without the new form of loop, you must explicitly 
        //declare an iterator and advance it through the list.
        
        //Other examples
        //BEFORE Java 5 WITHOUT GENERICS
        List wordsBefore = new ArrayList();
        wordsBefore.add("Hello");
        wordsBefore.add(" world!");
        String tb = ((String) wordsBefore.get(0)) + ((String) wordsBefore.get(1));
        System.out.println(tb);
        
        //AFTER Java 5 WITH GENERICS
        List<String> wordsAfter = new ArrayList<String>();
        wordsAfter.add("Hello");
        wordsAfter.add(" world!");
        String ta = wordsAfter.get(0) + wordsAfter.get(1);
        System.out.println(ta);
            
        //Without generics, the type parameters are omitted, but you must 
        //explicitly cast whenever an element is extracted from the list. 
        //In fact, the bytecode compiled from the two sources above will be 
        //identical. We say that generics are implemented by erasure because 
        //the types List<Integer>,List<String>, and List<List<String>> are 
        //all represented at run-time by the same type, List. We also use 
        //erasure to describe the process that converts the first program 
        //to the second. The term erasure is a slight misnomer, since the process 
        // erases type parameters but adds casts.
        //Generics implicitly perform the same cast that is explicitly 
        //performed without generics. If such casts could fail, it might 
        //be hard to debug code written with generics. This is why it is 
        //reassuring that generics come with the following guarantee:
        //Cast-iron guarantee: the implicit casts added by the compilation 
        //of generics never fail.
        //Of course, the cast-iron guarantee mentioned above holds only 
        //if you add generic types that match the legacy code
        //Another consequence of implementing generics by erasure is that 
        //array types differ in key ways from parameterized types. 
        //Executing
        //      new String[size]
        //allocates an array, and stores in that array an indication that 
        //its components are of type String. In contrast, executing:
        //      new ArrayList<String>()
        //allocates a list, but does not store in the list any indication 
        //of the type of its elements. In the jargon, we say that Java 
        //reifies array component types but does not reify list element types 
        //(or other generic types). Later, we will see how this design eases 
        //evolution but complicates casts, instance tests, and array creation 
        
        //Generics Versus Templates Generics in Java resemble templates in C++.
        //Semantically, Java generics are defined by erasure, whereas 
        //C++ templates are defined by expansion. In C++ templates, each instance 
        //of a template at a new type is compiled separately. If you use a list 
        //of integers, a list of strings, and a list of lists of string, 
        //there will be three versions of the code. If you use lists of a hundred 
        //different types, there will be a hundred versions of the code—a 
        //problem known as code bloat. In Java, no matter how many types of lists 
        //you use, there is always one version of the code, so bloat does not occur.
        //Expansion may lead to more efficient implementation than erasure, 
        //since it offers more opportunities for optimization, particularly 
        //for primitive types such as int. For code that is manipulating large 
        //amounts of data—for instance, large arrays in scientific 
        //computing—this difference may be significant. However, in practice, 
        //for most purposes the difference in efficiency is not important, 
        //whereas the problems caused by code bloat can be crucial.
        //In C++, you also may instantiate a template with a constant 
        //value rather than a type, making it possible to use templates 
        //as a sort of “macroprocessor on steroids” that can perform arbitrarily 
        //complex computations at compile time. Java generics are deliberately 
        //restricted to types, to keep them simple and easy to understand.
        
        
        //VARARGS
        //WITHOUT VARARGS
        //Now the method ListsBefore.toList may be invoked as follows:
        List<Integer> intsVB = ListsBefore.toList(new Integer[] { 1, 2, 3 });
        List<String> wordsVB = ListsBefore.toList(new String[] { "hello", "world" });
        
        //WITH VARARGS
        //The vararg feature permits a special, more convenient syntax for 
        //the case in which the last argument of a method is an array. 
        //To use this feature, we replace T[] with T… in the method declaration:
        //Now the method may be invoked as follows:
        List<Integer> intsVA = ListsAfter.toList(1, 2, 3);
        List<String> wordsVA = ListsAfter.toList("hello", "world");
        
        //This is just shorthand for what we wrote above. At run time, 
        //the arguments are packed into an array which is passed to the method, 
        //just as previously. 
        //Any number of arguments may precede a last vararg argument. 
        //Here is a method that accepts a list and adds all the additional 
        //arguments to the end of the list:
        //public static <T> void addAll(List<T> list, T... arr) {
        //    for (T elt : arr) list.add(elt);
        //}
        //Since varargs always create an array, they should be used 
        //only when the argument does not have a generic type
    }    
}

//VARARGS
//WITHOUT VARARGS
class ListsBefore{
    public static <T> List<T> toList(T[] arr) {
        List<T> list = new ArrayList<T>();
        for (T elt : arr) list.add(elt);
        return list;
    }
}

//WITH VARARGS
//The vararg feature permits a special, more convenient syntax for 
//the case in which the last argument of a method is an array. 
//To use this feature, we replace T[] with T… in the method declaration:
class ListsAfter{
    public static <T> List<T> toList(T... arr) {
        List<T> list = new ArrayList<T>();
        for (T elt : arr) list.add(elt);
        return list;
    }
}
