package com.readingisgood.ordermanagement.repository;

import com.readingisgood.ordermanagement.adapter.StatisticsDto;
import com.readingisgood.ordermanagement.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Order> findById(String id) {
        Order order = entityManager.find(Order.class, id);
        return Optional.of(order);
    }

    @Override
    public List<Order> listByCustomerId(String customerId, int pageIndex, int pageSize) {
        TypedQuery<Order> query = entityManager.createQuery("from tOrder Where customerId=:customerId", Order.class)
                .setFirstResult(pageIndex * pageSize)
                .setMaxResults(pageSize)
                .setParameter("customerId", customerId);

        return query.getResultList();
    }

    @Override
    public void create(Order order) {
        entityManager.persist(order);
    }

    @Override
    public List<Order> listByDate(Date from, Date to) {
        TypedQuery<Order> query = entityManager.createQuery("from tOrder Where createDate between :from and :to", Order.class)
                .setParameter("from", from)
                .setParameter("to", to);
        return query.getResultList();
    }

    @Override
    public List<StatisticsDto> statisticByCustomerId(String customerId) {
        Query query = entityManager.createNativeQuery("SELECT X.monthName, COUNT(id) orderCount, SUM(count) bookCount, SUM(Amount) amount\n" +
                "FROM (\n" +
                "\tSELECT monthname(createDate) monthName, id, count, amount  \n" +
                "\tFROM tOrder \n" +
                "\tWHERE customerId = :customerId \n" +
                "    ) X\n" +
                "group by X.monthName")
                .setParameter("customerId", customerId);
        List<Object[]> rows = query.getResultList();
        return rows.stream().map(x -> new StatisticsDto(x[0].toString(), Integer.valueOf(x[1].toString()), Integer.valueOf(x[2].toString()), new BigDecimal(x[3].toString())))
                            .collect(Collectors.toList());
    }
}
