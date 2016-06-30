package com.jacobgreenland.finalproject.league.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jacob on 30/06/16.
 */
public class TeamLink {

    @SerializedName("href")
    @Expose
    private String href;

    /**
     *
     * @return
     * The href
     */
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     * The href
     */
    public void setHref(String href) {
        this.href = href;
    }

}
