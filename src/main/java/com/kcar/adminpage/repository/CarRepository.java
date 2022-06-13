package com.kcar.adminpage.repository;

import com.kcar.adminpage.domain.Car;
import com.kcar.adminpage.domain.OrderCar;
import com.kcar.adminpage.domain.enums.SalesStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepository {

    private final EntityManager em;

    public void save(Car car){
        em.persist(car);
    }

    public Car findOne(Long id){
        return em.find(Car.class, id);
    }

    public List<Car> findAll(){
        return em.createQuery("select c from Car c", Car.class)
                .getResultList();
    }

    public List<Car> findByName(String name){
        return em.createQuery("select c from Car c where c.name =: name", Car.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Car> findAllWithCategoryAndAssessor(){
        return em.createQuery("select c from Car c" +
                                      " left join fetch c.categories t" +
                                      " left join fetch c.assessor a", Car.class)
                .getResultList();
    }

    public List<Car> findBySalesStatus(SalesStatus salesStatus){
        return em.createQuery("select c from Car c where c.salesStatus =: salesStatus", Car.class)
                .setParameter("salesStatus", salesStatus)
                .getResultList();
    }

    public List<Car> findOrderByDate(){
        return em.createQuery("SELECT c FROM Car c ORDER BY c.registrationDate DESC", Car.class)
                .setFirstResult(0)
                .setMaxResults(4)
                .getResultList();
    }

    public void delete(Car car) {
        em.remove(car);
    }
}
