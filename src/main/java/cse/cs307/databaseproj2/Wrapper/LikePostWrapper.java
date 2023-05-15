package cse.cs307.databaseproj2.Wrapper;

public class LikePostWrapper {
    // 帖子id
    private long postId;
    // 点赞者id
    private String likerId;

    public LikePostWrapper(long postId, String likerId) {
        this.postId = postId;
        this.likerId = likerId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getLikerId() {
        return likerId;
    }

    public void setLikerId(String likerId) {
        this.likerId = likerId;
    }
}
