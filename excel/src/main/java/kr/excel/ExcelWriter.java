package kr.excel;

import kr.excel.model.Member;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelWriter {
    public static void main(String[] args) {
        // 회원 정보 입력
        Scanner scanner = new Scanner(System.in);
        List<Member> members = new ArrayList<>();

        while (true) {
            System.out.print("Input your name.");
            String name = scanner.nextLine();
            if (name.equals("quit")) {
                break;
            }

            System.out.print("Input your age.");
            int age = scanner.nextInt();
            scanner.nextLine(); // delete newline character.

            System.out.print("Input your brithDate.");
            String birthDate = scanner.nextLine();

            System.out.print("Input your phone number.");
            String phone = scanner.nextLine();

            System.out.print("Input your address.");
            String address = scanner.nextLine();

            System.out.print("Check your marital status.");
            boolean isMarried = scanner.nextBoolean();
            scanner.nextLine();

            Member member = new Member(name, age, birthDate, phone, address, isMarried);
            members.add(member);
        }
        scanner.close();

        // 회원 정보 엑셀 저장
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("user info");

            // set header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("name");
            headerRow.createCell(1).setCellValue("age");
            headerRow.createCell(2).setCellValue("birthDate");
            headerRow.createCell(3).setCellValue("phoneNumber");
            headerRow.createCell(4).setCellValue("address");
            headerRow.createCell(5).setCellValue("maritalStatus");

            // create data
            for (int i = 0; i < members.size(); i++) {
                Member member = members.get(i);
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(member.getName());
                row.createCell(1).setCellValue(member.getAge());
                row.createCell(2).setCellValue(member.getBirthDate());
                row.createCell(3).setCellValue(member.getPhone());
                row.createCell(4).setCellValue(member.getAddress());

                Cell marriedCell = row.createCell(5);
                marriedCell.setCellValue(member.getMarried());
            }

            // save file
            String filename = "members.xlsx";
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            workbook.write(outputStream);
            workbook.close();

            System.out.println("Success for writing excel.");
        } catch (IOException e) {
            System.out.println("Error for writing excel.");
            e.printStackTrace();
        }
    }
}
