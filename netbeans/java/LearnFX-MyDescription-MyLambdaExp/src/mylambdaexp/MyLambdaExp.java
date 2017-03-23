
package mylambdaexp;


public class MyLambdaExp {

    public static void main(String[] args) {
        //normal declaration
        Ball b1 = new MyBall();
        b1.hit("normal");
        
        //declaretion with announymous class
        Ball b2 = new Ball(){
            public void hit(String s){
                System.out.println("declare - annonymous class - " + s);
            }
        };
        b2.hit("announymous");
        
        //declaration with lambda
        Ball b3 = (s)->{System.out.println("declare - lambda - " + s);};
        b3.hit("lambda");
        
        //declaration witn method reference
        Ball b4 = System.out::println;
        b4.hit("method reference");
        //т.е тук на b4 даваме функционалнастта на println от System.out
        
        
        }
    
}
