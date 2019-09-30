package life.expert.riso.domain.model.factory;
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

//import static java.util.function.Predicate.*;           //isEqual streamAPI

import life.expert.riso.domain.model.factory.CanvasBuilder;
import life.expert.riso.domain.model.factory.FillBuilder;
import life.expert.riso.domain.model.factory.LineBuilder;
import life.expert.riso.domain.model.factory.RectangleBuilder;

public interface DrawingFactory
	{
	
	CanvasBuilder newCanvasBuilder();
	
	FillBuilder newFillBuilder();
	
	LineBuilder newLineBuilder();
	
	RectangleBuilder newRectangleBuilder();
	
	}

