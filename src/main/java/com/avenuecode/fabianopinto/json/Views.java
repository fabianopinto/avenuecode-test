package com.avenuecode.fabianopinto.json;

public interface Views {

	public interface Summary {}
	public interface Children extends Summary {}
	public interface Images extends Summary {}
	public interface Complete extends Children, Images {}

}
