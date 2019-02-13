package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.BooleanCollection;
import com.github.powerlibraries.primitive.collections.BooleanList;

public interface BooleanArray extends BooleanList, Array<Boolean>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static BooleanArray EMPTY = new DefaultBooleanArray(new boolean[0]);
	
	@SuppressWarnings("unchecked")
	static  BooleanArray empty() {
		return EMPTY;
	}
	
	static  BooleanArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultBooleanArray(new boolean[size]);
	}
	
	static  BooleanArray copy(Collection<? extends Boolean> c) {
		BooleanArray result = ofSize(c.size());
		int i=0;
		for(Boolean v:c)
			result.setBoolean(i++, v);
		return result;
	}
	
	static  BooleanArray copy(BooleanArray arr) {
		return new DefaultBooleanArray(arr.toBooleanArray());
	}
	
	static  BooleanArray copy(boolean[] arr) {
		return new DefaultBooleanArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  BooleanArray copy(boolean[] arr, int offset, int length) {
		return new DefaultBooleanArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  BooleanArray copy(Boolean[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  BooleanArray copy(Boolean[] arr, int offset, int length) {
		boolean[] copy = new boolean[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultBooleanArray(copy);
	}
	
	static  BooleanArray wrap(boolean... arr) {
		return new DefaultBooleanArray(arr);
	}
	
	static  BooleanArray wrap(BooleanArray arr) {
		return new DefaultBooleanArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  BooleanArray wrap(boolean[] arr, int offset, int length) {
		return new DefaultBooleanArray(arr, offset, length);
	}

	static  BooleanArray wrap(BooleanArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultBooleanArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  BooleanArray concat(BooleanArray arr, boolean element) {
		boolean[] result = new boolean[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  BooleanArray concat(boolean element, BooleanArray arr) {
		boolean[] result = new boolean[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  BooleanArray concat(BooleanArray arr, Collection<? extends Boolean> elements) {
		BooleanArray result = BooleanArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setBoolean(i, arr.getBoolean(i));
		}
		int index = arr.size();
		for(boolean value : elements) {
			result.setBoolean(index++, value);
		}
		return result;
	}
	
	static  BooleanArray concat(Collection<? extends Boolean> elements, BooleanArray arr) {
		BooleanArray result = BooleanArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(boolean value : elements) {
			result.setBoolean(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setBoolean(i + index, arr.getBoolean(i));
		}
		return result;
	}
	
	static  BooleanArray concat(BooleanArray arr, BooleanArray arr2) {
		BooleanArray result = BooleanArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setBoolean(i, arr.getBoolean(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setBoolean(i + arr2.size(), arr2.getBoolean(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addBoolean(boolean e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addBoolean(int index, boolean element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllBooleans(BooleanCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Boolean> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Boolean> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default BooleanArray subList(int fromIndex, int toIndex) {
		return BooleanArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default BooleanArray subArray(int offset, int length) {
		return BooleanArray.wrap(this, offset, length);
	}
	
	default void copyTo(boolean[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(boolean[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	void fill(boolean val);
	
	boolean[] getInternalArray();
	
	int getInternalOffset();
}