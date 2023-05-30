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

    @Select("select p.*, u.username authorname, v.username sendername, " +
            "(SELECT COUNT(*) FROM replies c WHERE c.topostid = p.postid) +\n" +
            "(SELECT COUNT(*) FROM likes l WHERE l.postid = p.postid) * 2 +\n" +
            "(SELECT COUNT(*) FROM favorites f WHERE f.postid = p.postid) * 3 +\n" +
            "(SELECT COUNT(*) FROM shares s WHERE s.postid = p.postid) * 4 AS hot\n" +
            "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid " +
            "where postid <= #{lastPostId} and postid > #{lastPostId} - #{limit} " +
            "and u.userID not in (select shieldID from shieldby where userID = #{userid})\n" +
            "and v.userID not in (select shieldID from shieldby where userID = #{userid})\n" +
            "order by hot desc;")
        // replace also provide the username
    List<Posts> findPostByIdWithUsernamePageByPage(long lastPostId, long userid, int limit);

    @Select("select p.*, u.username authorname, v.username sendername " +
            "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid join (select * from shares where sharerid = #{userid}) s on p.postid = s.postid " +
            "and u.userID not in (select shieldID from shieldby where userID = '1')\n" +
            "and v.userID not in (select shieldID from shieldby where userID = '1')\n" +
            " order by p.postid desc;")
        // replace also provide the username
    List<Posts> findShareByIdWithUsernamePageByPage(long lastPostId, long userid, int limit);

    @Select("select p.*, u.username authorname, v.username sendername " +
            "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid join (select * from likes where likerid = #{userid}) s on p.postid = s.postid " +
            "and u.userID not in (select shieldID from shieldby where userID = '1')\n" +
            "and v.userID not in (select shieldID from shieldby where userID = '1')\n" +
            " order by p.postid desc;")
        // replace also provide the username
    List<Posts> findLikeByIdWithUsernamePageByPage(long lastPostId, long userid, int limit);

    @Select("select p.*, u.username authorname, v.username sendername " +
            "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid join (select * from favorites where collectorid = #{userid}) s on p.postid = s.postid " +
            "and u.userID not in (select shieldID from shieldby where userID = '1')\n" +
            "and v.userID not in (select shieldID from shieldby where userID = '1')\n" +
            " order by p.postid desc;")
        // replace also provide the username
    List<Posts> findFavoriteByIdWithUsernamePageByPage(long lastPostId, long userid, int limit);

    @Select("select p.*, u.username authorname, v.username sendername " +
            "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid\n" +
            "and senderid = #{userid};")
        // replace also provide the username
    List<Posts> findYourPostByIdWithUsernamePageByPage(long lastPostId, long userid, int limit);

    @Select("select max(postid) from posts;")
    long findMaxPostId();

    @Select("select p.*, u.username authorname, v.username sendername " +
            "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid " +
            "where p.senderid = #{senderid} order by postid desc;")
    List<Posts> findMyPosts(long senderid);

    @Select("select distinct (p.*), u.username authorname, v.username sendername\n" +
            "           from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid\n" +
            "join replies r on p.postid = r.topostid where r.authorid = #{authorid};")
    List<Posts> findMyReplyPost(long authorid);

    @Select("select category from postcategory p join categories c on p.categoryid = c.categoryid where p.postid = #{postid};")
    List<String> findCateByPostId(long postid);


    @Select("select category from categories join postcategory p on categories.categoryid = p.categoryid where p.postid = #{postid};")
    List<String> findPostCate(long postid);

    @Insert("insert into posts(title, content, postingtime, authorid, city, country, senderid, anonymous, originpostid) " +
            "values (#{title}, #{content}, #{postingTime}, #{authorId}, #{city}, #{country}, #{senderId}, #{anonymous}, #{postId}) returning postid")
    @Options(useGeneratedKeys = true, keyProperty = "postId")
    Long insertNewPost(Posts posts);

    @Select("select postid from posts where originPostId = #{originPostId} and senderId = #{senderId}")
    Long findNewSharePostId(long originPostId, long senderId);

    @Insert("insert into postcategory\n" +
            "select #{postId}, CategoryID from postcategory where PostID = #{originPostId};")
    Integer copyCate(long postId, long originPostId);

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
            "from posts p join users u on p.authorid = u.userid join users v on p.senderid = v.userid " +
            "join favorites f on f.postid = p.postid " +
            "where collectorid = #{collectorid};")
    List<Posts> findFavoritePosts(long collectorid);

    @Select("select PostID from shares where PostID = #{postId} and SharerID = #{sharerId}; ")
    Long whetherInShares(long sharerId, long postId);

    @Delete("delete from shares where PostID = #{postId} and SharerID = #{sharerId}; ")
    Integer deShares(long sharerId, long postId);

    @Delete("delete from posts where originPostId = #{originPostId} and senderid = #{senderId}")
    Integer deSharePost(long originPostId, long senderId); // 删post, 响应的replies和postCate会响应的删除


}
