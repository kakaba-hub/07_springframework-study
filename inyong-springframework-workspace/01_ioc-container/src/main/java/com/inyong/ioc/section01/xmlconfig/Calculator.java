package com.inyong.ioc.section01.xmlconfig;

public class Calculator {
    public void plus(int x, int y){
        System.out.printf("%d + %d = %d\n", x, y, x+y);
    }
    public void minus(int x, int y){
        System.out.printf("%d - %d = %d\n", x, y, x-y);
    }
    public void mul(int x, int y){
        System.out.printf("%d x %d = %d\n", x, y, x*y);
    }
    public void div(int x, int y){
        System.out.printf("%d / %d = %d\n", x, y, x/y);
    }
}
