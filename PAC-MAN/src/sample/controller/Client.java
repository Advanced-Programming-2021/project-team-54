package sample.controller;

import sample.view.components.ErrorMessages;
import sample.view.MainMenu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    public enum ServerErrorType {
        NO_ERROR, NATIONAL_CODE_INVALID, STUDENT_NUMBER_INVALID, SERVER_CONNECTION_FAILED, UNKNOWN_ERROR
    }

    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    public static void setupConnection() {
        try {
            socket = new Socket("localhost", 12345);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ServerErrorType isNationalCodeAndStudentNumberValid(String nationalCode, String studentNumber) {
        try {
            dataOutputStream.writeUTF("isValid-" + nationalCode + "-" + studentNumber);
            dataOutputStream.flush();
            String result = dataInputStream.readUTF();
            switch (result) {
                case "Success":
                    return ServerErrorType.NO_ERROR;
                case "Error - The provided national code is invalid":
                    return ServerErrorType.NATIONAL_CODE_INVALID;
                case "Error - The provided student number is invalid":
                    return ServerErrorType.STUDENT_NUMBER_INVALID;
                default:
                    return ServerErrorType.UNKNOWN_ERROR;
            }
        } catch (IOException ignored) {
            return ServerErrorType.SERVER_CONNECTION_FAILED;
        }
    }


    public static ErrorMessages register(String username, String nickname, String password) {
        setupConnection();
        String text = "user create --username " + username + " --nickname " + nickname + " --password " + password;
        try {
            dataOutputStream.writeUTF(text);
            dataOutputStream.flush();
            return ErrorMessages.getError(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorMessages.UNABLE_TO_SEND_TO_SERVER;
    }

    public static ErrorMessages login(String username, String password) {
        setupConnection();
        String text = "user login --username " + username + " --password " + password;
        try {
            dataOutputStream.writeUTF(text);
            dataOutputStream.flush();
            String result = dataInputStream.readUTF();
            if (result.startsWith("SUCCESS")) {
                String[] arguments = result.split(" ");
                new MainMenu(arguments[1]);
                MainMenu.authToken = arguments[1];
                return ErrorMessages.SUCCESS;
            }
            return ErrorMessages.getError(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorMessages.UNABLE_TO_SEND_TO_SERVER;
    }

    public static void logout() {
        setupConnection();
        try {
            dataOutputStream.writeUTF("user logout --authToken " + MainMenu.authToken);
            dataOutputStream.flush();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }


    public static ErrorMessages changePassword(String currentPass, String newPass) {
        setupConnection();
        String text = "user changePassword --authToken " + MainMenu.authToken  +" --currentPass " + currentPass + " --newPassword " + newPass;
        try {
            dataOutputStream.writeUTF(text);
            dataOutputStream.flush();
            return ErrorMessages.getError(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorMessages.UNABLE_TO_SEND_TO_SERVER;
    }

    public static ErrorMessages changeNickname(String nickname) {
        setupConnection();
        String text = "user changeNickname --authToken " + MainMenu.authToken  +" --nickname " + nickname;
        try {
            dataOutputStream.writeUTF(text);
            dataOutputStream.flush();
            return ErrorMessages.getError(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorMessages.UNABLE_TO_SEND_TO_SERVER;
    }

    public static String getProfileInfo() {
        setupConnection();
        try {
            dataOutputStream.writeUTF("user get info --authToken " + MainMenu.authToken);
            dataOutputStream.flush();
            return dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Failed to connect to the server";
    }

    public static int getPictureID() {
        setupConnection();
        try {
            dataOutputStream.writeUTF("user get picture --authToken " + MainMenu.authToken);
            dataOutputStream.flush();
            return Integer.parseInt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void changePicture() {
        setupConnection();
        try {
            dataOutputStream.writeUTF("user change picture --authToken " + MainMenu.authToken);
            dataOutputStream.flush();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static ArrayList<String> getScoreBoard() {
        setupConnection();
        try {
            dataOutputStream.writeUTF("user get scoreboard --authToken " + MainMenu.authToken);
            dataOutputStream.flush();
            ArrayList<String> scoreboard = new ArrayList<>();
            String[] scoreboardArray = dataInputStream.readUTF().split("partioned");
            for (String part : scoreboardArray)
                scoreboard.add(part);
            return scoreboard;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
