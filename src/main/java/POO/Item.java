package POO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;

public abstract class Item extends Coordonnées{


    String dossier;
    int id;





    public abstract ImageView Use(ImageView imageView, Boolean right1,boolean down);

    public abstract String getDossier();

    public int getId(){
        return this.id;}


}
