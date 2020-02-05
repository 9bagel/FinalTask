package by.epam.learn.bahlei.finaltask.entity.service;

import by.epam.learn.bahlei.finaltask.util.Constants;

public enum ServiceType {
    HAIRCUT(1, Constants.SERVICE_TYPE_HAIRCUT),
    PAW_CARE(2, Constants.SERVICE_TYPE_PAW_CARE),
    WASHING_DRYING(3, Constants.SERVICE_TYPE_WASHING_DRYING),
    COMBING_OUT(4, Constants.SERVICE_TYPE_COMBING_OUT),
    CLAW_TRIMMING(5, Constants.SERVICE_TYPE_CLAW_TRIMMING),
    EAR_CLEANING(6, Constants.SERVICE_TYPE_EAR_CLEANING),
    EYE_BRUSHING(7, Constants.SERVICE_TYPE_EYE_BRUSHING),
    SPA(8, Constants.SERVICE_TYPE_SPA),
    TEETH_CLEANING(9, Constants.SERVICE_TYPE_TEETH_CLEANING),
    TAXI(10, Constants.SERVICE_TYPE_TAXI);

    private int id;
    private String name;

    ServiceType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public static ServiceType getById(int id) {
        for (ServiceType serviceType : values()) {
            if (serviceType.id == id) return serviceType;
        }
        throw new IllegalArgumentException("No such typeId for serviceType");
    }

    public String getName() {
        return name;
    }
}
