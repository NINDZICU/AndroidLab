package ru.kpfu.itis.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anatoly on 08.07.2017.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO
    )
    @NotNull
    @Column(
            name = "id", unique = true
    )
    private Integer id;
    @Column(name = "login", unique = true)
    @NotNull
    private String login;
    @Column
    @Length(min = 6)
    private String password;
    @Column(name = "name")
    @NotNull
    private String name;
    @Column
    @NotNull
    private String city;
    @Column(name = "level")
    private int level;
    @Column
    private int exp;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}
    )
    private Set<MyTasks> myTasks;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "all_tasks", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="task_id", referencedColumnName = "id")})
    private Set<Tasks> tasks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "friends", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="friend_id", referencedColumnName = "id")})
    private Set<User> friends;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_authorities", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<MyTasks> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(Set<MyTasks> myTasks) {
        this.myTasks = myTasks;
    }

    public Set<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Tasks> tasks) {
        this.tasks = tasks;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}
