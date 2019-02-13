package com.github.powerlibraries.array;

import java.nio.ObjectBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import java.util.Comparator;
import java.util.Objects;

import com.github.powerlibraries.primitive.collections.AbstractObjectList;
import com.github.powerlibraries.primitive.collections.ObjectListIterator;
import com.github.powerlibraries.primitive.common.DefaultObjectPointer;
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
		if (index >= length || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
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
		for(int i=offset;i<offset+length;i++)
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
	public E removeAt(int index) {
		rangeCheck(index);
		E old = (E) elementData[offset+index];
		elementData[offset+index] = null;
		return old;
	}

	@Override
	public Object[] toObjectArray() {
		Object[] result = new Object[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public Object[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeObject(E o) {
		for(int i=offset;i<offset+length;i++) {
			if(Objects.equals(elementData[i], o)) {
				elementData[i] = null;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ObjectListIterator<E> listIterator(int index) {
		return new ObjectArrayIterator<E>(this, index);
	}

	@Override
	public Iterable<ObjectPointer<E>> primitiveIterable(int index) {
		return new ObjectPrimitiveIterable(index);
	}
	
	@Override
	public void reverse() {
		for(int i = 0; i < length / 2; i++) {
			Object temp = elementData[offset+i];
			elementData[i] = elementData[offset + length - i - 1];
			elementData[offset + length - i - 1] = temp;
		}
	}
	
	@Override
	public ObjectBuffer asBuffer() {
		return ObjectBuffer.wrap(elementData, offset, length);
	}
	
	private class ObjectPrimitiveIterable implements Iterable<ObjectPointer<E>> {

		private int initialPosition;

		public ObjectPrimitiveIterable(int initialPosition) {
			this.initialPosition = initialPosition;
		}

		@Override
		public Iterator<ObjectPointer<E>> iterator() {
			return new ObjectPointerIterator(initialPosition);
		}
	}
	
	private class ObjectPointerIterator implements Iterator<ObjectPointer<E>> {

		private int position;
		private DefaultObjectPointer<E> pointer;
		
		public ObjectPointerIterator(int position) {
			this.position = position;
			this.pointer = new DefaultObjectPointer<E>();
		}

		@Override
		public boolean hasNext() {
			return position+1<length;
		}

		@Override
		public ObjectPointer<E> next() {
			position++;
			if(position>=length)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(position));
			pointer.set(getObject(position));
			return pointer;
		}
		
	}
}