package com.luo.springcloudeurekaclientdraghook.entity;



import javax.persistence.*;
import java.io.Serializable;

//用户评论

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="openKey")
    private String key;//评论人的openId
    private String name;//评论人的昵称
    private String icon;//评论人的头像
    @Column(name = "replyName" )
    private String replyName;//被评论人的nickName
    private String replyKey;//被评论人的openId
    private String content;




    public Comment(String key, String name, String icon, String replyName, String replyKey, String content) {
        this.key = key;
        this.name = name;
        this.icon = icon;
        this.replyName = replyName;
        this.replyKey = replyKey;
        this.content = content;
    }

    public Comment() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyKey() {
        return replyKey;
    }

    public void setReplyKey(String replyKey) {
        this.replyKey = replyKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", replyName='" + replyName + '\'' +
                ", replyKey='" + replyKey + '\'' +
                ", content='" + content + '\'' +

                '}';
    }
}
