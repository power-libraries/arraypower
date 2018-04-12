package com.github.powerlibraries.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Objects;

import com.github.powerlibraries.primitive.collections.AbstractFloatList;
import com.github.powerlibraries.primitive.collections.FloatCollection;
import com.github.powerlibraries.primitive.common.FloatPointer;

public class DefaultFloatArray extends AbstractFloatList implements FloatArray {

	private final float[] elementData;
	private final int offset;
	private final int length;

	DefaultFloatArray(float[] elementData) {
		this(elementData, 0, elementData.length);
	}
	
	DefaultFloatArray(float[] elementData, int offset, int length) {
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
	public float getFloat(int index) {
		rangeCheck(index);
		return elementData[offset+index];
	}

	@Override
	public float setFloat(int index, float element) {
		rangeCheck(index);
		float old = elementData[offset+index];
		elementData[offset+index] = element;
		return old;
	}

	@Override
	public void addFloat(int index, float element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOfFloat(float o) {
		for(int i=offset; i<offset+length; i++) {
			if(elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOfFloat(float o) {
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
	public boolean addAll(Collection<? extends Float> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Float> c) throws UnsupportedOperationException {
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
				elementData[i] = 0f;
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		fill(0f);
	}
	
	@Override
	public void fill(float value) {
		for(int i=offset; i<offset+length; i++) {
			elementData[i] = 0f;
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
	public void sort() {
		Arrays.sort(elementData, offset, offset+length);
	}
	
	@Override
	public void parallelSort() {
		Arrays.parallelSort(elementData, offset, offset+length);
	}
	
	@Override
	public int binarySearch(float key) {
		return Arrays.binarySearch(elementData, offset, offset+length, key);
	}
	
	@Override
	public boolean containsFloat(float o) {
		for(int i=offset+length; i>=offset; i--) {
			if(elementData[i] == o) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Float remove(int index) {
		rangeCheck(index);
		float old = elementData[offset+index];
		elementData[offset+index] = 0f;
		return old;
	}

	@Override
	public float[] toFloatArray() {
		float[] result = new float[length];
		for(int i=0;i<length;i++)
			result[i] = elementData[i+offset];
		return result;
	}
	
	@Override
	public float[] getInternalArray() {
		return elementData;
	}
	
	@Override
	public int getInternalOffset() {
		return offset;
	}
	
	@Override
	public boolean removeFloat(float o) {
		for(int i=0;i<length;i++) {
			if(elementData[i] == o) {
				elementData[i] = 0f;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ListIterator<Float> listIterator(int index) {
		return new ArrayIterator<Float>(this, index);
	}

	@Override
	public Iterable<FloatPointer> primitiveIterable(int index) {
		return new FloatPrimitiveIterable(this, index);
	}
	
	public FloatArray reverse() {
		float[] a = new float[arr.size()];
		for(int i=0;i<arr.size();i++)
			a[i] = arr.getFloat(arr.size()-i-1);
		return new DefaultFloatArray(a);
	}
}