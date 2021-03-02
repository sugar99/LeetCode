package design.pattern.factory;

import design.pattern.*;

public class Factory {
    public Shape getShagpe(String type){
        if(type.equals("Circle")){
            return new Circle();
        }else if(type.equals("Rectangle")){
            return new Rectangle();
        }else{
            System.out.println("Not Found such Shape");
            return new Shape();
        }
    }

    public Fruit getFruit(String type){
        if(type.equals("Apple")){
            return new Apple();
        }else if(type.equals("Banana")){
            return new Banana();
        }else {
            return new Fruit();
        }
    }
}
