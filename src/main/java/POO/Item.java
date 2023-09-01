package POO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

public abstract class Item {


    String dossier;
    int id;





    public abstract ImageView Use(ImageView imageView, Boolean right1, SimpleDoubleProperty Hgx, SimpleDoubleProperty Hgy, SimpleDoubleProperty IHGX, SimpleDoubleProperty IHGY,boolean down);

    public abstract String getDossier();

    public int getId(){
        return this.id;}



}
