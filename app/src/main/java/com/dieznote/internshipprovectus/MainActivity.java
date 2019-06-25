package com.dieznote.internshipprovectus;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "JSON";
    private NotesAdapter mAdapter;
    private TextView noNotesView;
    private ProgressBar prb;
    RecyclerView recyclerView;


    private List<Item> mItems= new ArrayList<>();

    private class FetchItemsTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            FlickrFetchr F = new FlickrFetchr();
                mItems = F.fetchItems();

                Log.w(TAG,"mItems "+mItems);

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            toggleEmptyNotes();
            mAdapter = new NotesAdapter(MainActivity.this, mItems);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new MyDividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL, 16));
            recyclerView.setAdapter(mAdapter);


            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this,
                    recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, final int position) {
                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    intent.putExtra("personInfo",(new Gson()).toJson(mItems.get(position)));
                    startActivity(intent);
                    Log.w(TAG,"position - "+ position);
                }

                @Override
                public void onLongClick(View view, int position) {
                    showActionsDialog(position);
                }
            }));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new FetchItemsTask().execute();

        //CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);
        prb = findViewById(R.id.empty_notes_prbar);
        Log.d(TAG, "onCreate");

    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume ");
    }
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteNote(position);

            }
        });
        builder.show();
    }

    private void deleteNote(int position) {
        // removing the note from the list
        mItems.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }
    private void toggleEmptyNotes() {

        if (mItems.size() > 0) {
            noNotesView.setVisibility(View.GONE);
            prb.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
            prb.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent2 = new Intent(MainActivity.this, About.class);
            startActivity(intent2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
