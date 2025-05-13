package com.dk.localshopmvp.service;

import com.dk.localshopmvp.entity.Order;
import com.dk.localshopmvp.entity.OrderItem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * packageName    : com.dk.localshopmvp.service
 * fileName       : ExcelService
 * author         : doungukkim
 * date           : 2025. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 13.        doungukkim       최초 생성
 */
@Service
public class ExcelService {
    public byte[] generateOrderExcel(Order order) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("주문 내역");

        int rowIdx = 0;

        Row header = sheet.createRow(rowIdx++);
        header.createCell(0).setCellValue("주문 번호");
        header.createCell(1).setCellValue(order.getId().toString());

        sheet.createRow(rowIdx++).createCell(0).setCellValue("주문일시: " + order.getOrderTime());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("이름: " + order.getRecipientName());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("전화번호: " + order.getPhone());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("이메일: " + order.getEmail());
        sheet.createRow(rowIdx++).createCell(0).setCellValue("주소: " + order.getAddress());

        rowIdx++;

        Row tableHeader = sheet.createRow(rowIdx++);
        tableHeader.createCell(0).setCellValue("상품명");
        tableHeader.createCell(1).setCellValue("수량");
        tableHeader.createCell(2).setCellValue("단가");
        tableHeader.createCell(3).setCellValue("합계");

        for (OrderItem item : order.getOrderItems()) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(item.getProduct().getName());
            row.createCell(1).setCellValue(item.getQuantity());
            row.createCell(2).setCellValue(item.getPrice().toString());
            row.createCell(3).setCellValue(item.getTotalPrice().toString());
        }

        Row totalRow = sheet.createRow(rowIdx++);
        totalRow.createCell(2).setCellValue("총 결제금액");
        totalRow.createCell(3).setCellValue(order.getTotalPrice().toString());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }


}
