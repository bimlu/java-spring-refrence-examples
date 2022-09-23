package com.example.multipledatasources;

import com.example.multipledatasources.coupon.entities.Coupon;
import com.example.multipledatasources.coupon.repos.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MultipledatasourcesApplicationTests {

	@Autowired
	CouponRepository couponRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void testCoupanSave() {
		Coupon coupon = new Coupon();
		coupon.setCode("SUPERSALE");
		coupon.setDiscount(new BigDecimal(20));
		coupon.setExpDate("22/22/2022");
		System.out.println(couponRepository.save(coupon));
	}
}
