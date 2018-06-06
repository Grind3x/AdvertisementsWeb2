package com.gmail.grind3x.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String email;
    @Column
    private String phone;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Advertisement> advertisements = new ArrayList<>();

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public void addAdvertisement(Advertisement advertisement) {
        if (advertisement != null) {
            advertisement.setAuthor(this);
            advertisements.add(advertisement);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(email, author.email) &&
                Objects.equals(phone, author.phone) &&
                Objects.equals(advertisements, author.advertisements);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, email, phone, advertisements);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", advertisements=" + advertisements +
                '}';
    }
}
