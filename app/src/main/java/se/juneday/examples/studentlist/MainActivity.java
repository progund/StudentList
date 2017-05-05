package se.juneday.examples.studentlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName() ;
    private ListAdapter adapter;
    private List<Student> students;

    private static final int MENU_ENTRY_DELETE = 0 ;
    private static final int MENU_ENTRY_DUPLICATE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFakeStudentList();

        // Lookup ListView
        ListView listView = (ListView) findViewById(R.id.student_list);

        // Create Adapter
        adapter = new ArrayAdapter<Student>(this,
                android.R.layout.simple_list_item_1,
                students);

        // Set listView's adapter to the new adapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    final View view,
                                    int position /*The position of the view in the adapter.*/,
                                    long id /* The row id of the item that was clicked */) {
                Log.d(LOG_TAG, "Student clicked, pos:" + position + " id: " + id);

                Toast toast = Toast.makeText(getApplicationContext(),
                        students.get((int)id).toString(),
                        Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        registerForContextMenu(listView);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // get extra information set by the View that added this menu item
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Log.d(LOG_TAG, " itemId: " + item.getItemId());
        Log.d(LOG_TAG, " title:  " + item.getTitle());
        switch (item.getItemId()) {
            case MENU_ENTRY_DUPLICATE:
                Log.d(LOG_TAG, " student to duplicate: " + students.get(info.position));
                students.add(students.get(info.position));

                break;
            case MENU_ENTRY_DELETE:
                Log.d(LOG_TAG, " student to delete: " + students.get(info.position));
                students.remove(info.position);
                break;
            default:
                Log.d(LOG_TAG, " unknown action for student: " + students.get(info.position));
                break;
        }
        ((ListView)findViewById(R.id.student_list)).invalidateViews();

        Log.d(LOG_TAG, "Size: " + students.size());
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ListView listView = (ListView) v;
        AdapterView.AdapterContextMenuInfo acMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String student = listView.getItemAtPosition(acMenuInfo.position).toString();
        menu.setHeaderTitle("Manage " + student);
        menu.add(Menu.NONE, MENU_ENTRY_DELETE, Menu.NONE, "Delete");
        menu.add(Menu.NONE, MENU_ENTRY_DUPLICATE, Menu.NONE, "Duplicate");
    }

    private void createFakeStudentList() {
        students = new ArrayList<>();
        students.add(new Student("Ada", "Lovelace"));
        students.add(new Student("Burt", "Lovelace"));
        students.add(new Student("Cecilia", "Lovelace"));
        students.add(new Student("Dude", "Lovelace"));
        students.add(new Student("Emily", "Lovelace"));
        students.add(new Student("Federico", "Lovelace"));
        students.add(new Student("Gertrude", "Lovelace"));
        students.add(new Student("Hans", "Lovelace"));
        students.add(new Student("Ingrid", "Lovelace"));
        students.add(new Student("Jason", "Lovelace"));
        students.add(new Student("Kate", "Lovelace"));
        students.add(new Student("Leadbelly", "Lovelace"));
        students.add(new Student("Monica", "Lovelace"));
        students.add(new Student("Nico", "Lovelace"));
        students.add(new Student("Olivia", "Lovelace"));
        students.add(new Student("Peter", "Lovelace"));
        students.add(new Student("Q", "Lovelace"));
    }

}
