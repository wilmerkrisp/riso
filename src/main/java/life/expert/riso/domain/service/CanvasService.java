package life.expert.riso.domain.service;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.service
//                           wilmer 2019/08/13
//
//--------------------------------------------------------------------------------

import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.service.CanvasDataTransferObject;
import life.expert.riso.domain.service.FillDataTransferObject;
import life.expert.riso.domain.service.LineDataTransferObject;
import life.expert.riso.domain.service.RectangleDataTransferObject;
import reactor.core.publisher.Mono;

public interface CanvasService
	{
	public Mono<Canvas> createCanvas( CanvasDataTransferObject canvas );
	
	public Mono<Canvas> newLine( LineDataTransferObject line );
	
	public Mono<Canvas> newRectangle( RectangleDataTransferObject rectangle );
	
	public Mono<Canvas> newFill( FillDataTransferObject fill );
	
	}
