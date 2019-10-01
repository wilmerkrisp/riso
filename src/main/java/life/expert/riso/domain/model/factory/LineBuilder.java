package life.expert.riso.domain.model.factory;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/09/11
//---------------------------------------------

import io.vavr.control.Try;
import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Figure;
import reactor.core.publisher.Mono;



/**
 * for using with abstract factory
 */
public interface LineBuilder
	{
	
	/**
	 * Start point line builder.
	 *
	 * @param x0
	 * 	the x 0
	 * @param y0
	 * 	the y 0
	 *
	 * @return the line builder
	 */
	public LineBuilder startPoint( final int x0 ,
	                               final int y0 );
	
	/**
	 * Start point line builder.
	 *
	 * @param startPoint
	 * 	the start point
	 *
	 * @return the line builder
	 */
	public LineBuilder startPoint( PositivePoint startPoint );
	
	/**
	 * Start point x line builder.
	 *
	 * @param x0
	 * 	the x 0
	 *
	 * @return the line builder
	 */
	public LineBuilder startPointX( int x0 );
	
	/**
	 * Start point y line builder.
	 *
	 * @param y0
	 * 	the y 0
	 *
	 * @return the line builder
	 */
	public LineBuilder startPointY( int y0 );
	
	/**
	 * End point line builder.
	 *
	 * @param x1
	 * 	the x 1
	 * @param y1
	 * 	the y 1
	 *
	 * @return the line builder
	 */
	public LineBuilder endPoint( final int x1 ,
	                             final int y1 );
	
	/**
	 * End point line builder.
	 *
	 * @param endPoint
	 * 	the end point
	 *
	 * @return the line builder
	 */
	public LineBuilder endPoint( PositivePoint endPoint );
	
	/**
	 * End point x line builder.
	 *
	 * @param x1
	 * 	the x 1
	 *
	 * @return the line builder
	 */
	public LineBuilder endPointX( int x1 );
	
	/**
	 * End point y line builder.
	 *
	 * @param y1
	 * 	the y 1
	 *
	 * @return the line builder
	 */
	public LineBuilder endPointY( int y1 );
	
	/**
	 * Filler line builder.
	 *
	 * @param character
	 * 	the character
	 *
	 * @return the line builder
	 */
	public LineBuilder filler( char character );
	
	/**
	 * Build mono mono.
	 *
	 * @return the mono
	 */
	public Mono<Figure> buildMono();
	
	/**
	 * Build try try.
	 *
	 * @return the try
	 */
	public Try<Figure> buildTry();
	
	/**
	 * Build figure.
	 *
	 * @return the figure
	 */
	public Figure build();
	}
