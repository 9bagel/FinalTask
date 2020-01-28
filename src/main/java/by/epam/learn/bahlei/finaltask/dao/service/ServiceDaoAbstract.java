package by.epam.learn.bahlei.finaltask.dao.service;

import by.epam.learn.bahlei.finaltask.dao.AbstractEntityDao;
import by.epam.learn.bahlei.finaltask.entity.service.Service;

public abstract class ServiceDaoAbstract extends AbstractEntityDao<Service> {
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM services where id = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO services(type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price) VALUE(?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE services SET type_id = ?, title_en = ?, title_ru = ?, title_by = ?, description_en = ?, description_ru = ?, description_by = ?, price = ? WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services";
    }

    @Override
    protected String getSelectLimitQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services WHERE id > ? LIMIT ?";
    }

    protected String getServicesByTypeIdQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services WHERE type_id = ?";
    }

    protected String getServiceByIdQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services WHERE id = ?";
    }

    protected String getOrderedServicesQuery() {
        return "SELECT services.id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM ordered_services JOIN services on ordered_services.service_id=services.id where order_id = ?";
    }
}
