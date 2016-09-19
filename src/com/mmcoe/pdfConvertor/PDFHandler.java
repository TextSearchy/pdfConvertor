package com.mmcoe.pdfConvertor;

import java.io.IOException;

import com.mmcoe.pdfConvertor.utils.Convertor;

public class PDFHandler {

	public static void main(String[] args) throws IOException {
		Convertor C = new Convertor();
		C.setFilePath(args[0]);
		C.fileWriter(args[1], C);
	}

}
