package cse.cs307.databaseproj2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.cs307.databaseproj2.entities.Posts;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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
        "where p.senderid = #{senderid} order by postid desc;")
    List<Posts> findMyPosts(long senderid);

    @Select("select category from postcategory p join categories c on p.categoryid = c.categoryid where p.postid = #{postid};")
    List<String> findCateByPostId(long postid);



    @Select("select category from categories join postcategory p on categories.categoryid = p.categoryid where p.postid = #{postid};")
    List<String> findPostCate(long postid);

    @Insert("insert into posts(title, content, postingtime, authorid, city, country, senderid, anonymous) " +
        "values (#{title}, #{content}, #{postingTime}, #{authorId}, #{city}, #{country}, #{senderId}, #{anonymous}) returning postid")
    @Options(useGeneratedKeys = true, keyProperty = "postId")
    Long insertNewPost(Posts posts);
    /* 待修改*/
    @Insert("insert into categories(category) values #{category} returning categoryid")
    @Options(useGeneratedKeys = true, keyProperty = "postid")
    Long addCate(String category);

    @Select("select categoryid from categories where category = #{category};")
    long findCateIdByName(String category);

    @Insert("insert into postcategory values(#{postid}, #{cateid});")
    long addPostCate(long postid, long cateid);

    @Delete("delete from posts where postid = #{postid};")
    Integer deletePostById(long postid);

    @Select("select p.*, u.username authorname, v.username sendername\n" +
        "        from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid\n" +
        "        join likes f on f.postid = p.postid\n" +
        "        where likerid = #{likerid};")
    List<Posts> findLikePosts(long likerid);

    @Select("select p.*, u.username authorname, v.username sendername\n" +
        "        from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid\n" +
        "        where authorid != senderid and senderid = #{sharerid};")
    List<Posts> findSharePosts(long sharerid);

    @Select("select p.*, u.username authorname, v.username sendername " +
        "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid "+
        "join favorites f on f.postid = p.postid "+
        "where collectorid = #{collectorid};")
    List<Posts> findFavoritePosts(long collectorid);

    @Select("select PostID from shares where PostID = #{postId} and SharerID = #{sharerId}; ")
    Long whetherInShares(long sharerId, long postId);
    @Delete("delete from shares where PostID = #{postId} and SharerID = #{sharerId}; ")
    Integer deShares(long sharerId, long postId);
}
