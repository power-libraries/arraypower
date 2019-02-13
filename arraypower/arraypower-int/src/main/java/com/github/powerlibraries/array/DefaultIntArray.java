package com.github.powerlibraries.array;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.github.powerlibraries.primitive.collections.AbstractIntList;
import com.github.powerlibraries.primitive.collections.IntListIterator;
import com.github.powerlibraries.primitive.common.DefaultIntPointer;
import com.github.powerlibraries.primitive.common.IntPointer;

public class DefaultIntArray extends AbstractIntList implements IntArray {

	private final int[] elementData;
	private final int offset;
	private final int length;

	DefaultIntArray(int[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultIntArray(int[] elementData, int offset, int length) {
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
	public int getInt(int index) {
		rangeCheck(index);
		return elementData[offset+index];
	}

	@Override
	public int setInt(int index, int element) {
		rangeCheck(index);
		int old = elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public int indexOfInt(int o) {
		for(int i=offset; i<offset+length; i++) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfInt(int o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
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
				elementData[i] = 0;
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill(0);
	}
	
	@Override
	public void fill(int value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = 0;
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
	public void sort() {
		Arrays.sort(elementData, offset, offset+length);
	}
	
	@Override
	public void parallelSort() {
		Arrays.parallelSort(elementData, offset, offset+length);
	}
	
	@Override
	public int binarySearch(int key) {
		return Arrays.binarySearch(elementData, offset, offset+length, key);
	}
	
	@Override
	public boolean containsInt(int o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int removeAt(int index) {
		rangeCheck(index);
		int old = elementData[offset+index];
		elementData[offset+index] = 0;
		return old;
	}

	@Override
	public int[] toIntArray() {
		int[] result = new int[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public int[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeInt(int o) {
		for(int i=offset;i<offset+length;i++) {
			if(elementData[i] == o) {
				elementData[i] = 0;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public IntListIterator listIterator(int index) {
		return new IntArrayIterator(this, index);
	}

	@Override
	public Iterable<IntPointer> primitiveIterable(int index) {
		return new IntPrimitiveIterable(index);
	}
	
	@Override
	public void reverse() {
		for(int i = 0; i < length / 2; i++) {
			int temp = elementData[offset+i];
			elementData[i] = elementData[offset + length - i - 1];
			elementData[offset + length - i - 1] = temp;
		}
	}
	
	@Override
	public IntBuffer asBuffer() {
		return IntBuffer.wrap(elementData, offset, length);
	}
	
	private class IntPrimitiveIterable implements Iterable<IntPointer> {

		private int initialPosition;

		public IntPrimitiveIterable(int initialPosition) {
			this.initialPosition = initialPosition;
		}

		@Override
		public Iterator<IntPointer> iterator() {
			return new IntPointerIterator(initialPosition);
		}
	}
	
	private class IntPointerIterator implements Iterator<IntPointer> {

		private int position;
		private DefaultIntPointer pointer;
		
		public IntPointerIterator(int position) {
			this.position = position;
			this.pointer = new DefaultIntPointer();
		}

		@Override
		public boolean hasNext() {
			return position+1<length;
		}

		@Override
		public IntPointer next() {
			position++;
			if(position>=length)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(position));
			pointer.set(getInt(position));
			return pointer;
		}
		
	}
}