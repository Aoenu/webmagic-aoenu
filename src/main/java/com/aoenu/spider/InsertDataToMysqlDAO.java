package com.aoenu.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @CreateDate: Created in 2019-05-29 19:51
 * @Author: baoben.wu@hand-china.com
 */
@Component
public class InsertDataToMysqlDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Integer SIZE = 1000;
    private static final String INSERT_SQL = "INSERT INTO spider ( title, user_name, baby_age, city, reply_and_view, last_active_time) VALUES ( ?, ?, ?, ?, ?, ?)";

    /**
     * 批量插入mysql精选练习进度
     *
     * @param
     */
    public void batchInsert(List<MamaComDto> mamaComDtoInsertList) {
        int size = 0;
        while (size < mamaComDtoInsertList.size()) {
            List<MamaComDto> insertList = mamaComDtoInsertList.stream().skip(size).limit(SIZE).collect(Collectors.toList());
            List<Object[]> args = new ArrayList<>();
            insertList.forEach(mamaComDto -> args.add(new Object[]{
                    mamaComDto.getTitle(),
                    mamaComDto.getUserName(),
                    mamaComDto.getBabyAge(),
                    mamaComDto.getCity(),
                    mamaComDto.getReplyAndView(),
                    mamaComDto.getLastActiveTime()}));
            jdbcTemplate.batchUpdate(INSERT_SQL, args, new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
            size = size + SIZE;
        }
    }


}
