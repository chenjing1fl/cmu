package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.WbjdSxry;
import cn.edu.cmu.domain.User;
import cn.edu.cmu.framework.utils.CmuStringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;


/**
 * Service��Ĳ�����
 *
 * ����ɾ�Ĳ�
 */
public class WbjdSxryServiceImplTest_false extends SpringIOC {

    WbjdSxryService service;

    @Before
    public  void init(){
        service = ac.getBean(WbjdSxryService.class);
    }
    //����
    @Test
    public void save() {
        WbjdSxry wbjdSxry = new WbjdSxry(CmuStringUtil.UUID(),"eb036d5ea1394154bddbc27aa5b34900","01","01","01",null,null);
        boolean success = service.insert(wbjdSxry);
        System.out.println("WbjdSxry:"+wbjdSxry);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "24f5abdd9fb646829635e2e83e758141";
        WbjdSxry wbjdSxry = service.queryById(keyId);
        System.out.println(wbjdSxry);
        Assert.assertTrue(wbjdSxry!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        WbjdSxry wbjdSxryParam = new WbjdSxry();
        wbjdSxryParam.setXm("0");
        List<WbjdSxry> wbjdSxrys = service.list(wbjdSxryParam);
        for (WbjdSxry wbjdSxry : wbjdSxrys ) {
            System.out.println(wbjdSxry);
        }
        Assert.assertTrue(wbjdSxrys.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "24f5abdd9fb646829635e2e83e758141";
        WbjdSxry wbjdSxry = service.queryById(keyId);
        System.out.println("����ǰ:"+wbjdSxry);
        wbjdSxry.setXm("02");
        boolean success = service.updateById(wbjdSxry);
        System.out.println("success:"+success);
        WbjdSxry newWbjdSxry = service.queryById(keyId);
        System.out.println("���º�:"+newWbjdSxry);
        Assert.assertTrue(newWbjdSxry.getXm().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        WbjdSxry wbjdSxry = new WbjdSxry(keyId,"eb036d5ea1394154bddbc27aa5b34900","01","01","01",null,null);
        boolean success = service.insert(wbjdSxry);
        System.out.println("����ɹ���"+success);
        WbjdSxry queryWbjdSxry = service.queryById(keyId);
        System.out.println("������ѯ"+queryWbjdSxry);
        queryWbjdSxry.setValid("0");
        queryWbjdSxry.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryWbjdSxry);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}