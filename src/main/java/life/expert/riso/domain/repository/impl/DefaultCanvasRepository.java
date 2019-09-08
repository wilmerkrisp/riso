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
import lombok.NonNull;//@NOTNULL

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.text.MessageFormat.format;           //format string

import java.util.ResourceBundle;

import static com.google.common.base.Preconditions.*;   //checkArgument
//import static life.expert.common.base.Preconditions.*;  //checkCollection
import static org.apache.commons.lang3.Validate.*;      //notEmpty(collection)

import org.apache.commons.lang3.StringUtils;            //isNotBlank

import java.util.function.*;                            //producer supplier

import static java.util.stream.Collectors.*;            //toList streamAPI

import java.util.Optional;

import static reactor.core.publisher.Mono.*;
import static reactor.core.scheduler.Schedulers.*;
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
import static life.expert.common.function.TupleUtils.*; //vavr's tuple->R INTO func->R

import life.expert.value.string.*;
import life.expert.value.numeric.*;

import static life.expert.common.async.LogUtils.*;        //logAtInfo
import static life.expert.common.function.NullableUtils.*;//.map(nullableFunction)
import static life.expert.common.function.CheckedUtils.*;// .map(consumerToBoolean)
import static life.expert.common.reactivestreams.Preconditions.*; //reactive check
import static life.expert.common.reactivestreams.Patterns.*;    //reactive helper functions
import static life.expert.common.base.Objects.*;          //deepCopyOfObject
import static life.expert.common.reactivestreams.ForComprehension.*; //reactive for-comprehension

import static cyclops.control.Trampoline.more;
import static cyclops.control.Trampoline.done;

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.$;                            // pattern matching
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.*;                         //switch - case - success/failure
import static io.vavr.Predicates.*;                       //switch - case
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions
import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Function;     //lambda->Function3
import static io.vavr.API.Tuple;

import static io.vavr.API.Try;          //Try

import io.vavr.control.Try;                               //try

import static io.vavr.API.Failure;
import static io.vavr.API.Success;
import static io.vavr.API.Left;         //Either
import static io.vavr.API.Right;

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import lombok.NonNull;//@NOTNULL

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

 