package code.shubham.java;

public class ConstructorChaining {
    public class Parent {

        static {
            System.out.println("Parent class static initializr");
        }

        {
            System.out.println("Parent class object initializr");
        }

        Parent () {
            this("Constructor");
            System.out.println("Parent class Default Constructor");
        }
        Parent (String s){
            System.out.println("Parent Class Param "+s);
        }
    }
    public class Child extends Parent {

        static {
            System.out.println("child class static initializr");
        }

        {
            System.out.println("child class object initializr");
        }

        Child (){
            super("Constructor");
            System.out.println("Child Class Default Constructor");
        }
        Child (String s){
            System.out.println("Child Class Param Constructor "+s);
        }
    }

    void main(String[] args) {
        Child obj = new Child("A");
    }
}
