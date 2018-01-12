package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;

import java.util.Comparator;

import com.github.powerlibraries.primitive.collections.AbstractObjectList;
import com.github.powerlibraries.primitive.collections.ObjectCollection;
import com.github.powerlibraries.primitive.common.ObjectPointer;

public class DefaultObjectArray<E> extends AbstractObjectList<E> implements ObjectArray<E> {

	private final Object[] elementData;
	private final int offset;
	private final int length;

	DefaultObjectArray(Object[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultObjectArray(Object[] elementData, int offset, int length) {
		this.elementData = elementData;
		this.offset = offset;
		this.length = length;
	}

	/**
     * Checks if the given index is in range.  If not, throws an appropriate
     * runtime exception.
     */
    private void rangeCheck(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+elementData.length;
    }

	@Override
	public E getObject(int index) {
		rangeCheck(index);
		return (E) elementData[offset+index];
	}

	@Override
	public E setObject(int index, E element) {
		rangeCheck(index);
		E old = (E) elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public void addObject(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOfObject(E o) {
		for(int i=offset; i<offset+length; i++) {
			if(Objects.equals(elementData[i], o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfObject(E o) {
		for(int i=offset+length; i>=offset; i--) {
			if(Objects.equals(elementData[i], o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o:c) {
			if(!this.contains(o))
				return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for(Object o:c)
			changed |= this.remove(o);
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		for(int i=offset; i<offset+length; i++) {
			if(!c.contains(elementData[i])) {
				elementData[i] = null;
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill(null);
	}
	
	@Override
	public void fill(E value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = null;
		}
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if(a.length != length)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), length);
		for(int i=0;i<length;i++)
			a[i] = (T)(Object)elementData[i+offset];
		return a;
	}
	
	@Override
	public int binarySearch(E key, Comparator<E> comp) {
		return Arrays.binarySearch(elementData, offset, offset+length, key);
	}
	
	@Override
	public boolean containsObject(E o) {
		for(int i=offset+length; i>=offset; i--) {
			if(Objects.equals(elementData[i], o)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public E remove(int index) {
		rangeCheck(index);
		E old = (E) elementData[offset+index];
		elementData[offset+index] = null;
		return old;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toObjectArray() {
		Object[] result = new Object[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}

	@Override
	public boolean removeObject(E o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAllObjects(ObjectCollection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllObjects(ObjectCollection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAllObjects(ObjectCollection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<ObjectPointer<E>> primitiveIterable() {
		// TODO Auto-generated method stub
		return null;
	}
}