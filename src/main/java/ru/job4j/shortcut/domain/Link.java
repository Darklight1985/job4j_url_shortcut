package ru.job4j.shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uri;

    private String code;

    private int count;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public  static  Link of(String url, String code, Resource resource) {
        Link link = new Link();
        link.uri = url;
        link.code = code;
        link.resource = resource;
        link.count = 0;
        return link;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUrl(String uri) {
        this.uri = uri;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return count == link.count && Objects.equals(uri, link.uri)
                && Objects.equals(code, link.code) && Objects.equals(resource, link.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, code, count, resource);
    }

    @Override
    public String toString() {
        return "Link{"
                + "id=" + id
                + ", url='" + uri + '\''
                + ", code='" + code + '\''
                + '}';
    }
}
