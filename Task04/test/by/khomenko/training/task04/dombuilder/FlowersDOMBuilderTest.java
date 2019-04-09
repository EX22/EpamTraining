package by.khomenko.training.task04.dombuilder;

import by.khomenko.training.task04.entity.Flower;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Date;
import java.util.Set;

import static org.testng.Assert.*;

public class FlowersDOMBuilderTest {

    private static final String FILE_PATH = "data/flowersTest.xml";

    Flower testFlower = new Flower("African-Violets", "mixed",
            "Africa",
            new Flower.VisualParameters(20, "blue", "green"),
            new Flower.GrowingTips(18, 30, "medium", 150),
            "stems", new Date(2019-03-31));

    @Test
    public void testBuildSetFlowers() throws FileNotFoundException {

        InputStream inputStream = new FileInputStream(new File(FILE_PATH));

        FlowersDOMBuilder domBuilder = new FlowersDOMBuilder();
        domBuilder.buildSetFlowers(inputStream);
        Set<Flower> flowerSet = domBuilder.getFlowers();

    }
}