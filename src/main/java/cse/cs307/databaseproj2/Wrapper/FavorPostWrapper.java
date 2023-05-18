package cse.cs307.databaseproj2.Wrapper;

public class FavorPostWrapper {
    // 帖子id
    private long postId;
    // 收藏者id
    private long favorId;

    public FavorPostWrapper(long postId, long favorId) {
        this.postId = postId;
        this.favorId = favorId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getFavorId() {
        return favorId;
    }

    public void setFavorId(long favorId) {
        this.favorId = favorId;
    }
}
