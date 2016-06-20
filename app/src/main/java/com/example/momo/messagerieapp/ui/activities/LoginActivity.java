package com.example.momo.messagerieapp.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.momo.messagerieapp.R;
import com.example.momo.messagerieapp.core.ChatService;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.users.model.QBUser;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    EditText login,password;
    Button connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        connexion=(Button)findViewById(R.id.loginButton);
        login=(EditText)findViewById(R.id.loginEdit);
        password=(EditText)findViewById(R.id.passwordEdit);


        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USER_LOGIN=login.getText().toString();
                String USER_PASSWORD=password.getText().toString();
                final QBUser user = new QBUser();
                user.setLogin(USER_LOGIN);
                user.setPassword(USER_PASSWORD);

                ChatService.initIfNeed(getApplicationContext());

                ChatService.getInstance().login(user, new QBEntityCallbackImpl() {

                    @Override
                    public void onSuccess() {
                        // Go to Dialogs screen
                        //
                        Intent intent = new Intent(LoginActivity.this, DialogsActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(List errors) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                        dialog.setMessage("chat login errors: " + errors).create().show();
                    }
                });
            }
        });


    }
}
