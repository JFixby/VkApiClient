
package com.jfixby.vk.api.client;

public enum WALL_FILTER {
	ALL("all")//
	, SUGGEST("suggests")//
	, POSTPONED("postponed")//
	;

	private final String vkapiparam;

	WALL_FILTER (final String vkapiparam) {
		this.vkapiparam = vkapiparam;
	}

	public String VK_API_PARAM () {
		return this.vkapiparam;
	}

}
