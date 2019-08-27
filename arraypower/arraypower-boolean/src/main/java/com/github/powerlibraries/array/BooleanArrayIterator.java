package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.BooleanListIterator;

public class BooleanArrayIterator implements BooleanListIterator {

	private final BooleanArray array;
	private int position;

	public BooleanArrayIterator(BooleanArray array, int position) {
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
	public boolean nextBoolean() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getBoolean(position);
	}

	@Override
	public boolean previousBoolean() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getBoolean(position);
	}

	@Override
	public void setBoolean(boolean e) {
		array.setBoolean(position, e);
	}

	@Override
	public void addBoolean(boolean e) {
		throw new UnsupportedOperationException();
	}
}