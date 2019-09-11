package life.expert.riso.domain.model.value;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/30
//---------------------------------------------

//import static life.expert.common.base.Preconditions.*;  //checkCollection

import static reactor.core.publisher.Mono.*;
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Drawing;

import static life.expert.common.reactivestreams.Preconditions.*; //reactive check
import static life.expert.common.reactivestreams.Patterns.*;    //reactive helper functions

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.$;                            // pattern matching
import static io.vavr.API.Case;
import static io.vavr.API.Match;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions
import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Function;     //lambda->Function3
import static io.vavr.API.Tuple;

import io.vavr.control.Try;                               //try

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

import life.expert.riso.domain.model.DrawingFactory;
import life.expert.riso.domain.model.Figure;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import io.vavr.Tuple;
import io.vavr.Tuple5
	;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;

import com.google.common.collect.ComparisonChain;

import life.expert.common.function.TupleUtils;
import lombok.AccessLevel;

import reactor.core.publisher.Mono;

/**
 * simple immutable class: int int int int char
 *
 * - pattern new-call
 * - the class not for inheritance
 *
 * - only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
 * - 'of' - factory method is prohibited because it is intended only for easy creation of objects in tests, please use pure functional methods monoOf.., without raise exceptions.
 *
 * - in order not to do the same checks all the time, they are placed in special objects-preconditions.
 * Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
 * Nevertheless, for convenience, methods accepting simple parameters are available, then they internally use the same precondition objects for verification
 *
 *
 *
 *
 * Preconditions: in outer precondition-objects or inside (see explanation above)
 * Postconditions: none
 * Side effects: none
 * Tread safety:  Immutable
 */

@Value
@AllArgsConstructor( access = AccessLevel.PRIVATE )
@Patterns /*pattern matching in vavr*/
@Slf4j
public final class Rectangle
	implements Figure,
	           Comparable<Rectangle>
	{
	
	/**
	 * x0
	 *
	 * -- SETTER --
	 *
	 * @param x0
	 * 	x0
	 * @return x0
	 *
	 * 	-- GETTER --
	 * @return x0
	 * 	the x0
	 */
	private final int x0;
	
	/**
	 * y0
	 *
	 * -- SETTER --
	 *
	 * @param y0
	 * 	y0
	 * @return y0
	 *
	 * 	-- GETTER --
	 * @return y0
	 * 	the y0
	 */
	private final int y0;
	
	/**
	 * x1
	 *
	 * -- SETTER --
	 *
	 * @param x1
	 * 	x1
	 * @return x1
	 *
	 * 	-- GETTER --
	 * @return x1
	 * 	the x1
	 */
	private final int x1;
	
	/**
	 * y1
	 *
	 * -- SETTER --
	 *
	 * @param y1
	 * 	y1
	 * @return y1
	 *
	 * 	-- GETTER --
	 * @return y1
	 * 	the y1
	 */
	private final int y1;
	
	/**
	 * character
	 *
	 * -- SETTER --
	 *
	 * @param character
	 * 	character
	 * @return character
	 *
	 * 	-- GETTER --
	 * @return character
	 * 	the character
	 */
	private final char character;
	
	//todo !
	DrawingFactory factory=null;
	
	//	@Autowired
	//	public void setDrawingFiguresFactory(@Lazy DrawingFactory drawingFactory )
	//		{
	//		$drawingFiguresFactory=drawingFactory;
	//		}
	
	//<editor-fold desc="basic constructors">
	
	/*
	Other factories use this method to create an object.
	He himself calls the private constructor to create the object.
	* */
	private static Mono<Rectangle> monoOf_( final int x0 ,
	                                        final int y0 ,
	                                        final int x1 ,
	                                        final int y1 ,
	                                        final char character )
		{
		return fromSupplier( () -> new Rectangle( x0 , y0 , x1 , y1 , character ) );
		}
	
	/**
	 * Create Rectangle from simple arguments
	 * Only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
	 *
	 * - in order not to do the same checks all the time, they are placed in special objects-preconditions.
	 * Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
	 * Nevertheless, for convenience, methods accepting simple parameters are available, then they internally use the same precondition objects for verification
	 *
	 * @return the Mono with lazyli created object
	 *
	 * @implNote to create objects, this method calls the private factory monoOf_
	 * 	to verify objects, this method uses precondition-objects
	 */
	public static Mono<Rectangle> monoOf( final int x0 ,
	                                      final int y0 ,
	                                      final int x1 ,
	                                      final int y1 ,
	                                      final char character )
		{
		
		return PositivePoint.monoOf( x0 , y0 )
		                    .then( PositivePoint.monoOf( x1 , y1 ) )
		                    .then( monoOf_( x0 , y0 , x1 , y1 , character ) );
		}
	
	/**
	 * <pre>
	 * Classic fabric method for creating  Rectangle.
	 * This factory method is prohibited because it is intended only for easy creation of objects in tests
	 *
	 *
	 * @throws IllegalArgumentException if the input arguments do not satisfy the preconditions
	 * @deprecated please use pure functional methods monoOf.., without raise exceptions. </pre>
	 */
	@Deprecated
	public static Rectangle of( final int x0 ,
	                            final int y0 ,
	                            final int x1 ,
	                            final int y1 ,
	                            final char character )
		{
		return monoOf( x0 , y0 , x1 , y1 , character ).block();
		}
	
	/**
	 * Create Rectangle from Mono with Tuple inside
	 * The method helps chaining flows together
	 *
	 * @param tuple
	 * 	the tuple
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<Rectangle> monoOfMono( Mono<Tuple5<Integer,Integer,Integer,Integer,Character>> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input Mono must not be null." );
		else
			return tuple.flatMap( Rectangle::monoOfTuple );
		}
	
	/**
	 * Standard shallow copy factory
	 *
	 * @param other
	 * 	the other object
	 *
	 * @return the Mono with lazyli created object
	 *
	 * @implNote to create objects, this method calls the private factory monoOf_
	 */
	public static Mono<Rectangle> copyOf( final Rectangle other )
		{
		return monoOf_( other.getX0() , other.getY0() , other.getX1() , other.getY1() , other.getCharacter() );
		}
	
	//</editor-fold>
	
	//<editor-fold desc="using and outstanding preconditions">
	
	/**
	 * Create Rectangle from precondition-objects
	 * Only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
	 *
	 * - in order not to do the same checks all the time, they are placed in special objects-preconditions.
	 * Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
	 * Nevertheless, for convenience, methods accepting simple parameters are available, then they internally use the same precondition objects for verification
	 *
	 * @return the Mono with lazyli created object
	 *
	 * @implNote to create objects, this method calls the private factory monoOf_
	 * 	to verify objects, this method uses precondition-objects
	 */
	public static Mono<Rectangle> monoOfPreconditions( PositivePoint startPoint ,
	                                                   PositivePoint endPoint ,
	                                                   Character character )
		{
		return monoOf_( startPoint.getX() , startPoint.getY() , endPoint.getX() , endPoint.getY() , character );
		}
	
	/**
	 * Fabric method for creating objects wrapped into Try.
	 *
	 * For example, if this class is a precondition object and you need to check it and then pass it to the input of another object of the subject domain
	 * This method is supposed to be used when you need to get an error immediately (not lazily),
	 * for example, if the message is immediately returned to the user UI and not wait
	 * when, for example at night, lazy processing occurs and a user error is detected
	 *
	 * @return the Try with Success or Failure inside
	 */
	public static Try<Rectangle> tryOf( final int x0 ,
	                                    final int y0 ,
	                                    final int x1 ,
	                                    final int y1 ,
	                                    final char character )
		{
		return tryFromMono( monoOf( x0 , y0 , x1 , y1 , character ) );
		}
	
	//</editor-fold>
	
	//<editor-fold desc="object to tuple conversions">
	
	/**
	 * pattern matching in vavr
	 * The method helps with conversion operations Rectangle-&gt;Tuple
	 *
	 * - you need add static import to method with pattern matching
	 * import static life.expert.riso.common.RectanglePatterns.*;
	 */
	@Unapply
	public static Tuple5<Integer,Integer,Integer,Integer,Character> Rectangle( Rectangle object )
		{
		return Tuple.of( object.getX0() , object.getY0() , object.getX1() , object.getY1() , object.getCharacter() );
		
		}
	
	/**
	 * Create Rectangle from Tuple
	 * The method helps with conversion operations Tuple-&gt;Rectangle
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<Rectangle> monoOfTuple( Tuple5<Integer,Integer,Integer,Integer,Character> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input tuple must not be null." );
		else
			return TupleUtils.function( Rectangle::monoOf )
			                 .apply( tuple );
		}
	
	//</editor-fold>
	
	@Override
	public int compareTo( Rectangle o )
		{
		return ComparisonChain.start()
		                      .compare( this.x0 , o.x0 )
		                      .compare( this.y0 , o.y0 )
		                      .compare( this.x1 , o.x1 )
		                      .compare( this.y1 , o.y1 )
		                      .compare( this.character , o.character )
		                      .result();
		}
	
	@Override
	public Mono<Figure> draw( final Drawing canvas )
		{
		if( canvas == null )
			return illegalArgumentMonoError( "Canvas must not be empty" );
		else if( !( canvas.getXMax() >= this.getX0() && canvas.getXMax() >= this.getX1() && canvas.getYMax() >= this.getY0() && canvas.getYMax() >= this.getY1() ) )
			return illegalArgumentMonoError( "Figure must be inside canvas" );
		else
			return just( canvas ).flatMap( this::draw_ )
			                     .thenReturn( this );
		}
	
	private Mono<Void> draw_( final Drawing canvas )
		{
		//System.out.println( "Rectangle draw_ $drawingFiguresFactory$drawingFiguresFactory$drawingFiguresFactory$drawingFiguresFactory " + factory );
		
		var l1 = factory.newMonoOfLine( x0 , y0 , x1 , y0 , character );
		var l2 = factory.newMonoOfLine( x1 , y0 , x1 , y1 , character );
		var l3 = factory.newMonoOfLine( x1 , y1 , x0 , y1 , character );
		var l4 = factory.newMonoOfLine( x0 , y1 , x0 , y0 , character );
		
		return l1.flatMap( f -> f.draw( canvas ) )
		         .then( l2 )
		         .flatMap( f -> f.draw( canvas ) )
		         .then( l3 )
		         .flatMap( f -> f.draw( canvas ) )
		         .then( l4 )
		         .flatMap( f -> f.draw( canvas ) )
		         .then();
		}
		
	}
