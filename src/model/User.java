package model;

import java.util.Date;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String location;
    private Date joinDate;

    public User(int userId, String name, String email, String password, String location, Date joinDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.joinDate = joinDate;
    }

    public User(String name, String email, String password, String location, Date joinDate) {
        this(0, name, email, password, location, joinDate);
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
}
