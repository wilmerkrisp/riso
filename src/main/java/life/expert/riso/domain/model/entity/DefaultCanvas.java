package life.expert.riso.domain.model.entity;



import static life.expert.common.reactivestreams.Preconditions.checkArgumentAndMap;

import java.util.Optional;

import com.google.common.base.Strings;
import life.expert.riso.common.PositiveSize;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.Figure;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

import io.vavr.control.Try;

import static reactor.core.publisher.Mono.*;
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

import static life.expert.common.reactivestreams.Preconditions.*; //reactive check
import static life.expert.common.reactivestreams.Patterns.*;    //reactive helper functions

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.Case;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Function;     //lambda->Function3
import static io.vavr.API.Tuple;

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

import io.vavr.Tuple;
import io.vavr.Tuple2
	;
import io.vavr.match.annotation.Unapply;

import life.expert.common.function.TupleUtils;

//import static reactor.function.TupleUtils.*; // tuple->R INTO func->R

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.Match;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions

import static io.vavr.API.$;                            // pattern matching
//import static java.util.function.Predicate.*;           //isEqual streamAPI

/**
 * <pre>
 * It is a canvas for drawing where you can add shapes. Stores drawn shapes as raster graphics in an
 * array. To do: alter  to the DDD event sourcing pattern with storing only the latest snapshot
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: none
 * Tread safety:  Immutable
 * </pre>
 */
@Slf4j
@ToString
//@Getter
@Data
@Table
public class DefaultCanvas
	implements Canvas
	{
	@Id private String id;
	
	private String name;
	
	private int width;
	
	private int height;
	
	@Setter( AccessLevel.NONE )
	//@Transient
	private int xMax;
	
	@Setter( AccessLevel.NONE )
	//@Transient
	private int yMax;
	
	//@Getter( AccessLevel.NONE )
	private String screen;
	
	//<editor-fold desc="basic constructors">
	
	private DefaultCanvas()
		{
		throw new UnsupportedOperationException( "Please use constructor with parameters." );
		}
	
	/**
	 * reactive repository use this method to create an object with fulfilled screen
	 * He himself calls the private constructor to create the object.
	 *
	 * @param id
	 * 	the id
	 * @param name
	 * 	the name
	 * @param width
	 * 	the width
	 * @param height
	 * 	the height
	 * @param xMax
	 * 	the x max
	 * @param yMax
	 * 	the y max
	 * @param screen
	 * 	the screen
	 */
	@PersistenceConstructor
	public DefaultCanvas( final String id ,
	                      final String name ,
	                      final int width ,
	                      final int height ,
	                      final int xMax ,
	                      final int yMax ,
	                      final String screen )
		{
		var precondition = PositiveSize.of( width , height );
		if( id == null || name == null || width < 3 || height < 3 || screen == null || xMax < 1 || yMax < 1 )
			throw new IllegalArgumentException( "Violated precondition: id == null || name == null || width < 3 || height < 3 || screen == null || xMax < 1 || yMax < 1" );
		
		this.id = id;
		this.name = name;
		this.xMax = xMax;
		this.yMax = yMax;
		this.width = width;  //+ 2;
		this.height = height;// + 2;
		this.screen = screen;
		}
	
	private DefaultCanvas( int width ,
	                       int height )
		{
		this( "CANVAS" + RandomStringUtils.random( 10 , false , true ) , "default" , width + 2 , height + 2 , width , height , Strings.repeat( Strings.repeat( " " , width + 2 ) + "\n" , height + 2 ) );
		
		initScreen_();
		}
	
	private static Mono<DefaultCanvas> monoOf_( int width ,
	                                            int height )
		{
		return fromSupplier( () -> new DefaultCanvas( width , height ) );
		}
	
	/**
	 * Create DefaultCanvas from simple arguments
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
	public static Mono<DefaultCanvas> monoOf( int width ,
	                                          int height )
		{
		return PositiveSize.monoOf( width , height )
		                   .then( monoOf_( width , height ) );
		}
	
	/**
	 * <pre>
	 * Classic fabric method for creating  DefaultCanvas.
	 * This factory method is prohibited because it is intended only for easy creation of objects in tests
	 *
	 *
	 * @throws IllegalArgumentException if the input arguments do not satisfy the preconditions
	 * @deprecated please use pure functional methods monoOf.., without raise exceptions. </pre>
	 */
	@Deprecated
	public static DefaultCanvas of( int width ,
	                                int height )
		{
		return monoOf( width , height ).block();
		}
	
	/**
	 * Create DefaultCanvas from Mono with Tuple inside
	 * The method helps chaining flows together
	 *
	 * @param tuple
	 * 	the tuple
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<DefaultCanvas> monoOfMono( Mono<Tuple2<Integer,Integer>> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input Mono must not be null." );
		else
			return tuple.flatMap( DefaultCanvas::monoOfTuple );
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
	public static Mono<DefaultCanvas> copyOf( final DefaultCanvas other )
		{
		return fromSupplier( () -> new DefaultCanvas( other.id , other.name , other.width , other.height , other.xMax , other.yMax , other.screen ) );
		}
	
	/**
	 * Create DefaultCanvas from precondition-objects
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
	public static Mono<DefaultCanvas> monoOfPreconditions( PositiveSize size )
		{
		return monoOf_( size.getWidth() , size.getHeight() );
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
	public static Try<DefaultCanvas> tryOf( int width ,
	                                        int height )
		{
		return tryFromMono( monoOf( width , height ) );
		}
	
	//<editor-fold desc="object to tuple conversions">
	
	/**
	 * pattern matching in vavr
	 * The method helps with conversion operations Sample-&gt;Tuple
	 *
	 * - you need add static import to method with pattern matching
	 * import static life.expert.riso.common.SamplePatterns.*;
	 */
	@Unapply
	public static Tuple2<Integer,Integer> DefaultCanvas( DefaultCanvas object )
		{
		return Tuple.of( object.getWidth() , object.getHeight() );
		}
	
	/**
	 * Create DefaultCanvas from Tuple
	 * The method helps with conversion operations Tuple-&gt;Sample
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<DefaultCanvas> monoOfTuple( Tuple2<Integer,Integer> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input tuple must not be null." );
		else
			return TupleUtils.function( DefaultCanvas::monoOf )
			                 .apply( tuple );
		}
	
	//</editor-fold>
	
	private void initScreen_()
		{
		
		int max_height = this.height - 1;
		int max_width  = this.width - 1;
		
		// Upper and lower borders
		range( 0 , max_width ).subscribe( i ->
		                                  {
		                                  setPixel_( 0 , i , '-' );
		                                  setPixel_( max_height , i , '-' );
		                                  } );
		
		// Left and right borders
		range( 1 , max_height - 1 ).subscribe( i ->
		                                       {
		                                       setPixel_( i , 0 , '|' );
		                                       setPixel_( i , max_width , '|' );
		                                       } );
		
		// Border corners
		//		setPixel_( max_height , 0 , '╔' );
		//		setPixel_( max_height , max_width , '╗' );
		//		setPixel_( 0 , 0 , '╚' );
		//		setPixel_( 0 , max_width , '╝' );
		
		//flushScreen();
		}
	
	/**
	 * Fills the screen with spaces.
	 */
	//	private void flushScreen()
	//		{
	//		For( range( 1 , this.yMax ) , range( 1 , this.xMax ) ).yield( ( row , col ) -> this.screen[row][col] = ' ' )
	//		                                                      .subscribe();
	//		}
	
	//</editor-fold>
	
	//<editor-fold desc="drawing methods">
	@Override
	public void putPixel( int x ,
	                      int y ,
	                      char c )
		{
		//coordinates starts from 1
		if( x < 1 || x > this.xMax || y < 1 || y > this.yMax )
			return;
		else
			setPixel_( y , x , c );
		}
	
	/*
	 * Working with screen in this class throught this functions
	 * */
	private void setPixel_( int y ,
	                        int x ,
	                        char character )
		{
		if( x >= this.width || y >= this.height )
			return;
		
		int pos = y * ( this.width + 1 ) + x;
		screen = new StringBuilder( screen ).replace( pos , pos + 1 , String.valueOf( character ) )
		                                    .toString();
		//screen.substring( 0 , pos ) + String.valueOf( character ) + screen.substring( pos + 1 );//replace( pos , pos + 1 , String.valueOf( character ) );
		
		//System.out.println( String.format( "setPixel(%d,%d)  %s " , x , y , screen ) );
		}
	
	@Override
	public Optional<Character> getPixel( int x ,
	                                     int y )
		{
		//coordinates starts from 1
		return ( x < 1 || x > this.xMax || y < 1 || y > this.yMax ) ? Optional.empty() : Optional.of( getPixel_( y , x ) );
		
		
		}
	

	
	private char getPixel_( int y ,
	                        int x )
		{
		if( x >= this.width || y >= this.height )
			return ' ';
		
		int pos = y * ( this.width + 1 ) + x;
		return screen.charAt( pos );
		}
	
	/**
	 * return terminal image of screen as string
	 *
	 * @return the string
	 */
	@Override
	public Mono<String> makeScreen()
		{
		return Mono.just( screen.toString() );
		
		//		StringBuilder screenBuffer = new StringBuilder( ( this.width + 1 ) * this.height );
		//		//log( "makeScreen" );
		//		return range( 0 , this.height - 1 ).flatMap( row -> range( 0 , this.width - 1 ).map( col -> this.screen[row][col] )
		//		                                                                               .concatWithValues( '\n' ) )
		//		                                   .collect( StringBuilder::new , StringBuilder::append )
		//		                                   .map( StringBuilder::toString )
		//		                                   .filter( not( String::isBlank ) ) /*postcondition: screen alwase not blank*/
		//		                                   .single();
		}
	
	/**
	 * Put figure on canvas
	 *
	 * @param figure
	 * 	the figure
	 *
	 * @return the mono with Canvas
	 */
	@Override
	public Mono<Canvas> draw( Figure figure )
		{
		return figure.draw( this )
		             .thenReturn( this );
		}
	
	/**
	 * Put figure (as Mono) on canvas
	 *
	 * @param figure
	 * 	the figure
	 *
	 * @return the mono with Canvas
	 */
	@Override
	public Mono<Canvas> draw( Mono<Figure> figure )
		{
		return figure.flatMap( f -> f.draw( this ) )
		             .thenReturn( this );
		}
	

	
	//</editor-fold>
	
	}

