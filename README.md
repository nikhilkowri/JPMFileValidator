# JPMFileValidator
File Validation Program
Requirement to compile and run the program - requires Java 8 and above
The FileNameValidator java program accepts input file and validates the file name.

The file name format should be:
Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv
Where:
Test – hardcoded string prefix
<portfoliocode> - can only be A,B,C
<ddmmyyyy>– is valuation date format dd e.g 07, mm e.g 12, yyyy e.g 1987.
<2digit-sequencenumber> - is 2 digit sequence number

Assumption:
<2digit-sequencenumber> is just considered as two digits as the sequence logic is not mentioned.

How to run the program
Download the file FileNameValidator.java, using command prompt or a terminal go to the location of the file.
For Java 8-10 versions:
Compile the code by running the command "javac FilenameValidator.java".
Run the program by running the command "java FilenameValidator"

For Java 11 and above versions
Run the program by running the command "java FilenameValidator"

The program prints the sample output and prompts the user to enter the File Name. Once user types the filename and types enter key upon completion the file name is validated and the validation message is displayed. Every time you run the program you can validate a file name.

========================Sample Output===================================================================================
File 'Test_A_07121987_10.csv' passed validation.
File 'Test_B_07121987_99.csv' passed validation.
File 'Test_C_07121987_70.csv' passed validation.
File 'Test_A_07121987_10' failed validation. Invalid File format. Expected 'csv' found ''.
File 'Test__07121987_11.csv' failed validation. PortfolioCode should be A/B/C found ''.
File 'Hello_A_07121987_15.csv' failed validation. Prefix for the file should be 'Test' found 'Hello'.
File 'Test_A_07121987_09.txt' failed validation. Invalid File format. Expected 'csv' found 'txt'.
File 'Test.txt' failed validation. File format should be ‘Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv’
File 'Test_B_32121987_39.csv' failed validation. Valuation Date is not a valid date format 'ddmmyyyy'.
File 'Dust_E_02131987_191.csv' failed validation. Prefix for the file should be 'Test' found 'Dust'. PortfolioCode should be A/B/C found 'E'. Valuation Date is not a valid date format 'ddmmyyyy'. Expected '2 digit sequence number' found '191'.
========================================================================================================================


Please enter the File Name :

Future Enhancements Proposed:
Improve program to accept and validate multiple file names.
Along with filename validation check if file exists in the location.
