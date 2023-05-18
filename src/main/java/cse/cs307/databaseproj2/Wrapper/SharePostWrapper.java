package cse.cs307.databaseproj2.Wrapper;

public class SharePostWrapper {
    // 帖子id
    private long postId;
    // 收藏者id
    private long shareId;

    public SharePostWrapper(long postId, long shareId) {
        this.postId = postId;
        this.shareId = shareId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getShareId() {
        return shareId;
    }

    public void setShareId(long shareId) {
        this.shareId = shareId;
    }
}
