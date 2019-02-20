package com.example.sitemanager;

public class SiteAssign {
    String Worker1;
    String Worker2;
    String SiteName;
    String limit;
    public SiteAssign(){

    }

    public SiteAssign(String worker1, String worker2, String siteName, String limit) {
        Worker1 = worker1;
        Worker2 = worker2;
        SiteName = siteName;
        this.limit = limit;
    }

    public String getWorker1() {
        return Worker1;
    }

    public void setWorker1(String worker1) {
        Worker1 = worker1;
    }

    public String getWorker2() {
        return Worker2;
    }

    public void setWorker2(String worker2) {
        Worker2 = worker2;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
