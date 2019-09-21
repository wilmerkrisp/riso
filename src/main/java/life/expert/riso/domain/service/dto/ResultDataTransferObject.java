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
//               wilmer 2019/09/10
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
	 String $ canvasId
	 
*/
//</editor-fold>

/**
 * <pre>
 * simple mutable class: String String
 *
 * - pattern new-set-call
 * - not for inheritance
 *
 * {@code
 * 	  //pattern new-set-call
 * 	  ResultDataTransferObject o = new ResultDataTransferObject();
 * o.setCanvasId("a");
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
public final class ResultDataTransferObject
	{
	
	/**
	 * canvasId
	 *
	 * -- SETTER --
	 *
	 * @param canvasId
	 * 	canvasId
	 *
	 * 	-- GETTER --
	 * @return the canvasId
	 */
	private String canvasId;
	
	/**
	 * screen
	 *
	 * -- SETTER --
	 *
	 * @param screen
	 * 	screen
	 *
	 * 	-- GETTER --
	 * @return the screen
	 */
	private String screen;
	
	}
