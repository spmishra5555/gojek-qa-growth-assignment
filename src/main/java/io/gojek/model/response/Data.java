package io.gojek.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Data implements Comparable<Data> {

    private Integer id;

    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(id, data.id) &&
                Objects.equals(email, data.email) &&
                Objects.equals(firstName, data.firstName) &&
                Objects.equals(lastName, data.lastName) &&
                Objects.equals(avatar, data.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, avatar);
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    @Override
    public int compareTo(Data o) {
        int compare = this.id.compareTo(o.id);
        if (compare == 0) {
            compare = this.avatar.compareTo(o.avatar);
            if (compare == 0) {
                compare = this.email.compareTo(o.email);
                if (compare == 0) {
                    compare = this.firstName.compareTo(o.firstName);
                    if (compare == 0) {
                        compare = this.lastName.compareTo(o.lastName);
                    }
                }
            }
        }
        return compare;
    }
}
