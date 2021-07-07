package sample.model.enums;

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
    SUCCESS("Success");

    private final String errorMessage;
    ErrorMessages(String errorMessage) {this.errorMessage = errorMessage;}
    public String getErrorMessage() {return errorMessage;}
}
