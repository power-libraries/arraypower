package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.ByteListIterator;

public class ByteArrayIterator implements ByteListIterator {

	private final ByteArray array;
	private int position;

	public ByteArrayIterator(ByteArray array, int position) {
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
	public byte nextByte() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getByte(position);
	}

	@Override
	public byte previousByte() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getByte(position);
	}

	@Override
	public void setByte(byte e) {
		array.setByte(position, e);
	}

	@Override
	public void addByte(byte e) {
		throw new UnsupportedOperationException();
	}
}