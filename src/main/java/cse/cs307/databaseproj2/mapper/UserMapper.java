package cse.cs307.databaseproj2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.cs307.databaseproj2.entities.Users;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
/**
 * Should be modified when User change fields
 */
public interface UserMapper extends BaseMapper<Users> {


    //here
    @Select("select * from users where userid=#{id}")
    Users findById(String id);

    @Select("select * from users where username = #{username};")
    Users findByUsername(String username);

    @Select("select v.userid, v.username, v.RegistrationTime, v.Phone from users u\n" +
        "join FollowedBy FB on u.userID = FB.userid\n" +
        "join users v on v.userid = FB.followigid\n" +
        "where u.userid = #{id};")
    List<Users> findFollowing(String id);

    @Update("update users set username=#{username}, phone=#{phone}")
    int update(Users user);

    @Insert("insert into users values (#{userid}, #{username}, #{password}, #{registrationtime}, #{phone})")
    int addUser(String userid, String username, String password, LocalDateTime registrationtime, String phone);

    @Insert("insert into likes values (#{postid}, #{likerid})")
    int likePost(String postid, String likerid);

    @Insert("insert into favorites values(#{postid}, #{collectorid})")
    int collectPost(String postid, String collectorid);

    @Insert("insert into shares values(#{postid}, #{shareid})")
    int sharePost(String postid, String shareid);

    @Insert("insert into followedby(userID, FollowigID) values(#{userid}, #{followigid});")
    int followOthers(String userid, String followingid);

    @Delete("delete from followedby where userID = '#{userid}' and followigid = '#{followigid}';")
    int deFollowOthers(String userid, String followigid);

//    @Select("insert into posts(postid, title, content, postingtime, authorid, city, senderid, anonymous, accesslevel) values();")

    @Delete("delete from users where id=#{userid")
    int deleteById(String id);
}
