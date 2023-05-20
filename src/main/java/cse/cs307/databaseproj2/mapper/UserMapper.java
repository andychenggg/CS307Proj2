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



    @Select("select userid from users where username = #{username};")
    long findIdByUsername(String username);
    //here
    @Select("select * from users where userid=#{id}")
    Users findById(long id);

    @Select("select username from users where userid=#{id}")
    String findNameById(long id);

    @Select("select * from users where username = #{username};")
    Users findByUsername(String username);



    @Select("SELECT users.userid, username, registrationtime, phone,\n" +
        "       CASE\n" +
        "           WHEN followedby.userid IS NOT NULL THEN true\n" +
        "           ELSE false\n" +
        "       END AS is_followed\n" +
        "FROM users\n" +
        "LEFT JOIN followedby\n" +
        "ON users.userid = followedby.followigid\n" +
        "AND followedby.userid = #{id};")
    List<Users> findFollowing(long id);

    @Select("select *\n" +
            "from users\n" +
            "where userid in (select followigid from followedby where userid = #{userid});")
    int findFollowed(String userid);
    //按理应该不包括密码，如果后续需要不包括密码，可以参照这个userid, username, registrationtime, phone

    @Update("update users set username=#{username}, phone=#{phone}")
    int update(Users user);

    @Insert("insert into users(username, password, registrationtime, phone) values (#{username}, #{password}, #{registrationtime}, #{phone})")
    int addUser(String username, String password, LocalDateTime registrationtime, String phone);

    @Insert("insert into likes values (#{postid}, #{likerid})")
    int likePost(long postid, long likerid);

    @Insert("insert into favorites values(#{postid}, #{collectorid})")
    int collectPost(long postid, long collectorid);

    @Insert("insert into shares values(#{postid}, #{shareid})")
    int sharePost(long postid, long shareid);



    @Insert("insert into followedby(userID, FollowigID) values(#{userid}, #{followigid});")
    int followOthers(long userid, long followigid);

    @Delete("delete from followedby where userID = #{userid} and followigid = #{followigid};")
    int deFollowOthers(long userid, long followigid);

    @Delete("delete from users where id=#{userid")
    int deleteById(String id);
}
