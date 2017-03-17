
package com.example.khlopunov.serviceexample.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("results")
    @Expose
    private List<User> results = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> users) {
        this.results = users;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
