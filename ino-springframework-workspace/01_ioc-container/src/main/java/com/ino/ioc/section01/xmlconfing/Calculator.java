package com.ino.ioc.section01.xmlconfing;

public class Calculator {
    public void plus(int x, int y){
        System.out.printf("%d + %d = %d\n", x, y, x+y);
    }

    public void minus(int x, int y){
        System.out.printf("%d - %d = %d\n", x, y, x-y);
    }

    public void mul(int x, int y){
        System.out.printf("%d * %d = %d\n", x, y, x*y);
    }

    public void div(int x, int y){
        System.out.printf("%d / %d = %d\n", x, y, x/y);
    }
}
