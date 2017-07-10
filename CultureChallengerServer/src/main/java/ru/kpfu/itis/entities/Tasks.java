package ru.kpfu.itis.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anatoly on 08.07.2017.
 */
@Entity
public class Tasks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO
    )
    @NotNull
    @Column(
            name = "id", unique = true
    )
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String dateFinish;
    @Column
    private String difficulty;
    @Column
    private String customer;
    @Column
    private String address;
    @Column
    private String latitude;
    @Column
    private String longitude;


    @ManyToMany(fetch = FetchType.LAZY ,mappedBy = "tasks")
    private Set<User> users;

    public Tasks(){}

    public Tasks(String name, String description, String dateFinish, String difficulty, String customer) {
        this.name = name;
        this.description = description;
        this.dateFinish = dateFinish;
        this.difficulty = difficulty;
        this.customer = customer;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
