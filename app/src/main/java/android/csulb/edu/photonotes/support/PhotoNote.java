package android.csulb.edu.photonotes.support;

import java.io.Serializable;

/**
 * Created by Keval on 01-03-2017.
 */

public class PhotoNote implements Serializable{
    private String picPath;
    private String Caption;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }
}
