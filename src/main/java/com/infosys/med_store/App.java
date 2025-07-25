package com.infosys.med_store;

import com.infosys.med_store.dao.OrderDao;
import com.infosys.med_store.entity.Order.Status;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OrderDao od = new OrderDao();
        od.setOrderStatus(1, Status.SHIPPED);
        od.getAllOrder();
        
    }
}
