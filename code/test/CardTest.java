import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void cardTest() {
        Card.getCardByName("name");
        Monster.getCardByName("name");
        Monster.exportCard("name", "description");
        Spell.getCardByName("name");
        Spell.exportCard("name", "description");
        Trap.getCardByName("name");
        Trap.exportCard("name", "description");
    }
}
