package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

import java.util.Comparator;

import com.github.powerlibraries.primitive.collections.ObjectCollection;
import com.github.powerlibraries.primitive.collections.ObjectList;

public interface ObjectArray<E> extends ObjectList<E>, Array<E>, RandomAccess {

	@SuppressWarnings("rawtypes")
	final static ObjectArray EMPTY = new DefaultObjectArray(new Object[0]);
	
	@SuppressWarnings("unchecked")
	static <E> ObjectArray<E> empty() {
		return EMPTY;
	}
	
	static <E> ObjectArray<E> ofSize(int size) {
		if(size == 0)
			return empty();
		return new DefaultObjectArray<E>(new Object[size]);
	}
	
	static <E> ObjectArray<E> copy(Collection<? extends E> c) {
		ObjectArray<E> result = ofSize(c.size());
		int i=0;
		for(E v:c)
			result.setObject(i++, v);
		return result;
	}
	
	static <E> ObjectArray<E> copy(ObjectArray<? extends E> arr) {
		return new DefaultObjectArray<E>(arr.toObjectArray());
	}
	
	static <E> ObjectArray<E> copy(E[] arr) {
		return new DefaultObjectArray<E>(Arrays.copyOf(arr, arr.length));
	}
	
	static <E> ObjectArray<E> copy(E[] arr, int offset, int length) {
		return new DefaultObjectArray<E>(Arrays.copyOfRange(arr, offset, offset + length));
	}
	
	static <E> ObjectArray<E> wrap(Object... arr) {
		return new DefaultObjectArray<E>(arr);
	}
	
	static <E> ObjectArray<E> wrap(ObjectArray<? extends E> arr) {
		return new DefaultObjectArray<E>(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static <E> ObjectArray<E> wrap(Object[] arr, int offset, int length) {
		return new DefaultObjectArray<E>(arr, offset, length);
	}

	static <E> ObjectArray<E> wrap(ObjectArray<? extends E> arr, int offset, int lengt) {
		//TODO all of those construcor should check for correct offset and length values
		return new DefaultObjectArray<E>(arr.getInternalArray(), arr.getInternalOffset(), arr.size());
	}
	
	static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, E element) {
		Object[] result = new Object[arr.size() + 1];
		arr.copyTo(result);
		result[arr.size() + 1] = element;
		return wrap(result);
	}
	
	static <E> ObjectArray<E> concat(E element, ObjectArray<? extends E> arr) {
		Object[] result = new Object[arr.size() + 1];
		arr.copyTo(result, 1);
		result[0] = element;
		return wrap(result);
	}
	
	static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, Collection<? extends E> elements) {
		ObjectArray<E> result = ObjectArray.ofSize(arr.size() + elements.size());
		for(int i=0; i<arr.size(); i++) {
			result.setObject(i, arr.getObject(i));
		}
		int index = arr.size();
		for(E value : elements) {
			result.setObject(index++, value);
		}
		return result;
	}
	
	static <E> ObjectArray<E> concat(Collection<? extends E> elements, ObjectArray<? extends E> arr) {
		ObjectArray<E> result = ObjectArray.ofSize(arr.size() + elements.size());
		int index = 0;
		for(E value : elements) {
			result.setObject(index++, value);
		}
		for(int i=0; i<arr.size(); i++) {
			result.setObject(i + index, arr.getObject(i));
		}
		return result;
	}
	
	static <E> ObjectArray<E> concat(ObjectArray<? extends E> arr, ObjectArray<? extends E> arr2) {
		ObjectArray<E> result = ObjectArray.ofSize(arr.size() + arr2.size());
		for(int i=0; i<arr.size(); i++) {
			result.setObject(i, arr.getObject(i));
		}
		for(int i=0; i<arr2.size(); i++) {
			result.setObject(i + arr2.size(), arr2.getObject(i));
		}
		return result;
	}



	//Interface methods
	
	@Override
	default boolean addObject(E e) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default void addObject(int index, E element) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAllObjects(ObjectCollection c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(int index, Collection<? extends E> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	default ObjectArray<E> subList(int fromIndex, int toIndex) {
		return ObjectArray.wrap(this, fromIndex, toIndex-fromIndex);
	}
	
	default ObjectArray<E> subArray(int offset, int length) {
		return ObjectArray.wrap(this, offset, length);
	}
	
	default void copyTo(Object[] other) {
		this.copyTo(other, 0);
	}
	
	default void copyTo(Object[] other, int offset) {
		System.arraycopy(
			getInternalArray(),
			getInternalOffset(),
			other,
			offset,
			Math.min(size(), other.length-offset) 
		);
	}
	
	
	int binarySearch(E key, Comparator<E> comp);
	
	void fill(E val);
	
	Object[] getInternalArray();
	
	int getInternalOffset();
}