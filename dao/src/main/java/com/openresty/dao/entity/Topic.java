package com.openresty.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Data
@Accessors(chain = true)
@TableName("topic")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    //@ApiModelProperty("创建者id")
    private Integer userId;

    //@ApiModelProperty("创建者用户名")
    private String userName;

   // @ApiModelProperty("赞个数")
    private Integer likeNum;

    //@ApiModelProperty("收藏数")
    private Integer collectNum;

    //@ApiModelProperty("评论数")
    private Integer replyNum;

   // @ApiModelProperty("关注数")
    private Integer follow;

    //@ApiModelProperty("阅读数")
    private Integer viewNum;

    //@ApiModelProperty("最后回复者id")
    private Integer lastReplyId;

    //@ApiModelProperty("最后回复者用户名")
    private String lastReplyName;

    //@ApiModelProperty("所属类")
    private Integer categoryId;

   // @ApiModelProperty("1精华帖，0普通帖")
    private Integer isGood;

    //@ApiModelProperty("权重")
    private Integer weight;

    private LocalDateTime lastReplyTime;

    private LocalDateTime updateTime;

   // @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getLastReplyId() {
        return lastReplyId;
    }

    public void setLastReplyId(Integer lastReplyId) {
        this.lastReplyId = lastReplyId;
    }

    public String getLastReplyName() {
        return lastReplyName;
    }

    public void setLastReplyName(String lastReplyName) {
        this.lastReplyName = lastReplyName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIsGood() {
        return isGood;
    }

    public void setIsGood(Integer isGood) {
        this.isGood = isGood;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDateTime getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(LocalDateTime lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    
    public String toString() {
        return "Topic{" +
        "id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", userId=" + userId +
        ", userName=" + userName +
        ", likeNum=" + likeNum +
        ", collectNum=" + collectNum +
        ", replyNum=" + replyNum +
        ", follow=" + follow +
        ", viewNum=" + viewNum +
        ", lastReplyId=" + lastReplyId +
        ", lastReplyName=" + lastReplyName +
        ", categoryId=" + categoryId +
        ", isGood=" + isGood +
        ", weight=" + weight +
        ", lastReplyTime=" + lastReplyTime +
        ", updateTime=" + updateTime +
        ", createTime=" + createTime +
        "}";
    }
}
