package com.hormiga6.androidtesttoolpractice.MemoryLeak;

public class MemoryLeakModel {
    static Box contatiner;

    public class Cat {
    }

    public class Box {
        Cat hiddenCat;
    }
}
