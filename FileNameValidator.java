import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * FileNameValidator - class to validate file name
 */
public class FileNameValidator {
	
	public static final String UNDERSCORE = "_";
	public static final String CSV = ".csv";
	public static final String TEST_ = "Test_";
	public static final String ABC_MATCHER = "[ABC]";
	public static final String DATE_FORMAT = "ddMMyyyy";
	public static final String DIGITS_REGEX = "\\d+";
	public static final String NULL_ERROR_MSG = "File Name input is null";
	
	public static void main(String[] args) {
		System.out.println("======================================================================Sample Output===================================================================================");
		FileNameValidator fileNameValidator = new FileNameValidator();
		fileNameValidator.validateSampleFileNames();
		System.out.println("======================================================================================================================================================================");
		
		Scanner scanner = new Scanner(System.in);

		// File name input prompt
		System.out.println();
		System.out.println();
		System.out.print("Please enter the File Name : ");
		String fileNameInput = scanner.nextLine();

		if (fileNameInput == null) {
			System.out.println(NULL_ERROR_MSG);
			return;
		}

		scanner.close();

		fileNameValidator.validateFileName(fileNameInput);
	}

	/**
	 * Takes file name as input and validates if it conforms to format
	 * Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv
	 * @param fileNameInput
	 */
	public void validateFileName(String fileNameInput) {
		if(fileNameInput!=null) {
			// Validate the file name
			File file = new File(fileNameInput);
			String fileName = file.getName();
			String[] fileNameParts = fileName.split(UNDERSCORE);
			
			StringBuffer errors = new StringBuffer();
			
			String validationFailedError = new StringBuffer("File '").append(fileName).append("' failed validation.").toString();

			if (fileNameParts.length != 4) {
				System.out.print(validationFailedError);
				System.out.println(" File format should be ‘Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv’");
				return;
			}

			if (!fileName.endsWith(CSV)) {
				System.out.println(new StringBuffer(validationFailedError)
						.append(" Invalid File format. Expected 'csv' found '")
						.append(fileName.split("\\.").length==1?"":fileName.split("\\.")[1])
						.append("'."));
				return;
			}

			if (!fileName.startsWith(TEST_)) {
				errors.append(" Prefix for the file should be 'Test' found '")
				.append(fileName.split("_")[0])
				.append("'.");
			}

			String portfolioCode = fileNameParts[1];
			String valuationDateStr = fileNameParts[2];
			String sequenceNumber = fileNameParts[3].substring(0,fileNameParts[3].indexOf("."));

			Predicate<String> portfolioCodeABC = pc -> !pc.matches(ABC_MATCHER);
			if (portfolioCodeABC.test(portfolioCode)) {
				errors.append(" PortfolioCode should be A/B/C found '")
				.append(portfolioCode)
				.append("'.");
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			try {
				formatter.parse(valuationDateStr);
			} catch (DateTimeParseException e) {
				errors.append(" Valuation Date is not a valid date format 'ddmmyyyy'.");
			}

			Predicate<String> twoDigitSequence = dS -> (dS.length()!=2 || !dS.matches(DIGITS_REGEX));
			if(twoDigitSequence.test(sequenceNumber)) {
				errors.append(" Expected '2 digit sequence number' found '")
				.append(sequenceNumber)
				.append("'.");
			}

			if(errors.length()>0) {
				System.out.println(new StringBuffer(validationFailedError).append(errors));
				return;
			}

			System.out.println("File '" + fileName + "' passed validation.");
		} else {
			System.out.println(NULL_ERROR_MSG);
		}
	}

	/**
	 * validates some sample filenames
	 */
	public void validateSampleFileNames() {
		validateFileName("Test_A_07121987_10.csv");
		validateFileName("Test_B_07121987_99.csv");
		validateFileName("Test_C_07121987_70.csv");
		validateFileName("Test_A_07121987_10");
		validateFileName("Test__07121987_11.csv");
		validateFileName("Hello_A_07121987_15.csv");
		validateFileName("Test_A_07121987_09.txt");
		validateFileName("Test.txt");
		validateFileName("Test_B_32121987_39.csv");
		validateFileName("Dust_E_02131987_191.csv");
	}

}