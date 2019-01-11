package com.haco.mobile_service.DAO;

import com.haco.mobile_service.entity.Usage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface HstUsageMapper {
    ArrayList<Usage> getHstUsage(String userID);          //获取指定指定用户历史用量

    ArrayList<Usage> getHstUsageLimited(@Param("userID") String userID, @Param("beg") Date beg, @Param("end") Date end);      //获取指定指定用户某段时间历史用量

    void addHstUsage(Usage usage);

    void updateHstUsage(Usage usage);

    void deleteHstUsagesByUser(String userID);                  //删除指定用户历史用量

    void deleteHstUsagesByDate(Date deadline);                   //删除ddl前的历史用量记录(ddl以开始日期为准)
}
