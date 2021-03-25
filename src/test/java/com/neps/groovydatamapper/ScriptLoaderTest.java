package com.neps.groovydatamapper;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.neps.groovydatamapper.impl.ScriptCollectors;
import com.neps.groovydatamapper.impl.ScriptLoaderImpl;
import com.neps.groovydatamapper.model.WebOrder;
import com.neps.groovydatamapper.utils.ExportFileType;

public class ScriptLoaderTest {
	private ScriptLoader scriptLoader = new ScriptLoaderImpl();
	
	@Test
	public void testWebOrderList() {
		List<WebOrder> data = this.scriptLoader
		.withScriptDirectory("test-data")
		.load(Script.with("test.data.weborder.groovy", "weborder_test"))
		.toList(ScriptCollectors.toList(WebOrder.class));
		
		Assertions.assertNotNull(data);
	}
	
	@Test
	public void testWebOrderFile() {
		File file = this.scriptLoader.withScriptDirectory("test-data")
		.load(Script.with("test.data.weborder.groovy", "weborder_test1"))
		.toFile(ScriptCollectors.toFile(ExportFileType.Excel, WebOrder.class));
		Assertions.assertNotNull(file);
	}
}
