package com.github.powerlibraries.array;

import java.util.NoSuchElementException;

import com.github.powerlibraries.primitive.collections.CharListIterator;

public class CharArrayIterator implements CharListIterator {

	private final CharArray array;
	private int position;

	public CharArrayIterator(CharArray array, int position) {
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
	public char nextChar() {
		position++;
		if(position >= array.size())
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getChar(position);
	}

	@Override
	public char previousChar() {
		position--;
		if(position < 0)
			throw new NoSuchElementException("There is no element "+position+" in an array of size "+array.size());
		return array.getChar(position);
	}

	@Override
	public void setChar(char e) {
		array.setChar(position, e);
	}

	@Override
	public void addChar(char e) {
		throw new UnsupportedOperationException();
	}
}