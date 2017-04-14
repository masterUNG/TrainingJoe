package appewtc.masterung.trainingjoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //Explicit
    EditText nameEditText, userEditText, passwordEditText;
    Button button;
    String nameString, userString, passwordString;
    String urlPHP = "http://swiftcodingthai.com/joe/addUerMaster.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initial View
        initialView();

        //Button Controller
        buttonController();

    }   // Main Method

    private void buttonController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value From Edit
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (checkSpace()) {
                    //Have Space
                    JoeAlert joeAlert = new JoeAlert(RegisterActivity.this);
                    joeAlert.myDialog(getString(R.string.have_space),
                            getString(R.string.massage_have_space));
                } else {
                    //No Space
                    upLoadValueToServer();
                }

            }   // onClick
        });
    }

    private void upLoadValueToServer() {

        try {

            PostValueToServer postValueToServer = new PostValueToServer(RegisterActivity.this,
                    nameString, userString, passwordString, urlPHP);
            postValueToServer.execute();

            Log.d("14AprilV1", "Response ==> " + postValueToServer.get());

            if (Boolean.parseBoolean(postValueToServer.get())) {
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Cannot Save data", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.d("14AprilV1", "e upload ==> " + e.toString());
        }

    }

    private boolean checkSpace() {

        boolean result = false;

        if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
            //Have Space
            result = true;
        }

        return result;
    }

    private void initialView() {
        nameEditText = (EditText) findViewById(R.id.edtName);
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        button = (Button) findViewById(R.id.btnRegister);
    }

}   // Main Class
