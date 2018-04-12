package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
		...
	}
	
	public static  ShortArray copy(Short[] arr) {
		...
	}
	
	public static  ShortArray copy(Short[] arr, int offset, int length) {
		...
	}
	
	public static  ShortArray wrap(short[] arr) {
		...
	}
	
	public static  ShortArray wrap(ShortArray arr) {
		...
	}
	
	public static  ShortArray copy(short[] arr, int offset, int length) {
		...
	}
	
	public static  ShortArray wrap(short[] arr, int offset, int length) {
		...
	}
	
	public static  ShortArray wrap(ShortArray arr, int offset, int lengt) {
		...
	}
	
	public static  ShortArray concat(ShortArray arr, short element) {
		...
	}
	
	public static  ShortArray concat(ShortArray arr, short... elements) {
		...
	}
	
	public static  ShortArray concat(short element, ShortArray arr) {
		...
	}
	
	public static  ShortArray concat(short[] elements, ShortArray arr) {
		...
	}
	
	public static  ShortArray concat(ShortArray arr, Collection<? extends Short> elements) {
		...
	}
	
	public static  ShortArray concat(Collection<? extends Short> elements, ShortArray arr) {
		...
	}
	
	public static  ShortArray concat(ShortArray arr, ShortArray arr2) {
		...
	}
	
	public static  ShortArray reverse(ShortArray arr) {
		short[] a = new short[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getShort(arr.size()-i-1);
		return new DefaultShortArray(a);
	}



	//Interface methods
	
	@Override
	public default boolean addShort(short e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllShorts(ShortCollection c) throws UnsupportedOperationException {
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