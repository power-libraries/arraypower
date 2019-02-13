package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.LongCollection;
import com.github.powerlibraries.primitive.collections.LongList;

public interface LongArray extends LongList, Array<Long>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static LongArray EMPTY = new DefaultLongArray(new long[0]);
	
	@SuppressWarnings("unchecked")
	static  LongArray empty() {
		return EMPTY;
	}
	
	static  LongArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultLongArray(new long[size]);
	}
	
	static  LongArray copy(Collection<? extends Long> c) {
		LongArray result = ofSize(c.size());
		int i=0;
		for(Long v:c)
			result.setLong(i++, v);
		return result;
	}
	
	static  LongArray copy(LongArray arr) {
		return new DefaultLongArray(arr.toLongArray());
	}
	
	static  LongArray copy(long[] arr) {
		return new DefaultLongArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  LongArray copy(long[] arr, int offset, int length) {
		return new DefaultLongArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  LongArray copy(Long[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  LongArray copy(Long[] arr, int offset, int length) {
		long[] copy = new long[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultLongArray(copy);
	}
	
	static  LongArray wrap(long... arr) {
		return new DefaultLongArray(arr);
	}
	
	static  LongArray wrap(LongArray arr) {
		return new DefaultLongArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  LongArray wrap(long[] arr, int offset, int length) {
		return new DefaultLongArray(arr, offset, length);
	}

	static  LongArray wrap(LongArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultLongArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  LongArray concat(LongArray arr, long element) {
		long[] result = new long[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  LongArray concat(long element, LongArray arr) {
		long[] result = new long[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  LongArray concat(LongArray arr, Collection<? extends Long> elements) {
		LongArray result = LongArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setLong(i, arr.getLong(i));
		}
		int index = arr.size();
		for(long value : elements) {
			result.setLong(index++, value);
		}
		return result;
	}
	
	static  LongArray concat(Collection<? extends Long> elements, LongArray arr) {
		LongArray result = LongArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(long value : elements) {
			result.setLong(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setLong(i + index, arr.getLong(i));
		}
		return result;
	}
	
	static  LongArray concat(LongArray arr, LongArray arr2) {
		LongArray result = LongArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setLong(i, arr.getLong(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setLong(i + arr2.size(), arr2.getLong(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addLong(long e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addLong(int index, long element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllLongs(LongCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Long> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Long> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default LongArray subList(int fromIndex, int toIndex) {
		return LongArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default LongArray subArray(int offset, int length) {
		return LongArray.wrap(this, offset, length);
	}
	
	default void copyTo(long[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(long[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(long key);
	
	void fill(long val);
	
	long[] getInternalArray();
	
	int getInternalOffset();
}