import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {

	private static final String SEPARATOR = " ";
	
	public static String[] caseList;
	public static String[] caseListOutput;
	public static int caseNumber;
	public static double changes;
	public static double totalChanges;
	public static double iterations;
	
	public static void main(String[] args) {
		try {
			importData("Input2.txt");
			bubbleSort(caseList);
		} catch (IOException e) {
			System.out.println("An error ocurred. Please make sure you have Input2.txt inside the project's folder and you have permission to open the file.");
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}

	public static void importData(String fn) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fn));
		int lineCount = 0;
		
		String line = br.readLine();
		caseNumber = Integer.parseInt(line);
		caseList = new String[caseNumber];
		caseListOutput = new String[caseNumber];
		
		line = br.readLine();
		while(line != null){
			caseList[lineCount] = line;
			lineCount++;
			
			line = br.readLine();
		}
	    br.close();
	}
	
	public static void exportData(String fn) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fn));
		String formatString;
		
		for(int i = 0; i < caseListOutput.length; i++) {
			formatString = caseListOutput[i]
					.replace(",", "")
					.replace("[", "")
					.replace("]", "");
			bw.write(formatString + "\n");
		}
		
		bw.close();
		System.out.println("Data exported to file " + fn);
	}
	
	public static double[] convertStringArrToDoubleArr(String[] stringArray) {
		double[] result = new double[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			result[i] = Double.parseDouble(stringArray[i]);
		}
		return result;
	}

	public static void bubbleSort(String[] caseList) throws NullPointerException, IOException{
		for(int i = 0; i < caseList.length; i++) {
			totalChanges = 0;
			iterations = 0;
			String[] stringCase = caseList[i].split(SEPARATOR);
			double[] doubleCase = convertStringArrToDoubleArr(stringCase);
			for(int j = 1; j < doubleCase.length; j++) {
				changes = 0;
				for (int k = 0; k < doubleCase.length-j; k++) {
					if(doubleCase[k+1] < doubleCase[k]) {
						double temp = doubleCase[k];
						doubleCase[k] = doubleCase[k+1];
						doubleCase[k+1] = temp;
						changes++;
					}
				}
				totalChanges += changes;
				iterations++;
			}
			double averageChanges = totalChanges/iterations;
			//double roundAvgChanges = Math.round(averageChanges*100.0)/100.0;
			DecimalFormat df = new DecimalFormat(".00");
			df.setRoundingMode(RoundingMode.DOWN);
			caseListOutput[i] = df.format(averageChanges) + "-" + Arrays.toString(doubleCase);
		}
		exportData("Output2.txt");
	}
	
}
