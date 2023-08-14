package POO;

import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Jedi extends Force_users {
    int wise;
    public String image;
    public Jedi ( int life,int damage,int force,int color,int wise,String image) {
        this.image = image;
        this.life=life;
        this.damage=damage;
        this.force=force;
        this.color=color;
        this.wise=wise;
        vitesseX = 50;
        vitesseY = 50;
    }
    private double vitesseX;
    private double vitesseY;



    // Implémentez les méthodes de déplacement du personnage selon vos besoins
    // Par exemple :
   // public void deplacer() {imageView.setTranslateX(imageView.getTranslateX() + vitesseX);imageView.setTranslateY(imageView.getTranslateY() + vitesseY);}

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public String getImageView() {
        return image;
    }


}
