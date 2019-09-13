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

import life.expert.riso.domain.model.builder.CanvasBuilder;
import life.expert.riso.domain.model.builder.FillBuilder;
import life.expert.riso.domain.model.builder.LineBuilder;
import life.expert.riso.domain.model.builder.RectangleBuilder;

//import static java.util.function.Predicate.*;           //isEqual streamAPI

public interface DrawingFactory
	{
	
	CanvasBuilder newCanvasBuilder();
	
	FillBuilder newFillBuilder();
	
	LineBuilder newLineBuilder();
	
	RectangleBuilder newRectangleBuilder();
	
	}

