package by.epam.learn.bahlei.finaltask.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Integer> serviceIds = new ArrayList<>();

    public void addServiceId(int serviceId) {
        serviceIds.add(serviceId);
    }

    public void removeService(Integer serviceId) {
        serviceIds.remove(serviceId);
    }

    public List<Integer> getServiceIds() {
        return serviceIds;
    }

    public int getCount() {
        return serviceIds.size();
    }
}
