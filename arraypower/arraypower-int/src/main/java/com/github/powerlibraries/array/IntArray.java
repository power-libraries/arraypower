package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
		// TODO Auto-generated method stub
	}
	
	public static  IntArray copy(Integer[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray copy(Integer[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray wrap(int[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray wrap(IntArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray copy(int[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray wrap(int[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray wrap(IntArray arr, int offset, int lengt) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray concat(IntArray arr, int element) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray concat(IntArray arr, int... elements) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray concat(int element, IntArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  IntArray concat(int[] elements, IntArray arr) {
		// TODO Auto-generated method stub
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
	
	public static  IntArray reverse(IntArray arr) {
		int[] a = new int[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getInt(arr.size()-i-1);
		return new DefaultIntArray(a);
	}



	//Interface methods
	
	@Override
	public default boolean addInt(int e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllInts(IntCollection c) throws UnsupportedOperationException {
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