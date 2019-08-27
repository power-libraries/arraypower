package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.ShortListIterator;

public class ShortArrayIterator implements ShortListIterator {

	private final ShortArray array;
	private int position;

	public ShortArrayIterator(ShortArray array, int position) {
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
	public short nextShort() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getShort(position);
	}

	@Override
	public short previousShort() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getShort(position);
	}

	@Override
	public void setShort(short e) {
		array.setShort(position, e);
	}

	@Override
	public void addShort(short e) {
		throw new UnsupportedOperationException();
	}
}