package POO;

import javafx.beans.property.SimpleDoubleProperty;

public class Sith extends Force_users{
    int electrical;


    public Sith ( int life,int damage,int force,int electrical, String image) {
        this.image = image;
        this.life=life;
        this.damage=damage;
        this.force=force;

        this.electrical=electrical;
       }


}
