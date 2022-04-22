package ru.job4j.shortcut.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String site;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public static Resource of(String site, User user) {
        Resource resource = new Resource();
        resource.site = site;
        resource.user = user;
        return resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resource resource = (Resource) o;
        return Objects.equals(site, resource.site) && Objects.equals(user, resource.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site, user);
    }

    @Override
    public String toString() {
        return "Resource{"
                + "id=" + id
                + ", site='" + site + '\''
                + ", user=" + user
                + '}';
    }
}
