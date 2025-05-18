package model;

import java.util.Date;

public class Tip {
    private int tipId;
    private int userId;
    private String topic;
    private String content;
    private Date postedOn;

    public Tip(int tipId, int userId, String topic, String content, Date postedOn) {
        this.tipId = tipId;
        this.userId = userId;
        this.topic = topic;
        this.content = content;
        this.postedOn = postedOn;
    }

    public Tip(int userId, String topic, String content, Date postedOn) {
        this(0, userId, topic, content, postedOn);
    }

    // Getters and Setters
    public int getTipId() { return tipId; }
    public void setTipId(int tipId) { this.tipId = tipId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getPostedOn() { return postedOn; }
    public void setPostedOn(Date postedOn) { this.postedOn = postedOn; }
}
