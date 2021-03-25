package com.neps.groovydatamapper.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ScriptCollectionUtils {
	private ScriptCollectionUtils() {}
	
	public static <T> List<T> mapToList(Class<?> clazz, Map<String, Object> output, String...scriptNamespaces) {
		if (clazz == null || output == null) throw new RuntimeException("Configuration error: Must configure Generic<T> class type and output map to continue");
		List<String> namespaces = (scriptNamespaces == null || scriptNamespaces.length == 0) 
				? output.keySet().stream().collect(Collectors.toList()) 
						: Arrays.asList(scriptNamespaces);
		List<T> tList = namespaces.stream()
				.map(output::get)
				.filter(Map.class::isInstance)
				.map(map -> (Map<String, Object>) map)
				.flatMap(e -> e.values().stream())
				.filter(clazz::isInstance)
				.map(o -> (T) o)
				.collect(Collectors.toList());
		return tList;
	}
	
	public static File writeObject(List<?> list, File file) {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
			list.forEach(o -> {
				try {
					Serializable s = (Serializable) o;
					os.writeObject(s);
				} catch (IOException e) {
					throw new RuntimeException("Unable to writeObject to file: " + file.getAbsolutePath());
				}
			});
		} catch (IOException e) {
			throw new RuntimeException("Unable to writeObject to file:", e);
		}
		
		return file;
	}
	
	public static File writeToCSV(List<?> list, File file) {
		try(FileWriter fileWriter = new FileWriter(file)) {
			Object object = list.get(0);
			
			List<KvTuple> kvTuples = ScriptUtils.readKvTuples(object);
			String header = ScriptUtils.toCSVLine(ScriptUtils.readKeys(kvTuples));
			
			fileWriter.write(header +"\n");
			list.forEach(o -> {
				List<KvTuple> tuples = ScriptUtils.readKvTuples(o);
				String line = ScriptUtils.toCSVLine(ScriptUtils.readValues(tuples));
				
				try {
					fileWriter.write(line +"\n");
				} catch (IOException e) {
					throw new RuntimeException("Unable to writeObject to CSV: ", e);
				}
			});
			
		} catch (IOException e) {
			throw new RuntimeException("Unable to writeObject to CSV: ", e);
		}
		return file;
	}
	
	public static File writeToWorkbook(List<?> list, File file) {
		try(FileOutputStream fos = new FileOutputStream(file); Workbook workbook = new XSSFWorkbook();) {
			Sheet sheet = workbook.createSheet("Test Data");
			
			Font font = sheet.getWorkbook().createFont();
			font.setBold(true);
			font.setFontHeightInPoints((short)13);
			
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(font);
			headerStyle.setWrapText(true);
			headerStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.LEFT);
			cellStyle.setWrapText(true);
			
			font = sheet.getWorkbook().createFont();
			font.setFontHeightInPoints((short)12);
			cellStyle.setFont(font);
			
			Object object = list.get(0);
			List<KvTuple> kvTuples = ScriptUtils.readKvTuples(object);
			
			List<String> headers = ScriptUtils.readKeys(kvTuples);
			headers = headers.stream().sequential().map(String::toUpperCase).collect(Collectors.toCollection(LinkedList::new));
			
			// Create header row
			Row headerRow = sheet.createRow(0);
			createCell(sheet, headerRow, headers, headerStyle, true);
			
			// Create row and cell
			IntStream.range(0, list.size()).boxed().sequential().forEach(r -> {
				Row row = sheet.createRow(r +1); // ++ counted from header row
				
				List<KvTuple> tuples = ScriptUtils.readKvTuples(list.get(r));
				List<Object> values = ScriptUtils.readValues(tuples);
				createCell(sheet, row, values, cellStyle, true);
			});
			workbook.write(fos);
		} catch (Exception e) {
			throw new RuntimeException("Could not create excel for file: " + file.getAbsolutePath(), e);
		}
		return file;
	}
	
	private static void createCell(Sheet sheet, Row row, List<?> values, CellStyle cellStyle, boolean autoResizeCol ) {
		IntStream.range(0,  values.size()).boxed().sequential().forEach(col -> {
			Cell cell = row.createCell(col);
			cell.setCellStyle(cellStyle);
			
			String val = String.valueOf(values.get(col));
			cell.setCellValue(val);
			
			if (autoResizeCol) sheet.autoSizeColumn(col);
		});
	}
	
	public static File getFile(Class<?> clazz, String outputDirectory, String fileName, ExportFileType fileType) {
		outputDirectory = outputDirectory == null ? "output" + File.separator + clazz.getSimpleName() : outputDirectory;
		fileName = getFileName(fileType, fileName);
		
		File file = new File(outputDirectory + File.separator + fileName);
		try {
			if (file.getParentFile() != null && !file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException("Unable to create file: "+ file.getAbsolutePath(), e);
		}
		return file;
	}
	
	private static String getFileName(ExportFileType fileType, String fileName) {
		if (fileName != null) {
			return fileName;
		} else {
			fileName = "input_";
			switch (fileType) {
			case CSV:
			default:
				fileName += new Date().getTime() +".csv";
				break;
			case Excel:
				fileName += new Date().getTime() +".xlsx";
				break;
			case SER:
				fileName += new Date().getTime() +".txt";
				break;
			}
		}
		return fileName;
	}
}
