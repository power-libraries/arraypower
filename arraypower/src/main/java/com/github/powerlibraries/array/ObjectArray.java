package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

import java.util.Comparator;


import com.github.powerlibraries.primitive.collections.ObjectList;

public interface ObjectArray<E> extends ObjectList<E>, RandomAccess {

	@SuppressWarnings("rawtypes")
	public final static ObjectArray EMPTY = new DefaultObjectArray(new Object[0]);
	
	@SuppressWarnings("unchecked")
	public static <E> ObjectArray<E> empty() {
		return EMPTY;
	}
	
	public static <E> ObjectArray<E> ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultObjectArray<E>(new Object[size]);
	}
	
	public static <E> ObjectArray<E> copy(Collection<? extends E> c) {
		ObjectArray<E> result = ofSize(c.size());
		int i=0;
		for(E v:c)
			result.setObject(i++, v);
		return result;
	}
	
	public static <E> ObjectArray<E> copy(ObjectArray<? extends E> arr) {
		return new DefaultObjectArray<E>(arr.toObjectArray());
	}
	
	public static <E> ObjectArray<E> copy(E[] arr) {
		...
	}
	
	public static <E> ObjectArray<E> wrap(Object[] arr) {
		...
	}
	
	public static <E> ObjectArray<E> wrap(ObjectArray<? extends E> arr) {
		...
	}
	
	public static <E> ObjectArray<E> copy(E[] arr, int offset, int length) {
		...
	}
	
	public static <E> ObjectArray<E> wrap(Object[] arr, int offset, int length) {
		...
	}
	
	public static <E> ObjectArray<E> wrap(ObjectArray<? extends E> arr, int offset, int lengt) {
		...
	}
	
	public static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, E element) {
		...
	}
	
	public static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, E... elements) {
		...
	}
	
	public static <E> ObjectArray<E> concat(E element, ObjectArray<? extends E> arr) {
		...
	}
	
	public static <E> ObjectArray<E> concat(E[] elements, ObjectArray<? extends E> arr) {
		...
	}
	
	public static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, Collection<? extends E> elements) {
		...
	}
	
	public static <E> ObjectArray<E> concat(Collection<? extends E> elements, ObjectArray<? extends E> arr) {
		...
	}
	
	public static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, ObjectArray<? extends E> arr2) {
		...
	}
	
	public static <E> ObjectArray<E> reverse(ObjectArray<? extends E> arr) {
		Object[] a = new Object[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getObject(arr.size()-i-1);
		return new DefaultObjectArray<E>(a);
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
	public default ObjectArray<E> subList(int fromIndex, int toIndex) {
		return ObjectArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	public default ObjectArray<E> subArray(int offset, int length) {
		return ObjectArray.wrap(this, offset, length);
	}
	
	public int binarySearch(E key, Comparator<E> comp);
	
	public void fill(E val);
}