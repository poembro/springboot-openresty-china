package com.openresty.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements  Serializable {
    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String avatar;

    private LocalDateTime createTime;

    private String city;

    private String website;

    private String company;

    private String sign;

    private String github;

    private String email;

    //@ApiModelProperty("1公开，0私密")
    private Integer emailPublic;

   // @ApiModelProperty("1管理员，0普通用户")
    private Integer isAdmin;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmailPublic() {
        return emailPublic;
    }

    public void setEmailPublic(Integer emailPublic) {
        this.emailPublic = emailPublic;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    
    public String toString() {
        return "User{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", avatar=" + avatar +
        ", createTime=" + createTime +
        ", city=" + city +
        ", website=" + website +
        ", company=" + company +
        ", sign=" + sign +
        ", github=" + github +
        ", email=" + email +
        ", emailPublic=" + emailPublic +
        ", isAdmin=" + isAdmin +
        "}";
    }
}
