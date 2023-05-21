package cse.cs307.databaseproj2.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import java.util.List;

public class Replies {
    private long replyId;
    private Long toReplyId;
    private long toPostId;
    private String content;
    private int stars;
    private long authorId;
    private boolean isAnonymous;

    @TableField(exist = false)
    private String authorName;

    @TableField(exist = false)
    private String toUserId;

    @TableField(exist = false)
    private String toUserName;

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Replies() {
    }

    public Replies(long replyId, long toReplyId, long toPostId, String content, int stars,
                   long authorId) {
        this.replyId = replyId;
        this.toReplyId = toReplyId;
        this.toPostId = toPostId;
        this.content = content;
        this.stars = stars;
        this.authorId = authorId;
    }
}
