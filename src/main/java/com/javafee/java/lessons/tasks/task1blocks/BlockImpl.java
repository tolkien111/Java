package com.javafee.java.lessons.tasks.task1blocks;

public class BlockImpl implements  Block{

    private String color;
    private String material;

    public BlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }
}
