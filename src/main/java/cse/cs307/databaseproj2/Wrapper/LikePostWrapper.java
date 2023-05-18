package cse.cs307.databaseproj2.Wrapper;

public class LikePostWrapper {
    // 帖子id
    private long postId;
    // 点赞者id
    private long likerId;

    public LikePostWrapper(long postId, long likerId) {
        this.postId = postId;
        this.likerId = likerId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getLikerId() {
        return likerId;
    }

    public void setLikerId(long likerId) {
        this.likerId = likerId;
    }
}
