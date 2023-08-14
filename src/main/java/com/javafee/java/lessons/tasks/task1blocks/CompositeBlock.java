package com.javafee.java.lessons.tasks.task1blocks;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

interface CompositeBlock extends Block {

    List<Block> getBlocks();

    @Override
    default Stream<Block> toStream() {
        return Stream.concat(
                Block.super.toStream(),
                getBlocks().stream().flatMap(Block::toStream)
        );
    }
}
