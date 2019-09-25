package life.expert.riso.domain.model.factory;

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

import io.vavr.control.Try;
import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Figure;
import reactor.core.publisher.Mono;

//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI

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
	
	public Figure build();
	
	}
