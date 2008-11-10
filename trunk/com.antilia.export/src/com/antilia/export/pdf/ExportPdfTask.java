/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import com.antilia.common.util.ReflectionUtils;
import com.antilia.hibernate.context.IProgressReporter;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.export.AbstractExportTask;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportPdfTask<E extends Serializable> extends AbstractExportTask {

	
	private IPageableProvider<E> pageableProvider;
		
	private ITableModel<E> tableModel;
	
	
	public ExportPdfTask(IPageableProvider<E> pageableProvider, ITableModel<E> tableModel) {
		super();
		this.pageableProvider = pageableProvider.duplicate();
		this.tableModel = tableModel;
	}
	
	@Override
	protected void doExport(IProgressReporter progressReporter) throws Exception {
		long total = getTotalTasks();
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(getFile()));								
		document.open();
		int columns = tableModel.getColumns();
		float[] widths = new float[columns];
		int index = 0;
		java.util.Iterator<IColumnModel<E>> it = tableModel.getColumnModels();
		while(it.hasNext()) {
			IColumnModel<E> columnModel = it.next();
			widths[index] = columnModel.getWidth();
			index++;
		}
		
		PdfPTable table = new PdfPTable(widths);
		it = tableModel.getColumnModels();
		while(it.hasNext()) {
			IColumnModel<E> columnModel = it.next();
			table.addCell(columnModel.getPropertyPath());
		}
		document.add(table);
		
		
		for(long i=1; i<= total; i++) {
			if(progressReporter != null && progressReporter.isCanceled())
				break;			
			if(progressReporter != null) {
				progressReporter.setCurrentTask(i);			
				progressReporter.setMessage("Exporting record " + i + " of " + total);
			}
			Thread.sleep(10);
			table = new PdfPTable(widths);			
			E bean = pageableProvider.current();
			pageableProvider.next();
			it = tableModel.getColumnModels();
			while(it.hasNext()) {
				IColumnModel<E> columnModel = it.next();
				Object value = ReflectionUtils.getPropertyValue(bean, columnModel.getPropertyPath());
				if(value != null)
					table.addCell(value.toString());
				else 
					table.addCell("");
			}										
			document.add(table);
		}
		document.close();
	}
	
	@Override
	protected File getExportFile() throws Exception {
		return File.createTempFile("export", ".pdf");
	}
	
	@Override
	protected long getTotalTasks() {
		return pageableProvider.size();
	}
	
	@Override
	protected String getIntialMessage() {
		return "Exporting to PDF";
	}
}