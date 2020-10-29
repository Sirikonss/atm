package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.BankAccountService;
import th.ac.ku.atm.service.CustomerService;


@Controller
@RequestMapping("bankaccount")
public class BankAccountController {


    private BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        accountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/deposit/{id}")
    public String getDepositBankAccountPage(@PathVariable int id, Model model) {
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-deposit";
    }

    @PostMapping("/deposit/{id}")
    public String deposit(@PathVariable int id,
                          @ModelAttribute BankAccount bankAccount,
                          Model model) {
        accountService.depositBankAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawBankAccountPage(@PathVariable int id, Model model) {
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-withdraw";
    }

    @PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable int id,
                           @ModelAttribute BankAccount bankAccount,
                           Model model) {
        accountService.withdrawBankAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }


    @GetMapping("/delete/{id}")
    public String getDeleteBankAccountPage(@PathVariable int id, Model model) {
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {

        accountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }




}
