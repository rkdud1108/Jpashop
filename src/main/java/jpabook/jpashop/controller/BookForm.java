package jpabook.jpashop.controller;

import jpabook.jpashop.domain.File.Files;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class BookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
    private MultipartFile files;
}
