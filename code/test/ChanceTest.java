import org.junit.jupiter.api.Test;

public class ChanceTest {
    @Test
    void chanceTest() {
        Chance.dice();
        Chance.coin();
        Chance.makeRandomNumber(12);
    }
}
