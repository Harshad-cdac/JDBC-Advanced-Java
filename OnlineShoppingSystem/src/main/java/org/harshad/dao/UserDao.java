package org.harshad.dao;

import org.harshad.entity.User;
import org.harshad.exceptions.UserNotFoundException;
import org.harshad.exceptions.UserNotInsertedException;

public interface UserDao {
	boolean isAuthenticated(User obj) throws UserNotFoundException;
	boolean registerUser(User obj) throws UserNotInsertedException;
}
