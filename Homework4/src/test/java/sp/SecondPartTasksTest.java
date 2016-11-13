package sp;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.spbau.kaysin.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        assertEquals(
                Arrays.asList("One", "Three", "Five"),
                findQuotes(Arrays.asList("One", "Two", "Three", "Four", "Five"), "e")
        );

        assertEquals(
                Arrays.asList("One", "Two", "Three"),
                findQuotes(Arrays.asList("One", "Two", "Three"), "")
        );
    }

    @Test
    public void testPiDividedBy4() {
        final double epsilon = 1e-2;
        assertTrue(Math.abs(piDividedBy4() - Math.PI / 4.0) < epsilon);
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