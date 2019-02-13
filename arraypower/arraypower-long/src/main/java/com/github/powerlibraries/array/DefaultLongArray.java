package com.github.powerlibraries.array;

import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.github.powerlibraries.primitive.collections.AbstractLongList;
import com.github.powerlibraries.primitive.collections.LongListIterator;
import com.github.powerlibraries.primitive.common.DefaultLongPointer;
import com.github.powerlibraries.primitive.common.LongPointer;

public class DefaultLongArray extends AbstractLongList implements LongArray {

	private final long[] elementData;
	private final int offset;
	private final int length;

	DefaultLongArray(long[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultLongArray(long[] elementData, int offset, int length) {
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
	public long getLong(int index) {
		rangeCheck(index);
		return elementData[offset+index];
	}

	@Override
	public long setLong(int index, long element) {
		rangeCheck(index);
		long old = elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public int indexOfLong(long o) {
		for(int i=offset; i<offset+length; i++) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfLong(long o) {
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
				elementData[i] = 0L;
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill(0L);
	}
	
	@Override
	public void fill(long value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = 0L;
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
	public int binarySearch(long key) {
		return Arrays.binarySearch(elementData, offset, offset+length, key);
	}
	
	@Override
	public boolean containsLong(long o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public long removeAt(int index) {
		rangeCheck(index);
		long old = elementData[offset+index];
		elementData[offset+index] = 0L;
		return old;
	}

	@Override
	public long[] toLongArray() {
		long[] result = new long[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public long[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeLong(long o) {
		for(int i=offset;i<offset+length;i++) {
			if(elementData[i] == o) {
				elementData[i] = 0L;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public LongListIterator listIterator(int index) {
		return new LongArrayIterator(this, index);
	}

	@Override
	public Iterable<LongPointer> primitiveIterable(int index) {
		return new LongPrimitiveIterable(index);
	}
	
	@Override
	public void reverse() {
		for(int i = 0; i < length / 2; i++) {
			long temp = elementData[offset+i];
			elementData[i] = elementData[offset + length - i - 1];
			elementData[offset + length - i - 1] = temp;
		}
	}
	
	@Override
	public LongBuffer asBuffer() {
		return LongBuffer.wrap(elementData, offset, length);
	}
	
	private class LongPrimitiveIterable implements Iterable<LongPointer> {

		private int initialPosition;

		public LongPrimitiveIterable(int initialPosition) {
			this.initialPosition = initialPosition;
		}

		@Override
		public Iterator<LongPointer> iterator() {
			return new LongPointerIterator(initialPosition);
		}
	}
	
	private class LongPointerIterator implements Iterator<LongPointer> {

		private int position;
		private DefaultLongPointer pointer;
		
		public LongPointerIterator(int position) {
			this.position = position;
			this.pointer = new DefaultLongPointer();
		}

		@Override
		public boolean hasNext() {
			return position+1<length;
		}

		@Override
		public LongPointer next() {
			position++;
			if(position>=length)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(position));
			pointer.set(getLong(position));
			return pointer;
		}
		
	}
}