package com.javafee.java.lessons.tasks.task1blocks;

import java.util.ArrayList;
import java.util.List;

public class BlockFactory {

    public static Block createBlock(String color, String material) {
        return new Block() {

            @Override
            public String getColor() {
                return color;
            }

            @Override
            public String getMaterial() {
                return material;
            }
        };
    }

    public static CompositeBlock createCompositeBlock(String color, String material) {
        return new CompositeBlock() {
            private final List<Block> blockList = new ArrayList<>();

            @Override
            public List<Block> getBlocks() {
                return blockList;
            }

            @Override
            public String getColor() {
                return color;
            }

            @Override
            public String getMaterial() {
                return material;
            }
        };
    }
}