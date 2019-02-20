package com.example.sitemanager;

public class AddSited {
    String Site_name;
    String Site_address;
    String Site_cost;
    String Url;
public AddSited()
{

}

    public AddSited(String site_name, String site_address, String site_cost , String url) {
        Site_name = site_name;
        Site_address = site_address;
        Site_cost = site_cost;
        Url=url;

    }

    public String getSite_name() {        return Site_name;
    }

    public void setSite_name(String site_name) {
        Site_name = site_name;
    }

    public String getSite_address() {
        return Site_address;
    }

    public void setSite_address(String site_address) {
        Site_address = site_address;
    }

    public String getSite_cost() {
        return Site_cost;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setSite_cost(String site_cost) {
        Site_cost = site_cost;
    }
}
