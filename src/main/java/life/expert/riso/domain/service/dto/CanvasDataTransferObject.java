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
