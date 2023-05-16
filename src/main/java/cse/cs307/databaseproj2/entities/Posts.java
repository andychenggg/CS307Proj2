package cse.cs307.databaseproj2.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Table;

public class Posts {
    private long postId;
    private String title;
    private String content;
    private LocalDateTime postingTime;
    private String authorId;
    private String city;
    private String senderId;
    private boolean anonymous;
    @TableField(exist = false)
    private String country;
    @TableField(exist = false)
    private List<String> postCategories;
    @TableField(exist = false)
    private String authorName;

    @TableField(exist = false)
    private String senderName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(LocalDateTime postingTime) {
        this.postingTime = postingTime;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public List<String> getPostCategories() {
        return postCategories;
    }

    public void setPostCategories(List<String> postCategories) {
        this.postCategories = postCategories;
    }


    @Override
    public String toString() {
        return "Posts{" +
            "postId=" + postId +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", postingTime=" + postingTime +
            ", authorId='" + authorId + '\'' +
            ", city='" + city + '\'' +
            ", country='" + country + '\'' +
            ", postCategories=" + postCategories +
            '}';
    }
}




