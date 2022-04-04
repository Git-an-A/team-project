package team.project;

/**
 * Creates and defines the tiles
 */
public class TileGroup {
    private String letterID;
    private int numbers;

    /**
     * Instance Variables
     *
     * @param lett is the letters of the tiles
     * @param num is the numbers of the tiles
     */
    public TileGroup(String lett, int num){
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
