package com.example.design.service.impl;

import com.example.design.constant.Role;
import com.example.design.mapper.UserMapper;
import com.example.design.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 提供用户信息的服务 Created by lxh on 4/14/16.
 */
@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper mapper;

  public List<User> all() {
    return mapper.all();
  }

  public User id(long id) {
    return mapper.id(id);
  }

  public int addUser(User user) {
    return mapper.add(user);
  }

  public int updateInfo(User user) {
    return mapper.update(user);
  }

  public User getByAccountName(String name) {
    return mapper.selectByAccountName(name);
  }

  public int removeById(int id) {
    return mapper.delete(id);
  }

  public int removeByPhone(String phone) {
    return mapper.deleteByAccountName(phone);
  }

  public Role getRole(String name) {
    return mapper.getRole(name);
  }

  public boolean validateUser(String name, String password) {
    User user = mapper.selectByAccountName(name);
    return Objects.equals(user.getPassword(), password);
  }

  public boolean hasUser(String username) {
    User user = mapper.selectByAccountName(username);
    return user != null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getByAccountName(username);
    if (user == null) {
      throw new UsernameNotFoundException("");
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
    System.out.println(1 + user.getRole().name());
    return new org.springframework.security.core.userdetails.User(username,
            user.getPassword(), authorities);
  }
}
