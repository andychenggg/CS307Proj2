package cse.cs307.databaseproj2.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;
import java.util.List;

public class Users {
    private String userid;
    private String username;
//    @TableField(exist = false) // 调用mybatis-plus中的方法是不可能将password返回给前端的
    private String password;
    private LocalDateTime registrationtime;



    private String phone;
    @TableField(value = "is_followed")
    private boolean isFollowed;

    @TableField(value = "is_shield")
    private boolean isShield;

    public boolean isIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(boolean is_followed) {
        this.isFollowed = is_followed;
    }

    public void setIsShield(boolean is_shield) {
        this.isShield = is_shield;
    }

    public boolean isIsShield() {
        return isShield;
    }

    @TableField(exist = false)
    private List<Users> following;



    @TableField(exist = false)
    private List<Posts> posts;

    public Users() {

    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Users(String userid, String username, String password, LocalDateTime registrationtime,
                 String phone) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.registrationtime = registrationtime;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Users{" +
            "userid='" + userid + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", registrationtime=" + registrationtime +
            ", phone='" + phone + '\'' +
            '}';
    }

    public boolean checkPass(String pass){
        return password.equals(pass);
    }

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public LocalDateTime getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(LocalDateTime registrationtime) {
        this.registrationtime = registrationtime;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
