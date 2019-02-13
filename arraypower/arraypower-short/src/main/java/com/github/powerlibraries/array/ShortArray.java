package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.ShortCollection;
import com.github.powerlibraries.primitive.collections.ShortList;

public interface ShortArray extends ShortList, Array<Short>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static ShortArray EMPTY = new DefaultShortArray(new short[0]);
	
	@SuppressWarnings("unchecked")
	static  ShortArray empty() {
		return EMPTY;
	}
	
	static  ShortArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultShortArray(new short[size]);
	}
	
	static  ShortArray copy(Collection<? extends Short> c) {
		ShortArray result = ofSize(c.size());
		int i=0;
		for(Short v:c)
			result.setShort(i++, v);
		return result;
	}
	
	static  ShortArray copy(ShortArray arr) {
		return new DefaultShortArray(arr.toShortArray());
	}
	
	static  ShortArray copy(short[] arr) {
		return new DefaultShortArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  ShortArray copy(short[] arr, int offset, int length) {
		return new DefaultShortArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  ShortArray copy(Short[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  ShortArray copy(Short[] arr, int offset, int length) {
		short[] copy = new short[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultShortArray(copy);
	}
	
	static  ShortArray wrap(short... arr) {
		return new DefaultShortArray(arr);
	}
	
	static  ShortArray wrap(ShortArray arr) {
		return new DefaultShortArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  ShortArray wrap(short[] arr, int offset, int length) {
		return new DefaultShortArray(arr, offset, length);
	}

	static  ShortArray wrap(ShortArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultShortArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  ShortArray concat(ShortArray arr, short element) {
		short[] result = new short[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  ShortArray concat(short element, ShortArray arr) {
		short[] result = new short[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  ShortArray concat(ShortArray arr, Collection<? extends Short> elements) {
		ShortArray result = ShortArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setShort(i, arr.getShort(i));
		}
		int index = arr.size();
		for(short value : elements) {
			result.setShort(index++, value);
		}
		return result;
	}
	
	static  ShortArray concat(Collection<? extends Short> elements, ShortArray arr) {
		ShortArray result = ShortArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(short value : elements) {
			result.setShort(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setShort(i + index, arr.getShort(i));
		}
		return result;
	}
	
	static  ShortArray concat(ShortArray arr, ShortArray arr2) {
		ShortArray result = ShortArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setShort(i, arr.getShort(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setShort(i + arr2.size(), arr2.getShort(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addShort(short e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addShort(int index, short element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllShorts(ShortCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Short> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Short> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default ShortArray subList(int fromIndex, int toIndex) {
		return ShortArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default ShortArray subArray(int offset, int length) {
		return ShortArray.wrap(this, offset, length);
	}
	
	default void copyTo(short[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(short[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(short key);
	
	void fill(short val);
	
	short[] getInternalArray();
	
	int getInternalOffset();
}