package android.csulb.edu.photonotes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NotesList extends AppCompatActivity {

    private String[] mobileArray= new String[]{"1", "2","3","4","5","6"};

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

        /*ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.support.v7.appcompat.R.layout.abc_list_menu_item_layout, mobileArray);
        ListView listView = (ListView) findViewById(R.id.lvNotes);
        listView.setAdapter(adapter);*/
    }

    public void openAddNote(View view) {
        Intent intent = new Intent(this, AddPhoto.class);
        startActivity(intent);
    }
}
