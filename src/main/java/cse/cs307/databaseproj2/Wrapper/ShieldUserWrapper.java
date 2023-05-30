package cse.cs307.databaseproj2.Wrapper;

public class ShieldUserWrapper {
    private long userId;
    private long shieldId;

    public ShieldUserWrapper(long userId, long shieldId) {
        this.userId = userId;
        this.shieldId = shieldId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getShieldId() {
        return shieldId;
    }

    public void setShieldId(long shieldId) {
        this.shieldId = shieldId;
    }
}
