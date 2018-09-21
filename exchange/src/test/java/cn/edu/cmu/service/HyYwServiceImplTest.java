package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.HyYw;
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
public class HyYwServiceImplTest extends SpringIOC {

    HyYwService service;

    @Before
    public  void init(){
        service = ac.getBean(HyYwService.class);
    }
    //����
    @Test
    public void save() {
        HyYw hyYw = new HyYw(CmuStringUtil.UUID(),"01","01",new  Date(),"01", null,null);
        boolean success = service.insert(hyYw);
        System.out.println("HyYw:"+hyYw);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "3d7c6de3f5b246feabf7d9fe76aa624b";
        HyYw hyYw = service.queryById(keyId);
        System.out.println(hyYw);
        Assert.assertTrue(hyYw!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        HyYw hyYwParam = new HyYw();
        hyYwParam.setSx("01");
        List<HyYw> hyYws = service.list(hyYwParam);
        for (HyYw hyYw : hyYws ) {
            System.out.println(hyYw);
        }
        Assert.assertTrue(hyYws.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "3d7c6de3f5b246feabf7d9fe76aa624b";
        HyYw hyYw = service.queryById(keyId);
        System.out.println("����ǰ:"+hyYw);
        hyYw.setSx("02");
        boolean success = service.updateById(hyYw);
        System.out.println("success:"+success);
        HyYw newHyYw = service.queryById(keyId);
        System.out.println("���º�:"+newHyYw);
        Assert.assertTrue(newHyYw.getSx().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        HyYw hyYw = new HyYw(keyId,"01","01",new  Date(),"01", null,null);
        boolean success = service.insert(hyYw);
        System.out.println("����ɹ���"+success);
        HyYw queryHyYw = service.queryById(keyId);
        System.out.println("������ѯ"+queryHyYw);
        queryHyYw.setValid("0");
        queryHyYw.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryHyYw);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}