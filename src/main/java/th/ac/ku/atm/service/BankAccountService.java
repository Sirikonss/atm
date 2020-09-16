package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class BankAccountService {
    private ArrayList<BankAccount> BankAccountList;

    @PostConstruct
    public void postConstruct() {
        this.BankAccountList = new ArrayList<>();
    }

    public void createBankAccount(BankAccount bankAccount) {
        BankAccountList.add(bankAccount);
    }

    public List<BankAccount> getBankAccount() {
        return new ArrayList<>(this.BankAccountList);
    }

    public BankAccount findBankAccount(int id) {
        for (BankAccount bankAccount: BankAccountList) {
            if (bankAccount.getId() == id)
                return bankAccount;
        }
        return null;
    }


}
