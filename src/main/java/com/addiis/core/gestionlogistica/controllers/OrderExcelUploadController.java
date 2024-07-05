package com.addiis.core.gestionlogistica.controllers;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.exceptions.InvalidDataException;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderProduct;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderProductId;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderProductRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.ProductRepository;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.StoreRepository;
import com.addiis.core.gestionlogistica.response.ApiResponse;

import org.springframework.transaction.annotation.Transactional;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/orders/excel")
public class OrderExcelUploadController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    @PostMapping("/upload")
    @Transactional
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid file.");
        }

        int insertedProductsCount = 0;

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            AddiisLogger.info("eeeeeeeeeeeey" + sheet.getLastRowNum());
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // Verificar si la fila tiene datos significativos
                if (isRowEmpty(row)) {
                    continue; // Saltar filas vacías y continuar con la siguiente fila
                }

                // Leer datos de la fila
                BigInteger ean = getBigIntegerCellValue(row.getCell(0));
                Integer storeCode = getIntegerCellValue(row.getCell(1));
                BigInteger orderNumber = getBigIntegerCellValue(row.getCell(5));
                BigInteger detra = getBigIntegerCellValue(row.getCell(6));
                BigInteger quantity = getBigIntegerCellValue(row.getCell(8));

                AddiisLogger.info("EAN: " + ean + ", Store Code: " + storeCode + ", Order Number: " + orderNumber
                        + ", Detra: " + detra + ", Quantity: " + quantity + "fila: " + i);
                if (ean == null || storeCode == null || orderNumber == null || detra == null || quantity == null) {
                    String errorMessage = "Error reading row " + i + ": Missing data. EAN: " + ean + ", Store Code: "
                            + storeCode + ", Order Number: " + orderNumber + ", Detra: " + detra + ", Quantity: "
                            + quantity;
                    AddiisLogger.warn(errorMessage);
                    throw new InvalidDataException(errorMessage);
                }

                // Validar y buscar entidades
                Optional<Product> productOptional = productRepository.findByEan(ean);
                Optional<Store> storeOptional = storeRepository.findByCode(storeCode);
                AddiisLogger.info("Product: " + productOptional + ", Store: " + storeOptional);

                if (productOptional.isPresent() && storeOptional.isPresent()) {
                    Product product = productOptional.get();
                    Store store = storeOptional.get();

                    // Crear o actualizar Order
                    Order order = orderRepository.findByOrderNumber(orderNumber);
                    if (order == null) {
                        order = new Order();
                        order.setOrderNumber(orderNumber);
                        order.setDetra(detra);
                        order.setStore(store);
                        order.setDate(new Date()); // Usar la fecha actual
                        orderRepository.save(order);
                    }

                    // Crear OrderProduct
                    OrderProduct orderProduct = new OrderProduct();
                    OrderProductId orderProductId = new OrderProductId(order.getId(), product.getId());
                    orderProduct.setId(orderProductId);
                    orderProduct.setOrder(order);
                    orderProduct.setProduct(product);
                    orderProduct.setQuantity(quantity);
                    orderProductRepository.save(orderProduct);

                    insertedProductsCount++; // Incrementar contador de productos insertados
                }
            }

            return ApiResponse.ok(insertedProductsCount + " products inserted");
        } catch (IOException e) {
            return ApiResponse.error("Error reading file: " + e.getMessage());
        }
    }

    private BigInteger getBigIntegerCellValue(Cell cell) {
        if (cell == null)
            return null;
        switch (cell.getCellType()) {
            case NUMERIC:
                return BigInteger.valueOf((long) cell.getNumericCellValue());
            case STRING:
                return new BigInteger(cell.getStringCellValue());
            default:
                return null;
        }
    }

    private Integer getIntegerCellValue(Cell cell) {
        if (cell == null)
            return null;
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                return Integer.valueOf(cell.getStringCellValue());
            default:
                return null;
        }
    }
}
