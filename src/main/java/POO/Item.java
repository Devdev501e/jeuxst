package POO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item {


    String dossier;




    public abstract void Use(ImageView imageView, Boolean right1, SimpleDoubleProperty Hgx,SimpleDoubleProperty Hgy,SimpleDoubleProperty IHGX,SimpleDoubleProperty IHGY);

    public abstract String getDossier();
}
