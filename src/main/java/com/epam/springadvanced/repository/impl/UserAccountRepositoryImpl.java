package com.epam.springadvanced.repository.impl;

import com.epam.springadvanced.domain.entity.User;
import com.epam.springadvanced.domain.entity.UserAccount;
import com.epam.springadvanced.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author dmytro_adonin
 * @since 4/1/2016.
 */
@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

    private static final String SELECT_ACCOUNT_BY_USER_ID =
            "SELECT a.id, a.amount, u.id FROM account a JOIN user u ON a.user_id = u.id WHERE a.user_id = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE account SET amount = ? WHERE user_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserAccount getUserAccount(long userId) {
        return jdbcTemplate.queryForObject(SELECT_ACCOUNT_BY_USER_ID, accountMapper(), userId);
    }

    @Override
    public void changeAmount(long userId, float amount) {
        jdbcTemplate.update(UPDATE_ACCOUNT, amount, userId);
    }

    private RowMapper<UserAccount> accountMapper() {
        return (rs, rowNum) -> {
            UserAccount account = new UserAccount();
            account.setId(rs.getLong(1));
            account.setAmount(rs.getFloat(2));
            User user = new User();
            user.setId(rs.getLong(3));
            account.setUser(user);
            return account;
        };
    }

}
