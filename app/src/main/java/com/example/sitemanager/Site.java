package com.example.sitemanager;



public class Site {
    private String site_address;
    private String site_name;
    private String site_cost;

    public Site(String site_address, String site_name, String site_cost) {
        this.site_address = site_address;
        this.site_name = site_name;
        this.site_cost = site_cost;
    }

    public Site() {

    }

    public String getSite_address() {
        return site_address;
    }

    public void setSite_address(String site_address) {
        this.site_address = site_address;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public void setSite_cost(String site_cost) {
        this.site_cost = site_cost;
    }

    public String getSite_name() {
        return site_name;
    }



    public String getSite_cost() {
        return site_cost;
    }


}