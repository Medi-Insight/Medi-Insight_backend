package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.mapper.AccountMapper;
import ynu.mediinsight.MediInsightBackend.service.AccountService;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Override
    public Account findAccountByUsernameOrEmail(String text) {
        return this.query()
                .eq("username", text).or()
                .eq("email", text)
                .one();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByUsernameOrEmail(username);
        if (account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(username)
                .password(account.getPassword())
                .build();
    }
}