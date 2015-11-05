package com.fefeyo.parsefavoriter;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by USER on 2015/11/05.
 */
@ParseClassName("Favorite")
public class Favorite extends ParseObject{
    public String getTitle(){
        return getString("title");
    }
    public String getUrl(){
        return getString("url");
    }
    public String getEntryId(){
        return getString("id");
    }
    public String getOriginalIdI(){return getString("original");}
    public void setTitle(String title){
        put("title", title);
    }
    public void setUrl(String url){
        put("url", url);
    }
    public void setEntryId(String id){
        put("id", id);
    }
    public void setOriginalId(String original){put("original", original);}

    @Override
    public String toString() {
        return getTitle();
    }
}
