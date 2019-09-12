package life.expert.riso.domain.model;
//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/07
//---------------------------------------------

//import static life.expert.common.base.Preconditions.*;  //checkCollection

//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.$;                            // pattern matching
import static io.vavr.API.Case;
import static io.vavr.API.Match;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions
import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Function;     //lambda->Function3
import static io.vavr.API.Tuple;

import life.expert.riso.domain.model.builder.CanvasBuilder;
import life.expert.riso.domain.model.builder.FillBuilder;
import life.expert.riso.domain.model.builder.LineBuilder;
import life.expert.riso.domain.model.builder.RectangleBuilder;
import reactor.core.publisher.Mono;

public interface DrawingFactory
	{

	
	CanvasBuilder newCanvasBuilder();
	
	FillBuilder newFillBuilder();
	
	LineBuilder newLineBuilder();
	
	RectangleBuilder newRectangleBuilder();
	
	}

