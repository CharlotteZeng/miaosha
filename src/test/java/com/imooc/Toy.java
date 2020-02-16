package com.imooc;

interface HasBatteries{}
interface Waterproof{}
interface Shoots{}

/**
 * java编程思想 书中第317页 第十四章 类型信息
 */
public class Toy {
//    Toy(){}
    Toy(int i ){}
}
class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots{
    FancyToy(){super(1);}
}
class ToyTest{
    static void printInfo(Class cc){
        System.out.println("Class name:"+cc.getName()+" is interface? ["+cc.isInterface()+"]");
        System.out.println("Simple name : "+cc.getSimpleName());
        System.out.println("Canonical name : "+cc.getCanonicalName());

    }
    public static void main(String[] args){
        Class c = null;
        try {
            c = Class.forName("com.imooc.FancyToy");
        }catch (ClassNotFoundException e){
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for (Class face :
                c.getInterfaces()) {
            printInfo(face);
        }
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        } catch (InstantiationException e) {
            //当注释掉 Toy的默认构造器后会报此错
            System.out.println("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}