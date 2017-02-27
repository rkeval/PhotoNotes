package android.csulb.edu.photonotes.support;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Keval on 27-02-2017.
 */

public class CameraOperations {


    private static CameraOperations  cameraOperations=null;
    private String photoPath="";

    private CameraOperations(){

    }

    public static CameraOperations getInstance() {
        if (cameraOperations!=null)
            return cameraOperations;
        return cameraOperations= new CameraOperations();
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

}
