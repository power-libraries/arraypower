package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;



import com.github.powerlibraries.primitive.collections.LongList;

public interface LongArray extends LongList, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static LongArray EMPTY = new DefaultLongArray(new long[0]);
	
	@SuppressWarnings("unchecked")
	public static  LongArray empty() {
		return EMPTY;
	}
	
	public static  LongArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultLongArray(new long[size]);
	}
	
	public static  LongArray copy(Collection<? extends Long> c) {
		LongArray result = ofSize(c.size());
		int i=0;
		for(Long v:c)
			result.setLong(i++, v);
		return result;
	}
	
	public static  LongArray copy(LongArray arr) {
		return new DefaultLongArray(arr.toLongArray());
	}
	
	public static  LongArray copy(long[] arr) {
		...
	}
	
	public static  LongArray copy(Long[] arr) {
		...
	}
	
	public static  LongArray copy(Long[] arr, int offset, int length) {
		...
	}
	
	public static  LongArray wrap(long[] arr) {
		...
	}
	
	public static  LongArray wrap(LongArray arr) {
		...
	}
	
	public static  LongArray copy(long[] arr, int offset, int length) {
		...
	}
	
	public static  LongArray wrap(long[] arr, int offset, int length) {
		...
	}
	
	public static  LongArray wrap(LongArray arr, int offset, int lengt) {
		...
	}
	
	public static  LongArray concat(LongArray arr, long element) {
		...
	}
	
	public static  LongArray concat(LongArray arr, long... elements) {
		...
	}
	
	public static  LongArray concat(long element, LongArray arr) {
		...
	}
	
	public static  LongArray concat(long[] elements, LongArray arr) {
		...
	}
	
	public static  LongArray concat(LongArray arr, Collection<? extends Long> elements) {
		...
	}
	
	public static  LongArray concat(Collection<? extends Long> elements, LongArray arr) {
		...
	}
	
	public static  LongArray concat(LongArray arr, LongArray arr2) {
		...
	}
	
	public static  LongArray reverse(LongArray arr) {
		long[] a = new long[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getLong(arr.size()-i-1);
		return new DefaultLongArray(a);
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
	public default LongArray subList(int fromIndex, int toIndex) {
		return LongArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default LongArray subArray(int offset, int length) {
		return LongArray.wrap(this, offset, length);
	}
	
	
	public int binarySearch(long key);
	
	public void fill(long val);
}