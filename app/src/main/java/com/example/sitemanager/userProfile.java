package com.example.sitemanager;

public class userProfile {
    public  String uname;
    public  String udob;
    public  String uaddress;
    public  String uemail;
    public  String umobile;

    public userProfile(){

    }

    public userProfile(String uname, String udob, String uaddress, String uemail, String umobile) {
        this.uname = uname;
        this.udob = udob;
        this.uaddress = uaddress;
        this.uemail = uemail;
        this.umobile = umobile;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUdob() {
        return udob;
    }

    public void setUdob(String udob) {
        this.udob = udob;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUmobile() {
        return umobile;
    }

    public void setUmobile(String umobile) {
        this.umobile = umobile;
    }
}
