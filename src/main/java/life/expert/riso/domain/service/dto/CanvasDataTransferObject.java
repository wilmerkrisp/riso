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
	 String $ width
	 
*/
//</editor-fold>

/**
 * <pre>
 * simple mutable class: int int
 *
 * - pattern new-set-call
 * - not for inheritance
 * </pre>
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public final class CanvasDataTransferObject
	{
	
	/**
	 * width
	 *
	 * -- SETTER --
	 *
	 * @param width
	 * 	width
	 *
	 * 	-- GETTER --
	 * @return the width
	 */
	private int width;
	
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
	private int height;
	
	}