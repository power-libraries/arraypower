package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.IntCollection;
import com.github.powerlibraries.primitive.collections.IntList;

public interface IntArray extends IntList, Array<Integer>, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static IntArray EMPTY = new DefaultIntArray(new int[0]);
	
	@SuppressWarnings("unchecked")
	public static  IntArray empty() {
		return EMPTY;
	}
	
	public static  IntArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultIntArray(new int[size]);
	}
	
	public static  IntArray copy(Collection<? extends Integer> c) {
		IntArray result = ofSize(c.size());
		int i=0;
		for(Integer v:c)
			result.setInt(i++, v);
		return result;
	}
	
	public static  IntArray copy(IntArray arr) {
		return new DefaultIntArray(arr.toIntArray());
	}
	
	public static  IntArray copy(int[] arr) {
		return new DefaultIntArray(Arrays.copyOf(arr, arr.length));
	}
	
	public static  IntArray copy(int[] arr, int offset, int length) {
		return new DefaultIntArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	public static  IntArray copy(Integer[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	public static  IntArray copy(Integer[] arr, int offset, int length) {
		int[] copy = new int[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultIntArray(copy);
	}
	
	public static  IntArray wrap(int... arr) {
		return new DefaultIntArray(arr);
	}
	
	public static  IntArray wrap(IntArray arr) {
		return new DefaultIntArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static  IntArray wrap(int[] arr, int offset, int length) {
		return new DefaultIntArray(arr, offset, length);
	}

	public static  IntArray wrap(IntArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultIntArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static  IntArray concat(IntArray arr, int element) {
		int[] result = new int[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	public static  IntArray concat(int element, IntArray arr) {
		int[] result = new int[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	public static  IntArray concat(IntArray arr, Collection<? extends Integer> elements) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray concat(Collection<? extends Integer> elements, IntArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray concat(IntArray arr, IntArray arr2) {
		// TODO Auto-generated method stub
	}



	//Interface methods
	
	@Override
	public default boolean addInt(int e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default void addInt(int index, int element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllInts(IntCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAll(Collection<? extends Integer> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public default boolean addAll(int index, Collection<? extends Integer> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default IntArray subList(int fromIndex, int toIndex) {
		return IntArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default IntArray subArray(int offset, int length) {
		return IntArray.wrap(this, offset, length);
	}
	
	public int binarySearch(int key);
	
	public void fill(int val);
	
	public int[] getInternalArray();
	
	public int getInternalOffset();
}