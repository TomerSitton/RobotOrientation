package kalmanFilter.errorHandler;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

/**
 * this class is meant to find the constant errors of a set of devices, usually
 * sensors, by analyzing their measurements in a constant state.
 * 
 * @author noam mantin
 */
public class ConstantErrorsFinder {

	// the sum of the measurement vectors
	private RealVector resultSum;

	// the number of measurement, set to 0
	private int measurementNumber = 0;

	/**
	 * creates a new {@link ConstantErrorsFinder} object, with a default result sum
	 * (0 vector)
	 * 
	 * @param size
	 */
	public ConstantErrorsFinder(int size) {
		this.resultSum = new ArrayRealVector(size);
	}

	/**
	 * adds a received measurement vector to the statistics.
	 * 
	 * @param measurement
	 *            the measured vector
	 */
	public void addMeasurement(RealVector measurement) {
		// adding the new measurement
		resultSum.add(measurement);
		// updating the measurement number
		measurementNumber++;
	}

	/**
	 * returns a vector, which contains the average bias of the data suppliers,
	 * according to a received expected vector *
	 * 
	 * @param expected
	 *            the data which should be measured
	 * @return the average difference between the measurements to the expected
	 *         vector
	 */
	public RealVector getErrorVector(RealVector expected) {

		RealVector errorAvg = resultSum.mapDivide(measurementNumber).subtract(expected);

		return errorAvg;
	}
}