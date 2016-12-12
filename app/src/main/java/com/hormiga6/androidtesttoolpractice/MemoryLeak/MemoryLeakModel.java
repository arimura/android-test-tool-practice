package com.hormiga6.androidtesttoolpractice.MemoryLeak;

public class MemoryLeakModel {
    static Box contatiner;

    static public class Cat {
    }

    static public class Box {
        Cat hiddenCat;
    }
}
