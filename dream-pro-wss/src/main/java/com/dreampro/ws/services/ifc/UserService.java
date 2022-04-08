package com.dreampro.ws.services.ifc;

import com.dreampro.ws.dtos.user.UserIn;
import com.dreampro.ws.dtos.user.UserOut;
import com.dreampro.ws.entities.User;
import com.dreampro.ws.exceptions.EntitiesNotFoundException;

/**
 * Performs actions related to users.
 * 
 * @author Victorien KONGNUY on 07-06-2020.
 * 
 */
public interface UserService {

  /**
   * Get a list of users from database with the option of showing inative or not.
   * 
   * @param showInactive Boolean
   * 
   * @return Iterable<UserOutSmall>
   */
  public abstract Iterable<UserOut> findAll(Boolean showInactive);

  /**
   * Get a user from the giving id.
   * 
   * @param id long
   * 
   * @return UserOut
   */
  public abstract UserOut findOne(long id);

  /**
   * Get a user from the giving id.
   * 
   * @param id long
   * @throws EntitiesNotFoundException
   * 
   * @return User
   */
  public abstract User find(long id) throws EntitiesNotFoundException;

  /**
   * Get a User from the giving user uuid.
   * 
   * @param id long
   * 
   * @return UserOut
   */
  public abstract UserOut findByUuid(String uuid);

  /**
   * Creates a new user from the giving payload. The payload should be of type
   * userIn.
   * 
   * @param userIn UserIn
   * @throws ValueDuplicateException
   * 
   * @return UserOut
   */
  public abstract UserOut create(UserIn userIn);

  /**
   * Update a user from the giving id.
   * 
   * @param id           long
   * @param userUpdateIn UserUpdateIn
   * @param user         User
   * 
   * @throws ValueDuplicateException
   * 
   * @return UserOut
   */
  public abstract UserOut update(long id, UserIn userUpdateIn, User user);

  /**
   * Delete a user from the giving id.
   * 
   * @param id long
   * @throws DeleteConstraintException
   * 
   * @return boolean
   */
  public abstract boolean remove(long id);

  /**
   * Count users.
   * 
   * @return Long
   */
  public abstract Long count();
}