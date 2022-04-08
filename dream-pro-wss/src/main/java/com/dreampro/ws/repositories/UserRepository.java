/**
 * 
 */
package com.dreampro.ws.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.dreampro.ws.entities.User;

/**
 * Spring data repository for User entities.
 * 
 * @author Victorien KONGNUY
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByUuid(String userId);

}
