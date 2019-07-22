package com.catsserver.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cat {

    @NotNull
    private String name;
    @NotNull
    private String color;
    @Min(value = 0, message = "Параметр tail_length должен быть 0 или больше")
    @NotNull(message = "Параметр tail_length не должен быть пустым")
    private int tail_length;
    @Min(value = 0, message = "Параметр whiskers_length должен быть 0 или больше")
    @NotNull(message = "Параметр whiskers_length не должен быть пустым")
    private int whiskers_length;

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTail_length() {
        return tail_length;
    }

    public void setTail_length(int tail_length) {
        this.tail_length = tail_length;
    }

    public int getWhiskers_length() {
        return whiskers_length;
    }

    public void setWhiskers_length(int whiskers_length) {
        this.whiskers_length = whiskers_length;
    }
}
