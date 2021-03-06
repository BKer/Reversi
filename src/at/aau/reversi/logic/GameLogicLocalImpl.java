package at.aau.reversi.logic;

import at.aau.reversi.Constants;

public class GameLogicLocalImpl implements GameLogic {
	
	short[][] gameField;

	// Reihenfolge um Nachbarn zu durchlaufen
	private final int I_WAY[] = {-1, -1, -1,  0,  1,  1,  1,  0};
	private final int J_WAY[] = {-1,  0,  1,  1,  1,  0, -1, -1};
	
	public GameLogicLocalImpl(){
		gameField= new short[8][8];
		gameField[3][3] = Constants.FIELD_WHITE; 
		gameField[4][4] = Constants.FIELD_WHITE; 
		gameField[4][3] = Constants.FIELD_BLACK; 
		gameField[3][4] = Constants.FIELD_BLACK;
	}
	
	@Override
	public short[][] calcNewGameField(short xCoord,
			short yCoord, short color) {
		// TODO Algorithm which sets the Colors
		return null;
	}
	
	public boolean validMove(short xCoord, short yCoord, short color){
		// Feld muss leer und im Spielfeld sein
		if (gameField[xCoord][yCoord] == Constants.FIELD_EMPTY && inGamefield(xCoord, yCoord)){
		// Alle nachbarn muessen ueberprueft werden
	        for (int w=0; w <= 7; w++){                              
	    		// Nachbar muss nur ueberprueft werden wenn er im Spielfeld ist
	        	if (inGamefield(xCoord+I_WAY[w], yCoord+J_WAY[w]))
	        		// Ist der Nachbar gueltig
	        		if (validNeighbour(xCoord+I_WAY[w], yCoord+J_WAY[w], color))
	        			// Handelt es sich um eine gueltige Reihe
	        			if (validMove(xCoord+2*I_WAY[w], yCoord+2*J_WAY[w], color, w))  
	        				return true;  
	        }
		}
		return false;
	}

    /**
     * Ueberprueft, ob ein Stein in den Spielfeldgrenzen liegt
     * @param xCoord Zeile
     * @param yCoord Spalte
     * @return true/false
     */
    private boolean inGamefield (int xCoord, int yCoord) {
       return (xCoord >= 0 && xCoord < 8 && yCoord >= 0 && yCoord < 8);
    }	
	
    /**
     * Ueberprueft, ob angrenzendes Feld gueltig ist, 
     * d.h. es darf weder leer noch die eigene Spielfarbe besitzen
     * @param xCoord Zeile
     * @param yCoord Spalte
     * @param color Spielerfarbe
     * @return true/false
     */
    private boolean validNeighbour (int xCoord, int yCoord, int color) {
       return (gameField[xCoord][yCoord] != color && gameField[xCoord][yCoord] != Constants.FIELD_EMPTY);
    }
		
    /**
     * Ueberprueft, ob eine gueltige Reihe gefunden wurde
     * @param xCoord Zeile
     * @param yCoord Spalte
     * @param color Spielerfarbe
     * @param w Aktuell zu pruefender Weg
     * @return true/false
     */
    private boolean validMove(int xCoord, int yCoord, int color, int w) {
       if (inGamefield(xCoord, yCoord))                    
          if (gameField[xCoord][yCoord] == color)            
             return true;                         
          else if (gameField[xCoord][yCoord] == Constants.FIELD_EMPTY)   
                  return false;                   
               else                               
            	   // Pruefe naechstes Feld in der Reihe
                  return validMove(xCoord+I_WAY[w], yCoord+J_WAY[w], color, w);
       return false;
    }

	@Override
	public short[][] getGameField() {
		return gameField;
	}
}
