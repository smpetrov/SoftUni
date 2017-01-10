package ivoapp;

import ivoapp.interfaces.addable;
import ivoapp.interfaces.moveable;
import ivoapp.interfaces.removeadle;
import ivoapp.interfaces.switchable;
import ivoapp.interfaces.writeable;
import ivoapp.interfaces.readable;

public abstract class symbol implements writeable,readable,addable,moveable,switchable,removeadle{
    char ch;
    int symbolPosition;
    void mywrite(){
        
    }
}
