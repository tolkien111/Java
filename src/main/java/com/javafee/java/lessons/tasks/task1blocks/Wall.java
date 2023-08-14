package com.javafee.java.lessons.tasks.task1blocks;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {

    private final List<Block> blocks = new ArrayList<>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findByPredicate(x -> x.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
                return findByPredicate(y -> y.getMaterial().equals(material))
                .toList();
    }

    private Stream<Block> findByPredicate(Predicate<Block> predicate) {
        return blocks.stream()
                .flatMap(Block::toStream)
                .filter(predicate);
    }


    @Override
    public int count() {
        return (int) blocks.stream()
                .flatMap(Block::toStream)
                .count();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    public List<Block> getBlockList() {
        return Collections.unmodifiableList(blocks);
    }

    @Override
    public String toString() {
        return "Wall{" +
                "blocks=" + blocks +
                '}';
    }
}

