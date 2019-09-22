package life.expert.riso.domain.model.factory;
//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/13
//---------------------------------------------

import life.expert.riso.domain.model.builder.DrawingFactory;
import life.expert.riso.domain.model.builder.CanvasBuilder;
import life.expert.riso.domain.model.builder.FillBuilder;
import life.expert.riso.domain.model.builder.LineBuilder;
import life.expert.riso.domain.model.builder.RectangleBuilder;
import life.expert.riso.domain.model.entity.DefaultCanvas;
import life.expert.riso.domain.model.value.Fill;
import life.expert.riso.domain.model.value.Line;
import life.expert.riso.domain.model.value.Rectangle;
import org.springframework.stereotype.Component;

//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model
//                           wilmer 2019/08/13
//
//--------------------------------------------------------------------------------

@Component
public class DefaultDrawingFactory
	implements DrawingFactory
	{
	@Override
	public CanvasBuilder newCanvasBuilder()
		{
		return DefaultCanvas.builder();
		}
	
	@Override
	public FillBuilder newFillBuilder()
		{
		return Fill.builder();
		
		}
	
	@Override
	public LineBuilder newLineBuilder()
		{
		return Line.builder();
		}
	
	@Override
	public RectangleBuilder newRectangleBuilder()
		{
		return Rectangle.builder( this );
		}
		
	}
