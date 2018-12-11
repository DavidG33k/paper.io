package se.liu.ida.paperio;

import java.awt.Color;

public class Tile {

    private Player owner;
    private Color color = Color.white;
    private Player contestedOwner;
    private int x;
    private int y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Decides what color to be drawn on the Tile a player is on
     * @return The color that is going to be drawn
     */
    public Color getColor(){
        // If a Tile has an owner and Tile is not being contested,
        // returns owner's color darkened
        if(owner != null && contestedOwner == null) {
            return owner.getColor().darker();
        }
            // If Tile has no owner and is being contested,
            // returns contestedOwner's color with an alpha of 100
        else if (owner == null && contestedOwner != null) {
            Color clr = new Color(contestedOwner.getColor().getRed(), contestedOwner.getColor().getGreen(),
                    contestedOwner.getColor().getBlue(), 100);
            return clr;
        }
            // If Tile has an owner and is being contested by someone,
            // returns contestedOwner's color with an alpha of 100
        else if (owner != null && contestedOwner != owner){
            return blendColors();
        }else{
            return color;
        }
    }

    public Color blendColors(){
        float blendedRed = ((owner.getColor().getRed() / 255f) * (contestedOwner.getColor().getRed() / 255f));
        float blendedGreen = ((owner.getColor().getGreen() / 255f) * (contestedOwner.getColor().getGreen() / 255f));
        float blendedBlue = ((owner.getColor().getBlue() / 255f) * (contestedOwner.getColor().getBlue() / 255f));

        Color clr = new Color(((blendedRed + 1 ) / 2),((blendedGreen + 1) / 2),((blendedBlue +1 )/ 2));

        return clr;
    }

    /**
     * @return Player that is contesting Tile
     */
    public Player getContestedOwner() {
        return contestedOwner;
    }

    /** Sets a contester to Tile
     * @param contestedOwner Player that is contesting Tile
     */
    public void setContestedOwner(Player contestedOwner) {
        this.contestedOwner = contestedOwner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        contestedOwner = null;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
