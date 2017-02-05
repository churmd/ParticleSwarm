package model.vector;

import java.util.ArrayList;

public class VectorCalcDouble {

	public VectorCalcDouble() {
	}
	
	/**
	 * Add two vectors together in the form v1 + v2 and returns the result as a new vector
	 * @param v1 The first vector to add
	 * @param v2 The second vector to add
	 * @return A new vector as a result of adding v1 to v2
	 * @throws VectorDimensionException
	 */
	public Vector<Double> add(Vector<Double> v1, Vector<Double> v2) throws VectorDimensionException{
		int d1 = v1.numDimensions();
		int d2 = v2.numDimensions();
		if(d1 != d2){
			throw new VectorDimensionException(d1, d2);
		} else {
			Double[] vector = new Double[d1];
			for(int i = 0; i < d1; i++){
				vector[i] = v1.getElementAtIndex(i) + v2.getElementAtIndex(i);
			}
			return new Vector<Double>(vector);
		}
	}
	
	/**
	 * Adds a list of vectors together
	 * @param vectors A list of vectors to be added together
	 * @return The sum of adding all vectors in the list together
	 * @throws VectorDimensionException
	 */
	public Vector<Double> add(ArrayList<Vector<Double>> vectors) throws VectorDimensionException{
		int d1 = vectors.get(0).numDimensions();
		for(Vector<Double> v : vectors){
			if(v.numDimensions() != d1){
				throw new VectorDimensionException(d1, v.numDimensions());
			}
		}
		
		Double[] vector = new Double[d1];
		for(int i = 0; i < d1; i++){
			vector[i] = 0.0;
		}
		
		for(int i = 0; i < d1; i++){
			for(Vector<Double> v : vectors){
				vector[i] = vector[i] + v.getElementAtIndex(i);
			}
		}
		
		return new Vector<Double>(vector);
	}
	
	/**
	 * Subtract two vectors together in the form v1 - v2 and returns the result as a new vector
	 * @param v1 The vector to be subtracted from
	 * @param v2 The vector to subtract
	 * @return A new vector as a result of subtracting v2 from v1
	 * @throws VectorDimensionException
	 */
	public Vector<Double> subtract(Vector<Double> v1, Vector<Double> v2) throws VectorDimensionException{
		int d1 = v1.numDimensions();
		int d2 = v2.numDimensions();
		if(d1 != d2){
			throw new VectorDimensionException(d1, d2);
		} else {
			Double[] vector = new Double[d1];
			for(int i = 0; i < d1; i++){
				vector[i] = v1.getElementAtIndex(i) - v2.getElementAtIndex(i);
			}
			return new Vector<Double>(vector);
		}
	}
	
	/**
	 * Multiplies all elements in a vector by a constant
	 * @param vector The vector to be multiplied
	 * @param constant The constant to multiply the vector by
	 * @return A new vector resulting from the given vector being multiplied by the constant
	 */
	public Vector<Double> multiplyConstant(Vector<Double> vector, double constant){
		int d = vector.numDimensions();
		Double[] v = new Double[d];
		for(int i = 0; i < d; i++){
			v[i] = vector.getElementAtIndex(i) * constant;
		}
		return new Vector<Double>(v);
	}
	
	/**
	 * Divides all elements in a vector by a constant
	 * @param vector The vector to be divided
	 * @param constant The constant to divide the vector by
	 * @return A new vector resulting from the given vector being divided by the constant
	 */
	public Vector<Double> divideConstant(Vector<Double> vector, double constant){
		int d = vector.numDimensions();
		Double[] v = new Double[d];
		for(int i = 0; i < d; i++){
			v[i] = vector.getElementAtIndex(i) / constant;
		}
		return new Vector<Double>(v);
	}
	
	/**
	 * Calculates the euclidean distance between two vectors of the same dimension
	 * @param v1 The first vector 
	 * @param v2 The second vector
	 * @return The double positive value of the euclidean distance between the two vectors
	 * @throws VectorDimensionException
	 */
	public double distanceBetweenVectors(Vector<Double> v1, Vector<Double> v2) throws VectorDimensionException{
		int d1 = v1.numDimensions();
		int d2 = v2.numDimensions();
		if(d1 != d2){
			throw new VectorDimensionException(d1, d2);
		} else {
			double sum = 0.0;
			for(int i = 0; i < d1; i++){
				sum = sum + Math.pow(v2.getElementAtIndex(i) - v1.getElementAtIndex(i), 2);
			}
			return Math.sqrt(sum);
		}
	}
	
	public Vector<Double> normalise(Vector<Double> v){
		double length = 0.0;
		for(int i = 0; i < v.numDimensions(); i++){
			length = length + Math.pow(v.getElementAtIndex(i), 2);
		}
		length = Math.sqrt(length);
		
		if(length <= 1){
			return v;
		} else {
			return divideConstant(v, length);
		}
	}
	
}
