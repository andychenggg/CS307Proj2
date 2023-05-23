package cse.cs307.databaseproj2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.cs307.databaseproj2.entities.Replies;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RepliesMapper extends BaseMapper<Replies> {
    @Insert("insert into replies(toreplyid, topostid, content, stars, authorid, anonymous) " +
        "VALUES (#{toreplyid}, #{topostid}, #{content}, #{stars}, #{authorid}, #{anonymous})")
    int addReply(long toreplyid, long topostid, String content, int stars, long authorid, boolean anonymous);

    @Select("select r.*, u.username authorName, w.userid touserid, w.username tousername\n" +
        "        from replies r join users u on u.userid = r.authorid\n" +
        "        left join replies v on r.toreplyid = v.replyid\n" +
        "        left join users w on w.userid = v.authorid where r.authorid = #{authorId};")
    List<Replies> searchRepliesByAuthorId(long authorId);

    @Select("select r.*, u.username authorName, w.userid touserid, w.username tousername\n" +
        "        from replies r join users u on u.userid = r.authorid\n" +
        "        left join replies v on r.toreplyid = v.replyid\n" +
        "        left join users w on w.userid = v.authorid where r.postid = #{postId} order by replyId desc;")
    List<Replies> searchRepliesByPostId(long postId);
}
