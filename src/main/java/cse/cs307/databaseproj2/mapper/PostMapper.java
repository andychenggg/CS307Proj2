package cse.cs307.databaseproj2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.cs307.databaseproj2.entities.Posts;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostMapper extends BaseMapper<Posts> {

    @Select("select * from posts where postid = #{postid};")
    Posts findPostById(long postid);

    @Insert("insert into posts(title, content, postingtime, authorid, city, senderid, anonymous)\n" +
        "values (#{title}, #{content}, #{postingtime}, #{authorid}, #{city}, #{senderid}, #{anonymous})")
    int insertNewPost(String title, String content, LocalDateTime postingtime, String authorid, String city,String senderid, boolean anonymous);

    @Select("select *\n" +
            "from posts\n" +
            "where postid in (select postid from likes where likerid = #{likerid});")
    Posts findLikePosts(String likerid);

    @Select("select *\n" +
            "from posts\n" +
            "where postid in (select postid from shares where sharerid = #{sharerid});")
    Posts findSharePosts(String sharerid);

    @Select("select *\n" +
            "from posts\n" +
            "where postid in (select postid from shares where collectorid = #{collectorid});")
    Posts findFavoritePosts(String collectorid);




}
