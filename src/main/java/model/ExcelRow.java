package main.java.model;

/**
 * Represents an Excel Row model
 * 
 * @author Lino Silva
 *
 */
public class ExcelRow {

	private int id;
	private String packageName;
	private String className;
	private String methodName;
	private int LOC;
	private int CYCLO;
	private int ATFD;
	private float LAA;
	private boolean is_long_method;
	private boolean iPlasma;
	private boolean PMD;
	private boolean is_feature_envy;

	/**
	 * Create and ExcelRow
	 * 
	 * @param unformatted row's data
	 * @return row's is_feature_envy value
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
		this.is_long_method = Boolean.parseBoolean(rowData[8]);
		this.iPlasma = Boolean.parseBoolean(rowData[9]);
		this.PMD = Boolean.parseBoolean(rowData[10]);
		this.is_feature_envy = Boolean.parseBoolean(rowData[11]);
	}

	/**
	 * 
	 * @return row's id value
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return row's package name value
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * 
	 * @return row's class name value
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * 
	 * @return row's method name value
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * 
	 * @return row's LOC value
	 */
	public int getLOC() {
		return LOC;
	}

	/**
	 * 
	 * @return row's CYCLO value
	 */
	public int getCYCLO() {
		return CYCLO;
	}

	/**
	 * 
	 * @return row's ATFD value
	 */
	public int getATFD() {
		return ATFD;
	}

	/**
	 * 
	 * @return row's LAA value
	 */
	public float getLAA() {
		return LAA;
	}

	/**
	 * 
	 * @return row's is_long_method value
	 */
	public boolean isIs_long_method() {
		return is_long_method;
	}

	/**
	 * 
	 * @return row's iPlasma value
	 */
	public boolean isiPlasma() {
		return iPlasma;
	}

	/**
	 * 
	 * @return row's PMD value
	 */
	public boolean isPMD() {
		return PMD;
	}

	/**
	 * 
	 * @return row's is_feature_envy value
	 */
	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nPackage: " + packageName + "\nClass: " + className + "\nMethod: " + methodName
				+ "\nLOC: " + LOC + "\nCYCLO: " + CYCLO + "\nATFD: " + ATFD + "\nLAA: " + LAA + "\nIs Long Method: "
				+ is_long_method + "\niPlasma: " + iPlasma + "\nPMD: " + PMD + "\nIs Feature Envy: " + is_feature_envy;
	}

}
