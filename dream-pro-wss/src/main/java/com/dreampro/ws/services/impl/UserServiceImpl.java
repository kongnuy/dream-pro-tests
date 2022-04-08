package com.dreampro.ws.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.dreampro.ws.dtos.user.UserIn;
import com.dreampro.ws.dtos.user.UserOut;
import com.dreampro.ws.entities.User;
import com.dreampro.ws.exceptions.EntitiesNotFoundException;
import com.dreampro.ws.mappers.ifc.UserMapper;
import com.dreampro.ws.repositories.UserRepository;
import com.dreampro.ws.services.ifc.UserService;
import com.dreampro.ws.utils.ErrorCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

  Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  UserRepository repository;

  @Autowired
  UserMapper mapper;

  @Override
  public Iterable<UserOut> findAll(Boolean showInactive) {
    final List<UserOut> out = new ArrayList<>();
    repository.findAll().forEach(c -> {
      out.add(mapper.toUserOut(c));
    });
    return out;
  }

  @Override
  public UserOut findOne(long id) {
    return mapper.toUserOut(find(id));
  }

  @Override
  public User find(long id) throws EntitiesNotFoundException {
    User user = repository.findById(id).orElse(null);
    if (user == null)
      throw new EntitiesNotFoundException(ErrorCodes.USER_NOT_FOUND);
    return user;
  }

  @Override
  public UserOut findByUuid(String uuid) {
    return null;
  }

  @Override
  public UserOut create(UserIn userIn) {
    User user = mapper.fromUserIn(userIn);
    repository.save(user);
    return mapper.toUserOut(user);
  }

  @Override
  public UserOut update(long id, UserIn userUpdateIn, User user) {
    if (user != null)
      return mapper.toUserOut(repository.save(user));
    User usr = mapper.fromUserUpdateIn(userUpdateIn);
    repository.save(usr);
    return mapper.toUserOut(usr);
  }

  @Override
  public boolean remove(long id) {
    return false;
  }

  @Override
  public Long count() {
    return repository.count();
  }

}
