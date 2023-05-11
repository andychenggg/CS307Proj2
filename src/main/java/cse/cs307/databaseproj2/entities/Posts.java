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
    @TableField(exist = false)
    private String country;
    @TableField(exist = false)
    private List<FirstReplies> firstRepliesList;
    @TableField(exist = false)
    private List<String> postCategories;
    @TableField(exist = false)
    private List<Users> favorites;
    @TableField(exist = false)
    private List<Users> shares;
    @TableField(exist = false)
    private List<Users> likes;

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

    public List<FirstReplies> getFirstRepliesList() {
        return firstRepliesList;
    }

    public void setFirstRepliesList(
        List<FirstReplies> firstRepliesList) {
        this.firstRepliesList = firstRepliesList;
    }

    public List<String> getPostCategories() {
        return postCategories;
    }

    public void setPostCategories(List<String> postCategories) {
        this.postCategories = postCategories;
    }

    public List<Users> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Users> favorites) {
        this.favorites = favorites;
    }

    public List<Users> getShares() {
        return shares;
    }

    public void setShares(List<Users> shares) {
        this.shares = shares;
    }

    public List<Users> getLikes() {
        return likes;
    }

    public void setLikes(List<Users> likes) {
        this.likes = likes;
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
            ", firstRepliesList=" + firstRepliesList +
            ", postCategories=" + postCategories +
            ", favorites=" + favorites +
            ", shares=" + shares +
            ", likes=" + likes +
            '}';
    }
}




