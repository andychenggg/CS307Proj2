package cse.cs307.databaseproj2.entities;

import java.util.List;

public class Replies {
    private long replyId;
    private long toReplyId;
    private long toPostId;
    private String content;
    private int stars;
    private String authorId;
    private String postId;
    private List<String> picPaths;
    private List<String> videoPath;

}
