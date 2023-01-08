package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId()== null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public List<Item> findCusAll(){
        return em.createQuery("select i from Item i inner join i.files f " +
                "where i.files.fno = f.fno",Item.class).getResultList();
    }

    public List<Item> findAllforCustomer(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

}
