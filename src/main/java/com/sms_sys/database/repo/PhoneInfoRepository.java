package com.sms_sys.database.repo;

import com.sms_sys.database.entity.PhoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneInfoRepository extends JpaRepository<PhoneInfo,Long>, PhoneInfoRepositoryCustom {
}
