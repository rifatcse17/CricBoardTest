package com.example.rif.crickbroad;


public class Player {
    private long id;
    private String name;
    private long fid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }
}
