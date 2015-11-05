package com.fefeyo.parsefavoriter;

/**
 * Created by USER on 2015/11/05.
 */
public class Url {
    private String name;
    private String url;
    private String entryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    @Override
    public String toString() {
        return url;
    }
}
