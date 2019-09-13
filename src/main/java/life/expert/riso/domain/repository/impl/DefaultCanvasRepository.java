package life.expert.riso.domain.repository.impl;
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

import life.expert.riso.domain.model.entity.DefaultCanvas;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI
//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

/**
 * please name it DefaultEmployeeRepository
 */

@Repository
public interface DefaultCanvasRepository
	extends ReactiveCrudRepository<DefaultCanvas,String>
	{
	
	/*
	@Query( "select * from DEFAULT_EMPLOYEE where name = :name" )
	Flux<Employee> findByName( String name );
	
	@Query( "INSERT INTO DEFAULT_EMPLOYEE (ID, NAME) VALUES(:id, :name )" )
	Mono<Long> create( String id ,
	                   String name );
	                   */
		
	}

 