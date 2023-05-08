package cse.cs307.databaseproj2.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;
import java.util.List;

public class Users {
    private String userid;
    private String username;
    @TableField(exist = false) // 调用mybatis-plus中的方法是不可能将password返回给前端的
    private String password;
    private LocalDateTime registrationtime;
    private String phone;
    @TableField(exist = false)
    private List<Users> following;

    @Override
    public String toString() {
        return "Users{" +
            "userid='" + userid + '\'' +
            ", username='" + username + '\'' +
            ", registrationtime=" + registrationtime +
            ", phone='" + phone + '\'' +
            '}';
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
