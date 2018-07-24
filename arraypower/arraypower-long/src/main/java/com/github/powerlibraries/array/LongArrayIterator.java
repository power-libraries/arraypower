package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.LongListIterator;

public class LongArrayIterator implements LongListIterator {

	private final LongArray array;
	private int position;

	public LongArrayIterator(LongArray array, int position) {
		this.array = array;
		this.position = position;
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
	public long nextLong() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getLong(position);
	}

	@Override
	public long previousLong() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getLong(position);
	}

	@Override
	public void setLong(long e) {
		array.setLong(position, e);
	}

	@Override
	public void addLong(long e) {
		throw new UnsupportedOperationException();
	}
}