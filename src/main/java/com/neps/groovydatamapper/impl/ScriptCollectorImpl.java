package com.neps.groovydatamapper.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.neps.groovydatamapper.ScriptCollector;
import com.neps.groovydatamapper.utils.ExportFileType;
import com.neps.groovydatamapper.utils.ScriptCollectionUtils;

public final class ScriptCollectorImpl implements ScriptCollector {

	private Class<?> clazz;
	private String[] namespaces;
	private ExportFileType fileType;
	private String outputDirectory, fileName;
	private Map<String, Object> output;
	
	private ScriptCollectorImpl() {}
	
	public ScriptCollectorImpl(Class<?> clz, String...namespaces) {
		this.clazz = clz;
		this.namespaces = namespaces;
	}
	
	public ScriptCollectorImpl(ExportFileType fileType, Class<?> clz, String fileName) {
		this.fileType = fileType;
		this.clazz = clz;
		this.fileName = fileName;
	}
	
	@Override
	public File toFile() {
		File file = ScriptCollectionUtils.getFile(this.clazz, this.outputDirectory, this.fileName, this.fileType);
		switch (this.fileType) {
		case SER:
		default:
			if (this.isFileSupported(file, "txt") || this.isFileSupported(file, "ser")) {
				file = ScriptCollectionUtils.writeObject(ScriptCollectionUtils.mapToList(this.clazz, this.output, this.namespaces), file);
			}
			break;
		case CSV:
			if (this.isFileSupported(file, "csv")) {
				file = ScriptCollectionUtils.writeToCSV(ScriptCollectionUtils.mapToList(this.clazz, this.output, this.namespaces), file);
			}
			break;
		case Excel:
			if (this.isFileSupported(file, "xlsx")) {
				file = ScriptCollectionUtils.writeToWorkbook(ScriptCollectionUtils.mapToList(this.clazz, this.output, this.namespaces), file);
			}
			break;
		}
		return file;
	}

	@Override
	public <T> List<T> toList() {
		return ScriptCollectionUtils.mapToList(this.clazz, this.output, this.namespaces);
	}

	@Override
	public ScriptCollector map(Map<String, Object> output) {
		this.output = output;
		return this;
	}

	@Override
	public ScriptCollector withOutputDirectory(String directory) {
		this.outputDirectory = directory;
		return this;
	}

	private boolean isFileSupported(File file, String extension) {
		if (!FilenameUtils.getExtension(file.getName()).equals(extension)) {
			if (file.exists()) file.delete();
			throw new RuntimeException("Unsupported file format: " + file.getName());
		}
		return true;
	}
}
