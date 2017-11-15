package org.fornever.reflectfeature.repos;

import java.util.List;

import org.fornever.reflectfeature.models.User;

public interface UserRepository {

	public List<User> findUserByUsername(String username);

}
