package life.expert.riso.domain.service;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.service
//                           wilmer 2019/08/13
//
//--------------------------------------------------------------------------------

import life.expert.riso.domain.service.dto.CanvasDataTransferObject;
import life.expert.riso.domain.service.dto.FillDataTransferObject;
import life.expert.riso.domain.service.dto.LineDataTransferObject;
import life.expert.riso.domain.service.dto.RectangleDataTransferObject;
import life.expert.riso.domain.service.dto.ResultDataTransferObject;
import reactor.core.publisher.Mono;

public interface CanvasService
	{
	public Mono<ResultDataTransferObject> createCanvas( CanvasDataTransferObject canvas );
	
	public Mono<ResultDataTransferObject> newLine( LineDataTransferObject line );
	
	public Mono<ResultDataTransferObject> newRectangle( RectangleDataTransferObject rectangle );
	
	public Mono<ResultDataTransferObject> newFill( FillDataTransferObject fill );
	
	}
