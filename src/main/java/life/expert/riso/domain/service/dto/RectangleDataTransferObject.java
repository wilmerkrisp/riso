package life.expert.riso.domain.service.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/30
//---------------------------------------------
//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

//<editor-fold desc=".">
/*

@Data означает
    @ToString,
    @EqualsAndHashCode,
    @Getter
    @Setter
    @RequiredArgsConstructor


 	- класс сделан final тк класс изначально не предназначался для наследования
  
 
 i) если нужно закешировать свойство то пометить его аннотацией
	@Getter( lazy=true)

j) для игнорирования свойства, назвать его с $
	 String $ x0
	 
*/
//</editor-fold>

/**
 * <pre>
 * simple mutable class: int int int int char
 *
 * - pattern new-set-call
 * - not for inheritance
 *
 *  {@code
 * 	  //pattern new-set-call
 * 	  RectangleDataTransferObject o = new RectangleDataTransferObject();
 * o.setX0("a");
 * o.compute();
 *    }
 *
 *
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: none
 * Tread safety:  not thread safe
 * </pre>
 */

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public final class RectangleDataTransferObject
	{
	
	/**
	 * canvas identificator for drawing figure
	 *
	 * -- SETTER --
	 *
	 * @param id
	 * 	id
	 *
	 * 	-- GETTER --
	 * @return the id
	 */
	private String canvasId;
	
	/**
	 * x0
	 *
	 * -- SETTER --
	 *
	 * @param x0
	 * 	x0
	 *
	 * 	-- GETTER --
	 * @return the x0
	 */
	private int x0;
	
	/**
	 * y0
	 *
	 * -- SETTER --
	 *
	 * @param y0
	 * 	y0
	 *
	 * 	-- GETTER --
	 * @return the y0
	 */
	private int y0;
	
	/**
	 * x1
	 *
	 * -- SETTER --
	 *
	 * @param x1
	 * 	x1
	 *
	 * 	-- GETTER --
	 * @return the x1
	 */
	private int x1;
	
	/**
	 * y1
	 *
	 * -- SETTER --
	 *
	 * @param y1
	 * 	y1
	 *
	 * 	-- GETTER --
	 * @return the y1
	 */
	private int y1;
	
	/**
	 * character
	 *
	 * -- SETTER --
	 *
	 * @param character
	 * 	character
	 *
	 * 	-- GETTER --
	 * @return the character
	 */
	private char character;
	
	}