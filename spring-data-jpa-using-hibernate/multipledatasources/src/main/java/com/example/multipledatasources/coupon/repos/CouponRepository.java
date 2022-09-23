package com.example.multipledatasources.coupon.repos;

import com.example.multipledatasources.coupon.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
