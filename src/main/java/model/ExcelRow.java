package main.java.model;

import java.util.ArrayList;

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
	
	public ExcelRow(ArrayList<String> rowData) {
		this.id = Integer.parseInt(rowData.get(0));
		this.packageName = rowData.get(1);
		this.className = rowData.get(2);
		this.methodName = rowData.get(3);
		this.LOC = Integer.parseInt(rowData.get(4));
		this.CYCLO = Integer.parseInt(rowData.get(5));
		this.ATFD = Integer.parseInt(rowData.get(6));
		this.LAA = Float.parseFloat(rowData.get(7));
		this.is_long_method = Boolean.parseBoolean(rowData.get(8));
		this.iPlasma = Boolean.parseBoolean(rowData.get(9));
		this.PMD = Boolean.parseBoolean(rowData.get(10));
		this.is_feature_envy = Boolean.parseBoolean(rowData.get(11));
	}
	
	public int getId() {
		return id;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public int getLOC() {
		return LOC;
	}
	public int getCYCLO() {
		return CYCLO;
	}
	
	public int getATFD() {
		return ATFD;
	}
	
	public float getLAA() {
		return LAA;
	}
	
	public boolean isIs_long_method() {
		return is_long_method;
	}
	
	public boolean isiPlasma() {
		return iPlasma;
	}
	
	public boolean isPMD() {
		return PMD;
	}
	
	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}
	
	@Override
	public String toString() {
		return 	"ID: " + id + 
				"\nPackage: " + packageName +
				"\nClass: " + className +
				"\nMethod: " + methodName +
				"\nLOC: " + LOC +
				"\nCYCLO: " + CYCLO +
				"\nATFD: " + ATFD +
				"\nLAA: " + LAA +
				"\nIs Long Method: " + is_long_method +
				"\niPlasma: " + iPlasma +
				"\nPMD: " + PMD +
				"\nIs Feature Envy: " + is_feature_envy;
	}
	
}
