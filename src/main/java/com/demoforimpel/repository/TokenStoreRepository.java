package com.demoforimpel.repository;

import com.demoforimpel.domain.TokenStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional
@Repository
public interface TokenStoreRepository extends JpaRepository<TokenStore,Long> {
    @Query("SELECT tokenStore FROM TokenStore tokenStore WHERE tokenStore.token=:token")
    TokenStore findByToken(@Param("token") String token);

    @Query("SELECT model FROM TokenStore model WHERE model.user.id=:userId")
    TokenStore findByUserId(@Param("userId") Long userId);


    @Modifying
    @Transactional
    @Query("UPDATE TokenStore tokenStore SET tokenStore.active=false WHERE tokenStore.user.id=:userId AND tokenStore.active=true")
    void deactivateAll(@Param("userId") Long userId);
}
