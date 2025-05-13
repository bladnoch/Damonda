package com.dk.localshopmvp.service;

import com.dk.localshopmvp.dto.ProductCreateForm;
import com.dk.localshopmvp.entity.Product;
import com.dk.localshopmvp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * packageName    : com.dk.localshopmvp.service
 * fileName       : ProductService
 * author         : doungukkim
 * date           : 2025. 5. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 7.        doungukkim       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void register(ProductCreateForm form, MultipartFile imageFile) throws IOException {
        // 1. 실제 저장 경로 (절대 경로로 설정)
        String uploadDirPath = System.getProperty("user.dir") + File.separator + "uploads"; // 예: /Users/you/project/uploads

        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 2. 파일명 생성
        String originalFilename = imageFile.getOriginalFilename();
        String filename = UUID.randomUUID() + "_" + originalFilename;

        // 3. 파일 저장
        File destinationFile = new File(uploadDir, filename);
        imageFile.transferTo(destinationFile);

        // 4. DB에 저장될 경로 (브라우저 접근용)
        String imagePath = "/uploads/" + filename;

        // 5. DB 저장
        Product product = new Product();
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        product.setStockQuantity(form.getStockQuantity());
        product.setImagePath(imagePath);

        productRepository.save(product);
    }





    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Product findById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("해당 상품 없음"));
    }

}
