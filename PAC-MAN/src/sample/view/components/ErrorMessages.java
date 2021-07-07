package sample.view.components;

public enum ErrorMessages {
    FILL_IN_ALL_PARTS("Please fill in all parts!"),
    TAKEN_USERNAME("This username has already been taken!"),
    TAKEN_NICKNAME("This nickname has already been taken!"),
    USER_NOT_IN_DATABASE("Username and password didn't match!"), //There is no player with this username in our database!
    WRONG_PASSWORD("Username and password didn't match!"), //Password was incorrect!
    OLD_PASS_WRONG("Current password is incorrect!"),
    NEW_PASS_IS_OLD_PASS("Your new password can't be your current password!"),
    NEW_NICK_IS_OLD_NICK("Your new nickname can't be your current nickname!"),
    INVALID_TOKEN("Invalid Token\n Can't allow access!"),
    SUCCESS("Success"),
    UNABLE_TO_SEND_TO_SERVER("Couldn't connect to the server!");

    private final String errorMessage;
    ErrorMessages(String errorMessage) {this.errorMessage = errorMessage;}
    public String getErrorMessage() {return errorMessage;}
    public static ErrorMessages getError(String error) {
        if (error.equals("FILL_IN_ALL_PARTS"))
            return FILL_IN_ALL_PARTS;
        if (error.equals("TAKEN_USERNAME"))
            return TAKEN_USERNAME;
        if (error.equals("TAKEN_NICKNAME"))
            return TAKEN_NICKNAME;
        if (error.equals("USER_NOT_IN_DATABASE"))
            return USER_NOT_IN_DATABASE;
        if (error.equals("WRONG_PASSWORD"))
            return WRONG_PASSWORD;
        if (error.equals("OLD_PASS_WRONG"))
            return OLD_PASS_WRONG;
        if (error.equals("NEW_PASS_IS_OLD_PASS"))
            return NEW_PASS_IS_OLD_PASS;
        if (error.equals("NEW_NICK_IS_OLD_NICK"))
            return NEW_NICK_IS_OLD_NICK;
        if (error.equals("INVALID_TOKEN"))
            return INVALID_TOKEN;
        if (error.equals("UNABLE_TO_SEND_TO_SERVER"))
            return UNABLE_TO_SEND_TO_SERVER;
        return SUCCESS;
    }
}
