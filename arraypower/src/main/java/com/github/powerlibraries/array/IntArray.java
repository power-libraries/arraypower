package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;



import com.github.powerlibraries.primitive.collections.IntList;

public interface IntArray extends IntList, RandomAccess {

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
		...
	}
	
	public static  IntArray copy(Integer[] arr) {
		...
	}
	
	public static  IntArray copy(Integer[] arr, int offset, int length) {
		...
	}
	
	public static  IntArray wrap(int[] arr) {
		...
	}
	
	public static  IntArray wrap(IntArray arr) {
		...
	}
	
	public static  IntArray copy(int[] arr, int offset, int length) {
		...
	}
	
	public static  IntArray wrap(int[] arr, int offset, int length) {
		...
	}
	
	public static  IntArray wrap(IntArray arr, int offset, int lengt) {
		...
	}
	
	public static  IntArray concat(IntArray arr, int element) {
		...
	}
	
	public static  IntArray concat(IntArray arr, int... elements) {
		...
	}
	
	public static  IntArray concat(int element, IntArray arr) {
		...
	}
	
	public static  IntArray concat(int[] elements, IntArray arr) {
		...
	}
	
	public static  IntArray concat(IntArray arr, Collection<? extends Integer> elements) {
		...
	}
	
	public static  IntArray concat(Collection<? extends Integer> elements, IntArray arr) {
		...
	}
	
	public static  IntArray concat(IntArray arr, IntArray arr2) {
		...
	}
	
	public static  IntArray reverse(IntArray arr) {
		int[] a = new int[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getInt(arr.size()-i-1);
		return new DefaultIntArray(a);
	}



	//Interface methods
	
	@Override
	public default boolean addBoolean(boolean e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllBooleans(BooleanCollection c) throws UnsupportedOperationException {
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
}