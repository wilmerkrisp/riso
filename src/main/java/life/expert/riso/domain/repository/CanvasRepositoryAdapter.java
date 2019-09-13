package life.expert.riso.domain.repository;
//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/28
//---------------------------------------------

import life.expert.riso.common.ForwardingReactiveCrudRepository;
import life.expert.riso.domain.model.Canvas;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI
//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

/**
 * please name it EmployeeRepositoryAdapter
 */

@Repository( "canvasRepository" )
@SuppressWarnings( "unchecked" )
public class CanvasRepositoryAdapter
	extends ForwardingReactiveCrudRepository<Canvas,String>
	implements CanvasRepository
	{
	
	private final ReactiveCrudRepository<? extends Canvas,String> delegate; // backing list
	
	@Override
	protected ReactiveCrudRepository<? extends Canvas,String> delegate()
		{
		return delegate;
		}
	
	private CanvasRepositoryAdapter()
		{
		super();
		
		throw new UnsupportedOperationException( "Dont use this PRIVATE constructor.Please use constructor with parameters." );
		
		}
	
	@Autowired
	public CanvasRepositoryAdapter( @NonNull @Lazy ReactiveCrudRepository<? extends Canvas,String> backingRepository )
		{
		this.delegate = backingRepository;
		}
		
	}

 