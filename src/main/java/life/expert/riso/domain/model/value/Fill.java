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

import life.expert.riso.domain.model.Drawing;
import life.expert.riso.domain.model.Figure;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import io.vavr.Tuple;
import io.vavr.Tuple3
	;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;

import com.google.common.collect.ComparisonChain;

import life.expert.common.function.TupleUtils;
import lombok.AccessLevel;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.stream.IntStream;

import java.util.ArrayDeque;

import life.expert.riso.common.PositivePoint;
import reactor.util.function.Tuple2;

/**
 * simple immutable class: int int char
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
public final class Fill
	implements Figure,
	           Comparable<Fill>
	{
	
	/**
	 * x
	 *
	 * -- SETTER --
	 *
	 * @param x
	 * 	x
	 * @return x
	 *
	 * 	-- GETTER --
	 * @return x
	 * 	the x
	 */
	private final int x;
	
	/**
	 * y
	 *
	 * -- SETTER --
	 *
	 * @param y
	 * 	y
	 * @return y
	 *
	 * 	-- GETTER --
	 * @return y
	 * 	the y
	 */
	private final int y;
	
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
	
	//<editor-fold desc="basic constructors">
	
	/*
	Other factories use this method to create an object.
	He himself calls the private constructor to create the object.
	* */
	private static Mono<Fill> monoOf_( final int x ,
	                                   final int y ,
	                                   final char character )
		{
		return fromSupplier( () -> new Fill( x , y , character ) );
		}
	
	/**
	 * Create Fill from simple arguments
	 * Only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
	 *
	 * - in order not to do the same checks all the time, they are placed in special objects-preconditions.
	 * Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
	 * Nevertheless, for convenience, methods accepting simple parameters are available, then they internally use the same precondition objects for verification
	 *
	 * @param x
	 * 	the x
	 * @param y
	 * 	the y
	 * @param character
	 * 	the character
	 *
	 * @return the Mono with lazyli created object
	 *
	 * @implNote to create objects, this method calls the private factory monoOf_ 	to verify objects, this method uses precondition-objects
	 */
	public static Mono<Fill> monoOf( final int x ,
	                                 final int y ,
	                                 final char character )
		{
		return PositivePoint.monoOf( x , y )
		                    .then( monoOf_( x , y , character ) );
		}
	
	/**
	 * <pre>
	 * Classic fabric method for creating  Fill.
	 * This factory method is prohibited because it is intended only for easy creation of objects in tests
	 *
	 *
	 * @param x the x
	 * @param y the y
	 * @param character the character
	 * @return the fill
	 * @throws IllegalArgumentException if the input arguments do not satisfy the preconditions
	 * @deprecated please use pure functional methods monoOf.., without raise exceptions. </pre>
	 */
	@Deprecated
	public static Fill of( final int x ,
	                       final int y ,
	                       final char character )
		{
		return monoOf( x , y , character ).block();
		}
	
	/**
	 * Create Fill from Mono with Tuple inside
	 * The method helps chaining flows together
	 *
	 * @param tuple
	 * 	the tuple
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<Fill> monoOfMono( Mono<Tuple3<Integer,Integer,Character>> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input Mono must not be null." );
		else
			return tuple.flatMap( Fill::monoOfTuple );
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
	public static Mono<Fill> copyOf( final Fill other )
		{
		return monoOf_( other.getX() , other.getY() , other.getCharacter() );
		}
	
	//</editor-fold>
	
	//<editor-fold desc="using and outstanding preconditions">
	
	/**
	 * Create Fill from precondition-objects
	 * Only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
	 *
	 * - in order not to do the same checks all the time, they are placed in special objects-preconditions.
	 * Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
	 * Nevertheless, for convenience, methods accepting simple parameters are available, then they internally use the same precondition objects for verification
	 *
	 * @param point
	 * 	the point
	 * @param character
	 * 	the character
	 *
	 * @return the Mono with lazyli created object
	 *
	 * @implNote to create objects, this method calls the private factory monoOf_ 	to verify objects, this method uses precondition-objects
	 */
	public static Mono<Fill> monoOfPreconditions( PositivePoint point ,
	                                              Character character )
		{
		return monoOf_( point.getX() , point.getY() , character );
		}
	
	/**
	 * Fabric method for creating objects wrapped into Try.
	 *
	 * For example, if this class is a precondition object and you need to check it and then pass it to the input of another object of the subject domain
	 * This method is supposed to be used when you need to get an error immediately (not lazily),
	 * for example, if the message is immediately returned to the user UI and not wait
	 * when, for example at night, lazy processing occurs and a user error is detected
	 *
	 * @param x
	 * 	the x
	 * @param y
	 * 	the y
	 * @param character
	 * 	the character
	 *
	 * @return the Try with Success or Failure inside
	 */
	public static Try<Fill> tryOf( final int x ,
	                               final int y ,
	                               final char character )
		{
		return tryFromMono( monoOf( x , y , character ) );
		}
	
	//</editor-fold>
	
	//<editor-fold desc="object to tuple conversions">
	
	/**
	 * pattern matching in vavr
	 * The method helps with conversion operations Fill-&gt;Tuple
	 *
	 * - you need add static import to method with pattern matching
	 * import static life.expert.riso.common.FillPatterns.*;
	 *
	 * @param object
	 * 	the object
	 *
	 * @return the tuple 3
	 */
	@Unapply
	public static Tuple3<Integer,Integer,Character> Fill( Fill object )
		{
		return Tuple.of( object.getX() , object.getY() , object.getCharacter() );
		
		}
	
	/**
	 * Create Fill from Tuple
	 * The method helps with conversion operations Tuple-&gt;Fill
	 *
	 * @param tuple
	 * 	the tuple
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<Fill> monoOfTuple( Tuple3<Integer,Integer,Character> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input tuple must not be null." );
		else
			return TupleUtils.function( Fill::monoOf )
			                 .apply( tuple );
		}
	
	@Override
	public int compareTo( Fill o )
		{
		return ComparisonChain.start()
		                      .compare( this.x , o.x )
		                      .compare( this.y , o.y )
		                      .compare( this.character , o.character )
		                      .result();
		}
	
	//</editor-fold>
	
	@Override
	public Mono<Figure> draw( final Drawing canvas )
		{
		if( canvas == null )
			return illegalArgumentMonoError( "Canvas must not be empty" );
		else if( !( canvas.getXMax() >= this.getX() && canvas.getYMax() >= this.getY() ) )
			return illegalArgumentMonoError( "Figure must be inside canvas" );
		else
			return just( canvas ).flatMap( this::draw_ )
			                     .thenReturn( this );
		}
	
	@SuppressWarnings( "unchecked" )
	private Mono<Void> draw_( final Drawing canvas )
		{
		var  h         = canvas.getYMax();
		var  w         = canvas.getXMax();
		char old_color = pixel_( canvas , x , y );
		
		var stack = new ArrayDeque<Tuple2>( 80 );
		for( var point = Tuples.of( x , y ) ;
		     point != null ;
		     point = stack.poll() )
			{
			int x_ = point.getT1();
			int y_ = point.getT2();
			
			//goto up
			y_ = IntStream.iterate( y_ , i -> i >= 1 , i -> i - 1 )
			              .takeWhile( i -> pixel_( canvas , x_ , i ) == old_color )
			              .min()
			              .orElse( 1 );
			
			//goto down
			for( boolean left = false, right = false /*variables for 'breaking' vertical 'lines'*/ ;
			     y_ <= h && pixel_( canvas , x_ , y_ ) == old_color ;
			     y_++ )
				{
				canvas.putPixel( x_ , y_ , character );
				
				if( !left && x_ > 1 && pixel_( canvas , x_ - 1 , y_ ) == old_color )
					{
					//add left vertical line for analysis
					stack.offer( Tuples.of( x_ - 1 , y_ ) );
					left = true;
					}
				else if( left && x_ > 1 && pixel_( canvas , x_ - 1 , y_ ) != old_color )
					{
					//if left vertical line breaks
					left = false;
					}
				
				if( !right && x_ < w && pixel_( canvas , x_ + 1 , y_ ) == old_color )
					{
					//add left vertical line for analysis
					stack.offer( Tuples.of( x_ + 1 , y_ ) );
					right = true;
					}
				else if( right && x_ < w && pixel_( canvas , x_ + 1 , y_ ) != old_color )
					{
					//if left vertical line breaks
					right = false;
					}
				}
				
			}
		
		return empty();
		}
	
	//helper method
	private char pixel_( Drawing canvas ,
	                     int x ,
	                     int y )
		{
		return canvas.getPixel( x , y )
		             .orElse( ' ' );
		}
		
	}
