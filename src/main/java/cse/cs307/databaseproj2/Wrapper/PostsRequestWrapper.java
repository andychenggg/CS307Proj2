package cse.cs307.databaseproj2.Wrapper;

public class PostsRequestWrapper {
    private long lastPostId;
    private int limit;

    public PostsRequestWrapper(long lastPostId, int limit) {
        this.lastPostId = lastPostId;
        this.limit = limit;
    }

    public long getLastPostId() {
        return lastPostId;
    }

    public void setLastPostId(long lastPostId) {
        this.lastPostId = lastPostId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
