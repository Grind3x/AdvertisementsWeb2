package com.gmail.grind3x.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Images")
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "image")
    private Advertisement advertisement;

    public Image() {
    }

    public Image(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(name, image.name) &&
                Objects.equals(path, image.path) &&
                Objects.equals(advertisement, image.advertisement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, path, advertisement);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", advertisement=" + advertisement +
                '}';
    }


}
