

package POO;
import javafx.application.Application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class Personnage extends  ImageView {

    int life;
    public Item item;
    public String image;



    public Item getItem(){

        return this.item;
    }

    public void changeItem(Item item){

        this.item=item;

    }
    public String getImageView() {
        return image;
    }


    public int walkAnimation(String direction,int leftAnimation, ImageView pers,boolean up1){

        Image  imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/jeuxst/image/mario/mario_Left.png")));

        if(!up1) {
            if(leftAnimation>=0 &&leftAnimation<=3){
                leftAnimation+=1;
              imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/jeuxst/image/" + this.image + "/" + this.image +"_"+direction+".png")));

            }else if (leftAnimation>=4 &&leftAnimation<=7){
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/jeuxst/image/" + this.image + "/" + this.image + "_"+direction+"1.png")));
                leftAnimation+=1;
            }else if (leftAnimation>=8 &&leftAnimation<=11){
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/jeuxst/image/" + this.image + "/" +this.image + "_"+direction+"2.png")));
                leftAnimation+=1;
            }else if (leftAnimation>=12 &&leftAnimation<=15){
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/jeuxst/image/" + this.image + "/" + this.image + "_"+direction+"1.png")));
                leftAnimation+=1;
            }else if (leftAnimation>=16){
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/jeuxst/image/" + this.image + "/" + this.image + "_"+direction+"3.png")));
                leftAnimation=0;
            }
            pers.setImage(imagePersonnage);
        }
        return leftAnimation;
    }

}
