package POO;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Personnage extends  ImageView {

    int life;

    public void moveUp() {

        System.out.println("MOVIN' UP!");
    }

    public void moveDown() {

        System.out.println("MOVIN' DOWN!");
    }

    public void moveLeft() {

        System.out.println("MOVIN' LEFT!");
    }

    public void moveRight() {

        System.out.println("MOVIN' RIGHT!");
    }

}
