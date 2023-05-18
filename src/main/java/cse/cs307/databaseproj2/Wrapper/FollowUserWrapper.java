package cse.cs307.databaseproj2.Wrapper;

public class FollowUserWrapper {
    private long userId;
    private long followigId;

    public FollowUserWrapper(long userId, long followigId) {
        this.userId = userId;
        this.followigId = followigId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFollowigId() {
        return followigId;
    }

    public void setFollowigId(long followigId) {
        this.followigId = followigId;
    }
}
