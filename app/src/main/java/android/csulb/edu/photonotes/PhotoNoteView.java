package android.csulb.edu.photonotes;

import android.app.Activity;
import android.content.ContentResolver;
import android.csulb.edu.photonotes.support.PhotoNote;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PhotoNoteView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_note_view);
        PhotoNote photoNote = (PhotoNote) this.getIntent().getSerializableExtra("photoNote");
        ImageView imageView = (ImageView) findViewById(R.id.ivViewPhoto);
        imageView.setImageBitmap(loadPic(photoNote.getPicPath()));
        TextView textView = (TextView) findViewById(R.id.tvViewCaption);
        textView.setText(photoNote.getCaption().toCharArray(),0,photoNote.getCaption().length());
    }
    private Bitmap loadPic(String mCurrentPhotoPath) {

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
        return imageBitmap;
    }
}
