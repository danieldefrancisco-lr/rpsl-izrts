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

import commerce.training.car.garage.model.CarGarageProduct;
import commerce.training.car.garage.service.CarGarageProductLocalService;
import commerce.training.car.garage.service.persistence.CarGaragePersistence;
import commerce.training.car.garage.service.persistence.CarGarageProductPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the car garage product local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link commerce.training.car.garage.service.impl.CarGarageProductLocalServiceImpl}.
 * </p>
 *
 * @author Roselaine Marques
 * @see commerce.training.car.garage.service.impl.CarGarageProductLocalServiceImpl
 * @generated
 */
public abstract class CarGarageProductLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CarGarageProductLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CarGarageProductLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>commerce.training.car.garage.service.CarGarageProductLocalServiceUtil</code>.
	 */

	/**
	 * Adds the car garage product to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageProductLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarageProduct the car garage product
	 * @return the car garage product that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CarGarageProduct addCarGarageProduct(
		CarGarageProduct carGarageProduct) {

		carGarageProduct.setNew(true);

		return carGarageProductPersistence.update(carGarageProduct);
	}

	/**
	 * Creates a new car garage product with the primary key. Does not add the car garage product to the database.
	 *
	 * @param carGarageProductId the primary key for the new car garage product
	 * @return the new car garage product
	 */
	@Override
	@Transactional(enabled = false)
	public CarGarageProduct createCarGarageProduct(long carGarageProductId) {
		return carGarageProductPersistence.create(carGarageProductId);
	}

	/**
	 * Deletes the car garage product with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageProductLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarageProductId the primary key of the car garage product
	 * @return the car garage product that was removed
	 * @throws PortalException if a car garage product with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CarGarageProduct deleteCarGarageProduct(long carGarageProductId)
		throws PortalException {

		return carGarageProductPersistence.remove(carGarageProductId);
	}

	/**
	 * Deletes the car garage product from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageProductLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarageProduct the car garage product
	 * @return the car garage product that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CarGarageProduct deleteCarGarageProduct(
		CarGarageProduct carGarageProduct) {

		return carGarageProductPersistence.remove(carGarageProduct);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CarGarageProduct.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return carGarageProductPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>commerce.training.car.garage.model.impl.CarGarageProductModelImpl</code>.
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

		return carGarageProductPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>commerce.training.car.garage.model.impl.CarGarageProductModelImpl</code>.
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

		return carGarageProductPersistence.findWithDynamicQuery(
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
		return carGarageProductPersistence.countWithDynamicQuery(dynamicQuery);
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

		return carGarageProductPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CarGarageProduct fetchCarGarageProduct(long carGarageProductId) {
		return carGarageProductPersistence.fetchByPrimaryKey(
			carGarageProductId);
	}

	/**
	 * Returns the car garage product matching the UUID and group.
	 *
	 * @param uuid the car garage product's UUID
	 * @param groupId the primary key of the group
	 * @return the matching car garage product, or <code>null</code> if a matching car garage product could not be found
	 */
	@Override
	public CarGarageProduct fetchCarGarageProductByUuidAndGroupId(
		String uuid, long groupId) {

		return carGarageProductPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the car garage product with the primary key.
	 *
	 * @param carGarageProductId the primary key of the car garage product
	 * @return the car garage product
	 * @throws PortalException if a car garage product with the primary key could not be found
	 */
	@Override
	public CarGarageProduct getCarGarageProduct(long carGarageProductId)
		throws PortalException {

		return carGarageProductPersistence.findByPrimaryKey(carGarageProductId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			carGarageProductLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CarGarageProduct.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("carGarageProductId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			carGarageProductLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CarGarageProduct.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"carGarageProductId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			carGarageProductLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CarGarageProduct.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("carGarageProductId");
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
			new ActionableDynamicQuery.PerformActionMethod<CarGarageProduct>() {

				@Override
				public void performAction(CarGarageProduct carGarageProduct)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, carGarageProduct);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(CarGarageProduct.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return carGarageProductLocalService.deleteCarGarageProduct(
			(CarGarageProduct)persistedModel);
	}

	public BasePersistence<CarGarageProduct> getBasePersistence() {
		return carGarageProductPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return carGarageProductPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the car garage products matching the UUID and company.
	 *
	 * @param uuid the UUID of the car garage products
	 * @param companyId the primary key of the company
	 * @return the matching car garage products, or an empty list if no matches were found
	 */
	@Override
	public List<CarGarageProduct> getCarGarageProductsByUuidAndCompanyId(
		String uuid, long companyId) {

		return carGarageProductPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of car garage products matching the UUID and company.
	 *
	 * @param uuid the UUID of the car garage products
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of car garage products
	 * @param end the upper bound of the range of car garage products (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching car garage products, or an empty list if no matches were found
	 */
	@Override
	public List<CarGarageProduct> getCarGarageProductsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CarGarageProduct> orderByComparator) {

		return carGarageProductPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the car garage product matching the UUID and group.
	 *
	 * @param uuid the car garage product's UUID
	 * @param groupId the primary key of the group
	 * @return the matching car garage product
	 * @throws PortalException if a matching car garage product could not be found
	 */
	@Override
	public CarGarageProduct getCarGarageProductByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return carGarageProductPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the car garage products.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>commerce.training.car.garage.model.impl.CarGarageProductModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of car garage products
	 * @param end the upper bound of the range of car garage products (not inclusive)
	 * @return the range of car garage products
	 */
	@Override
	public List<CarGarageProduct> getCarGarageProducts(int start, int end) {
		return carGarageProductPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of car garage products.
	 *
	 * @return the number of car garage products
	 */
	@Override
	public int getCarGarageProductsCount() {
		return carGarageProductPersistence.countAll();
	}

	/**
	 * Updates the car garage product in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CarGarageProductLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param carGarageProduct the car garage product
	 * @return the car garage product that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CarGarageProduct updateCarGarageProduct(
		CarGarageProduct carGarageProduct) {

		return carGarageProductPersistence.update(carGarageProduct);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CarGarageProductLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		carGarageProductLocalService = (CarGarageProductLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CarGarageProductLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CarGarageProduct.class;
	}

	protected String getModelClassName() {
		return CarGarageProduct.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = carGarageProductPersistence.getDataSource();

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

	@Reference
	protected CarGaragePersistence carGaragePersistence;

	protected CarGarageProductLocalService carGarageProductLocalService;

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