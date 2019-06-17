package com.luo.springcloudeurekaclientnotice.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(indexes = {@Index(name = "PERSON_INDX_0", columnList = "count") })
public class Document implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String fileUrl;
    private String ImgUrl;
    private Integer count;


    public Document() {
    }

    public void addCount()
    {
        count++;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
