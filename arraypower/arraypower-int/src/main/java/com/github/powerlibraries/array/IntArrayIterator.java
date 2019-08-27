package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.IntListIterator;

public class IntArrayIterator implements IntListIterator {

	private final IntArray array;
	private int position;

	public IntArrayIterator(IntArray array, int position) {
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
	public int nextInt() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getInt(position);
	}

	@Override
	public int previousInt() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getInt(position);
	}

	@Override
	public void setInt(int e) {
		array.setInt(position, e);
	}

	@Override
	public void addInt(int e) {
		throw new UnsupportedOperationException();
	}
}