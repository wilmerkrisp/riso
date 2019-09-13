package life.expert.riso.domain.model;



import java.util.Optional;

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.entity
//                           wilmer 2019/07/23
//
//--------------------------------------------------------------------------------

/**
 * This interface uses shapes to display itself on the screen
 */
public interface Drawing
	{
	
	/**
	 * Gets max X coordinate of a screen. On this coordinate, too, can be draw
	 *
	 * @return the x max
	 */
	public int getXMax();
	
	/**
	 * Gets max Y coordinate of a screen. On this coordinate, too, can be draw
	 *
	 * @return the y max
	 */
	public int getYMax();
	
	/**
	 * Put screen char.
	 *
	 * @param x
	 * 	the x
	 * @param y
	 * 	the y
	 * @param c
	 * 	the c
	 */
	public void putPixel( int x ,
	                      int y ,
	                      char c );
	
	/**
	 * Gets screen char.
	 *
	 * @param x
	 * 	the x
	 * @param y
	 * 	the y
	 *
	 * @return the pixel
	 */
	public Optional<Character> getPixel( int x ,
	                                     int y );
		
	}
