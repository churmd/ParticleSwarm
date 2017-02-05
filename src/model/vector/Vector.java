package model.vector;

public class Vector<T extends Number> {

	private T[] elements;

	public Vector(T[] elements) {
		this.elements = elements;
	}
	
	public int numDimensions(){
		return elements.length;
	}
	
	public T getElementAtIndex(int index){
		return elements[index];
	}
}
