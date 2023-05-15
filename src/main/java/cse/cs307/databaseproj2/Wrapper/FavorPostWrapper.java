package cse.cs307.databaseproj2.Wrapper;

public class FavorPostWrapper {
    // 帖子id
    private long postId;
    // 收藏者id
    private String favorId;

    public FavorPostWrapper(long postId, String favorId) {
        this.postId = postId;
        this.favorId = favorId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getFavorId() {
        return favorId;
    }

    public void setFavorId(String favorId) {
        this.favorId = favorId;
    }
}
