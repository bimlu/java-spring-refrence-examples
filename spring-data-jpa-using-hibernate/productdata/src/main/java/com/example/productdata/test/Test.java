package com.example.productdata.test;

public class Test {

    private static int counter = 0;

    public Test() {
        counter++;
        if (counter==1) {
        }
    }

    public void sayHello() {
        System.out.println("Hello");
    }

    public void sayBye() {
        System.out.println("Bye");
    }

    public static void main(String[] args) {

        Test test1 = new Test();
        Test test2 = new Test();

        test1.sayHello();

        test2.sayHello();
        test2.sayBye();
    }
}
