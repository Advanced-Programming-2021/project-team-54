package sample.model.enums;

public enum Scenes {
    MAIN("welcome"),
    SIGNUP("register"),
    LOGIN("login"),
    SCOREBOARD("scoreboard"),
    MAIN_MENU("mainMenu"),
    PROFILE("profile"),
    CHANGE_NICKNAME("changeNickname"),
    CHANGE_PASSWORD("changePassword"),
    PRE_GAME_SETTINGS("preGameSettings"),
    GAME("game"),
    GAME_OVER("gameOver");

    private final String resourceFilename;

    Scenes(String resourceFilename) {
        this.resourceFilename = "/fxml/" + resourceFilename + ".fxml";
    }

    public String getResourceFilename() {
        return resourceFilename;
    }
}
