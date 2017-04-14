package appewtc.masterung.trainingjoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    //Explicit
    EditText nameEditText, userEditText, passwordEditText;
    Button button;
    String nameString, userString, passwordString;

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
                }

            }   // onClick
        });
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
