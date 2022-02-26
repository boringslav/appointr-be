package com.appointr.model;

public class UserImpl implements User {
    private final String id;
    private String email;
    private String username;
    private String password;

    UserImpl(String id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;

    }

    /**
     * @return the id of this user. The ID should not be blank or null
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * @return the email of this user. The email should not be blank or null
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the username of this user. The username should not be blank or null
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the password of the user. The password should not be blank or null
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @param currentPassword the current password of the user
     * @param newPassword     the new user password
     * @return true if the password is successfully changed
     */
    @Override
    public Boolean changePassword(String currentPassword, String newPassword) {
        if( this.password.equals(currentPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }
}
