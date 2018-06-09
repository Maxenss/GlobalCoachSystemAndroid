package com.easylabs.globalcoachsystemandroid.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easylabs.globalcoachsystemandroid.R;
import com.easylabs.globalcoachsystemandroid.data.SessionData;
import com.easylabs.globalcoachsystemandroid.server.ApiWorker;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText etEmail;
    EditText etPassword;
    Button _loginButton;
    TextView _signupLink;

    private void initializeView() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        _loginButton = findViewById(R.id.btn_login);
        _signupLink = findViewById(R.id.link_signup);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        //  Intent intent = new Intent(this, MessageListActivity.class);
        //  startActivity(intent);

        //  // СОздаем асинх. поток авторизации
        //  SignInAsyncTask signInAsyncTask = new SignInAsyncTask("testt", "testt");
        //  // Запускаем его
        //  signInAsyncTask.execute();
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        SignInAsyncTask signInTaskAsync = new SignInAsyncTask(email, password);
        signInTaskAsync.execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    // Асинхронный поток, для авторизации
    class SignInAsyncTask extends AsyncTask<Void, Void, Void> {
        String login;
        String password;
        ProgressDialog progressDialog;
        ApiWorker.SignInStatus signInStatus;

        SignInAsyncTask(String _login, String _password) {
            login = _login;
            password = _password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Sign In...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        // Выполняется в ОТДЕЛЬНОМ потоке
        @Override
        protected Void doInBackground(Void... voids) {
            // Вызываем метод авторизации
            signInStatus = ApiWorker.signIn(login, password);

            if (signInStatus.equals(ApiWorker.SignInStatus.LOGIN_ACCOUNT))
                ApiWorker.getUserSpecializations(SessionData.currentUser.getId());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();

            if (signInStatus.equals(ApiWorker.SignInStatus.LOGIN_ACCOUNT)) {
                Toast.makeText(LoginActivity.this, "Авторизация прошла успешно", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            } else if (signInStatus.equals(ApiWorker.SignInStatus.NOT_CONNECt)) {
                Toast.makeText(LoginActivity.this, "Ошибка при авторизации", Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Main Thread: ------------------------------------------------------
    // Login Thread:  ----------------------------------------------------
}