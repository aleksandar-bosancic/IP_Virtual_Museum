package com.museum.bank.backend.repositories;

import com.museum.bank.backend.model.entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {
    @Modifying
    @Query("update BankAccountEntity b set b.balance=(b.balance-:amount) where b.id= :accountId")
    void updateBalance(@Param("accountId") int id, @Param("amount") double amount);
}
