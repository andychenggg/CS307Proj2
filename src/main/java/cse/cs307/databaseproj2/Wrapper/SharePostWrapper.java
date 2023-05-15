package cse.cs307.databaseproj2.Wrapper;

public class SharePostWrapper {
    // 帖子id
    private long postId;
    // 收藏者id
    private String shareId;

    public SharePostWrapper(long postId, String shareId) {
        this.postId = postId;
        this.shareId = shareId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }
}
