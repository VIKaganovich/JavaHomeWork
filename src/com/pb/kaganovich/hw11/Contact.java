package com.pb.kaganovich.hw11;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс контакт
 */
public class Contact {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("Phone numbers")
    private List<String> phone;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("Birthday")
    private LocalDate dateOfBirth;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("Last date of modification")
    private LocalDateTime lastTimeModified;

    public Contact() {
    }

    public Contact(String name, String address, List<String> phone, LocalDate dateOfBirth) {
        this.name = name;
        this.address = address;
        this.phone = new ArrayList<>();
        this.phone.addAll(phone);
        this.dateOfBirth = dateOfBirth;
        lastTimeModified = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getLastTimeModified() {
        return lastTimeModified;
    }

    public void setLastTimeModified(LocalDateTime lastTimeModified) {
        this.lastTimeModified = lastTimeModified;
    }

    @Override
    public String toString() {
        return name + " (" + address + ", " + dateOfBirth + ")\n"
                + "Телефоны: " + phone + "\n" +
                "Дата последней модификации: " + lastTimeModified;
    }

}
