package com.javafee.java.lessons.tasks.task1blocks;

import java.util.List;

public class CompositeBlockImpl implements CompositeBlock{

    private List <Block> blocks;

    private String color;
    private String material;

    public CompositeBlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
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
