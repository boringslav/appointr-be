package com.appointr.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserImpl implements User {
    private final String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;


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
     * @return the first name of this user. The first name should not be blank or null
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return the last name of this user. The last name should not be blank or null
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @return the full name of this user. The full name should not be blank or null
     */
    @Override
    public String getFullName() {
        return this.firstName + " " + this.lastName;
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
