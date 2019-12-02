package com.example.carreragatos;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VistaDibujo extends SurfaceView implements Runnable{
    Thread hiloDibujo;
    volatile boolean running;
    SurfaceHolder holder;
    int x;
    ObjetoAnimado gato;
    Fondo pista;
    public VistaDibujo(Context context) {
        super(context);
        holder = getHolder();
        pista = new Fondo("pista.jpg");
        pista.inicializar(context.getAssets());
        gato = new ObjetoAnimado(0,25,"gato.png");
        gato.inicializar(context.getAssets());
    }


    @Override
    public void run() {
    //funcion que dibuja
        x = 0;
        long actual = System.currentTimeMillis();
        while(running) {
            x++;
            x = x%256;
            if(System.currentTimeMillis() - actual == 80)
            {
                actual =System.currentTimeMillis();
                gato.nextFrame();
            }
            //bloqueamos holder
            if(holder.getSurface().isValid()) {
                //si es valido
                Canvas c=holder.lockCanvas();
                drawSurface(c);
                holder.unlockCanvasAndPost(c);
            }else
            {
                continue;
            }
        }
    }

    private void drawSurface(Canvas c) {
        //Esta funcion nos dice qué dibujamos(fondo y elementos animados en nuestro caso)
        pista.draw(c);
        gato.draw(c);
    }

    public void pausar(){
        running  = false;
        //esperamos a que pare
        while(true){
            try {
                hiloDibujo.join();
                break;
            }
            catch(Exception ex)
            {

            }
        }
        hiloDibujo = null;
    }

    public void reanudar(){
        hiloDibujo = new Thread(this);
        running = true;
        hiloDibujo.start();
    }

}
