package com.yangle.service.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * programname: product_factory
 * <p>
 * title: 数据源路由
 *
 * @author: yishao
 * <p>
 * created: 2018-11-28 15:16
 **/
public class DynamicDataSource extends AbstractRoutingDataSource{

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    //主库 仅一个
    private DataSource master;
    //从库 可以多个
    private List<DataSource> slaves;
    private AtomicLong slaveCount = new AtomicLong();
    private int slaveSize;

    private Map<Object,Object> dataSources = new HashMap<Object, Object>();

    private static final ThreadLocal<LinkedList<String>> dataSourceHolder = new ThreadLocal<LinkedList<String>>(){
        @Override
        protected LinkedList<String> initialValue() {
            return new LinkedList<String>();
        }
    };

    /**
     * 初始化
     */
    @Override
    public void afterPropertiesSet() {
        if (null == master){
            throw new IllegalArgumentException("Property 'master' is required");
        }

        dataSources.put(DataSourceTypeEnum.MASTER.name(),master);

        if(null != slaves && slaves.size()>0){
            for (int i=0;i<slaves.size();i++){
                dataSources.put(DataSourceTypeEnum.SLAVE.name()+(i+1),slaves.get(i));
            }
            slaveSize = slaves.size();
        }

        this.setDefaultTargetDataSource(master);
        this.setTargetDataSources(dataSources);
        super.afterPropertiesSet();
    }

    /**
     * 选择使用主库，放到当前ThreadLocal的栈顶
     */
    public static void useMaser(){

        LinkedList<String> m = dataSourceHolder.get();
        m.offerFirst(DataSourceTypeEnum.MASTER.name());

    }

    /**
     * 选择使用从库，放到当前ThreadLocal的栈顶
     */
    public static void useSlave(){
        LinkedList<String> m = dataSourceHolder.get();
        m.offerFirst(DataSourceTypeEnum.SLAVE.name());
    }


    /**
     * 重置当前栈
     */
    public static void reset() {
        LinkedList<String> m = dataSourceHolder.get();
        if (m.size() > 0) {
            m.poll();
        }
    }


    /**
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {

        LinkedList<String> m = dataSourceHolder.get();

        String key = m.peekFirst() == null ? DataSourceTypeEnum.MASTER.name() : m.peekFirst();

        if(null!=key){
            if (DataSourceTypeEnum.MASTER.name().equalsIgnoreCase(key)){
                return key;
            }else if(DataSourceTypeEnum.SLAVE.name().equalsIgnoreCase(key)){

                if (slaveSize>1){
                    long c = slaveCount.incrementAndGet();
                    c = c % slaveSize;
                    return DataSourceTypeEnum.SLAVE.name()+(c+1);
                }else {
                    return DataSourceTypeEnum.SLAVE.name()+1;
                }

            }
            return null;
        }
        return null;
    }

    public DataSource getMaster() {
        return master;
    }

    public void setMaster(DataSource master) {
        this.master = master;
    }

    public List<DataSource> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<DataSource> slaves) {
        this.slaves = slaves;
    }
}
