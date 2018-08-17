package diogomarcelo.battleship.logic.ships;

import java.util.ArrayList;

public abstract class Ship {

    String name;
    Integer height;
    Integer width;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }



    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> drawShip(){

        ArrayList<Integer> vec;
        vec = new ArrayList<>(2);
        vec.add(this.height);
        vec.add(this.width);

        return vec;
    }
}
