package jpabook.jpashop.controller;

import jpabook.jpashop.domain.File.Files;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //상품 등록 화면 포워딩
    @GetMapping(value = "/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    //상품 등록
    @PostMapping(value = "/items/new")
    public String create(BookForm form, @RequestParam MultipartFile files) throws Exception{
        Book book=new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        if(!files.isEmpty()){
            Files file =new Files();
            String sourceFileName = files.getOriginalFilename();
            String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();//파일 확장자
            File destinationFile;
            String destinationFileName;
            String fileUrl = "C:/Users/GY/Desktop/study/jpashop/src/main/resources/st동탄2르파비스아파트atic/images/";

            do{
                destinationFileName = RandomStringUtils.randomAlphabetic(32)+"."+sourceFileNameExtension;
                destinationFile = new File(fileUrl+destinationFileName);
            }while(destinationFile.exists());

            destinationFile.getParentFile().mkdirs();//디렉토리 만들고
            files.transferTo(destinationFile);//파일 저장

            file.setFilename(destinationFileName);
            file.setFileOriName(sourceFileName);
            file.setFileurl(fileUrl);

            book.setFiles(file);
        }

        itemService.saveItem(book);
        return "redirect:/";
    }

    //상품 목록
    @GetMapping(value = "/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    //상품 수정 폼
    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){

        Book item = (Book)itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form",form);
        return "items/updateItemForm";
    }

    //상품 수정
    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form){
        itemService.updateItem(form.getId(),form.getName(),form.getPrice(),form.getStockQuantity());
        return "redirect:/items";
    }

    //고객 상품 리스트
    //상품 목록
    @GetMapping(value = "/customer/items")
    public String customerList(Model model){
        List<Item> items = itemService.findCusItems();
        model.addAttribute("items", items);
        return "items/customerItemList";
    }

}
