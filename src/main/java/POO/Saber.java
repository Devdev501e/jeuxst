package POO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

public class Saber extends Weapon{

    double endurance;


    public Saber (double damage, int color, double endurance) {


        this.damage=damage;
        this.id=color;
        this.endurance=endurance;
        this.dossier="Saber/";
    }

@Override
    public ImageView Use(ImageView imageView, Boolean right1,boolean down){
        double X=0.02;
        double Y =0.06;
    this.HGY.set(this.HGY.get()+Y);
         if(right1) {

             this.HGX.set(this.HGX.get()+X);
             imageView.setRotate(90);

         }else{

             this.HGX.set(this.HGX.get()-X);
             imageView.setRotate(-90);

         }
         return imageView;
    }

    @Override
    public String getDossier() {
        return this.dossier;
    }
}
