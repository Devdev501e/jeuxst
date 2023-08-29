package POO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Saber extends Weapon{
    int color;

    double endurance;


    public Saber (double damage, int color, double endurance) {


        this.damage=damage;
        this.color=color;
        this.endurance=endurance;
        this.dossier="Saber/";
    }

@Override
    public void Use(ImageView imageView, Boolean right1, SimpleDoubleProperty Hgx, SimpleDoubleProperty Hgy, SimpleDoubleProperty IHGX, SimpleDoubleProperty IHGY){
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
    }

    @Override
    public String getDossier() {
        return this.dossier;
    }
}
