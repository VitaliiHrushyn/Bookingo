package com.droozhbooking.domain.hoteldetails;

/** 
 * Перечисление типов срока размещения.
 * @version 1.0-snapshot
 * @author DroozhbakeeCoders
 */

public enum AccomodationTerm {
	
	/**
	 * Размещение с обусловленным граничным сроком.
	 * Заранее устанавливается срок размещения, то есть все непостоянное проживание в отелях, базах отдыха и прочем. 
	 */
	TEMPORARY,
	
	/**
	 * Постоянное размещение, точный конечный срок которого не обуславливается.
	 * Например аренда жилья на постоянной основе.
	 */
	PERMANENT
	
}