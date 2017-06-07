package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountRoleMapping;

public interface AccountRoleMappingDao {
  
	 /**
     * This method pass AccountRoleMapping as input and get the {@link Integer} from database
     *
     * @param accountRoleMapping
     * @return {@link Integer}
     * @throws ProcessFailed the process failed
     */
    public UUID save(AccountRoleMapping accountRoleMapping) throws ProcessFailed;
    
    /**
     * This method pass Account as input and updates the {@link AccountRoleMapping} from database
     *
     * @param accountRoleMapping
     * @throws ProcessFailed the process failed
     */
    public void update(AccountRoleMapping accountRoleMapping) throws ProcessFailed;

    /**
     * This method pass Account as input and updates the {@link AccountRoleMapping} from database
     *
     * @param accountRoleMapping
     * @throws ProcessFailed the process failed
     */
    public void delete(AccountRoleMapping accountRoleMapping) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link AccountRoleMapping} from DAO layer
     *
     * @param id
     * @return {@link AccountRoleMapping}
     * @throws ProcessFailed the process failed
     */
    public AccountRoleMapping getByAccountId(UUID accountId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link AccountRoleMapping} from DAO layer
     *
     * @param id
     * @return {@link AccountRoleMapping}
     * @throws ProcessFailed the process failed
     */
    public List<AccountRoleMapping> getByRole(List<Integer> roleIds) throws ProcessFailed;
}
