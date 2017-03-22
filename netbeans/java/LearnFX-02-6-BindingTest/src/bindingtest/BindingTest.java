
package bindingtest;


public class BindingTest {

    public static void main(String[] args) {
        
        //Lazily Instantiating Property Objects
        //ползва се когато класа ползва obserbable and binding features
        //рядко.
        //два са случайте:
        //  1- /клас Monitor/ -когато пропъртито често се инициализира и ползва дефолтната си стойност
        //  2- /клас Item/ - когато пропъртито ползва рядко obserbable and binding features
        
        
        Monitor m = new Monitor();
        String st1 = m.screenTypeProperty().get(); //тук инстанцираме пропърти обекта screenType
        String st2 = m.getScreenType(); //тук не инстанцираме пропърти обекта screenType
        
        Item i = new Item();
        i.setWeight(5.0); //тук не инстанцираме пропърти обекта weight
        double d1 = i.weightProperty().get(); //тук инстанцираме пропърти обекта weight
        double d2 = i.getWeight(); //тук не инстанцираме пропърти обекта weight
        
        //Класа Monitor декларира статична променлива DEFAULT_SCREEN_TYPE
        //която е дефолтната стойност на типа screenType. Той декларира 
        //StringProperty, което не се инстанцира при декларирането на класа 
        //а се инстанцира по-късно когато е необходимо
        
        //Item класа декларира една допълнителна променлива _weight, която
        //се използва за да държи стойността/value/ без да се инстанцира
        //пропъртито weight докато не се инстанцира пропъртито weight.
        //За разлика от класа Monitor при класа Item промяната на стойността на 
        //на weight не води до инстанциране на пропъртито weight. Пропъртито weight
        //се инстанцира чак когато се викне метода weightProperty()
    }
    
}
