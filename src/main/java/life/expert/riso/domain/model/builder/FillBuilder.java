package life.expert.riso.domain.model.builder;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/09/12
//---------------------------------------------

import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.Figure;
import life.expert.riso.domain.model.value.Fill;
import lombok.NonNull;//@NOTNULL

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.text.MessageFormat.format;           //format string

import java.util.Objects;
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
import reactor.core.publisher.Mono;

import static io.vavr.API.Failure;
import static io.vavr.API.Success;
import static io.vavr.API.Left;         //Either
import static io.vavr.API.Right;

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

/**
 * <pre>
 *   interface simple
 *   !CHANGE_ME_DESCRIPTION!
 *
 *
 *
 *
 *
 * - этот тип предназанчен для наследования
 *
 *
 *
 * Конструкторы класса не должны вызывать переопределяемые методы, непосредственно или опосредованно.
 * Нарушение этого правила может привестик аварийному завершению программы.
 * Тк Конструктор суперкласса выполняется прежде конструктора подкласса,
 *
 *
 * в джавадоке (в тегах @implSpec  @implNote ) нужно раскрыть детали реализации методов и указать какие из переопределяемых методов он использует сам
 * тк наследование нарушает инкапсуляцию
 * например, чтобы юзер знал что некоторый добавленный в overrided методы функционал  может сработать два раза (в случае когда AddAll вызывает Add)
 *
 *
 * {@code
 *
 *
 * example 1
 *
 *              VC_somesubclass                  implements FillBuilder
 *              VI_somesubinterface              extends    FillBuilder
 *              VCG_somesubclass< T >        implements FillBuilder
 *              VIG_somesubinterface< T >    extends    FillBuilder
 *
 * }</pre>
 *
 * @author wilmer
 * @version 1.0
 * @since 1.0 wilmer draft
 */
public interface FillBuilder
	{
	
	public FillBuilder point( final int x0 ,
	                                    final int y0 );
	
	public FillBuilder point( PositivePoint startPoint );
	
	public FillBuilder filler( char character );
	
	public FillBuilder pointX( int x0 );
	public FillBuilder pointY( int y0 );
	
	public Mono<Figure> buildMono();
	
	public Try<Figure> buildTry();
	
	public  Figure build();

	
    
        }
