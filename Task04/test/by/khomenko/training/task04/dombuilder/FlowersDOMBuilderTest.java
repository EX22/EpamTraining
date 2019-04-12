package by.khomenko.training.task04.dombuilder;

import by.khomenko.training.task04.entity.Flower;
import org.testng.annotations.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class FlowersDOMBuilderTest {

    private static final String FILE_PATH = "data/flowersTest.xml";

    @Test
    public void testBuildSetFlowers() throws FileNotFoundException, ParseException {

        Flower testFlowerOne = new Flower("Amazon-Lilies", "silt", "North-America",
                new Flower.VisualParameters(25, "white", "green"),
                new Flower.GrowingTips(18, 30, "high", 100),
                "roots", new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-20"));

        Flower testFlowerTwo = new Flower("African-Violets", "mixed", "Africa",
                new Flower.VisualParameters(20, "blue", "green"),
                new Flower.GrowingTips(18, 30, "medium", 150),
                "stems", new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-31"));

        Flower testFlowerThree = new Flower("Chinese-Hibiscus", "sandy", "Asia",
                new Flower.VisualParameters(20, "yellow", "green"),
                new Flower.GrowingTips(20, 60, "high", 400),
                "roots", new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-04"));

        Set<Flower> testSet = new HashSet<>();
        testSet.add(testFlowerOne);
        testSet.add(testFlowerTwo);
        testSet.add(testFlowerThree);

        InputStream inputStream = new FileInputStream(new File(FILE_PATH));

        FlowersDOMBuilder domBuilder = new FlowersDOMBuilder();
        domBuilder.buildSetFlowers(inputStream);
        Set<Flower> flowerSet = domBuilder.getFlowers();
        assertEquals(flowerSet, testSet);

    }
}