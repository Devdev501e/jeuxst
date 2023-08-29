package POO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ranged extends Weapon{

    int amo;

    public Ranged (double damage, int amo) {


       this.damage=damage;
       this.amo=amo;
       this.dossier="Ranged/";
    }

    @Override
    public void Use(ImageView imageView, Boolean right1, SimpleDoubleProperty Hgx, SimpleDoubleProperty Hgy, SimpleDoubleProperty IHGX, SimpleDoubleProperty IHGY) {



    }
    @Override
    public String getDossier(){

        return this.dossier;
    }
}
