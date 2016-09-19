package com.mmcoe.pdfConvertor.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Convertor {
	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	private String Text;
	private String filePath;
	private File file;

	public String ToText() throws IOException {
		this.pdfStripper = null;
		this.pdDoc = null;
		this.cosDoc = null;

		file = new File(filePath);
		parser = new PDFParser(new RandomAccessFile(file, "r")); // update for
																	// PDFBox V
																	// 2.0

		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdDoc.getNumberOfPages();
		pdfStripper.setStartPage(1);
		pdfStripper.setEndPage(10);

		// reading text from page 1 to 10
		// if you want to get text from full pdf file use this code
		// pdfStripper.setEndPage(pdDoc.getNumberOfPages());

		Text = pdfStripper.getText(pdDoc);
		return Text;
	}

	public void setFilePath(String string) {
		// TODO Auto-generated method stub
		System.out.println("Converting file: " + string);
		this.filePath = string;
	}

	public void fileWriter(String path, Convertor pdfText) {
		try {
			// Use FileWriter For now
			FileWriter out = new FileWriter(path);
			BufferedWriter buffer = new BufferedWriter(out);
			buffer.write(pdfText.ToText());
			// Close This Shit
			buffer.close();

		} catch (IOException e) {
			// Write Nicely.
			System.out.println("Cannot Read File \"" + path
					+ "\", Broken or does not exist.\n\n");
			e.printStackTrace();

		}

	}
}
