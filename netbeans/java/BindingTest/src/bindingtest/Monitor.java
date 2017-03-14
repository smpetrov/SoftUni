
package bindingtest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//този клас декларира StringProperty, което не се инстанцира 
//в момента на декларерането на класа а се инстанцира
//по късно когато е необходимо - спестява се памет
//така се прави за клас, който в повечето случай ползва дифолтната стойност
public class Monitor {
    public static final String DEFAULT_SCREEN_TYPE="flat";
    private StringProperty screenType;
    
    //тук getScreenType връща стринг а не обекта screenType 
    //като  проверява дали screeType е инстанциран
    //и ако не е се връща стринг или ако е се връща стринга 
    //запазен като value в screenType пропъртито
    public String getScreenType(){
        return (screenType == null) ? DEFAULT_SCREEN_TYPE : screenType.get();
    }
    
    //тук проверяваме дали screeType пропъртито не е вече инстанцирано
    //или дали стойността, която искаме да му присвоям е различна от дифолтната
    //и само тогава инстанцираме пропъртито/обекта/ screenType - така спестяваме памет
    public void setScreenType(String newScreenType){
        if ((screenType != null) || 
            (!DEFAULT_SCREEN_TYPE.equals(newScreenType))){
            screenTypeProperty().set(newScreenType);
        }
    }
    
    //тук инстанцираме screenType пропъртито/обект/ първия път
    //когато го извикваме
    public StringProperty screenTypeProperty(){
        if (screenType == null){
            screenType = new SimpleStringProperty(
                this,"screenType",DEFAULT_SCREEN_TYPE);
        }
        return screenType;
    }
    
    
}
