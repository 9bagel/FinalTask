package by.epam.learn.bahlei.finaltask.dao.service;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.entity.service.ServiceType;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceDao extends ServiceDaoAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ServiceDao.class);

    @Override
    protected List<Service> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Service> services = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Service service = new Service();

                service.setId(resultSet.getInt(Constants.ID));

                service.setTitleEn(resultSet.getString(Constants.TITLE_EN));
                service.setTitleRu(resultSet.getString(Constants.TITLE_RU));
                service.setTitleBy(resultSet.getString(Constants.TITLE_BY));

                service.setDescriptionEn(resultSet.getString(Constants.DESCRIPTION_EN));
                service.setDescriptionRu(resultSet.getString(Constants.DESCRIPTION_RU));
                service.setDescriptionBy(resultSet.getString(Constants.DESCRIPTION_BY));

                service.setServiceType(ServiceType.getById(resultSet.getInt(Constants.TYPE_ID)));
                service.setPrice(resultSet.getBigDecimal(Constants.PRICE));

                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in parseResultSet in ServiceDao", e));
        }
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Service service) throws DaoException {
        try {
            preparedStatement.setInt(1, service.getServiceType().getId());

            preparedStatement.setString(2, service.getTitleEn());
            preparedStatement.setString(3, service.getTitleRu());
            preparedStatement.setString(4, service.getTitleBy());

            preparedStatement.setString(5, service.getDescriptionEn());
            preparedStatement.setString(6, service.getDescriptionRu());
            preparedStatement.setString(7, service.getDescriptionBy());

            preparedStatement.setBigDecimal(8, service.getPrice());
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Service service) throws DaoException {
        try {
            preparedStatement.setInt(1, service.getServiceType().getId());

            preparedStatement.setString(2, service.getTitleEn());
            preparedStatement.setString(3, service.getTitleRu());
            preparedStatement.setString(4, service.getTitleBy());

            preparedStatement.setString(5, service.getDescriptionEn());
            preparedStatement.setString(6, service.getDescriptionRu());
            preparedStatement.setString(7, service.getDescriptionBy());

            preparedStatement.setBigDecimal(8, service.getPrice());
            preparedStatement.setInt(9, service.getId());
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }

    public List<Service> getServicesByType(ServiceType serviceType) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getServicesByTypeIdQuery())) {
            int typeId = serviceType.getId();
            preparedStatement.setInt(1, typeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }

    public boolean isServiceExists(int serviceId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getSelectServiceByIdQuery())) {

            preparedStatement.setInt(1, serviceId);
            return preparedStatement.executeQuery().next();
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in isServiceExists() in ServiceDao", e));
        }
    }

    public List<Service> getServicesById(List<Integer> serviceIds) throws DaoException {
        List<Service> services = new ArrayList<>();
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getServiceByIdQuery())) {

            for (Integer serviceId : serviceIds) {
                preparedStatement.setInt(1, serviceId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Optional<Service> optionalService = parseResultSet(resultSet)
                            .stream()
                            .findFirst();
                    services.add(optionalService.orElseThrow(DaoException::new));
                }
            }
            return services;
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in getServicesByIdsAndLanguage() in ServiceDao", e));
        }
    }

    public List<Service> getOrderedServices(int orderId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getOrderedServicesQuery())) {
            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrderedServices()", e));
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

    public List<Service> getServiceById(int serviceId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getServiceByIdQuery())) {
            preparedStatement.setInt(1, serviceId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }
}