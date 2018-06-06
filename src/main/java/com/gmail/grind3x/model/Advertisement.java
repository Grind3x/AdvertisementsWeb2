package com.gmail.grind3x.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Advertisements")
public class Advertisement {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 350)
    private String shortText;
    @Column(nullable = false, length = 1000)
    private String text;
    @Column(nullable = false)
    private Date createDate;
    @Column(nullable = false)
    private Integer cost;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Advertisement() {
    }

    public Advertisement(String title, String shortText, String text, Author author, Image image, Category category, Integer cost) {
        this.title = title;
        this.shortText = shortText;
        this.text = text;
        this.createDate = new Date();
        this.author = author;
        this.image = image;
        this.category = category;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(shortText, that.shortText) &&
                Objects.equals(text, that.text) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(author, that.author) &&
                Objects.equals(image, that.image) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, shortText, text, createDate, cost, author, image, category);
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortText='" + shortText + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", cost=" + cost +
                ", author=" + author +
                ", image=" + image +
                ", category=" + category +
                '}';
    }
}
