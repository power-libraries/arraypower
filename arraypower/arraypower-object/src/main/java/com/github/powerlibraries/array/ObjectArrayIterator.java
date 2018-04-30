package com.github.powerlibraries.array;

import com.github.powerlibraries.primitive.collections.ObjectListIterator;

public class ObjectArrayIterator<E> implements ObjectListIterator<E> {

	private final ObjectArray<E> array;
	private int position = 0;

	public ObjectArrayIterator(ObjectArray<E> array, int position) {
	}
}