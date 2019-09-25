package life.expert.riso.domain.model.impl.value;//@Header@
//--------------------------------------------------------------------------------
//
//                          graph  life.expert.riso.base
//                           wilmer 2019/01/31
//
//--------------------------------------------------------------------------------

import life.expert.riso.domain.model.Canvas;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

/**
 * The type App bechmark.
 */
public class AppBechmark
	{
	
	/**
	 * Fill test.
	 *
	 * @param data
	 * 	the data
	 * @param hole
	 * 	the hole
	 */
	@Benchmark
	public void fillTest( ExperimentData data ,
	                      Blackhole hole )
		{
		Canvas.monoOf( data.iterations , data.iterations )
		      .flatMap( c -> c.draw( Fill.monoOf( 1 , 1 , 'o' ) ) )
		      .subscribe( hole::consume );
		}
		
	}
