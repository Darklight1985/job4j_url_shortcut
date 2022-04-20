package ru.job4j.shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public  static  Link of(String url, Resource resource) {
        Link link = new Link();
        link.url = url;
        link.resource = resource;
        return link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Link link = (Link) o;
        return Objects.equals(url, link.url) && Objects.equals(resource, link.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, resource);
    }

    @Override
    public String toString() {
        return "Link{"
                + "id=" + id
                + ", url='" + url + '\''
                + ", resource=" + resource
                + '}';
    }
}
