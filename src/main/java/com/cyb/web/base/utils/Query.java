package com.cyb.web.base.utils;
import java.io.Serializable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.collection.po.User;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月19日
 */
public class Query implements Serializable {
	    Log log = LogFactory.getLog(Query.class);
		 
	    private static final long serialVersionUID = 1L;
	    
	    public static void main(String[] args) {
	    	 
	        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
	        EntityManager entityManager = factory.createEntityManager();
	        Query query = Query.forClass(User.class, entityManager);
	        User user = new User(); 
	        // 保存
	        query.insert(user);
	        // 更新
	        query.update(user);
	        // 查询
	        @SuppressWarnings("unused")
			List<User> users = query.query();
	    }
	 
	    private EntityManager entityManager;
	 
	    /** 要查询的模型对象 */
	    @SuppressWarnings("rawtypes")
		private Class clazz;
	 
	    /** 查询条件列表 */
	    @SuppressWarnings("rawtypes")
		private Root from;
	 
	    private List<Predicate> predicates;
	 
	    @SuppressWarnings("rawtypes")
		private CriteriaQuery criteriaQuery;
	 
	    private CriteriaBuilder criteriaBuilder;
	 
	    /** 排序方式列表 */
	    private List<Order> orders;
	 
	    /** 关联模式 */
	    private Map<String, Query> subQuery;
	 
	    private Map<String, Query> linkQuery;
	 
	    private String projection;
	 
	    /** 或条件 */
	    @SuppressWarnings("unused")
		private List<Query> orQuery;
	 
	    private Query() {
	    }
	 
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		private Query(Class clazz, EntityManager entityManager) {
	        this.clazz = clazz;
	        this.entityManager = entityManager;
	        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
	        this.criteriaQuery = criteriaBuilder.createQuery(this.clazz);
	        this.from = criteriaQuery.from(this.clazz);
	        this.predicates = new ArrayList();
	        this.orders = new ArrayList();
	    }
	 
	    /** 通过类创建查询条件 */
	    @SuppressWarnings("rawtypes")
		public static Query forClass(Class clazz, EntityManager entityManager) {
	        return new Query(clazz, entityManager);
	    }
	 
	    /** 增加子查询 */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		private void addSubQuery(String propertyName, Query query) {
	        if (this.subQuery == null)
	            this.subQuery = new HashMap();
	 
	        if (query.projection == null)
	            throw new RuntimeException("子查询字段未设置");
	 
	        this.subQuery.put(propertyName, query);
	    }
	 
	    @SuppressWarnings("unused")
		private void addSubQuery(Query query) {
	        addSubQuery(query.projection, query);
	    }
	 
	    /** 增关联查询 */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public void addLinkQuery(String propertyName, Query query) {
	        if (this.linkQuery == null)
	            this.linkQuery = new HashMap();
	 
	        this.linkQuery.put(propertyName, query);
	    }
	 
	    /** 相等 */
	    public void eq(String propertyName, Object value) {
	        if (isEmpty(value))
	            return;
	        this.predicates.add(criteriaBuilder.equal(from.get(propertyName), value));
	    }
	 
	    public void or(List<String> propertyName, Object value) {
	        if (isEmpty(value))
	            return;
	        if ((propertyName == null) || (propertyName.size() == 0))
	            return;
	        Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(from.get(propertyName.get(0)), value));
	        for (int i = 1; i < propertyName.size(); ++i)
	            predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(from.get(propertyName.get(i)), value));
	        this.predicates.add(predicate);
	    }
	 
	    /** 空 */
	    public void isNull(String propertyName) {
	        this.predicates.add(criteriaBuilder.isNull(from.get(propertyName)));
	    }
	 
	    /** 非空 */
	    public void isNotNull(String propertyName) {
	        this.predicates.add(criteriaBuilder.isNotNull(from.get(propertyName)));
	    }
	 
	    /** 不相等 */
	    public void notEq(String propertyName, Object value) {
	        if (isEmpty(value)) {
	            return;
	        }
	        this.predicates.add(criteriaBuilder.notEqual(from.get(propertyName), value));
	    }
	 
	    /**
	     * not in
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param value
	     *            值集合
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public void notIn(String propertyName, Collection value) {
	        if ((value == null) || (value.size() == 0)) {
	            return;
	        }
	        Iterator iterator = value.iterator();
	        In in = criteriaBuilder.in(from.get(propertyName));
	        while (iterator.hasNext()) {
	            in.value(iterator.next());
	        }
	        this.predicates.add(criteriaBuilder.not(in));
	    }
	 
	    /**
	     * 模糊匹配
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param value
	     *            属性值
	     */
	    @SuppressWarnings("unchecked")
		public void like(String propertyName, String value) {
	        if (isEmpty(value))
	            return;
	        if (value.indexOf("%") < 0)
	            value = "%" + value + "%";
	        this.predicates.add(criteriaBuilder.like(from.get(propertyName), value));
	    }
	 
	    /**
	     * 时间区间查询
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param lo
	     *            属性起始值
	     * @param go
	     *            属性结束值
	     */
	    @SuppressWarnings("unchecked")
		public void between(String propertyName, Date lo, Date go) {
	        if (isNotEmpty(lo) && isNotEmpty(go)) {
	            this.predicates.add(criteriaBuilder.between(from.get(propertyName), lo, go));
	            return;
	        }
	 
	        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if (isNotEmpty(lo) && isEmpty(go)) {
	            this.predicates.add(criteriaBuilder.lessThan(from.get(propertyName), lo));// formatter.format(lo)
	            return;
	        }
	 
	        if (isNotEmpty(go)) {
	            this.predicates.add(criteriaBuilder.greaterThan(from.get(propertyName), go));
	        }
	 
	    }
	 
	    public void between(String propertyName, Number lo, Number go) {
	        if (!(isEmpty(lo)))
	            ge(propertyName, lo);
	 
	        if (!(isEmpty(go)))
	            le(propertyName, go);
	    }
	 
	    /**
	     * 小于等于
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param value
	     *            属性值
	     */
	    @SuppressWarnings("unchecked")
		public void le(String propertyName, Number value) {
	        if (isEmpty(value)) {
	            return;
	        }
	        this.predicates.add(criteriaBuilder.le(from.get(propertyName), value));
	    }
	 
	    /**
	     * 小于
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param value
	     *            属性值
	     */
	    @SuppressWarnings("unchecked")
		public void lt(String propertyName, Number value) {
	        if (isEmpty(value)) {
	            return;
	        }
	        this.predicates.add(criteriaBuilder.lt(from.get(propertyName), value));
	    }
	 
	    /**
	     * 大于等于
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param value
	     *            属性值
	     */
	    @SuppressWarnings("unchecked")
		public void ge(String propertyName, Number value) {
	        if (isEmpty(value)) {
	            return;
	        }
	        this.predicates.add(criteriaBuilder.ge(from.get(propertyName), value));
	    }
	 
	    /**
	     * 大于
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param value
	     *            属性值
	     */
	    @SuppressWarnings("unchecked")
		public void gt(String propertyName, Number value) {
	        if (isEmpty(value)) {
	            return;
	        }
	        this.predicates.add(criteriaBuilder.gt(from.get(propertyName), value));
	    }
	 
	    /**
	     * in
	     * 
	     * @param propertyName
	     *            属性名称
	     * @param value
	     *            值集合
	     */
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public void in(String propertyName, Collection value) {
	        if ((value == null) || (value.size() == 0)) {
	            return;
	        }
	        Iterator iterator = value.iterator();
	        In in = criteriaBuilder.in(from.get(propertyName));
	        while (iterator.hasNext()) {
	            in.value(iterator.next());
	        }
	        this.predicates.add(in);
	    }
	 
	    /** 直接添加JPA内部的查询条件,用于应付一些复杂查询的情况,例如或 */
	    public void addCriterions(Predicate predicate) {
	        this.predicates.add(predicate);
	    }
	 
	    /**
	     * 创建查询条件
	     * 
	     * @return JPA离线查询
	     */
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public CriteriaQuery newCriteriaQuery() {
	        criteriaQuery.where(predicates.toArray(new Predicate[0]));
	        if (this.orders != null) {
	            criteriaQuery.orderBy(orders);
	        }
	        addLinkCondition(this);
	        return criteriaQuery;
	    }
	 
	    @SuppressWarnings("rawtypes")
		private void addLinkCondition(Query query) {
	 
	        Map subQuery = query.linkQuery;
	        if (subQuery == null)
	            return;
	 
	        for (Iterator queryIterator = subQuery.keySet().iterator(); queryIterator.hasNext();) {
	            String key = (String) queryIterator.next();
	            Query sub = (Query) subQuery.get(key);
	            from.join(key);
	            criteriaQuery.where(sub.predicates.toArray(new Predicate[0]));
	            addLinkCondition(sub);
	        }
	    }
	 
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public void addOrder(String propertyName, String order) {
	        if (order == null || propertyName == null)
	            return;
	 
	        if (this.orders == null)
	            this.orders = new ArrayList();
	 
	        if (order.equalsIgnoreCase("asc"))
	            this.orders.add(criteriaBuilder.asc(from.get(propertyName)));
	        else if (order.equalsIgnoreCase("desc"))
	            this.orders.add(criteriaBuilder.desc(from.get(propertyName)));
	    }
	 
	    public void setOrder(String propertyName, String order) {
	        this.orders = null;
	        addOrder(propertyName, order);
	    }
	 
	    @SuppressWarnings("rawtypes")
		public Class getClazz() {
	        return this.clazz;
	    }
	 
	    public void setFetchModes(List<String> fetchField, List<String> fetchMode) {
	 
	    }
	 
	    // ====================================================
	    // ======================= 查询操作 =======================
	    // ====================================================
	    /** 每次批量操作数 */
	    private int batchSize = 50;
	 
	    /** 设置每次操作数 */
	    public void setBatchSize(int batchSize) {
	        this.batchSize = batchSize;
	    }
	 
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public <E> E get(Class clazz, Serializable id) {
	        return (E) entityManager.find(clazz, id);
	    }
	 
	    /**
	     * 统计记录
	     * 
	     * @param query
	     *            统计条件
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public Long getCount() {
	        Selection selection = criteriaQuery.getSelection();
	        criteriaQuery.select(criteriaBuilder.count(from));
	        Long count = (Long) entityManager.createQuery(newCriteriaQuery()).getResultList().get(0);
	        criteriaQuery.select(selection);
	        return count;
	    }
	 
	    /**
	     * 根据query查找记录
	     * 
	     * @param query
	     *            查询条件
	     * @param firstResult
	     *            起始行
	     * @param maxResults
	     *            结束行
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public <E extends Serializable> List<E> query(int firstResult, int maxResults) {
	        List result = entityManager.createQuery(newCriteriaQuery()).setFirstResult(firstResult)
	                .setMaxResults(maxResults).getResultList();
	        return result;
	    }
	 
	    /**
	     * 根据query查找记录
	     * 
	     * @param query
	     *            查询条件
	     */
	    @SuppressWarnings("unchecked")
		public <E extends Serializable> List<E> query() {
	        return entityManager.createQuery(newCriteriaQuery()).getResultList();
	    }
	 
	    /**
	     * 分页查询
	     * 
	     * @param query
	     *            查询条件
	     * @param pageNo
	     *            页号
	     * @param rowsPerPage
	     *            每页显示条数
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public Map<String, Object> queryPage(int pageNo, int rowsPerPage) {
	        log.debug(getClazz() + "-----开始查询,页码:" + pageNo + ",每页显示:" + rowsPerPage + "----");
	        log.debug("查询条件:");
	        for (Predicate cri : predicates)
	            log.debug(cri);
	 
	        int count = getCount().intValue();
	        int firstResult = calc(pageNo, rowsPerPage, count);
	        List result = entityManager.createQuery(newCriteriaQuery()).setFirstResult(firstResult)
	                .setMaxResults(rowsPerPage).getResultList();
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("count", count);
	        resultMap.put("pageNo", pageNo);
	        resultMap.put("rowsPerPage", rowsPerPage);
	        resultMap.put("result", result);
	        return resultMap;
	    }
	 
	    /**
	     * 计算起始条数
	     */
	    public int calc(int pageNo, int rowsPerPage, int count) {
	        if (pageNo <= 0)
	            pageNo = 1;
	        if (rowsPerPage <= 0)
	            rowsPerPage = 15;
	 
	        // 当把最后一页数据删除以后,页码会停留在最后一个上必须减一
	        int totalPageCount = count / rowsPerPage;
	        if (pageNo > totalPageCount && (count % rowsPerPage == 0)) {
	            pageNo = totalPageCount;
	        }
	        if (pageNo - totalPageCount > 2) {
	            pageNo = totalPageCount + 1;
	        }
	        int firstRow = (pageNo - 1) * rowsPerPage;
	        if (firstRow < 0) {
	            firstRow = 0;
	        }
	        return firstRow;
	    }
	 
	    /**
	     * 插入记录
	     * 
	     * @param entity
	     *            要插入的记录
	     */
	    @SuppressWarnings("rawtypes")
		public void insert(Object entity) {
	        if (entity instanceof List) {
	            insertList((List) entity);
	            return;
	        } else if (entity instanceof Object[]) {
	            return;
	        }
	        try {
	            entityManager.persist(entity);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /**
	     * 批量增加
	     * 
	     * @param list
	     *            要新增的数据
	     */
	    @SuppressWarnings("rawtypes")
		public void insertList(List list) {
	        if (list == null || list.size() == 0) {
	            return;
	        }
	        int i = 0;
	        for (Object o : list) {
	            insert(o);
	            if (i % batchSize == 0) {
	                entityManager.flush();
	            }
	            i++;
	        }
	        log.debug(list.get(0).getClass() + "批量增加数据" + i + "条");
	    }
	 
	    /**
	     * 更新记录
	     * 
	     * @param entity
	     *            要更新的记录
	     */
	    @SuppressWarnings("rawtypes")
		public void update(Object entity) {
	        if (entity instanceof List) {
	            this.updateList((List) entity);
	            return;
	        }
	        entityManager.merge(entity);
	    }
	 
	    /**
	     * 更新entityManager
	     */
	    @SuppressWarnings("rawtypes")
		public void updateList(List list) {
	        for (Object entity : list) {
	            this.update(entity);
	        }
	    }
	 
	    /**
	     * 删除记录
	     * 
	     * @param entity
	     *            要删除的记录
	     */
	    @SuppressWarnings("rawtypes")
		public void delete(Object entity) {
	        if (entity instanceof List) {
	            List list = (List) entity;
	            for (Object o : list) {
	                entityManager.remove(o);
	            }
	        } else {
	            entityManager.remove(entity);
	        }
	    }
	 
	    /**
	     * 根据ids删除数据
	     * 
	     * @param entity
	     *            删除实体类
	     * @param ids
	     *            删除条件
	     */
	    @SuppressWarnings("rawtypes")
		public void delete(Class entity, List ids) {
	        String idName = getPrimaryKeyName();
	        StringBuffer sb = new StringBuffer();
	        sb.append(idName + " in(");
	        for (int i = 0; i < ids.size(); i++) {
	            sb.append("'" + ids.get(i) + "',");
	        }
	        String jpqlCondition = sb.substring(0, sb.length() - 1) + ")";
	        delete(entity, jpqlCondition);
	    }
	 
	    @SuppressWarnings("rawtypes")
		public void delete(Class entity, String jpqlCondition) {
	        if (isEmpty(jpqlCondition)) {
	            jpqlCondition = "1=1";
	        }
	        int no = updateJpql("delete " + entity.getName() + " where " + jpqlCondition);
	        log.debug(entity.getName() + "删除" + no + "条数据");
	    }
	 
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public <E extends Serializable> List<E> query(String jpql, int firstResult, int maxResults) {
	        List result = entityManager.createQuery(jpql).setFirstResult(firstResult).setMaxResults(maxResults)
	                .getResultList();
	        return result;
	    }
	 
	    @SuppressWarnings("unchecked")
		public <E extends Serializable> List<E> queryBySql(String sql, int firstResult, int maxResults) {
	        return entityManager.createNativeQuery(sql).setFirstResult(firstResult).setMaxResults(maxResults)
	                .getResultList();
	    }
	 
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public <E extends Serializable> List<E> queryAll(Class clazz) {
	        CriteriaQuery<E> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);
	        criteriaQuery.from(clazz);
	        return entityManager.createQuery(criteriaQuery).getResultList();
	    }
	 
	    @SuppressWarnings("unchecked")
		public <E extends Serializable> List<E> query(String jpql) {
	        return entityManager.createQuery(jpql).getResultList();
	    }
	 
	    public Integer updateJpql(String jpql) {
	        return entityManager.createQuery(jpql).executeUpdate();
	    }
	 
	    public Integer updateSql(String sql) {
	        return entityManager.createNativeQuery(sql).executeUpdate();
	    }
	 
	    @SuppressWarnings("unchecked")
		public <E extends Serializable> List<E> queryBySql(String sql) {
	        return entityManager.createNativeQuery(sql).getResultList();
	    }
	 
	    /**
	     * 获得主键名称
	     * 
	     * @param clazz
	     *            操作是实体对象
	     * @param EntityManager
	     *            jpa的entityManager工厂
	     * @return 初建名称
	     * */
	    public String getPrimaryKeyName() {
	        @SuppressWarnings("unchecked")
			EntityType<?> entityType = entityManager.getMetamodel().entity(clazz);
	        return entityType.getId(entityType.getIdType().getJavaType()).getName();
	    }
	 
	    /**
	     * 判断一个对象是否为空。它支持如下对象类型：
	     * <ul>
	     * <li>null : 一定为空
	     * <li>字符串 : ""为空,多个空格也为空
	     * <li>数组
	     * <li>集合
	     * <li>Map
	     * <li>其他对象 : 一定不为空
	     * </ul>
	     * 
	     * @param obj
	     *            任意对象
	     * @return 是否为空
	     */
	    public final static boolean isEmpty(final Object obj) {
	        if (obj == null) {
	            return true;
	        }
	        if (obj instanceof String) {
	            return "".equals(String.valueOf(obj).trim());
	        }
	        if (obj.getClass().isArray()) {
	            return Array.getLength(obj) == 0;
	        }
	        if (obj instanceof Collection<?>) {
	            return ((Collection<?>) obj).isEmpty();
	        }
	        if (obj instanceof Map<?, ?>) {
	            return ((Map<?, ?>) obj).isEmpty();
	        }
	        return false;
	    }
	 
	    public final static boolean isNotEmpty(final Object obj) {
	        return !isEmpty(obj);
	    }
}
