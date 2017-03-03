package android.csulb.edu.photonotes;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.csulb.edu.photonotes.support.CameraOperations;
import android.csulb.edu.photonotes.support.SQLOperations;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPhoto extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath="";
    CameraOperations cameraOperations;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Uninstall");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri packageUri = Uri.parse("package:android.csulb.edu.photonotes");
        Intent uninstallIntent =
                new Intent(Intent.ACTION_DELETE, packageUri);
        startActivity(uninstallIntent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        cameraOperations = CameraOperations.getInstance();
        loadPic();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onBackPressed() {
        cameraOperations.setPhotoPath("");
        //TODO - Delete the image.
        super.onBackPressed();
    }

    //Save Note button pressed
    public void saveNote(View view) {
        SQLOperations myDb= new SQLOperations(this);
        EditText caption =(EditText)findViewById(R.id.txtCaption);
       if(caption.getText().toString().equals("") || cameraOperations.getPhotoPath().equals("")){
           Toast.makeText(this,"Caption or Image is not filled. Please fill both the field.",Toast.LENGTH_SHORT).show();
           return;
       }
        myDb.saveNote(caption.getText().toString(),cameraOperations.getPhotoPath());
        cameraOperations.setPhotoPath("");
        finish();
    }
    //Take Photo button pressed
    public void takePhoto(View view) {

        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "android.csulb.edu.photonotes",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void loadPic() {
        mCurrentPhotoPath = cameraOperations.getPhotoPath();

        if (mCurrentPhotoPath.equals(""))
            return;

        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        ContentResolver cr = getContentResolver();
        InputStream in = null;
        try {
            in = cr.openInputStream(contentUri);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found",Toast.LENGTH_SHORT).show();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize=8;
        Bitmap imageBitmap = BitmapFactory.decodeStream(in,null,options);
        ImageView imageView = (ImageView) findViewById(R.id.ivPhoto);
        imageView.setImageBitmap(imageBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            loadPic();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        cameraOperations.setPhotoPath(mCurrentPhotoPath);
        return image;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("AddPhoto Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
