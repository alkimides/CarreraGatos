package com.example.carreragatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import static com.example.carreragatos.R.layout.abc_dialog_title_material;
import static com.example.carreragatos.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    VistaDibujo v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        v = new VistaDibujo(this);
        setContentView(v);
        v.reanudar();
    }
}
