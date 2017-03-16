package net.carlosu.ws.rest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "email" })
public class User{
  private String email;
  private String name;
}
