package design.pattern.factory;

import design.pattern.Circle;
import design.pattern.Rectangle;
import design.pattern.Shape;

public class FactoryTest {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Shape s1 = factory.getShagpe("Circle");
        s1.draw();
        Shape s2 = factory.getShagpe("Rectangle");
        s2.draw();

        // 如果不用工厂
        Shape s3 = new Circle();
        s3.draw();
        Shape s4 = new Rectangle();
    }

}
