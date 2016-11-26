package sp;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.spbau.kaysin.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        File f1 = new File("one.txt");
        File f2 = new File("two.txt");
        try (OutputStream out1 = new FileOutputStream(f1); OutputStream out2 = new FileOutputStream(f2)) {
            Writer w1 = new OutputStreamWriter(out1);
            Writer w2 = new OutputStreamWriter(out2);
            w1.write("Hello1\n");
            w1.write("World1\n");

            w2.write("World2\n");
            w2.write("Hello2\n");

            w1.close();
            w2.close();

        } catch (Exception e) {
            fail();
        }

        try {
            assertEquals(Arrays.asList("World1", "World2"), findQuotes(Arrays.asList("one.txt", "two.txt"), "rld"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(piDividedBy4(), Math.PI/4, 1e-2);
    }

    @Test
    public void testFindPrinter() {
        assertEquals(
                "Kavinsky",
                findPrinter(
                        ImmutableMap.of(
                                "Kavinsky", Arrays.asList("I'm giving you a nightcall to tell you how I feel",
                                                          "I want to drive you through the night, down the hills"),

                                "Michael Stipe", Arrays.asList("That's me in the corner",
                                                               "That's me in the spotlight",
                                                                "Losing my religion"),

                                "Me", Arrays.asList("", "", "", "")
                        )
                )
        );
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(
                ImmutableMap.of(
                        "Bags_of_grass", 2,
                        "Pellets_of_mescaline", 75,
                        "Sheets_of_blotter_acid", 5),
                calculateGlobalOrder(Arrays.asList(
                        ImmutableMap.of(
                                "Bags_of_grass", 1,
                                "Pellets_of_mescaline", 20,
                                "Sheets_of_blotter_acid", 2
                        ),
                        ImmutableMap.of(
                                "Bags_of_grass", 1,
                                "Sheets_of_blotter_acid", 3
                        ),
                        ImmutableMap.of(
                                "Pellets_of_mescaline", 20
                        ),
                        ImmutableMap.of(
                                "Pellets_of_mescaline", 35
                        )
                ))
        );
    }
}