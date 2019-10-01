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



/**
 * The interface Drawing factory.
 */
public interface DrawingFactory
	{
	
	/**
	 * New canvas builder canvas builder.
	 *
	 * @return the canvas builder
	 */
	CanvasBuilder newCanvasBuilder();
	
	/**
	 * New fill builder fill builder.
	 *
	 * @return the fill builder
	 */
	FillBuilder newFillBuilder();
	
	/**
	 * New line builder line builder.
	 *
	 * @return the line builder
	 */
	LineBuilder newLineBuilder();
	
	/**
	 * New rectangle builder rectangle builder.
	 *
	 * @return the rectangle builder
	 */
	RectangleBuilder newRectangleBuilder();
	
	}

