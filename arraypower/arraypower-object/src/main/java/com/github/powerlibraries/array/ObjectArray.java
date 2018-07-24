package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import java.util.Comparator;

import com.github.powerlibraries.primitive.collections.ObjectCollection;
import com.github.powerlibraries.primitive.collections.ObjectList;

public interface ObjectArray<E> extends ObjectList<E>, Array<E>, RandomAccess {

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
		return new DefaultObjectArray<E>(Arrays.copyOf(arr, arr.length));
	}
	
	public static <E> ObjectArray<E> copy(E[] arr, int offset, int length) {
		return new DefaultObjectArray<E>(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	public static <E> ObjectArray<E> wrap(Object... arr) {
		return new DefaultObjectArray<E>(arr);
	}
	
	public static <E> ObjectArray<E> wrap(ObjectArray<? extends E> arr) {
		return new DefaultObjectArray<E>(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static <E> ObjectArray<E> wrap(Object[] arr, int offset, int length) {
		return new DefaultObjectArray<E>(arr, offset, length);
	}

	public static <E> ObjectArray<E> wrap(ObjectArray<? extends E> arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultObjectArray<E>(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	public static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, E element) {
		Object[] result = new Object[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	public static <E> ObjectArray<E> concat(E element, ObjectArray<? extends E> arr) {
		Object[] result = new Object[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	public static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, Collection<? extends E> elements) {
		// TODO Auto-generated method stub
	}
	
	public static <E> ObjectArray<E> concat(Collection<? extends E> elements, ObjectArray<? extends E> arr) {
		// TODO Auto-generated method stub
	}
	
	public static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, ObjectArray<? extends E> arr2) {
		// TODO Auto-generated method stub
	}



	//Interface methods
	
	@Override
	public default boolean addObject(E e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default void addObject(int index, E element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAllObjects(ObjectCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public default boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public default boolean addAll(int index, Collection<? extends E> c) throws UnsupportedOperationException {
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
	
	public Object[] getInternalArray();
	
	public int getInternalOffset();
}