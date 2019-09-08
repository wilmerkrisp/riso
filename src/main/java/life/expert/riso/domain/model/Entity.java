package life.expert.riso.domain.model;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.entity
//                           wilmer 2019/07/23
//
//--------------------------------------------------------------------------------

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base abstract class for aggregate (DDD pattern)
 *
 * @param <ID>
 * 	the type of entity ID
 */

public interface Entity<ID>
	{
	
	ID getId();
	
	//void setId(T id);
	
	String getName();
	
	//void setName(String name);
	}
