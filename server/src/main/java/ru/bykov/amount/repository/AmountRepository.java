package ru.bykov.amount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bykov.amount.model.Amount;

@Repository
public interface AmountRepository extends JpaRepository<Amount, Integer> {


    @Query("SELECT a.value from Amount a where a.id = :id")
    Long getAmountById(@Param("id") Integer id);

    @Modifying
    @Query("update Amount a set a.value = (a.value + :value) where a.id = :id")
    void setValueById(@Param("id") Integer id,
                      @Param("value") Long value);
}
