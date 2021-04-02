package io.corecode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity(name="writer")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private Integer writerId;

    @Column(name = "name")
    private String name;


    // ***********************************

    @OneToMany(mappedBy="writer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "writerId=" + writerId +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    // ***********************************

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    public Writer(){

    }

    public Writer(String name) {
        this.name = name;
    }

    public Writer(Integer writerId, String name) {
        this.writerId = writerId;
        this.name = name;
    }
}


