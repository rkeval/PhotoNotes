package android.csulb.edu.photonotes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NotesList extends AppCompatActivity {

    private List<String> mobileArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.support.v7.appcompat.R.layout.abc_list_menu_item_layout, mobileArray);
        ListView listView = (ListView) findViewById(R.id.lvNotes);
        listView.setAdapter(adapter);
    }

    public void openAddNote(View view) {
        Intent intent = new Intent(this,AddPhoto.class);
        startActivity(intent);
    }
}
