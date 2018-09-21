package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.WbjdSq;
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
public class WbjdSqServiceImplTest extends SpringIOC {

    WbjdSqService service;

    @Before
    public  void init(){
        service = ac.getBean(WbjdSqService.class);
    }
    //����
    @Test
    public void save() {
        WbjdSq wbjdSq = new WbjdSq(CmuStringUtil.UUID(), "01", BigDecimal.valueOf(1.34),"01",new  Date(),new  Date(), "01", "01","01","01","01","01",
                BigDecimal.valueOf(1.34), "01","01","01","01",new  Date(),"01","01","01","01","01","01","01","01","01","01","01" ,"01","01", null,null);
        boolean success = service.insert(wbjdSq);
        System.out.println("WbjdSq:"+wbjdSq);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "eb036d5ea1394154bddbc27aa5b34900";
        WbjdSq wbjdSq = service.queryById(keyId);
        System.out.println(wbjdSq);
        Assert.assertTrue(wbjdSq!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        WbjdSq wbjdSqParam = new WbjdSq();
        wbjdSqParam.setDbtmc("0");
        List<WbjdSq> wbjdSqs = service.list(wbjdSqParam);
        for (WbjdSq wbjdSq : wbjdSqs ) {
            System.out.println(wbjdSq);
        }
        Assert.assertTrue(wbjdSqs.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "eb036d5ea1394154bddbc27aa5b34900";
        WbjdSq wbjdSq = service.queryById(keyId);
        System.out.println("����ǰ:"+wbjdSq);
        wbjdSq.setDbtmc("02");
        boolean success = service.updateById(wbjdSq);
        System.out.println("success:"+success);
        WbjdSq newWbjdSq = service.queryById(keyId);
        System.out.println("���º�:"+newWbjdSq);
        Assert.assertTrue(newWbjdSq.getDbtmc().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        WbjdSq wbjdSq = new WbjdSq(keyId , "01", BigDecimal.valueOf(1.34),"01",new  Date(),new  Date(), "01", "01","01","01","01","01",
                BigDecimal.valueOf(1.34), "01","01","01","01",new  Date(),"01","01","01","01","01","01","01","01","01","01","01" ,"01","01", null,null);
        boolean success = service.insert(wbjdSq);
        System.out.println("����ɹ���"+success);
        WbjdSq queryWbjdSq = service.queryById(keyId);
        System.out.println("������ѯ"+queryWbjdSq);
        queryWbjdSq.setValid("0");
        queryWbjdSq.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryWbjdSq);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}