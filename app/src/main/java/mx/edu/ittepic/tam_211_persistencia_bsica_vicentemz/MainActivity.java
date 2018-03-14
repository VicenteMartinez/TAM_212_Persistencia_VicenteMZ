package mx.edu.ittepic.tam_211_persistencia_bsica_vicentemz;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    EditText nombre,celular,dire,fecha,hora,platillos,postres;
    CheckBox basico,lujo;
    Button guardar,mostrar;
    TextView progreso;
    SeekBar see;
    int pro=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.nombre);
        guardar=findViewById(R.id.guardar);
        mostrar=findViewById(R.id.mostrar);
        celular=findViewById(R.id.nnombre);
        dire=findViewById(R.id.direccion);
        fecha=findViewById(R.id.fecha);
        hora=findViewById(R.id.hora);
        platillos=findViewById(R.id.plati);
        postres=findViewById(R.id.postres);

        basico=findViewById(R.id.basi);
        lujo=findViewById(R.id.ujoo);

        see=findViewById(R.id.seekBar);
        progreso=findViewById(R.id.seektext);

        see.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                progreso.setText(""+progressChangedValue);
                pro=progressChangedValue;
            }
        });

        cargarPreferencias();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences= getSharedPreferences("Credenciales2",MODE_PRIVATE);

                SharedPreferences.Editor editor=preferences.edit();

                editor.putString("nombre", nombre.getText().toString());
                editor.putString("cel", celular.getText().toString());
                editor.putString("dire", dire.getText().toString());
                editor.putString("fecha", fecha.getText().toString());
                editor.putString("hora", hora.getText().toString());
                editor.putString("plati", platillos.getText().toString());
                editor.putString("post", postres.getText().toString());
                editor.putString("see", progreso.getText().toString());
                if(basico.isChecked()){
                    editor.putBoolean("basi",true);
                }
                if(lujo.isChecked()){
                    editor.putBoolean("lujo",true);
                }
                editor.commit();

                Toast.makeText(MainActivity.this,"Guardado",Toast.LENGTH_SHORT).show();
            }
        });
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarPreferencias();
            }
        });

    }

    private void cargarPreferencias() {
        SharedPreferences preferences= getSharedPreferences("Credenciales2",MODE_PRIVATE);

        nombre.setText(preferences.getString("nombre",""));
        celular.setText(preferences.getString("cel",""));
        dire.setText(preferences.getString("dire",""));
        fecha.setText(preferences.getString("fecha",""));
        hora.setText(preferences.getString("hora",""));
        platillos.setText(preferences.getString("plati",""));
        postres.setText(preferences.getString("post",""));

        pro=parseInt(preferences.getString("see","1"));
        progreso.setText(preferences.getString("see","1"));

        see.setProgress(pro);

        if(preferences.getBoolean("basi",false)){
            basico.setChecked(true);
        }else{basico.setChecked(false);}

        if(preferences.getBoolean("lujo",false)){
            lujo.setChecked(true);
        }else{lujo.setChecked(false);}


    }
}
