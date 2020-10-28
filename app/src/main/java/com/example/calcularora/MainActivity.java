package com.example.calcularora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView pantalla;
    private String cifra = "", operador, op;
    private Double dcif1, dcif2, res;
    private Integer punto = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla = (TextView)findViewById(R.id.lbl1);
    }
    public static String mostrarNum(double d) {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    public void numeros(View view) {
        Button bnum = (Button) view;
        // Toast.makeText(this, "bnum: " + bnum.getText(), Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "pantalla: " + pantalla.getText(), Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "cifra: " + cifra, Toast.LENGTH_SHORT).show();
        boolean haypunto = cifra.contains(".");

        if (haypunto == false && bnum.getId() == R.id.bpunt) {
            if (cifra == "" || cifra == "0") {
                cifra = "0";
                cifra += bnum.getText();
            }
            else {
                cifra += bnum.getText();
            }
        }
        else if (bnum.getId() != R.id.bpunt){
            if (bnum.getId() == R.id.b0 && (cifra == "" || cifra == "0")) {
                cifra = "0";
            }
            else {
                if (cifra == "" || cifra == "0") {
                    cifra = bnum.getText().toString();
                }
                else {
                    cifra += bnum.getText();
                }

            }
        }
            pantalla.setText(cifra);
    }
    public void signos(View view) {
        Button bsig = (Button) view;
        if (dcif1 == null) {
            dcif1 = Double.parseDouble(pantalla.getText().toString().trim());
        }
        else{
            dcif2 = Double.parseDouble(pantalla.getText().toString().trim());

        }
        if (bsig.getId() == R.id.bporci){
            op = operador;
        }
        operador = bsig.getText().toString();
        if (dcif1 != null && dcif2 != null ) {
            operar();
            dcif1 = res;
            pantalla.setText(mostrarNum(res));
            res = null;
            dcif2 = null;
            operador = null;
        }
        cifra = "";
        //Toast.makeText(this, "op: " + op, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "operador: " + operador, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "dcif1: " + dcif1, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "dcif2: " + dcif2, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "res: " + res, Toast.LENGTH_SHORT).show();
    }
    public void igual(View view) {
        if (dcif1 != null) {
            Toast.makeText(this, "operador antes: " + operador, Toast.LENGTH_SHORT).show();
            if (op == null)
            {
                dcif2 = Double.parseDouble(pantalla.getText().toString().trim());
                operar();
                pantalla.setText(mostrarNum(res));
                dcif1 = null;
                dcif2 = null;
                res = null;
            }
            else {
                pantalla.setText(pantalla.getText().toString());
            }
        }
        Toast.makeText(this, "op: " + op, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "dcif1: " + dcif1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "dcif2: " + dcif2, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "operador después: " + operador, Toast.LENGTH_SHORT).show();
    }
    public void miclick(View view) {
    }
    public void operar() {
        if (dcif1 != null)
        {
            switch (operador){
                case "+":
                    res = dcif1 + dcif2;
                    break;
                case "−":
                    res = dcif1 - dcif2;
                    break;
                case "×":
                    res = dcif1 * dcif2;
                    break;
                case "÷":
                    res = dcif1 / dcif2;
                    break;
                case "%":
                    if (op == null ) {
                        if (operador == null)
                        {
                            res = dcif1 / 100;
                        }
                        else {
                            res = Double.parseDouble(pantalla.getText().toString());
                        }
                    }
                    else {
                        switch (op) {
                            case "+":
                                res = dcif1 + (dcif1 / 100 * dcif2);
                                break;
                            case "−":
                                res = dcif1 - (dcif1 / 100 * dcif2);
                                break;
                            case "×":
                                res = dcif1 * (dcif1 / 100 * dcif2);
                                break;
                            case "÷":
                                res = dcif1 / (dcif1 / 100 * dcif2);
                                break;
                        }
                    }
                    break;
            }
            cifra = "";
        }
    }
    public void borrar(View view) {
        Button bborrar = (Button) view;
        switch (bborrar.getId()) {
            case R.id.ac:
                cifra = "0";
                dcif1 = null;
                dcif2 = null;
                res = null;
                operador = null;
                op = null;
                break;
            case R.id.c:
                //if (pantalla.getText().toString() == "0") {
                //    operador = null;
                //    op = null;
                //}
                //else {
                    cifra = "0";
                //}
                break;
            case R.id.del:
                if (cifra.length() == 0) {
                    cifra = "0";
                }
                else {
                    cifra = cifra.substring(0, cifra.length() - 1);
                    if (cifra.length() == 0) {
                        cifra = "0";
                    }
                }
                break;
        }
        pantalla.setText(cifra);
        Toast.makeText(this, "cifra: " + cifra, Toast.LENGTH_SHORT).show();
    }
}