package com.dreampro.ws.mappers.ifc;

import com.dreampro.ws.dtos.user.UserIn;
import com.dreampro.ws.dtos.user.UserOut;
import com.dreampro.ws.entities.User;

/**
 * 
 * This class is use to convert User entity to User DTOs and vice-versa.
 * 
 * @author Victorien KONGNUY on 07-06-2020.
 * 
 */
public interface UserMapper {

  /**
   * Convert UserIn request payload to a User entity object.
   * 
   * @param userIn The request payload (must be a UserIn instance) {@link UserIn}
   * 
   * @return User {@link User}
   */
  public abstract User fromUserIn(UserIn userIn);

  /**
   * Convert UserIn request payload to a User entity object.
   * 
   * @param userIn The request payload (must be a UserIn instance) {@link UserIn}
   * 
   * @return User {@link User}
   */
  public abstract User fromUserUpdateIn(UserIn userIn);

  /**
   * Convert User entity object to a UserOut response.
   * 
   * @param user The user entity instance {@link User}
   * 
   * @return UserOut {@link UserOut}
   */
  public abstract UserOut toUserOut(User user);

}
