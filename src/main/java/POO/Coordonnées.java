package POO;

import javafx.beans.property.SimpleDoubleProperty;

public abstract class Coordonnées {



    SimpleDoubleProperty HGY =new SimpleDoubleProperty();
    SimpleDoubleProperty HGX =new SimpleDoubleProperty();

   // public abstract SimpleDoubleProperty getHGY();
  //  public abstract  SimpleDoubleProperty getHGX();


    public SimpleDoubleProperty getHGY() {
        return this.HGY;
    }


    public SimpleDoubleProperty getHGX() {
        return this.HGX;

    }



}
