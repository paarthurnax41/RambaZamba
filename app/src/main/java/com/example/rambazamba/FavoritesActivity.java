package com.example.rambazamba;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FavoritesActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;
    private ListView listView;
    private Cursor myCursor;
    private MyCursorAdapter myCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        listView = findViewById(R.id.listViewId);

        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.open();
        listView.setEmptyView(findViewById(R.id.empty));


        myCursor = dataBaseHelper.getActivitiesCursor();

        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(() -> {
            myCursorAdapter = new MyCursorAdapter(
                    FavoritesActivity.this,
                    myCursor,
                    0
            );
            listView.setAdapter(myCursorAdapter);

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createDialog(view, id);
                return true;
            }
        });

    }

    public void createDialog(View view, long id) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        //adb.setView(Main.this);
        adb.setTitle("Share or Delete");
        adb.setIcon(R.drawable.monkeys);
        adb.setPositiveButton("SHARE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                share(dataBaseHelper.get(id));
                Toast.makeText(getApplicationContext(), "SHARED", Toast.LENGTH_LONG).show();
            }
        });

        adb.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "DELETED", Toast.LENGTH_LONG).show();
                dataBaseHelper.delete(id);
                //ANSTATT ACTIVITY NEU ZU STARTEN UM LISTE ZU ERNEUERN  CURSOR ERNEUERN ! - recreate(); ist verboten :D
                myCursorAdapter.changeCursor(dataBaseHelper.getActivitiesCursor());
                myCursorAdapter.notifyDataSetChanged();

            }
        });

        AlertDialog alertDialog = adb.create();
        alertDialog.show();

    }

    private void share(String activity) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, activity);
        startActivity(intent);
    }
}