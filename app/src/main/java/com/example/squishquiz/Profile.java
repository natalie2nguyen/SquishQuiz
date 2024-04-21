package com.example.squishquiz;

public class Profile {
    private String username;
    private String password;
    private int points;

    public Profile(String username, String password, int points){
        this.username = username;
        this.password = password;
        this.points = points;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password = password;
    }

    public int getPoints(){
        return points;
    }
    public void setPoints(){
        this.points = points;
    }

}