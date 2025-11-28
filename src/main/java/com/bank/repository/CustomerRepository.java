package com.bank.repository;

import com.bank.domain.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findByCustomerName(String name);

    @Query("SELECT c FROM Customer c ORDER BY " +
            "CASE " +
            " WHEN FUNCTION('MONTH', c.birthDate) > FUNCTION('MONTH', CURRENT_DATE) THEN 0 " +
            " WHEN FUNCTION('MONTH', c.birthDate) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('DAY', c.birthDate) >= FUNCTION('DAY', CURRENT_DATE) THEN 0 " +
            " ELSE 1 " +
            "END, " +
            "FUNCTION('MONTH', c.birthDate), FUNCTION('DAY', c.birthDate)")
    List<Customer> findUpcomingBirthday(Pageable pageable);
}
