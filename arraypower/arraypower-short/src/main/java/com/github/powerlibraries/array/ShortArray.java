package com.github.powerlibraries.array;

import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.ShortCollection;
import com.github.powerlibraries.primitive.collections.ShortList;

public interface ShortArray extends ShortList, Array<Short>, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static ShortArray EMPTY = new DefaultShortArray(new short[0]);
	
	@SuppressWarnings("unchecked")
	public static  ShortArray empty() {
		return EMPTY;
	}
	
	public static  ShortArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultShortArray(new short[size]);
	}
	
	public static  ShortArray copy(Collection<? extends Short> c) {
		ShortArray result = ofSize(c.size());
		int i=0;
		for(Short v:c)
			result.setShort(i++, v);
		return result;
	}
	
	public static  ShortArray copy(ShortArray arr) {
		return new DefaultShortArray(arr.toShortArray());
	}
	
	public static  ShortArray copy(short[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray copy(short[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray copy(Short[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray copy(Short[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray wrap(short[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray wrap(ShortArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray wrap(short[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray wrap(ShortArray arr, int offset, int lengt) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray concat(ShortArray arr, short element) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray concat(ShortArray arr, short... elements) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray concat(short element, ShortArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray concat(short[] elements, ShortArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray concat(ShortArray arr, Collection<? extends Short> elements) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray concat(Collection<? extends Short> elements, ShortArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  ShortArray concat(ShortArray arr, ShortArray arr2) {
		// TODO Auto-generated method stub
	}
	




	//Interface methods
	
	@Override
	public default boolean addShort(short e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default void addShort(int index, short element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllShorts(ShortCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAll(Collection<? extends Short> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public default boolean addAll(int index, Collection<? extends Short> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default ShortArray subList(int fromIndex, int toIndex) {
		return ShortArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default ShortArray subArray(int offset, int length) {
		return ShortArray.wrap(this, offset, length);
	}
	
	public int binarySearch(short key);
	
	public void fill(short val);
	
	public short[] getInternalArray();
	
	public int getInternalOffset();
}