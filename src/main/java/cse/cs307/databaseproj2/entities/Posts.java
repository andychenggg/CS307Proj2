package cse.cs307.databaseproj2.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Table;
import org.springframework.web.multipart.MultipartFile;

public class Posts {
    private long postId;
    private String title;
    private String content;
    private LocalDateTime postingTime;
    private long authorId;
    private String city;
    private long senderId;
    private boolean anonymous;
    private boolean isSenderAnonymous;
    private String country;
    private String filePath;
    private long originPostId;

    public boolean getissenderanonymous() {
        return isSenderAnonymous;
    }

    public void setSenderAnonymous(boolean senderAnonymous) {
        isSenderAnonymous = senderAnonymous;
    }

    @TableField(exist = false)
    private List<String> postCategories;
    @TableField(exist = false)
    private String authorName;

    @TableField(exist = false)
    private String senderName;

    @TableField(exist = false)
    private String ip;

    @TableField(exist = false)
    private MultipartFile file;
    @TableField(exist = false)
    private String filename;
    @TableField(exist = false)
    private String filepath;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


    public long getOriginPostId() {
        return originPostId;
    }

    public void setOriginPostId(long originPostId) {
        this.originPostId = originPostId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(LocalDateTime postingTime) {
        this.postingTime = postingTime;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public List<String> getPostCategories() {
        return postCategories;
    }

    public void setPostCategories(List<String> postCategories) {
        this.postCategories = postCategories;
    }


    @Override
    public String toString() {
        return "Posts{" +
            "postId=" + postId +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", postingTime=" + postingTime +
            ", authorId=" + authorId +
            ", city='" + city + '\'' +
            ", senderId=" + senderId +
            ", anonymous=" + anonymous +
            ", country='" + country + '\'' +
            ", postCategories=" + postCategories +
            ", authorName='" + authorName + '\'' +
            ", senderName='" + senderName + '\'' +
            ", ip='" + ip + '\'' +
            '}';
    }
}




