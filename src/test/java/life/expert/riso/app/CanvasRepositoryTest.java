package life.expert.riso.app;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/28
//---------------------------------------------

import io.r2dbc.spi.ConnectionFactory;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.impl.entity.DefaultCanvas;
import life.expert.riso.domain.repository.CanvasRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static life.expert.common.async.LogUtils.print;

//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI
//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

@RunWith( SpringRunner.class )
//@SpringBootTest
@DataR2dbcTest
//@ComponentScan( { "life.expert.algo.research.app", "life.expert.algo.research.repository"  } )
//@EnableR2dbcRepositories( basePackages = { "life.expert.algo.research.app" ,
//                                           "life.expert.algo.research.domain.repository" }, repositoryBaseClass = MergeableSimpleR2dbcRepository.class )
//@TestPropertySource( properties = { "spring.r2dbc.schema=classpath:schema.sql", "spring.r2dbc.initialization-mode=always"})

// todo 1 ПОМЕСТИ ЭТОТ ТЕСТ В ТОТ ЖЕ ПАКЕТ ЧТО И @ApplicationCOntext только в тестовой иерархии
// todo 2 в тестовой иерархии не долнобыть своего application.properties
public class CanvasRepositoryTest
	{
	//@Qualifier( "canvasRepository")
	@Autowired private CanvasRepository canvasRepository;
	
	@Autowired ConnectionFactory connectionFactory;
	
	@Data
	@AllArgsConstructor
	@Table
	public static class Reservation
		{
		@Id public Long id;
		
		public String name;
		}
	
	/**
	 * Context loads.
	 */
	@Test
	public void connectionFactoryTest()
		{
		print( "tst_________________1__________________" );
		
		var client = DatabaseClient.create( connectionFactory );
		client.execute( "CREATE TABLE RESERVATION (id IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(255))" )
		      .fetch()
		      .all()
		      //.doOnEach( x -> System.out.println( "++++++++++ " + x ) )
		      .log()
		      .as( StepVerifier::create )
		      .verifyComplete();
		
		client.insert()
		      .into( Reservation.class )
		      .using( new Reservation( null , "NEW RESERVATION" ) )
		      .then()
		      .as( StepVerifier::create )
		      .verifyComplete();
		
		client.select()
		      .from( Reservation.class )
		      .fetch()
		      .all()
		      //.doOnEach( x -> System.out.println( "++++++++++ " + x ) )
		      .as( StepVerifier::create )
		      .expectNext( new Reservation( 1L , "NEW RESERVATION" ) )
		      .verifyComplete();
		}
	
	@Test
	public void repositoryTest()
		{
		//@formatter:off
                var canvas_for_test=
		    "----------------------\n" +
	            "|                    |\n" +
	            "|                    |\n" +
	            "|                    |\n" +
	            "|                    |\n" +
	            "----------------------\n";
                //@formatter:on
		
		//
		//		this.canvasRepository.findAll()
		//		                     .doOnEach( x -> System.out.println( "++DEL++++++++ " + x ) )
		//		                     .flatMap( r -> this.canvasRepository.deleteById( r.getId() ) )
		//		                     .as( StepVerifier::create )
		//		                     .expectNextCount( 0 )
		//		                     .verifyComplete();
		
		//		this.canvasRepository.findAll()
		//		                     .doOnEach( x -> System.out.println( "++++++++++ " + x ) )
		//		                     .as( StepVerifier::create )
		//		                     .expectNextCount( 1 )
		//		                     .verifyComplete();
		
		Canvas before_and_after = new DefaultCanvas( "x" , "x" , 22 , 6 , 20 , 4 , canvas_for_test );
		
		Flux.just( before_and_after )
		    .flatMap( r -> this.canvasRepository.save( r ) )
		    .log()
		    .as( StepVerifier::create )
		    .expectNextCount( 1 )
		    .verifyComplete();
		
		this.canvasRepository.findById( "x" )
		                     .doOnEach( x -> System.out.println( "++ITOGO++++ " + x ) )
		                     .as( StepVerifier::create )
		                     .expectNext( before_and_after )
		                     .verifyComplete();
		
		//		canvasRepository.findByName( "first" )
		//		                .as( StepVerifier::create )
		//		                .expectNextCount( 1 )
		//		                .verifyComplete();
		}
		
	}


