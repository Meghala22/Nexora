package com.nexora.operations.repository;

import com.nexora.operations.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> { Optional<UserAccount> findByEmailIgnoreCase(String email); }
