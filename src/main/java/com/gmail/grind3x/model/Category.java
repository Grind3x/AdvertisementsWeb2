package com.gmail.grind3x.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Advertisement> advertisements;

    public Category() {
    }

    public Category(String name, Image image) {
        this.name = name;
        this.image = image;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void addAdvertisement(Advertisement advertisement) {
        advertisement.setCategory(this);
        advertisements.add(advertisement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(image, category.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, image);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
