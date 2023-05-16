package cse.cs307.databaseproj2.Wrapper;

public class FollowUserWrapper {
    private String userId;
    private String followigId;

    public FollowUserWrapper(String userId, String followigId) {
        this.userId = userId;
        this.followigId = followigId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowigId() {
        return followigId;
    }

    public void setFollowigId(String followigId) {
        this.followigId = followigId;
    }
}
