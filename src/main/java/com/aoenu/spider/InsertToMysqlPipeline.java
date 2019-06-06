package com.aoenu.spider;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: Created in 2019-05-29 21:09
 * @Author: baoben.wu@hand-china.com
 */
@Service("InsertToMysqlPipeline")
public class InsertToMysqlPipeline implements Pipeline {

    private static int size = 10000;

    private static List<MamaComDto> mamaComDtoList = new ArrayList<>();

    @Autowired
    private InsertDataToMysqlDAO insertDataToMysqlDAO;


    @Override
    public void process(ResultItems resultItems, Task task) {
        // 设置为100  应该为size
        if (mamaComDtoList.size() >= size) {
            insertDataToMysqlDAO.batchInsert(mamaComDtoList);
            mamaComDtoList.clear();
        }

        Map map = resultItems.getAll();
        Object object = map.get(Constant.RESULT_LIST_MAP);
        if (object == null) {
            return;
        }
        List<Map<String, String>> listMap = (List) object;
        if (CollectionUtils.isEmpty(listMap)) {
            return;
        }

        for (Map mapResult : listMap) {
            MamaComDto mamaComDto = new MamaComDto();
            mamaComDto.setTitle(mapResult.get("title").toString());
            mamaComDto.setUserName(mapResult.get("username").toString());
            mamaComDto.setBabyAge(mapResult.get("babyAge").toString());
            mamaComDto.setCity(mapResult.get("city").toString());
            mamaComDto.setReplyAndView(mapResult.get("replyAndView").toString());
            mamaComDto.setLastActiveTime(mapResult.get("lastActiveTime").toString());
            mamaComDtoList.add(mamaComDto);
        }


    }
}
