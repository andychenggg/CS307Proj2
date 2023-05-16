package cse.cs307.databaseproj2.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import java.util.List;

public class Replies {
    private long replyId;
    private long toReplyId;
    private long toPostId;
    private String content;
    private int stars;
    private String authorId;
    private String postId;
    private boolean isAnonymous;

    @TableField(exist = false)
    private String authorName;

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
    }

    public long getToReplyId() {
        return toReplyId;
    }

    public void setToReplyId(long toReplyId) {
        this.toReplyId = toReplyId;
    }

    public long getToPostId() {
        return toPostId;
    }

    public void setToPostId(long toPostId) {
        this.toPostId = toPostId;
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

    public Replies() {
    }

    public Replies(long replyId, long toReplyId, long toPostId, String content, int stars,
                   String authorId, String postId) {
        this.replyId = replyId;
        this.toReplyId = toReplyId;
        this.toPostId = toPostId;
        this.content = content;
        this.stars = stars;
        this.authorId = authorId;
        this.postId = postId;
    }
}
