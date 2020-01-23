package by.epam.learn.bahlei.finaltask.dao.service;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceTypeDto;
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
        throw new DaoException("Operation not supported");
    }

    protected List<Service> parseResultSet(ResultSet resultSet, LanguageTypeDto languageType) throws DaoException {

        List<Service> serviceList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Service service = new Service();

                String title = resultSet.getString(languageType.getTitleColumnName());

                String description = resultSet.getString(languageType.getDescriptionColumnName());

                int typeId = resultSet.getInt(Constants.TYPE_ID);

                int price = resultSet.getInt(Constants.PRICE_COLUMN_NAME);
                int id = resultSet.getInt(Constants.ID_COLUMN_NAME);

                service.setId(id);
                service.setTitle(title);
                service.setDescription(description);
                service.setTypeId(typeId);
                service.setPrice(price);

                serviceList.add(service);
            }
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in parseResultSet in ServiceDao", e));
        }

        return serviceList;
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
            return parseResultSet(resultSet, languageTypeDto);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }

    }


    public Service getServiceById(int serviceId) {
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
