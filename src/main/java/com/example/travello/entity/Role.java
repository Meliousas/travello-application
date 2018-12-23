package com.example.travello.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role {

   @Id
   @GeneratedValue
   Long id;

   String name;

   public Role(String name) {this.name=name;}
}
