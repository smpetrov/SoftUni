
package mymethodreference;

public class MyMethodReference {
    String value;
    
    public MyMethodReference(){
        this.value = "default";
    }
    
    public static void method(String input){
        System.out.println("method " + input);
    }
    
    public void nextMethod(String input){
        System.out.println("nextMethod " + input);
    }

    public static void main(String[] args) {
        //constructor reference
        ConstructorReference reference = MyMethodReference::new;
        MyMethodReference cc =  (MyMethodReference) reference.myconstructor();
        
        //static method reference
        MethodReference mr = MyMethodReference::method;
        mr.anotherMethod("static method reference");
        
        //object method reference
        MethodReference mr2 = cc::nextMethod;
        mr2.anotherMethod("object method reference");
        
        System.out.println(cc.value);
    }
    
}
