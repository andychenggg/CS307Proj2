package cse.cs307.databaseproj2.entities;

public class SecondReplies {
    private long secondReplyId;
    private String content;
    private int stars;
    private String authorId;
    private String firstReplyId;

    public long getSecondReplyId() {
        return secondReplyId;
    }

    public void setSecondReplyId(long secondReplyId) {
        this.secondReplyId = secondReplyId;
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

    public String getFirstReplyId() {
        return firstReplyId;
    }

    public void setFirstReplyId(String firstReplyId) {
        this.firstReplyId = firstReplyId;
    }

    @Override
    public String toString() {
        return "SecondReplies{" +
            "secondReplyId=" + secondReplyId +
            ", content='" + content + '\'' +
            ", stars=" + stars +
            ", authorId='" + authorId + '\'' +
            ", firstReplyId='" + firstReplyId + '\'' +
            '}';
    }
}
