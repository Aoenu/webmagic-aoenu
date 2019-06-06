package com.aoenu.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @CreateDate: Created in 2019-05-29 21:35
 * @Author: baoben.wu@hand-china.com
 */
@RestController
public class MamaSpiderController {
    @Autowired
    private MamaSpiderService spiderService;

    @RequestMapping(value = "/spider")
    @ResponseBody
    public String spider() {

        spiderService.startSpider();

        return "spider start!";
    }
}
