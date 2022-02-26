package com.appointr.model;

public interface User {
    /**
     * @return the id of this user. The ID should not be blank or null
     */
    String getID();

    /**
     * @return the email of this user. The email should not be blank or null
     */
    String getEmail();

    /**
     * @return the username of this user. The username should not be blank or null
     */
    String getUsername();


    /**
     * @return the password of the user. The password should not be blank or null
     */
    String getPassword();

    /**
     * @param currentPassword the current password of the user
     * @param newPassword     the new user password
     * @return True if the password is successfully changed
     */
    Boolean changePassword(String currentPassword, String newPassword);

}
