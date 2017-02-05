package model.vector;

public class VectorDimensionException extends Exception {

	private static final long serialVersionUID = -6219071411807057212L;

	public VectorDimensionException() {
		super();
	}

	public VectorDimensionException(String message) {
		super(message);
	}
	
	public VectorDimensionException(int dimension1, int dimension2) {
		super("Vector dimensions do not match, vector 1 has a dimension of " + dimension1
				+ " and vector 2 has a dimension of " + dimension2);
	}
}
