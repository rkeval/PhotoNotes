package android.csulb.edu.photonotes;

import android.content.Intent;
import android.csulb.edu.photonotes.support.PhotoNote;
import android.csulb.edu.photonotes.support.SQLOperations;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class NotesList extends AppCompatActivity {

    private String[] mobileArray= new String[]{"1", "2","3","4","5","6"};
    private SQLOperations myDb = new SQLOperations(this);
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
        setContentView(R.layout.activity_notes_list);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Cursor res=null;
        try
        {
            res = myDb.getAllEntries();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            Log.e("Message",e.getMessage());
        }
        if (res.getCount() == 0)
            return;
        String[] from= {"Caption","ImagePath"};
        int[] to = {R.id.tvCaption,R.id.tvPicPath};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.list_entry,res,from,to,0);
        ListView listView = (ListView) findViewById(R.id.lvNotes);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                // TODO Auto-generated method stub
               // int id_To_Search = arg2 + 1;

                PhotoNote photoNote = new PhotoNote();
                TextView textView = (TextView) arg1.findViewById(R.id.tvCaption);
                photoNote.setCaption(textView.getText().toString());
                textView = (TextView) arg1.findViewById(R.id.tvPicPath);
                photoNote.setPicPath(textView.getText().toString());

                Intent intent = new Intent(getApplicationContext(),PhotoNoteView.class);

                intent.putExtra("photoNote", photoNote);
                startActivity(intent);
            }
        });
    }

    public void openAddNote(View view) {
        Intent intent = new Intent(this, AddPhoto.class);
        startActivity(intent);
    }
}
