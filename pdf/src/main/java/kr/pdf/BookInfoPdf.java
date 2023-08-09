package kr.pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Year;
import java.util.HashMap;

public class BookInfoPdf {
    public static void main(String[] args) {
        HashMap<String, String> bookInfo = new HashMap<>();
        bookInfo.put("title", "kr java");
        bookInfo.put("author", "kim");
        bookInfo.put("publisher", "hello");
        bookInfo.put("year", String.valueOf(Year.now().getValue()));
        bookInfo.put("price", "25000");

        try {
            // create PdfWriter object for PDF file.
            PdfWriter writer = new PdfWriter(new FileOutputStream("book_info.pdf"));
            // create PdfDocument object.
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Set font
            PdfFont font = PdfFontFactory.createFont("NanumGothic-Regular.ttf", PdfEncodings.IDENTITY_H, true);
            document.setFont(font);

            for (String key : bookInfo.keySet()) {
                Paragraph paragraph = new Paragraph(key + ": " + bookInfo.get(key));
                document.add(paragraph);
            }
            document.close();
            System.out.println("Success for creating pdf.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
