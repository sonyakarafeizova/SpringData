package org.demo.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
public class User  extends BaseEntity{
   @Column(unique = true)
    private String username;
   @Column
   private int age;
   @OneToMany(mappedBy = "user")
   private Set<Account>accounts;

    public User() {
       this.accounts=new HashSet<>();
    }

    public User(String username, int age) {
        this.username = username;
        this.age=age;
        this.accounts=new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
