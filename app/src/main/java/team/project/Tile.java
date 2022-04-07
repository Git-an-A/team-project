package team.project;


public class Tile {
    private final String letterID;
    private final int numbers;

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

    public int getXpos(){
        return numbers;
    }

    public int getYpos(){
        switch (letterID){
            case "A" : return 1;
            case "B" : return 2;
            case "C" : return 3;
            case "D" : return 4;
            case "E" : return 5;
            case "F" : return 6;
            case "G" : return 7;
            case "H" : return 8;
            case "I" : return 9;
            default : return -1;
        }
    }

    @Override
    public String toString() {
        String sb = getLetterID() + getNumbers();
        return sb;
    }

}
