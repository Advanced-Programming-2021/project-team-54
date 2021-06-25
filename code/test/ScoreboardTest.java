import org.junit.jupiter.api.Test;

public class ScoreboardTest {
    @Test
    void scoreboardTest() {
        Scoreboard.scoreboardControl("show");
        Scoreboard.scoreboardControl("scoreboard show");
    }
}
