package com.javafee.java.lessons.tasks.task1blocks;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private List<Block> blocks;

    public Optional<Block> findBlockByColor(String color) {
        return findByPredicateC(block -> block.getColor().equals(color));
    }

    public List<Block> findBlocksByMaterial(String material) {
        return findByPredicateM(n -> material.equals(n.getMaterial()));
    }

    private List<Block> findByPredicateM(Predicate<Block> predicate) {
        return blocks.stream()
                .map(f -> f.getMatchingObject(predicate))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Block> findByPredicateC(Predicate<Block> predicate) {
        return blocks.stream()
                .map(f -> f.getMatchingObject(predicate))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public int count() {
        return blocks.stream()
                .mapToInt(Block::getChildrenCount)
                .sum();
    }
}
