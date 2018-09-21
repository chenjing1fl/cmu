package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.HzxyGb;
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
public class HzxyGbServiceImplTest extends SpringIOC {

    HzxyGbService service;

    @Before
    public  void init(){
        service = ac.getBean(HzxyGbService.class);
    }
    //����
    @Test
    public void save() {
        HzxyGb hzxyGb = new HzxyGb(CmuStringUtil.UUID(),"01","01","611eece2fe8244fa8e1729e9a8d3417a",null,null);
        boolean success = service.insert(hzxyGb);
        System.out.println("HzxyGb:"+hzxyGb);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "e1f1f942fa874f52ac844f3fb8c757f9";
        HzxyGb hzxyGb = service.queryById(keyId);
        System.out.println(hzxyGb);
        Assert.assertTrue(hzxyGb!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        HzxyGb hzxyGbParam = new HzxyGb();
        hzxyGbParam.setGbmc("01");
        List<HzxyGb> hzxyGbs = service.list(hzxyGbParam);
        for (HzxyGb hzxyGb : hzxyGbs ) {
            System.out.println(hzxyGb);
        }
        Assert.assertTrue(hzxyGbs.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "e1f1f942fa874f52ac844f3fb8c757f9";
        HzxyGb hzxyGb = service.queryById(keyId);
        System.out.println("����ǰ:"+hzxyGb);
        hzxyGb.setGbmc("02");
        boolean success = service.updateById(hzxyGb);
        System.out.println("success:"+success);
        HzxyGb newHzxyGb = service.queryById(keyId);
        System.out.println("���º�:"+newHzxyGb);
        Assert.assertTrue(newHzxyGb.getGbmc().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        HzxyGb hzxyGb = new HzxyGb(keyId,"01","01","611eece2fe8244fa8e1729e9a8d3417a",null,null);
        boolean success = service.insert(hzxyGb);
        System.out.println("����ɹ���"+success);
        HzxyGb queryHzxyGb = service.queryById(keyId);
        System.out.println("������ѯ"+queryHzxyGb);
        queryHzxyGb.setValid("0");
        queryHzxyGb.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryHzxyGb);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}