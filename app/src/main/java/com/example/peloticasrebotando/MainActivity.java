package com.example.peloticasrebotando;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pelotica> peloticas = new ArrayList<>();
    private Pelotica p = null;
    private ImageView imagenP = null;

    private int ancho;
    private int alto;

    private float posicion = 0f;
    private Timer timer;

    private int alturaBar = 0;
    private int idRecursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = findViewById(R.id.layout);

        //Meter la imagen Bitmap para que la pelotica tenga imagen
        Bitmap pelotaImagen = BitmapFactory.decodeResource(getResources(), R.drawable.meteorito);

        timer = new Timer();

        //Le asignamos un tamaño a la pelota
        pelotaImagen = Bitmap.createScaledBitmap(pelotaImagen, 300,300, false);

        //Coger la medida de la nav bar

        Resources recursos = getApplicationContext().getResources();
        idRecursos = recursos.getIdentifier("navigation_bar_height", "dimen", "android");
        if (idRecursos > 0) {
            alturaBar = recursos.getDimensionPixelSize(idRecursos);
        }

        //Bucle para introducir la imagen a cada pelota
        for (int i=0; i < 3; i++){

            imagenP = new ImageView(getApplicationContext());
            //Le metemos la imagen que hemos puesto, al image view
            imagenP.setImageBitmap(pelotaImagen);

            //Creamos un objeto pelota
            Pelotica p = new Pelotica(0,0, 3*(i+1), 3*(i+1), imagenP);

            //La añadimos al array de pelotas
            peloticas.add(p);


            rl.addView(imagenP);
            posicion+=30;
        }



        //Metodo para coger las medidas de la pantalla
        DisplayMetrics medidas = this.getBaseContext().getResources().getDisplayMetrics();
        ancho = medidas.widthPixels;
        alto = medidas.heightPixels-alturaBar;


        TimerTask tTask = new TimerTask() {
            @Override
            public void run() {
                for (int i=0; i < peloticas.size(); i++) {
                    p = peloticas.get(i);
                    imagenP = peloticas.get(i).getImagen();


                    //Controlamos el movimiento en X de la pelotica
                    if (p.getPosicionX() < 0) {

                        p.setXvelocidad((float)p.getXvelocidad() * -1);
                        p.setPosicionX((float)(p.getPosicionX() + p.getXvelocidad()));

                    }else if(ancho > p.getPosicionX() && p.getPosicionX() >= 0.0) {

                        //Cambiamos velocidad y posicion de X
                        if (p.getPosicionX() >= ancho) {

                            p.setXvelocidad(p.getPosicionX() * -1);
                            p.setPosicionX((float)(p.getPosicionX() + p.getXvelocidad()));

                        } else {

                            imagenP.setX((float) (p.getPosicionX() + p.getXvelocidad()));
                            p.setPosicionX((float)(p.getPosicionX() + p.getXvelocidad()));
                        }
                    } else if (ancho <= p.getPosicionX()) {

                        p.setXvelocidad((float)p.getXvelocidad() * -1);
                        p.setPosicionX((float) (p.getPosicionX() + p.getXvelocidad()));

                    }else{


                    }

                    //Controlamos el movimiento en Y de la pelotica
                    if (p.getPosicionY() < 0.0) {

                        p.setYvelocidad(p.getYvelocidad() * -1);
                        p.setPosicionY(p.getPosicionY() + p.getYvelocidad());

                    }else if(alto > p.getPosicionY() && p.getPosicionY() >= 0.0) {

                        imagenP.setY(p.getPosicionY() + p.getYvelocidad());
                        p.setPosicionY(p.getPosicionY() + p.getYvelocidad());

                    } else if (alto <= p.getPosicionY()) {

                        p.setYvelocidad(p.getYvelocidad() * -1);
                        p.setPosicionY(p.getPosicionY() + p.getYvelocidad());

                    } else{


                    }
                }
            }
        };

        timer.schedule(tTask, 0, 10);
    }

}

