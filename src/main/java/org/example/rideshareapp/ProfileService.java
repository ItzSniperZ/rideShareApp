package org.example.rideshareapp;

public class ProfileService {
    private boolean loggedIn = false;
    private String username = "test";
    private String password = "test";
    private long phoneNumber = 0L;

    public boolean login(String username, String password) {
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    public boolean logout() {
        this.loggedIn = false;
        return true;
    }

    public void updateUsername(String newUsername) {
        if (newUsername != null && !newUsername.isEmpty()) {
            this.username = newUsername;
        }
    }

    public void updatePassword(String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
        }
    }

    public void updatePhoneNumber(long newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void updateProfile(String username, String password, long phoneNumber) {
        if (username != null && !username.isEmpty()) updateUsername(username);
        if (password != null && !password.isEmpty()) updatePassword(password);
        if (phoneNumber != 0L) updatePhoneNumber(phoneNumber);
    }

    public void contactSupport(String reason, String description) {
        System.out.println("Support ticket -> " + reason + ": " + description);
    }
}
