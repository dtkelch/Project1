package com.spencer.project1;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.activity_first)
public class FirstActivity extends ActionBarActivity {

    @RestService
    PersonRepository mPersonRepository;

    @ViewById(R.id.indexTextView)
    EditText mIndexEditTextView;

    @Extra
    String testString;

    @ViewById(R.id.textView)
    TextView textView;

    @AfterViews
    void afterView() {
       // textView.setText(testString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.button)
    void buttonWasClicked() {
        print("success");

        SecondActivity_.intent(this)
                .testString("hello there second activity")
                .start();
    }

    @Click(R.id.button2)
    void whatsUpClicked() {
        Context context = getApplicationContext();
        CharSequence text = "Whaddup!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Click(R.id.getPersonButton)
    void onGetPersonButtonClick() {
        //get person's index
        String indexValue = mIndexEditTextView.getText().toString();
        try {
            //if the input is a number, parse it and get the person.
            int index = Integer.parseInt(indexValue);
            getPerson(index); //execute a process on a background thread.
        } catch(NumberFormatException e) {
            //NO-OP
        }
    }

    @Click(R.id.getAllPeopleButton)
    void onGetPeopleButtonClick() {
        //get all the people
        getPeople();
    }

    @Background
    protected void getPerson(int index) {
        //methods annotated with @Background will user a separate thread.
        //this method uses network so he requires another thread.
        String json = mPersonRepository.getPerson(index); //network call.

        Gson gson = new Gson();
        gson.fromJson(json, Person.class);
        System.out.println(json);
        System.out.println(gson.toString());
//        displayToast(json); //this method does ui stuff so, run it on ui thread.
//        displayToast("PAUSE");
//        displayToast(gson.toString()); //this method does ui stuff so, run it on ui thread.
    }

    @Background
    protected void getPeople() {
        //methods annotated with @Background will user a separate thread.
        //this method uses network so he requires another thread.
        String json = mPersonRepository.getPeople(); //network call

        Gson gson = new Gson();
        gson.fromJson(json, Person.class);
        System.out.println(gson.toString());
//        displayToast(json); //ui process
    }

    /**
     * Displays a {@link Toast#LENGTH_SHORT} toast on the ui-thread.
     * @param text the text to display.
     */
    @UiThread
    protected void displayToast(String text) {
        //methods annotated with @UiThread will run on the ui-thread
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * This is a printing method.
     * @param text the text to print.
     */
    private void print(String text) {
        if (text != null && text.length() > 0) {
            System.out.println(text);
        }
    }
}
