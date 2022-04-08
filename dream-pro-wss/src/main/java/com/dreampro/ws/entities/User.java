/**
 * 
 */
package com.dreampro.ws.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User
 * 
 * @author Victorien KONGNUY
 *
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Getter
  @Setter
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Getter
  @Setter
  @Column(unique = true, length = 100)
  String uuid;

  @Getter
  @Setter
  @Column(unique = true, length = 100, nullable = true)
  String email;

  @Setter
  @Getter
  @JsonIgnore
  String password;

  @Getter
  @Setter
  Date createdAt;

  @Getter
  @Setter
  Date updatedAt;
}