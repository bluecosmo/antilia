/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.antilia.common.dao.IDaoLocator;
import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.Query;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.model.TableModel;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.feedback.AntiliaFeedBackPanel;
import com.antilia.web.field.AutoFieldCreator;
import com.antilia.web.field.AutoFieldPanel;
import com.antilia.web.field.BeanForm;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IAutoFieldCreator;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;
import com.antilia.web.menu.Menu;
import com.antilia.web.navigator.INavigatorSelector;
import com.antilia.web.navigator.IPageableNavigator;
import com.antilia.web.navigator.impl.DataProviderPageableNavigator;
import com.antilia.web.provider.ILoadable;
import com.antilia.web.provider.IQuerableDataProvider;
import com.antilia.web.provider.impl.DaoQuerableDataProvider;
import com.antilia.web.utils.DaoUtils;
import com.google.inject.Inject;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SearchPanel<B extends Serializable> extends Panel implements ILoadablePanel<B>, ICRUDModeReporter {

	private static final long serialVersionUID = 1L;

	private IQuery<B> filterQuery;
	
	private BeanProxy<B> beanProxy;
	
	private IPageableNavigator<B> pageableProvider;
	
	private Table<B> table;
	
	private CrudStyler<B> styler;
	
	private Map<String,IFieldModel<B>> models;
	
	private AntiliaFeedBackPanel feedback;
	
	@SpringBean
	@Inject(optional=true)
	private transient IDaoLocator daoLocator;
	
	public static final String EXTRA_ID_SEARCH = "SearchPanel";

	/**
	 * 
	 * @param id
	 * @param styler
	 */
	public SearchPanel(String id, CrudStyler<B> styler) {
		this(id,  null, (IPageableNavigator<B>)null, styler);
	}
	
	/**
	 * 
	 * @param id
	 * @param filterQuery
	 * @param pageableProvider
	 * @param styler
	 */
	public SearchPanel(String id,  IQuerableDao<B> dao, CrudStyler<B> styler) {
		super(id);
		IQuery<B> filterQuery = new Query<B>(styler.getBeanClass());
		initialize(filterQuery, new DataProviderPageableNavigator<B>(new DaoQuerableDataProvider<B>(filterQuery, dao), filterQuery),styler);
		
	}
	
	/**
	 * 
	 * @param id
	 * @param filterQuery
	 * @param pageableProvider
	 * @param styler
	 */
	public SearchPanel(String id,  IQuery<B> filterQuery, IQuerableDao<B> dao, CrudStyler<B> styler) {
		this(id, filterQuery, new DataProviderPageableNavigator<B>(new DaoQuerableDataProvider<B>(filterQuery, dao), filterQuery),styler);
	}
	
	/**
	 * 
	 * @param id
	 * @param beanClass
	 * @param filterQuery
	 */
	public SearchPanel(String id,  IQuery<B> filterQuery, IPageableNavigator<B> pageableProvider, CrudStyler<B> styler) {
		super(id);		
		initialize(filterQuery, pageableProvider, styler);		
	}
	
	private void initialize(IQuery<B> filterQuery, IPageableNavigator<B> pageableProvider, CrudStyler<B> styler) {
		setOutputMarkupId(true);
		
		this.models = new HashMap<String,IFieldModel<B>>();
		
		if(filterQuery != null)			
			this.filterQuery = filterQuery;
		else 
			this.filterQuery =  new Query<B>(styler.getBeanClass());
		// create the form
		
		this.beanProxy = new BeanProxy<B>(this.filterQuery.getEntityClass());
		if(pageableProvider != null) 
			this.pageableProvider =  pageableProvider;
		else {
			this.pageableProvider = new DataProviderPageableNavigator<B>(createPageableProvider(this.filterQuery), this.filterQuery);
		}
		
		this.styler = styler;
		
		IAutoFieldCreator<B> autoFieldModel = newAutoFieldModel(this.filterQuery, this.beanProxy);
		configureFieldModel(autoFieldModel);
		
		BeanForm<B> beanForm = newForm("form", this.beanProxy);
		add(beanForm);
				
				
		Menu menu = newTopMenuMenu("topMenu");
		 
		beanForm.add(menu);
		 
		AutoFieldPanel<B> autoFieldPanel = newAutoFieldPanel("autofield",autoFieldModel);
		beanForm.add(autoFieldPanel);
		
		// allow users to configure the column models.
		ITableModel<B> tableModel = newTableModel(styler.getBeanClass());		
		Iterator<IColumnModel<B>> it = tableModel.getColumnModels();		
		while(it.hasNext() ) {
			IColumnModel<B> columnModel = it.next();
			configureColumnModel(columnModel);
		}
		
		// allow users to configure hidden column models.
		it = tableModel.getHiddenModels();		
		while(it.hasNext() ) {
			IColumnModel<B> columnModel = it.next();
			configureColumnModel(columnModel);
		}
		
		table  = newTable("table",tableModel, this.pageableProvider);
		table.addColumnRenderers(styler.getRenderers());
		beanForm.add(table);
		
		feedback =  new AntiliaFeedBackPanel("messages");		
		add(feedback);
		
		table.setFeedback(feedback);
	}
	
	/**
	 * Give the chance to sub-classes to return 
	 * 
	 * @param filterQuery
	 * @return
	 */
	protected IQuerableDataProvider<B> createPageableProvider(IQuery<B> filterQuery) {
		return new DaoQuerableDataProvider<B>(filterQuery, createQuerableDao(filterQuery));
	}
	
	/**
	 * 
	 * @return
	 */
	protected IQuerableDao<B> createQuerableDao(IQuery<B> filterQuery) {
		return DaoUtils.findQuerableDao(daoLocator, filterQuery.getEntityClass(), getExtraId());		
	}
	
	protected String getExtraId() {
		return EXTRA_ID_SEARCH;
	}
	
	protected Menu newTopMenuMenu(String id) {
		return Menu.createMenu(id, null,
				new IMenuItemsFactory() {
			
			private static final long serialVersionUID = 1L;

			public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
				SearchPanel.this.addItemsTopMenuBeforeSearchButtons(menuId, itemHolder);
			}
			
		},
		SearchPanelButtonsFactory.getInstance(),
		new IMenuItemsFactory() {
			
			private static final long serialVersionUID = 1L;

			public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
				SearchPanel.this.addItemsTopMenuAfterSearchButtons(menuId, itemHolder);
			}
			
		});
	}
	
	/**
	 * Override it to add items to top menu before the standard search buttons.
	 * @param menuId
	 * @param itemHolder
	 */
	protected void addItemsTopMenuBeforeSearchButtons(String menuId, IMenuItemHolder itemHolder) {
		
	}
	
	/**
	 * Override it to add items to top menu before the standard search buttons.
	 * @param menuId
	 * @param itemHolder
	 */
	protected void addItemsTopMenuAfterSearchButtons(String menuId, IMenuItemHolder itemHolder) {
		
	}
	
	 public INavigatorSelector<B> getSelected() {
		 return table.getSourceSelector();
	 }
	
	protected void configureFieldModel(IAutoFieldCreator<B> autoFieldModel) {
		List<String> searchFields = styler.getSearchFields();
		if(searchFields != null) {
			for(String propertyPath: searchFields) {
				autoFieldModel.newFieldModel(propertyPath);
			}
		}
	}
	
	protected void populateTopMenu(Menu topMenu) {
		
	}
	
	protected AutoFieldPanel<B> newAutoFieldPanel(String id, IAutoFieldCreator<B> autoFieldModel) {
		return new AutoFieldPanel<B>(id,autoFieldModel, FieldMode.SEARCH);
	}
	
	protected BeanForm<B> newForm(String id, BeanProxy<B> beanProxy) {
		return new BeanForm<B>(id, beanProxy);
	}
	
	protected Table<B> newTable(String id, ITableModel<B> tableModel, IPageableNavigator<B> pageableProvider) {
		return new Table<B>(id,tableModel, pageableProvider);
	}
	
	protected ITableModel<B> newTableModel(Class<B> beanClass) {
		return new TableModel<B>(beanClass, styler.getTableColumns(), styler.getHiddenTableColumns());
	}
	
	/**
	 * This method is called for each column model to give the user a chance to configure the 
	 * columns...
	 * @param model
	 */
	protected void configureColumnModel(IColumnModel<B> model) {
		
	}
	
	protected IAutoFieldCreator<B> newAutoFieldModel(IQuery<B> query, BeanProxy<B> beanProxy) {
		return new AutoFieldCreator<B>(query, beanProxy, this.models);
	}
	
	@SuppressWarnings("unchecked")
	public void reload() {
		getBeanProxy().updateFilterQuery(getFilterQuery(), this.models);
		if(this.pageableProvider instanceof ILoadable) {
			((ILoadable)this.pageableProvider).load(getFilterQuery());
		}
	}

	/**
	 * @return the filterQuery
	 */
	public Query<B> getFilterQuery() {
		return (Query<B>)filterQuery;
	}

	/**
	 * @param filterQuery the filterQuery to set
	 */
	public void setFilterQuery(Query<B> filterQuery) {
		this.filterQuery = filterQuery;
	}

	/**
	 * @return the beanProxy
	 */
	public BeanProxy<B> getBeanProxy() {
		return beanProxy;
	}

	/**
	 * @return the pageableProvider
	 */
	public IPageableNavigator<B> getPageableProvider() {
		return pageableProvider;
	}
	
	public CRUDMode getCrudMode() {
		return CRUDMode.SEARCH;
	}
	
	public AntiliaFeedBackPanel getFeedback() {
		return feedback;
	}

	public void setFeedback(AntiliaFeedBackPanel feedback) {
		this.feedback = feedback;
	}
}
