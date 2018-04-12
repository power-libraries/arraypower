package com.github.powerlibraries.array;

import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.LongCollection;
import com.github.powerlibraries.primitive.collections.LongList;

public interface LongArray extends LongList, Array<Long>, RandomAccess {

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
		// TODO Auto-generated method stub
	}
	
	public static  LongArray copy(long[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray copy(Long[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray copy(Long[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray wrap(long[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray wrap(LongArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray wrap(long[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray wrap(LongArray arr, int offset, int lengt) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray concat(LongArray arr, long element) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray concat(LongArray arr, long... elements) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray concat(long element, LongArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray concat(long[] elements, LongArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray concat(LongArray arr, Collection<? extends Long> elements) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray concat(Collection<? extends Long> elements, LongArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  LongArray concat(LongArray arr, LongArray arr2) {
		// TODO Auto-generated method stub
	}
	




	//Interface methods
	
	@Override
	public default boolean addLong(long e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default void addLong(int index, long element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllLongs(LongCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAll(Collection<? extends Long> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public default boolean addAll(int index, Collection<? extends Long> c) throws UnsupportedOperationException {
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
	
	public long[] getInternalArray();
	
	public int getInternalOffset();
	
	@Override
	LongArray reverse();
}