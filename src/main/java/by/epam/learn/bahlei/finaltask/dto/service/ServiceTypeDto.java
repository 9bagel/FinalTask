package by.epam.learn.bahlei.finaltask.dto.service;

public enum ServiceTypeDto {
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

    ServiceTypeDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ServiceTypeDto getById(int id) {
        for (ServiceTypeDto serviceTypeDto : values()) {
            if (serviceTypeDto.id == id) return serviceTypeDto;
        }
        throw new IllegalArgumentException("No such typeId for serviceType");
    }
}
