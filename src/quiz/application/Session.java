package quiz.application;

public class Session {
    private static String loggedInUsername = "";
    private static String selectedMode = "Challenger"; // default
    private static int timerSeconds = 8 * 60; // 8 minutes default

    public static void setUsername(String username) {
        loggedInUsername = username;
    }

    public static String getUsername() {
        return loggedInUsername;
    }

    public static void clear() {
        loggedInUsername = "";
        selectedMode = "Challenger";
        timerSeconds = 8 * 60;
    }

    public static void setMode(String mode, int timer) {
        selectedMode = mode;
        timerSeconds = timer;
    }

    public static String getSelectedMode() {
        return selectedMode;
    }

    public static int getTimerSeconds() {
        return timerSeconds;
    }
}

