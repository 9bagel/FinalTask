package by.epam.learn.bahlei.finaltask.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Integer> services = new ArrayList<>();

    public void addServiceId(int serviceId) {
        services.add(serviceId);
    }

    public void removeService(Integer serviceId) {
        services.remove(serviceId);
    }

    public List<Integer> getServiceList() {
        return services;
    }

    public int getCount() {
        return services.size();
    }
}
