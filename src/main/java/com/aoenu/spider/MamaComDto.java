package com.aoenu.spider;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: Created in 2019-05-29 17:52
 * @Author: baoben.wu@hand-china.com
 */
public class MamaComDto {
    private Long id;
    private String title;
    private String userName;
    private String babyAge;
    private String city;
    private String replyAndView;
    private String lastActiveTime;
    private Date insertTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBabyAge() {
        return babyAge;
    }

    public void setBabyAge(String babyAge) {
        this.babyAge = babyAge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReplyAndView() {
        return replyAndView;
    }

    public void setReplyAndView(String replyAndView) {
        this.replyAndView = replyAndView;
    }

    public String getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "MamaComDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userName='" + userName + '\'' +
                ", babyAge='" + babyAge + '\'' +
                ", city='" + city + '\'' +
                ", replyAndView='" + replyAndView + '\'' +
                ", lastActiveTime='" + lastActiveTime + '\'' +
                ", insertTime=" + insertTime +
                '}';
    }
}
