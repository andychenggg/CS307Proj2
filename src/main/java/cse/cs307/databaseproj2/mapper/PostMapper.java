package cse.cs307.databaseproj2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cse.cs307.databaseproj2.entities.Posts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostMapper extends BaseMapper<Posts> {

    @Select("select *\n" +
            "from posts\n" +
            "where postid in (select postid from likes where likerid = '#{likerid}');")
    Posts findLikePosts(String likerid);

    @Select("select *\n" +
            "from posts\n" +
            "where postid in (select postid from shares where sharerid = '#{sharerid}');")
    Posts findSharePosts(String sharerid);

    @Select("select *\n" +
            "from posts\n" +
            "where postid in (select postid from shares where collectorid = '#{collectorid}');")
    Posts findFavoritePosts(String collectorid);




}
