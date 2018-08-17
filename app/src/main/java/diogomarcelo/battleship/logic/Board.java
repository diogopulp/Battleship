package diogomarcelo.battleship.logic;

import java.util.ArrayList;

import diogomarcelo.battleship.logic.ships.Carrier;
import diogomarcelo.battleship.logic.ships.Cruiser;
import diogomarcelo.battleship.logic.ships.Destroyer;
import diogomarcelo.battleship.logic.ships.Submarine;

public class Board {

    Integer size;

    Submarine sub1, sub2;
    Destroyer destroyer1, destroyer2;
    Cruiser cruiser1, cruiser2;
    Carrier carrier;

    //ArrayList<Integer>

    public Integer getSize() {
        return size;
    }

    public Board() {
        this.size = 8;
    }

}
