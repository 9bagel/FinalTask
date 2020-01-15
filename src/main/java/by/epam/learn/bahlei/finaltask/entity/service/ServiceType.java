package by.epam.learn.bahlei.finaltask.entity.service;

public enum ServiceType {
    HAIRCUT(1),
    PAW_CARE(2),
    WASHING_DRYING(3),
    COMBING_OUT(4),
    CLAW_TRIMMING(5),
    EAR_CLEANING(6),
    EYE_BRUSHING(7),
    SPA(8),
    TEETH_CLEANING(9),
    TAXI(10);

    private int id;

    ServiceType(int id) {
        this.id = id;
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
}
