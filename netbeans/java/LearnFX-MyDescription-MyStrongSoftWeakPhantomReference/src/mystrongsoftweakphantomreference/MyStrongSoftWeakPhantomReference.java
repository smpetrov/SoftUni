
package mystrongsoftweakphantomreference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

class A{
    //code
}
//get from http://javaconceptoftheday.com/types-of-references-in-java-strong-soft-weak-and-phantom/
public class MyStrongSoftWeakPhantomReference {

  
    public static void main(String[] args) {
        
        //Strong reference
        
        //If you make reference ‘a’ to point to null like in Line 15, then, 
        //object to which ‘a’ is pointing earlier will become eligible for 
        //garbage collection. Because, it will have no active references 
        //pointing to it. This object is most likely to be garbage collected 
        //when garbage collector decides to run.
        A a = new A(); //Strong Reference
        a = null;      //Now, object to which 'a' is pointing earlier is eligible for garbage collection.
        
        //Soft reference
        //The objects which are softly referenced will not be garbage collected 
        //(even though they are available for garbage collection) until JVM badly 
        //needs memory. These objects will be cleared from the memory only if JVM 
        //runs out of memory. You can create a soft reference to an existing object 
        //by using  java.lang.ref.SoftReference class. Below is the code example on 
        //how to create a soft reference.
        //One more use of SoftReference class is that you can retrieve back the 
        //object which has been softly referenced. It will be done by using get() method. 
        //This method returns reference to the object if object is not cleared 
        //from the memory. If object is cleared from the memory, it will return null.
        //Creating Soft Reference to A-type object to which 'a' is also pointing
        SoftReference<A> softA = new SoftReference<A>(a);
        a = null;    //Now, A-type object to which 'a' is pointing earlier is eligible for garbage collection. But, it will be garbage collected only when JVM needs memory.
        a = softA.get();    //You can retrieve back the object which has been softly referenced
        
        //Weak reference
        //JVM ignores the weak references. That means objects which has only 
        //week references are eligible for garbage collection. They are likely 
        //to be garbage collected when JVM runs garbage collector thread. 
        //JVM doesn’t show any regard for weak references.
        //You may think that what is the use of creating weak references if they are 
        //ignored by the JVM, Use of weak reference is that you can retrieve back 
        //the weakly referenced object if it is not yet removed from the memory. 
        //This is done using get() method of WeakReference class. 
        //It will return reference to the object if object is not yet removed from the memory.
        //Creating Weak Reference to A-type object to which 'a' is also pointing.
         WeakReference<A> weakA = new WeakReference<A>(a);
         a = null;    //Now, A-type object to which 'a' is pointing earlier is available for garbage collection.
         a = weakA.get();    //You can retrieve back the object which has been weakly referenced.
         
         //Phantom reference
         //The objects which are being referenced by phantom references are eligible 
         //for garbage collection. But, before removing them from the memory, JVM puts 
         //them in a queue called ‘reference queue’ . They are put in a reference queue 
         //after calling finalize() method on them. You can’t retrieve back the objects 
         //which are being phantom referenced. That means calling get() method on 
         //phantom reference always returns null.
         //Creating ReferenceQueue
         ReferenceQueue<A> refQueue = new ReferenceQueue<A>();
         //Creating Phantom Reference to A-type object to which 'a' is also pointing
         PhantomReference<A> phantomA = new PhantomReference<A>(a, refQueue);
         a = null;    //Now, A-type object to which 'a' is pointing earlier is available 
                      //for garbage collection. But, this object is kept in 'refQueue' 
                      //before removing it from the memory.
         a = phantomA.get();    //it always returns null
    }
    
}
