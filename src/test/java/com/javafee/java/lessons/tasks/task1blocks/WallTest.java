package com.javafee.java.lessons.tasks.task1blocks;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private static final Block BLOCK01 = BlockFactory.createBlock("color1", "material3");
    private static final Block BLOCK02 = BlockFactory.createBlock("color2", "material2");
    private static final CompositeBlock COMPOSITE_BLOCK03 = BlockFactory.createCompositeBlock("color3", "material3");
    private static final Block BLOCK04 = BlockFactory.createBlock("color2", "material4");
    private static final CompositeBlock COMPOSITE_BLOCK05 = BlockFactory.createCompositeBlock("color4", "material3");
    private static final Block BLOCK06 = BlockFactory.createBlock("color6", "material3");

    private Wall filledWall;

    @BeforeAll
    static void setUpBlocks() {
        COMPOSITE_BLOCK05.getBlocks().add(BLOCK06);

        COMPOSITE_BLOCK03.getBlocks().add(COMPOSITE_BLOCK05);
        COMPOSITE_BLOCK03.getBlocks().add(BLOCK04);
    }

    @BeforeEach
    void setUp(){
        filledWall = new Wall();
        filledWall.addBlock(BLOCK01);
        filledWall.addBlock(BLOCK02);
        filledWall.addBlock(COMPOSITE_BLOCK03);

    }

    @Test
    void shouldFindAnyBlockOfColor() {
        //GIVEN
        String colorExpected = "color6";
        //WHEN & THEN
        Optional<Block> result = filledWall.findBlockByColor(colorExpected);
        assertTrue(result.isPresent());
        assertEquals(colorExpected, result.get().getColor());

    }

    @Test
    void shouldFindAllBlocksOfMaterial(){
        //GIVEN
        String materialExpected = "material3";
        //WHEN & THEN
        List<Block> result = filledWall.findBlocksByMaterial(materialExpected);
        assertEquals(4, result.size());
    }

    @Test
    void shouldCountAllBlocksInTheWall() {
        //GIVEN
        int amountExpected = 6;
        //WHEN & THEN
        int result = filledWall.count();
        assertEquals(amountExpected, result);
    }

}


