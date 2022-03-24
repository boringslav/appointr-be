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
     * @return the first name of this user. The first name should not be blank or null
     */
    String getFirstName();

    /**
     * @return  the last name of this user. The last name should not be blank or null
     */
    String getLastName();

    /**
     * @return the full name of this user. The full name should not be blank or null
     */
    String getFullName();

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
