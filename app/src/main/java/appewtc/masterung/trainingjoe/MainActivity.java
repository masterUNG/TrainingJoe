package appewtc.masterung.trainingjoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View
        initialView();

        //Controller
        controller();

    }   // Main Method

    private void controller() {
        textView.setOnClickListener(MainActivity.this);
        button.setOnClickListener(MainActivity.this);
    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtRegister);
        button = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View view) {

        //For TextView
        if (view == textView) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        //For Button
        if (view == button) {

            //Get Value From Edit Text
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            //Check Space
            JoeAlert joeAlert = new JoeAlert(MainActivity.this);
            if ((userString.length() == 0) || passwordString.length() == 0) {
                //Have Space
                joeAlert.myDialog(getResources().getString(R.string.have_space),
                        getResources().getString(R.string.massage_have_space));
            } else {
                //No Space
                checkUserAnPass();
            }

        }

    }   // onClick

    private void checkUserAnPass() {

        String tag = "14AprilV2";
        String urlPHP = "http://swiftcodingthai.com/joe/getUserMaster.php";
        String truePassword = null, strName = null;
        JoeAlert joeAlert = new JoeAlert(MainActivity.this);

        try {

            GetUser getUser = new GetUser(this);
            getUser.execute(urlPHP);

            String strJSON = getUser.get();
            Log.d(tag, "JSoN ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    aBoolean = false;
                    truePassword = jsonObject.getString("Password");
                    strName = jsonObject.getString("Name");
                }

            }   //for

            if (aBoolean) {
                joeAlert.myDialog(getString(R.string.title_user_false),
                        getString(R.string.message_user_false));
            } else if (passwordString.equals(truePassword)) {

                Toast.makeText(MainActivity.this, "Welcome "+ strName, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                intent.putExtra("Name", strName);
                startActivity(intent);

            } else {
                joeAlert.myDialog(getString(R.string.title_pass_false),
                        getString(R.string.message_pass_false));
            }

        } catch (Exception e) {
            Log.d(tag, "e checkUser ==> " + e.toString());
        }

    }

}   // Main Class
