package com.sokah.todoapp;

public class Folder {
    private String id,UserId,name;

    public Folder(String id, String userId, String name) {
        this.id = id;
        UserId = userId;
        this.name = name;
    }
    public Folder(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
