package cse.cs307.databaseproj2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.cs307.databaseproj2.entities.Posts;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostMapper extends BaseMapper<Posts> {

    @Select("select * from posts where postid = #{postid};")
    Posts findPostById(long postid);


    @Select("select p.*, u.username authorname, v.username sendername " +
        "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid "+
        "where postid <= #{lastPostId} and postid > #{lastPostId} - #{limit} order by postid desc;")
    // replace also provide the username
    List<Posts> findPostByIdWithUsernamePageByPage(long lastPostId, int limit);
    @Select("select max(postid) from posts;")
    long findMaxPostId();
    @Select("select p.*, u.username authorname, v.username sendername " +
        "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid "+
        "where p.authorid = #{authorid} order by postid desc;")
    List<Posts> findMyPosts(String authorid);



    @Select("select category from categories join postcategory p on categories.categoryid = p.categoryid where p.postid = #{postid};")
    List<String> findPostCate(long postid);

    @Insert("insert into posts(title, content, postingtime, authorid, city, senderid, anonymous) " +
        "values (#{title}, #{content}, #{postingtime}, #{authorid}, #{city}, #{senderid}, #{anonymous})")
    int insertNewPost(String title, String content, LocalDateTime postingtime, String authorid, String city,String senderid, boolean anonymous);
    /* 待修改*/
    @Insert("insert into cities values(#{city}, #{country})")
    int addCity(String city, String country);
    @Insert("insert into categories(category) values #{category}")
    int addCate(String category);

    @Select("select p.*, u.username authorname, v.username sendername" +
        "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid "+
        "where likerid = #{likerid};")
    List<Posts> findLikePosts(String likerid);

    @Select("select p.*, u.username authorname, v.username sendername " +
        "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid "+
        "where sharerid = #{sharerid};")
    List<Posts> findSharePosts(String sharerid);

    @Select("select p.*, u.username authorname, v.username sendername " +
        "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid "+
        "where collectorid = #{collectorid};")
    List<Posts> findFavoritePosts(String collectorid);


}
