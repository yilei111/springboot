package com.springboot.activiti.eimm.activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
@RestController  
@RequestMapping("/test")  
public interface ActivityConsumerService {  
    /** 
     * 流程demo 
     * @return 
     */  
    @RequestMapping(value="/activitiDemo",method=RequestMethod.GET)  
    public boolean startActivityDemo();  
      
}  