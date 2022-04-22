package ru.job4j.shortcut.dto;

public class StatisticDto {
    private String uri;
    private int total;

    public StatisticDto() {
    }

    public StatisticDto(String uri, int total) {
        this.uri = uri;
        this.total = total;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
