package ru.job4j.shortcut.dto;

public class LinkDto {
   private String uri;

    public LinkDto() {
    }

    public LinkDto(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
