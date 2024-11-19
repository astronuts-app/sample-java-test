package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDFOCRProcessor {
    private final Tesseract tesseract;

    public PDFOCRProcessor(String tessDataPath) {
        this.tesseract = new Tesseract();
        this.tesseract.setDatapath(tessDataPath); // Path to Tesseract's tessdata folder
    }

    public String extractTextFromPDF(String pdfPath) throws IOException, TesseractException {
        File file = new File(pdfPath);
        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        StringBuilder result = new StringBuilder();
        int pageCount = document.getNumberOfPages();

        System.out.println("Processing " + pageCount + " pages...");
        for (int page = 0; page < pageCount; page++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300); // Render at 300 DPI for better OCR accuracy
            String pageText = tesseract.doOCR(image);
            result.append(pageText).append("\n");
        }

        document.close();
        return result.toString();
    }

    public void saveTextToFile(String text, String outputPath) throws IOException {
        FileWriter writer = new FileWriter(outputPath);
        writer.write(text);
        writer.close();
        System.out.println("Text saved to: " + outputPath);
    }
}
