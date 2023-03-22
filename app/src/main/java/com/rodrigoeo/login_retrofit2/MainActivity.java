package com.rodrigoeo.login_retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText user, password;
    Button btnLogin;

    String mLatitud, mLongitud;

    FusedLocationProviderClient fusedClient;
    private LocationCallback mCallback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    // login
                    goLogin();
                }
            }
        });
    }


    public void goLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser(user.getText().toString());
        loginRequest.setPassword(password.getText().toString());


        if (!BuildConfig.DEBUG) {
            locationWizardry(this);
            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();


        } else {

            Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        }
                    }, 1000);
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed Login " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }


    }


    @SuppressLint("MissingPermission")
    public void locationWizardry(Activity activity) {
        fusedClient = LocationServices.getFusedLocationProviderClient(activity);
        //Inicialmente, obtenemos la última ubicación conocida
        fusedClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLatitud= String.valueOf(location.getLatitude());
                    mLongitud = String.valueOf(location.getLongitude());

                    String lastLocatioon= mLatitud+mLongitud;
                    Log.d("RODRI", " " + lastLocatioon);
                    //lastLocation.setText(lastLocatioon);

                }
            }
        });




    }

}