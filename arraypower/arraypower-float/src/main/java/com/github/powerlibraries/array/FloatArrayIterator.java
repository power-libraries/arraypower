package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.FloatListIterator;

public class FloatArrayIterator implements FloatListIterator {

	private final FloatArray array;
	private int position;

	public FloatArrayIterator(FloatArray array, int position) {
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
	public float nextFloat() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getFloat(position);
	}

	@Override
	public float previousFloat() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getFloat(position);
	}

	@Override
	public void setFloat(float e) {
		array.setFloat(position, e);
	}

	@Override
	public void addFloat(float e) {
		throw new UnsupportedOperationException();
	}
}