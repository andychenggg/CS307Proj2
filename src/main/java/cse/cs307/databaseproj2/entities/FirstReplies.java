package cse.cs307.databaseproj2.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import java.util.List;

public class FirstReplies {
    private long firstReplyId;
    private String content;
    private int stars;
    private String authorId;
    private String postId;
    @TableField(exist = false)
    private List<SecondReplies> secondRepliesList;

    public long getFirstReplyId() {
        return firstReplyId;
    }

    public void setFirstReplyId(long firstReplyId) {
        this.firstReplyId = firstReplyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<SecondReplies> getSecondRepliesList() {
        return secondRepliesList;
    }

    public void setSecondRepliesList(
        List<SecondReplies> secondRepliesList) {
        this.secondRepliesList = secondRepliesList;
    }

    @Override
    public String toString() {
        return "FirstReplies{" +
            "firstReplyId=" + firstReplyId +
            ", content='" + content + '\'' +
            ", stars=" + stars +
            ", authorId='" + authorId + '\'' +
            ", postId='" + postId + '\'' +
            ", secondRepliesList=" + secondRepliesList +
            '}';
    }
}
