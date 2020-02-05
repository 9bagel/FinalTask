package by.epam.learn.bahlei.finaltask.dao.service;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceDto;
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

    public static ServiceDao getInstance() {
        return INSTANCE;
    }

    protected List<Service> parseResultSet(ResultSet resultSet) throws DaoException {
        throw new DaoException("Operation not supported");
    }

    protected List<Service> parseResultSet(ResultSet resultSet, LanguageTypeDto languageType) throws DaoException {

        List<Service> serviceList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Service service = new Service();

                service.setId(resultSet.getInt(Constants.ID_COLUMN_NAME));
                service.setTitle(resultSet.getString(languageType.getTitleColumnName()));
                service.setDescription(resultSet.getString(languageType.getDescriptionColumnName()));
                service.setTypeId(resultSet.getInt(Constants.TYPE_ID));
                service.setPrice(resultSet.getBigDecimal(Constants.PRICE));

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

    protected void prepareInsert(PreparedStatement preparedStatement, ServiceDto serviceDto) throws DaoException {
        try {
            preparedStatement.setInt(1, serviceDto.getTypeId());

            preparedStatement.setString(2, serviceDto.getTitleEn());
            preparedStatement.setString(3, serviceDto.getTitleRu());
            preparedStatement.setString(4, serviceDto.getTitleBy());

            preparedStatement.setString(5, serviceDto.getDescriptionEn());
            preparedStatement.setString(6, serviceDto.getDescriptionRu());
            preparedStatement.setString(7, serviceDto.getDescriptionBy());

            preparedStatement.setBigDecimal(8, serviceDto.getPrice());
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Service entity) throws DaoException {

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

    public List<Service> getOrderedServices(int orderId, LanguageTypeDto languageTypeDto) throws DaoException {
        String getOrderedServices = getOrderedServicesQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getOrderedServices)) {

            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return parseResultSet(resultSet, languageTypeDto);
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrderedServices()", e));
        }
    }

    public List<Service> getAll(LanguageTypeDto languageTypeDto) throws DaoException {
        String selectAllQuery = getSelectAllQuery();
        List<Service> services;

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            services = parseResultSet(resultSet, languageTypeDto);

            return services;
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getAll method", e));
        }
    }

    public boolean isServiceExists(int serviceId) throws DaoException {
        String selectServiceByIdQuery = getSelectServiceByIdQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectServiceByIdQuery)) {

            preparedStatement.setInt(1, serviceId);

            return preparedStatement.executeQuery().next();
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in isServiceExists() in ServiceDao", e));
        }
    }

    public List<Service> getServicesByIdsAndLanguage(List<Integer> serviceIds, LanguageTypeDto languageTypeDto) throws DaoException {
        List<Service> services = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getServiceByIdQuery())) {

            for (Integer serviceId : serviceIds) {
                preparedStatement.setInt(1, serviceId);
                ResultSet resultSet = preparedStatement.executeQuery();
                services.add(parseResultSet(resultSet, languageTypeDto).get(0));
            }
            return services;
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in getServicesByIdsAndLanguage() in ServiceDao", e));
        }
    }

    public void insert(ServiceDto serviceDto) throws DaoException {
        String insertQuery = getInsertQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            prepareInsert(preparedStatement, serviceDto);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }

    public void deleteServiceById(int serviceId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.setInt(1, serviceId);
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }
}