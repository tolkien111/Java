package com.javafee.java.lessons.tasks.task1blocks;

import java.util.Optional;
import java.util.function.Predicate;

interface Block {

    String getColor();

    String getMaterial();

    default int getChildrenCount() {
        return 1;
    }

    default Optional<Block> getMatchingObject(Predicate<Block> predicate) {
        if (predicate.test(this)) {
            return Optional.of(this);
        }
        return Optional.empty();
    }
}
