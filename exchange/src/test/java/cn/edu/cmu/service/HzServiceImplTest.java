package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.Hz;
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
public class HzServiceImplTest extends SpringIOC {

    HzService service;

    @Before
    public  void init(){
        service = ac.getBean(HzService.class);
    }
    //����
    @Test
    public void save() {
        Hz hz = new Hz(CmuStringUtil.UUID(),"01","01","01","01",new Date(), "01",new Date(), "01",new Date(),"01","01",new  Date(), "01", null,null);
        boolean success = service.insert(hz);
        System.out.println("Hz:"+hz);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "c8f98f57d4e54ee29e85ac849db01552";
        Hz hz = service.queryById(keyId);
        System.out.println(hz);
        Assert.assertTrue(hz!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        Hz hzParam = new Hz();
        hzParam.setXm("01");
        List<Hz> hzs = service.list(hzParam);
        for (Hz hz : hzs ) {
            System.out.println(hz);
        }
        Assert.assertTrue(hzs.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "c8f98f57d4e54ee29e85ac849db01552";
        Hz hz = service.queryById(keyId);
        System.out.println("����ǰ:"+hz);
        hz.setXm("02");
        boolean success = service.updateById(hz);
        System.out.println("success:"+success);
        Hz newHz = service.queryById(keyId);
        System.out.println("���º�:"+newHz);
        Assert.assertTrue(newHz.getXm().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        Hz hz = new Hz(keyId ,"01","01","01","01",new Date(), "01",new Date(), "01",new Date(),"01","01",new  Date(), "01", null,null);
        boolean success = service.insert(hz);
        System.out.println("����ɹ���"+success);
        Hz queryHz = service.queryById(keyId);
        System.out.println("������ѯ"+queryHz);
        queryHz.setValid("0");
        queryHz.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryHz);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}