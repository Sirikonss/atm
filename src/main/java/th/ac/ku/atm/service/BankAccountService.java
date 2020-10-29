package th.ac.ku.atm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class BankAccountService {

    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BankAccount> getCustomerBankAccount(int customerId) {
        String url = "http://bankaccount-api:8091/api/bankaccount/customer/" +
                customerId;
        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();

        return Arrays.asList(accounts);
    }

    private ArrayList<BankAccount> BankAccountList;

    @PostConstruct
    public void postConstruct() {
        this.BankAccountList = new ArrayList<>();
    }


    public BankAccount findBankAccount(int id) {
        for (BankAccount bankAccount: BankAccountList) {
            if (bankAccount.getId() == id)
                return bankAccount;
        }
        return null;
    }

    public void openAccount(BankAccount bankAccount) {
        String url = "http://bankaccount-api:8091/api/bankaccount";

        restTemplate.postForObject(url, bankAccount, BankAccount.class);
    }

    public List<BankAccount> getBankAccounts() {
        String url = "http://bankaccount-api:8091/api/bankaccount/";

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);
    }
    public BankAccount getBankAccount(int id) {
        String url = "http://bankaccount-api:8091/api/bankaccount/" + id;

        ResponseEntity<BankAccount> response =
                restTemplate.getForEntity(url, BankAccount.class);

        return response.getBody();
    }

//    public void editBankAccount(BankAccount bankAccount) {
//        String url = "http://bankaccount-api:8091/api/bankaccount/" +
//                bankAccount.getId();
//        restTemplate.put(url, bankAccount);
//    }

    public void depositBankAccount(BankAccount bankAccount) {
        String url = "http://bankaccount-api:8091/api/bankaccount/" +
                bankAccount.getId();
        BankAccount response = restTemplate.getForObject(url,BankAccount.class);
        response.deposit(bankAccount.getBalance());
        restTemplate.put(url,response);
    }

    public void withdrawBankAccount(BankAccount bankAccount) {
        String url = "http://bankaccount-api:8091/api/bankaccount/" +
                bankAccount.getId();
        BankAccount response = restTemplate.getForObject(url,BankAccount.class);
        response.withdraw(bankAccount.getBalance());
        restTemplate.put(url,response);
    }


    public void deleteBankAccount(BankAccount bankAccount) {
        String url = "http://bankaccount-api:8091/api/bankaccount/" +
                bankAccount.getId();
        restTemplate.delete(url, bankAccount);
    }





}
