package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.github.powerlibraries.primitive.collections.AbstractDoubleList;
import com.github.powerlibraries.primitive.collections.DoubleListIterator;
import com.github.powerlibraries.primitive.common.DefaultDoublePointer;
import com.github.powerlibraries.primitive.common.DoublePointer;

public class DefaultDoubleArray extends AbstractDoubleList implements DoubleArray {

	private final double[] elementData;
	private final int offset;
	private final int length;

	DefaultDoubleArray(double[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultDoubleArray(double[] elementData, int offset, int length) {
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
	public double getDouble(int index) {
		rangeCheck(index);
		return elementData[offset+index];
	}

	@Override
	public double setDouble(int index, double element) {
		rangeCheck(index);
		double old = elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public int indexOfDouble(double o) {
		for(int i=offset; i<offset+length; i++) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfDouble(double o) {
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
				elementData[i] = 0d;
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill(0d);
	}
	
	@Override
	public void fill(double value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = 0d;
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
	public int binarySearch(double key) {
		return Arrays.binarySearch(elementData, offset, offset+length, key);
	}
	
	@Override
	public boolean containsDouble(double o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Double remove(int index) {
		rangeCheck(index);
		double old = elementData[offset+index];
		elementData[offset+index] = 0d;
		return old;
	}

	@Override
	public double[] toDoubleArray() {
		double[] result = new double[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public double[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeDouble(double o) {
		for(int i=offset;i<offset+length;i++) {
			if(elementData[i] == o) {
				elementData[i] = 0d;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public DoubleListIterator listIterator(int index) {
		return new DoubleArrayIterator(this, index);
	}

	@Override
	public Iterable<DoublePointer> primitiveIterable(int index) {
		return new DoublePrimitiveIterable(index);
	}
	
	@Override
	public void reverse() {
		for(int i = 0; i < length / 2; i++) {
			double temp = elementData[offset+i];
			elementData[i] = elementData[offset + length - i - 1];
			elementData[offset + length - i - 1] = temp;
		}
	}
	
	private class DoublePrimitiveIterable implements Iterable<DoublePointer> {

		private int initialPosition;

		public DoublePrimitiveIterable(int initialPosition) {
			this.initialPosition = initialPosition;
		}

		@Override
		public Iterator<DoublePointer> iterator() {
			return new DoublePointerIterator(initialPosition);
		}
	}
	
	private class DoublePointerIterator implements Iterator<DoublePointer> {

		private int position;
		private DefaultDoublePointer pointer;
		
		public DoublePointerIterator(int position) {
			this.position = position;
			this.pointer = new DefaultDoublePointer();
		}

		@Override
		public boolean hasNext() {
			return position+1<length;
		}

		@Override
		public DoublePointer next() {
			position++;
			if(position>=length)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(position));
			pointer.set(getDouble(position));
			return pointer;
		}
		
	}
}