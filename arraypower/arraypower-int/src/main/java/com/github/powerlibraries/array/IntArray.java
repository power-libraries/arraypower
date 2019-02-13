package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.IntCollection;
import com.github.powerlibraries.primitive.collections.IntList;

public interface IntArray extends IntList, Array<Integer>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static IntArray EMPTY = new DefaultIntArray(new int[0]);
	
	@SuppressWarnings("unchecked")
	static  IntArray empty() {
		return EMPTY;
	}
	
	static  IntArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultIntArray(new int[size]);
	}
	
	static  IntArray copy(Collection<? extends Integer> c) {
		IntArray result = ofSize(c.size());
		int i=0;
		for(Integer v:c)
			result.setInt(i++, v);
		return result;
	}
	
	static  IntArray copy(IntArray arr) {
		return new DefaultIntArray(arr.toIntArray());
	}
	
	static  IntArray copy(int[] arr) {
		return new DefaultIntArray(Arrays.copyOf(arr, arr.length));
	}
	
	static  IntArray copy(int[] arr, int offset, int length) {
		return new DefaultIntArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static  IntArray copy(Integer[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	static  IntArray copy(Integer[] arr, int offset, int length) {
		int[] copy = new int[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultIntArray(copy);
	}
	
	static  IntArray wrap(int... arr) {
		return new DefaultIntArray(arr);
	}
	
	static  IntArray wrap(IntArray arr) {
		return new DefaultIntArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  IntArray wrap(int[] arr, int offset, int length) {
		return new DefaultIntArray(arr, offset, length);
	}

	static  IntArray wrap(IntArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultIntArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static  IntArray concat(IntArray arr, int element) {
		int[] result = new int[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static  IntArray concat(int element, IntArray arr) {
		int[] result = new int[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static  IntArray concat(IntArray arr, Collection<? extends Integer> elements) {
		IntArray result = IntArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setInt(i, arr.getInt(i));
		}
		int index = arr.size();
		for(int value : elements) {
			result.setInt(index++, value);
		}
		return result;
	}
	
	static  IntArray concat(Collection<? extends Integer> elements, IntArray arr) {
		IntArray result = IntArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(int value : elements) {
			result.setInt(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setInt(i + index, arr.getInt(i));
		}
		return result;
	}
	
	static  IntArray concat(IntArray arr, IntArray arr2) {
		IntArray result = IntArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setInt(i, arr.getInt(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setInt(i + arr2.size(), arr2.getInt(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addInt(int e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addInt(int index, int element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllInts(IntCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends Integer> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends Integer> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default IntArray subList(int fromIndex, int toIndex) {
		return IntArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default IntArray subArray(int offset, int length) {
		return IntArray.wrap(this, offset, length);
	}
	
	default void copyTo(int[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(int[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(int key);
	
	void fill(int val);
	
	int[] getInternalArray();
	
	int getInternalOffset();
}