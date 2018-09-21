package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.HyShenb;
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
public class HyShenbServiceImplTest extends SpringIOC {

    HyShenbService service;

    @Before
    public  void init(){
        service = ac.getBean(HyShenbService.class);
    }
    //����
    @Test
    public void save() {
        HyShenb hyShenb = new HyShenb(CmuStringUtil.UUID(), "3e0b62966c6347d49729316edfa79fda","01","01","01",new  Date(),"01","01","01","01","01","01","01","01","01", null,null);
        boolean success = service.insert(hyShenb);
        System.out.println("HyShenb:"+hyShenb);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "db7922e1e7fc4da79e6158fbf8025640";
        HyShenb hyShenb = service.queryById(keyId);
        System.out.println(hyShenb);
        Assert.assertTrue(hyShenb!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        HyShenb hyShenbParam = new HyShenb();
        hyShenbParam.setJbdw("01");
        List<HyShenb> hyShenbs = service.list(hyShenbParam);
        for (HyShenb hyShenb : hyShenbs ) {
            System.out.println(hyShenb);
        }
        Assert.assertTrue(hyShenbs.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "db7922e1e7fc4da79e6158fbf8025640";
        HyShenb hyShenb = service.queryById(keyId);
        System.out.println("����ǰ:"+hyShenb);
        hyShenb.setJbdw("02");
        boolean success = service.updateById(hyShenb);
        System.out.println("success:"+success);
        HyShenb newHyShenb = service.queryById(keyId);
        System.out.println("���º�:"+newHyShenb);
        Assert.assertTrue(newHyShenb.getJbdw().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        HyShenb hyShenb = new HyShenb(keyId, "3e0b62966c6347d49729316edfa79fda","01","01","01",new  Date(),"01","01","01","01","01","01","01","01","01", null,null);
        boolean success = service.insert(hyShenb);
        System.out.println("����ɹ���"+success);
        HyShenb queryHyShenb = service.queryById(keyId);
        System.out.println("������ѯ"+queryHyShenb);
        queryHyShenb.setValid("0");
        queryHyShenb.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryHyShenb);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}