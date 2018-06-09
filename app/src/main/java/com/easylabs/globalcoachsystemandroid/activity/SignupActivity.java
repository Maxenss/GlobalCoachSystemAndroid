package com.easylabs.globalcoachsystemandroid.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easylabs.globalcoachsystemandroid.R;
import com.easylabs.globalcoachsystemandroid.server.ApiWorker;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText etName;
    EditText etSurname;
    EditText etEmail;
    EditText etPassword;
    EditText etConfPass;
    EditText etAge;
    Button _signupButton;
    TextView _loginLink;

    private void initializeView() {
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfPass = findViewById(R.id.etConfPass);
        etAge = findViewById(R.id.etAge);
        _signupButton = findViewById(R.id.btn_signup);
        _loginLink = findViewById(R.id.link_login);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                onBackPressed();

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializeView();
    }

    public void signUp() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etConfPass.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());

        // TODO: Implement your own signup logic here.
        SignUpTaskAsync signUpTaskAsync = new SignUpTaskAsync(
                email, password, name, surname, age);

        signUpTaskAsync.execute();
    }


    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Incorrect Data", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etConfPass.getText().toString();

        // Проверка Имени
        if (name.isEmpty() || name.length() < 2) {
            etName.setError("at least 3 characters");
            valid = false;
        } else {
            etName.setError(null);
        }

        // Проверка фамилии
        if (surname.isEmpty() || surname.length() < 2) {
            etSurname.setError("at least 3 characters");
            valid = false;
        } else {
            etSurname.setError(null);
        }

        // Проверка email
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        // Проверка пароля
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        // Проверка пароля confirm
        if (passwordConfirm.isEmpty()
                || passwordConfirm.length() < 4
                || passwordConfirm.length() > 10) {
            etConfPass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            etConfPass.setError(null);
        }

        // Проверяем на корректность пароли
        if (!password.equals(passwordConfirm)) {
            etPassword.setError("passwords not match");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        // Проверяем на возраст
        if (etAge.getText().toString().length() == 0) {
            etPassword.setError("age must be more than 16 and less more 150");
            valid = false;
            return valid;
        } else {
            etPassword.setError(null);
        }

        int age = Integer.parseInt(etAge.getText().toString());

        // Проверяем на возраст
        if (age < 16 || age > 150) {
            etPassword.setError("age must be more than 16 and less more 150");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    // Создаём поток для регистрации
    class SignUpTaskAsync extends AsyncTask<Void, Void, Void> {
        String login;
        String password;
        String firstName;
        String secondName;
        int age;
        ProgressDialog progressDialog;

        ApiWorker.SignUpStatus signUpStatus =
                ApiWorker.SignUpStatus.UNKNOWN_ERROR;

        public SignUpTaskAsync(String login
                , String password
                , String firstName
                , String secondName
                , int age) {
            this.login = login;
            this.password = password;
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SignupActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            signUpStatus = ApiWorker.signUp(login, password, firstName, secondName, age);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Перстаем отображать диалог
            progressDialog.cancel();

            if (signUpStatus == ApiWorker.SignUpStatus.CREATE_ACCOUNT) {
                Toast.makeText(SignupActivity.this, "Регистрация прошла", Toast.LENGTH_SHORT).show();
            } else if (signUpStatus == ApiWorker.SignUpStatus.LOGIN_EXIST_ERROR)
                Toast.makeText(SignupActivity.this, "Юзер с таким логином уже есть", Toast.LENGTH_SHORT).show();
            if (signUpStatus == ApiWorker.SignUpStatus.NOT_CONNECt)
                Toast.makeText(SignupActivity.this, "Нет коннекта", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
}