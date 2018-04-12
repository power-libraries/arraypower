package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray copy(Boolean[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray copy(Boolean[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray wrap(boolean[] arr) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray wrap(BooleanArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray copy(boolean[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray wrap(boolean[] arr, int offset, int length) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray wrap(BooleanArray arr, int offset, int lengt) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray concat(BooleanArray arr, boolean element) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray concat(BooleanArray arr, boolean... elements) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray concat(boolean element, BooleanArray arr) {
		// TODO Auto-generated method stub
	}
	
	public static  BooleanArray concat(boolean[] elements, BooleanArray arr) {
		// TODO Auto-generated method stub
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
	
	public static  BooleanArray reverse(BooleanArray arr) {
		boolean[] a = new boolean[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getBoolean(arr.size()-i-1);
		return new DefaultBooleanArray(a);
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