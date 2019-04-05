package com.example.peloticasrebotando;

import android.widget.ImageView;

public class Pelotica {

    private float posicionX;
    private float posicionY;
    private float Xvelocidad;
    private float Yvelocidad;

    private ImageView imagen;

    public Pelotica(float posicionX, float posicionY, float Xvelocidad, float Yvelocidad, ImageView imagen) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.Xvelocidad = Xvelocidad;
        this.Yvelocidad = Yvelocidad;
        this.imagen = imagen;
    }


    public double getXvelocidad() {
        return Xvelocidad;
    }

    public void setXvelocidad(float xvelocidad) {
        Xvelocidad = xvelocidad;
    }

    public float getYvelocidad() {
        return Yvelocidad;
    }

    public void setYvelocidad(float yvelocidad) {
        Yvelocidad = yvelocidad;
    }

    public float getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(float posicionX) {
        this.posicionX = posicionX;
    }

    public float getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(float posicionY) {
        this.posicionY = posicionY;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
}
