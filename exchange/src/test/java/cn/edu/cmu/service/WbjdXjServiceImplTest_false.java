package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.WbjdXj;
import cn.edu.cmu.domain.User;
import cn.edu.cmu.framework.utils.CmuStringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Service��Ĳ�����
 *
 * ����ɾ�Ĳ�
 */
public class WbjdXjServiceImplTest_false extends SpringIOC {

    WbjdXjService service;
    @Before
    public  void init(){
        service = ac.getBean(WbjdXjService.class);
    }
    //����
    @Test
    public void save() {
        WbjdXj wbjdXj = new WbjdXj(CmuStringUtil.UUID(),"01","01", BigDecimal.valueOf(1.34),"01",new  Date(),new  Date(), "01",
                "01","01","01","01", "01",BigDecimal.valueOf(1.34), "01","01","01","01",new  Date(), "01","01",
                "01","01","01","01","01","01","01","01","01","01","01", null,null);
        boolean success = service.insert(wbjdXj);
        System.out.println("WbjdXj:"+wbjdXj);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "e6a186552a5a4707a3d80d98e2d5f50b";
        WbjdXj wbjdXj = service.queryById(keyId);
        System.out.println(wbjdXj);
        Assert.assertTrue(wbjdXj!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        WbjdXj wbjdXjParam = new WbjdXj();
        wbjdXjParam.setDbtmc("0");
        List<WbjdXj> wbjdXjs = service.list(wbjdXjParam);
        for (WbjdXj wbjdXj : wbjdXjs ) {
            System.out.println(wbjdXj);
        }
        Assert.assertTrue(wbjdXjs.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "e6a186552a5a4707a3d80d98e2d5f50b";
        WbjdXj wbjdXj = service.queryById(keyId);
        System.out.println("����ǰ:"+wbjdXj);
        wbjdXj.setDbtmc("02");
        boolean success = service.updateById(wbjdXj);
        System.out.println("success:"+success);
        WbjdXj newWbjdXj = service.queryById(keyId);
        System.out.println("���º�:"+newWbjdXj);
        Assert.assertTrue(newWbjdXj.getDbtmc().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        WbjdXj wbjdXj = new WbjdXj(keyId,"01","01", BigDecimal.valueOf(1.34),"01",new  Date(),new  Date(), "01",
                "01","01","01","01", "01",BigDecimal.valueOf(1.34), "01","01","01","01",new  Date(), "01","01",
                "01","01","01","01","01","01","01","01","01","01","01", null,null);
        boolean success = service.insert(wbjdXj);
        System.out.println("����ɹ���"+success);
        WbjdXj queryWbjdXj = service.queryById(keyId);
        System.out.println("������ѯ"+queryWbjdXj);
        queryWbjdXj.setValid("0");
        queryWbjdXj.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryWbjdXj);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}