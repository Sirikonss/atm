package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.BankAccountService;
import th.ac.ku.atm.service.CustomerService;


@Controller
@RequestMapping("bankaccount")
public class BankAccountController {

    private BankAccountService  BankAccountService;

    public BankAccountController(BankAccountService  BankAccountService) {
        this.BankAccountService = BankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("allBankAccount", BankAccountService.getBankAccount());
        return "BankAccount";
    }

    @PostMapping
    public String registerBankAccount(@ModelAttribute BankAccount BankAccount, Model model) {
        BankAccountService.createBankAccount(BankAccount);
        model.addAttribute("allBankAccount", BankAccountService.getBankAccount());
        return "redirect:bankaccount";
    }
}
