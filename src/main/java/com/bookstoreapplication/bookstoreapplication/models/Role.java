package com.bookstoreapplication.bookstoreapplication.models;

import javax.persistence.*;
import lombok.*;
@Entity
@Table(name="roles")
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class Role {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated(EnumType.STRING)
  private RoleEnum name;
}
