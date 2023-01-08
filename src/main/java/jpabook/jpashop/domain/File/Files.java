package jpabook.jpashop.domain.File;

import jpabook.jpashop.domain.item.Item;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Files {

    @Id @GeneratedValue
    int fno;

    String filename;
    String fileOriName;
    String fileurl;

    //파일 이미지
    @OneToOne(mappedBy = "files")
    private Item item;
}
