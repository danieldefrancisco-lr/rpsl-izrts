/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package commerce.training.car.garage.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import commerce.training.car.garage.model.CarGarage;
import commerce.training.car.garage.service.CarGarageLocalService;
import commerce.training.car.garage.service.persistence.CarGaragePersistence;
import commerce.training.car.garage.service.persistence.CarGarageProductPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the car garage local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link commerce.training.car.garage.service.impl.CarGarageLocalServiceImpl}.
 * </p>
 *
 * @author Roselaine Marques
 * @see commerce.training.car.garage.service.impl.CarGarageLocalServiceImpl
 * @generated
 */
public abstract class CarGarageLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CarGarageLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CarGarageLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>commerce.training.car.garage.service.CarGarageLocalServiceUtil</code>.
	 */

	/**
	 * Adds the car garage to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarage the car garage
	 * @return the car garage that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CarGarage addCarGarage(CarGarage carGarage) {
		carGarage.setNew(true);

		return carGaragePersistence.update(carGarage);
	}

	/**
	 * Creates a new car garage with the primary key. Does not add the car garage to the database.
	 *
	 * @param carGarageId the primary key for the new car garage
	 * @return the new car garage
	 */
	@Override
	@Transactional(enabled = false)
	public CarGarage createCarGarage(long carGarageId) {
		return carGaragePersistence.create(carGarageId);
	}

	/**
	 * Deletes the car garage with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarageId the primary key of the car garage
	 * @return the car garage that was removed
	 * @throws PortalException if a car garage with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CarGarage deleteCarGarage(long carGarageId) throws PortalException {
		return carGaragePersistence.remove(carGarageId);
	}

	/**
	 * Deletes the car garage from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarage the car garage
	 * @return the car garage that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CarGarage deleteCarGarage(CarGarage carGarage) {
		return carGaragePersistence.remove(carGarage);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CarGarage.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return carGaragePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>commerce.training.car.garage.model.impl.CarGarageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return carGaragePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>commerce.training.car.garage.model.impl.CarGarageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return carGaragePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return carGaragePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return carGaragePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CarGarage fetchCarGarage(long carGarageId) {
		return carGaragePersistence.fetchByPrimaryKey(carGarageId);
	}

	/**
	 * Returns the car garage matching the UUID and group.
	 *
	 * @param uuid the car garage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching car garage, or <code>null</code> if a matching car garage could not be found
	 */
	@Override
	public CarGarage fetchCarGarageByUuidAndGroupId(String uuid, long groupId) {
		return carGaragePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the car garage with the primary key.
	 *
	 * @param carGarageId the primary key of the car garage
	 * @return the car garage
	 * @throws PortalException if a car garage with the primary key could not be found
	 */
	@Override
	public CarGarage getCarGarage(long carGarageId) throws PortalException {
		return carGaragePersistence.findByPrimaryKey(carGarageId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(carGarageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CarGarage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("carGarageId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			carGarageLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CarGarage.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"carGarageId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(carGarageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CarGarage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("carGarageId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CarGarage>() {

				@Override
				public void performAction(CarGarage carGarage)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, carGarage);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(CarGarage.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return carGarageLocalService.deleteCarGarage((CarGarage)persistedModel);
	}

	public BasePersistence<CarGarage> getBasePersistence() {
		return carGaragePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return carGaragePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the car garages matching the UUID and company.
	 *
	 * @param uuid the UUID of the car garages
	 * @param companyId the primary key of the company
	 * @return the matching car garages, or an empty list if no matches were found
	 */
	@Override
	public List<CarGarage> getCarGaragesByUuidAndCompanyId(
		String uuid, long companyId) {

		return carGaragePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of car garages matching the UUID and company.
	 *
	 * @param uuid the UUID of the car garages
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of car garages
	 * @param end the upper bound of the range of car garages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching car garages, or an empty list if no matches were found
	 */
	@Override
	public List<CarGarage> getCarGaragesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CarGarage> orderByComparator) {

		return carGaragePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the car garage matching the UUID and group.
	 *
	 * @param uuid the car garage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching car garage
	 * @throws PortalException if a matching car garage could not be found
	 */
	@Override
	public CarGarage getCarGarageByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return carGaragePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the car garages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>commerce.training.car.garage.model.impl.CarGarageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of car garages
	 * @param end the upper bound of the range of car garages (not inclusive)
	 * @return the range of car garages
	 */
	@Override
	public List<CarGarage> getCarGarages(int start, int end) {
		return carGaragePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of car garages.
	 *
	 * @return the number of car garages
	 */
	@Override
	public int getCarGaragesCount() {
		return carGaragePersistence.countAll();
	}

	/**
	 * Updates the car garage in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarage the car garage
	 * @return the car garage that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CarGarage updateCarGarage(CarGarage carGarage) {
		return carGaragePersistence.update(carGarage);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CarGarageLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		carGarageLocalService = (CarGarageLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CarGarageLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CarGarage.class;
	}

	protected String getModelClassName() {
		return CarGarage.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = carGaragePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected CarGarageLocalService carGarageLocalService;

	@Reference
	protected CarGaragePersistence carGaragePersistence;

	@Reference
	protected CarGarageProductPersistence carGarageProductPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetTagLocalService
		assetTagLocalService;

}