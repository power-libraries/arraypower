package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.CharCollection;
import com.github.powerlibraries.primitive.collections.CharList;

public interface CharArray extends CharList, Array<Character>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static CharArray EMPTY = new DefaultCharArray(new char[0]);
	
	@SuppressWarnings("unchecked")
	static  CharArray empty() {
		return EMPTY;
	}
	
	static  CharArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultCharArray(new char[size]);
	}
	
	static  CharArray copy(Collection<? extends Character> c) {
		CharArray result = ofSize(c.size());
		int i=0;
		for(Character v:c)
			result.setChar(i++, v);
		return result;
	}
	
	static  CharArray copy(CharArray arr) {
		return new DefaultCharArray(arr.toCharArray());
	}
	
	static  CharArray copy(char[] arr) {
		return new DefaultCharArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  CharArray copy(char[] arr, int offset, int length) {
		return new DefaultCharArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  CharArray copy(Character[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  CharArray copy(Character[] arr, int offset, int length) {
		char[] copy = new char[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultCharArray(copy);
	}
	
	static  CharArray wrap(char... arr) {
		return new DefaultCharArray(arr);
	}
	
	static  CharArray wrap(CharArray arr) {
		return new DefaultCharArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  CharArray wrap(char[] arr, int offset, int length) {
		return new DefaultCharArray(arr, offset, length);
	}

	static  CharArray wrap(CharArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultCharArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  CharArray concat(CharArray arr, char element) {
		char[] result = new char[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  CharArray concat(char element, CharArray arr) {
		char[] result = new char[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  CharArray concat(CharArray arr, Collection<? extends Character> elements) {
		CharArray result = CharArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setChar(i, arr.getChar(i));
		}
		int index = arr.size();
		for(char value : elements) {
			result.setChar(index++, value);
		}
		return result;
	}
	
	static  CharArray concat(Collection<? extends Character> elements, CharArray arr) {
		CharArray result = CharArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(char value : elements) {
			result.setChar(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setChar(i + index, arr.getChar(i));
		}
		return result;
	}
	
	static  CharArray concat(CharArray arr, CharArray arr2) {
		CharArray result = CharArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setChar(i, arr.getChar(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setChar(i + arr2.size(), arr2.getChar(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addChar(char e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addChar(int index, char element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllChars(CharCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Character> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Character> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default CharArray subList(int fromIndex, int toIndex) {
		return CharArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default CharArray subArray(int offset, int length) {
		return CharArray.wrap(this, offset, length);
	}
	
	default void copyTo(char[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(char[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(char key);
	
	void fill(char val);
	
	char[] getInternalArray();
	
	int getInternalOffset();
}