package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.DoubleListIterator;

public class DoubleArrayIterator implements DoubleListIterator {

	private final DoubleArray array;
	private int position;

	public DoubleArrayIterator(DoubleArray array, int position) {
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
	public double nextDouble() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getDouble(position);
	}

	@Override
	public double previousDouble() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getDouble(position);
	}

	@Override
	public void setDouble(double e) {
		array.setDouble(position, e);
	}

	@Override
	public void addDouble(double e) {
		throw new UnsupportedOperationException();
	}
}