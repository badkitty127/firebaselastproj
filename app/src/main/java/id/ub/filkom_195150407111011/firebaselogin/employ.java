package id.ub.filkom_195150407111011.firebaselogin;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class employ implements Serializable
{
    @Exclude
    private String key;
    private String name;
    private String pos;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public employ(String name, String pos) {
        this.name = name;
        this.pos = pos;
    }

    public employ() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
