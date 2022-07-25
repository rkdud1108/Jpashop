package jpabook.jpashop.domain.File;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Files {

    @Id @GeneratedValue
    int fno;

    String filename;
    String fileOriName;
    String fileurl;
}
