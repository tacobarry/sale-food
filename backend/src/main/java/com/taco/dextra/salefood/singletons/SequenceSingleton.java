package com.taco.dextra.salefood.singletons;

public class SequenceSingleton {
	
	private Integer value;
	public static SequenceSingleton instance = new SequenceSingleton();
	
	public int getValue() {
		if (null == this.value) {
			this.value = 0;
		} else {
			this.value++;
		}
		return this.value;
	}

}
