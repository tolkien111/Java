package com.javafee.java.lessons.tasks.task1blocks;

import java.util.List;

interface CompositeBlock extends Block {

    List<Block> getBlocks();

    default int getChildrenCount() {
        return getBlocks().stream()
                .mapToInt(Block::getChildrenCount)
                .sum();
    }
}
