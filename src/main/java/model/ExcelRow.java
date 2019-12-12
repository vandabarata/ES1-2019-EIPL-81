package main.java.model;

/**
 * Model which parses the content taken from an excel row to attributes we can
 * use
 * 
 */
public class ExcelRow {

	/** Method ID, located in the first column */
	private int id;

	/** Package name, located in the second column */
	private String packageName;

	/** Class name, located in the third column */
	private String className;

	/** Method name, located in the fourth column */
	private String methodName;

	/** LOC metric, located in the fifth column */
	private int LOC;

	/** CYCLO metric, located in the sixth column */
	private int CYCLO;

	/** ATFD metric, located in the seventh column */
	private int ATFD;

	/** LAA metric, located in the eighth column */
	private float LAA;

	/** boolean isLongMethod, located in the ninth column */
	private boolean isLongMethod;

	/** boolean to indicate the iPlasma tool result, located in the tenth column */
	private boolean iPlasma;

	/** boolean to indicate the PMD tool result, located in the eleventh column */
	private boolean PMD;

	/** boolean isFeatureEnvy, located in the twelfth column */
	private boolean isFeatureEnvy;

	/**
	 * Creates an ExcelRow parsing the given column values to usable attributes
	 * 
	 * @param rowData - unformatted row's data
	 */
	public ExcelRow(String[] rowData) {
		this.id = Integer.parseInt(rowData[0]);
		this.packageName = rowData[1];
		this.className = rowData[2];
		this.methodName = rowData[3];
		this.LOC = Integer.parseInt(rowData[4]);
		this.CYCLO = Integer.parseInt(rowData[5]);
		this.ATFD = Integer.parseInt(rowData[6]);
		this.LAA = Float.parseFloat(rowData[7]);
		this.isLongMethod = Boolean.parseBoolean(rowData[8]);
		this.iPlasma = Boolean.parseBoolean(rowData[9]);
		this.PMD = Boolean.parseBoolean(rowData[10]);
		this.isFeatureEnvy = Boolean.parseBoolean(rowData[11]);
	}

	/**
	 * Returns the row's MethodID
	 * 
	 * @return int id - MethodID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns Package name
	 * 
	 * @return String packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Returns Class name
	 * 
	 * @return String className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Returns Method name
	 * 
	 * @return String methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Returns LOC value
	 * 
	 * @return int LOC
	 */
	public int getLOC() {
		return LOC;
	}

	/**
	 * Returns CYCLO value
	 * 
	 * @return int CYCLO
	 */
	public int getCYCLO() {
		return CYCLO;
	}

	/**
	 * Returns ATFD value
	 * 
	 * @return int ATFD
	 */
	public int getATFD() {
		return ATFD;
	}

	/**
	 * Returns LAA value
	 * 
	 * @return float LAA
	 */
	public float getLAA() {
		return LAA;
	}

	/**
	 * Returns isLongMethod boolean result
	 * 
	 * @return boolean isLongMethod
	 */
	public boolean isLongMethod() {
		return isLongMethod;
	}

	/**
	 * Returns iPlasma tool's result
	 * 
	 * @return boolean iPlasma
	 */
	public boolean getIPlasmaResult() {
		return iPlasma;
	}

	/**
	 * Returns PMD tool's result
	 * 
	 * @return boolean PMD
	 */
	public boolean getPMDResult() {
		return PMD;
	}

	/**
	 * Returns isFeatureEnvy boolean result
	 * 
	 * @return boolean isFeatureEnvy
	 */
	public boolean isFeatureEnvy() {
		return isFeatureEnvy;
	}

	/**
	 * String value representing this excel row and its attributes accompanied by the
	 * attribute's name, ordered by column number
	 * 
	 * @return String ExcelRow's attirbutes
	 */
	@Override
	public String toString() {
		return "ID: " + id + "\nPackage: " + packageName + "\nClass: " + className + "\nMethod: " + methodName
				+ "\nLOC: " + LOC + "\nCYCLO: " + CYCLO + "\nATFD: " + ATFD + "\nLAA: " + LAA + "\nIs Long Method: "
				+ isLongMethod + "\niPlasma: " + iPlasma + "\nPMD: " + PMD + "\nIs Feature Envy: " + isFeatureEnvy;
	}

}
