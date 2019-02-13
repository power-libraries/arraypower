package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import com.github.powerlibraries.primitive.collections.BooleanCollection;
import com.github.powerlibraries.primitive.collections.BooleanList;

public interface BooleanArray extends BooleanList, Array<Boolean>, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static BooleanArray EMPTY = new DefaultBooleanArray(new boolean[0]);
	
	@SuppressWarnings("unchecked")
	public static  BooleanArray empty() {
		return EMPTY;
	}
	
	public static  BooleanArray ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultBooleanArray(new boolean[size]);
	}
	
	public static  BooleanArray copy(Collection<? extends Boolean> c) {
		BooleanArray result = ofSize(c.size());
		int i=0;
		for(Boolean v:c)
			result.setBoolean(i++, v);
		return result;
	}
	
	public static  BooleanArray copy(BooleanArray arr) {
		return new DefaultBooleanArray(arr.toBooleanArray());
	}
	
	public static  BooleanArray copy(boolean[] arr) {
		return new DefaultBooleanArray(Arrays.copyOf(arr, arr.length));
	}
	
	public static  BooleanArray copy(boolean[] arr, int offset, int length) {
		return new DefaultBooleanArray(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	public static  BooleanArray copy(Boolean[] arr) {
		return copy(arr, 0, arr.length);
	}
	
	public static  BooleanArray copy(Boolean[] arr, int offset, int length) {
		boolean[] copy = new boolean[length];
		for(int i=0; i < length; i++) {
			copy[i] = arr[offset + i];
		}
		return new DefaultBooleanArray(copy);
	}
	
	public static  BooleanArray wrap(boolean... arr) {
		return new DefaultBooleanArray(arr);
	}
	
	public static  BooleanArray wrap(BooleanArray arr) {
		return new DefaultBooleanArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static  BooleanArray wrap(boolean[] arr, int offset, int length) {
		return new DefaultBooleanArray(arr, offset, length);
	}

	public static  BooleanArray wrap(BooleanArray arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultBooleanArray(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static  BooleanArray concat(BooleanArray arr, boolean element) {
		boolean[] result = new boolean[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	public static  BooleanArray concat(boolean element, BooleanArray arr) {
		boolean[] result = new boolean[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	public static  BooleanArray concat(BooleanArray arr, Collection<? extends Boolean> elements) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray concat(Collection<? extends Boolean> elements, BooleanArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray concat(BooleanArray arr, BooleanArray arr2) {
		// TODO Auto-generated method stub
	}



	//Interface methods
	
	@Override
	public default boolean addBoolean(boolean e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default void addBoolean(int index, boolean element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllBooleans(BooleanCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAll(Collection<? extends Boolean> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public default boolean addAll(int index, Collection<? extends Boolean> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default BooleanArray subList(int fromIndex, int toIndex) {
		return BooleanArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default BooleanArray subArray(int offset, int length) {
		return BooleanArray.wrap(this, offset, length);
	}
	
	public void fill(boolean val);
	
	public boolean[] getInternalArray();
	
	public int getInternalOffset();
}