package team.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile {
    private String letterID;
    private int numbers;

    /**
     * Instance Variables
     *
     * @param lett is the letters of the tiles
     * @param num is the numbers of the tiles
     */
    public Tile(String lett, int num){
        this.letterID = lett;
        this.numbers = num;
    }

    public int getNumbers(){return numbers;}

    public String getLetterID() {
        return letterID;
    }

    @Override
    public String toString() {
        String sb = getLetterID() + getNumbers();
        return sb;
    }

}
