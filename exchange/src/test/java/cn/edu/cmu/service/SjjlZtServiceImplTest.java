package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.SjjlZt;
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
public class SjjlZtServiceImplTest extends SpringIOC {

    SjjlZtService service;

    @Before
    public  void init(){
        service = ac.getBean(SjjlZtService.class);
    }
    //����
    @Test
    public void save() {
        SjjlZt sjjlZt = new SjjlZt(CmuStringUtil.UUID(),"01", new Date(), "01","01","01", null,null);
        boolean success = service.insert(sjjlZt);
        System.out.println("SjjlZt:"+sjjlZt);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "bfc64cee16ef4935a7f34a8b9f0ed2eb";
        SjjlZt sjjlZt = service.queryById(keyId);
        System.out.println(sjjlZt);
        Assert.assertTrue(sjjlZt!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        SjjlZt sjjlZtParam = new SjjlZt();
        sjjlZtParam.setGjnr("0");
        List<SjjlZt> sjjlZts = service.list(sjjlZtParam);
        for (SjjlZt sjjlZt : sjjlZts ) {
            System.out.println(sjjlZt);
        }
        Assert.assertTrue(sjjlZts.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "bfc64cee16ef4935a7f34a8b9f0ed2eb";
        SjjlZt sjjlZt = service.queryById(keyId);
        System.out.println("����ǰ:"+sjjlZt);
        sjjlZt.setGjnr("02");
        boolean success = service.updateById(sjjlZt);
        System.out.println("success:"+success);
        SjjlZt newSjjlZt = service.queryById(keyId);
        System.out.println("���º�:"+newSjjlZt);
        Assert.assertTrue(newSjjlZt.getGjnr().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        SjjlZt sjjlZt = new SjjlZt(keyId,"01", new Date(), "01","01","01", null,null);
        boolean success = service.insert(sjjlZt);
        System.out.println("����ɹ���"+success);
        SjjlZt querySjjlZt = service.queryById(keyId);
        System.out.println("������ѯ"+querySjjlZt);
        querySjjlZt.setValid("0");
        querySjjlZt.setCreateTime(new Date());
        success = service.updateByIdAllColumn(querySjjlZt);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}