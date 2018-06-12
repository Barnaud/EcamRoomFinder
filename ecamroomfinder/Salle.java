package com.example.benoitarnaud.ecamroomfinder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.HashMap;

import static android.graphics.Bitmap.Config.ARGB_8888;

public class Salle extends Place {
    private Point pointAdj;
    private int[] etageResource;
    public Salle() {
    }
    public Salle(String nom, int etage, float[] position, Point pointAdj){
        this.nom = nom;
        this.etage = etage;
        this.position = position;
        this.pointAdj = pointAdj;
        if (pointAdj.getSallesAdj().indexOf(this)==-1){
            pointAdj.addSallesAdj(this);
            etageResource = new int[]{R.drawable.e0, R.drawable.e1, R.drawable.e2};
        }
    }

    public Point getPointAdj() {
        return pointAdj;
    }

    public void setPointAdj(Point pointAdj) {
        this.pointAdj = pointAdj;
        if (pointAdj.getSallesAdj().indexOf(this)==-1 ){
            pointAdj.addSallesAdj(this);
        }

    }
    public void displayStart(Canvas canvas, Paint paint, Resources resources){

        canvas.drawCircle(canvas.getWidth()*this.position[0]/100, canvas.getHeight()*this.position[1]/100, 5, paint);
        canvas.drawCircle(canvas.getWidth()*this.position[0]/100, canvas.getHeight()*this.position[1]/100, 40, paint);
        Bitmap depart = BitmapFactory.decodeResource(resources,R.drawable.depart);
        canvas.drawBitmap(depart, canvas.getWidth()*this.position[0]/100+30, canvas.getHeight()*this.position[1]/100+40, paint);
    }
    public void displayArrival(Canvas canvas, Paint paint, Resources resources){
        canvas.drawRect((canvas.getWidth()*this.position[0]/100)-40, (canvas.getHeight()*((this.position[1])/100))+40, canvas.getWidth()*((this.position[0])/100)+40, (canvas.getHeight()*((this.position[1])/100))-40, paint);
        Bitmap arrival = BitmapFactory.decodeResource(resources, R.drawable.arrival);
        canvas.drawBitmap(arrival, canvas.getWidth()*this.position[0]/100+30, canvas.getHeight()*this.position[1]/100+30, paint);
    }
    public void displayMap(Canvas canvas, Paint paint, Resources resources){
        Bitmap carte;
        switch (etage) {
            case 0:
                carte = BitmapFactory.decodeResource(resources, R.drawable.e0);
                carte.copy(ARGB_8888 ,true);
                canvas.drawBitmap(carte, null, new Rect(0,0,canvas.getWidth(), canvas.getHeight()), null);
                break;
            case 1:
                carte = BitmapFactory.decodeResource(resources, R.drawable.e1);
                carte.copy(ARGB_8888 ,true);
                canvas.drawBitmap(carte, null, new Rect(canvas.getWidth()*15/100, 0, canvas.getWidth()*85/100, canvas.getHeight()), null);
                break;
            case 2:
                carte= BitmapFactory.decodeResource(resources, R.drawable.e2);
                carte.copy(ARGB_8888 ,true);
                canvas.drawBitmap(carte, null, new Rect(canvas.getWidth()*15/100, 0, canvas.getWidth()*85/100, canvas.getHeight()), null);
            break;


        }
    }
    public void goToPoint(Canvas canvas, Paint paint){
        canvas.drawLine(this.position[0]*canvas.getWidth()/100, this.position[1]*canvas.getHeight()/100, canvas.getWidth()*this.pointAdj.position[0]/100,canvas.getHeight()*this.pointAdj.position[1]/100, paint );
    }
    public void pointToPoint(Salle salle, Canvas canvas, Paint paint){
        canvas.drawLine(this.pointAdj.position[0]*canvas.getWidth()/100, this.pointAdj.position[1]*canvas.getHeight()/100, salle.pointAdj.position[0]*canvas.getWidth()/100, salle.pointAdj.position[1]*canvas.getHeight()/100, paint);
    }
    public void findPath(Salle salle, Canvas canvas, Paint paint, Resources resources){
        this.displayMap(canvas, paint,resources);
        Paint whiteSquare = new Paint();
        whiteSquare.setColor(Color.WHITE);
        whiteSquare.setStyle(Paint.Style.FILL_AND_STROKE);

        if(Arrays.asList(MainActivity.sallesComplexes).contains(this) && Arrays.asList(MainActivity.sallesComplexes).contains(salle)){
            this.displayMap(canvas, paint, resources);
            this.displayStart(canvas, paint, resources);
            salle.displayArrival(canvas, paint, resources);
            salle.goToPoint(canvas, paint);
            this.goToPoint(canvas, paint);


            if (this.nom == "105" || salle.nom == "105"){
                MainActivity.salles.get("101").pointToPoint(MainActivity.salles.get("105"), canvas, paint);
                if(this.nom !="101"&& salle.nom!="101") {
                    MainActivity.salles.get("102").pointToPoint(MainActivity.salles.get("101"), canvas, paint);
                }

            }else{
                if (this.nom == "101" || salle.nom == "101"){
                    MainActivity.salles.get("102").pointToPoint(MainActivity.salles.get("101"), canvas, paint);

                }  
            }



        }

        else if (this.pointAdj == salle.pointAdj){
            this.displayStart(canvas, paint, resources);
            salle.displayArrival(canvas, paint, resources);
            canvas.drawLine(this.position[0]*canvas.getWidth()/100, this.position[1]*canvas.getHeight()/100,salle.position[0]*canvas.getWidth()/100, salle.position[1]*canvas.getHeight()/100, paint);

        }

        else if(this.etage == salle.etage && !Arrays.asList(MainActivity.sallesComplexes).contains(this) && !Arrays.asList(MainActivity.sallesComplexes).contains(salle)){
            this.displayStart(canvas, paint, resources);
            salle.displayArrival(canvas, paint, resources);
            this.goToPoint(canvas, paint);
            salle.goToPoint(canvas, paint);
            this.pointToPoint(salle, canvas, paint);

        }else if (this.nom == "207"){
            if(MapActivity.touched){
                /*
                salle.displayMap(canvas, paint, resources);
                salle.displayArrival(canvas, paint, resources);
                salle.goToPoint(canvas, paint);
                MainActivity.salles.get("e1s2").goToPoint(canvas, paint);
                salle.pointToPoint(MainActivity.salles.get("e1s2"), canvas, paint);
                */if(salle.etage == 1){
                    MainActivity.salles.get("e1s2").findPath(salle, canvas, paint,resources );
                    }
                    else{
                    MainActivity.salles.get("e0s2").findPath(salle, canvas, paint,resources );
                }

                }
                else{
                //MainActivity.drawing.invalidate();
                /*this.displayStart(canvas, paint, resources);
                this.goToPoint(canvas,paint);
                this.pointAdj.nrstStairs.goToPoint(canvas, paint);
                this.pointToPoint(this.pointAdj.nrstStairs, canvas, paint);
                Bitmap map = BitmapFactory.decodeResource(resources,R.drawable.e1);
                */
                this.findPath(MainActivity.salles.get("e2s2"), canvas, paint,resources );
            }
        }
        else if (salle.nom == "207"){
            if(!MapActivity.touched){
                /*
                salle.displayMap(canvas, paint, resources);
                salle.displayArrival(canvas, paint, resources);
                salle.goToPoint(canvas, paint);
                MainActivity.salles.get("e1s2").goToPoint(canvas, paint);
                salle.pointToPoint(MainActivity.salles.get("e1s2"), canvas, paint);
                */if(this.etage == 1){
                    this.findPath(MainActivity.salles.get("e1s2"), canvas, paint,resources );
                }
                else{
                    this.findPath(MainActivity.salles.get("e0s2"), canvas, paint,resources );
                }

            }
            else{
                //MainActivity.drawing.invalidate();
                /*salle.displayArrival(canvas, paint, resources);
                salle.goToPoint(canvas,paint);
                salle.pointAdj.nrstStairs.goToPoint(canvas, paint);
                salle.pointToPoint(this.pointAdj.nrstStairs, canvas, paint);
                Bitmap map = BitmapFactory.decodeResource(resources,R.drawable.e1);
                */

                canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), whiteSquare);
                MainActivity.salles.get("e2s2").findPath(salle, canvas, paint, resources);
            }
        }
        else if(this.etage != salle.etage && !Arrays.asList(MainActivity.sallesComplexes).contains(this) && !Arrays.asList(MainActivity.sallesComplexes).contains(salle)){
            if(!MapActivity.touched){
                canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), whiteSquare);
                this.findPath(this.pointAdj.nrstStairs, canvas, paint, resources);
            }
            else{
                canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), whiteSquare);
                MainActivity.stairLink.get(this.pointAdj.nrstStairs).findPath(salle, canvas, paint, resources);

            }

        }
        else{

            if(Arrays.asList(MainActivity.sallesComplexes).contains(this)){
                if (!MapActivity.touched) {


                //si départ d'une salle complexe

                this.displayStart(canvas, paint, resources);
                this.goToPoint(canvas,paint);
                if(this.nom !="105") {
                    this.pointToPoint(MainActivity.salles.get("101"), canvas, paint);
                }
                if(salle.nom == "101"){
                    MainActivity.salles.get("101").goToPoint(canvas, paint);
                    MainActivity.salles.get("101").displayArrival(canvas, paint, resources);
                }
                else{
                    MainActivity.salles.get("105").pointToPoint(MainActivity.salles.get("101"), canvas, paint);
                    if (salle.nom == "105"){
                        MainActivity.salles.get("105").goToPoint(canvas, paint);
                        MainActivity.salles.get("105").displayArrival(canvas, paint, resources);
                    }
                    else{
                            MainActivity.salles.get("105").pointToPoint(MainActivity.salles.get("blank"), canvas, paint);
                            MainActivity.salles.get("blank").pointToPoint(MainActivity.salles.get("e1s1"), canvas, paint);
                            //MainActivity.salles.get("e1s1").findPath(salle, canvas, paint, resources);
                        if(salle.etage == 1 && salle.nom!="e1s2"){
                            MainActivity.salles.get("e1s1").pointToPoint(MainActivity.salles.get("108"), canvas, paint);
                            salle.displayArrival(canvas, paint, resources);
                            salle.goToPoint(canvas, paint);

                        }
                        else if(salle.etage ==0){
                            MainActivity.salles.get("e1s1").goToPoint(canvas,paint);
                        }
                        else{

                            MainActivity.salles.get("e1s1").pointToPoint(MainActivity.salles.get("e1s2"), canvas, paint);
                            MainActivity.salles.get("e1s2").goToPoint(canvas, paint);
                        }


                    }
                }
            }
                else{
                    if(salle.nom=="207"){
                        canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), whiteSquare);
                        MainActivity.salles.get("e2s2").findPath(salle, canvas, paint, resources);
                    }
                    else if (salle.etage == 0){
                        MainActivity.salles.get("e0s1").findPath(salle, canvas, paint, resources);
                    }
                }
            }
            //Ici
            else if(Arrays.asList(MainActivity.sallesComplexes).contains(salle)){
                if (MapActivity.touched || this.etage ==1) {


                    //si arrivée à une salle complexe


                    canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), whiteSquare);
                    salle.displayMap(canvas, paint, resources);
                    salle.goToPoint(canvas,paint);
                    salle.displayArrival(canvas, paint, resources);



                        MainActivity.salles.get("105").pointToPoint(MainActivity.salles.get("blank"), canvas, paint);
                        MainActivity.salles.get("blank").pointToPoint(MainActivity.salles.get("e1s1"), canvas, paint);





                         if (salle.nom!= "105"){
                             salle.pointToPoint(MainActivity.salles.get("101"), canvas, paint);
                            MainActivity.salles.get("105").pointToPoint(MainActivity.salles.get("101"), canvas, paint);
                            //MainActivity.salles.get("105").goToPoint(canvas, paint);
                            //MainActivity.salles.get("105").displayArrival(canvas, paint, resources);
                        }



                            //MainActivity.salles.get("e1s1").findPath(salle, canvas, paint, resources);
                            if(this.etage == 1 && this.nom !="e1s2"){
                                MainActivity.salles.get("e1s1").pointToPoint(MainActivity.salles.get("108"), canvas, paint);
                                this.displayStart(canvas, paint, resources);
                                this.goToPoint(canvas, paint);

                            }
                            else if(this.etage ==0){
                                MainActivity.salles.get("e1s1").goToPoint(canvas,paint);
                            }
                            else{

                                MainActivity.salles.get("e1s1").pointToPoint(MainActivity.salles.get("e1s2"), canvas, paint);
                                MainActivity.salles.get("e1s2").goToPoint(canvas, paint);
                            }




                }
                else{
                    if(this.nom=="207"){
                        canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), whiteSquare);
                        MainActivity.salles.get("e2s2").findPath(salle, canvas, paint, resources);
                    }
                    else{
                        this.findPath(MainActivity.salles.get("e0s1"), canvas, paint, resources);
                    }
                }
            }
        }

    }
}

