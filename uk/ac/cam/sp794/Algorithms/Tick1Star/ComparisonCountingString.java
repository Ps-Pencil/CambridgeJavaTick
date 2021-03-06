package uk.ac.cam.sp794.Algorithms.Tick1Star;

public class ComparisonCountingString implements Comparable< ComparisonCountingString > {
		
	// State to track the number of comparisons since reset
	private static int sCompCount=0;
	
	// The String value we are wrapping
	private String mString=null;
	
	// Constructor from normal String
	public ComparisonCountingString(String s) { 	mString=s;}
		
	public static int getComparisonCount() { return sCompCount;}
	
	public static void resetComparisonCount() { sCompCount=0;}
	
	@Override
	public int compareTo(ComparisonCountingString o) {
		// Add to the comparison count
		sCompCount++;
		// Just pass on the comparison
                System.out.println(o.mString+" v "+this.mString);
		return mString.compareTo(o.mString);
	}
	
	// Have a valid equals method too 
	@Override
	public boolean equals(Object o) {
		return mString.equals(((ComparisonCountingString)o).mString);
	}
	
	@Override
	public int hashCode() { return mString.hashCode();}
	
	// Access to underlying String
	public String getString() { return mString; }
		
	// So you can just print a ComparisonCountingString object and get something meaningful
	@Override
	public String toString() {	return getString(); }
}
