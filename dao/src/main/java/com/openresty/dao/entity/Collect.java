package com.openresty.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 我的收藏
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer topicId;

    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    
    public String toString() {
        return "Collect{" +
        "id=" + id +
        ", userId=" + userId +
        ", topicId=" + topicId +
        ", createTime=" + createTime +
        "}";
    }
}
