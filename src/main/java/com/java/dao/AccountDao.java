package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.entity.Account;

public interface AccountDao extends JpaRepository<Account, String>{
	
	@Query("select DISTINCT ar.account from Authority ar where ar.role.id in ('DIRE', 'STAF')")
	// DISTINCT được sử dụng kết hợp với câu lệnh SELECT để loại bỏ tất cả các bản ghi trùng lặp và chỉ lấy các bản ghi duy nhất trong bảng.
	List<Account> getAdministrators();

}
