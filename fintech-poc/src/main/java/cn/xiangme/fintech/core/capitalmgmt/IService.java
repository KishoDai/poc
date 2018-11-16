package cn.xiangme.fintech.core.capitalmgmt;

import java.util.List;

/**
 * @author kisho
 */
public interface IService {

    /**
     * @return
     */
    Context save(Context context);

    List<Context> retrieveRecords4Compensation();

}
