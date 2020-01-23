package by.epam.learn.bahlei.finaltask.dao.service;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceTypeDto;
import by.epam.learn.bahlei.finaltask.entity.service.LocalisedService;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao extends ServiceDaoAbstract {
    private static final int TYPE_ID_INDEX = 1;
    private static final ServiceDao INSTANCE = new ServiceDao();
    private static final Logger LOGGER = LogManager.getLogger(ServiceDao.class);

    private ServiceDao() {
    }

    protected List<Service> parseResultSet(ResultSet resultSet) throws DaoException {
        try {
            List<Service> services = new ArrayList<>();
            while (resultSet.next()) {
                Service service = new Service();

                String titleEn = resultSet.getString(Constants.TITLE_COLUMN_NAME_EN);
                String titleRu = resultSet.getString(Constants.TITLE_COLUMN_NAME_RU);
                String titleBy = resultSet.getString(Constants.TITLE_COLUMN_NAME_BY);

                String descriptionEn = resultSet.getString(Constants.DESCRIPTION_COLUMN_NAME_EN);
                String descriptionRu = resultSet.getString(Constants.DESCRIPTION_COLUMN_NAME_RU);
                String descriptionBy = resultSet.getString(Constants.DESCRIPTION_COLUMN_NAME_BY);

                int typeId = resultSet.getInt(Constants.TYPE_ID);
                int price = resultSet.getInt(Constants.PRICE_COLUMN_NAME);
                int id = resultSet.getInt(Constants.ID_COLUMN_NAME);

                service.setTitleEn(titleEn);
                service.setTitleRu(titleRu);
                service.setTitleBy(titleBy);

                service.setDescriptionEn(descriptionEn);
                service.setDescriptionRu(descriptionRu);
                service.setDescriptionBy(descriptionBy);

                service.setId(id);
                service.setTypeId(typeId);
                service.setPrice(price);

                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in parseResultSet in ServiceDao", e));
        }
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Service entity) throws DaoException {

    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Service entity) throws DaoException {

    }

    public static ServiceDao getInstance() {
        return INSTANCE;
    }

    public List<Service> getServicesByTypeAndLanguage(ServiceTypeDto serviceTypeDto, LanguageTypeDto languageTypeDto) throws DaoException {

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getServicesByTypeIdQuery())) {

            int typeId = serviceTypeDto.getId();
            preparedStatement.setInt(TYPE_ID_INDEX, typeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            return parseResultSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }

    }

    public LocalisedService getServiceById(int serviceId) {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getServicesByTypeIdQuery())) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery(getServiceByIdQuery());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Service> getServicesByTypeId(int typeId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getServicesByTypeIdQuery())) {

            preparedStatement.setInt(1, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseResultSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Exception in getServicesByTypeId in ServiceDao", e));
        }
    }
}
