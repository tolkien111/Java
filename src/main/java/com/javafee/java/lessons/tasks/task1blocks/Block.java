package com.javafee.java.lessons.tasks.task1blocks;

import java.util.stream.Stream;

interface Block {

    String getColor();
    String getMaterial();

    default Stream<Block> toStream() {
        return Stream.of(this);
    }
}
