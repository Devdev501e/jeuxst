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
    public ImageView Use(ImageView imageView, Boolean right1, SimpleDoubleProperty Hgx, SimpleDoubleProperty Hgy, SimpleDoubleProperty IHGX, SimpleDoubleProperty IHGY,boolean down){
        double X=0.08;
        double Y =0.01;
         if(right1) {
             IHGY.set(Hgy.get()-Y);
             IHGX.set(Hgx.get()+X);
             imageView.setRotate(90);

         }else{
              IHGY.set(Hgy.get()-Y);
             IHGX.set(Hgx.get()-X);
             imageView.setRotate(-90);

         }
         return imageView;
    }

    @Override
    public String getDossier() {
        return this.dossier;
    }
}
