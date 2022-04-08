package com.dreampro.ws.mappers.impl;

import java.util.Date;

import com.dreampro.ws.dtos.user.UserIn;
import com.dreampro.ws.dtos.user.UserOut;
import com.dreampro.ws.entities.User;
import com.dreampro.ws.exceptions.ValueDuplicateException;
import com.dreampro.ws.mappers.ifc.UserMapper;
import com.dreampro.ws.repositories.UserRepository;
import com.dreampro.ws.utils.ErrorCodes;
import com.dreampro.ws.utils.Util;
import com.github.dozermapper.core.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

  @Autowired
  Mapper dozerMapper;

  @Autowired
  UserRepository repository;

  @Autowired
  Util util;

  @Override
  public User fromUserIn(UserIn userIn) {
    User user = dozerMapper.map(userIn, User.class);
    verifyUniqueConstraints(user);
    user.setPassword(user.getPassword().trim());
    user.setCreatedAt(new Date());
    user.setUuid(util.uuid());
    return user;
  }

  @Override
  public User fromUserUpdateIn(UserIn userIn) {
    User user = dozerMapper.map(userIn, User.class);
    verifyUniqueConstraints(user);
    user.setPassword(user.getPassword().trim());
    user.setUpdatedAt(new Date());
    return user;
  }

  @Override
  public UserOut toUserOut(User user) {
    UserOut userOut = dozerMapper.map(user, UserOut.class);
    return userOut;
  }

  private void verifyUniqueConstraints(User user) {
    User exists;

    if (user.getEmail() != null) {
      exists = repository.findByEmail(user.getEmail()).orElse(null);
      if (exists != null) {
        if (user.getId() != exists.getId()) {
          throw new ValueDuplicateException(ErrorCodes.EMAIL_DUPLICATE);
        }
      }
    }

  }
}
