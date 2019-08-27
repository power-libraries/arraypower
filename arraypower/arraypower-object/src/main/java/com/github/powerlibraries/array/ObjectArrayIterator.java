package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.ObjectListIterator;

public class ObjectArrayIterator<E> implements ObjectListIterator<E> {

	private final ObjectArray<E> array;
	private int position;

	public ObjectArrayIterator(ObjectArray<E> array, int position) {
		this.array = array;
		this.position = position - 1;
	}
	
	@Override
	public boolean hasNext() {
		return position + 1 < array.size();
	}

	@Override
	public boolean hasPrevious() {
		return position > 0;
	}

	@Override
	public int nextIndex() {
		return position + 1;
	}

	@Override
	public int previousIndex() {
		return position - 1;
	}

	@Override
	public void remove() {
		array.removeAt(position);
	}

	@Override
	public E nextObject() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getObject(position);
	}

	@Override
	public E previousObject() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getObject(position);
	}

	@Override
	public void setObject(E e) {
		array.setObject(position, e);
	}

	@Override
	public void addObject(E e) {
		throw new UnsupportedOperationException();
	}
}