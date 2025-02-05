package com.example.springbootboard.domain;

import com.example.springbootboard.domain.common.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="post")
public class Post extends BaseTimeEntity {

    // Default Constructor
    protected Post(){

    }

    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    // 연관관계 편의 메소드
    public void setCreatedBy(User createdBy) {
        if(Objects.nonNull(this.createdBy)) {
            this.createdBy.getPosts().remove(this);
        }
        this.createdBy = createdBy;
        createdBy.getPosts().add(this);
    }

    // GETTER

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    // SETTER

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
