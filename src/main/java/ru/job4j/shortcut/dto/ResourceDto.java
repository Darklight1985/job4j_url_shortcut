package ru.job4j.shortcut.dto;

public class ResourceDto {
    private String site;

    public ResourceDto(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
